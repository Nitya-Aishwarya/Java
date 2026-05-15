Below is a complete explanation of **functional interfaces in Java**.

# Functional Interface in Java

A **functional interface** in Java is an interface that contains **exactly one abstract method**.

That one abstract method is called the **Single Abstract Method**, or **SAM**.

A functional interface is mainly used with:

* Lambda expressions
* Method references
* Stream API
* Functional programming style
* Passing behavior as an argument

Example:

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}
```

This is a functional interface because it has only one abstract method:

```java
void sayHello();
```

---

# 1. Meaning of Functional Interface

An interface normally defines a contract. A class that implements the interface must provide implementation for its abstract methods.

A functional interface is a special type of interface that defines only **one required behavior**.

Example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

This interface represents one behavior: calculation.

Now we can provide different implementations using lambda expressions.

```java
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;
```

Here, the same interface is used for different behaviors.

---

# 2. Why Functional Interfaces Were Introduced

Before Java 8, Java did not have lambda expressions.

If we wanted to provide implementation for an interface, we had to create a class or use an anonymous inner class.

Example before Java 8:

```java
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting g = new Greeting() {
            public void sayHello() {
                System.out.println("Hello");
            }
        };

        g.sayHello();
    }
}
```

This code works, but it is long.

After Java 8, we can write the same logic using a lambda expression:

```java
Greeting g = () -> System.out.println("Hello");
```

This is shorter and cleaner.

---

# 3. Main Rule of Functional Interface

A functional interface must have **exactly one abstract method**.

Valid example:

```java
@FunctionalInterface
interface MyInterface {
    void show();
}
```

Invalid example:

```java
@FunctionalInterface
interface MyInterface {
    void show();
    void display();
}
```

This is invalid because it has two abstract methods.

The compiler gives an error.

---

# 4. What Is an Abstract Method?

An abstract method is a method that has only a declaration and no body.

Example:

```java
void show();
```

It does not contain implementation.

The implementing class or lambda expression must provide the implementation.

---

# 5. What Is a Lambda Expression?

A lambda expression is a short way to provide implementation for the single abstract method of a functional interface.

Syntax:

```java
(parameters) -> body
```

Example:

```java
(a, b) -> a + b
```

Here:

```java
(a, b)
```

are parameters.

```java
->
```

is the lambda operator.

```java
a + b
```

is the method body.

---

# 6. Functional Interface with Lambda Expression

Example:

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting g = () -> System.out.println("Hello Java");

        g.sayHello();
    }
}
```

Output:

```text
Hello Java
```

Explanation:

The interface has one abstract method:

```java
void sayHello();
```

The lambda expression:

```java
() -> System.out.println("Hello Java")
```

provides the implementation of that method.

When we call:

```java
g.sayHello();
```

Java executes the lambda body.

---

# 7. Internal Explanation of Functional Interface

When Java sees this code:

```java
Greeting g = () -> System.out.println("Hello Java");
```

Java first looks at the left side:

```java
Greeting
```

