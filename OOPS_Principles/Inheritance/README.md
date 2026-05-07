# Inheritance, Composition, and Aggregation in Object-Oriented Programming (OOP)

# Introduction

Inheritance, Composition, and Aggregation are important concepts in Object-Oriented Programming (OOP) that define relationships between classes and objects.

These concepts help in:

* Code reusability
* Better software design
* Flexibility
* Modularity
* Maintainability
* Scalability

In OOP, relationships are mainly categorized into:

```text
1. IS-A Relationship
2. HAS-A Relationship
```

---

# Relationship Types in OOP

| Concept     | Relationship              |
| ----------- | ------------------------- |
| Inheritance | IS-A Relationship         |
| Composition | Strong HAS-A Relationship |
| Aggregation | Weak HAS-A Relationship   |

---

# 1. Inheritance

# Introduction

Inheritance is one of the four fundamental principles of OOP. It allows one class to inherit the properties and methods of another class.

In simple words:

> **Inheritance means acquiring the properties and behaviors of another class.**

Inheritance helps in:

* Reusing existing code
* Reducing duplication
* Building hierarchy
* Supporting polymorphism

---

# Definition of Inheritance

> Inheritance is the process by which one class acquires the properties and methods of another class.

OR

> Inheritance allows a child class to reuse the features of a parent class.

---

# Real Meaning of Inheritance

In real life:

* A child inherits features from parents.
* A son may inherit:

  * surname
  * habits
  * properties
  * behavior

Similarly in programming:

* Child class inherits:

  * variables
  * methods
  * behaviors

from parent class.

This is Inheritance.

---

# IS-A Relationship

Inheritance represents:

```text
IS-A Relationship
```

Examples:

```text
Dog IS-A Animal
Car IS-A Vehicle
Manager IS-A Employee
```

This means:

* Dog is a type of Animal.
* Car is a type of Vehicle.

---

# Parent Class and Child Class

Inheritance mainly involves two classes.

| Term         | Meaning                              |
| ------------ | ------------------------------------ |
| Parent Class | Class whose properties are inherited |
| Child Class  | Class that inherits properties       |

---

# Other Names

| Parent Class | Child Class   |
| ------------ | ------------- |
| Base Class   | Derived Class |
| Super Class  | Sub Class     |

---

# Syntax of Inheritance in Java

Inheritance is achieved using:

```java
extends
```

keyword.

---

# Syntax

```java
class Parent {

}

class Child extends Parent {

}
```

---

# Basic Example of Inheritance

```java
class Animal {

    void eat() {
        System.out.println("Animal eats food");
    }
}

class Dog extends Animal {

    void bark() {
        System.out.println("Dog barks");
    }
}
```

---

# Main Class

```java
public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();

        d.eat();

        d.bark();
    }
}
```

---

# Output

```text
Animal eats food
Dog barks
```

---

# Explanation

Here:

```java
class Dog extends Animal
```

means:

```text
Dog IS-A Animal
```

So:

* Dog inherits the `eat()` method from Animal class.
* Dog also has its own method:

  * `bark()`

---

# What Child Class Inherits

Child class inherits:

* Variables
* Methods
* Behaviors

except:

* Constructors
* Private members

---

# Example with Variables

```java
class Animal {

    String color = "Brown";
}

class Dog extends Animal {

    void display() {

        System.out.println(color);
    }
}
```

---

# Output

```text
Brown
```

---

# Why Inheritance is Needed

Without inheritance:

* Same code must be written repeatedly.
* Code duplication increases.
* Maintenance becomes difficult.

Inheritance solves these problems.

---

# Example Without Inheritance

```java
class Dog {

    void eat() {
        System.out.println("Eating");
    }
}

class Cat {

    void eat() {
        System.out.println("Eating");
    }
}
```

Same code repeated.

---

# Example With Inheritance

```java
class Animal {

    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {

}

class Cat extends Animal {

}
```

Code reused.

---

# Generalization and Specialization

Inheritance models:

