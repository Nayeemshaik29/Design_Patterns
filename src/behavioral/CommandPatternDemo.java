/*
===============================================================================

                        COMMAND DESIGN PATTERN

Definition:
------------
Command Pattern encapsulates a request as an object, thereby allowing us to
parameterize clients with different requests, queue requests, log requests,
and support undo/redo functionality.

Instead of calling methods directly, the client sends a Command object to
the Invoker, which then executes the request on the Receiver.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose you have a Smart Home Remote.

The remote has buttons like:

✔ Light ON
✔ Light OFF
✔ AC ON
✔ AC OFF

The remote doesn't know how to switch ON/OFF the devices.
It simply executes the assigned Command object.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                        RECEIVER

Light knows how to perform actual operations.

===============================================================================
*/

class Light {

    public void on() {

        System.out.println("Light turned ON");
    }

    public void off() {

        System.out.println("Light turned OFF");
    }
}

/*
===============================================================================

                        RECEIVER

AC knows how to perform actual operations.

===============================================================================
*/

class AC {

    public void on() {

        System.out.println("AC turned ON");
    }

    public void off() {

        System.out.println("AC turned OFF");
    }
}

/*
===============================================================================

                    COMMAND INTERFACE

Every command should implement:

execute()

undo()

===============================================================================
*/

interface Command {

    void execute();

    void undo();
}

/*
===============================================================================

                CONCRETE COMMAND

Light ON

===============================================================================
*/

class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {

        this.light = light;
    }

    @Override
    public void execute() {

        light.on();
    }

    @Override
    public void undo() {

        light.off();
    }
}

/*
===============================================================================

                CONCRETE COMMAND

Light OFF

===============================================================================
*/

class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {

        this.light = light;
    }

    @Override
    public void execute() {

        light.off();
    }

    @Override
    public void undo() {

        light.on();
    }
}

/*
===============================================================================

                CONCRETE COMMAND

AC ON

===============================================================================
*/

class ACOnCommand implements Command {

    private AC ac;

    public ACOnCommand(AC ac) {

        this.ac = ac;
    }

    @Override
    public void execute() {

        ac.on();
    }

    @Override
    public void undo() {

        ac.off();
    }
}

/*
===============================================================================

                CONCRETE COMMAND

AC OFF

===============================================================================
*/

class ACOffCommand implements Command {

    private AC ac;

    public ACOffCommand(AC ac) {

        this.ac = ac;
    }

    @Override
    public void execute() {

        ac.off();
    }

    @Override
    public void undo() {

        ac.on();
    }
}

/*
===============================================================================

                        INVOKER

RemoteControl stores commands and executes them.

Also supports Undo.

===============================================================================
*/

class RemoteControl {

    private Command[] buttons = new Command[4];

    private Stack<Command> commandHistory =
            new Stack<>();

    public void setCommand(int slot,
                           Command command) {

        buttons[slot] = command;
    }

    public void pressButton(int slot) {

        if (buttons[slot] != null) {

            buttons[slot].execute();

            commandHistory.push(buttons[slot]);
        }
        else {

            System.out.println(
                    "No command assigned to Slot "
                            + slot);
        }
    }

    public void pressUndo() {

        if (!commandHistory.isEmpty()) {

            commandHistory.pop().undo();
        }
        else {

            System.out.println(
                    "No commands to Undo.");
        }
    }
}

/*
===============================================================================

                        CLIENT

Creates Receivers, Commands and assigns them to the Invoker.

===============================================================================
*/