Then Java checks the `Greeting` interface.

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}
```

Java finds only one abstract method:

```java
void sayHello();
```

So Java understands that the lambda expression is the implementation of `sayHello()`.

Conceptually, this lambda:

```java
Greeting g = () -> System.out.println("Hello Java");
```

is similar to this anonymous inner class:

```java
Greeting g = new Greeting() {
    public void sayHello() {
        System.out.println("Hello Java");
    }
};
```

However, internally they are not exactly the same.

Anonymous inner classes create a separate class file or class structure.

Lambda expressions are handled more efficiently by the JVM using a mechanism called `invokedynamic`.

For basic understanding, remember this:

A lambda expression becomes the body of the single abstract method of a functional interface.

---

# 8. Why Only One Abstract Method Is Allowed?

A lambda expression does not mention the method name.

Example:

```java
() -> System.out.println("Hello")
```

This lambda only gives behavior.

It does not say whether it is implementing `show()`, `display()`, `print()`, or another method.

If an interface has only one abstract method, Java can easily decide which method the lambda is implementing.

Example:

```java
interface Demo {
    void show();
}
```

This is clear:

```java
Demo d = () -> System.out.println("Hello");
```

Java knows the lambda implements `show()`.

But if the interface has two abstract methods:

```java
interface Demo {
    void show();
    void display();
}
```

Then this becomes confusing:

```java
Demo d = () -> System.out.println("Hello");
```

Java cannot know whether the lambda should implement `show()` or `display()`.

That is why a functional interface must have only one abstract method.

---

# 9. `@FunctionalInterface` Annotation

The `@FunctionalInterface` annotation is used to tell the compiler that the interface is intended to be functional.

Example:

```java
@FunctionalInterface
interface Message {
    void print();
}
```

This annotation is optional.

This also works:

```java
interface Message {
    void print();
}
```

But using `@FunctionalInterface` is recommended.

It helps prevent mistakes.

Example:

```java
@FunctionalInterface
interface Message {
    void print();
    void send();
}
```

This gives a compile-time error because a functional interface cannot have two abstract methods.

So, `@FunctionalInterface` acts like a compiler-level safety check.

---

# 10. Functional Interface Without Annotation

An interface can still be functional even without the annotation.

Example:

```java
interface Task {
    void execute();
}
```

This is still a functional interface because it has only one abstract method.

The annotation is not required, but it is good practice.

---

# 11. Functional Interface with Parameters

A functional interface method can accept parameters.

Example:

```java
@FunctionalInterface
interface Printer {
    void print(String message);
}
```

Usage:

```java
public class Main {
    public static void main(String[] args) {
        Printer p = message -> System.out.println(message);

        p.print("Java is powerful");
    }
}
```

Output:

```text
Java is powerful
```

Because there is only one parameter, parentheses are optional.

These two are both valid:

```java
message -> System.out.println(message)
```

```java
(message) -> System.out.println(message)
```

---

# 12. Functional Interface with Return Value

A functional interface method can return a value.

Example:

```java
@FunctionalInterface
interface Square {
    int findSquare(int number);
}
```

Usage:

```java
public class Main {
    public static void main(String[] args) {
        Square s = number -> number * number;

        System.out.println(s.findSquare(5));
    }
}
```

Output:

```text
25
```

Here, the lambda returns:

```java
number * number
```

When the lambda body has only one expression, Java automatically returns the result.

---

# 13. Lambda with Multiple Statements

If the lambda body has multiple statements, we must use curly braces.

Example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> {
            int result = a + b;
            return result;
        };

        System.out.println(add.calculate(10, 20));
    }
}
```

Output:

```text
30
```

When curly braces are used and the method returns a value, the `return` keyword is required.

---

# 14. Default Methods in Functional Interface

A functional interface can have default methods.

Default methods have a body, so they are not abstract.

Example:

```java
@FunctionalInterface
interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }

    default void fuel() {
        System.out.println("Vehicle needs fuel");
    }
}
```

This is still a functional interface because only `start()` is abstract.

Usage:

```java
public class Main {
    public static void main(String[] args) {
        Vehicle bike = () -> System.out.println("Bike started");

        bike.start();
        bike.stop();
        bike.fuel();
    }
}
```

Output:

```text
Bike started
Vehicle stopped
Vehicle needs fuel
```

---

# 15. Static Methods in Functional Interface

A functional interface can have static methods.

Static methods belong to the interface itself.

Example:

```java
@FunctionalInterface
interface Utility {
    void execute();

    static void info() {
        System.out.println("This is a utility interface");
    }
}
```

Usage:

```java
public class Main {
    public static void main(String[] args) {
        Utility u = () -> System.out.println("Executing task");

        u.execute();

        Utility.info();
    }
}
```

Output:

```text
Executing task
This is a utility interface
```

Static methods do not affect the functional interface rule.

---

# 16. Private Methods in Functional Interface

From Java 9 onwards, interfaces can have private methods.

Private methods are usually used to share common code between default methods.

