# Factory Design Pattern Theory in Java

## Introduction

The **Factory Design Pattern** is a **creational design pattern**. It is used to create objects without exposing the object creation logic to the client.

In simple words, the client does not create objects directly using the `new` keyword. Instead, the client asks a factory class to create the required object.

The factory decides which class object should be created and returns it to the client.

---

# Meaning of Factory Pattern

A factory means a place where products are created.

Similarly, in Java, a factory class is responsible for creating objects.

The client only tells the factory what type of object it wants. The factory creates the correct object and gives it back.

---

# Why Do We Use Factory Pattern?

We use the Factory Pattern when the object creation process should be separated from the main business logic.

It is useful when the exact class of object is not known until runtime.

For example, in an application, a user may choose payment by credit card, UPI, or PayPal. The program should create the correct payment object based on the user’s choice. This decision can be handled by a factory class.

---

# Main Purpose of Factory Pattern

The main purpose of the Factory Pattern is to **hide object creation logic**.

The client should not know which concrete class is being created internally.

The client should only depend on a common interface or abstract class.

This makes the application flexible, maintainable, and loosely coupled.

---

# What Problem Does Factory Pattern Solve?

Without Factory Pattern, object creation code may be spread across many classes.

For example:

```java
Payment payment = new CreditCardPayment();
```

This means the client class is directly dependent on `CreditCardPayment`.

If later we want to use `UPIPayment`, we must modify the client code.

This creates tight coupling.

Factory Pattern solves this problem by moving object creation logic into a separate factory class.

---

# Important Terms

## 1. Product

The product is the common interface or abstract class.

It defines the common behavior that all concrete classes must follow.

Example:

```java
interface Payment {
    void pay();
}
```

---

## 2. Concrete Product

Concrete products are actual classes that implement the product interface.

Example:

```java
class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Payment using Credit Card");
    }
}
```

```java
class UPIPayment implements Payment {
    public void pay() {
        System.out.println("Payment using UPI");
    }
}
```

---

## 3. Factory Class

The factory class contains the object creation logic.

It decides which object should be created based on input.

Example:

```java
class PaymentFactory {

    public Payment createPayment(String type) {

        if (type.equalsIgnoreCase("CREDIT")) {
            return new CreditCardPayment();
        }

        else if (type.equalsIgnoreCase("UPI")) {
            return new UPIPayment();
        }

        return null;
    }
}
```

---

## 4. Client

The client is the class that needs the object.

The client asks the factory for an object instead of creating it directly.

Example:

```java
PaymentFactory factory = new PaymentFactory();

Payment payment = factory.createPayment("UPI");

payment.pay();
```

---

# How Factory Pattern Works

The Factory Pattern works in the following way:

First, the client sends a request to the factory.

Then, the factory checks the given input or condition.

After that, the factory creates the required object.

Finally, the factory returns the object using a common interface or abstract class.

The client uses the returned object without knowing its actual class.

---

# Real-Time Example

Consider an online shopping application.

The customer can choose different payment methods such as:

* Credit Card
* UPI
* PayPal
* Net Banking

Each payment method has different internal logic.

However, all payment methods perform one common operation: payment.

So, we create a common `Payment` interface.

Each payment type implements that interface.

The factory class creates the correct payment object depending on the customer’s selection.

The client code does not need to know how each payment object is created.

---

# Why Factory Pattern Gives Loose Coupling

Factory Pattern gives loose coupling because the client depends on an interface, not on concrete classes.

Instead of writing:

```java
CreditCardPayment payment = new CreditCardPayment();
```

we write:

```java
Payment payment = factory.createPayment("CREDIT");
```

Here, `Payment` is an interface.

The client only knows about the `Payment` interface.

It does not directly depend on `CreditCardPayment` or `UPIPayment`.

This makes the code easier to change.

---

# Why Factory Pattern Improves Maintainability

Factory Pattern improves maintainability because object creation logic is kept in one place.

If object creation logic changes, we only update the factory class.

The client classes do not need to be changed.

For example, if a new payment type called `WalletPayment` is added, we create a new class and update the factory.

The rest of the application can continue using the same interface.

---

# Why Factory Pattern Supports Runtime Decision Making

Factory Pattern is useful when object type is decided at runtime.

Runtime means while the program is running.

