# Singleton Design Pattern in Java

## Introduction

The Singleton Design Pattern is a **creational design pattern** that ensures a class has **only one object** throughout the application and provides a **global access point** to that object.

In simple words, Singleton allows us to create only one instance of a class and use that same instance everywhere in the program.

---

# Why Do We Use Singleton?

We use Singleton when:

* Only one object is required in the entire application
* A shared resource must be controlled centrally
* Memory usage should be reduced
* Consistent data is required across all classes

Instead of creating multiple objects repeatedly, Singleton creates one object and shares it throughout the application.

---

# Real-Time Analogy

## Example: CEO of a Company

A company can have only one CEO.

If every employee creates a separate CEO object, confusion will occur because:

* Different decisions may be taken
* Data may become inconsistent

Therefore, all employees should communicate with the same CEO object.

This is the concept of Singleton.

---

# Main Characteristics of Singleton

The Singleton class contains:

1. A private static object of the same class
2. A private constructor
3. A public static method to access the object

---

# Why Do We Use a Static Variable in Singleton?

We use a static variable in Singleton because it must hold a single shared reference to the instance at the class level, allowing global access without creating multiple objects.

Since static members belong to the class rather than individual objects:

* The instance can be shared across the entire application
* Memory is saved because only one object is stored
* All classes access the same object reference

Without a static variable, every object would have its own separate instance variable, which would break the Singleton principle.

---

# Why Is the Constructor Private?

The constructor is made private so that no other class can create objects using the `new` keyword.

This restriction ensures that the class itself controls object creation.

---

# Why Is getInstance() Method Static?

The `getInstance()` method is static because we need to access the Singleton object without creating another object of the class.

If `getInstance()` were non-static, we would first need to create an object to call it, which would defeat the purpose of Singleton.

---

# Steps to Create Singleton Class

## Step 1: Make Constructor Private

The constructor is made private so that no other class can create an object using the `new` keyword.

## Step 2: Create Static Object

A static variable stores the single object of the class.

## Step 3: Provide Public Method

A public static method returns the single object whenever required.

---

# Basic Singleton Implementation

```java
class Singleton {

    // Static variable holds single shared object
    private static Singleton instance;

    // Private constructor prevents object creation
    private Singleton() {
        System.out.println("Singleton Object Created");
    }

    // Public static method provides global access
    public static Singleton getInstance() {

        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

---

# Usage Example

```java
public class Main {

    public static void main(String[] args) {

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2);
    }
}
```

---

# Output

```java
Singleton Object Created
true
```

The output is `true` because both variables refer to the same object.

---

# How Singleton Works

When `getInstance()` is called for the first time:

* The object does not exist
* A new object is created

When `getInstance()` is called again:

* The existing object is returned
* No new object is created

Thus, only one object exists throughout the application.

---

# Real-Time Examples of Singleton

# 1. Database Connection

## Why Singleton?

Database connections are expensive to create.

If multiple objects create separate database connections:

* Memory usage increases
* Performance decreases
* Database server overload may occur

Therefore, one database connection object is shared across the application.

---

## Example

```java
class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("Database Connected");
    }

    public static DatabaseConnection getInstance() {

        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
```

---

# 2. Logger Class

## Why Singleton?

A logger records application activities.

If multiple logger objects exist:

* Different log files may be created
* Logs may become inconsistent

Therefore, one logger object should manage all logs.

---

## Example

```java
class Logger {

    private static Logger logger;

    private Logger() {
    }

    public static Logger getInstance() {

        if (logger == null) {
            logger = new Logger();
        }

        return logger;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
```

---

# 3. Printer Spooler

In an office:

* Multiple users send print requests
* One printer manages all requests

If multiple printer manager objects exist:

* Print conflicts may occur
* Pages may print incorrectly

Therefore, Singleton is used.

---

# 4. Configuration Manager

Applications store:

* Database URL
* API keys
* System settings

Loading these configurations repeatedly wastes memory.

Singleton allows one configuration object to be shared everywhere.

---

# 5. Cache Manager

Applications use cache to store frequently used data.

If multiple cache objects exist:

* Duplicate data may occur
* Synchronization problems may happen

Singleton provides centralized cache management.

---

# Types of Singleton Implementation

# 1. Eager Initialization

In eager initialization, the object is created when the class is loaded.

```java
class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```

## Advantages

* Simple
* Thread-safe

## Disadvantage

* Object is created even if not used

---

# 2. Lazy Initialization

In lazy initialization, the object is created only when needed.

```java
class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

## Advantage

* Saves memory

## Disadvantage

* Not thread-safe

---

# Problem in Multithreading

Suppose two threads call `getInstance()` simultaneously.

Both threads may create separate objects.

This breaks the Singleton principle.

---

# Thread-Safe Singleton

```java
class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {

        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

## Explanation

`synchronized` allows only one thread to access the method at a time.

This prevents multiple object creation.

---

# Disadvantage of Synchronized Method

Synchronization reduces performance because every thread waits even after the object is created.

---

# Double-Checked Locking

This is an optimized thread-safe Singleton.

```java
class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (instance == null) {

            synchronized (Singleton.class) {

                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
```

---

# Why Double-Checked Locking?

* Synchronization occurs only once
* Improves performance
* Ensures thread safety

---

# Singleton Using Enum

```java
enum Singleton {

    INSTANCE;

    public void showMessage() {
        System.out.println("Singleton using Enum");
    }
}
```

Usage:

```java
public class Main {

    public static void main(String[] args) {

        Singleton.INSTANCE.showMessage();
    }
}
```

---

# Why Enum Singleton Is Best

Enum Singleton:

* Is thread-safe
* Prevents reflection attacks
* Prevents serialization issues
* Is simple to implement

---

# Advantages of Singleton

| Advantage                 | Explanation                              |
| ------------------------- | ---------------------------------------- |
| Saves Memory              | Only one object is created               |
| Better Performance        | Repeated object creation is avoided      |
| Shared Access             | Same object used everywhere              |
| Consistent Data           | All classes use same data                |
| Controlled Resource Usage | Useful for database and cache management |

---

# Disadvantages of Singleton

| Disadvantage                             | Explanation                                         |
| ---------------------------------------- | --------------------------------------------------- |
| Difficult Testing                        | Global state makes unit testing harder              |
| Tight Coupling                           | Classes become dependent on Singleton               |
| Multithreading Issues                    | Improper implementation may create multiple objects |
| Violates Single Responsibility Principle | Class manages both logic and object creation        |

---

# Real-Time Areas Where Singleton Is Used

| Application            | Singleton Example     |
| ---------------------- | --------------------- |
| Banking System         | Database connection   |
| E-commerce Application | Logger                |
| Gaming Application     | Game manager          |
| Operating System       | Printer spooler       |
| Web Application        | Cache manager         |
| Mobile Application     | Configuration manager |

---

# Important Interview Points

## Why Constructor Is Private?

The constructor is private to prevent object creation from outside the class.

---

## Why Object Is Static?

We use a static variable because it stores one shared object at the class level, allowing all classes to access the same instance without creating new objects.

---

## Why getInstance() Method Is Static?

The method is static because the Singleton object must be accessed without creating another object of the class.

---

# Final Definition

> Singleton is a creational design pattern that ensures only one instance of a class exists throughout the application and provides a global point of access to that instance.

---

# Conclusion

The Singleton Design Pattern is widely used in Java applications where only one shared object is needed. It improves memory usage, provides centralized control, and ensures consistent behavior across the application. Common real-world examples include database connections, loggers, cache managers, and configuration managers.

Although Singleton is useful, it should be used carefully because excessive use may reduce flexibility and make testing difficult.
