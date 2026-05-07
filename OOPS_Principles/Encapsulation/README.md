# Encapsulation in Object-Oriented Programming (OOP)

## Introduction

Encapsulation is one of the most important principles of Object-Oriented Programming (OOP). It is used to combine data and the methods that operate on that data into a single unit called a class. At the same time, encapsulation protects the data from unauthorized access.

In simple words:

> **Encapsulation means wrapping data and methods together into one unit and restricting direct access to the data.**

Encapsulation helps in:

* Data security
* Controlled access
* Better code organization
* Improved maintainability

---

# Encapsulation Formula

```text id="0qhfho"
Encapsulation = Data Hiding + Abstraction
```

---

# Understanding Encapsulation Step by Step

Encapsulation mainly consists of two important concepts:

1. Data Hiding
2. Abstraction

---

# 1. Data Hiding

Data hiding means restricting direct access to the data members of a class.

This is achieved using access specifiers like:

* private
* protected
* public

Most commonly, `private` is used for data hiding.

---

## Example of Data Hiding

```java id="j1o2d4"
class Student {

    private int age;
}
```

### Explanation

Here:

* `age` is private.
* It can only be accessed inside the `Student` class.
* Outside classes cannot directly access or modify it.

Wrong Approach:

```java id="4i3a9f"
Student s = new Student();

s.age = 20; // Compilation Error
```

This restriction improves security.

---

# 2. Abstraction

Abstraction means hiding internal implementation details and showing only the necessary features to the user.

### Real-Life Examples

* ATM Machine
* Car Driving
* Mobile Phone

You use these systems without knowing their internal working.

Encapsulation uses abstraction to provide a simple and controlled interface.

---

# Real-Life Example 1 — Capsule Medicine

A capsule used for curing fever contains different medicinal compositions grouped together inside a single capsule.

For example:

* Different chemicals work together to cure fever.
* The user only sees the capsule, not the internal compositions.

Similarly in programming:

* Data and operations related to that data are grouped into one class.

This grouping is called Encapsulation.

---

# Real-Life Example 2 — Car

A car contains:

* Engine
* Wheels
* Steering
* Gear system
* Brake system

All these parts are grouped into one body to form a complete car.

Imagine if:

* Wheels were separated
* Engine was outside
* Steering was disconnected

The car would not work properly.

Similarly in OOP:

* All operations related to specific data should remain grouped together inside a class.

This is Encapsulation.

---

# Access Specifiers in Java

Access specifiers are keywords used to define the accessibility or visibility of data members and methods in a class.
They help in implementing encapsulation and data hiding.

There are four main access specifiers in Java:

| Access Specifier | Description                                 |
| ---------------- | ------------------------------------------- |
| private          | Accessible only inside the same class       |
| default          | Accessible within the same package          |
| protected        | Accessible within package and child classes |
| public           | Accessible from anywhere                    |

---

# 1. Private Access Specifier

The private access specifier allows access only within the same class.

1.It provides maximum security.
2.It is mainly used for data hiding.

## Example

```java id="nqegqh"
class Student {

    private int age;
}
```

### Explanation

* age can only be accessed inside the Student class.
* Outside classes cannot access it directly.
* Access is provided through getter and setter methods.

---

# 2. Public Access Specifier

The public access specifier allows access from anywhere in the program.

## Example

```java id="mjlwm9"
class Student {

    public String name;
}
```

### Explanation

```java id="y0vmgh"
Student s = new Student();

s.name = "John";
```

Public members are accessible everywhere.

---

# 3. Protected Access Specifier

Protected members can be accessed:

* Within the same class
* Within the same package
* Inside child classes

## Example

```java id="wwl9vf"
class Parent {

    protected int value = 100;
}

class Child extends Parent {

    void display() {
        System.out.println(value);
    }
}
```

---

# 4. Default Access Specifier

If no access specifier is mentioned, Java uses default access.

## Example

```java id="fd14v4"
class Student {

    int age;
}
```

### Explanation

* Accessible only inside the same package.

---

# Access Specifier Table

| Access Specifier | Same Class | Same Package | Child Class | Outside Package |
| ---------------- | ---------- | ------------ | ----------- | --------------- |
| private          | YES        | NO           | NO          | NO              |
| default          | YES        | YES          | NO          | NO              |
| protected        | YES        | YES          | YES         | NO              |
| public           | YES        | YES          | YES         | YES             |

---

# Encapsulation Using Getter and Setter Methods

Encapsulation is usually implemented using:

* Getter methods
* Setter methods

---

# Getter Method

A getter method is used to read data.

## Example

```java id="0c0jca"
public int getAge() {
    return age;
}
```

---

# Setter Method

A setter method is used to modify data safely.

## Example

```java id="7qj4h3"
public void setAge(int age) {

    if(age > 0) {
        this.age = age;
    }
}
```

### Explanation

* Validation can be added before updating data.
* Invalid values can be prevented.

---

# Complete Programmatic Explanation of Encapsulation

## Program

