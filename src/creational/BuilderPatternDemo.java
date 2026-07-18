package creational;/*
===============================================================================

                    BUILDER DESIGN PATTERN

Definition:
------------
Builder Pattern is used to construct complex objects step by step.
It separates object construction from its representation.

Instead of using a constructor with many parameters,

    new BurgerMeal(..., ..., ..., ..., ..., ...)

we use a Builder to create the object in a readable way.

Real World Examples:
--------------------
1. Burger Customization
2. House Construction
3. Laptop Configuration
4. User Registration Form
5. StringBuilder in Java

===============================================================================
*/

import java.util.*;

/*
===============================================================================

Product Class

Represents the final Burger Meal object.

Contains:
1. Required Fields
2. Optional Fields

Object creation is allowed ONLY through the Builder.

===============================================================================
*/

class BurgerMeal {

    /*
    ===========================================================================
    Required Fields

    Every burger must have:
    1. Bun Type
    2. Patty

    ===========================================================================
    */

    private final String bunType;
    private final String patty;

    /*
    ===========================================================================
    Optional Fields

    User may or may not choose them.

    ===========================================================================
    */

    private final boolean hasCheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;

    /*
    ===========================================================================
    Private Constructor

    Prevents direct object creation.

    This is NOT allowed:

        new BurgerMeal();

    Builder is responsible for creating objects.

    ===========================================================================
    */

    private BurgerMeal(BurgerBuilder builder) {

        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }

    /*
    ===========================================================================
    Display Method

    Prints the burger configuration.

    ===========================================================================
    */

    public void displayMeal() {

        System.out.println("----------------------------------------");

        System.out.println("Bun Type  : " + bunType);
        System.out.println("Patty     : " + patty);
        System.out.println("Cheese    : " + hasCheese);
        System.out.println("Toppings  : " + toppings);
        System.out.println("Side      : " + side);
        System.out.println("Drink     : " + drink);

        System.out.println("----------------------------------------");
        System.out.println();
    }

    /*
    ===========================================================================

                        STATIC NESTED BUILDER CLASS

    Responsible for constructing BurgerMeal object step by step.

    ===========================================================================
    */

    public static class BurgerBuilder {

        /*
        ===============================================================

        Required Fields

        ===============================================================
        */

        private final String bunType;
        private final String patty;

        /*
        ===============================================================

        Optional Fields

        ===============================================================
        */

        private boolean hasCheese;

        private List<String> toppings = new ArrayList<>();

        private String side;

        private String drink;

        /*
        ===============================================================

        Builder Constructor

        Requires mandatory fields.

        ===============================================================
        */

        public BurgerBuilder(String bunType, String patty) {

            this.bunType = bunType;
            this.patty = patty;
        }

        /*
        ===============================================================

        Builder Methods

        Every method returns Builder object itself.

        This enables Method Chaining.

        Example:

        builder
            .withCheese(true)
            .withDrink("Coke")
            .build();

        ===============================================================
        */

        public BurgerBuilder withCheese(boolean hasCheese) {

            this.hasCheese = hasCheese;

            return this;
        }

        public BurgerBuilder withToppings(List<String> toppings) {

            this.toppings = toppings;

            return this;
        }

        public BurgerBuilder withSide(String side) {

            this.side = side;

            return this;
        }

        public BurgerBuilder withDrink(String drink) {

            this.drink = drink;

            return this;
        }

        /*
        ===============================================================

        Final Build Method

        Creates and returns BurgerMeal object.

        ===============================================================
        */

        public BurgerMeal build() {

            return new BurgerMeal(this);
        }
    }
}


/*
===============================================================================

Driver Class

===============================================================================
*/

public class BuilderPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Burger 1

        Only Required Fields

        ===============================================================
        */

        BurgerMeal plainBurger =
                new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                        .build();

        plainBurger.displayMeal();


        /*
        ===============================================================

        Burger 2

        Required + Cheese

        ===============================================================
        */

        BurgerMeal cheeseBurger =
                new BurgerMeal.BurgerBuilder("Wheat", "Veg")
                        .withCheese(true)
                        .build();

        cheeseBurger.displayMeal();


        /*
        ===============================================================

        Burger 3

        Fully Customized Burger

        ===============================================================
        */

        List<String> toppings =
                Arrays.asList("Lettuce", "Onion", "Jalapeno");

        BurgerMeal loadedBurger =
                new BurgerMeal.BurgerBuilder("Multigrain", "Chicken")
                        .withCheese(true)
                        .withToppings(toppings)
                        .withSide("Fries")
                        .withDrink("Coke")
                        .build();

        loadedBurger.displayMeal();
    }
}


/*
===============================================================================

Dry Run

Burger 1

Builder

↓

bunType = Wheat
patty = Veg

↓

build()

↓

BurgerMeal Object Created


----------------------------------------------------


Burger 2

Builder

↓

bunType = Wheat
patty = Veg

↓

withCheese(true)

↓

build()

↓

BurgerMeal Object Created


----------------------------------------------------


Burger 3

Builder

↓

bunType = Multigrain

↓

patty = Chicken

↓

withCheese(true)

↓

withToppings(...)

↓

withSide("Fries")

↓

withDrink("Coke")

↓

build()

↓

BurgerMeal Object Created


===============================================================================

Flow Diagram


                Client

                   |

                   |

        BurgerBuilder

           |

           |

--------------------------------------

withCheese()

withToppings()

withSide()

withDrink()

--------------------------------------

           |

           |

         build()

           |

           |

      BurgerMeal Object


===============================================================================

OUTPUT

----------------------------------------
Bun Type  : Wheat
Patty     : Veg
Cheese    : false
Toppings  : []
Side      : null
Drink     : null
----------------------------------------

----------------------------------------
Bun Type  : Wheat
Patty     : Veg
Cheese    : true
Toppings  : []
Side      : null
Drink     : null
----------------------------------------

----------------------------------------
Bun Type  : Multigrain
Patty     : Chicken
Cheese    : true
Toppings  : [Lettuce, Onion, Jalapeno]
Side      : Fries
Drink     : Coke
----------------------------------------

===============================================================================

Advantages

✔ Handles objects with many optional parameters.

✔ Improves readability.

✔ Supports Method Chaining.

✔ Object becomes immutable after creation.

✔ Avoids Constructor Explosion.

===============================================================================

Disadvantages

✘ More code.

✘ Builder class needs to be maintained.

===============================================================================

Interview Questions

Q1. What is Builder Pattern?

Ans:
Builder Pattern constructs complex objects step by step and separates
construction from representation.

------------------------------------------------

Q2. Why use Builder Pattern?

Ans:
To avoid constructors with many parameters and improve readability.

------------------------------------------------

Q3. Why does every builder method return Builder?

Ans:
To support Method Chaining.

------------------------------------------------

Q4. Why is BurgerMeal constructor private?

Ans:
To force object creation only through Builder.

------------------------------------------------

Q5. Which design pattern category does it belong to?

Ans:
Creational Design Pattern.

===============================================================================

Interview Summary (30 Seconds)

Builder Pattern is a Creational Design Pattern used to create complex
objects step by step. It separates object construction from the final
representation and supports method chaining for better readability.
Instead of using constructors with many parameters, the Builder allows
optional fields to be added fluently before calling build() to create
the final immutable object.

===============================================================================
*/