Example:

```java
@FunctionalInterface
interface Logger {
    void log(String message);

    default void info(String message) {
        print("INFO: " + message);
    }

    default void error(String message) {
        print("ERROR: " + message);
    }

    private void print(String message) {
        System.out.println(message);
    }
}
```

This is still a functional interface because only `log(String message)` is abstract.

Private methods do not count as abstract methods.

---

# 17. Object Class Methods Do Not Count

Methods from the `Object` class do not count as abstract methods in a functional interface.

Example:

```java
@FunctionalInterface
interface Demo {
    void show();

    String toString();
}
```

This is valid.

The method `toString()` already exists in the `Object` class.

Other examples are:

```java
boolean equals(Object obj);
int hashCode();
```

These methods do not break the functional interface rule.

---

# 18. Functional Interface Extending Another Interface

A functional interface can extend another interface.

Example:

```java
interface A {
    void show();
}

@FunctionalInterface
interface B extends A {
}
```

This is valid.

`B` inherits one abstract method from `A`.

So `B` is still functional.

Invalid example:

```java
interface A {
    void show();
}

@FunctionalInterface
interface B extends A {
    void display();
}
```

This is invalid.

Now `B` has two abstract methods:

```java
show()
display()
```

So it is not a functional interface.

---

# 19. Built-in Functional Interfaces

Java provides many predefined functional interfaces in the package:

```java
java.util.function
```

The most important ones are:

| Functional Interface  | Method                   | Meaning                                             |
| --------------------- | ------------------------ | --------------------------------------------------- |
| `Predicate<T>`        | `boolean test(T t)`      | Takes input and returns true or false               |
| `Function<T, R>`      | `R apply(T t)`           | Takes input and returns output                      |
| `Consumer<T>`         | `void accept(T t)`       | Takes input and returns nothing                     |
| `Supplier<T>`         | `T get()`                | Takes no input and returns output                   |
| `BiFunction<T, U, R>` | `R apply(T t, U u)`      | Takes two inputs and returns output                 |
| `BiPredicate<T, U>`   | `boolean test(T t, U u)` | Takes two inputs and returns boolean                |
| `BiConsumer<T, U>`    | `void accept(T t, U u)`  | Takes two inputs and returns nothing                |
| `UnaryOperator<T>`    | `T apply(T t)`           | Takes and returns same type                         |
| `BinaryOperator<T>`   | `T apply(T t1, T t2)`    | Takes two values of same type and returns same type |

---

# 20. `Predicate<T>`

`Predicate<T>` is used when we want to test a condition.

It takes one input and returns a boolean value.

Method:

```java
boolean test(T t);
```

Example:

```java
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEven = number -> number % 2 == 0;

        System.out.println(isEven.test(10));
        System.out.println(isEven.test(7));
    }
}
```

Output:

```text
true
false
```

Use `Predicate` when the result should be `true` or `false`.

---

# 21. `Function<T, R>`

`Function<T, R>` takes one input and returns one output.

Method:

```java
R apply(T t);
```

Example:

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> lengthFinder = text -> text.length();

        System.out.println(lengthFinder.apply("Java"));
    }
}
```

Output:

```text
4
```

Here:

```java
String
```

is the input type.

```java
Integer
```

is the return type.

---

# 22. `Consumer<T>`

`Consumer<T>` takes one input and returns nothing.

Method:

```java
void accept(T t);
```

Example:

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = name -> System.out.println(name);

        printer.accept("Nitya");
    }
}
```

Output:

```text
Nitya
```

Use `Consumer` when you want to use a value but do not want to return anything.

---

# 23. `Supplier<T>`

`Supplier<T>` takes no input and returns a value.

Method:

```java
T get();
```

Example:

```java
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<Double> randomNumber = () -> Math.random();

        System.out.println(randomNumber.get());
    }
}
```

Use `Supplier` when you want to generate or supply a value.

---

# 24. `BiFunction<T, U, R>`

`BiFunction<T, U, R>` takes two inputs and returns one output.

