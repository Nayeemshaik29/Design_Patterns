/*
===============================================================================

                    ABSTRACT FACTORY DESIGN PATTERN

Definition:
------------
Abstract Factory Pattern provides an interface for creating families of
related objects without specifying their concrete classes.

Unlike Factory Pattern (creates one object),
Abstract Factory creates a family of related objects.

Real World Example:
-------------------
E-commerce Checkout System

India
------
Payment Gateway -> Razorpay / PayU
Invoice         -> GST Invoice

US
----
Payment Gateway -> PayPal / Stripe
Invoice         -> US Invoice

Client only selects Region.
Factory creates all required objects.

===============================================================================
*/


/*
===============================================================================

                        PRODUCT INTERFACES

Every Payment Gateway must implement processPayment().

Every Invoice must implement generateInvoice().

===============================================================================
*/

interface PaymentGateway {

    void processPayment(double amount);
}

interface Invoice {

    void generateInvoice();
}


/*
===============================================================================

                INDIA IMPLEMENTATIONS

===============================================================================
*/

/*
India Payment Gateway - Razorpay
*/

class RazorpayGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {

        System.out.println(
                "Processing INR payment via Razorpay : ₹" + amount);
    }
}


/*
India Payment Gateway - PayU
*/

class PayUGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {

        System.out.println(
                "Processing INR payment via PayU : ₹" + amount);
    }
}


/*
India Invoice
*/

class GSTInvoice implements Invoice {

    @Override
    public void generateInvoice() {

        System.out.println(
                "Generating GST Invoice for India.");
    }
}


/*
===============================================================================

                    US IMPLEMENTATIONS

===============================================================================
*/

/*
US Payment Gateway - PayPal
*/

class PayPalGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {

        System.out.println(
                "Processing USD payment via PayPal : $" + amount);
    }
}


/*
US Payment Gateway - Stripe
*/

class StripeGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {

        System.out.println(
                "Processing USD payment via Stripe : $" + amount);
    }
}


/*
US Invoice
*/

class USInvoice implements Invoice {

    @Override
    public void generateInvoice() {

        System.out.println(
                "Generating Invoice as per US norms.");
    }
}


/*
===============================================================================

                    ABSTRACT FACTORY

Creates a FAMILY of objects.

1. Payment Gateway
2. Invoice

Every region must implement these methods.

===============================================================================
*/

interface RegionFactory {

    PaymentGateway createPaymentGateway(String gatewayType);

    Invoice createInvoice();
}


/*
===============================================================================

                    INDIA FACTORY

Responsible for creating India related objects.

===============================================================================
*/

class IndiaFactory implements RegionFactory {

    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {

        if (gatewayType.equalsIgnoreCase("razorpay")) {

            return new RazorpayGateway();
        }

        else if (gatewayType.equalsIgnoreCase("payu")) {

            return new PayUGateway();
        }

        throw new IllegalArgumentException(
                "Unsupported Gateway : " + gatewayType);
    }

    @Override
    public Invoice createInvoice() {

        return new GSTInvoice();
    }
}


/*
===============================================================================

                    US FACTORY

Responsible for creating US related objects.

===============================================================================
*/

class USFactory implements RegionFactory {

    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {

        if (gatewayType.equalsIgnoreCase("paypal")) {

            return new PayPalGateway();
        }

        else if (gatewayType.equalsIgnoreCase("stripe")) {

            return new StripeGateway();
        }

        throw new IllegalArgumentException(
                "Unsupported Gateway : " + gatewayType);
    }

    @Override
    public Invoice createInvoice() {

        return new USInvoice();
    }
}


/*
===============================================================================

                    CLIENT / SERVICE CLASS

Uses Abstract Factory instead of directly creating objects.

Without Abstract Factory

new RazorpayGateway();
new GSTInvoice();

With Abstract Factory

factory.createPaymentGateway();
factory.createInvoice();

Client is independent of concrete classes.

===============================================================================
*/

class CheckoutService {

    private PaymentGateway paymentGateway;

