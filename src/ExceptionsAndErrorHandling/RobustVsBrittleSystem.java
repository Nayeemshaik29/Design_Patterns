package ExceptionsAndErrorHandling;

import DependencyInjection.PaymentService;

/**
 * ============================================================================
 * File Name : RobustVsBrittleSystem.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Robust System vs Brittle System
 * ============================================================================
 *
 * WHAT IS A BRITTLE SYSTEM?
 * -------------------------
 *
 * A Brittle System is a system that
 * easily breaks when something goes wrong.
 *
 * Even a small failure
 * can crash the entire application.
 *
 * Characteristics
 *
 * ❌ Poor Error Handling
 *
 * ❌ Application Crash
 *
 * ❌ No Recovery
 *
 * ❌ Difficult Maintenance
 *
 * ============================================================================
 * WHAT IS A ROBUST SYSTEM?
 * ============================================================================
 *
 * A Robust System is designed
 * to handle failures gracefully.
 *
 * Even if one component fails,
 * the application continues working.
 *
 * Characteristics
 *
 * ✔ Handles Exceptions
 *
 * ✔ Logs Errors
 *
 * ✔ Provides Meaningful Messages
 *
 * ✔ Continues Execution
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Imagine a Restaurant.
 *
 * --------------------------------------------------------------------------
 * BRITTLE SYSTEM
 * --------------------------------------------------------------------------
 *
 * Customer orders:
 *
 *      Pizza
 *
 * Pizza machine stops working.
 *
 * Restaurant closes.
 *
 * No food served.
 *
 * Everything stops.
 *
 * --------------------------------------------------------------------------
 * ROBUST SYSTEM
 * --------------------------------------------------------------------------
 *
 * Pizza machine stops.
 *
 * Chef says
 *
 * "Sorry, Pizza isn't available.
 * Here's Pasta instead."
 *
 * Restaurant continues serving customers.
 *
 * ============================================================================
 * GOAL
 * ============================================================================
 *
 * We will compare
 *
 *      Brittle System
 *
 *              VS
 *
 *      Robust System
 *
 * using the same payment example.
 *
 * ============================================================================
 */

class PaymentServicee {

    /*
     * Simulate payment processing.
     */

    public void processPayment(int amount) {

        System.out.println("Processing Payment...");

        /*
         * Simulating payment failure.
         */

        throw new RuntimeException("Payment Gateway Down");
    }
}

/**
 * ============================================================================
 * BRITTLE SYSTEM
 * ============================================================================
 *
 * No exception handling.
 *
 * If payment fails,
 * application crashes.
 */

class BrittleOrderService {

    private PaymentServicee paymentService =
            new PaymentServicee();

    public void checkout() {

        System.out.println("\n========== BRITTLE SYSTEM ==========\n");

        /*
         * No try-catch.
         *
         * Exception propagates.
         *
         * Program terminates.
         */

        paymentService.processPayment(5000);

        /*
         * This line never executes.
         */

        System.out.println("Order Placed Successfully.");
    }
}

/**
 * ============================================================================
 * ROBUST SYSTEM
 * ============================================================================
 *
 * Handles failures properly.
 *
 * Application continues running.
 */

class RobustOrderService {


    private PaymentServicee paymentService =
            new PaymentServicee();

    public void checkout() {

        System.out.println("\n========== ROBUST SYSTEM ==========\n");

        try {

            paymentService.processPayment(5000);

            System.out.println("Order Placed.");

        }

        catch (RuntimeException e) {

            /*
             * Handle exception gracefully.
             */

            System.out.println("Payment Failed.");

            System.out.println("Reason : " + e.getMessage());

            System.out.println("Please try again later.");

            /*
             * Application still running.
             */

        }

        finally {

            System.out.println("Checkout Process Completed.");

        }
    }
}

/**
 * ============================================================================
 * DRIVER CLASS
 * ============================================================================
 */

public class RobustVsBrittleSystem {

    public static void main(String[] args) {

        /*
         * ===============================================================
         * BRITTLE SYSTEM
         * ===============================================================
         */

        try {

            BrittleOrderService brittle =
                    new BrittleOrderService();

            brittle.checkout();

        }

        catch (Exception e) {

            System.out.println();

            System.out.println("Application Crashed.");

        }

        /*
         * ===============================================================
         * ROBUST SYSTEM
         * ===============================================================
         */

        RobustOrderService robust =
                new RobustOrderService();

        robust.checkout();

        System.out.println();

        System.out.println("Application is still running...");
    }
}

/*
===============================================================================

OUTPUT

========== BRITTLE SYSTEM ==========

Processing Payment...

Application Crashed.

===================================================

========== ROBUST SYSTEM ==========

Processing Payment...

Payment Failed.

Reason : Payment Gateway Down

Please try again later.

Checkout Process Completed.

Application is still running...

===============================================================================

EXECUTION FLOW

BRITTLE SYSTEM

Checkout

↓

Payment

↓

Exception

↓

Application Crash

===============================================================================

ROBUST SYSTEM

Checkout

↓

Payment

↓

Exception

↓

Catch Block

↓

Meaningful Message

↓

Cleanup

↓

Continue Execution

===============================================================================

COMPARISON

+-------------------------+-----------------------------+
| Brittle System          | Robust System               |
+-------------------------+-----------------------------+
| Crashes Easily          | Handles Failures            |
| No Recovery             | Graceful Recovery           |
| Poor User Experience    | Better User Experience      |
| Difficult Maintenance   | Easy Maintenance            |
| Unstable                | Stable                      |
+-------------------------+-----------------------------+

===============================================================================

REAL EXAMPLES

BRITTLE

❌ No try-catch

❌ No logging

❌ No validation

❌ No fallback

❌ Application crash

------------------------------------------------

ROBUST

✔ try-catch

✔ Logging

✔ Validation

✔ Retry Mechanism

✔ Circuit Breaker

✔ Fallback Method

✔ Custom Exceptions

✔ Global Exception Handling

===============================================================================

INTERVIEW QUESTIONS

Q) What is a Brittle System?

Answer

A Brittle System is one that easily fails
or crashes when an unexpected error occurs.

-------------------------------------------------------------------------------

Q) What is a Robust System?

Answer

A Robust System handles failures gracefully,
provides meaningful error messages,
and continues functioning whenever possible.

-------------------------------------------------------------------------------

Q) How do we make a Java application Robust?

Answer

✔ Exception Handling

✔ Input Validation

✔ Logging

✔ Retry Mechanism

✔ Custom Exceptions

✔ Global Exception Handling

✔ Circuit Breaker (Microservices)

✔ Proper Testing

-------------------------------------------------------------------------------

Q) Which system should we build?

Answer

Always build a Robust System
because real-world applications
must tolerate failures instead of crashing.

===============================================================================

INTERVIEW TIP

A Robust System doesn't mean
"no errors occur."

It means

"When errors occur,
the system handles them gracefully
without bringing down the entire application."

This is the answer interviewers are usually looking for.

===============================================================================

 */