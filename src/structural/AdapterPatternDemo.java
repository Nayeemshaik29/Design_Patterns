package structural;/*
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

        System.out.println("Charging structural.Mobile using Old USB Charger.");
    }
}


/*
===============================================================================

                    ADAPTER

Implements structural.MobileCharger interface.

Internally uses structural.OldUSBCharger.

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

Already follows structural.MobileCharger interface.

No adapter required.

===============================================================================
*/

class TypeCCharger implements MobileCharger {

    @Override
    public void charge() {

        System.out.println("Charging structural.Mobile using Type-C Charger.");
    }
}


/*
===============================================================================

                    CLIENT

structural.Mobile only understands

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

        structural.Mobile uses Old Charger through Adapter

        structural.Mobile

            |

        structural.MobileCharger

            |

        structural.ChargerAdapter

            |

        structural.OldUSBCharger

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

        structural.Mobile uses Type-C Charger directly

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

structural.Mobile

    |

structural.MobileCharger

    |

structural.ChargerAdapter

    |

structural.OldUSBCharger

    |

powerSupply()

Output

Charging structural.Mobile using Old USB Charger.


------------------------------------------------------


Case 2

structural.Mobile

    |

structural.MobileCharger

    |

structural.TypeCCharger

    |

charge()

Output

Charging structural.Mobile using Type-C Charger.


===============================================================================

Flow Diagram


                structural.Mobile

                   |

             structural.MobileCharger

             /           \

            /             \

    structural.TypeCCharger     structural.ChargerAdapter

                           |

                           |

                    structural.OldUSBCharger

                           |

                     powerSupply()


===============================================================================

OUTPUT

Charging structural.Mobile using Old USB Charger.

--------------------------------

Charging structural.Mobile using Type-C Charger.

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
structural.MobileCharger.

------------------------------------------------

Q3. What is the Adaptee?

Ans:
structural.OldUSBCharger.

------------------------------------------------

Q4. What is the Adapter?

Ans:
structural.ChargerAdapter.

------------------------------------------------

Q5. Which category does it belong to?

Ans:
Structural Design Pattern.

===============================================================================

Interview Summary (30 Seconds)

Adapter Pattern is a Structural Design Pattern that acts as a bridge
between two incompatible interfaces. In this example, the structural.Mobile
expects a structural.MobileCharger interface, but the structural.OldUSBCharger provides
powerSupply() instead of charge(). structural.ChargerAdapter converts the call,
allowing the old charger to work seamlessly with the new mobile
without changing existing code.

===============================================================================
*/