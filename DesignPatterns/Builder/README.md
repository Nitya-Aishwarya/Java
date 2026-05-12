# Builder Design Pattern in Java

## Introduction

The **Builder Design Pattern** is a **creational design pattern** that is used to construct complex objects step by step.

It separates the construction of an object from its representation so that the same construction process can create different representations of the object.

In simple words, the Builder Pattern helps us create objects that contain many fields or optional parameters in a clean and readable way.

---

# Why Do We Use Builder Pattern?

We use the Builder Pattern when:

* An object contains many fields or parameters
* Some parameters are optional
* Object creation becomes complicated
* Constructors become too large
* We want readable and maintainable object creation

The Builder Pattern avoids creating constructors with too many parameters.

---

# Problem Without Builder Pattern

Suppose we have a `Student` class.

```java
class Student {

    int id;
    String name;
    int age;
    String department;
    String email;
    String address;

    Student(int id, String name, int age,
            String department, String email,
            String address) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.email = email;
        this.address = address;
    }
}
```

Creating an object becomes difficult:

```java
Student s = new Student(
    101,
    "Nitya",
    21,
    "CSE",
    "nitya@gmail.com",
    "Chennai"
);
```

Problems:

* Difficult to remember parameter order
* Constructors become very long
* Readability decreases
* Optional values become difficult to manage

This problem is called the **Telescoping Constructor Problem**.

Builder Pattern solves this issue.

---

# Real-Time Analogy

## Example: Ordering a Burger

When ordering a burger:

* Bread is mandatory
* Cheese is optional
* Sauce is optional
* Extra toppings are optional

The customer selects items step by step.

The final burger is prepared after all selections are complete.

Similarly, Builder Pattern creates objects step by step.

---

# Main Idea of Builder Pattern

The Builder Pattern:

* Creates objects step by step
* Separates construction logic from the object itself
* Makes object creation flexible and readable

Instead of passing everything into one constructor, we build the object gradually.

---

# Components of Builder Pattern

The Builder Pattern usually contains:

1. Product Class
2. Builder Class
3. Build Method
4. Client Class

---

# 1. Product Class

The product class is the actual object being created.

Example:

```java
class Student {

    private int id;
    private String name;
    private int age;
    private String department;

    // Constructor
}
```

---

# 2. Builder Class

The builder class contains methods for setting values step by step.

It usually:

* Stores temporary values
* Returns the builder object itself
* Contains a `build()` method

---

# 3. Build Method

The `build()` method creates and returns the final object.

---

# 4. Client

The client uses the builder to create objects.

---

# Why Do We Use a Builder Class?

We use a Builder class because:

* It improves readability
* It avoids constructors with many parameters
* It handles optional parameters easily
* It creates immutable objects safely
* It provides flexible object creation

Without a Builder class:

* Constructors become confusing
* Code becomes harder to maintain

---

# Basic Builder Pattern Example

## Step 1: Create Product Class

```java
class Student {

    private int id;
    private String name;
    private int age;
    private String department;

    private Student(StudentBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.department = builder.department;
    }

    public void display() {
        System.out.println(id + " " + name + " " + age + " " + department);
    }
```

---

## Step 2: Create Static Builder Class

```java
    public static class StudentBuilder {

        private int id;
        private String name;
        private int age;
        private String department;

        public StudentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
```

---

# Usage Example

```java
public class Main {

    public static void main(String[] args) {

        Student s = new Student.StudentBuilder()
                        .setId(101)
                        .setName("Nitya")
                        .setAge(21)
                        .setDepartment("CSE")
                        .build();

        s.display();
    }
}
```

---

# Output

```java
101 Nitya 21 CSE
```

---

# How Builder Pattern Works

1. The client creates a Builder object
2. Values are set step by step
3. Each setter method returns the same Builder object
4. Method chaining is used
5. Finally, `build()` creates the final object

---

# Why Setter Methods Return `this`

Builder methods return `this` so that method chaining becomes possible.

Example:

```java
.setId(101)
.setName("Nitya")
.setAge(21)
```

This improves readability.

---

# Why Constructor Is Usually Private in Builder Pattern

The constructor is usually private so that objects cannot be created directly.

This forces the client to use the Builder.

It ensures controlled object creation.

---

# Why Builder Pattern Improves Readability

Compare:

## Without Builder

