package MultiThreadingAndConcurrency;

import java.util.concurrent.atomic.AtomicInteger;

/*
===============================================================================

                        ATOMICINTEGER

===============================================================================

Definition
-----------
AtomicInteger is a thread-safe class provided by Java that allows multiple
threads to safely update an integer without using synchronized or locks.

Instead of locking threads, it internally uses

CAS (Compare-And-Swap)

making it much faster for simple operations.

Package

java.util.concurrent.atomic

===============================================================================

Problem
--------
Suppose multiple users like a YouTube video simultaneously.

Current Likes

100

Thread 1

Read 100

↓

101

----------------------------------

Thread 2

Read 100

↓

101

Expected

102

Actual

101

One like is lost.

This is a

Race Condition.

===============================================================================

Traditional Solution

Use

synchronized

↓

Only one thread enters.

↓

Correct Result

↓

Performance decreases because of locking.

===============================================================================

Better Solution

Use

AtomicInteger

↓

No explicit locks

↓

Uses CAS

↓

Much Faster

↓

Thread Safe

===============================================================================

What is CAS?
------------

CAS

Compare And Swap

Step 1

Read Current Value

↓

Step 2

Compare Current Value

↓

Step 3

If unchanged

↓

Update Value

Else

↓

Retry

===============================================================================

Real World Example

Instagram Likes

Facebook Reactions

YouTube Views

Website Visitors

API Request Counter

Shopping Cart Counter

Inventory Count

===============================================================================

Advantages

✔ Lock-Free

✔ Thread Safe

✔ Faster than synchronized

✔ Better CPU Utilization

✔ Production Ready

===============================================================================

Disadvantages

✘ Only works for simple atomic operations

✘ Not suitable for complex business logic

✘ Multiple variables cannot be updated atomically

===============================================================================
*/

// ============================================================================
// Shared Counter using AtomicInteger
// ============================================================================

class PurchaseAtomicCounter {

    /*
    AtomicInteger

    Thread-safe integer.

    Initial Value

    0
    */

    private final AtomicInteger likes =
            new AtomicInteger(0);

    /*
    ===============================================================

    incrementLikes()

    Uses CAS

    Compare

    And

    Swap

    ===============================================================
    */

    public void incrementLikes() {

        int previousValue;

        int nextValue;

        do {

            /*
            Step 1

            Read Current Value
            */

            previousValue = likes.get();

            /*
            Step 2

            Calculate Next Value
            */

            nextValue = previousValue + 1;

            /*
            Step 3

            compareAndSet()

            If

            Current Value == previousValue

            Update

            Otherwise

            Retry

            */

        }

        while (!likes.compareAndSet(previousValue, nextValue));

    }

    /*
    Returns latest counter value.
    */

    public int getCount() {

        return likes.get();

    }

}


// ============================================================================
// Client Code
// ============================================================================

public class AtomicIntegerDemo {

    public static void main(String[] args)
            throws InterruptedException {

        PurchaseAtomicCounter counter =
                new PurchaseAtomicCounter();

        /*
        ===============================================================

        Runnable Task

        Each thread increments

        1000 times.

        ===============================================================
        */

        Runnable task = () -> {

            for (int i = 0; i < 1000; i++) {

                counter.incrementLikes();

            }

        };

        /*
        ===============================================================

        Create Two Threads

        ===============================================================
        */

        Thread thread1 =
                new Thread(task);

        Thread thread2 =
                new Thread(task);

        /*
        ===============================================================

        Start Both Threads

        ===============================================================
        */

        thread1.start();

        thread2.start();

        /*
        ===============================================================

        Wait until both threads finish.

        ===============================================================
        */

        thread1.join();

        thread2.join();

        /*
        ===============================================================

        Expected

        2000

        AtomicInteger guarantees correctness.

        ===============================================================
        */

        System.out.println(
                "Final Likes : "
                        + counter.getCount());

    }

}

/*
===============================================================================

Dry Run

Initial Value

0

----------------------------------

Thread 1

Read

0

↓

Next

1

↓

CAS Success

↓

Value becomes

1

----------------------------------

Thread 2

Read

1

↓

Next

2

↓

CAS Success

↓

Value becomes

2

----------------------------------

Suppose

Both threads read

5

Thread 1

CAS Success

↓

Value

6

Thread 2

CAS Fails

↓

Reads Again

↓

CAS Success

↓

Value

7

No data loss.

===============================================================================

Flow Diagram

Thread 1

↓

Read Value

↓

Calculate Next

↓

CAS

↓

Success

↓

Done

----------------------------------

Thread 2

↓

Read Value

↓

CAS Failed

↓

Retry

↓

CAS Success

↓

Done

===============================================================================

Timeline

Main Thread

↓

Create Counter

↓

Create Thread 1

↓

Create Thread 2

↓

Start Threads

↓

Both Execute CAS

↓

Retry if Needed

↓

join()

↓

Print Count

===============================================================================

OUTPUT

Final Likes : 2000

===============================================================================

Difference

Normal Integer

count++

↓

Race Condition

↓

Incorrect Result

----------------------------------

synchronized

↓

Locks Thread

↓

Correct Result

↓

Slower

----------------------------------

AtomicInteger

↓

CAS

↓

No Locks

↓

Correct Result

↓

Fast

===============================================================================

Interview Questions

Q1. What is AtomicInteger?

Ans:

AtomicInteger is a thread-safe integer that performs atomic operations
without using explicit synchronization.

------------------------------------------------

Q2. What is CAS?

Ans:

CAS (Compare-And-Swap) compares the current value with the expected value.
If they match, it updates the value atomically; otherwise, it retries.

------------------------------------------------

Q3. Why is AtomicInteger faster than synchronized?

Ans:

AtomicInteger is lock-free and uses CPU-level CAS instructions, avoiding
the overhead of locking and unlocking threads.

------------------------------------------------

Q4. When should we use AtomicInteger?

Ans:

✔ Like Counters

✔ View Counters

✔ Request Counters

✔ Inventory Count

✔ Statistics

✔ Rate Limiting

------------------------------------------------

Q5. When should we NOT use AtomicInteger?

Ans:

When multiple variables need to be updated together or when business logic
requires locking across several operations.

===============================================================================

Production Use Cases

✔ YouTube Likes

✔ Instagram Followers

✔ API Request Counter

✔ Active User Counter

✔ Inventory Count

✔ Visitor Count

✔ Rate Limiter

===============================================================================

Modern Java

AtomicInteger is widely used in high-performance concurrent applications.

Other atomic classes include

✔ AtomicLong

✔ AtomicBoolean

✔ AtomicReference

for lock-free thread-safe programming.

===============================================================================

One-Line Interview Summary

AtomicInteger is a lock-free, thread-safe integer that uses the
Compare-And-Swap (CAS) algorithm to perform atomic updates without
using synchronized blocks or explicit locks.

===============================================================================

 */