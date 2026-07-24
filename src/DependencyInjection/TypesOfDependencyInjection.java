/**
 * ============================================================================
 * File Name : TypesOfDependencyInjection.java
 * Author    : Shaik Nayeem Basha
 * Topic     : Types of Dependency Injection
 * ============================================================================
 *
 * WHAT IS DEPENDENCY INJECTION?
 * -----------------------------
 *
 * Dependency Injection (DI) is a Design Pattern
 * in which the required objects (dependencies)
 * are provided from outside instead of creating
 * them inside the class.
 *
 * Instead of
 *
 *      new PaymentService();
 *
 * another class or Spring IoC Container
 * provides the dependency.
 *
 * This creates
 *
 *      Loose Coupling
 *
 * ============================================================================
 * TYPES OF DEPENDENCY INJECTION
 * ============================================================================
 *
 * 1. Constructor Injection  ⭐⭐⭐⭐⭐ (Recommended)
 *
 *      Dependencies are injected
 *      using constructor.
 *
 * ------------------------------------------------
 *
 * 2. Setter Injection ⭐⭐⭐
 *
 *      Dependencies are injected
 *      using setter methods.
 *
 * ------------------------------------------------
 *
 * 3. Field Injection ⭐⭐
 *
 *      Dependencies are injected
 *      directly into fields using
 *
 *          @Autowired
 *
 *      (Spring Framework)
 *
 * ============================================================================
 * REAL WORLD EXAMPLE
 * ============================================================================
 *
 * Imagine a Car.
 *
 * The Car needs an Engine.
 *
 * Without DI
 *
 * Car creates Engine itself.
 *
 *      Car
 *       │
 *       ▼
 *   new Engine()
 *
 * Tight Coupling
 *
 * ------------------------------------------------
 *
 * With DI
 *
 * Someone provides the Engine.
 *
 *      Engine
 *         │
 *         ▼
 *        Car
 *
 * Loose Coupling
 *
 * ============================================================================
 */

class Engine {

    public void start() {

        System.out.println("Engine Started...");
    }
}

/**
 * ============================================================================
 * TYPE 1
 * CONSTRUCTOR INJECTION
 * ============================================================================
 *
 * Dependencies are injected
 * through Constructor.
 *
 * This is the BEST approach.
 */

class ConstructorCar {

    private Engine engine;

    /*
     * Constructor receives dependency.
     */

    public ConstructorCar(Engine engine) {

        this.engine = engine;
    }

    public void drive() {

        System.out.println("Constructor Injection");

        engine.start();

        System.out.println("Car Running\n");
    }
}

/**
 * ============================================================================
 * TYPE 2
 * SETTER INJECTION
 * ============================================================================
 *
 * Object is created first.
 *
 * Dependency is provided later
 * using Setter Method.
 */

class SetterCar {

    private Engine engine;

    /*
     * Setter Injection
     */

    public void setEngine(Engine engine) {

        this.engine = engine;
    }

    public void drive() {

        System.out.println("Setter Injection");

        engine.start();

        System.out.println("Car Running\n");
    }
}

/**
 * ============================================================================
 * TYPE 3
 * FIELD INJECTION
 * ============================================================================
 *
 * Spring injects dependency
 * directly into field.
 *
 * Example:
 *
 *      @Autowired
 *      private Engine engine;
 *
 * NOTE:
 *
 * Here @Autowired is commented
 * because this is plain Java.
 *
 * It works only inside Spring.
 */

class FieldCar {

    // @Autowired
    private Engine engine;

    /*
     * Simulating Spring Injection
     * for plain Java example.
     */

    public FieldCar(Engine engine) {

        this.engine = engine;
    }

    public void drive() {

        System.out.println("Field Injection");

        engine.start();

        System.out.println("Car Running\n");
    }
}

/**
 * ============================================================================
 * DRIVER CLASS
 * ============================================================================
 */

public class TypesOfDependencyInjection {

    public static void main(String[] args) {

        /*
         * Shared Dependency
         */

        Engine engine = new Engine();

        /*
         * ==========================================================
         * Constructor Injection
         * ==========================================================
         */

        ConstructorCar constructorCar =
                new ConstructorCar(engine);

        constructorCar.drive();

        /*
         * ==========================================================
         * Setter Injection
         * ==========================================================
         */

        SetterCar setterCar = new SetterCar();

        setterCar.setEngine(engine);

        setterCar.drive();

        /*
         * ==========================================================
         * Field Injection
         * ==========================================================
         *
         * Spring injects automatically.
         *
         * Here we simulate it.
         */

        FieldCar fieldCar =
                new FieldCar(engine);

        fieldCar.drive();
    }
}

/*
===============================================================================

OUTPUT

Constructor Injection

Engine Started...

Car Running

--------------------------------

Setter Injection

Engine Started...

Car Running

--------------------------------

Field Injection

Engine Started...

Car Running

===============================================================================

COMPARISON

+----------------------+--------------------+-------------------------+
| Constructor          | Setter             | Field                   |
+----------------------+--------------------+-------------------------+
| Mandatory Dependency | Optional           | Hidden Dependency       |
| Immutable Object     | Mutable            | Mutable                 |
| Easy Testing         | Easy Testing       | Difficult Testing       |
| Recommended          | Sometimes Used     | Avoid in Production     |
+----------------------+--------------------+-------------------------+

===============================================================================

WHICH ONE SHOULD WE USE?

⭐⭐⭐⭐⭐ Constructor Injection

Reason

✔ Recommended by Spring Team

✔ Immutable Objects

✔ Easy Unit Testing

✔ Dependencies cannot be forgotten

✔ Better Design

------------------------------------------------

⭐⭐⭐ Setter Injection

Use when

Dependency is optional.

------------------------------------------------

⭐⭐ Field Injection

Use only for

Small demos or legacy code.

Avoid in production because

❌ Difficult Unit Testing

❌ Hidden Dependencies

❌ Breaks Immutability

===============================================================================

INTERVIEW QUESTIONS

Q) How many types of Dependency Injection are there?

Answer

1. Constructor Injection

2. Setter Injection

3. Field Injection

-------------------------------------------------------------------------------

Q) Which Dependency Injection is recommended?

Answer

Constructor Injection.

-------------------------------------------------------------------------------

Q) Why Constructor Injection?

Answer

✔ Mandatory dependencies

✔ Better design

✔ Immutable object

✔ Easier Unit Testing

✔ Official Spring recommendation

-------------------------------------------------------------------------------

Q) Does Java provide Dependency Injection?

Answer

No.

Dependency Injection is a Design Pattern.

Spring Framework provides Dependency Injection
through its IoC Container.

===============================================================================
*/