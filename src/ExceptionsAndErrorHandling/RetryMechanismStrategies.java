package ExceptionsAndErrorHandling;

/**
 * ============================================================================
 * File Name : RetryMechanismStrategies.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Retry Mechanism Strategies
 * ============================================================================
 *
 * WHAT IS A RETRY MECHANISM?
 * --------------------------
 *
 * A Retry Mechanism automatically retries
 * an operation that failed due to a temporary issue.
 *
 * Examples:
 *
 * ✔ Network Timeout
 *
 * ✔ Payment Gateway Busy
 *
 * ✔ Database Connection Lost
 *
 * ✔ External API Timeout
 *
 * ============================================================================
 * WHY DO WE NEED RETRIES?
 * ============================================================================
 *
 * Many failures are temporary.
 *
 * Example
 *
 * Payment Gateway is busy for 2 seconds.
 *
 * Instead of failing immediately,
 *
 * retry the request.
 *
 * The next attempt may succeed.
 *
 * ============================================================================
 * TYPES OF RETRY
 * ============================================================================
 *
 * 1. Naive Retry
 *
 * Retry immediately.
 *
 * ------------------------------------------------
 *
 * 2. Exponential Backoff
 *
 * Wait before each retry.
 *
 * Wait Time
 *
 * 1 sec
 *
 * 2 sec
 *
 * 4 sec
 *
 * 8 sec
 *
 * ...
 *
 * This prevents overwhelming
 * the failing service.
 *
 * ============================================================================
 */

class PaymentGateway {

    /*
     * Simulate temporary failure.
     */

    private int attempt = 0;

    public void processPayment() {

        attempt++;

        System.out.println("Calling Payment Gateway... Attempt " + attempt);

        /*
         * First two attempts fail.
         */

        if (attempt < 3) {

            throw new RuntimeException("Gateway Timeout");

        }

        System.out.println("Payment Successful");
    }
}

public class RetryMechanismStrategies {

    public static void main(String[] args) throws InterruptedException {

        /*
         * ==========================================================
         * Strategy 1
         * Naive Retry
         * ==========================================================
         */

        System.out.println("========== NAIVE RETRY ==========\n");

        PaymentGateway gateway1 = new PaymentGateway();

        int maxRetries = 3;

        for (int i = 1; i <= maxRetries; i++) {

            try {

                gateway1.processPayment();

                break;

            }

            catch (Exception e) {

                System.out.println("Retry " + i + " Failed");

                if (i == maxRetries) {

                    System.out.println("Maximum Retry Limit Reached");

                }

            }

        }

        /*
         * ==========================================================
         * Strategy 2
         * Exponential Backoff
         * ==========================================================
         */

        System.out.println();

        System.out.println("========== EXPONENTIAL BACKOFF ==========\n");

        PaymentGateway gateway2 = new PaymentGateway();

        long waitTime = 1000;

        for (int i = 1; i <= maxRetries; i++) {

            try {

                gateway2.processPayment();

                break;

            }

            catch (Exception e) {

                System.out.println("Retry " + i + " Failed");

                if (i == maxRetries) {

                    System.out.println("Maximum Retry Limit Reached");

                    break;

                }

                System.out.println("Waiting " + waitTime + " ms...");

                Thread.sleep(waitTime);

                /*
                 * Double the wait time.
                 */

                waitTime *= 2;

            }

        }

    }
}

/*
===============================================================================

OUTPUT

========== NAIVE RETRY ==========

Calling Payment Gateway... Attempt 1

Retry 1 Failed

Calling Payment Gateway... Attempt 2

Retry 2 Failed

Calling Payment Gateway... Attempt 3

Payment Successful

===================================================

========== EXPONENTIAL BACKOFF ==========

Calling Payment Gateway... Attempt 1

Retry 1 Failed

Waiting 1000 ms...

Calling Payment Gateway... Attempt 2

Retry 2 Failed

Waiting 2000 ms...

Calling Payment Gateway... Attempt 3

Payment Successful

===============================================================================

EXECUTION FLOW

NAIVE RETRY

Call API

↓

Failure

↓

Retry Immediately

↓

Failure

↓

Retry Immediately

↓

Success / Max Retries

===============================================================================

EXPONENTIAL BACKOFF

Call API

↓

Failure

↓

Wait 1 sec

↓

Retry

↓

Failure

↓

Wait 2 sec

↓

Retry

↓

Failure

↓

Wait 4 sec

↓

Retry

↓

Success / Max Retries

===============================================================================

COMPARISON

+---------------------------+------------------------------+
| Naive Retry               | Exponential Backoff          |
+---------------------------+------------------------------+
| Immediate Retry           | Wait Between Retries         |
| Simple                    | Smarter                     |
| Can overload server       | Reduces server load          |
| Best for quick retries    | Best for distributed systems |
+---------------------------+------------------------------+

===============================================================================

REAL-WORLD EXAMPLES

Naive Retry

✔ Local Database Connection

✔ Temporary File Lock

------------------------------------------------

Exponential Backoff

✔ Payment Gateway

✔ REST APIs

✔ Kafka Consumers

✔ AWS SDK

✔ Google Cloud APIs

✔ Azure SDK

===============================================================================

INTERVIEW QUESTIONS

Q) What is a Retry Mechanism?

Answer

A Retry Mechanism automatically retries
an operation that failed because of a temporary
or transient error.

-------------------------------------------------------------------------------

Q) What is Naive Retry?

Answer

Naive Retry immediately retries the failed operation
without any delay.

-------------------------------------------------------------------------------

Q) What is Exponential Backoff?

Answer

Exponential Backoff increases the waiting time
between retry attempts, reducing pressure on
the failing service.

-------------------------------------------------------------------------------

Q) Why is Exponential Backoff preferred?

Answer

✔ Prevents Server Overload

✔ Reduces Network Congestion

✔ Improves System Stability

✔ Common in Cloud and Microservices

-------------------------------------------------------------------------------

Q) Does Spring Boot support Retry?

Answer

Yes.

Spring Retry provides annotations like:

✔ @Retryable

✔ @Recover

You can also use Resilience4j Retry
for microservices.

===============================================================================

INTERVIEW TIP

Never retry every exception.

Retry only transient failures like:

✔ Network Timeout

✔ HTTP 503 Service Unavailable

✔ Temporary Database Connection Loss

Do NOT retry permanent failures like:

❌ Invalid Password

❌ Invalid Credit Card

❌ Illegal Arguments

These require user correction, not retries.

===============================================================================
*/