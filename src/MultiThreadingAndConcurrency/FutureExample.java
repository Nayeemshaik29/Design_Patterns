package MultiThreadingAndConcurrency;

import java.util.concurrent.*;

/*
===============================================================================

                    FUTURE + SUBMIT

===============================================================================

Problem

Sometimes a task returns a value.

Example

Calculate ETA

Generate OTP

Fetch Database Record

Call Payment API

Thread cannot directly return values.

Runnable

↓

Returns nothing.

Callable

↓

Returns a value.

Future

↓

Holds that value until computation finishes.

===============================================================================

Flow

Main Thread

↓

submit()

↓

ExecutorService

↓

Callable executes

↓

Returns Result

↓

Future stores Result

↓

future.get()

↓

Main Thread receives Result

===============================================================================

Real World Example

Food Delivery

Customer Places Order

↓

Calculate ETA

↓

Return

25 Minutes

Main thread can continue doing other work.

===============================================================================

Advantages

✔ Returns Value

✔ Handles Exceptions

✔ Waits only when required

✔ Production Ready

===============================================================================

Disadvantages

✘ future.get() blocks current thread

✘ Better alternative in modern Java

CompletableFuture

===============================================================================
*/

public class FutureExample {

    public static void main(String[] args)
            throws Exception {

        // Create thread pool
        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        /*
            submit()

            Executes Callable.

            Returns Future object immediately.

            Task runs in background.
         */
        Future<Integer> future =
                executor.submit(() -> {

                    /*
                        Simulating
                        database/API work
                     */

                    Thread.sleep(1000);

                    return 77;

                });

        /*
            Main thread DOES NOT wait.

            It continues executing.
         */
        System.out.println("Doing other work...");

        /*
            get()

            Waits until background task completes.

            Then returns the result.
         */
        Integer result = future.get();

        System.out.println("Result : " + result);

        executor.shutdown();

    }
}

/*
===============================================================================

Dry Run

Main Thread

↓

submit()

↓

Background Thread

↓

sleep(1000)

↓

return 77

↓

Future stores value

↓

future.get()

↓

Result = 77

===============================================================================

Timeline

0 sec

submit()

↓

Main Thread prints

Doing other work...

↓

Background Thread works

↓

1 sec

Result Ready

↓

future.get()

↓

77

===============================================================================

Interview Question

Q) Difference between execute() and submit()?

execute()

✔ Used with Runnable

✔ No return value

✔ Fire-and-forget

submit()

✔ Used with Runnable or Callable

✔ Returns Future

✔ Can return value

✔ Can retrieve exceptions

===============================================================================

Q) Why use Future?

Future allows the main thread to continue executing while a background task
runs. Later, it can retrieve the result once the computation finishes.

===============================================================================

Production Use Cases

✔ Payment Gateway

✔ Database Queries

✔ Fetch User Profile

✔ Calling Microservices

✔ Order Processing

✔ Analytics

✔ Weather APIs

===============================================================================

Modern Java

Instead of Future,

today most production systems use

✔ CompletableFuture

because it supports

thenApply()

thenCompose()

thenCombine()

exceptionally()

without blocking.

===============================================================================

 */