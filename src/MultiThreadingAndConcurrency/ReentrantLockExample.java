package MultiThreadingAndConcurrency;

import java.util.concurrent.locks.ReentrantLock;

/*
===============================================================================

                        REENTRANT LOCK

===============================================================================

Problem

When multiple threads access the same shared resource simultaneously,
only one thread should modify it at a time.

Without synchronization,

↓

Two users may book the same ticket.

This leads to

❌ Race Condition

===============================================================================

Why ReentrantLock?

Java provides another synchronization mechanism apart from synchronized.

ReentrantLock

↓

Gives explicit control over locking and unlocking.

Unlike synchronized,

✔ Can manually lock/unlock

✔ Supports tryLock()

✔ Supports timeout

✔ Supports fairness policy

✔ Interruptible locking

Hence it is more flexible.

===============================================================================

Flow

User 1

↓

lock()

↓

Critical Section

↓

unlock()

↓

User 2 acquires lock

===============================================================================

Real World Example

Movie Ticket Booking

Available Seats = 1

Two users click

"Book Now"

at exactly the same time.

Without Lock

↓

Both may book

↓

Overselling Ticket

With ReentrantLock

↓

Only one user enters the booking section.

↓

Second user waits.

↓

No overselling.

===============================================================================

Advantages

✔ More Flexible than synchronized

✔ Supports tryLock()

✔ Supports timeout

✔ Supports fairness

✔ Interruptible locking

✔ Explicit lock management

===============================================================================

Disadvantages

✘ Must manually unlock()

✘ Forgetting unlock() causes deadlock

✘ Slightly more complex than synchronized

===============================================================================
*/

public class ReentrantLockExample {

    public static void main(String[] args) {

        TicketBooking bookingSystem = new TicketBooking();

        /*
            Two users trying
            to book simultaneously.
         */

        Thread user1 =
                new Thread(() ->
                        bookingSystem.bookTicket("User 1"));

        Thread user2 =
                new Thread(() ->
                        bookingSystem.bookTicket("User 2"));

        user1.start();
        user2.start();

        try {

            user1.join();
            user2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
===============================================================================

Shared Resource

Only one ticket is available.

===============================================================================
*/

class TicketBooking {

    /*
        Shared Resource

        Initially only one seat.
     */
    private int availableSeats = 1;

    /*
        Reentrant Lock

        Protects critical section.
     */
    private final ReentrantLock lock =
            new ReentrantLock();

    /*
        Booking Method

        Multiple threads may call
        this simultaneously.
     */
    public void bookTicket(String user) {

        System.out.println(user + " is trying to book...");

        /*
            Acquire Lock

            If another thread already owns
            the lock,

            Current thread waits.
         */
        lock.lock();

        try {

            System.out.println(user + " acquired lock.");

            /*
                Critical Section

                Only ONE thread can execute
                this block at a time.
             */
            if (availableSeats > 0) {

                System.out.println(user +
                        " successfully booked the ticket.");

                availableSeats--;

            } else {

                System.out.println(user +
                        " could not book. No seats left.");

            }

        } finally {

            /*
                Always release the lock.

                Even if exception occurs.

                Otherwise,

                Other threads wait forever.
             */
            System.out.println(user + " released the lock.");

            lock.unlock();
        }
    }
}

/*
===============================================================================

Dry Run

Available Seats = 1

Thread-1

↓

lock()

↓

Seat Available

↓

Books Ticket

↓

Seats = 0

↓

unlock()

↓

Thread-2

↓

lock()

↓

No Seats

↓

Booking Failed

↓

unlock()

===============================================================================

Possible Output

User 1 is trying to book...

User 2 is trying to book...

User 1 acquired lock.

User 1 successfully booked the ticket.

User 1 released the lock.

User 2 acquired lock.

User 2 could not book. No seats left.

User 2 released the lock.

===============================================================================

Visual Representation

Without Lock

Thread-1 ---- Book Seat

Thread-2 ---- Book Same Seat

Result

❌ Duplicate Booking

--------------------------------------------------

With ReentrantLock

Thread-1

↓

LOCK

↓

Book Seat

↓

UNLOCK

↓

Thread-2

↓

LOCK

↓

No Seat Available

↓

UNLOCK

===============================================================================

Interview Questions

Q) Why use ReentrantLock instead of synchronized?

ReentrantLock provides additional features like

✔ tryLock()

✔ lockInterruptibly()

✔ timeout

✔ fairness policy

✔ explicit lock/unlock

whereas synchronized automatically acquires and releases locks
but offers less flexibility.

-------------------------------------------------------------------------------

Q) Why unlock inside finally?

Because even if an exception occurs,

the lock is always released.

Otherwise,

other threads remain blocked forever.

-------------------------------------------------------------------------------

Q) What happens if unlock() is forgotten?

The thread never releases the lock.

Other threads wait forever.

↓

Deadlock.

===============================================================================

Production Use Cases

✔ Banking Transactions

✔ Ticket Booking Systems

✔ Inventory Management

✔ Payment Processing

✔ ATM Systems

✔ Hotel Booking

✔ Airline Reservation

✔ Shared Cache Updates

===============================================================================

Comparison

synchronized

✔ Simple

✔ Automatic Lock Release

✔ Less Flexible

--------------------------------------------------

ReentrantLock

✔ Manual Lock Management

✔ tryLock()

✔ Timeout

✔ Fair Locking

✔ Interruptible

✔ Production Friendly

===============================================================================

One-Line Interview Summary

ReentrantLock is an advanced locking mechanism that provides explicit
control over locking and unlocking, offering greater flexibility,
fairness, interruptibility, and timeout support compared to synchronized.

===============================================================================

 */