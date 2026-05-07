# Abstraction in Object-Oriented Programming (OOP)

## Introduction

Abstraction is one of the fundamental principles of Object-Oriented Programming (OOP). It is the process of hiding internal implementation details and showing only the essential features or functionalities to the user.

In simple words:

> **Abstraction means showing only the required functionality to the user while hiding the internal implementation details.**

The user only focuses on:

* What the object does

The user does not need to know:

* How the object performs the task internally

Abstraction helps in:

* Reducing complexity
* Improving security
* Simplifying code usage
* Increasing maintainability
* Enhancing flexibility

---

# Definition of Abstraction

> Abstraction is the process of hiding implementation details and exposing only the essential features of an object.

---

# Formula of Abstraction

```text id="9pk0w6"
Abstraction = Hiding Internal Implementation + Showing Essential Features
```

---

# Why Abstraction is Needed

Without abstraction:

* Users would need to understand every internal detail.
* Software systems would become very complex.
* Maintenance would become difficult.

With abstraction:

* Complexity remains hidden.
* Users interact with simple functionalities.
* Systems become easier to use and maintain.

---

# Main Goal of Abstraction

The main goal of abstraction is:

> “Focus on what an object does instead of how it does it.”

---

# Real-Life Example 1 — ATM Machine

We use ATM machines for:

* Withdrawing money
* Checking account balance
* Depositing money
* Transferring money

As users:

* We insert the card
* Enter the PIN
* Select an operation
* Receive the result

But we do not know:

* How the card is validated
* How bank servers communicate
* How the database updates
* How security checks happen

We only use the services.

This is **Abstraction**.

---

# Explanation of ATM Example

The ATM provides a simple interface to users.

Users interact only with:

* Buttons
* Screen
* Menu options

The internal implementation remains hidden.

Similarly in programming:

* Users only use methods
* Internal logic remains hidden

---

# Real-Life Example 2 — Television

A television provides functionalities such as:

* Change channel
* Adjust volume
* Turn ON/OFF

As users:

* We simply press buttons on the remote.
* We do not know how the internal circuits work.

We use the functionality without understanding the implementation.

This is Abstraction.

---

# Television Example in Programming

```java id="g0dh5v"
class Television {

    void changeChannel() {
        // Internal Implementation
    }

    void adjustVolume() {
        // Internal Implementation
    }

    void powerOn() {
        // Internal Implementation
    }
}
```

---

# Explanation

The Television class provides:

* `changeChannel()`
* `adjustVolume()`
* `powerOn()`

As users:

* We only use these methods.
* We do not know their internal logic.

This is abstraction.

---

# Real-Life Example 3 — Car Driving

While driving a car:

* We use steering
* Brake
* Accelerator
* Gear system

But we do not know:

* Engine combustion process
* Fuel injection mechanisms
* Internal mechanical operations

We only use the provided functionalities.

This is Abstraction.

---

# Abstraction in Programming

In programming:

* Users call methods/functions
* Internal implementation remains hidden

Example:

```java id="tv5t8t"
Math.pow(2, 3);
```

We know:

* It calculates power.

But we do not know:

* The internal algorithm used.

This is abstraction.

---

# Features of Abstraction

---

## 1. Hides Complexity

Users do not need to understand internal implementation.

---

## 2. Improves Security

Internal logic remains hidden from unauthorized users.

---

## 3. Improves Maintainability

Internal changes can be made without affecting users.

---

## 4. Improves Flexibility

Different implementations can exist for the same abstraction.

---

## 5. Improves Reusability

Abstract designs can be reused across applications.

---

# Types of Abstraction in Java

Java supports two types of abstraction:

1. Partial Abstraction
2. Complete Abstraction

---

# 1. Partial Abstraction

Partial abstraction means:

* Some methods contain implementation
* Some methods remain abstract

Achieved using:

* Abstract classes

---

## Example

```java id="3lw2s5"
abstract class Vehicle {

    abstract void start();

    void fuelType() {
        System.out.println("Petrol");
    }
}
```

### Explanation

* `start()` is abstract
* `fuelType()` contains implementation

This is partial abstraction.

---

# 2. Complete Abstraction

Complete abstraction means:

* Only method declarations are visible
* Implementation details remain hidden

Achieved using:

* Interfaces

---

## Example

