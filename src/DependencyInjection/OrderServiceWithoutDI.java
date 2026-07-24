package DependencyInjection;

/**
 * ============================================================================
 * File Name : OrderServiceWithoutDI.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Problem without Dependency Injection (Tight Coupling)
 * ============================================================================
 *
 * PROBLEM STATEMENT
 * -----------------
 *
 * We have an OrderService class responsible for:
 *
 *      1. Blocking Inventory
 *      2. Processing Payment
 *      3. Sending Notification
 *
 * At first glance, the code looks fine.
 *
 * But there is a BIG design problem.
 *
 * OrderService creates its own dependencies.
 *
 * This is called
 *
 *          TIGHT COUPLING
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Imagine buying a new TV.
 *
 * The TV comes with ONLY one fixed remote.
 *
 * You cannot use:
 *
 *      Samsung Remote
 *      LG Remote
 *      Sony Remote
 *
 * because the TV itself created the remote.
 *
 * If you want another remote,
 * you must open the TV and replace its hardware.
 *
 * That's exactly what happens here.
 *
 * OrderService creates every object itself.
 *
 * ============================================================================
 * PROBLEM
 * ============================================================================
 *
 * OrderService directly creates:
 *
 *      InventoryService
 *      RazorpayPayment
 *      NotificationService
 *
 * OrderService is now responsible for:
 *
 * ✔ Business Logic
 * ✔ Object Creation
 *
 * This violates the
 *
 * Single Responsibility Principle (SRP)
 *
 * ============================================================================
 * WHY IS THIS BAD?
 * ============================================================================
 *
 * Because OrderService now depends on
 * concrete classes instead of abstractions.
 *
 * Example:
 *
 * Today:
 *
 *      RazorpayPayment
 *
 * Tomorrow:
 *
 *      StripePayment
 *
 * You'll have to modify OrderService.
 *
 * Every new payment gateway
 * requires changing existing code.
 *
 * This violates:
 *
 *      Open Closed Principle (OCP)
 *
 * ============================================================================
 * SOLID PRINCIPLE VIOLATED
 * ============================================================================
 *
 * ❌ Single Responsibility Principle
 *
 *      OrderService performs business logic
 *      AND creates objects.
 *
 * ❌ Open Closed Principle
 *
 *      Every new implementation
 *      requires modifying OrderService.
 *
 * ❌ Dependency Inversion Principle
 *
 *      High-level module depends on
 *      low-level concrete classes.
 *
 * ============================================================================
 * CLASS RELATIONSHIP
 * ============================================================================
 *
 *            OrderService
 *                 │
 *      ┌──────────┼──────────┐
 *      ▼          ▼          ▼
 * Inventory   Razorpay   Notification
 *  Service     Payment      Service
 *
 * OrderService creates everything itself.
 *
 * This is Tight Coupling.
 *
 * ============================================================================
 */

class InventoryService {

    public void blockItems(Order order) {
        System.out.println("Inventory Blocked");
    }
}

class RazorpayPayment {

    public void process(Order order) {
        System.out.println("Payment Successful using Razorpay");
    }
}

class NotificationService1 {

    public void sendConfirmation(Order order) {
        System.out.println("Confirmation Email Sent");
    }
}

class Order {
}

/**
 * ============================================================================
 * HIGH LEVEL MODULE
 * ============================================================================
 *
 * This class contains business logic.
 *
 * But notice carefully...
 *
 * It is also creating all dependency objects.
 *
 * That is the actual problem.
 */

class OrderService {

    /*
     * ------------------------------------------------------------------------
     * Dependency #1
     * ------------------------------------------------------------------------
     *
     * OrderService directly creates InventoryService.
     *
     * Problem:
     *
     * We cannot replace InventoryService easily.
     *
     * If tomorrow we have
     *
     *      CloudInventoryService
     *
     * we must modify this class.
     */

    private InventoryService inventory = new InventoryService();

    /*
     * ------------------------------------------------------------------------
     * Dependency #2
     * ------------------------------------------------------------------------
     *
     * Payment gateway is fixed.
     *
     * Today:
     *
     *      Razorpay
     *
     * Tomorrow:
     *
     *      Stripe
     *      PayPal
     *      PhonePe
     *
     * Every change requires editing OrderService.
     */

    private RazorpayPayment payment = new RazorpayPayment();

    /*
     * ------------------------------------------------------------------------
     * Dependency #3
     * ------------------------------------------------------------------------
     *
     * Notification service is also fixed.
     *
     * Tomorrow we may want:
     *
     * SMS
     * WhatsApp
     * Push Notification
     *
     * Again,
     * OrderService must change.
     */

    private NotificationService1 notification = new NotificationService1();

    public OrderService(InventoryService inventory, PaymentService payment, NotificationService1 notification) {
    }


    /*
     * ------------------------------------------------------------------------
     * Business Method
     * ------------------------------------------------------------------------
     *
     * Checkout Process
     *
     * 1. Block Inventory
     * 2. Process Payment
     * 3. Send Confirmation
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

public class OrderServiceWithoutDI {

    public static void main(String[] args) {

        OrderService1 orderService = new OrderService1();

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

private RazorpayPayment payment = new RazorpayPayment();

New Code

private StripePayment payment = new StripePayment();

OrderService changed ❌

This violates Open Closed Principle.

===============================================================================

BIGGEST PROBLEMS

❌ Tight Coupling

OrderService directly creates objects.

------------------------------------------------

❌ Difficult to Test

How will you inject a Mock PaymentService?

Impossible.

------------------------------------------------

❌ Difficult to Extend

Adding new Payment Gateway
requires changing OrderService.

------------------------------------------------

❌ Difficult to Maintain

Every dependency change
requires editing OrderService.

------------------------------------------------

❌ Not Reusable

Cannot reuse OrderService
with another Payment implementation.

===============================================================================

INTERVIEW QUESTION

Q) What is Tight Coupling?

Answer:

When one class directly creates or depends on
another concrete class, making it difficult
to replace, extend, test, or maintain.

===============================================================================

NEXT STEP

To solve all these problems,

Spring introduced

        Dependency Injection (DI)

where dependencies are supplied from outside
instead of being created inside the class.

That converts

        Tight Coupling

into

        Loose Coupling.

===============================================================================
*/