Method:

```java
R apply(T t, U u);
```

Example:

```java
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        System.out.println(add.apply(10, 20));
    }
}
```

Output:

```text
30
```

---

# 25. `UnaryOperator<T>`

`UnaryOperator<T>` is a special type of `Function`.

It takes one value and returns a value of the same type.

Example:

```java
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<Integer> square = n -> n * n;

        System.out.println(square.apply(5));
    }
}
```

Output:

```text
25
```

---

# 26. `BinaryOperator<T>`

`BinaryOperator<T>` takes two values of the same type and returns a value of the same type.

Example:

```java
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        BinaryOperator<Integer> add = (a, b) -> a + b;

        System.out.println(add.apply(10, 20));
    }
}
```

Output:

```text
30
```

---

# 27. Common Functional Interfaces Outside `java.util.function`

Some older Java interfaces also became functional interfaces because they have one abstract method.

Examples:

| Interface        | Abstract Method                       | Purpose                            |
| ---------------- | ------------------------------------- | ---------------------------------- |
| `Runnable`       | `void run()`                          | Used for threads                   |
| `Callable<V>`    | `V call()`                            | Used for tasks that return a value |
| `Comparator<T>`  | `int compare(T o1, T o2)`             | Used for sorting                   |
| `ActionListener` | `void actionPerformed(ActionEvent e)` | Used in event handling             |

---

# 28. Example with `Runnable`

`Runnable` is a functional interface.

It has one abstract method:

```java
void run();
```

Example:

```java
public class Main {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Thread is running");

        Thread t = new Thread(r);
        t.start();
    }
}
```

Here, the lambda expression provides the implementation of the `run()` method.

---

# 29. Example with `Callable`

`Callable` is similar to `Runnable`, but it returns a value and can throw an exception.

Example:

```java
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws Exception {
        Callable<Integer> task = () -> 100;

        System.out.println(task.call());
    }
}
```

Output:

```text
100
```

---

# 30. Example with `Comparator`

`Comparator` is used for sorting.

Example:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1);

        numbers.sort((a, b) -> a - b);

        System.out.println(numbers);
    }
}
```

Output:

```text
[1, 2, 5, 9]
```

Here, the lambda expression implements the `compare()` method.

---

# 31. Functional Interface with Method Reference

A method reference is a shorter form of a lambda expression.

Lambda example:

```java
Consumer<String> printer = text -> System.out.println(text);
```

Method reference example:

```java
Consumer<String> printer = System.out::println;
```

Full example:

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;

        printer.accept("Hello Java");
    }
}
```

Output:

```text
Hello Java
```

The method reference works because `Consumer<String>` has one abstract method:

```java
void accept(String value);
```

---

# 32. Types of Method References

There are four common types of method references.

## 32.1 Reference to a Static Method

```java
Function<String, Integer> parser = Integer::parseInt;
```

This is similar to:

```java
Function<String, Integer> parser = s -> Integer.parseInt(s);
```

## 32.2 Reference to an Instance Method of a Particular Object

```java
Consumer<String> printer = System.out::println;
```

This is similar to:

```java
Consumer<String> printer = s -> System.out.println(s);
```

## 32.3 Reference to an Instance Method of an Arbitrary Object

```java
Function<String, String> upper = String::toUpperCase;
```

This is similar to:

```java
Function<String, String> upper = s -> s.toUpperCase();
```

## 32.4 Reference to a Constructor

```java
Supplier<ArrayList<String>> listSupplier = ArrayList::new;
```

This is similar to:

```java
Supplier<ArrayList<String>> listSupplier = () -> new ArrayList<>();
```

---

# 33. Functional Interfaces in Stream API

Functional interfaces are heavily used in Java Streams.