```java
Student s = new Student(
    101,
    "Nitya",
    21,
    "CSE"
);
```

## With Builder

```java
Student s = new Student.StudentBuilder()
                .setId(101)
                .setName("Nitya")
                .setAge(21)
                .setDepartment("CSE")
                .build();
```

The Builder version clearly shows which value belongs to which field.

---

# Real-Time Examples of Builder Pattern

# 1. StringBuilder in Java

Java’s `StringBuilder` class follows the Builder concept.

Example:

```java
StringBuilder sb = new StringBuilder();

sb.append("Hello")
  .append(" World");

System.out.println(sb);
```

Object construction happens step by step.

---

# 2. House Construction

A house may contain:

* Rooms
* Kitchen
* Garden
* Garage
* Swimming pool

Different customers may need different combinations.

Builder Pattern helps construct houses step by step.

---

# 3. Meal Ordering System

A meal may include:

* Burger
* Fries
* Coke
* Dessert

Customers choose items step by step.

Builder creates the final meal object.

---

# 4. Laptop Configuration

A laptop may contain:

* RAM
* Processor
* Graphics card
* SSD
* Keyboard type

Builder Pattern helps configure systems flexibly.

---

# 5. User Registration Form

A user may have:

* Name
* Email
* Phone number
* Address
* Profile picture

Some fields are optional.

Builder Pattern handles optional fields cleanly.

---

# Advantages of Builder Pattern

| Advantage                   | Explanation                             |
| --------------------------- | --------------------------------------- |
| Improves Readability        | Code becomes easier to understand       |
| Avoids Large Constructors   | No telescoping constructors             |
| Handles Optional Parameters | Optional fields are easy to manage      |
| Flexible Object Creation    | Objects can be built step by step       |
| Supports Immutable Objects  | Objects can remain unchangeable         |
| Cleaner Code                | Object creation logic becomes organized |

---

# Disadvantages of Builder Pattern

| Disadvantage          | Explanation                        |
| --------------------- | ---------------------------------- |
| More Code             | Builder class increases code size  |
| Additional Complexity | Small classes may not need Builder |
| Extra Classes         | Builder classes add more structure |

---

# Builder Pattern and Immutability

Builder Pattern is commonly used for immutable classes.

Immutable means:

* Object values cannot be changed after creation

Example:

* String class in Java

Builder creates the object once, and afterward the object remains fixed.

---

# Builder Pattern vs Constructor

| Constructor                    | Builder Pattern                |
| ------------------------------ | ------------------------------ |
| Difficult with many parameters | Easy to manage many parameters |
| Poor readability               | High readability               |
| Hard to manage optional values | Optional values handled easily |
| Parameter order matters        | Clear field names              |
| Constructors become large      | Cleaner structure              |

---

# Builder Pattern vs Factory Pattern

| Builder Pattern                     | Factory Pattern                     |
| ----------------------------------- | ----------------------------------- |
| Builds complex objects step by step | Creates objects based on conditions |
| Focuses on object construction      | Focuses on object selection         |
| Used for many fields                | Used for multiple object types      |
| Improves readability                | Reduces tight coupling              |

---

# When Should We Use Builder Pattern?

We should use Builder Pattern when:

* Objects have many parameters
* Some parameters are optional
* Constructor becomes very large
* Readability is important
* Immutable objects are required

---

# When Should We Not Use Builder Pattern?

We should not use Builder Pattern when:

* Objects are very simple
* Only a few fields exist
* Constructors are already simple

---

# Important Interview Points

## What Problem Does Builder Pattern Solve?

It solves the telescoping constructor problem.

---

## Why Does Builder Improve Readability?

Because field names are clearly visible during object creation.

---

## Why Is Method Chaining Used?

Method chaining allows multiple builder methods to be called continuously.

---

## Why Is Builder a Creational Pattern?

Because it deals with object creation.

---

# Interview Definition

> Builder Design Pattern is a creational design pattern used to construct complex objects step by step while separating object construction from representation.

---

# Conclusion

The Builder Design Pattern is used to create complex objects in a clean, readable, and flexible way.

It is especially useful when objects contain many fields or optional parameters.

Builder Pattern improves maintainability, readability, and object construction management.

Real-world examples include meal ordering systems, house construction, laptop configuration systems, and Java’s `StringBuilder` class.