public class CommandPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Receivers

        ===============================================================
        */

        Light light = new Light();

        AC ac = new AC();

        /*
        ===============================================================

        Commands

        ===============================================================
        */

        Command lightOn =
                new LightOnCommand(light);

        Command lightOff =
                new LightOffCommand(light);

        Command acOn =
                new ACOnCommand(ac);

        Command acOff =
                new ACOffCommand(ac);

        /*
        ===============================================================

        Invoker

        ===============================================================
        */

        RemoteControl remote =
                new RemoteControl();

        remote.setCommand(0, lightOn);

        remote.setCommand(1, lightOff);

        remote.setCommand(2, acOn);

        remote.setCommand(3, acOff);

        /*
        ===============================================================

        Execute Commands

        ===============================================================
        */

        remote.pressButton(0);

        remote.pressButton(2);

        remote.pressButton(1);

        System.out.println();

        System.out.println("--------------------------------");

        System.out.println();

        /*
        ===============================================================

        Undo Commands

        ===============================================================
        */

        remote.pressUndo();

        remote.pressUndo();
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Receivers

↓

Light

↓

AC

------------------------------------------------------------

Step 2

Create Commands

↓

LightOnCommand

↓

LightOffCommand

↓

ACOnCommand

↓

ACOffCommand

------------------------------------------------------------

Step 3

Assign Commands

↓

RemoteControl

------------------------------------------------------------

Step 4

Press Button 0

↓

Light ON

------------------------------------------------------------

Step 5

Press Button 2

↓

AC ON

------------------------------------------------------------

Step 6

Press Button 1

↓

Light OFF

------------------------------------------------------------

Step 7

Undo

↓

Light ON

------------------------------------------------------------

Step 8

Undo

↓

AC OFF

===============================================================================

Flow Diagram


                  Client

                     |

             RemoteControl
               (Invoker)

                     |

                 Command
          (Command Interface)

        /       |       |       \

       /        |       |        \

 LightOn  LightOff  ACOn  ACOff

      \       |       |       /

       \      |       |      /

           Light      AC

          (Receivers)

===============================================================================

Execution Flow


Client

   |

RemoteControl

   |

Command

   |

Receiver

   |

Execute Action

===============================================================================

OUTPUT

Light turned ON

AC turned ON

Light turned OFF

--------------------------------

Light turned ON

AC turned OFF

===============================================================================

Advantages

✔ Loose coupling between sender and receiver.

✔ Supports Undo/Redo.

✔ Easy to add new commands.

✔ Supports logging and command history.

✔ Follows Open/Closed Principle.

===============================================================================

Disadvantages

✘ Increases the number of classes.

✘ Slightly more complex design.

===============================================================================

When to Use

✔ Remote Controls.

✔ GUI Buttons.

✔ Menu Systems.

✔ Task Scheduling.

✔ Undo/Redo Operations.

✔ Macro Recording.

===============================================================================

Interview Questions

Q1. What is Command Pattern?

Ans:

Command Pattern encapsulates a request as an object so that requests can
be executed, queued, logged, or undone.

------------------------------------------------

Q2. Which is the Command?

Ans:

Command Interface.

------------------------------------------------

Q3. Which are the Concrete Commands?

Ans:

LightOnCommand

LightOffCommand

ACOnCommand

ACOffCommand

------------------------------------------------

Q4. Which is the Invoker?

Ans:

RemoteControl.

------------------------------------------------

Q5. Which are the Receivers?

Ans:

Light

AC

------------------------------------------------

Q6. Why use Command Pattern?

Ans:

To decouple the sender from the receiver and support undo/redo operations.

------------------------------------------------

Q7. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

------------------------------------------------

Q8. Which category does Command belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Command Pattern is a Behavioral Design Pattern that encapsulates a request
into a Command object. This decouples the Invoker from the Receiver and
supports features like undo, redo, logging, and request queuing.

In this example, Command is the Command interface, LightOnCommand,
LightOffCommand, ACOnCommand, and ACOffCommand are the Concrete Commands,
Light and AC are the Receivers, RemoteControl is the Invoker, and the
Client creates and wires everything together. When a button is pressed,
the RemoteControl executes the assigned command, making the design flexible,
extensible, and compliant with the Open/Closed Principle.

===============================================================================
*/