```java id="ctjlwm"
interface Vehicle {

    void start();
}
```

This provides complete abstraction.

---

# How Abstraction is Achieved in Java

Abstraction is achieved using:

1. Abstract Classes
2. Interfaces

---

# Abstract Class

An abstract class is a class that:

* Cannot be instantiated directly
* May contain abstract and normal methods

---

# Abstract Method

An abstract method:

* Has no implementation/body
* Only contains declaration

---

# Syntax of Abstract Class

```java id="3wvcc5"
abstract class ClassName {

    abstract void methodName();
}
```

---

# Example of Abstract Class

```java id="sx0mkq"
abstract class Animal {

    abstract void sound();
}
```

---

# Explanation

* `Animal` is an abstract class.
* `sound()` is an abstract method.
* The implementation is hidden.

Child classes provide implementation.

---

# Complete Programmatic Example Using Abstract Class

## Program

```java id="hprn1n"
abstract class Animal {

    // Abstract Method
    abstract void sound();

    // Normal Method
    void sleep() {
        System.out.println("Animal is sleeping");
    }
}

class Dog extends Animal {

    // Implementation of Abstract Method
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();

        d.sound();

        d.sleep();
    }
}
```

---

# Step-by-Step Explanation

---

## Step 1 — Abstract Class

```java id="1vhswl"
abstract class Animal
```

* `Animal` is declared abstract.
* Objects of abstract classes cannot be created.

Wrong:

```java id="4xtrfv"
Animal a = new Animal(); // Error
```

---

## Step 2 — Abstract Method

```java id="u9eslk"
abstract void sound();
```

* No implementation/body.
* Child classes must implement it.

---

## Step 3 — Child Class

```java id="jlwmvz"
class Dog extends Animal
```

Dog inherits properties from Animal.

---

## Step 4 — Method Implementation

```java id="w3b4gb"
void sound() {
    System.out.println("Dog barks");
}
```

Dog provides implementation.

---

## Step 5 — Normal Method

```java id="r1vlga"
void sleep()
```

Abstract classes can contain normal methods.

---

# Output

```text id="lpryxz"
Dog barks
Animal is sleeping
```

---

# Interface in Java

An interface is used to achieve abstraction.

Interfaces contain:

* Method declarations
* No complete implementation

---

# Syntax of Interface

```java id="pjlwm4"
interface InterfaceName {

    void methodName();
}
```

---

# Example Using Interface

```java id="qef9sq"
interface Vehicle {

    void start();
}

class Car implements Vehicle {

    public void start() {
        System.out.println("Car starts using key");
    }
}

public class Main {

    public static void main(String[] args) {

        Car c = new Car();

        c.start();
    }
}
```

---

# Output

```text id="v5n45n"
Car starts using key
```

---

# Explanation

* `Vehicle` provides method declaration.
* `Car` provides implementation.
* Users only use the `start()` method.

This is abstraction.

---

# Default Methods in Interface (Java 8)

Before Java 8:

* Interfaces could only contain abstract methods.

Java 8 introduced:

* Default methods
* Static methods

---

# Default Method

A default method:

* Contains implementation
* Uses the `default` keyword

---

# Syntax

```java id="7vw1j4"
interface InterfaceName {

    default void methodName() {

        // Implementation
    }
}
```

---

# Example of Default Method

```java id="yn6vs2"
interface Vehicle {

    void start();

    default void fuelType() {
        System.out.println("Petrol");
    }
}

class Car implements Vehicle {

    public void start() {
        System.out.println("Car Starts");
    }
}

public class Main {

    public static void main(String[] args) {

        Car c = new Car();

        c.start();

        c.fuelType();
    }
}
```

---

# Output

```text id="2nb7w7"
Car Starts
Petrol
```

---

# Explanation

* `start()` must be implemented.
* `fuelType()` already contains implementation.
* Child classes may override default methods.

---

# Why Default Methods Were Introduced

Suppose thousands of classes implement an interface.

If a new abstract method is added:

* Existing classes would break.

Default methods solve this problem by:

* Providing default implementation directly inside the interface.

This improves backward compatibility.

---

# Static Methods in Interface (Java 8)

Interfaces can also contain static methods.

Static methods:

* Belong to the interface
* Are called using interface name
* Cannot be overridden

---

# Syntax

```java id="djlwmr"
interface InterfaceName {

    static void methodName() {

        // Implementation
    }
}
```

