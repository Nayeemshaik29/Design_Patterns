/*
===============================================================================

                    FACTORY DESIGN PATTERN

Definition:
------------
Factory Pattern creates objects without exposing the object creation
logic to the client.

Instead of using:

    new Air();
    new Road();

the client simply asks the Factory for the required object.

Real World Examples:
--------------------
1. Payment Gateway (UPI, Credit Card, PayPal)
2. Notification Service (Email, SMS, Push)
3. Logistics Service (Air, Road, Sea)
4. Database Driver Selection

===============================================================================
*/


/*
===============================================================================

Product Interface

Every logistics type must implement send().

===============================================================================
*/

interface Logistics {

    // Common method for all logistics

    void send();
}


/*
===============================================================================

Concrete Product - Road

Implements the Logistics interface.

Contains Road specific business logic.

===============================================================================
*/

class Road implements Logistics {

    @Override
    public void send() {

        System.out.println("Sending package using Road Transport.");
    }
}


/*
===============================================================================

Concrete Product - Air

Implements the Logistics interface.

Contains Air specific business logic.

===============================================================================
*/

class Air implements Logistics {

    @Override
    public void send() {

        System.out.println("Sending package using Air Transport.");
    }
}


/*
===============================================================================

Factory Class

Responsibility:
---------------
Creates and returns the appropriate object based on user input.

Client does NOT know which object is being created.

Client simply asks:

    LogisticsFactory.getLogistics("Air");

Factory decides which object to create.

===============================================================================
*/

class LogisticsFactory {

    public static Logistics getLogistics(String mode) {

        // Create Air object

        if (mode.equalsIgnoreCase("Air")) {

            return new Air();
        }

        // Create Road object

        else if (mode.equalsIgnoreCase("Road")) {

            return new Road();
        }

        // Invalid input

        throw new IllegalArgumentException(
                "Unknown logistics mode : " + mode);
    }
}


/*
===============================================================================

Service Class

Uses Factory instead of directly creating objects.

Without Factory:

    Logistics logistics = new Air();

With Factory:

    Logistics logistics =
            LogisticsFactory.getLogistics(mode);

This follows loose coupling.

===============================================================================
*/

class LogisticsService {

    public void send(String mode) {

        /*
        Ask Factory to create the required object.

        Factory handles object creation.
        */

        Logistics logistics =
                LogisticsFactory.getLogistics(mode);

        // Execute business logic

        logistics.send();
    }
}


/*
===============================================================================

Driver Class

===============================================================================
*/

public class FactoryPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Create Service Object

        ===============================================================
        */

        LogisticsService service = new LogisticsService();



        /*
        ===============================================================

        User selects Air

        Service

            |

        Factory

            |

        Creates Air Object

            |

        send()

        ===============================================================
        */

        service.send("Air");



        /*
        ===============================================================

        User selects Road

        Service

            |

        Factory

            |

        Creates Road Object

            |

        send()

        ===============================================================
        */

        service.send("Road");
    }
}


/*
===============================================================================

Dry Run

Client

service.send("Air")

        |

LogisticsService

        |

LogisticsFactory.getLogistics("Air")

        |

Creates

        Air Object

        |

send()

Output:

Sending package using Air Transport.



------------------------------------------------------


Client

service.send("Road")

        |

LogisticsService

        |

LogisticsFactory.getLogistics("Road")

        |

Creates

        Road Object

        |

send()

Output:

Sending package using Road Transport.


===============================================================================

Flow Diagram

                Client
                   |
                   |
                   v
          LogisticsService
                   |
                   |
                   v
           LogisticsFactory
              /          \
             /            \
            v              v
         Air Object     Road Object
            |              |
            |              |
            v              v
          send()         send()


===============================================================================

OUTPUT

Sending package using Air Transport.

Sending package using Road Transport.

===============================================================================

Advantages

✔ Client does not know object creation logic.

✔ Loose coupling.

✔ Easy to add new implementations.

✔ Follows Open-Closed Principle.

✔ Centralized object creation.

===============================================================================

Disadvantages

✘ More classes.

✘ Slightly increases complexity.

===============================================================================

Interview Questions

Q1. What is Factory Pattern?

Ans:
Factory Pattern creates objects without exposing the object creation
logic to the client.

------------------------------------------------

Q2. Why use Factory Pattern?

Ans:
To achieve loose coupling and centralize object creation.

------------------------------------------------

Q3. Which class is responsible for object creation?

Ans:
LogisticsFactory.

------------------------------------------------

Q4. Which design pattern category does it belong to?

Ans:
Creational Design Pattern.

------------------------------------------------

Q5. What principle does Factory Pattern follow?

Ans:
Open-Closed Principle.

New object types can be added without changing client code.

===============================================================================

Interview Summary (30 Seconds)

Factory Pattern is a Creational Design Pattern that hides object creation
from the client. The client requests an object from the Factory instead
of creating it directly using the new keyword. This promotes loose
coupling, better maintainability, and scalability. In this example,
LogisticsFactory creates either an Air or Road object based on the
requested transport mode.

===============================================================================
*/