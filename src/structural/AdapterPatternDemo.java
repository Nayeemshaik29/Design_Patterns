/*
===============================================================================

                    ADAPTER DESIGN PATTERN

Definition:
------------
Adapter Pattern allows two incompatible interfaces to work together.

Real World Example:
--------------------
Suppose you have:

Old Charger  ---> USB-A Port

But your new mobile has

USB-C Port

You cannot connect them directly.

An Adapter converts USB-A to USB-C, allowing both devices to work together.

===============================================================================
*/


/*
===============================================================================

                    TARGET INTERFACE

This is the interface expected by the mobile.

Every charger should provide:

charge()

===============================================================================
*/

interface MobileCharger {

    void charge();
}


/*
===============================================================================

                    ADAPTEE

Old Charger

It provides a different method:

powerSupply()

instead of charge()

===============================================================================
*/

class OldUSBCharger {

    public void powerSupply() {

        System.out.println("Charging Mobile using Old USB Charger.");
    }
}


/*
===============================================================================

                    ADAPTER

Implements MobileCharger interface.

Internally uses OldUSBCharger.

Converts

charge()

into

powerSupply()

===============================================================================
*/

class ChargerAdapter implements MobileCharger {

    private OldUSBCharger oldUSBCharger;

    public ChargerAdapter() {

        oldUSBCharger = new OldUSBCharger();
    }

    @Override
    public void charge() {

        oldUSBCharger.powerSupply();
    }
}


/*
===============================================================================

                    MODERN CHARGER

Already follows MobileCharger interface.

No adapter required.

===============================================================================
*/

class TypeCCharger implements MobileCharger {

    @Override
    public void charge() {

        System.out.println("Charging Mobile using Type-C Charger.");
    }
}


/*
===============================================================================

                    CLIENT

Mobile only understands

charge()

It doesn't know whether charger is old or new.

===============================================================================
*/

class Mobile {

    private MobileCharger charger;

    public Mobile(MobileCharger charger) {

        this.charger = charger;
    }

    public void startCharging() {

        charger.charge();
    }
}


/*
===============================================================================

                    DRIVER CLASS

===============================================================================
*/

public class AdapterPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Mobile uses Old Charger through Adapter

        Mobile

            |

        MobileCharger

            |

        ChargerAdapter

            |

        OldUSBCharger

        ===============================================================
        */

        Mobile mobile1 =

                new Mobile(new ChargerAdapter());

        mobile1.startCharging();


        System.out.println();

        System.out.println("--------------------------------");

        System.out.println();


        /*
        ===============================================================

        Mobile uses Type-C Charger directly

        No Adapter Required

        ===============================================================
        */

        Mobile mobile2 =

                new Mobile(new TypeCCharger());

        mobile2.startCharging();
    }
}


/*
===============================================================================

Dry Run

Case 1

Mobile

    |

MobileCharger

    |

ChargerAdapter

    |

OldUSBCharger

    |

powerSupply()

Output

Charging Mobile using Old USB Charger.


------------------------------------------------------


Case 2

Mobile

    |

MobileCharger

    |

TypeCCharger

    |

charge()

Output

Charging Mobile using Type-C Charger.


===============================================================================

Flow Diagram


                Mobile

                   |

             MobileCharger

             /           \

            /             \

    TypeCCharger     ChargerAdapter

                           |

                           |

                    OldUSBCharger

                           |

                     powerSupply()


===============================================================================

OUTPUT

Charging Mobile using Old USB Charger.

--------------------------------

Charging Mobile using Type-C Charger.

===============================================================================

Advantages

✔ Connects incompatible interfaces.

✔ Reuses existing code.

✔ Loose coupling.

✔ No modification to existing classes.

===============================================================================

Interview Questions

Q1. What is Adapter Pattern?

Ans:
Adapter Pattern allows incompatible interfaces to work together.

------------------------------------------------

Q2. What is the Target Interface?

Ans:
MobileCharger.

------------------------------------------------

Q3. What is the Adaptee?

Ans:
OldUSBCharger.

------------------------------------------------

Q4. What is the Adapter?

Ans:
ChargerAdapter.

------------------------------------------------

Q5. Which category does it belong to?

Ans:
Structural Design Pattern.

===============================================================================

Interview Summary (30 Seconds)

Adapter Pattern is a Structural Design Pattern that acts as a bridge
between two incompatible interfaces. In this example, the Mobile
expects a MobileCharger interface, but the OldUSBCharger provides
powerSupply() instead of charge(). ChargerAdapter converts the call,
allowing the old charger to work seamlessly with the new mobile
without changing existing code.

===============================================================================
*/