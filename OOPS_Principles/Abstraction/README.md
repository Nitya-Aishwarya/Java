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
Initially, interfaces in Java could contain only:

Abstract methods-by default public abstract
Public static final variables

But from Java 8 onwards, interfaces can also contain:

Default Methods
Static Methods

This was introduced to improve:

* Flexibility
* Backward compatibility
* Reusability
---

# Default Method

A default method is a method inside an interface that already contains implementation.
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
# Overriding Default Methods

Default methods can also be overridden.

Example
interface Vehicle {

    default void fuelType() {
        System.out.println("Petrol");
    }
}

class ElectricCar implements Vehicle {

    public void fuelType() {
        System.out.println("Electric Battery");
    }
}
Explanation

The child class overrides the default implementation.

This provides customization.

# Why Default Methods Were Introduced

Suppose an interface is already implemented by many classes.

If we add a new abstract method to the interface:

All implementing classes must implement that method.
Existing code may break.

To avoid this problem:

Java introduced default methods.
A default implementation can be provided directly inside the interface.

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


### Interview Questions on Default and Static Methods

## 1. Can interfaces contain method implementation?
Yes.

Using:

Default methods
Static methods

## 2. What is a default method?
A method inside an interface with implementation using the default keyword.

## 3. Why were default methods introduced?

To support backward compatibility and avoid breaking existing code.

## 4. Can default methods be overridden?

Yes.

## 5. What is a static method in interface?

A method that belongs to the interface itself and is called using interface name.

## 6. Can static methods be overridden?

No.

## 7. Can we call static interface methods using object?

No.

Correct way:

InterfaceName.methodName();
### Tricky Interview Questions
## 1. Can an interface have constructors?

No.

Interfaces cannot have constructors because objects cannot be created for interfaces.

## 2. Are default methods inherited?

Yes.

## 3. Are static methods inherited?

No.

## 4. Why can’t static methods be overridden?

Because they belong to the interface, not to objects.

## 5. Can interfaces have private methods?

Yes.

From Java 9 onwards, interfaces can contain private methods.

---
# Interview Questions on Abstraction in Java

---

##  1. What is abstraction in Java?

Abstraction is the process of hiding implementation details and showing only essential functionality to the user.

It focuses on:

* what an object does
* not how it does it

---

##  2. How is abstraction achieved in Java?
Abstraction is achieved using:

* Abstract classes
* Interfaces

---

##  3. What is an abstract class?
An abstract class is a class that:

* cannot be instantiated
* may contain abstract and concrete methods

### Example

```java id="z9u3j6"
abstract class A {

    abstract void show();

    void display() {
        System.out.println("Concrete method");
    }
}
```

---

##  4. What is an interface?

An interface is a blueprint of a class that contains abstract methods and also supports:

* default methods
* static methods
* private methods (Java 9+)

Interfaces are mainly used to achieve abstraction and multiple inheritance.

---

##  5. Can we create an object of abstract class or interface?

No.

Both abstract classes and interfaces cannot be instantiated directly.

Wrong:

```java id="e6p9od"
Animal a = new Animal(); // Error
```

---

##  6. Can abstract classes have constructors?
Yes.

Abstract classes can have constructors, and they are called when a child class object is created.

### Example

```java id="vq07od"
abstract class Animal {

    Animal() {
        System.out.println("Abstract class constructor");
    }
}
```

---

##  7. Can interfaces have constructors?


No.

Interfaces cannot have constructors because:

* interfaces cannot be instantiated
* constructors are used during object creation

---

##  8. Can interfaces have methods with implementation?
Yes.

From Java 8 onwards, interfaces can contain:

* default methods
* static methods

From Java 9 onwards:

* private methods

---

##  9. Are default and static methods part of abstraction?:

Yes.

Abstraction still exists because:

* interfaces still define a contract
* implementation is still provided by classes
* default methods are optional
* static methods are utility-based

---

##  10. Is abstraction still 100% in Java interfaces after Java 8?


Before Java 8:

* Interfaces provided 100% abstraction.

After Java 8:

* Interfaces are not strictly 100% abstract because they can contain implemented methods.

