package MultiThreadingAndConcurrency;

/*
===============================================================================

                            DEADLOCK

===============================================================================

Problem

Sometimes multiple threads need multiple resources.

If Thread-1 holds Resource-A
and waits for Resource-B,

while

Thread-2 holds Resource-B
and waits for Resource-A,

Neither thread can continue.

This situation is called

DEADLOCK

===============================================================================

Real World Example

Imagine

Person-1

↓

Locks Room A

↓

Needs Key B

----------------------------------

Person-2

↓

Locks Room B

↓

Needs Key A

Both wait forever.

No one proceeds.

===============================================================================

In Java

Thread-1

↓

Lock Account-A

↓

Wait for Account-B

----------------------------------

Thread-2

↓

Lock Account-B

↓

Wait for Account-A

↓

Deadlock

===============================================================================

Why Does Deadlock Happen?

Deadlock occurs when all four conditions are true.

✔ Mutual Exclusion

Only one thread can own a lock.

✔ Hold and Wait

Thread holds one lock while waiting for another.

✔ No Preemption

Locks cannot be taken away forcibly.

✔ Circular Wait

Thread-A waits for Thread-B

Thread-B waits for Thread-A

===============================================================================

Flow

Thread-1

↓

Lock Account-A

↓

Sleep

↓

Waiting for Account-B

----------------------------------

Thread-2

↓

Lock Account-B

↓

Sleep

↓

Waiting for Account-A

----------------------------------

Both Threads

↓

Waiting Forever

↓

Application Hangs

===============================================================================

Code Explanation

BankAccount

↓

Shared Resource

Each BankAccount object has its own intrinsic monitor lock.

===============================================================================

TransferTask

Each thread transfers money.

Step-1

Acquire lock on "from" account.

↓

Sleep

↓

Acquire lock on "to" account.

===============================================================================

Problem

Thread-1

locks

Account-A

↓

Thread-2

locks

Account-B

↓

Thread-1 waits for Account-B

↓

Thread-2 waits for Account-A

↓

Deadlock

===============================================================================

Output

T1 locked Account-A

T2 locked Account-B

Program hangs...

"Both threads finished execution."

is NEVER printed.

===============================================================================

Timeline

0 sec

T1 locks Account-A

T2 locks Account-B

↓

0.1 sec

T1 waiting for Account-B

T2 waiting for Account-A

↓

Forever

Deadlock

===============================================================================

Advantages of Synchronization

✔ Prevents Race Conditions

✔ Protects Shared Data

✔ Maintains Data Consistency

===============================================================================

Disadvantages

✘ Deadlock Risk

✘ Reduced Performance

✘ Thread Blocking

✘ Difficult Debugging

===============================================================================

How to Prevent Deadlocks?

✔ Always acquire locks in the same order.

Example

Correct

Thread-1

Lock A

↓

Lock B

Thread-2

Lock A

↓

Lock B

Never reverse the order.

--------------------------------------------------

✔ Use ReentrantLock.tryLock()

Instead of waiting forever,

try acquiring the lock.

If unavailable,

retry later.

--------------------------------------------------

✔ Avoid Nested Locks

Acquire fewer locks whenever possible.

--------------------------------------------------

✔ Keep Critical Sections Small

Hold locks only for the minimum required time.

===============================================================================

Production Examples

✔ Banking Systems

Money Transfer

✔ Inventory Systems

Product Reservation

✔ Hotel Booking

Room Allocation

✔ Railway Reservation

Seat Booking

✔ Payment Gateways

Wallet Transfer

===============================================================================

Interview Question

Q) What is Deadlock?

Deadlock is a situation where two or more threads wait forever for each
other's locks, preventing all of them from making progress.

===============================================================================

Q) How do you prevent Deadlocks?

✔ Lock resources in a fixed order

✔ Use tryLock()

✔ Reduce nested locking

✔ Keep synchronized blocks as small as possible

===============================================================================

Modern Java

Instead of relying only on synchronized,

production applications often use

✔ ReentrantLock

✔ tryLock()

✔ Timed Locks

✔ Lock Ordering

to avoid deadlocks.

===============================================================================
*/