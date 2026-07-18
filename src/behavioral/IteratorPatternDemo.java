/*
===============================================================================

                        ITERATOR DESIGN PATTERN

Definition:
------------
Iterator Pattern provides a way to access elements of a collection
sequentially without exposing its internal representation.

Instead of directly accessing the collection, the client uses an
Iterator object to traverse the elements one by one.

Category:
----------
Behavioral Design Pattern

Real World Example:
-------------------
Suppose you have a YouTube Playlist.

The playlist contains multiple videos.

Instead of accessing the internal list directly, YouTube provides
Next and Previous buttons to navigate through videos.

The Iterator acts like the "Next" button, allowing users to traverse
the playlist without knowing how videos are stored internally.

===============================================================================
*/

import java.util.*;

/*
===============================================================================

                        ELEMENT

Represents a single video.

===============================================================================
*/

class Video {

    private String title;

    public Video(String title) {

        this.title = title;
    }

    public String getTitle() {

        return title;
    }
}

/*
===============================================================================

                        AGGREGATE

Represents the collection of videos.

Provides methods to add videos and create an iterator.

===============================================================================
*/

class YouTubePlaylist {

    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {

        videos.add(video);
    }

    public PlaylistIterator createIterator() {

        return new YouTubePlaylistIterator(videos);
    }
}

/*
===============================================================================

                        ITERATOR

Defines traversal methods.

===============================================================================
*/

interface PlaylistIterator {

    boolean hasNext();

    Video next();
}

/*
===============================================================================

                CONCRETE ITERATOR

Traverses the playlist sequentially.

===============================================================================
*/

class YouTubePlaylistIterator implements PlaylistIterator {

    private List<Video> videos;

    private int position = 0;

    public YouTubePlaylistIterator(List<Video> videos) {

        this.videos = videos;
    }

    @Override
    public boolean hasNext() {

        return position < videos.size();
    }

    @Override
    public Video next() {

        if (hasNext()) {

            return videos.get(position++);
        }

        return null;
    }
}

/*
===============================================================================

                        CLIENT

Client only uses the iterator.

It never accesses the internal list directly.

===============================================================================
*/

public class IteratorPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Create Playlist

        ===============================================================
        */

        YouTubePlaylist playlist = new YouTubePlaylist();

        playlist.addVideo(new Video("LLD Tutorial"));

        playlist.addVideo(new Video("System Design Basics"));

        playlist.addVideo(new Video("Java Design Patterns"));

        /*
        ===============================================================

        Create Iterator

        ===============================================================
        */

        PlaylistIterator iterator = playlist.createIterator();

        /*
        ===============================================================

        Traverse Playlist

        ===============================================================
        */

        System.out.println("YouTube Playlist:\n");

        while (iterator.hasNext()) {

            System.out.println(iterator.next().getTitle());
        }
    }
}

/*
===============================================================================

Dry Run

Step 1

Create Playlist

↓

Add

LLD Tutorial

↓

Add

System Design Basics

↓

Add

Java Design Patterns

------------------------------------------------------------

Step 2

Client requests

createIterator()

↓

YouTubePlaylistIterator created

------------------------------------------------------------

Step 3

Iterator starts

position = 0

------------------------------------------------------------

hasNext()

↓

true

↓

next()

↓

LLD Tutorial

------------------------------------------------------------

hasNext()

↓

true

↓

next()

↓

System Design Basics

------------------------------------------------------------

hasNext()

↓

true

↓

next()

↓

Java Design Patterns

------------------------------------------------------------

hasNext()

↓

false

↓

Stop Iteration

===============================================================================

Flow Diagram


                     Client

                        |

                        |

                YouTubePlaylist
                  (Aggregate)

                        |

                createIterator()

                        |

                PlaylistIterator
                   (Iterator)

                        |

          YouTubePlaylistIterator
              (Concrete Iterator)

                        |

                     Video List

===============================================================================

Execution Flow


Client

   |

YouTubePlaylist

   |

createIterator()

   |

YouTubePlaylistIterator

   |

hasNext()

   |

next()

   |

Video

===============================================================================

OUTPUT

YouTube Playlist:

LLD Tutorial

System Design Basics

Java Design Patterns

===============================================================================

Advantages

✔ Hides internal implementation.

✔ Supports sequential traversal.

✔ Multiple iterators can exist simultaneously.

✔ Simplifies client code.

✔ Follows Single Responsibility Principle.

===============================================================================

Disadvantages

✘ Adds extra classes.

✘ Slight overhead for small collections.

===============================================================================

When to Use

✔ Collections

✔ Playlists

✔ Trees

✔ Graphs

✔ File Systems

✔ Database Result Sets

===============================================================================

Interview Questions

Q1. What is Iterator Pattern?

Ans:

Iterator Pattern provides a way to traverse a collection without exposing
its internal representation.

------------------------------------------------

Q2. Which is the Aggregate?

Ans:

YouTubePlaylist

------------------------------------------------

Q3. Which is the Iterator?

Ans:

PlaylistIterator

------------------------------------------------

Q4. Which is the Concrete Iterator?

Ans:

YouTubePlaylistIterator

------------------------------------------------

Q5. Why use Iterator Pattern?

Ans:

To traverse collections without exposing internal implementation.

------------------------------------------------

Q6. Which SOLID Principle is followed?

Ans:

Single Responsibility Principle.

The collection manages data.

The iterator manages traversal.

------------------------------------------------

Q7. Which category does Iterator belong to?

Ans:

Behavioral Design Pattern.

===============================================================================

30-Second Interview Summary

Iterator Pattern is a Behavioral Design Pattern that provides a standard
way to traverse a collection without exposing its internal structure.

In this example, YouTubePlaylist is the Aggregate, PlaylistIterator is
the Iterator interface, and YouTubePlaylistIterator is the Concrete
Iterator. The client simply requests an iterator and traverses the
playlist using hasNext() and next(), making the code clean, flexible,
and independent of the collection's internal implementation.

===============================================================================
*/