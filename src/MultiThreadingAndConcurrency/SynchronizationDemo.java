package MultiThreadingAndConcurrency;

/*
===============================================================================

            SYNCHRONIZED METHOD vs BLOCK vs VOLATILE

===============================================================================

Definition
-----------
Java provides multiple mechanisms for thread safety.

1. synchronized Method
   → Locks the entire method.

2. synchronized Block
   → Locks only critical code.

3. volatile Keyword
   → Guarantees visibility only.

===============================================================================

Problem
--------
Multiple threads accessing shared data can cause:

✘ Race Conditions

✘ Lost Updates

✘ Inconsistent Results

Example

Thread 1

count++

Thread 2

count++

Expected

2

Actual

1

===============================================================================

Solutions

synchronized Method
↓

Lock Entire Method

----------------------------------

synchronized Block
↓

Lock Critical Section

----------------------------------

volatile
↓

No Lock

Only Visibility

===============================================================================

Real World Examples

synchronized Method

✔ Bank Account

✔ Wallet

✔ Inventory

----------------------------------

synchronized Block

✔ Shopping Cart

✔ Cache Update

✔ Shared List

----------------------------------

volatile

✔ Server Running Flag

✔ Stop Thread Flag

✔ Config Refresh

===============================================================================
*/

// ============================================================================
// SYNCHRONIZED METHOD
// ============================================================================

class SynchronizedMethodCounter {

    private int count = 0;

    /*
    Entire method is locked.

    Only ONE thread can execute
    increment() at a time.
    */

    public synchronized void increment() {

        count++;

    }

    public synchronized int getCount() {

        return count;

    }
}

/*
===============================================================================

Flow

Thread 1

↓

Lock Object

↓

increment()

↓

Unlock

↓

Thread 2 enters

===============================================================================

Advantages

✔ Simple

✔ Thread Safe

✔ Easy to Understand

Disadvantages

✘ Locks whole method

✘ Lower performance

===============================================================================
*/


// ============================================================================
// SYNCHRONIZED BLOCK
// ============================================================================

class SynchronizedBlockCounter {

    private int count = 0;

    private final Object lock = new Object();

    public void increment() {

        /*
            Lock ONLY critical section.
        */

        synchronized (lock) {

            count++;

        }
    }

    public int getCount() {

        return count;

    }
}

/*
===============================================================================

Flow

Method Starts

↓

Non-critical code runs freely

↓

Lock acquired

↓

count++

↓

Unlock

↓

Continue

===============================================================================

Advantages

✔ Better Performance

✔ Smaller Lock Scope

✔ More Flexible

Disadvantages

✘ Slightly Complex

===============================================================================
*/


// ============================================================================
// VOLATILE
// ============================================================================

class SharedFlag {

    /*
        Visible immediately
        to all threads.
     */

    volatile boolean running = true;

    public void stopServer() {

        running = false;

    }

    public boolean isRunning() {

        return running;

    }
}

/*
===============================================================================

Flow

Thread 1

running = false

↓

Main Memory Updated

↓

Thread 2 immediately sees false

===============================================================================

IMPORTANT

volatile DOES NOT solve:

count++

Because

count++

↓

READ

↓

INCREMENT

↓

WRITE

Still causes Race Condition.

===============================================================================

Advantages

✔ Fast

✔ No Lock

✔ Immediate Visibility

Disadvantages

✘ No Atomicity

✘ No Thread Safety

===============================================================================
*/


// ============================================================================
// CLIENT CODE
// ============================================================================

public class SynchronizationDemo {

    public static void main(String[] args)
            throws InterruptedException {

        /*
        ===============================================================

        SYNCHRONIZED METHOD

        ===============================================================
        */

        SynchronizedMethodCounter methodCounter =
                new SynchronizedMethodCounter();

        Runnable task1 = () -> {

            for (int i = 0; i < 1000; i++) {

                methodCounter.increment();

            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task1);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(
                "Synchronized Method Count : "
                        + methodCounter.getCount()
        );


        /*
        ===============================================================

        SYNCHRONIZED BLOCK

        ===============================================================
        */

        SynchronizedBlockCounter blockCounter =
                new SynchronizedBlockCounter();

        Runnable task2 = () -> {

            for (int i = 0; i < 1000; i++) {

                blockCounter.increment();

            }
        };

        Thread t3 = new Thread(task2);
        Thread t4 = new Thread(task2);

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println(
                "Synchronized Block Count : "
                        + blockCounter.getCount()
        );


        /*
        ===============================================================

        VOLATILE

        ===============================================================
        */

        SharedFlag flag = new SharedFlag();

        System.out.println(
                "Server Running : "
                        + flag.isRunning()
        );

        flag.stopServer();

        System.out.println(
                "Server Running : "
                        + flag.isRunning()
        );
    }
}

/*
===============================================================================

OUTPUT

Synchronized Method Count : 2000

Synchronized Block Count : 2000

Server Running : true

Server Running : false

===============================================================================

DIFFERENCE TABLE

Feature               Method     Block      Volatile

Lock Entire Method      ✔           ✘            ✘

Lock Critical Section   ✘           ✔            ✘

Visibility              ✔           ✔            ✔

Atomicity               ✔           ✔            ✘

Race Condition Safe     ✔           ✔            ✘

Performance             Medium      Better       Fastest

===============================================================================

Interview Questions

Q1. Difference between synchronized method and block?

Method

Locks entire method.

Block

Locks only critical section.

Block provides better performance.

------------------------------------------------

Q2. Does volatile prevent race conditions?

No.

volatile provides only visibility.

It does not provide atomicity.

------------------------------------------------

Q3. Which is faster?

volatile

↓

synchronized Block

↓

synchronized Method

===============================================================================

One-Line Interview Summary

synchronized Method locks the entire method,
synchronized Block locks only the critical section,
and volatile guarantees visibility without locking.

===============================================================================
*/