    private Invoice invoice;

    /*
    Constructor receives Abstract Factory.

    Factory decides which objects to create.
    */

    public CheckoutService(RegionFactory factory,
                           String gatewayType) {

        paymentGateway =
                factory.createPaymentGateway(gatewayType);

        invoice =
                factory.createInvoice();
    }

    /*
    Business Method

    1. Process Payment

    2. Generate Invoice

    */

    public void completeOrder(double amount) {

        paymentGateway.processPayment(amount);

        invoice.generateInvoice();
    }
}


/*
===============================================================================

                        DRIVER CLASS

===============================================================================
*/

public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        INDIA CHECKOUT

        Client

            |

            |

        India Factory

        /             \

RazorpayGateway    GSTInvoice

        ===============================================================
        */

        CheckoutService indiaCheckout =

                new CheckoutService(
                        new IndiaFactory(),
                        "razorpay");

        indiaCheckout.completeOrder(1999);

        System.out.println();

        System.out.println("--------------------------------");

        System.out.println();


        /*
        ===============================================================

        US CHECKOUT

        Client

            |

            |

        US Factory

        /             \

PayPalGateway      USInvoice

        ===============================================================
        */

        CheckoutService usCheckout =

                new CheckoutService(
                        new USFactory(),
                        "paypal");

        usCheckout.completeOrder(49.99);
    }
}


/*
===============================================================================

Dry Run

Client

new CheckoutService(new IndiaFactory(),"razorpay")

                |

                |

        IndiaFactory

        /                \

createPaymentGateway()   createInvoice()

        |                     |

        |                     |

RazorpayGateway        GSTInvoice

        |

completeOrder()

        |

processPayment()

        |

generateInvoice()


------------------------------------------------------


Client

new CheckoutService(new USFactory(),"paypal")

                |

                |

            USFactory

        /                 \

PayPalGateway        USInvoice

        |

completeOrder()

        |

processPayment()

        |

generateInvoice()


===============================================================================

Flow Diagram


                    Client

                       |

                       |

                CheckoutService

                       |

                       |

                RegionFactory

                /             \

       IndiaFactory        USFactory

        /       \            /      \

 Razorpay   GSTInvoice   PayPal   USInvoice

===============================================================================

OUTPUT

Processing INR payment via Razorpay : ₹1999.0

Generating GST Invoice for India.

--------------------------------

Processing USD payment via PayPal : $49.99

Generating Invoice as per US norms.

===============================================================================

Advantages

✔ Creates families of related objects.

✔ Loose Coupling.

✔ Easy to add new regions.

✔ Follows Open-Closed Principle.

✔ Client doesn't know concrete classes.

===============================================================================

Disadvantages

✘ More interfaces and classes.

✘ Slightly complex compared to Factory Pattern.

===============================================================================

Interview Questions

Q1. What is Abstract Factory Pattern?

Ans:
It creates families of related objects without specifying their
concrete classes.

------------------------------------------------

Q2. Difference between Factory and Abstract Factory?

Factory Pattern
---------------
Creates ONE object.

Example:
Payment Gateway only.

Abstract Factory
----------------
Creates a FAMILY of related objects.

Example:
Payment Gateway + Invoice.

------------------------------------------------

Q3. Which class creates India objects?

Ans:
IndiaFactory.

------------------------------------------------

Q4. Which Design Pattern category?

Ans:
Creational Design Pattern.

------------------------------------------------

Q5. What principle does it follow?

Ans:
Open-Closed Principle and Dependency Inversion Principle.

===============================================================================

Interview Summary (30 Seconds)

Abstract Factory is a Creational Design Pattern that provides an interface
for creating families of related objects without exposing their concrete
implementations. In this example, RegionFactory creates both PaymentGateway
and Invoice objects. IndiaFactory produces Razorpay/GSTInvoice, while
USFactory produces PayPal/USInvoice, allowing CheckoutService to remain
independent of concrete classes and making the system scalable and loosely
coupled.

===============================================================================
*/