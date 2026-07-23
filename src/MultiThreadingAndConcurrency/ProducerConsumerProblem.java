package MultiThreadingAndConcurrency;

/**

 * PROBLEM STATEMENT
 * -----------------
 * There are two threads:
 *
 * 1. Producer
 *      - Produces data (Coffee)
 *
 * 2. Consumer
 *      - Consumes data (Drinks Coffee)
 *
 * The producer should NOT produce when the buffer is already full.
 *
 * The consumer should NOT consume when the buffer is empty.
 *
 * Therefore both threads must communicate with each other.
 *
 * Java provides:
 *
 *      synchronized
 *      wait()
 *      notify()
 *
 * to solve this problem.
 *
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Think of a Coffee Machine.
 *
 *                 Coffee Machine
 *              -------------------
 *                 Capacity = 1 Cup
 *
 *        Producer                   Consumer
 *     (Makes Coffee)            (Drinks Coffee)
 *
 *            │                       ▲
 *            ▼                       │
 *        Coffee Ready -------------> Drinks
 *
 * Rules:
 *
 * ✔ Producer waits if coffee already exists.
 *
 * ✔ Consumer waits if coffee is not available.
 *
 *
 * ============================================================================
 * CONCEPTS USED
 * ============================================================================
 *
 * ✔ Thread
 * ✔ synchronized
 * ✔ Monitor Lock
 * ✔ wait()
 * ✔ notify()
 * ✔ Thread Communication
 * ✔ Producer Consumer Pattern
 *
 *
 * ============================================================================
 * HOW synchronized WORKS
 * ============================================================================
 *
 * synchronized means:
 *
 * "Only ONE thread can enter this method at a time."
 *
 * Example:
 *
 * Producer enters makeCoffee()
 *
 * Consumer tries to enter drinkCoffee()
 *
 * Consumer has to wait until Producer leaves.
 *
 *
 * ============================================================================
 * HOW wait() WORKS
 * ============================================================================
 *
 * wait()
 *
 * 1. Releases the object's lock.
 *
 * 2. Moves thread into WAITING state.
 *
 * 3. Thread sleeps forever
 *    until another thread calls notify().
 *
 *
 * ============================================================================
 * HOW notify() WORKS
 * ============================================================================
 *
 * notify()
 *
 * Wakes ONE waiting thread.
 *
 * It DOES NOT immediately give execution.
 *
 * The awakened thread first competes again
 * to acquire the object's lock.
 *
 *
 * ============================================================================
 * WHY while INSTEAD OF if ?
 * ============================================================================
 *
 * Never write
 *
 *      if(condition)
 *          wait();
 *
 * Always write
 *
 *      while(condition)
 *          wait();
 *
 * Because after notify(),
 * another thread may change the condition
 * before this thread gets the lock.
 *
 * This is called
 *
 *      Spurious Wakeup
 *
 * Hence while is mandatory.
 *
 * ============================================================================
 */

class CoffeeMachine {

    /*
     * ------------------------------------------------------------------------
     * Shared Variable
     * ------------------------------------------------------------------------
     *
     * This variable represents our BUFFER.
     *
     * false
     *      Coffee NOT available
     *
     * true
     *      Coffee available
     *
     * Since buffer capacity is ONE,
     * boolean is enough.
     *
     * If capacity were 10,
     * we would use Queue instead.
     */

    private boolean coffeeReady = false;

    /*
     * ------------------------------------------------------------------------
     * PRODUCER METHOD
     * ------------------------------------------------------------------------
     *
     * synchronized
     *
     * Only one thread can execute this method.
     *
     * Producer produces coffee.
     */

