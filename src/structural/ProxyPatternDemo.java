package structural;/*
===============================================================================

                        PROXY DESIGN PATTERN

Definition:
------------
Proxy Pattern provides a placeholder or surrogate object that controls access
to another object.

Instead of directly accessing the real object, the client communicates with
the Proxy, which can add extra functionality like:

✔ Caching
✔ Security
✔ Logging
✔ Lazy Loading
✔ Access Control

Category:
----------
Structural Design Pattern

Real World Example:
-------------------
Suppose you watch a YouTube video.

The first time you play the video:

✔ The video is downloaded from the server.

The second time:

✔ Instead of downloading again,
✔ The cached version is used.

The Proxy handles caching automatically without the user knowing.

===============================================================================
*/

/*
===============================================================================

                        SUBJECT

Every video downloader should provide:

downloadVideo()

===============================================================================
*/

import java.util.HashMap;
import java.util.Map;

interface VideoDownloader {

    String downloadVideo(String videoURL);
}

/*
===============================================================================

                    REAL SUBJECT

Actually downloads the video.

===============================================================================
*/

class RealVideoDownloader implements VideoDownloader {

    @Override
    public String downloadVideo(String videoURL) {

        System.out.println("Downloading video from URL : " + videoURL);

        return "Video Content from " + videoURL;
    }
}

/*
===============================================================================

                        PROXY

Acts as an intermediary between Client and RealVideoDownloader.

Adds:

✔ Cache
✔ Avoids duplicate downloads

===============================================================================
*/

class CachedVideoDownloader implements VideoDownloader {

    private RealVideoDownloader realDownloader;

    private Map<String, String> cache;

    public CachedVideoDownloader() {

        realDownloader = new RealVideoDownloader();

        cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String videoURL) {

        if (cache.containsKey(videoURL)) {

            System.out.println("Returning Cached Video : " + videoURL);

            return cache.get(videoURL);
        }

        System.out.println("Cache Miss... Downloading Video");

        String video = realDownloader.downloadVideo(videoURL);

        cache.put(videoURL, video);

        return video;
    }
}

/*
===============================================================================

                        CLIENT

Client communicates only with Proxy.

Client never knows whether the video is downloaded
or retrieved from cache.

===============================================================================
*/

public class ProxyPatternDemo {

    public static void main(String[] args) {

        /*
        ===============================================================

        Client creates Proxy

        ===============================================================
        */

        VideoDownloader downloader =
                new CachedVideoDownloader();

        /*
        ===============================================================

        User 1 downloads video

        Cache Miss

        ===============================================================
        */

        System.out.println("User 1 downloads video");

        downloader.downloadVideo(
                "https://video.com/proxy-pattern");

        System.out.println();

        System.out.println("--------------------------------");

        System.out.println();

        /*
        ===============================================================

        User 2 downloads same video

        Cache Hit

        ===============================================================
        */

        System.out.println("User 2 downloads same video");

        downloader.downloadVideo(
                "https://video.com/proxy-pattern");
    }
}

/*
===============================================================================

Dry Run

Step 1

Client creates

CachedVideoDownloader

------------------------------------------------------------

Step 2

User 1 requests

downloadVideo()

↓

Cache

Not Found

↓

Proxy forwards request

↓

RealVideoDownloader

↓

Downloads Video

↓

Stores in Cache

------------------------------------------------------------

Step 3

User 2 requests

downloadVideo()

↓

Cache

Found

↓

Returns Cached Video

↓

No Download

===============================================================================

Flow Diagram


                   Client

                      |

               VideoDownloader

                      |

          CachedVideoDownloader

                (Proxy)

                      |

          ---------------------

          |                   |

     Cache Exists?        Cache Miss

          |                   |

         Yes                  No

          |                   |

Return Cached Video     RealVideoDownloader

                              |

                        Download Video

                              |

                         Store in Cache

===============================================================================

Execution Flow


Client

   |

CachedVideoDownloader

   |

Check Cache

   |

----------------------------

|                          |

Found                    Not Found

|                          |

Return Cached      RealVideoDownloader

Video                     |

                      Download Video

                            |

                     Store in Cache

===============================================================================

OUTPUT

User 1 downloads video

Cache Miss... Downloading Video

Downloading video from URL :
https://video.com/proxy-pattern

--------------------------------

User 2 downloads same video

Returning Cached Video :
https://video.com/proxy-pattern

===============================================================================

Advantages

✔ Improves performance using caching.

✔ Controls access to the real object.

✔ Supports lazy initialization.

✔ Adds logging/security without changing original class.

✔ Follows Open/Closed Principle.

===============================================================================

Disadvantages

✘ Adds one extra layer.

✘ Slight increase in complexity.

✘ Cache management may become difficult.

===============================================================================

When to Use

✔ Caching.

✔ Security.

✔ Lazy Loading.

✔ Logging.

✔ Remote Objects (Remote Proxy).

✔ Access Control.

===============================================================================

Interview Questions

Q1. What is Proxy Pattern?

Ans:

Proxy Pattern provides a surrogate object that controls access to another
object.

------------------------------------------------

Q2. Which is the Subject?

Ans:

VideoDownloader

------------------------------------------------

Q3. Which is the Real Subject?

Ans:

RealVideoDownloader

------------------------------------------------

Q4. Which is the Proxy?

Ans:

CachedVideoDownloader

------------------------------------------------

Q5. What extra functionality does Proxy provide here?

Ans:

Caching.

------------------------------------------------

Q6. Is Proxy created before the Real Object?

Ans:

Yes.

The client communicates with the Proxy first.

------------------------------------------------

Q7. Which SOLID Principle is followed?

Ans:

Open/Closed Principle.

------------------------------------------------

Q8. Which category does Proxy belong to?

Ans:

Structural Design Pattern.

===============================================================================

30-Second Interview Summary

Proxy Pattern is a Structural Design Pattern that provides a placeholder
or surrogate object to control access to another object. Instead of
communicating directly with the real object, the client interacts with
the Proxy, which can add features such as caching, logging, security,
or lazy loading.

In this example, VideoDownloader is the Subject, RealVideoDownloader
is the Real Subject, and CachedVideoDownloader is the Proxy. The Proxy
checks whether a requested video already exists in the cache. If it does,
it returns the cached version; otherwise, it downloads the video,
stores it in the cache, and returns it. This improves performance by
avoiding unnecessary downloads.

===============================================================================
*/