Example:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);

        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(n -> System.out.println(n));
    }
}
```

Output:

```text
10
20
30
```

Explanation:

```java
filter(n -> n % 2 == 0)
```

uses a `Predicate<Integer>`.

```java
forEach(n -> System.out.println(n))
```

uses a `Consumer<Integer>`.

---

# 34. More Stream Example

```java
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Nitya", "Asha", "Ravi", "Neha");

        List<String> result = names.stream()
                .filter(name -> name.startsWith("N"))
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

Output:

```text
[NITYA, NEHA]
```

Explanation:

```java
name -> name.startsWith("N")
```

is a `Predicate<String>`.

```java
name -> name.toUpperCase()
```

is a `Function<String, String>`.

---

# 35. Target Type of Lambda

A lambda expression needs a target type.

Example:

```java
Calculator add = (a, b) -> a + b;
```

Here, `Calculator` is the target type.

Java checks the abstract method in `Calculator` and matches the lambda with it.

This will not work alone:

```java
(a, b) -> a + b;
```

A lambda must be assigned to a functional interface or passed where a functional interface is expected.

---

# 36. Complete Custom Example

```java
@FunctionalInterface
interface Payment {
    void pay(double amount);
}

public class Main {
    public static void main(String[] args) {
        Payment creditCardPayment = amount -> {
            System.out.println("Paid " + amount + " using credit card");
        };

        Payment upiPayment = amount -> {
            System.out.println("Paid " + amount + " using UPI");
        };

        creditCardPayment.pay(500.0);
        upiPayment.pay(750.0);
    }
}
```

Output:

```text
Paid 500.0 using credit card
Paid 750.0 using UPI
```

Explanation:

The `Payment` interface has one abstract method:

```java
void pay(double amount);
```

Each lambda gives a different implementation of that method.

---

# 37. Passing Functional Interface as Method Argument

Functional interfaces allow us to pass behavior as an argument.

Example:

```java
@FunctionalInterface
interface Operation {
    int perform(int a, int b);
}

public class Main {
    static void calculate(int x, int y, Operation op) {
        System.out.println(op.perform(x, y));
    }

    public static void main(String[] args) {
        calculate(10, 5, (a, b) -> a + b);
        calculate(10, 5, (a, b) -> a - b);
        calculate(10, 5, (a, b) -> a * b);
    }
}
```

Output:

```text
15
5
50
```

This is powerful because the method `calculate()` can accept different behaviors.

---

# 38. Returning Functional Interface from Method

A method can also return a functional interface.

Example:

```java
@FunctionalInterface
interface Operation {
    int perform(int a, int b);
}

public class Main {
    static Operation getAdditionOperation() {
        return (a, b) -> a + b;
    }

    public static void main(String[] args) {
        Operation op = getAdditionOperation();

        System.out.println(op.perform(10, 20));
    }
}
```

Output:

```text
30
```

---

# 39. Functional Interface and Generics

Functional interfaces can use generics.

Example:

```java
@FunctionalInterface
interface Converter<T, R> {
    R convert(T value);
}
```

Usage:

```java
public class Main {
    public static void main(String[] args) {
        Converter<String, Integer> stringToInteger = value -> Integer.parseInt(value);

        System.out.println(stringToInteger.convert("100"));
    }
}
```

Output:

```text
100
```

Here:

```java
T
```

represents input type.

```java
R
```

represents return type.

---

# 40. Functional Interface and Checked Exceptions

If a functional interface method declares an exception, the lambda can throw that exception.

Example:

