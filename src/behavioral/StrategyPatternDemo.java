package behavioral;/*
===============================================================================

                        STRATEGY DESIGN PATTERN

Definition:
------------
Strategy Pattern defines a family of algorithms, encapsulates each one,
and makes them interchangeable at runtime.

Instead of hardcoding an algorithm inside a class, the behavior is delegated
to separate Strategy classes. The client or context can switch strategies
dynamically without modifying existing code.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose you book a ride using Uber.

Depending on the situation, Uber may use different strategies to match
a rider with a driver.

✔ Nearest Driver
✔ Airport Queue
✔ Surge Priority

The Ride Matching Service doesn't know the matching logic.
It simply delegates the work to the selected strategy.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                        STRATEGY INTERFACE

Every matching strategy should provide:

match()

===============================================================================
*/

interface MatchingStrategy {

    void match(String riderLocation);
}

/*
===============================================================================

                CONCRETE STRATEGY

Nearest Driver Strategy

===============================================================================
*/

class NearestDriverStrategy implements MatchingStrategy {

    @Override
    public void match(String riderLocation) {

        System.out.println(
                "Matching with the nearest available driver to "
                        + riderLocation
        );
    }
}

/*
===============================================================================

                CONCRETE STRATEGY

Airport Queue Strategy

===============================================================================
*/

class AirportQueueStrategy implements MatchingStrategy {

    @Override
    public void match(String riderLocation) {

        System.out.println(
                "Matching using FIFO Airport Queue for "
                        + riderLocation
        );
    }
}

/*
===============================================================================

                CONCRETE STRATEGY

Surge Priority Strategy

===============================================================================
*/

class SurgePriorityStrategy implements MatchingStrategy {

    @Override
    public void match(String riderLocation) {

        System.out.println(
                "Matching rider using Surge Pricing Priority near "
                        + riderLocation
        );
    }
}

/*
===============================================================================

                        CONTEXT

RideMatchingService contains a reference to MatchingStrategy.

The strategy can be changed dynamically at runtime.

===============================================================================
*/

class RideMatchingService {

    private MatchingStrategy strategy;

    public RideMatchingService(MatchingStrategy strategy) {

        this.strategy = strategy;
    }

    public void setStrategy(MatchingStrategy strategy) {

        this.strategy = strategy;
    }

    public void matchRider(String location) {

        strategy.match(location);
    }
}

/*
===============================================================================

                        CLIENT

Client selects the strategy.

The context delegates the work to the selected strategy.

===============================================================================
*/

public class StrategyPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Airport Queue Strategy

        ===============================================================
        */

        RideMatchingService rideService =

                new RideMatchingService(
                        new AirportQueueStrategy());

        rideService.matchRider("Terminal 1");

        System.out.println();

        System.out.println("--------------------------------");

        System.out.println();

        /*
        ===============================================================

        Nearest Driver Strategy

        ===============================================================
        */

        RideMatchingService rideService2 =

                new RideMatchingService(
                        new NearestDriverStrategy());

        rideService2.matchRider("Downtown");

        System.out.println();

        /*
        ===============================================================

        Change Strategy at Runtime

        Surge Priority Strategy

        ===============================================================
        */

        rideService2.setStrategy(
                new SurgePriorityStrategy());

        rideService2.matchRider("Downtown");
    }
}

/*
===============================================================================

Dry Run

Step 1

Client creates

RideMatchingService

↓

AirportQueueStrategy

------------------------------------------------------------

Step 2

matchRider("Terminal 1")

↓

AirportQueueStrategy.match()

↓

FIFO Airport Queue Matching

------------------------------------------------------------

Step 3

Client creates

RideMatchingService

↓

NearestDriverStrategy

------------------------------------------------------------

Step 4

matchRider("Downtown")

↓

NearestDriverStrategy.match()

↓

Nearest Driver Selected

------------------------------------------------------------

Step 5

Client changes strategy

↓

SurgePriorityStrategy

------------------------------------------------------------

Step 6

matchRider("Downtown")

↓

SurgePriorityStrategy.match()

↓

Premium Driver Selected

===============================================================================

Flow Diagram


                    Client

                       |

              RideMatchingService

                 (Context)

                       |

               MatchingStrategy

             (Strategy Interface)

            /         |          \

           /          |           \

NearestDriver   AirportQueue   SurgePriority

   Strategy        Strategy       Strategy

===============================================================================

Execution Flow


Client

   |

RideMatchingService

   |

MatchingStrategy

   |

Concrete Strategy

   |

Matching Logic

===============================================================================

OUTPUT

Matching using FIFO Airport Queue for Terminal 1

--------------------------------

Matching with the nearest available driver to Downtown

Matching rider using Surge Pricing Priority near Downtown

===============================================================================

Advantages

✔ Algorithms are interchangeable.

✔ Follows Open/Closed Principle.

✔ Removes multiple if-else conditions.

✔ Easy to add new strategies.

✔ Promotes composition over inheritance.

===============================================================================

Disadvantages

✘ Increases the number of classes.

✘ Client must know which strategy to choose.

===============================================================================

When to Use

✔ Payment Methods.

✔ Ride Matching.

✔ Sorting Algorithms.

✔ Compression Algorithms.

✔ Authentication Methods.

✔ Pricing Strategies.

===============================================================================

Interview Questions

Q1. What is Strategy Pattern?

Ans:

Strategy Pattern defines a family of algorithms, encapsulates each one,
and makes them interchangeable at runtime.

------------------------------------------------

Q2. Which is the Strategy?

Ans:

MatchingStrategy

------------------------------------------------

Q3. Which are the Concrete Strategies?

Ans:

NearestDriverStrategy

AirportQueueStrategy

SurgePriorityStrategy

------------------------------------------------

Q4. Which is the Context?

Ans:

RideMatchingService

------------------------------------------------

Q5. Why use Strategy Pattern?

Ans:

To switch algorithms dynamically without changing the context class.

------------------------------------------------

Q6. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

New strategies can be added without modifying existing code.

------------------------------------------------

Q7. Which category does Strategy belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Strategy Pattern is a Behavioral Design Pattern that encapsulates multiple
algorithms into separate strategy classes and allows them to be selected
or changed at runtime. Instead of hardcoding logic, the Context delegates
the work to a Strategy object.

In this example, MatchingStrategy is the Strategy interface,
NearestDriverStrategy, AirportQueueStrategy, and SurgePriorityStrategy are
the Concrete Strategies, and RideMatchingService is the Context. The client
can switch matching algorithms dynamically, making the code flexible,
extensible, and compliant with the Open/Closed Principle.

===============================================================================
*/