    public synchronized void makeCoffee() throws InterruptedException {

        /*
         * Buffer Full?
         *
         * YES
         * ----
         * Producer cannot produce again.
         *
         * Therefore Producer waits.
         *
         * wait()
         *
         * releases monitor lock
         * and enters WAITING state.
         */

        while (coffeeReady) {

            System.out.println(
                    "Producer : Coffee already exists.");
            System.out.println(
                    "Producer : Waiting...");

            wait();
        }

        /*
         * Producer reached here
         * because buffer is empty.
         */

        System.out.println(
                "\nProducer : Brewing Coffee...");

        /*
         * Artificial delay
         * only for demonstration.
         *
         * sleep()
         *
         * DOES NOT release lock.
         */

        Thread.sleep(1000);

        /*
         * Coffee prepared.
         *
         * Buffer becomes FULL.
         */

        coffeeReady = true;

        System.out.println(
                "Producer : Coffee Ready.");

        /*
         * Notify Consumer.
         *
         * Consumer may now wake up.
         */

        notify();
    }

    /*
     * ------------------------------------------------------------------------
     * CONSUMER METHOD
     * ------------------------------------------------------------------------
     *
     * Consumer drinks coffee.
     */

    public synchronized void drinkCoffee()
            throws InterruptedException {

        /*
         * Buffer Empty?
         *
         * Consumer has nothing to drink.
         *
         * Therefore wait.
         */

        while (!coffeeReady) {

            System.out.println(
                    "Consumer : Waiting for Coffee...");

            wait();
        }

        /*
         * Consumer resumes
         * because producer notified.
         */

        System.out.println(
                "Consumer : Drinking Coffee...");

        Thread.sleep(1000);

        /*
         * Coffee finished.
         *
         * Buffer becomes EMPTY.
         */

        coffeeReady = false;

        System.out.println(
                "Consumer : Cup Empty.");

        /*
         * Notify Producer.
         *
         * Producer can make next coffee.
         */

        notify();
    }
}

/**
 * ============================================================================
 * DRIVER CLASS
 * ============================================================================
 *
 * Creates:
 *
 *      CoffeeMachine
 *
 *      Producer Thread
 *
 *      Consumer Thread
 *
 * Starts both threads.
 */

public class ProducerConsumerProblem {

    public static void main(String[] args) {

        /*
         * Shared Object
         *
         * Both Producer
         * and Consumer
         * communicate through this object.
         */

        CoffeeMachine machine = new CoffeeMachine();

        /*
         * Producer Thread
         */

        Thread producer = new Thread(() -> {

            /*
             * Infinite loop
             *
             * Producer keeps making coffee.
             */

            while (true) {

                try {

                    machine.makeCoffee();

                } catch (InterruptedException e) {

                    /*
                     * Best Practice
                     *
                     * Restore interrupt status.
                     */

                    Thread.currentThread().interrupt();

                    break;
                }
            }

        }, "Producer");

        /*
         * Consumer Thread
         */

        Thread consumer = new Thread(() -> {

            /*
             * Infinite loop
             *
             * Consumer keeps drinking.
             */

            while (true) {

                try {

                    machine.drinkCoffee();

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    break;
                }
            }

        }, "Consumer");

        /*
         * Start Producer
         */

        producer.start();

        /*
         * Start Consumer
         */

        consumer.start();
    }
}

/*
===============================================================================

EXPECTED OUTPUT

Producer : Brewing Coffee...
Producer : Coffee Ready.

Consumer : Drinking Coffee...
Consumer : Cup Empty.

Producer : Brewing Coffee...
Producer : Coffee Ready.

Consumer : Drinking Coffee...
Consumer : Cup Empty.

(repeats forever)

===============================================================================

EXECUTION FLOW

Program Starts

↓

CoffeeMachine Created

↓

Producer Thread Starts

↓

Consumer Thread Starts

↓

Producer enters synchronized method

↓

Coffee Produced

↓

notify()

↓

Consumer wakes

↓

Coffee Consumed

↓

notify()

↓

Producer wakes

↓

Repeat Forever

===============================================================================

INTERVIEW QUESTIONS

1. Why synchronized?

   To allow only one thread at a time.

2. Why wait()?

   Releases lock and waits.

3. Why notify()?

   Wakes waiting thread.

4. Why while instead of if?

   Prevents Spurious Wakeups.

5. Difference between sleep() and wait()?

   sleep() keeps lock.

   wait() releases lock.

6. What is Monitor Lock?

   Every Java object has an intrinsic lock.
   synchronized uses that lock.

===============================================================================
*/