```java id="2r72tg"
class Student {

    // Private variable (Data Hiding)
    private int age;

    // Setter Method
    public void setAge(int age) {

        if(age > 0) {
            this.age = age;
        }
        else {
            System.out.println("Invalid Age");
        }
    }

    // Getter Method
    public int getAge() {
        return age;
    }
}

public class Main {

    public static void main(String[] args) {

        Student s = new Student();

        // Setting value using setter
        s.setAge(20);

        // Getting value using getter
        System.out.println("Age: " + s.getAge());
    }
}
```

---

# Step-by-Step Program Explanation

---

## Step 1 — Private Variable

```java id="08wzgd"
private int age;
```

### Explanation

* `age` is hidden from outside classes.
* Direct access is not allowed.

---

## Step 2 — Setter Method

```java id="6l6u3n"
public void setAge(int age)
```

### Purpose

Used to modify the value safely.

---

## Step 3 — Validation

```java id="b91z7r"
if(age > 0)
```

### Explanation

* Prevents invalid values.
* Only positive ages are accepted.

---

## Step 4 — Getter Method

```java id="q8zwot"
public int getAge()
```

### Purpose

Used to read data safely.

---

## Step 5 — Object Creation

```java id="q4gq7f"
Student s = new Student();
```

Creates an object of the class.

---

## Step 6 — Access Through Methods

```java id="d2ns3h"
s.setAge(20);

System.out.println(s.getAge());
```

### Explanation

* Data is accessed indirectly through methods.
* This provides controlled access.

---

# Output

```text id="r9t2v2"
Age: 20
```

---

# Working Flow of Encapsulation

```text id="ye4jrr"
User
   |
   v
Getter / Setter Methods
   |
   v
Private Data
```

The user cannot directly access private data.

---

# Encapsulation in LinkedList Example

## Example

```java id="xaq3e1"
class LinkedList {

    private class Node {

        int data;
        Node next;
    }

    public void addNode() {}

    public void deleteNode() {}

    public void searchNode() {}
}
```

---

# Explanation

* `Node` is hidden using private access.
* All operations related to LinkedList are grouped into one class.
* This improves organization and security.

---

# Features of Encapsulation

## 1. Data Security

Protects sensitive data.

---

## 2. Controlled Access

Data can only be modified through authorized methods.

---

## 3. Better Code Organization

Related data and methods stay together.

---

## 4. Easy Maintenance

Internal changes do not affect external code.

---

## 5. Flexibility

Validation can easily be added.

---

# Advantages of Encapsulation

* Improves security
* Prevents accidental modifications
* Makes code reusable
* Improves maintainability
* Reduces complexity

---

# Disadvantages of Encapsulation

* Requires additional methods
* Slightly increases code size

However, the advantages are much greater.

---

# Difference Between Encapsulation and Data Hiding

| Encapsulation                      | Data Hiding                       |
| ---------------------------------- | --------------------------------- |
| Wrapping data and methods together | Restricting direct access to data |
| Achieved using classes             | Achieved using private keyword    |
| Broad OOP concept                  | Part of encapsulation             |

---

# Difference Between Encapsulation and Abstraction

| Encapsulation                    | Abstraction                                |
| -------------------------------- | ------------------------------------------ |
| Focuses on data security         | Focuses on hiding complexity               |
| Achieved using access specifiers | Achieved using abstract classes/interfaces |
| Protects data                    | Simplifies usage                           |

---

# Important Interview Questions

---

## 1. What is Encapsulation?

Encapsulation is the process of binding data and methods into a single unit while restricting direct access to data.

---

## 2. How is Encapsulation achieved in Java?

Using:

* Classes
* Private variables
* Getter and Setter methods

---

## 3. Why are variables declared private?

To protect data from unauthorized access.

---

## 4. What is Data Hiding?

Restricting direct access to data using access specifiers.

---

## 5. What is the difference between Encapsulation and Abstraction?

Encapsulation hides data while abstraction hides implementation details.

---

## 6. Which access specifier provides maximum security?

`private`

---

## 7. Which access specifier provides maximum accessibility?

`public`

---

# Tricky Interview Questions

---

## 1. Is Encapsulation only about making variables private?

No.

Encapsulation also includes:

* Grouping data and methods together
* Providing controlled access

---

## 2. Can Encapsulation exist without Data Hiding?

Yes, but proper encapsulation usually includes data hiding.

---

## 3. Can private methods be inherited?

No.

Private methods are not accessible outside the class.

---

## 4. Why use Setter methods instead of public variables?

Setter methods:

* Validate data
* Prevent invalid values
* Provide controlled access

---

## 5. What happens if all variables are public?

* Data becomes unsafe
* Invalid modifications become possible
* Security decreases

---

# Final Definition

> Encapsulation is the process of binding data members and methods into a single unit while restricting direct access to data using access specifiers and controlled methods.

---

# Conclusion

Encapsulation is one of the most important concepts in Object-Oriented Programming. It combines data and methods into a single unit and protects the data from direct access. By using access specifiers, getter methods, and setter methods, encapsulation improves security, code organization, maintainability, and flexibility. It is widely used in real-world software development to create secure and well-structured applications.