```text
Generalization and Specialization
```

---

# Generalization

Generalization means:

* Common properties are moved into parent class.

Example:

```java
class Animal {

    void eat() {
        System.out.println("Eating");
    }
}
```

Here:

* `eat()` is common for all animals.

---

# Specialization

Specialization means:

* Child class adds specific features.

Example:

```java
class Dog extends Animal {

    void bark() {
        System.out.println("Barking");
    }
}
```

Dog becomes:

* Specialized Animal

---

# Advantages of Inheritance

## 1. Code Reusability

Existing code can be reused.

---

## 2. Reduces Code Duplication

No need to rewrite common code.

---

## 3. Better Maintainability

Changes in parent affect child classes.

---

## 4. Supports Polymorphism

Inheritance enables:

* Method overriding
* Runtime polymorphism

---

## 5. Better Organization

Common properties stay in parent class.

---

# Types of Inheritance

There are mainly five types of inheritance conceptually.

---

# 1. Single Inheritance

One child inherits one parent.

---

## Example

```java
class Animal {

    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {

    void bark() {
        System.out.println("Barking");
    }
}
```

---

# Diagram

```text
Animal
   |
  Dog
```

---

# 2. Multilevel Inheritance

A child class becomes parent for another class.

---

## Example

```java
class Animal {

    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {

    void bark() {
        System.out.println("Barking");
    }
}

class Puppy extends Dog {

    void weep() {
        System.out.println("Weeping");
    }
}
```

---

# Diagram

```text
Animal
   |
  Dog
   |
 Puppy
```

---

# 3. Hierarchical Inheritance

Multiple child classes inherit one parent class.

---

## Example

```java
class Animal {

    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {

}

class Cat extends Animal {

}
```

---

# Diagram

```text
       Animal
       /    \
     Dog    Cat
```

---

# 4. Multiple Inheritance

One class inherits multiple parent classes.

Java does NOT support multiple inheritance using classes.

---

# Why Java Does Not Support Multiple Inheritance

Because of:

```text
Diamond Problem
```

---

# Diamond Problem Example

```java
class A {

    void show() {
        System.out.println("A");
    }
}

class B extends A {

}

class C extends A {

}

// class D extends B, C { } // Not allowed
```

JVM becomes confused:

* Which `show()` method should be inherited?

To avoid ambiguity:

* Java avoids multiple inheritance using classes.

---

# 5. Hybrid Inheritance

Combination of multiple inheritance types.

Java does not support hybrid inheritance using classes.

---

# Multiple Inheritance Using Interfaces

Java supports multiple inheritance using interfaces.

---

# Example

```java
interface A {

    void show();
}

interface B {

    void display();
}

class Test implements A, B {

    public void show() {
        System.out.println("Show");
    }

    public void display() {
        System.out.println("Display");
    }
}
```

---

# super Keyword in Inheritance

`super` refers to parent class object.

Used to:

* Access parent variables
* Access parent methods
* Call parent constructor

---

# Access Parent Variable

```java
class Animal {

    String color = "Brown";
}

class Dog extends Animal {

    String color = "Black";

    void display() {

        System.out.println(super.color);
    }
}
```

---

# Output

```text
Brown
```

---

# Access Parent Method

```java
class Animal {

    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {

    void sound() {
        System.out.println("Dog barks");
    }

    void display() {

        super.sound();
    }
}
```

---

# Output

```text
Animal sound
```

---

# Constructor Chaining

When child object is created:

* Parent constructor executes first.

This is called:

```text
Constructor Chaining
```

---

# Example

```java
class Animal {

    Animal() {
        System.out.println("Animal Constructor");
    }
}

class Dog extends Animal {

    Dog() {

        super();

        System.out.println("Dog Constructor");
    }
}
```

---

# Output

```text
Animal Constructor
Dog Constructor
```

---

# 2. Composition

# Introduction

Composition is another important relationship concept in OOP.

Composition represents:

```text
Strong HAS-A Relationship
```

---