```java
@FunctionalInterface
interface FileReaderTask {
    void read() throws Exception;
}

public class Main {
    public static void main(String[] args) {
        FileReaderTask task = () -> {
            throw new Exception("File error");
        };

        try {
            task.read();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

Output:

```text
File error
```

Built-in interfaces like `Consumer`, `Function`, and `Predicate` usually do not declare checked exceptions.

So if you need checked exceptions, you may create your own functional interface.

---

# 41. Functional Interface vs Normal Interface

| Point            | Functional Interface                | Normal Interface                   |
| ---------------- | ----------------------------------- | ---------------------------------- |
| Abstract methods | Exactly one                         | Can have one or more               |
| Lambda support   | Yes                                 | Only if it has one abstract method |
| Main use         | Represent single behavior           | General abstraction                |
| Annotation       | Usually uses `@FunctionalInterface` | Usually does not                   |
| Example          | `Runnable`, `Predicate`             | `List`, `Map`                      |

---

# 42. Functional Interface vs Marker Interface

| Point            | Functional Interface | Marker Interface      |
| ---------------- | -------------------- | --------------------- |
| Abstract methods | One                  | None                  |
| Purpose          | Represents behavior  | Marks or tags a class |
| Lambda support   | Yes                  | No                    |
| Example          | `Runnable`           | `Serializable`        |

---

# 43. Functional Interface vs Anonymous Inner Class

Functional interface can be implemented using both anonymous inner class and lambda expression.

Anonymous inner class:

```java
Greeting g = new Greeting() {
    public void sayHello() {
        System.out.println("Hello");
    }
};
```

Lambda expression:

```java
Greeting g = () -> System.out.println("Hello");
```

Lambda expressions are shorter and cleaner.

However, there are differences.

In an anonymous inner class, `this` refers to the anonymous class object.

In a lambda expression, `this` refers to the surrounding class object.

---

# 44. Important Lambda Rules

If there is one parameter, parentheses are optional.

```java
x -> x * x
```

If there are zero parameters, parentheses are required.

```java
() -> System.out.println("Hello")
```

If there are two or more parameters, parentheses are required.

```java
(a, b) -> a + b
```

If the body has one expression, curly braces are optional.

```java
(a, b) -> a + b
```

If the body has multiple statements, curly braces are required.

```java
(a, b) -> {
    int result = a + b;
    return result;
}
```

---

# 45. Variable Capture in Lambda

A lambda expression can access local variables from the surrounding method, but those variables must be final or effectively final.

Example:

```java
public class Main {
    public static void main(String[] args) {
        String message = "Hello";

        Runnable r = () -> System.out.println(message);

        r.run();
    }
}
```

This works because `message` is not changed after assignment.

This does not work:

```java
public class Main {
    public static void main(String[] args) {
        String message = "Hello";

        Runnable r = () -> System.out.println(message);

        message = "Hi";

        r.run();
    }
}
```

This gives an error because `message` is no longer effectively final.

---

# 46. Why Functional Interfaces Are Important

Functional interfaces are important because they make Java more flexible.

They allow methods to accept behavior.

They make code shorter.

They are the foundation of lambda expressions.

They are used heavily in modern Java programming.

They are important for collections, streams, sorting, filtering, mapping, and event handling.

---

# 47. Advantages of Functional Interfaces

Functional interfaces provide many advantages.

They reduce boilerplate code.

They improve readability.

They support lambda expressions.

They support method references.

They allow behavior to be passed as data.

They make Stream API possible.

They help write cleaner and more flexible code.

---

# 48. Disadvantages or Limitations

Functional interfaces are not always the best choice.

If an interface needs multiple unrelated methods, it should not be forced into a functional interface.

Too many lambdas can make code harder to debug.

Complex lambda logic can reduce readability.

For long logic, a normal method is usually better than a very large lambda.

---

# 49. Best Practices

Use `@FunctionalInterface` for custom functional interfaces.

Use built-in interfaces like `Predicate`, `Function`, `Consumer`, and `Supplier` when possible.

Keep lambda expressions short and readable.

Avoid writing too much logic inside a lambda.

Use method references when they improve readability.

Create custom functional interfaces only when built-in ones do not clearly express your intention.

---

# 50. Final Summary

A **functional interface** is an interface with exactly one abstract method.

It can contain default methods, static methods, and private methods.

It can be implemented using a lambda expression.

It can also be implemented using a method reference or anonymous inner class.

The single abstract method gives Java a clear target for the lambda expression.

Example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;

        System.out.println(add.calculate(10, 20));
    }
}
```

Output:

```text
30
```

The most important idea is this:

A functional interface represents one behavior, and a lambda expression provides the implementation of that behavior.
