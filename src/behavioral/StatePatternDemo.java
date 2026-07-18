/*
===============================================================================

                        STATE DESIGN PATTERN

Definition:
------------
State Pattern allows an object to change its behavior when its internal
state changes. The object appears to change its class by delegating
behavior to different State objects.

Instead of using multiple if-else or switch statements, each state
encapsulates its own behavior.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose you order food from Zomato or Swiggy.

An order moves through different states:

✔ Order Placed
✔ Preparing
✔ Out for Delivery
✔ Delivered
✔ Cancelled

The actions available depend on the current state.

For example,

✔ Order can be cancelled while Preparing.

✘ Order cannot be cancelled after it is Out for Delivery.

Each state controls its own behavior.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                        CONTEXT

Maintains the current state of the order.

Delegates all actions to the current state.

===============================================================================
*/

class OrderContext {

    private OrderState currentState;

    public OrderContext() {

        currentState = new OrderPlacedState();
    }

    public void setState(OrderState state) {

        this.currentState = state;
    }

    public void next() {

        currentState.next(this);
    }

    public void cancel() {

        currentState.cancel(this);
    }

    public String getCurrentState() {

        return currentState.getStateName();
    }
}

/*
===============================================================================

                        STATE INTERFACE

Every state should provide:

next()

cancel()

getStateName()

===============================================================================
*/

interface OrderState {

    void next(OrderContext context);

    void cancel(OrderContext context);

    String getStateName();
}

/*
===============================================================================

                CONCRETE STATE

Order Placed

===============================================================================
*/

class OrderPlacedState implements OrderState {

    @Override
    public void next(OrderContext context) {

        context.setState(new PreparingState());

        System.out.println(
                "Order is now being Prepared.");
    }

    @Override
    public void cancel(OrderContext context) {

        context.setState(new CancelledState());

        System.out.println(
                "Order has been Cancelled.");
    }

    @Override
    public String getStateName() {

        return "ORDER_PLACED";
    }
}

/*
===============================================================================

                CONCRETE STATE

Preparing

===============================================================================
*/

class PreparingState implements OrderState {

    @Override
    public void next(OrderContext context) {

        context.setState(new OutForDeliveryState());

        System.out.println(
                "Order is Out for Delivery.");
    }

    @Override
    public void cancel(OrderContext context) {

        context.setState(new CancelledState());

        System.out.println(
                "Order has been Cancelled.");
    }

    @Override
    public String getStateName() {

        return "PREPARING";
    }
}

/*
===============================================================================

                CONCRETE STATE

Out For Delivery

===============================================================================
*/

class OutForDeliveryState implements OrderState {

    @Override
    public void next(OrderContext context) {

        context.setState(new DeliveredState());

        System.out.println(
                "Order has been Delivered.");
    }

    @Override
    public void cancel(OrderContext context) {

        System.out.println(
                "Cannot Cancel. Order is Out for Delivery.");
    }

    @Override
    public String getStateName() {

        return "OUT_FOR_DELIVERY";
    }
}

/*
===============================================================================

                CONCRETE STATE

Delivered

===============================================================================
*/

class DeliveredState implements OrderState {

    @Override
    public void next(OrderContext context) {

        System.out.println(
                "Order is already Delivered.");
    }

    @Override
    public void cancel(OrderContext context) {

        System.out.println(
                "Cannot Cancel a Delivered Order.");
    }

    @Override
    public String getStateName() {

        return "DELIVERED";
    }
}

/*
===============================================================================

                CONCRETE STATE

Cancelled

===============================================================================
*/

class CancelledState implements OrderState {

    @Override
    public void next(OrderContext context) {

        System.out.println(
                "Cancelled Order cannot move to next State.");
    }

    @Override
    public void cancel(OrderContext context) {

        System.out.println(
                "Order is already Cancelled.");
    }

    @Override
    public String getStateName() {

        return "CANCELLED";
    }
}