# Definition of Composition

> Composition is a strong HAS-A relationship where the child object cannot exist independently of the parent object.

OR

> Composition represents strong ownership between objects.

---

# Real-Life Example 1 — Car and Engine

A car contains:

* Engine
* Wheels
* Gear system

We say:

```text
Car HAS-A Engine
```

Engine strongly belongs to Car.

This is Composition.

---

# Real-Life Example 2 — Human and Heart

A human body contains:

* Heart
* Brain

We say:

```text
Human HAS-A Heart
```

Heart strongly depends on Human.

This is Composition.

---

# Characteristics of Composition

* Strong relationship
* Strong ownership
* Child depends on parent
* Tight coupling
* Parent controls child lifecycle
* Represents Part-Of relationship

---

# Composition Diagram

```text
Car ◆──── Engine
```

Filled diamond represents:

* Composition

---

# Composition Example

```java
class Engine {

    void start() {
        System.out.println("Engine Starts");
    }
}

class Car {

    private Engine engine;

    Car() {

        engine = new Engine();
    }

    void drive() {

        engine.start();

        System.out.println("Car is Driving");
    }
}
```

---

# Main Class

```java
public class Main {

    public static void main(String[] args) {

        Car c = new Car();

        c.drive();
    }
}
```

---

# Output

```text
Engine Starts
Car is Driving
```

---

# Explanation

Here:

```java
engine = new Engine();
```

means:

* Car itself creates Engine object.

So:

* Engine lifecycle depends on Car.

This is Composition.

---

# Lifecycle Dependency in Composition

This is the most important property of Composition.

In composition:

* Child object lifecycle depends on parent object.

If parent object is destroyed:

* Child object is also destroyed.

Example:

```text
Human → Heart
```

Without Human:

* Heart loses meaning in that relationship.

---

# Why Composition is Strong Relationship

Because:

* Child object cannot independently exist inside this relationship.
* Parent controls child object lifecycle.

---

# 3. Aggregation

# Introduction

Aggregation also represents HAS-A relationship, but it is weaker than composition.

Aggregation represents:

```text
Weak HAS-A Relationship
```

---

# Definition of Aggregation

> Aggregation is a weak HAS-A relationship where the child object can exist independently of the parent object.

OR

> Aggregation represents weak association between objects.

---

# Real-Life Example 1 — College and Student

A college contains students.

We say:

```text
College HAS-A Student
```

But:

* Student can exist independently.
* Student may join another college.

This is Aggregation.

---

# Real-Life Example 2 — Department and Teacher

A department contains teachers.

But:

* Teacher can exist independently.

This is Aggregation.

---

# Characteristics of Aggregation

* Weak relationship
* Weak ownership
* Child exists independently
* Loose coupling
* Parent does not control child lifecycle

---

# Aggregation Diagram

```text
College ◇──── Student
```

Empty diamond represents:

* Aggregation

---

# Aggregation Example

```java
class Student {

    String name;

    Student(String name) {

        this.name = name;
    }

    void displayStudent() {

        System.out.println(name);
    }
}

class College {

    private Student student;

    College(Student student) {

        this.student = student;
    }

    void displayCollege() {

        student.displayStudent();

        System.out.println("College Displayed");
    }
}
```

---

# Main Class

```java
public class Main {

    public static void main(String[] args) {

        Student s = new Student("John");

        College c = new College(s);

        c.displayCollege();
    }
}
```

---

# Output

```text
John
College Displayed
```

---

# Explanation

Here:

```java
Student s = new Student("John");
```

Student object is created independently.

Then:

```java
College c = new College(s);
```

College simply uses the Student object.

So:

* Student lifecycle does not depend on College.

This is Aggregation.

---

# Loose Coupling in Aggregation

Aggregation creates:

```text
Loose Coupling
```

because:

* Parent and child are weakly connected.

This improves:

* Flexibility
* Scalability
* Reusability

---

# Composition vs Aggregation

