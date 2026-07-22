package MultiThreadingAndConcurrency;

import java.util.concurrent.Semaphore;

/*
===============================================================================

                            SEMAPHORE

===============================================================================

Problem

Sometimes we don't want to allow ALL threads
to access a shared resource.

Instead,

we want to allow

ONLY a LIMITED number of threads.

Examples

Netflix

Only 4 devices can stream simultaneously.

--------------------------------------------------

TUF+

Maximum 2 logged-in devices.

--------------------------------------------------

Database Connection Pool

Only 20 database connections available.

--------------------------------------------------

Parking Lot

Only 100 parking slots.

--------------------------------------------------

Printer

Only 2 printers shared by an office.

Using synchronized

↓

Only ONE thread is allowed.

But sometimes

we need

2

5

10

or

100

threads simultaneously.

===============================================================================

Solution

Semaphore

↓

Maintains

PERMITS

Every permit represents

permission to access a resource.

Example

Semaphore(2)

↓

2 permits available

Thread-1

↓

Gets Permit

Thread-2

↓

Gets Permit

Thread-3

↓

Waits (or fails using tryAcquire())

Until a permit is released.

===============================================================================

Flow

Thread-1

↓

Acquire Permit

↓

Work

↓

Release Permit

────────────────────────────

Thread-2

↓

Acquire Permit

↓

Work

↓

Release Permit

────────────────────────────

Thread-3

↓

Wait

↓

Gets permit after release

===============================================================================

Real World Example

TUF+ Subscription

Maximum Devices Allowed = 2

User-A

↓

Login

✔ Allowed

────────────────────────────

User-B

↓

Login

✔ Allowed

────────────────────────────

User-C

↓

Login

❌ Denied

Too many devices logged in.

After User-A logs out,

↓

User-C can log in.

===============================================================================

Advantages

✔ Limits concurrent access

✔ Controls limited resources

✔ Simple API

✔ Production Ready

===============================================================================

Disadvantages

✘ Forgetting release() causes permit leaks

✘ Too many permits reduce protection

✘ Harder to debug if permits are mismanaged

===============================================================================
*/

public class SemaphoreExample {

    public static void main(String[] args)
            throws InterruptedException {

        /*
            Account allows only

            TWO

            simultaneous devices.
         */

        TUFPlusAccount account =
                new TUFPlusAccount(2);

        Thread user1 =
                new Thread(() ->
                        trySession(account, "User-A"));

        Thread user2 =
                new Thread(() ->
                        trySession(account, "User-B"));

        Thread user3 =
                new Thread(() ->
                        trySession(account, "User-C"));

        user1.start();
        user2.start();

        /*
            Small delay so first two
            occupy permits.
         */
        Thread.sleep(100);

        user3.start();

        user1.join();
        user2.join();
        user3.join();
    }

    /*
        Helper Method

        Simulates a user session.
     */
    private static void trySession(
            TUFPlusAccount account,
            String user) {

        try {

            if (account.login(user)) {

                /*
                    Simulate
                    watching a course.
                 */
                Thread.sleep(500);

                account.logout(user);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
===============================================================================

Shared Resource

TUF+ Login Slots

Protected using Semaphore.

===============================================================================
*/

class TUFPlusAccount {

    /*
        Semaphore with

        fixed permits.

        Example

        2 Devices
     */
    private final Semaphore deviceSlots;

    public TUFPlusAccount(int maxDevices) {

        deviceSlots =
                new Semaphore(maxDevices);
    }

    /*
        Login

        tryAcquire()

        Attempts to grab permit
        immediately.

        Returns

        true

        or

        false

        without waiting.
     */
    public boolean login(String user)
            throws InterruptedException {

        System.out.println(
                user + " trying to log in...");

        if (deviceSlots.tryAcquire()) {

            System.out.println(
                    user + " successfully logged in.");

            return true;

        } else {

            System.out.println(
                    user + " denied login - maximum devices reached.");

            return false;
        }
    }

    /*
        Logout

        Releases permit.

        Another waiting user
        can now access.
     */
    public void logout(String user) {

        System.out.println(
                user + " logging out.");

        deviceSlots.release();
    }
}

/*
===============================================================================

Dry Run

Semaphore = 2

Permits Available = 2

────────────────────────────

User-A

↓

Acquire Permit

↓

Permit Left = 1

────────────────────────────

User-B

↓

Acquire Permit

↓

Permit Left = 0

────────────────────────────

User-C

↓

No Permit

↓

Login Denied

────────────────────────────

User-A

↓

Logout

↓

Release Permit

↓

Permit Left = 1

===============================================================================

Possible Output

User-A trying to log in...

User-A successfully logged in.

User-B trying to log in...

User-B successfully logged in.

User-C trying to log in...

User-C denied login - maximum devices reached.

User-A logging out.

User-B logging out.

===============================================================================

Visual Representation

Semaphore(2)

Available Permits = 2

────────────────────────────

User-A

↓

Acquire

Permit = 1

────────────────────────────

User-B

↓

Acquire

Permit = 0

────────────────────────────

User-C

↓

No Permit

↓

Denied

────────────────────────────

User-A Logout

↓

Release

Permit = 1

===============================================================================

Interview Questions

Q) What is Semaphore?

Semaphore is a synchronization utility that controls
how many threads can access a shared resource
simultaneously using permits.

-------------------------------------------------------------------------------

Q) Difference between Semaphore and synchronized?

synchronized

✔ Only ONE thread.

Semaphore

✔ Configurable number of threads.

Example

Semaphore(5)

↓

Five threads can execute together.

-------------------------------------------------------------------------------

Q) Difference between acquire() and tryAcquire()?

acquire()

✔ Waits until permit becomes available.

tryAcquire()

✔ Does NOT wait.

✔ Immediately returns

true

or

false.

-------------------------------------------------------------------------------

Q) What happens if release() is forgotten?

Permit is never returned.

Eventually,

all permits become occupied.

New threads can never acquire permits.

This is called

Permit Leak.

===============================================================================

Production Use Cases

✔ Netflix Device Limits

✔ TUF+ Multiple Device Login

✔ Database Connection Pools

✔ Printer Sharing

✔ Parking Lots

✔ API Rate Limiting

✔ Resource Pooling

✔ Thread Limiting

===============================================================================

Comparison

synchronized

✔ One Thread

✔ Mutual Exclusion

--------------------------------------------------

ReentrantLock

✔ One Thread

✔ Advanced Features

--------------------------------------------------

ReadWriteLock

✔ Multiple Readers

✔ One Writer

--------------------------------------------------

Semaphore

✔ Multiple Threads

✔ Limited by Permits

===============================================================================

One-Line Interview Summary

Semaphore is a synchronization mechanism that limits
the number of threads accessing a shared resource
simultaneously using permits, making it ideal for
resource pools, login limits, connection pools,
and rate limiting.

===============================================================================

 */