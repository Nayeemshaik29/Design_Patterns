package DependencyInjection;

/**
 * ============================================================================
 * File Name : OrderServiceWithDependencyInjection.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Constructor Dependency Injection (Loose Coupling)
 * ============================================================================
 *
 * PROBLEM STATEMENT
 * -----------------
 *
 * In the previous example,
 *
 * OrderService created its own dependencies.
 *
 *      new InventoryService()
 *      new RazorpayPayment()
 *      new NotificationService()
 *
 * This created Tight Coupling.
 *
 * ============================================================================
 * SOLUTION
 * ============================================================================
 *
 * Instead of creating dependencies,
 *
 * OrderService will RECEIVE them
 * from outside.
 *
 * This technique is called
 *
 *          Dependency Injection (DI)
 *
 * Dependency Injection is a Design Pattern.
 *
 * Spring Framework implements this pattern
 * using its IoC (Inversion of Control) Container.
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Imagine you buy a TV.
 *
 * This time,
 *
 * the TV does NOT come with a built-in remote.
 *
 * Instead,
 *
 * you can connect
 *
 *      Samsung Remote
 *      Sony Remote
 *      LG Remote
 *      Universal Remote
 *
 * The TV doesn't care
 * which remote you use.
 *
 * Someone else provides the remote.
 *
 * This is Dependency Injection.
 *
 * ============================================================================
 * BENEFITS
 * ============================================================================
 *
 * ✔ Loose Coupling
 *
 * ✔ Easy to Replace
 *
 * ✔ Easy to Test
 *
 * ✔ Easy to Maintain
 *
 * ✔ Easy to Extend
 *
 * ============================================================================
 * SOLID PRINCIPLES FOLLOWED
 * ============================================================================
 *
 * ✔ Single Responsibility Principle
 *
 *      OrderService only performs business logic.
 *
 * ✔ Open Closed Principle
 *
 *      Add new implementations
 *      without modifying OrderService.
 *
 * ✔ Dependency Inversion Principle
 *
 *      High-level module depends on abstraction,
 *      not concrete implementation.
 *
 * ============================================================================
 * CLASS RELATIONSHIP
 * ============================================================================
 *
 *                   PaymentService
 *                         ▲
 *                         │
 *          ┌──────────────┴──────────────┐
 *          │                             │
 *          ▼                             ▼
 *   RazorpayPayment              StripePayment
 *
 *                         ▲
 *                         │
 *                   OrderService
 *
 * OrderService depends on Interface.
 *
 * This is Loose Coupling.
 *
 * ============================================================================
 */

class Order1 {

}

/*
 * ------------------------------------------------------------------------
 * PAYMENT INTERFACE
 * ------------------------------------------------------------------------
 *
 * Instead of depending on a concrete class,
 * OrderService depends on this interface.
 *
 * Any payment implementation
 * can be injected.
 */

public interface PaymentService {

    void process(Order order);
}

/*
 * ------------------------------------------------------------------------
 * IMPLEMENTATION #1
 * ------------------------------------------------------------------------
 */

class RazorpayPayment1 implements PaymentService {

    @Override
    public void process(Order order) {

        System.out.println("Payment Successful using Razorpay");
    }
}

/*
 * ------------------------------------------------------------------------
 * IMPLEMENTATION #2
 * ------------------------------------------------------------------------
 */

class StripePayment implements PaymentService {

    @Override
    public void process(Order order) {

        System.out.println("Payment Successful using Stripe");
    }
}

/*
 * ------------------------------------------------------------------------
 * INVENTORY SERVICE
 * ------------------------------------------------------------------------
 */

class InventoryService1 {

    public void blockItems(Order order) {

        System.out.println("Inventory Blocked");
    }
}

/*
 * ------------------------------------------------------------------------
 * NOTIFICATION SERVICE
 * ------------------------------------------------------------------------
 */

class NotificationService2 {

    public void sendConfirmation(Order order) {

        System.out.println("Confirmation Email Sent");
    }
}

/**
 * ============================================================================
 * HIGH LEVEL MODULE
 * ============================================================================
 *
 * Notice carefully...
 *
 * There is NO "new RazorpayPayment()" here.
 *
 * Dependencies are received
 * through Constructor.
 *
 * This is Constructor Dependency Injection.
 */

class OrderService1 {

    private InventoryService inventory;

    private PaymentService payment;

    private NotificationService1 notification;

    /*
     * ------------------------------------------------------------------------
     * CONSTRUCTOR INJECTION
     * ------------------------------------------------------------------------
     *
     * Dependencies are injected
     * from outside.
     *
     * OrderService no longer creates objects.
     */

    public OrderService1(InventoryService inventory,
                        PaymentService payment,
                        NotificationService1 notification) {

        this.inventory = inventory;
        this.payment = payment;
        this.notification = notification;
    }

    public OrderService1() {

    }

    /*
     * ------------------------------------------------------------------------
     * BUSINESS METHOD
     * ------------------------------------------------------------------------
     */

    public void checkout(Order order) {

        inventory.blockItems(order);

        payment.process(order);

        notification.sendConfirmation(order);
    }
}

/**
 * ============================================================================
 * DRIVER CLASS
 * ============================================================================
 */

public class OrderServiceWithDependencyInjection {

    public static void main(String[] args) {

        /*
         * Creating dependencies.
         *
         * In Spring,
         * IoC Container creates these objects.
         *
         * Here,
         * we create them manually.
         */

        InventoryService inventory = new InventoryService();

        PaymentService payment = new RazorpayPayment1();

        NotificationService1 notification = new NotificationService1();

        /*
         * Dependencies injected
         * using Constructor.
         */

        OrderService orderService =
                new OrderService(inventory,
                        payment,
                        notification);

        Order order = new Order();

        orderService.checkout(order);
    }
}

/*
===============================================================================

OUTPUT

Inventory Blocked

Payment Successful using Razorpay

Confirmation Email Sent

===============================================================================

WHAT IF BUSINESS CHANGES?

Today

Payment = Razorpay

Tomorrow

Payment = Stripe

Old Code

PaymentService payment = new RazorpayPayment();

New Code

PaymentService payment = new StripePayment();

OrderService changed ❌ NO

Only injected dependency changed ✔

===============================================================================

ADVANTAGES

✔ Loose Coupling

OrderService depends on Interface.

------------------------------------------------

✔ Easy to Test

Inject MockPaymentService.

------------------------------------------------

✔ Easy to Extend

Create another PaymentService implementation.

------------------------------------------------

✔ Easy to Maintain

Business logic never changes.

------------------------------------------------

✔ Reusable

Works with any PaymentService.

===============================================================================

INTERVIEW QUESTIONS

Q) What is Dependency Injection?

Answer:

Dependency Injection is a Design Pattern
where dependencies are provided from outside
instead of being created inside the class.

-------------------------------------------------------------------------------

Q) What type of Dependency Injection is used here?

Answer:

Constructor Dependency Injection.

-------------------------------------------------------------------------------

Q) Why Constructor Injection is preferred?

Answer:

✔ Mandatory dependencies

✔ Object becomes immutable

✔ Easier Unit Testing

✔ Recommended by Spring

-------------------------------------------------------------------------------

Q) Who creates the objects in Spring?

Answer:

Spring IoC Container creates the objects
and injects them into dependent classes.

===============================================================================

FLOW

Application Starts

↓

Create InventoryService

↓

Create RazorpayPayment

↓

Create NotificationService

↓

Inject all dependencies

↓

Create OrderService

↓

checkout()

↓

Inventory Blocked

↓

Payment Processed

↓

Notification Sent

===============================================================================
*/