---

# Example of Static Method

```java id="gzw5hu"
interface Calculator {

    static int add(int a, int b) {
        return a + b;
    }
}

public class Main {

    public static void main(String[] args) {

        int result = Calculator.add(10, 20);

        System.out.println(result);
    }
}
```

---

# Output

```text id="f4swzx"
30
```

---

# Explanation

* `add()` belongs to the interface.
* Called using:

```java id="0yj0ow"
Calculator.add();
```

No object creation required.

---

# Private Methods in Interface (Java 9)

Java 9 introduced private methods inside interfaces.

These methods:

* Are used internally
* Help avoid code duplication

---

# Example

```java id="tvdfxr"
interface Test {

    private void show() {
        System.out.println("Private Method");
    }
}
```

---

# Purpose of Private Methods

Used internally by:

* Default methods
* Static methods

This improves code reusability.

---

# Important Rules of Abstract Classes

---

## Rule 1

Abstract classes cannot be instantiated.

---

## Rule 2

If a class contains an abstract method, the class must be abstract.

---

## Rule 3

Child classes must implement all abstract methods.

---

## Rule 4

Abstract classes can contain:

* Constructors
* Variables
* Static methods
* Normal methods

---

# Important Rules of Interfaces

---

## Rule 1

Interfaces provide abstraction.

---

## Rule 2

Methods inside interfaces are public and abstract by default.

---

## Rule 3

Interfaces use the `implements` keyword.

---

## Rule 4

A class can implement multiple interfaces.

---

## Rule 5

Interfaces can contain:

* Default methods
* Static methods
* Private methods (Java 9+)

---

# Difference Between Abstract Class and Interface

| Abstract Class                    | Interface                        |
| --------------------------------- | -------------------------------- |
| Supports partial abstraction      | Supports complete abstraction    |
| Can contain normal methods        | Mainly contains abstract methods |
| Uses `abstract` keyword           | Uses `interface` keyword         |
| Supports constructors             | No constructors                  |
| Supports state/instance variables | Only constants                   |

---

# Difference Between Abstraction and Encapsulation

| Abstraction                                | Encapsulation                    |
| ------------------------------------------ | -------------------------------- |
| Hides implementation details               | Hides data                       |
| Focuses on simplicity                      | Focuses on security              |
| Achieved using abstract classes/interfaces | Achieved using access specifiers |
| Shows essential features                   | Restricts direct access          |

---

# Advantages of Abstraction

* Reduces complexity
* Improves security
* Simplifies usage
* Improves maintainability
* Increases flexibility
* Makes systems scalable

---

# Disadvantages of Abstraction

* Increases design complexity
* Requires careful planning

---

# Important Interview Questions

---

## 1. What is Abstraction?

Abstraction is the process of hiding implementation details and showing only essential functionalities to the user.

---

## 2. How is Abstraction achieved in Java?

Using:

* Abstract classes
* Interfaces

---

## 3. What is an Abstract Class?

An abstract class is a class that cannot be instantiated and may contain abstract methods.

---

## 4. What is an Abstract Method?

An abstract method is a method without implementation.

---

## 5. Can we create objects of abstract classes?

No.

---

## 6. What is the purpose of interfaces?

Interfaces provide abstraction and support multiple inheritance.

---

## 7. What are default methods?

Methods with implementation inside interfaces using the `default` keyword.

---

## 8. What are static methods in interfaces?

Methods that belong to the interface itself and are called using interface name.

---

## 9. Can static methods be overridden?

No.

---

## 10. Can interfaces contain private methods?

Yes, from Java 9 onwards.

---

# Tricky Interview Questions

---

## 1. Can an abstract class contain constructors?

Yes.

---

## 2. Can abstract methods have a body?

No.

---

## 3. Can interfaces contain implemented methods?

Yes.

Using:

* Default methods
* Static methods

---

## 4. Are default methods inherited?

Yes.

---

## 5. Are static methods inherited?

No.

---

## 6. Can a class contain both abstraction and encapsulation?

Yes.

---

# Most Important Definition

> Abstraction is the process of hiding internal implementation details and exposing only the essential functionalities to the user.

---

# One-Line Quick Revision

```text id="nmyxjs"
Abstraction hides complexity and allows users to interact only with the required functionalities.
```