For example, a user may select “UPI” on the payment page.

Based on that input, the factory creates a `UPIPayment` object.

If the user selects “Credit Card”, the factory creates a `CreditCardPayment` object.

So, the object type is not fixed at compile time. It is decided at runtime.

---

# Factory Pattern and Polymorphism

Factory Pattern uses polymorphism.

The factory returns a parent type such as an interface or abstract class.

The actual object may be a child class.

Example:

```java
Payment payment = new UPIPayment();
```

Here, `Payment` is the reference type, and `UPIPayment` is the actual object.

This is runtime polymorphism.

The method call:

```java
payment.pay();
```

will execute the `pay()` method of the actual object.

---

# Complete Example

```java
interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {

    public void pay() {
        System.out.println("Payment done using Credit Card");
    }
}

class UPIPayment implements Payment {

    public void pay() {
        System.out.println("Payment done using UPI");
    }
}

class PayPalPayment implements Payment {

    public void pay() {
        System.out.println("Payment done using PayPal");
    }
}

class PaymentFactory {

    public Payment createPayment(String type) {

        if (type == null) {
            return null;
        }

        if (type.equalsIgnoreCase("CREDIT")) {
            return new CreditCardPayment();
        }

        else if (type.equalsIgnoreCase("UPI")) {
            return new UPIPayment();
        }

        else if (type.equalsIgnoreCase("PAYPAL")) {
            return new PayPalPayment();
        }

        return null;
    }
}

public class Main {

    public static void main(String[] args) {

        PaymentFactory factory = new PaymentFactory();

        Payment payment = factory.createPayment("UPI");

        payment.pay();
    }
}
```

Output:

```java
Payment done using UPI
```

---

# Advantages of Factory Pattern

Factory Pattern reduces tight coupling between classes.

It hides object creation logic from the client.

It centralizes object creation in one place.

It makes code easier to maintain.

It supports runtime object creation.

It improves flexibility because new object types can be added easily.

It supports polymorphism by returning objects through a common interface.

---

# Disadvantages of Factory Pattern

Factory Pattern can increase the number of classes in the project.

For small applications, it may add unnecessary complexity.

If many conditions are added inside the factory, the factory class may become large.

Adding a new product type may still require modification in the factory class.

---

# When Should We Use Factory Pattern?

We should use Factory Pattern when the exact object type depends on input or conditions.

We should use it when object creation logic is complex.

We should use it when many classes implement the same interface.

We should use it when we want to hide object creation from the client.

We should use it when we want loose coupling between client and implementation classes.

---

# When Should We Not Use Factory Pattern?

We should not use Factory Pattern when the object creation is very simple.

We should not use it when there is only one class and no possibility of adding more types.

We should not use it when adding a factory makes the code unnecessarily complex.

---

# Real-Time Examples

## 1. Payment Gateway

An e-commerce application can use Factory Pattern to create payment objects like UPI, credit card, debit card, and PayPal.

## 2. Notification System

An application can create email, SMS, and push notification objects using a notification factory.

## 3. Vehicle Manufacturing

A vehicle factory can create car, bike, or truck objects based on customer requirements.

## 4. Database Connection

A database factory can create MySQL, Oracle, or PostgreSQL connection objects.

## 5. UI Components

A UI factory can create buttons, checkboxes, or dialog boxes depending on the operating system.

---

# Factory Pattern vs Direct Object Creation

| Direct Object Creation             | Factory Pattern                      |
| ---------------------------------- | ------------------------------------ |
| Client uses `new` keyword directly | Factory creates the object           |
| Client depends on concrete class   | Client depends on interface          |
| Tight coupling                     | Loose coupling                       |
| Harder to maintain                 | Easier to maintain                   |
| Less flexible                      | More flexible                        |
| Object creation logic is repeated  | Object creation logic is centralized |

---

# Interview Definition

Factory Design Pattern is a creational design pattern that provides a way to create objects without exposing the object creation logic to the client. It allows the client to use a common interface while the factory decides which concrete class object should be created.

---

# Conclusion

The Factory Design Pattern is used to separate object creation logic from business logic. It helps in creating objects based on conditions or runtime input.

It is especially useful when multiple classes implement the same interface and the client should not know which concrete class is being created.

Factory Pattern makes code flexible, maintainable, and loosely coupled.