| Composition               | Aggregation                       |
| ------------------------- | --------------------------------- |
| Strong HAS-A relationship | Weak HAS-A relationship           |
| Strong ownership          | Weak ownership                    |
| Child depends on parent   | Child independent                 |
| Tight coupling            | Loose coupling                    |
| Parent controls lifecycle | Parent does not control lifecycle |
| Example: Car–Engine       | Example: College–Student          |

---

# Inheritance vs Composition vs Aggregation

| Concept     | Relationship |
| ----------- | ------------ |
| Inheritance | IS-A         |
| Composition | Strong HAS-A |
| Aggregation | Weak HAS-A   |

---

# Real-Life Summary

| Example               | Relationship |
| --------------------- | ------------ |
| Dog IS-A Animal       | Inheritance  |
| Car HAS-A Engine      | Composition  |
| College HAS-A Student | Aggregation  |

---

# Why Composition is Preferred Over Inheritance

Modern software design often prefers:

```text
Composition Over Inheritance
```

Because composition:

* Provides flexibility
* Reduces tight coupling
* Improves maintainability

Inheritance can create:

* Deep hierarchy
* Tight dependency

---

# Design Perspective

---

# Use Inheritance When:

```text
True IS-A relationship exists
```

Examples:

* Dog IS-A Animal
* Car IS-A Vehicle

---

# Use Composition When:

```text
Object strongly owns another object
```

Examples:

* Car HAS-A Engine
* Human HAS-A Heart

---

# Use Aggregation When:

```text
Objects are independent but associated
```

Examples:

* College HAS-A Student
* Department HAS-A Teacher

---

# Advantages of Inheritance

* Code reuse
* Reduced duplication
* Better organization
* Supports polymorphism

---

# Advantages of Composition

* Better flexibility
* Better modularity
* Better maintainability

---

# Advantages of Aggregation

* Loose coupling
* Independent objects
* Better scalability

---

# Disadvantages of Inheritance

* Tight coupling
* Deep inheritance increases complexity

---

# Disadvantages of Composition

* Slightly more complex
* Lifecycle management required

---

# Disadvantages of Aggregation

* Weak ownership may complicate management

---

# Important Interview Questions

---

## ❓ 1. What is inheritance?

### 👉 Answer:

Inheritance is the process by which one class acquires the properties and methods of another class.

---

## ❓ 2. What relationship does inheritance represent?

### 👉 Answer:

IS-A relationship.

---

## ❓ 3. What is composition?

### 👉 Answer:

Composition is a strong HAS-A relationship where child objects depend on parent objects.

---

## ❓ 4. What is aggregation?

### 👉 Answer:

Aggregation is a weak HAS-A relationship where child objects can exist independently.

---

## ❓ 5. What is the difference between composition and aggregation?

| Composition         | Aggregation         |
| ------------------- | ------------------- |
| Strong relationship | Weak relationship   |
| Dependent objects   | Independent objects |

---

## ❓ 6. What are the types of inheritance?

### 👉 Answer:

* Single inheritance
* Multilevel inheritance
* Hierarchical inheritance
* Multiple inheritance
* Hybrid inheritance

---

## ❓ 7. Does Java support multiple inheritance using classes?

### 👉 Answer:

No.

---

## ❓ 8. Why does Java not support multiple inheritance?

### 👉 Answer:

Because of Diamond Problem.

---

## ❓ 9. What is constructor chaining?

### 👉 Answer:

Parent constructor executes before child constructor.

---

## ❓ 10. What is the super keyword?

### 👉 Answer:

Used to access parent class members.

---

# Most Important Final Line

```text
Inheritance represents IS-A relationship, while Composition and Aggregation represent HAS-A relationships.
```

---

# Final Definition

> Inheritance is used for IS-A relationship, Composition represents strong HAS-A relationship, and Aggregation represents weak HAS-A relationship in Object-Oriented Programming.

---

# One-Line Quick Revision

```text
Inheritance → IS-A, Composition → Strong HAS-A, Aggregation → Weak HAS-A.
```

