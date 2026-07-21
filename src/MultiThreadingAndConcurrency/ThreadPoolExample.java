package MultiThreadingAndConcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
===============================================================================

                    THREAD POOL (ExecutorService)

===============================================================================

Problem
-------

Suppose Amazon receives 1,00,000 orders.

For every order it needs to

✔ Send Email
✔ Send SMS
✔ Update Inventory
✔ Generate Invoice

If we create

new Thread()

for every request

↓

1,00,000 Threads

Problems

❌ Huge Memory Usage

❌ Context Switching

❌ Slow Performance

❌ Thread Creation Cost

===============================================================================

Solution

Use Thread Pool.

Instead of creating new threads every time,

Java creates a fixed number of worker threads.

Example

Pool Size = 10

Thread-1

Thread-2

Thread-3

...

Thread-10

Tasks are assigned to available threads.

Once a thread finishes,

it is reused.

No new thread creation.

===============================================================================

Real World Example

Amazon

1000 users place orders.

Instead of

1000 Threads

↓

Use

10 Worker Threads

↓

Process tasks one by one.

===============================================================================

Execution

Task1 -> Thread-1

Task2 -> Thread-2

Task3 -> Thread-3

...

Task11 waits until one thread becomes free.

===============================================================================

Advantages

✔ Reuses Threads

✔ Faster

✔ Better Performance

✔ Lower Memory Usage

✔ Production Standard

===============================================================================

Disadvantages

✘ Wrong pool size can reduce performance

✘ Long-running tasks may block worker threads

===============================================================================

Production Use Cases

✔ Spring Boot

✔ Banking

✔ Amazon

✔ Netflix

✔ Uber

✔ REST APIs

===============================================================================
*/

public class ThreadPoolExample {

    // Fixed thread pool containing 10 reusable worker threads
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(10);

    /*
        Task to send email.

        Instead of creating

        new Thread()

        we simply submit the task to ExecutorService.

        ExecutorService decides which worker thread executes it.
     */
    public static void sendEmail(String recipient) {

        executor.execute(() -> {

            System.out.println(
                    "Sending email to "
                            + recipient
                            + " using "
                            + Thread.currentThread().getName());

            try {

                // Simulate API delay
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            }

            System.out.println("Email sent to " + recipient);

        });

    }

    public static void main(String[] args) {

        /*
            Imagine these are 25 customers.

            Only 10 threads exist.

            Remaining tasks wait in queue.
         */

        for (int i = 1; i <= 25; i++) {

            sendEmail("user" + i + "@gmail.com");

        }

        /*
            Gracefully shutdown.

            No new tasks accepted.

            Existing tasks continue.
         */
        executor.shutdown();

    }
}

/*
===============================================================================

Dry Run

Pool Size = 10

Users

1
2
3
...
25

Initially

Thread-1 -> User1

Thread-2 -> User2

...

Thread-10 -> User10

User11 waits.

Once Thread-3 finishes

↓

Thread-3

↓

Processes User11

Thread gets reused.

===============================================================================

Interview Question

Q) Why use ExecutorService instead of Thread?

Answer

ExecutorService manages thread creation,
reuses existing threads,
reduces memory consumption,
improves scalability,
and is the preferred way to perform concurrent tasks in production Java.

=================================================================================

 */