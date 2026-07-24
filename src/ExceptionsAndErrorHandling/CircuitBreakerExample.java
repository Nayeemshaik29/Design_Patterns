package ExceptionsAndErrorHandling;

/**
 * ============================================================================
 * File Name : CircuitBreakerExample.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Circuit Breaker Pattern using Resilience4j
 * ============================================================================
 *
 * WHAT IS A CIRCUIT BREAKER?
 * --------------------------
 *
 * A Circuit Breaker is a resilience pattern
 * that prevents an application from repeatedly
 * calling a failing service.
 *
 * Instead of continuously sending requests,
 * it temporarily blocks them
 * and returns a fallback response.
 *
 * ============================================================================
 *
 * WHY DO WE NEED A CIRCUIT BREAKER?
 * ============================================================================
 *
 * Imagine:
 *
 * Payment Service is DOWN.
 *
 * Without Circuit Breaker
 *
 * Every request still tries
 * to contact the payment service.
 *
 * Result
 *
 * ❌ High Response Time
 *
 * ❌ Thread Exhaustion
 *
 * ❌ Cascading Failures
 *
 * ❌ Entire Application Slows Down
 *
 * ============================================================================
 *
 * WITH CIRCUIT BREAKER
 * ============================================================================
 *
 * Payment Service Fails
 *
 * ↓
 *
 * Circuit Opens
 *
 * ↓
 *
 * No More Requests Sent
 *
 * ↓
 *
 * Fallback Method Executes
 *
 * ↓
 *
 * Application Remains Responsive
 *
 * ============================================================================
 *
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Imagine an Electric Circuit.
 *
 * Too much current flows.
 *
 * Circuit Breaker trips.
 *
 * Electricity stops.
 *
 * Damage is prevented.
 *
 * Once power becomes stable,
 * the breaker closes again.
 *
 * Software Circuit Breaker
 * works exactly the same way.
 *
 * ============================================================================
 *
 * CIRCUIT STATES
 *
 *               CLOSED
 *                  │
 *     Requests are allowed
 *                  │
 *          Failures exceed threshold
 *                  ▼
 *                OPEN
 *                  │
 *    Requests are blocked immediately
 *                  │
 *      Wait duration expires
 *                  ▼
 *             HALF-OPEN
 *                  │
 *     Allow a few test requests
 *                  │
 *     ┌────────────┴─────────────┐
 *     ▼                          ▼
 * Success                    Failure
 *     │                          │
 *     ▼                          ▼
 * CLOSED                     OPEN
 *
 * ============================================================================
 */

@Service
public class PaymentService {

    /*
     * ------------------------------------------------------------------------
     * Circuit Breaker Annotation
     * ------------------------------------------------------------------------
     *
     * name
     *
     * Refers to the configuration
     * in application.yml.
     *
     * fallbackMethod
     *
     * Method executed when
     * service fails.
     */

    @CircuitBreaker(
            name = "paymentService",
            fallbackMethod = "paymentFallback"
    )
    public String charge(String userId,
                         double amount) {

        /*
         * Calling external API.
         *
         * If API keeps failing,
         * Circuit Breaker eventually opens.
         */

        return externalPaymentApi
                .charge(userId, amount);
    }

    /*
     * ------------------------------------------------------------------------
     * FALLBACK METHOD
     * ------------------------------------------------------------------------
     *
     * Executed whenever
     * Circuit Breaker blocks
     * the request.
     *
     * Parameters
     *
     * Same parameters as original method
     * +
     * Throwable
     */

    public String paymentFallback(
            String userId,
            double amount,
            Throwable throwable) {

        System.out.println();

        System.out.println(
                "Payment Service Down.");

        System.out.println(
                "Fallback Method Executed.");

        System.out.println(
                "Reason : "
                        + throwable.getMessage());

        /*
         * Return safe response.
         */

        return "PAYMENT_FAILED";
    }
}

/*
===============================================================================

EXECUTION FLOW

Client Request

↓

PaymentService.charge()

↓

External Payment API

↓

Success ?

──────────────┬─────────────────

YES           NO

│             │

▼             ▼

Return        Failure Count++

Success

              │

              ▼

Threshold Reached ?

              │

      ┌───────┴─────────┐

      ▼                 ▼

     NO                YES

      │                 │

Retry Next        Circuit Opens

Request                │

                       ▼

             Fallback Method

                       │

                       ▼

               PAYMENT_FAILED

===============================================================================

CIRCUIT STATES

1. CLOSED

✔ Normal State

✔ Requests Allowed

------------------------------------------------

2. OPEN

✔ Requests Blocked

✔ Fallback Executes

------------------------------------------------

3. HALF-OPEN

✔ Limited Requests Allowed

✔ If Success

Close Circuit

✔ If Failure

Open Again

===============================================================================

REAL WORLD EXAMPLES

Netflix

↓

Movie Recommendation Service Down

↓

Show Trending Movies

------------------------------------------------

Amazon

↓

Payment Gateway Down

↓

Try Another Gateway

------------------------------------------------

Uber

↓

Maps API Down

↓

Use Cached Route

------------------------------------------------

Swiggy

↓

Restaurant API Down

↓

Show Cached Restaurants

===============================================================================

INTERVIEW QUESTIONS

Q) What is Circuit Breaker?

Answer

A Circuit Breaker is a resilience pattern
that stops sending requests to a failing
service and executes a fallback response
until the service recovers.

-------------------------------------------------------------------------------

Q) Why use Circuit Breaker?

Answer

✔ Prevent Cascading Failure

✔ Improve Response Time

✔ Increase System Stability

✔ Better User Experience

-------------------------------------------------------------------------------

Q) What are the three Circuit Breaker states?

Answer

1. CLOSED

2. OPEN

3. HALF-OPEN

-------------------------------------------------------------------------------

Q) What is a Fallback Method?

Answer

A fallback method is executed
when the protected service
fails or the circuit is open,
returning a safe alternative response.

-------------------------------------------------------------------------------

Q) Which library provides Circuit Breaker in Spring Boot?

Answer

Resilience4j

(Spring Cloud Circuit Breaker
can integrate with Resilience4j.)

===============================================================================

INTERVIEW TIP

Circuit Breaker

≠ Retry

Retry

✔ Keeps trying

Circuit Breaker

✔ Stops trying after repeated failures

Best Practice

Retry transient failures first.

If failures continue,

Circuit Breaker opens.

Together they improve resilience.

===============================================================================
*/
