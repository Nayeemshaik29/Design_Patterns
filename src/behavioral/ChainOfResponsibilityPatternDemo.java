/*
===============================================================================

                CHAIN OF RESPONSIBILITY DESIGN PATTERN

Definition:
------------
Chain of Responsibility Pattern allows a request to pass through a chain of
handlers until one of them handles it.

Each handler decides whether it can process the request or pass it to the
next handler in the chain.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose you contact Amazon Customer Support.

Depending on your issue, the request is forwarded to different departments.

✔ General Queries
✔ Billing / Refund
✔ Technical Issues
✔ Delivery Issues

If one department cannot resolve the issue, it forwards the request to the
next department.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                    ABSTRACT HANDLER

Every support handler should:

✔ Handle the request

✔ Forward it to the next handler if required

===============================================================================
*/

abstract class SupportHandler {

    protected SupportHandler nextHandler;

    /*
    ===============================================================

    Set Next Handler

    ===============================================================
    */

    public void setNextHandler(SupportHandler nextHandler) {

        this.nextHandler = nextHandler;
    }

    /*
    ===============================================================

    Handle Request

    ===============================================================
    */

    public abstract void handleRequest(String requestType);
}

/*
===============================================================================

                CONCRETE HANDLER

General Support

===============================================================================
*/

class GeneralSupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("general")) {

            System.out.println(
                    "General Support : Handling General Query");
        }
        else if (nextHandler != null) {

            nextHandler.handleRequest(requestType);
        }
    }
}

/*
===============================================================================

                CONCRETE HANDLER

Billing Support

===============================================================================
*/

class BillingSupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("refund")) {

            System.out.println(
                    "Billing Support : Handling Refund Request");
        }
        else if (nextHandler != null) {

            nextHandler.handleRequest(requestType);
        }
    }
}

/*
===============================================================================

                CONCRETE HANDLER

Technical Support

===============================================================================
*/

class TechnicalSupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("technical")) {

            System.out.println(
                    "Technical Support : Handling Technical Issue");
        }
        else if (nextHandler != null) {

            nextHandler.handleRequest(requestType);
        }
    }
}

/*
===============================================================================

                CONCRETE HANDLER

Delivery Support

===============================================================================
*/

class DeliverySupport extends SupportHandler {

    @Override
    public void handleRequest(String requestType) {

        if (requestType.equalsIgnoreCase("delivery")) {

            System.out.println(
                    "Delivery Support : Handling Delivery Issue");
        }
        else if (nextHandler != null) {

            nextHandler.handleRequest(requestType);
        }
        else {

            System.out.println(
                    "No Support Handler Found for : "
                            + requestType);
        }
    }
}

/*
===============================================================================

                        CLIENT

Creates the chain:

General

↓

Billing

↓

Technical

↓

Delivery

The request automatically moves through the chain until handled.

===============================================================================
*/

public class ChainOfResponsibilityPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Create Handlers

        ===============================================================
        */

        SupportHandler general =

                new GeneralSupport();

        SupportHandler billing =

                new BillingSupport();

        SupportHandler technical =

                new TechnicalSupport();

        SupportHandler delivery =

                new DeliverySupport();

        /*
        ===============================================================

        Build Chain

        General

            ↓

        Billing

            ↓

        Technical

            ↓

        Delivery

        ===============================================================
        */

        general.setNextHandler(billing);

        billing.setNextHandler(technical);

        technical.setNextHandler(delivery);

        /*
        ===============================================================

        Refund Request

        ===============================================================
        */

        general.handleRequest("refund");

        System.out.println();

        /*
        ===============================================================

        Delivery Request

        ===============================================================
        */

        general.handleRequest("delivery");

        System.out.println();

        /*
        ===============================================================

        Unknown Request

        ===============================================================
        */

        general.handleRequest("unknown");
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Chain

General

↓

Billing

↓

Technical

↓

Delivery

------------------------------------------------------------

Step 2

Request

refund

↓

General

↓

Cannot Handle

↓

Billing

↓

Handles Request

------------------------------------------------------------

Step 3

Request

delivery

↓

General

↓

Billing

↓

Technical

↓

Delivery

↓

Handles Request

------------------------------------------------------------

Step 4

Request

unknown

↓

General

↓

Billing

↓

Technical

↓

Delivery

↓

No Handler Found

===============================================================================

Flow Diagram


                SupportHandler
              (Abstract Handler)

                     |

    ---------------------------------------------

    |            |            |                |

General      Billing     Technical       Delivery

Support      Support      Support         Support

                     |

               Next Handler

===============================================================================

Execution Flow


Client

   |

General Support

   |

Billing Support

   |

Technical Support

   |

Delivery Support

   |

Request Handled

===============================================================================

OUTPUT

Billing Support : Handling Refund Request

Delivery Support : Handling Delivery Issue

No Support Handler Found for : unknown

===============================================================================

Advantages

✔ Loose coupling between sender and receiver.

✔ Easy to add new handlers.

✔ Flexible request processing.

✔ Follows Open/Closed Principle.

✔ Reduces complex if-else chains.

===============================================================================

Disadvantages

✘ Request may travel through many handlers.

✘ Harder to debug long chains.

✘ No guarantee that a request will be handled.

===============================================================================

When to Use

✔ Customer Support Systems.

✔ Exception Handling.

✔ Logging Frameworks.

✔ Authentication Filters.

✔ Servlet Filters.

✔ Middleware Pipelines.

===============================================================================

Interview Questions

Q1. What is Chain of Responsibility Pattern?

Ans:

It passes a request through a chain of handlers until one handler processes
the request.

------------------------------------------------

Q2. Which is the Handler?

Ans:

SupportHandler.

------------------------------------------------

Q3. Which are the Concrete Handlers?

Ans:

GeneralSupport

BillingSupport

TechnicalSupport

DeliverySupport

------------------------------------------------

Q4. Why use Chain of Responsibility?

Ans:

To decouple the sender from the receiver and allow multiple handlers to
process a request.

------------------------------------------------

Q5. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

New handlers can be added without modifying existing handlers.

------------------------------------------------

Q6. Which category does Chain of Responsibility belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Chain of Responsibility Pattern is a Behavioral Design Pattern that allows
a request to pass through a chain of handlers until one of them handles it.
Each handler decides whether it can process the request or forward it to
the next handler.

In this example, SupportHandler is the Abstract Handler, GeneralSupport,
BillingSupport, TechnicalSupport, and DeliverySupport are the Concrete
Handlers. The client builds the chain, and requests automatically travel
through it until an appropriate handler is found. This makes the system
flexible, loosely coupled, and easy to extend.

===============================================================================
*/