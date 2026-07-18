package behavioral;/*
===============================================================================

                        MEMENTO DESIGN PATTERN

Definition:
------------
Memento Pattern captures and stores an object's internal state so that it
can be restored later without exposing its internal implementation.

It is mainly used for Undo/Redo functionality.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose you're editing your Resume.

You keep making changes like:

✔ Add Skills
✔ Update Experience
✔ Change Education

Before every major change, you click Save Draft.

If something goes wrong, you simply Undo and restore the previous version.

Instead of exposing every field, a Memento object stores the snapshot.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                        ORIGINATOR

Creates Memento.

Restores Memento.

===============================================================================
*/

class ResumeEditor {

    private String name;

    private String education;

    private String experience;

    private List<String> skills;

    public void setName(String name) {

        this.name = name;
    }

    public void setEducation(String education) {

        this.education = education;
    }

    public void setExperience(String experience) {

        this.experience = experience;
    }

    public void setSkills(List<String> skills) {

        this.skills = skills;
    }

    public void printResume() {

        System.out.println("=========== Resume ===========");

        System.out.println("Name : " + name);

        System.out.println("Education : " + education);

        System.out.println("Experience : " + experience);

        System.out.println("Skills : " + skills);

        System.out.println("==============================");
    }

    /*
    ===============================================================

    Save Current State

    ===============================================================
    */

    public Memento save() {

        return new Memento(

                name,

                education,

                experience,

                List.copyOf(skills)
        );
    }

    /*
    ===============================================================

    Restore Previous State

    ===============================================================
    */

    public void restore(Memento memento) {

        this.name = memento.getName();

        this.education = memento.getEducation();

        this.experience = memento.getExperience();

        this.skills = memento.getSkills();
    }

    /*
    ===============================================================

    MEMENTO

    Stores Snapshot

    Immutable

    ===============================================================
    */

    public static class Memento {

        private final String name;

        private final String education;

        private final String experience;

        private final List<String> skills;

        private Memento(

                String name,

                String education,

                String experience,

                List<String> skills) {

            this.name = name;

            this.education = education;

            this.experience = experience;

            this.skills = skills;
        }

        private String getName() {

            return name;
        }

        private String getEducation() {

            return education;
        }

        private String getExperience() {

            return experience;
        }

        private List<String> getSkills() {

            return skills;
        }
    }
}

/*
===============================================================================

                        CARETAKER

Maintains History.

Does not modify Memento.

===============================================================================
*/

class ResumeHistory {

    private Stack<ResumeEditor.Memento> history =
            new Stack<>();

    /*
    ===============================================================

    Save State

    ===============================================================
    */

    public void save(ResumeEditor editor) {

        history.push(editor.save());
    }

    /*
    ===============================================================

    Undo

    ===============================================================
    */

    public void undo(ResumeEditor editor) {

        if (!history.isEmpty()) {

            editor.restore(history.pop());
        }
        else {

            System.out.println("No Previous State Available.");
        }
    }
}

/*
===============================================================================

                        CLIENT

Uses Resume Editor and Resume History.

===============================================================================
*/

public class MementoPatternDemo {

    public static void main(String[] args) {

        ResumeEditor editor =

                new ResumeEditor();

        ResumeHistory history =

                new ResumeHistory();

        /*
        ===============================================================

        Initial Resume

        ===============================================================
        */

        editor.setName("Alice");

        editor.setEducation("B.Tech CSE");

        editor.setExperience("Fresher");

        editor.setSkills(Arrays.asList(

                "Java",

                "DSA"
        ));

        history.save(editor);

        /*
        ===============================================================

        Update Resume

        ===============================================================
        */

        editor.setExperience(

                "SDE Intern at TUF+");

        editor.setSkills(Arrays.asList(

                "Java",

                "DSA",

                "LLD",

                "Spring Boot"
        ));

        history.save(editor);

        System.out.println("Current Resume");

        editor.printResume();

        System.out.println();

        /*
        ===============================================================

        Undo Once

        ===============================================================
        */

        history.undo(editor);

        System.out.println("After First Undo");

        editor.printResume();

        System.out.println();

        /*
        ===============================================================

        Undo Again

        ===============================================================
        */

        history.undo(editor);

        System.out.println("After Second Undo");

        editor.printResume();
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Resume

↓

Name

↓

Education

↓

Experience

↓

Skills

↓

Save Snapshot

------------------------------------------------------------

Step 2

Update Resume

↓

Experience

↓

Skills

↓

Save Snapshot

------------------------------------------------------------

Step 3

Undo

↓

Restore Previous Snapshot

------------------------------------------------------------

Step 4

Undo Again

↓

Restore Initial Snapshot

===============================================================================

Flow Diagram


               ResumeEditor
               (Originator)

                     |

              Save / Restore

                     |

                 Memento
          (Stores Snapshot)

                     |

               ResumeHistory
               (Caretaker)

===============================================================================

Execution Flow


Client

   |

ResumeEditor

   |

Save()

   |

ResumeHistory

   |

Undo()

   |

Restore()

===============================================================================

OUTPUT

Current Resume

=========== Resume ===========

Name : Alice

Education : B.Tech CSE

Experience : SDE Intern at TUF+

Skills : [Java, DSA, LLD, Spring Boot]

==============================

After First Undo

=========== Resume ===========

Name : Alice

Education : B.Tech CSE

Experience : SDE Intern at TUF+

Skills : [Java, DSA, LLD, Spring Boot]

==============================

After Second Undo

=========== Resume ===========

Name : Alice

Education : B.Tech CSE

Experience : Fresher

Skills : [Java, DSA]

==============================

===============================================================================

Advantages

✔ Supports Undo/Redo functionality.

✔ Encapsulates object state.

✔ Maintains data integrity.

✔ Follows Single Responsibility Principle.

✔ Easy snapshot management.

===============================================================================

Disadvantages

✘ High memory usage for many snapshots.

✘ Large objects create large mementos.

✘ Can become expensive if state is huge.

===============================================================================

When to Use

✔ Text Editors.

✔ Resume Builders.

✔ Drawing Applications.

✔ IDE Undo/Redo.

✔ Game Save Points.

✔ Database Transactions.

===============================================================================

Interview Questions

Q1. What is Memento Pattern?

Ans:

Memento Pattern stores an object's internal state so it can be restored
later without exposing its implementation.

------------------------------------------------

Q2. Which is the Originator?

Ans:

ResumeEditor.

------------------------------------------------

Q3. Which is the Memento?

Ans:

ResumeEditor.Memento.

------------------------------------------------

Q4. Which is the Caretaker?

Ans:

ResumeHistory.

------------------------------------------------

Q5. Why use Memento Pattern?

Ans:

To implement Undo/Redo while keeping object encapsulation intact.

------------------------------------------------

Q6. Which SOLID Principle is followed?

Ans:

Single Responsibility Principle.

The Originator manages state, while the Caretaker manages history.

------------------------------------------------

Q7. Which category does Memento belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Memento Pattern is a Behavioral Design Pattern that captures and restores
an object's previous state without exposing its internal details. It is
commonly used to implement Undo/Redo functionality.

In this example, ResumeEditor is the Originator, ResumeEditor.Memento is
the Memento, and ResumeHistory is the Caretaker. The Originator creates
snapshots of its state, the Caretaker stores them, and when Undo is
performed, the previous snapshot is restored. This keeps the design clean,
maintains encapsulation, and makes rollback operations simple.

===============================================================================
*/