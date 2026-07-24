/**
 * ============================================================================
 * File Name : GracefulDegradationStrategies.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Graceful Degradation Strategies
 * ============================================================================
 *
 * WHAT IS GRACEFUL DEGRADATION?
 * -----------------------------
 *
 * Graceful Degradation means
 * the system continues providing
 * limited functionality
 * even when one or more services fail.
 *
 * Instead of crashing,
 * the application provides
 * an alternative response.
 *
 * ============================================================================
 *
 * WHY IS IT IMPORTANT?
 *
 * Imagine Netflix.
 *
 * Recommendation Service fails.
 *
 * Should Netflix stop streaming movies?
 *
 * NO.
 *
 * Instead,
 *
 * Show Trending Movies.
 *
 * User continues watching.
 *
 * ============================================================================
 *
 * COMMON STRATEGIES
 *
 * 1. Return Cached Data
 *
 *      Use previously stored data.
 *
 * ------------------------------------------------
 *
 * 2. Show Fallback UI
 *
 *      Display friendly message
 *      instead of crashing.
 *
 * ------------------------------------------------
 *
 * 3. Queue Requests
 *
 *      Store failed request
 *      and process later.
 *
 * ============================================================================
 */

import java.util.LinkedList;
import java.util.Queue;

class ProductService {

    /*
     * Simulate external API failure.
     */

    public String getLatestProducts() {

        throw new RuntimeException("Product Service Down");
    }
}

public class GracefulDegradationStrategies {

    /*
     * Simulated Cache
     */

    private static final String CACHE =
            "Cached Products : iPhone, Laptop, Smart Watch";

    /*
     * Simulated Request Queue
     */

    private static Queue<String> requestQueue =
            new LinkedList<>();

    public static void main(String[] args) {

        ProductService service =
                new ProductService();

        /*
         * ==========================================================
         * Strategy 1
         * Return Cached Data
         * ==========================================================
         */

        System.out.println("========== RETURN CACHED DATA ==========\n");

        try {

            System.out.println(service.getLatestProducts());

        }

        catch (Exception e) {

            System.out.println("Service Failed.");

            System.out.println("Returning Cached Data...");

            System.out.println(CACHE);

        }

        /*
         * ==========================================================
         * Strategy 2
         * Show Fallback UI
         * ==========================================================
         */

        System.out.println();

        System.out.println("========== FALLBACK UI ==========\n");

        try {

            System.out.println(service.getLatestProducts());

        }

        catch (Exception e) {

            System.out.println("⚠ Product service is temporarily unavailable.");

            System.out.println("Please try again in a few minutes.");

            System.out.println("Showing Trending Products...");

        }

        /*
         * ==========================================================
         * Strategy 3
         * Queue Request
         * ==========================================================
         */

        System.out.println();

        System.out.println("========== QUEUE REQUEST ==========\n");

        String userRequest =
                "Place Order #1025";

        try {

            throw new RuntimeException("Order Service Down");

        }

        catch (Exception e) {

            requestQueue.offer(userRequest);

            System.out.println("Service Down.");

            System.out.println("Request added to Queue.");

        }

        System.out.println();

        System.out.println("Queued Requests :");

        for (String request : requestQueue) {

            System.out.println(request);

        }

    }
}

/*
===============================================================================

OUTPUT

========== RETURN CACHED DATA ==========

Service Failed.

Returning Cached Data...

Cached Products : iPhone, Laptop, Smart Watch

===================================================

========== FALLBACK UI ==========

⚠ Product service is temporarily unavailable.

Please try again in a few minutes.

Showing Trending Products...

===================================================

========== QUEUE REQUEST ==========

Service Down.

Request added to Queue.

Queued Requests :

Place Order #1025

===============================================================================

REAL WORLD EXAMPLES

RETURN CACHED DATA

Netflix

Google Maps

Amazon Home Page

------------------------------------------------

FALLBACK UI

YouTube

Instagram

Amazon

Swiggy

------------------------------------------------

QUEUE REQUESTS

UPI Payments

Email Sending

Order Processing

WhatsApp Messages

===============================================================================

WHEN TO USE EACH STRATEGY

RETURN CACHED DATA

✔ Product Catalog

✔ User Profile

✔ News Feed

✔ Dashboard

------------------------------------------------

FALLBACK UI

✔ Recommendation Service

✔ Search Service

✔ Analytics

✔ Reviews

------------------------------------------------

QUEUE REQUEST

✔ Payment Retry

✔ Order Placement

✔ Email Notification

✔ SMS Sending

✔ Invoice Generation

===============================================================================

INTERVIEW QUESTIONS

Q) What is Graceful Degradation?

Answer

Graceful Degradation is a design approach where
the system continues to provide limited functionality
instead of completely failing when a service is unavailable.

-------------------------------------------------------------------------------

Q) Name three Graceful Degradation strategies.

Answer

1. Return Cached Data

2. Show Fallback UI

3. Queue Requests

-------------------------------------------------------------------------------

Q) Why is Graceful Degradation important?

Answer

✔ Better User Experience

✔ Higher Availability

✔ Improved Reliability

✔ Prevents Complete System Failure

-------------------------------------------------------------------------------

Q) Which companies use Graceful Degradation?

Answer

Netflix

Amazon

Google

Uber

Swiggy

Flipkart

===============================================================================

MICROSERVICES CONNECTION

Graceful Degradation is commonly implemented with:

✔ Circuit Breaker (Resilience4j)

✔ Retry

✔ Cache (Redis)

✔ Message Queue (Kafka/RabbitMQ)

✔ Fallback Methods

✔ Bulkhead Pattern

These patterns help build highly available
and fault-tolerant distributed systems.

===============================================================================

 */