However, abstraction still exists because the main contract remains abstract.

---

#  11. Difference between Abstract Class and Interface?

| Feature      | Abstract Class              | Interface                              |
| ------------ | --------------------------- | -------------------------------------- |
| Methods      | Abstract + concrete         | Abstract + default + static            |
| Variables    | Can have instance variables | Only constants (`public static final`) |
| Constructors |  Yes                        |  No                                    |
| Inheritance  | Single inheritance          | Multiple inheritance supported         |
| Keyword      | `extends`                   | `implements`                           |

---

##  12. Why do we need abstraction?


Abstraction:

* hides implementation complexity
* improves security
* increases code reusability
* makes systems modular
* improves maintainability
* supports scalability

---

##  13. Can a class implement multiple interfaces?
Yes.

Java supports multiple inheritance using interfaces.

### Example

```java id="d0ydn8"
class C implements A, B {

}
```

---

##  14. Can a class extend multiple abstract classes?

No.

Java does not support multiple inheritance with classes because it creates ambiguity problems.

---

##  15. Can abstract class have a main method?
Yes.

Because:

* static methods are allowed in abstract classes
* `main()` is a static method

### Example

```java id="0jlwmk"
abstract class Test {

    public static void main(String[] args) {

        System.out.println("Main Method");
    }
}
```

---

##  16. Can abstract class have final methods?
Yes.

Final methods:

* cannot be overridden
* are inherited as they are

### Example

```java id="mjlwm0"
abstract class A {

    final void show() {
        System.out.println("Final Method");
    }
}
```

---

## ❓ 17. Can interfaces have variables?

Yes.

But interface variables are always:

* `public`
* `static`
* `final`

### Example

```java id="8pjlwm"
interface Test {

    int x = 10;
}
```

Internally:

```java id="pnc2x9"
public static final int x = 10;
```

---

##  18. Why are interface variables static final?


Because:

* interfaces cannot be instantiated
* variables must be shared
* values must remain constant

---

##  19. Can abstract methods exist in interfaces?

Yes.

All interface methods are abstract by default unless they are:

* default methods
* static methods
* private methods

---

##  20. Key difference between abstraction and encapsulation?

| Abstraction                             | Encapsulation                   |
| --------------------------------------- | ------------------------------- |
| Hides complexity                        | Hides data                      |
| Focuses on behavior                     | Focuses on data security        |
| Achieved using abstract class/interface | Achieved using access modifiers |

---

# Additional Tricky Interview Questions

---

##  21. Can abstract classes contain static methods?
Yes.

Abstract classes can contain:

* static methods
* concrete methods
* constructors

---

##  22. Can abstract methods be private?

No.

Because abstract methods must be overridden, and private methods cannot be inherited.

Wrong:

```java id="wjlwm0"
private abstract void show(); // Error
```

---

##  23. Can abstract methods be final?
No.

Because:

* abstract methods must be overridden
* final methods cannot be overridden

Both are opposite concepts.

---

##  24. Can constructors be abstract?
No.

Constructors are used during object creation, while abstract methods are incomplete methods.

---

##  25. Can interfaces extend another interface?

Yes.

### Example

```java id="7n2r7s"
interface A {

}

interface B extends A {

}
```

---

##  26. Can interfaces implement another interface?
No.

Interfaces can only extend interfaces.

Classes implement interfaces.

---

##  27. Can an abstract class implement an interface?

Yes.

An abstract class may choose not to implement all interface methods.

### Example

```java id="tv0lsr"
interface A {

    void show();
}

abstract class B implements A {

}
```

---

##  28. What happens if a class does not implement all interface methods?

The class must be declared abstract.

---

##  29. Why do interfaces support multiple inheritance?

Because interfaces only define method contracts and avoid ambiguity caused by multiple class inheritance.

---

##  30. What is the main advantage of abstraction in large projects?


Abstraction:

* reduces complexity
* improves maintainability
* supports modularity
* hides internal implementation
* makes applications scalable

---

# Most Important Interview Definition

> Abstraction is the process of hiding internal implementation details and exposing only essential functionalities to the user.

---