/*
===============================================================================

                        CLIENT

Client only interacts with OrderContext.

The current state decides how the request is handled.

===============================================================================
*/

public class StatePatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Create Order

        Default State

        ORDER_PLACED

        ===============================================================
        */

        OrderContext order = new OrderContext();

        System.out.println(
                "Current State : "
                        + order.getCurrentState());

        System.out.println();

        /*
        ===============================================================

        Move to Preparing

        ===============================================================
        */

        order.next();

        /*
        ===============================================================

        Move to Out For Delivery

        ===============================================================
        */

        order.next();

        /*
        ===============================================================

        Try Cancelling

        ===============================================================
        */

        order.cancel();

        /*
        ===============================================================

        Deliver Order

        ===============================================================
        */

        order.next();

        /*
        ===============================================================

        Try Cancelling Again

        ===============================================================
        */

        order.cancel();

        System.out.println();

        System.out.println(
                "Final State : "
                        + order.getCurrentState());
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Order

↓

Current State

ORDER_PLACED

------------------------------------------------------------

Step 2

next()

↓

PreparingState

↓

Order is now being Prepared

------------------------------------------------------------

Step 3

next()

↓

OutForDeliveryState

↓

Order is Out for Delivery

------------------------------------------------------------

Step 4

cancel()

↓

Not Allowed

↓

Cannot Cancel

------------------------------------------------------------

Step 5

next()

↓

DeliveredState

↓

Order Delivered

------------------------------------------------------------

Step 6

cancel()

↓

Cannot Cancel Delivered Order

===============================================================================

Flow Diagram


                  OrderContext
                    (Context)

                       |

                  OrderState
                (State Interface)

        ----------------------------------------

        |         |         |        |          |

 OrderPlaced  Preparing  OutForDelivery  Delivered  Cancelled

===============================================================================

Execution Flow


Client

   |

OrderContext

   |

Current State

   |

Concrete State

   |

Execute Behavior

===============================================================================

OUTPUT

Current State : ORDER_PLACED

Order is now being Prepared.

Order is Out for Delivery.

Cannot Cancel. Order is Out for Delivery.

Order has been Delivered.

Cannot Cancel a Delivered Order.

Final State : DELIVERED

===============================================================================

Advantages

✔ Eliminates large if-else or switch statements.

✔ Encapsulates state-specific behavior.

✔ Easy to add new states.

✔ Follows Open/Closed Principle.

✔ Improves maintainability.

===============================================================================

Disadvantages

✘ Increases the number of classes.

✘ Can become complex with many states.

===============================================================================

When to Use

✔ Order Management Systems.

✔ ATM Machines.

✔ Traffic Light Systems.

✔ Media Players.

✔ Document Workflow.

✔ Game Characters.

===============================================================================

Interview Questions

Q1. What is State Pattern?

Ans:

State Pattern allows an object to change its behavior when its internal
state changes by delegating work to State objects.

------------------------------------------------

Q2. Which is the Context?

Ans:

OrderContext.

------------------------------------------------

Q3. Which is the State Interface?

Ans:

OrderState.

------------------------------------------------

Q4. Which are the Concrete States?

Ans:

OrderPlacedState

PreparingState

OutForDeliveryState

DeliveredState

CancelledState

------------------------------------------------

Q5. Why use State Pattern?

Ans:

To remove complex if-else logic and encapsulate state-specific behavior.

------------------------------------------------

Q6. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

New states can be added without modifying existing code.

------------------------------------------------

Q7. Which category does State belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

State Pattern is a Behavioral Design Pattern that allows an object to alter
its behavior when its internal state changes. Instead of using large
if-else or switch statements, each state is represented by a separate
class containing its own behavior.

In this example, OrderContext is the Context, OrderState is the State
interface, and OrderPlacedState, PreparingState, OutForDeliveryState,
DeliveredState, and CancelledState are the Concrete States. Each state
controls valid transitions and actions, making the design clean,
extensible, and easy to maintain.

===============================================================================
*/