# Learning Concurrency in Kotlin

<a href="https://www.packtpub.com/application-development/learning-concurrency-kotlin?utm_source=github&utm_medium=repository&utm_campaign=9781788627160 "><img src="https://d1ldz4te4covpm.cloudfront.net/sites/default/files/imagecache/ppv4_main_book_cover/B09861_MockupCoverNew.png" alt="Learning Concurrency in Kotlin" height="256px" align="right"></a>

This is the code repository for [Learning Concurrency in Kotlin](https://www.packtpub.com/application-development/learning-concurrency-kotlin?utm_source=github&utm_medium=repository&utm_campaign=9781788627160), published by Packt.

**Take advantage of Kotlin's concurrency primitives to write efficient multithreaded applications**

## What is this book about?
The primary requirements of modern-day applications are scalability, speed, and making the most use of hardware. Kotlin meets these requirements with its immense support for concurrency. Many concurrent primitives of Kotlin, such as channels and suspending functions, are designed to be non-blocking and efficient. This allows for new approaches to concurrency and creates unique challenges for the design and implementation of concurrent code. Learning Concurrency in Kotlin addresses those challenges with real-life examples and exercises that take advantage of Kotlin's primitives.

This book covers the following exciting features:
* Understand Kotlin’s approach to concurrency and how it is different to that of Java’s 
* Implement suspending and asynchronous coroutines 
* Understand how to decide which thread to execute a coroutine 
* Create potentially infinite data streams that are loaded lazily and concurrently 
* Explore the best practices for error handling 

If you feel this book is for you, get your [copy](https://www.amazon.com/dp/1788627164) today!

<a href="https://www.packtpub.com/?utm_source=github&utm_medium=banner&utm_campaign=GitHubBanner"><img src="https://raw.githubusercontent.com/PacktPublishing/GitHub/master/GitHub.png" 
alt="https://www.packtpub.com/" border="5" /></a>

## Instructions and Navigations
All of the code is organized into folders. For example, Chapter02.

The code will look like the following:
```
val time = measureTimeMillis {
        val name = async { getName() }
        val lastName = async { getLastName() }

        println("Hello, ${name.await()} ${lastName.await()}")
    }
```

**Following is what you need for this book:**
If you’re a Kotlin or Android developer interested in learning how to program concurrently to enhance the performance of your applications, this is the book for you.

With the following software and hardware list you can run all code files present in the book (Chapter 1-9).
### Software and Hardware List
| Chapter | Software required | OS required |
| -------- | ------------------------------------ | ----------------------------------- |
| 1 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 2 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 3 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 4 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 5 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 6 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 7 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 8 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |
| 9 | IntelliJ IDEA Community Edition 18.01 and Android Studio | Windows, Mac OS X, and Linux (Any) |

### Related products
* Hands-on Design Patterns with Kotlin [[Packt]](https://www.packtpub.com/application-development/hands-design-patterns-kotlin?utm_source=github&utm_medium=repository&utm_campaign=9781788998017) [[Amazon]](https://www.amazon.com/dp/1788998014)

* Mastering High Performance with Kotlin [[Packt]](https://www.packtpub.com/application-development/mastering-high-performance-kotlin?utm_source=github&utm_medium=repository&utm_campaign=9781788996648) [[Amazon]](https://www.amazon.com/dp/178899664X)

## Get to Know the Author
**Miguel Angel Castiblanco Torres**
is a software engineer living in the United States. He works as a full-stack technical leader and software designer at Globant, where he has led many successful projects for a Forbes' Top Ten World's Most Valuable Brand and Top Five Regarded Company.

Passionate about what's next, Miguel was an early adopter of Kotlin, writing about Kotlin's concurrency primitives from the first beta release of coroutines. He always keeps an eye on the new and upcoming features of Kotlin.

### Suggestions and Feedback
[Click here](https://docs.google.com/forms/d/e/1FAIpQLSdy7dATC6QmEL81FIUuymZ0Wy9vH1jHkvpY57OiMeKGqib_Ow/viewform) if you have any feedback or suggestions.


