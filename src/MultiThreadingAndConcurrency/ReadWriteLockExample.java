package MultiThreadingAndConcurrency;

import java.util.concurrent.locks.*;

/*
===============================================================================

                        READ WRITE LOCK

===============================================================================

Problem

Sometimes multiple threads only READ data,
while very few threads MODIFY it.

Example

Stock Price

Bank Balance

Configuration

Product Catalog

Weather Data

Using synchronized,

↓

Only ONE thread can access the resource.

Even readers have to wait.

This reduces performance.

===============================================================================

Solution

ReadWriteLock

↓

Allows

✔ Multiple Readers

✔ One Writer

at the same time.

Rules

✔ Many threads can READ together.

✔ Only one thread can WRITE.

✔ During writing,

  No other reader or writer can access the resource.

===============================================================================

Flow

Thread-1

↓

Read Lock

↓

Reading

────────────────────────────

Thread-2

↓

Read Lock

↓

Reading

────────────────────────────

Thread-3

↓

Write Lock

↓

Waits until readers finish

↓

Updates Data

===============================================================================

Real World Example

Stock Market

Thousands of users

↓

Read current stock price

Only stock exchange server

↓

Updates stock price

Instead of blocking thousands of readers,

ReadWriteLock allows

✔ Unlimited Readers

✔ One Writer

===============================================================================

Advantages

✔ Better Performance

✔ Multiple Readers simultaneously

✔ Exclusive Writer

✔ Ideal for Read-Heavy applications

===============================================================================

Disadvantages

✘ More complex than synchronized

✘ Writer may wait if readers keep reading

✘ Slight overhead

===============================================================================
*/

public class ReadWriteLockExample {

    public static void main(String[] args)
            throws InterruptedException {

        StockData stock = new StockData();

        /*
            Reader Threads
         */
        Thread reader1 =
                new Thread(stock::readPrice, "Reader-1");

        Thread reader2 =
                new Thread(stock::readPrice, "Reader-2");

        Thread reader3 =
                new Thread(stock::readPrice, "Reader-3");

        /*
            Writer Thread
         */
        Thread writer =
                new Thread(() ->
                        stock.updatePrice(125.50),
                        "Writer");

        reader1.start();
        reader2.start();
        reader3.start();

        reader1.join();
        reader2.join();
        reader3.join();

        writer.start();

        writer.join();

        /*
            Read Updated Value
         */
        Thread reader4 =
                new Thread(stock::readPrice,
                        "Reader-4");

        reader4.start();
        reader4.join();
    }
}

/*
===============================================================================

Shared Resource

Stock Price

Protected using ReadWriteLock.

===============================================================================
*/

class StockData {

    /*
        Shared Data
     */
    private double price = 100.0;

    /*
        ReadWrite Lock

        Provides

        Read Lock

        Write Lock
     */
    private final ReadWriteLock lock =
            new ReentrantReadWriteLock();

    /*
        Writer

        Only ONE writer allowed.
     */
    public void updatePrice(double newPrice) {

        /*
            Acquire Write Lock

            Blocks all readers and writers.
         */
        lock.writeLock().lock();

        try {

            System.out.println(
                    Thread.currentThread().getName()
                            + " updating price to "
                            + newPrice);

            price = newPrice;

        } finally {

            /*
                Always release lock.
             */
            lock.writeLock().unlock();
        }
    }

    /*
        Reader

        Multiple readers
        can execute together.
     */
    public void readPrice() {

        /*
            Acquire Read Lock
         */
        lock.readLock().lock();

        try {

            System.out.println(
                    Thread.currentThread().getName()
                            + " read price : "
                            + price);

        } finally {

            /*
                Release Read Lock
             */
            lock.readLock().unlock();
        }
    }
}

/*
===============================================================================

Dry Run

Initial Price = 100

Reader-1

↓

Read Lock

↓

Reads 100

────────────────────────────

Reader-2

↓

Read Lock

↓

Reads 100

────────────────────────────

Reader-3

↓

Read Lock

↓

Reads 100

────────────────────────────

Writer

↓

Waits

↓

Readers finish

↓

Write Lock

↓

Updates Price

↓

125.50

────────────────────────────

Reader-4

↓

Reads Updated Price

↓

125.50

===============================================================================

Possible Output

Reader-1 read price : 100.0

Reader-2 read price : 100.0

Reader-3 read price : 100.0

Writer updating price to 125.5

Reader-4 read price : 125.5

===============================================================================

Visual Representation

Without ReadWriteLock

Reader

↓

Wait

↓

Reader

↓

Wait

↓

Writer

↓

Wait

↓

Reader

Only ONE thread works.

--------------------------------------------------

With ReadWriteLock

Reader-1

██████

Reader-2

██████

Reader-3

██████

Writer

          ████

Reader-4

              ████

Multiple readers execute together.

===============================================================================

Interview Questions

Q) Why use ReadWriteLock?

ReadWriteLock improves performance by allowing multiple readers
to access shared data simultaneously while ensuring only one
writer modifies the data at a time.

-------------------------------------------------------------------------------

Q) When should we use ReadWriteLock?

Use it when

✔ Reads are frequent

✔ Writes are rare

Examples

• Product Catalog

• Stock Market

• Configuration Data

• Cache

• Banking Balance Lookup

-------------------------------------------------------------------------------

Q) Difference between Lock and ReadWriteLock?

Lock

✔ Only one thread allowed.

ReadWriteLock

✔ Multiple Readers

✔ Single Writer

Better throughput for read-heavy systems.

===============================================================================

Production Use Cases

✔ Banking Systems

✔ Stock Market

✔ Product Catalog

✔ Cache Systems

✔ Weather Applications

✔ Configuration Servers

✔ Microservices Cache

✔ Analytics Dashboard

===============================================================================

Comparison

synchronized

✔ One Thread

✔ Simple

--------------------------------------------------

ReentrantLock

✔ One Thread

✔ Flexible

--------------------------------------------------

ReadWriteLock

✔ Multiple Readers

✔ One Writer

✔ Best for Read-Heavy Applications

===============================================================================

One-Line Interview Summary

ReadWriteLock improves concurrency by allowing multiple threads
to read shared data simultaneously while ensuring that only one
thread can write at a time, making it ideal for read-heavy systems.

===============================================================================

 */