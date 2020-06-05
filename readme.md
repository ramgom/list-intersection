# Read Me First

This project uses Gradle and Spring Boot to build and package the application.

* To build the application use: ./gradlew clean build
* To run the application use: ./gradlew run

You will need at least JDK 8 to compile the code.

I'm using [lombok](https://projectlombok.org/) please refer to the documentation to properly set up your IDE to make the code compiles correctly.

I'm also using [jmockit](http://jmockit.github.io/) please refer to the documentation to properlt set up your IDE to be able to run the unit test.

# Some considerations

To measure the running time of the algorithm method I'm using a very simple [Stopwatch](https://guava.dev/releases/29.0-jre/api/docs/com/google/common/base/Stopwatch.html) provide by [Guava](https://github.com/google/guava/wiki) but in a production application I would probably use Micrometer with Prometheus and instead of having the metric embedded in the code I would use AOP to create an aspect around the method call that will actually calculate the running time. Usually frameworks like SpringBoot already offer annotations to achieve this.

The IntersectionFunction should probably be generic since we could apply this algorithm to other types of objects but for simplicity I just used Integer since the task was tailored to a list of random generated Integers.

For the random generator I decided to use the [ThreadLocalRandom](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadLocalRandom.html) generator because it performs better in a multithreading application. Probably for this test was not relevant, but I just wanted to show that this is a better option than using the global Random. I wanted to decouple the code from the actual random generator implementation because we could change implementation for an RNG (random number generator) service instead of using a local one without affecting the code.

I structured the code in a way that the presentation layer (Controller) is completely decoupled from business logic, that way we could just change the Controller for a spring like rest controller or mvc controller and the code doesn't have to change and since the Intersection function implementation is very related to the presentation layer I wanted to have the presentation layer to handle the concrete implementation.

Honestly the most challenging part of the task was the UI layer using JavaFX which is totally new to me. I had fun.