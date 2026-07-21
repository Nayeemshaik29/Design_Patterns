package MultiThreadingAndConcurrency;

import java.util.concurrent.*;

/*
===============================================================================

                SCHEDULEDEXECUTORSERVICE

===============================================================================

Definition
-----------
ScheduledExecutorService is a thread pool that can execute tasks

✔ After a delay
✔ At a fixed interval
✔ Repeatedly

Unlike ExecutorService, it supports scheduling tasks.

===============================================================================

Real World Example
-------------------

Suppose an application needs to

✔ Clean expired sessions every 5 minutes
✔ Send heartbeat every 10 seconds
✔ Refresh cache every 30 minutes
✔ Backup database every night

Instead of using an infinite loop with sleep(),

Java provides

ScheduledExecutorService

===============================================================================

Problem with Normal Code
-------------------------

while(true){

    cleanExpiredSessions();

    Thread.sleep(5000);

}

Problems

✘ Wastes CPU resources

✘ Hard to maintain

✘ Difficult to stop gracefully

✘ Poor production design

===============================================================================

Solution

Use

ScheduledExecutorService

It automatically schedules tasks at fixed intervals.

===============================================================================

Flow

Main Thread

↓

Create Scheduler

↓

scheduleAtFixedRate()

↓

Worker Thread

↓

Execute Task

↓

Wait 5 Seconds

↓

Execute Again

↓

Wait 5 Seconds

↓

Execute Again

...

===============================================================================

Advantages

✔ Automatic Scheduling

✔ Reuses Threads

✔ Better CPU Utilization

✔ Production Ready

✔ Graceful Shutdown Support

===============================================================================

Disadvantages

✘ Long-running tasks may delay next execution

✘ Wrong interval configuration may overload CPU

===============================================================================
*/

public class ScheduledExecutorServiceExample {

    public static void main(String[] args)
            throws InterruptedException {

        /*
        ===============================================================

        Create Scheduler

        Thread Pool Size = 1

        ===============================================================
        */

        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        /*
        ===============================================================

        Task to execute repeatedly

        ===============================================================
        */

        Runnable task = () ->

                System.out.println(
                        "Cleaning expired sessions on "
                                + Thread.currentThread().getName());

        /*
        ===============================================================

        scheduleAtFixedRate()

        Parameters

        task

        Initial Delay

        Time Between Executions

        Time Unit

        ===============================================================
        */

        scheduler.scheduleAtFixedRate(

                task,

                0,

                5,

                TimeUnit.SECONDS

        );

        /*
        ===============================================================

        Program runs for 20 seconds.

        Otherwise JVM exits immediately.

        ===============================================================
        */

        Thread.sleep(20000);

        /*
        ===============================================================

        Shutdown Scheduler

        Always shutdown thread pools.

        ===============================================================
        */

        scheduler.shutdown();

        System.out.println("\nScheduler Stopped.");

    }

}

/*
===============================================================================

Dry Run

Main Thread

↓

Create Scheduler

↓

scheduleAtFixedRate()

↓

Worker Thread

↓

Cleaning expired sessions...

↓

Wait 5 Seconds

↓

Cleaning expired sessions...

↓

Wait 5 Seconds

↓

Cleaning expired sessions...

↓

Wait 5 Seconds

↓

Cleaning expired sessions...

↓

shutdown()

===============================================================================

Timeline

0 sec

Scheduler Started

↓

Cleaning expired sessions...

↓

5 sec

Cleaning expired sessions...

↓

10 sec

Cleaning expired sessions...

↓

15 sec

Cleaning expired sessions...

↓

20 sec

Scheduler Shutdown

===============================================================================

OUTPUT

Cleaning expired sessions on pool-1-thread-1

Cleaning expired sessions on pool-1-thread-1

Cleaning expired sessions on pool-1-thread-1

Cleaning expired sessions on pool-1-thread-1

Scheduler Stopped.

===============================================================================

Interview Questions

Q1. What is ScheduledExecutorService?

Ans:
It is a thread pool that schedules tasks to execute after a delay or
at regular intervals.

------------------------------------------------

Q2. Difference between schedule() and scheduleAtFixedRate()?

schedule()

Runs only once after a delay.

scheduleAtFixedRate()

Runs repeatedly at fixed intervals.

------------------------------------------------

Q3. Why not use while(true) with Thread.sleep()?

Ans:

ScheduledExecutorService

✔ Cleaner

✔ Reuses Threads

✔ Better Performance

✔ Easier to Manage

✔ Production Ready

------------------------------------------------

Q4. Which package provides ScheduledExecutorService?

Ans:

java.util.concurrent

===============================================================================

Production Use Cases

✔ Session Cleanup

✔ Cache Refresh

✔ Health Check APIs

✔ Heartbeat Messages

✔ Database Backup

✔ Log Rotation

✔ Email Scheduling

✔ Token Cleanup

===============================================================================

Modern Java

ScheduledExecutorService is the standard way to execute periodic
background jobs in Java applications.

Spring Boot provides an even simpler approach using

@Scheduled

for scheduled background tasks.

===============================================================================

One-Line Interview Summary

ScheduledExecutorService is a thread pool that executes tasks after a
specified delay or at regular intervals, making it ideal for periodic
background jobs like cache refresh, session cleanup, and health checks.

===============================================================================

 */