package MultiThreadingAndConcurrency;

import java.util.concurrent.*;

/*
===============================================================================

                        RACE CONDITION

===============================================================================

Definition
-----------
A Race Condition occurs when two or more threads access and modify the
same shared resource simultaneously without proper synchronization.

Since multiple threads "race" to update the same data, the final result
becomes unpredictable.

===============================================================================

Real World Example
-------------------

Suppose an Amazon application stores the number of products sold.

Current Purchase Count

1000

Two customers purchase a product at exactly the same time.

Thread 1

Reads

1000

Thread 2

Also Reads

1000

Thread 1

Adds 1

1001

Thread 2

Adds 1

1001

Expected

1002

Actual

1001

One update is lost.

This is called

Race Condition.

===============================================================================

Problem

count++

looks like one statement.

But internally Java performs

READ

↓

INCREMENT

↓

WRITE

These three operations are NOT atomic.

Another thread can interrupt between them.

===============================================================================

Flow

Thread 1

↓

Read count = 100

↓

Increment = 101

↓

Waiting...

----------------------------------

Thread 2

↓

Read count = 100

↓

Increment = 101

↓

Write 101

----------------------------------

Thread 1 resumes

↓

Write 101

Final Value

101

Expected

102

===============================================================================

Solution

Protect the shared resource using

✔ synchronized

✔ Lock

✔ AtomicInteger

These ensure only one thread updates the shared data at a time.

===============================================================================

Advantages

✔ Demonstrates concurrency problems

✔ Helps understand synchronization

✔ Foundation for thread safety

===============================================================================

Disadvantages

✘ Incorrect results

✘ Lost updates

✘ Difficult debugging

✘ Unpredictable behavior

===============================================================================
*/

// ===================================================================
// Shared Resource
// ===================================================================

class PurchaseCounter {

    /*
    Shared variable accessed by multiple threads.
    */
    private int count = 0;

    /*
    increment()

    count++

    is NOT atomic.

    Internally

    READ

    ↓

    INCREMENT

    ↓

    WRITE
    */

    public void increment() {

        count++;

    }

    /*
    Returns final counter value.
    */

    public int getCount() {

        return count;

    }

}

// ===================================================================
// Client Code
// ===================================================================

public class RaceConditionDemo {

    public static void main(String[] args)
            throws InterruptedException {

        /*
        ===============================================================

        Shared Counter

        Both threads modify the SAME object.

        ===============================================================
        */

        PurchaseCounter counter =
                new PurchaseCounter();

        /*
        ===============================================================

        Runnable Task

        Each thread increments

        1000 times.

        ===============================================================
        */

        Runnable task = () -> {

            for (int i = 0; i < 1000; i++) {

                counter.increment();

            }

        };

        /*
        ===============================================================

        Create Two Threads

        ===============================================================
        */

        Thread thread1 = new Thread(task);

        Thread thread2 = new Thread(task);

        /*
        ===============================================================

        Start Both Threads

        Both execute simultaneously.

        ===============================================================
        */

        thread1.start();

        thread2.start();

        /*
        ===============================================================

        Wait for both threads.

        ===============================================================
        */

        thread1.join();

        thread2.join();

        /*
        ===============================================================

        Expected

        2000

        Actual

        Usually less than 2000 because of Race Condition.

        ===============================================================
        */

        System.out.println("Final Count : "
                + counter.getCount());

    }

}

/*
===============================================================================

Dry Run

Initial Count

0

Thread 1

↓

Read 0

↓

Increment 1

↓

Waiting...

-------------------------------------

Thread 2

↓

Read 0

↓

Increment 1

↓

Write 1

-------------------------------------

Thread 1 resumes

↓

Write 1

Final Count

1

Expected

2

Lost Update

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

Start Both

↓

Both Increment

1000 Times

↓

Race Condition Happens

↓

join()

↓

Print Final Count

===============================================================================

OUTPUT (Example)

Final Count : 1978

or

Final Count : 1993

or

Final Count : 2000

(The output is NOT guaranteed because thread scheduling is unpredictable.)

===============================================================================

Interview Questions

Q1. What is a Race Condition?

Ans:

A Race Condition occurs when multiple threads simultaneously access and
modify shared data without synchronization, producing unpredictable
results.

------------------------------------------------

Q2. Why is count++ unsafe?

Ans:

count++ is not atomic.

Internally it performs

READ

↓

INCREMENT

↓

WRITE

Another thread can interrupt between these operations.

------------------------------------------------

Q3. How do we prevent Race Conditions?

Ans:

✔ synchronized

✔ ReentrantLock

✔ AtomicInteger

✔ Concurrent Collections

------------------------------------------------

Q4. Why does the output change every execution?

Ans:

Because the Operating System schedules threads differently every time.

Thread execution order is non-deterministic.

===============================================================================

Production Use Cases

Race Conditions commonly occur in

✔ Banking Transactions

✔ Inventory Management

✔ Ticket Booking Systems

✔ Payment Gateways

✔ Shopping Carts

✔ Shared Counters

✔ Multiplayer Games

===============================================================================

Modern Java

Instead of manually protecting shared variables,

Java provides

✔ AtomicInteger

✔ ReentrantLock

✔ ConcurrentHashMap

✔ synchronized

to build thread-safe applications.

===============================================================================

One-Line Interview Summary

A Race Condition occurs when multiple threads simultaneously modify the
same shared resource without synchronization, leading to lost updates,
incorrect results, and unpredictable program behavior.

===============================================================================

 */