/*
===============================================================================

                        FACADE DESIGN PATTERN

Definition:
------------
Facade Pattern provides a simplified interface to a complex subsystem.

Instead of interacting with multiple classes directly, the client communicates
with only one class called the Facade.

Category:
----------
Structural Design Pattern

Real World Example:
-------------------
Suppose you want to book a movie ticket.

Without Facade, you need to:

✔ Make Payment
✔ Reserve Seat
✔ Generate Ticket
✔ Add Loyalty Points
✔ Send Confirmation Email

The customer doesn't want to call all these services manually.

Instead, he simply calls:

bookMovieTicket()

The Facade internally performs all these operations.

===============================================================================
*/

/*
===============================================================================

                    SUBSYSTEM 1

                Payment Service

Responsible for payment.

===============================================================================
*/

class PaymentService {

    public void makePayment(String accountId, double amount) {

        System.out.println("Payment of ₹" + amount +
                " successful for account " + accountId);
    }
}

/*
===============================================================================

                    SUBSYSTEM 2

            Seat Reservation Service

Responsible for reserving seats.

===============================================================================
*/

class SeatReservationService {

    public void reserveSeat(String movieId, String seatNumber) {

        System.out.println("Seat " + seatNumber +
                " reserved for movie " + movieId);
    }
}

/*
===============================================================================

                    SUBSYSTEM 3

                Ticket Service

Responsible for ticket generation.

===============================================================================
*/

class TicketService {

    public void generateTicket(String movieId,
                               String seatNumber) {

        System.out.println("Ticket generated for movie "
                + movieId + ", Seat: " + seatNumber);
    }
}

/*
===============================================================================

                    SUBSYSTEM 4

            Loyalty Points Service

Responsible for reward points.

===============================================================================
*/

class LoyaltyPointsService {

    public void addPoints(String accountId,
                          int points) {

        System.out.println(points +
                " loyalty points added to account "
                + accountId);
    }
}

/*
===============================================================================

                    SUBSYSTEM 5

            Notification Service

Responsible for sending confirmation.

===============================================================================
*/

class NotificationService {

    public void sendBookingConfirmation(String email) {

        System.out.println("Booking confirmation sent to "
                + email);
    }
}

/*
===============================================================================

                        FACADE

Provides one simple method:

bookMovieTicket()

Internally coordinates all subsystem classes.

===============================================================================
*/

class MovieBookingFacade {

    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private TicketService ticketService;
    private LoyaltyPointsService loyaltyPointsService;
    private NotificationService notificationService;

    public MovieBookingFacade() {

        paymentService = new PaymentService();
        seatReservationService = new SeatReservationService();
        ticketService = new TicketService();
        loyaltyPointsService = new LoyaltyPointsService();
        notificationService = new NotificationService();
    }

    public void bookMovieTicket(String accountId,
                                String movieId,
                                String seatNumber,
                                String email,
                                double amount) {

        paymentService.makePayment(accountId, amount);

        seatReservationService.reserveSeat(movieId, seatNumber);

        ticketService.generateTicket(movieId, seatNumber);

        loyaltyPointsService.addPoints(accountId, 50);

        notificationService.sendBookingConfirmation(email);

        System.out.println();

        System.out.println("Movie Ticket Booking Completed Successfully!");
    }
}

/*
===============================================================================

                        CLIENT

Client only knows the Facade.

It doesn't know anything about PaymentService,
TicketService,
NotificationService etc.

===============================================================================
*/

public class FacadePatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Client books movie ticket

        Only one method call

        bookMovieTicket()

        ===============================================================
        */

        MovieBookingFacade facade = new MovieBookingFacade();

        facade.bookMovieTicket(
                "user123",
                "movie456",
                "A10",
                "user@example.com",
                500
        );
    }
}

/*
===============================================================================

Dry Run

Step 1

Client creates

MovieBookingFacade

------------------------------------------------------------

Step 2

Client calls

bookMovieTicket()

------------------------------------------------------------

Facade internally performs

↓

PaymentService.makePayment()

↓

SeatReservationService.reserveSeat()

↓

TicketService.generateTicket()

↓

LoyaltyPointsService.addPoints()

↓

NotificationService.sendBookingConfirmation()

------------------------------------------------------------

Finally

Movie Ticket Booking Completed Successfully!

===============================================================================

Flow Diagram


                    Client

                       |

                       |

             MovieBookingFacade

        _________________________________

       |         |          |            |

       |         |          |            |

 PaymentService  SeatReservationService

                          |

                    TicketService

                          |

                 LoyaltyPointsService

                          |

                 NotificationService


===============================================================================

Execution Flow


Client

   |

MovieBookingFacade

   |

PaymentService

   |

SeatReservationService

   |

TicketService

   |

LoyaltyPointsService

   |

NotificationService

   |

Booking Successful


===============================================================================

OUTPUT

Payment of ₹500.0 successful for account user123

Seat A10 reserved for movie movie456

Ticket generated for movie movie456, Seat: A10

50 loyalty points added to account user123

Booking confirmation sent to user@example.com

Movie Ticket Booking Completed Successfully!

===============================================================================

Advantages

✔ Simplifies complex systems.

✔ Hides subsystem complexity.

✔ Reduces coupling.

✔ Easy to use.

✔ Improves code readability.

✔ Client depends only on Facade.

===============================================================================

Disadvantages

✘ Facade can become a God Class if too many responsibilities are added.

✘ Limited flexibility if client needs subsystem-specific features.

===============================================================================

When to Use

✔ When a subsystem is complex.

✔ When you want a single entry point.

✔ When reducing coupling is important.

✔ When exposing only necessary functionality.

===============================================================================

Interview Questions

Q1. What is Facade Pattern?

Ans:
Facade Pattern provides a simplified interface to a complex subsystem.

------------------------------------------------

Q2. Which class is the Facade?

Ans:

MovieBookingFacade

------------------------------------------------

Q3. What are the subsystem classes?

Ans:

PaymentService

SeatReservationService

TicketService

LoyaltyPointsService

NotificationService

------------------------------------------------

Q4. Why use Facade Pattern?

Ans:

To reduce complexity and provide a single interface.

------------------------------------------------

Q5. Does Facade add new functionality?

Ans:

No.

It only simplifies access to existing functionality.

------------------------------------------------

Q6. Which SOLID Principle is supported?

Ans:

Dependency Inversion Principle

Client depends on Facade instead of multiple subsystem classes.

------------------------------------------------

Q7. Which category does Facade belong to?

Ans:

Structural Design Pattern.

===============================================================================

30-Second Interview Summary

Facade Pattern is a Structural Design Pattern that provides a single,
simplified interface to a complex subsystem. Instead of interacting with
multiple classes individually, the client communicates only with the
Facade class.

In this example, MovieBookingFacade coordinates PaymentService,
SeatReservationService, TicketService, LoyaltyPointsService, and
NotificationService. The client simply calls bookMovieTicket(), and the
Facade internally performs all required operations, making the code easy
to use, loosely coupled, and easier to maintain.

===============================================================================
*/