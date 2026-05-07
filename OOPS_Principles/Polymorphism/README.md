# Polymorphism in Object-Oriented Programming (OOP)

## Introduction

Polymorphism is one of the most important principles of Object-Oriented Programming. It allows the same object, method, or reference to behave in different ways depending on the situation.

The word **Polymorphism** is made from two words:

```text
Poly  = Many
Morph = Forms
```

So, polymorphism means:

> **Many forms**

In simple words:

> **Polymorphism means one name with multiple forms or one action behaving differently in different situations.**

---

# Definition of Polymorphism

> **Polymorphism is the ability of an object, method, or reference variable to take multiple forms.**

OR

> **Polymorphism allows the same method or same reference to behave differently based on the object or parameters used.**

---

# Why Polymorphism is Needed

Polymorphism is used to make programs more:

* Flexible
* Reusable
* Scalable
* Maintainable
* Easy to extend

It allows us to write common code that can work with different types of objects.

---

# Real-Life Example 1 — Television

Suppose we have different brands of televisions:

* Samsung TV
* LG TV
* Sony TV
* Xiaomi TV

Even though these televisions are from different brands, we commonly call all of them:

```text
Television
```

So, one common name **Television** represents many different forms.

This is polymorphism.

In programming, this means a parent class reference can refer to different child class objects.

---

# Real-Life Example 2 — Person

A single person can behave differently in different situations:

* At home → Son/Daughter
* In college → Student
* In office → Employee
* With friends → Friend

The person is the same, but the role changes according to the situation.

This is also polymorphism.

---

# Real-Life Example 3 — Payment

A payment can be done using:

* Credit Card
* Debit Card
* UPI
* Net Banking
* Cash

The action is the same:

```text
pay()
```

But the implementation is different for each payment type.

This is polymorphism.

---

# Polymorphism in Programming

In programming, polymorphism can happen in two major ways:

1. **Object polymorphism**
2. **Method polymorphism**

---

# 1. Object Polymorphism

Object polymorphism means a parent class reference can refer to a child class object.

Example:

```java
class Television {

    void turnOn() {
        System.out.println("Television is ON");
    }

    void turnOff() {
        System.out.println("Television is OFF");
    }
}

class SamsungTV extends Television {

    void browseInternet() {
        System.out.println("Browsing internet on Samsung TV");
    }
}
```

Object creation:

```java
Television tv = new SamsungTV();
```

Here:

```text
Reference Type = Television
Object Type    = SamsungTV
```

This is polymorphism because a `SamsungTV` object is being treated as a general `Television`.

We can say:

```text
SamsungTV IS-A Television
```

So this is valid.

---

# Reference Type and Object Type

This is very important in polymorphism.

Consider:

```java
Television tv = new SamsungTV();
```

## Reference Type

The class written on the left side is called the **reference type**.

```java
Television tv
```

Here, `Television` is the reference type.

## Object Type

The class written on the right side after `new` is called the **object type**.

```java
new SamsungTV()
```

Here, `SamsungTV` is the object type.

---

# Important Rule

> **Reference type decides which methods are accessible.**

> **Object type decides which overridden method will execute.**

Example:

```java
Television tv = new SamsungTV();

tv.turnOn();        // Allowed
tv.turnOff();       // Allowed
tv.browseInternet(); // Error
```

Why is `browseInternet()` an error?

Because the reference type is `Television`, and `Television` does not know about `browseInternet()`.

Even though the actual object is `SamsungTV`, the compiler checks the reference type first.

---
Below are the **missing parts you can add** to your existing Polymorphism notes while maintaining the same format and explanation style.

---

# Additional Concepts to Add in Polymorphism

---

# Reference Type, Object Type, Variables, and Methods

In polymorphism, it is very important to understand how Java handles:

* Reference type
* Object type
* Variables
* Instance methods
* Static methods

Consider this example:

```java
class Parent {

    int x = 10;

    void show() {
        System.out.println("Parent's show");
    }

    static void staticShow() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {

    int x = 20;

    @Override
    void show() {
        System.out.println("Child's show");
    }

    static void staticShow() {
        System.out.println("Child static method");
    }
}
```

---

# Case 1 — Parent Reference and Parent Object

```java
Parent p = new Parent();

System.out.println(p.x);
p.show();
p.staticShow();
```

Output:

```text
10
Parent's show
Parent static method
```

Explanation:

* Reference Type = Parent
* Object Type = Parent
* Variable accessed = Parent’s `x`
* Method called = Parent’s `show()`
* Static method called = Parent’s `staticShow()`

---

# Case 2 — Parent Reference and Child Object

```java
Parent p = new Child();

System.out.println(p.x);
p.show();
p.staticShow();
```

Output:

```text
10
Child's show
Parent static method
```

Explanation:

Here:

```java
Parent p = new Child();
```

Reference Type is:

```text
Parent
```

Object Type is:

```text
Child
```

Now:

```java
System.out.println(p.x);
```

prints:

```text
10
```

Because variables are resolved based on reference type.

But:

```java
p.show();
```

prints:

```text
Child's show
```

Because instance methods are resolved based on object type during runtime.

And:

```java
p.staticShow();
```

prints:

```text
Parent static method
```

Because static methods are resolved based on reference type.

---

# Case 3 — Child Reference and Child Object

```java
Child c = new Child();

System.out.println(c.x);
c.show();
c.staticShow();
```

Output:

```text
20
Child's show
Child static method
```

Explanation:

* Reference Type = Child
* Object Type = Child
* Variable accessed = Child’s `x`
* Method called = Child’s `show()`
* Static method called = Child’s `staticShow()`

---

# Variable and Method Resolution Table

| Reference Type | Object Type | Accessed Variable | Called Method             |
| -------------- | ----------- | ----------------- | ------------------------- |
| Parent         | Parent      | Parent’s `x = 10` | Parent’s method           |
| Parent         | Child       | Parent’s `x = 10` | Child’s overridden method |
| Child          | Child       | Child’s `x = 20`  | Child’s method            |

---

# Important Point

```text
Variables are resolved at compile time based on reference type.

Methods are resolved at runtime based on object type.
```

This is one of the most important rules of polymorphism.

---

# Feature Resolution Table

| Feature         | Polymorphic? | Resolved At  | Based On       |
| --------------- | ------------ | ------------ | -------------- |
| Instance Method | Yes          | Runtime      | Object Type    |
| Static Method   | No           | Compile-time | Reference Type |
| Variable        | No           | Compile-time | Reference Type |

---

# If Methods Are Static

Static methods are not overridden.

They are hidden.

This is called:

```text
Method Hiding
```

Example:

```java
class Parent {

    static void show() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {

    static void show() {
        System.out.println("Child static method");
    }
}
```

Usage:

```java
Parent p = new Child();

p.show();
```

Output:

```text
Parent static method
```

---

# Why?

Because static methods belong to the class, not to the object.

So Java resolves static methods using reference type.

Here reference type is:

```text
Parent
```

So Parent’s static method is called.

---

# Static Method Resolution Table

| Reference Type | Object Type | Method Called           |
| -------------- | ----------- | ----------------------- |
| Parent         | Parent      | `Parent.staticMethod()` |
| Parent         | Child       | `Parent.staticMethod()` |
| Child          | Child       | `Child.staticMethod()`  |

---

# Complete Resolution Table

| What               | Compile-time | Runtime | Resolved Based On | Notes                                     |
| ------------------ | ------------ | ------- | ----------------- | ----------------------------------------- |
| `instanceVar`      | Yes          | No      | Reference Type    | No polymorphism for variables             |
| `staticVar`        | Yes          | No      | Reference Type    | Static fields are not polymorphic         |
| `instanceMethod()` | No           | Yes     | Object Type       | Dynamic method dispatch                   |
| `staticMethod()`   | Yes          | No      | Reference Type    | Static methods are hidden, not overridden |

---

# Flow of Execution in Runtime Polymorphism

Consider this program:

```java
class Parent {

    void show() {
        System.out.println("Parent's show");
    }
}

class Child extends Parent {

    @Override
    void show() {
        System.out.println("Child's show");
    }
}

public class Main {

    public static void main(String[] args) {

        Parent p = new Child();

        p.show();
    }
}
```

Output:

```text
Child's show
```

---

# Step 1 — Compile-Time Check

The compiler looks at the reference type.

```java
Parent p
```

Compiler checks:

```text
Does Parent class have show() method?
```

Answer:

```text
Yes
```

So the code compiles successfully.

---

# Important Rule

> At compile time, Java checks method availability using reference type.

---

# Step 2 — Runtime Execution

At runtime:

```java
p
```

is actually pointing to:

```java
new Child()
```

So JVM checks the actual object type.

Actual object type is:

```text
Child
```

Now JVM checks:

```text
Does Child override show()?
```

Answer:

```text
Yes
```

So JVM calls:

```java
Child.show()
```

Output:

```text
Child's show
```

---

# Internal Flow

```text
p.show() is called

Compiler checks:
Does Parent have show()?
Yes → Compile successful

Runtime JVM checks:
Actual object is Child

Does Child override show()?
Yes → Call Child's show()
```

---

# Visual Summary

```text
Reference Type = Parent
Object Type    = Child

[Compile-Time]
Does Parent have show()? → Yes → Compile OK

[Runtime]
Actual object = Child
Does Child override show()? → Yes
Call Child's show()
```

---

# What If Child Does Not Override show()?

```java
class Parent {

    void show() {
        System.out.println("Parent's show");
    }
}

class Child extends Parent {

}
```

Usage:

```java
Parent p = new Child();

p.show();
```

Output:

```text
Parent's show
```

Explanation:

* Compiler checks `Parent`.
* `show()` exists in `Parent`.
* Runtime checks `Child`.
* `Child` does not override `show()`.
* So JVM calls Parent’s `show()` method.

---

# What If Parent Does Not Have the Method?

```java
class Parent {

}

class Child extends Parent {

    void show() {
        System.out.println("Child's show");
    }
}
```

Usage:

```java
Parent p = new Child();

p.show();
```

Output:

```text
Compile-time error
```

Explanation:

Even though the actual object is `Child`, the compiler first checks the reference type.

Reference type is:

```text
Parent
```

Parent does not have the `show()` method.

So compilation fails.

---

# Final Takeaway

| Step         | What It Checks                          | Based On       |
| ------------ | --------------------------------------- | -------------- |
| Compile-time | Does the method exist?                  | Reference Type |
| Runtime      | Which overridden method should execute? | Object Type    |

---

# Most Important Line

```text
Reference type decides what can be accessed.

Object type decides which overridden instance method will execute.
```

---

# Dynamic Method Dispatch

Dynamic Method Dispatch is the process by which JVM decides at runtime which overridden method should be called.

Example:

```java
Animal a = new Dog();

a.sound();
```

Here:

* Reference Type = Animal
* Object Type = Dog

Compiler checks:

```text
Does Animal have sound()?
```

Runtime checks:

```text
Actual object is Dog.
Does Dog override sound()?
```

So Dog’s `sound()` method executes.

This is called:

```text
Dynamic Method Dispatch
```

---

# Types of Polymorphism

There are mainly two types of polymorphism:

```text
1. Compile-Time Polymorphism
2. Runtime Polymorphism
```

---

# 1. Compile-Time Polymorphism

Compile-time polymorphism means the method call is decided during compilation.

It is also called:

```text
Static Binding
Early Binding
```

Compile-time polymorphism is achieved using:

```text
Method Overloading
```

---

# Method Overloading

Method overloading means:

> **Defining multiple methods with the same name but different parameters in the same class.**

The method name remains the same, but the method signature changes.

---

# Method Signature

A method signature includes:

* Method name
* Number of parameters
* Type of parameters
* Order of parameters

Return type alone is not part of method signature.

---

# Example of Method Overloading

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

Usage:

```java
public class Main {

    public static void main(String[] args) {

        Calculator c = new Calculator();

        System.out.println(c.add(10, 20));
        System.out.println(c.add(10, 20, 30));
        System.out.println(c.add(10.5, 20.5));
    }
}
```

Output:

```text
30
60
31.0
```

---

# Explanation

Here, the method name is the same:

```java
add()
```

But parameters are different:

```java
add(int, int)
add(int, int, int)
add(double, double)
```

So this is method overloading.

The compiler decides which method to call based on the arguments passed.

---

# Why Overloading is Compile-Time Polymorphism

Example:

```java
c.add(10, 20);
```

Here, the compiler sees:

```text
10 and 20 are integers
```

So it selects:

```java
add(int, int)
```

This decision is made at compile time.

That is why method overloading is called compile-time polymorphism.

---

# Valid Ways to Overload Methods

## 1. By changing number of parameters

```java
void show(int a) {}

void show(int a, int b) {}
```

## 2. By changing data type of parameters

```java
void show(int a) {}

void show(double a) {}
```

## 3. By changing order of parameters

```java
void show(int a, String b) {}

void show(String a, int b) {}
```

---

# Invalid Method Overloading

Methods cannot be overloaded only by changing return type.

Wrong:

```java
int add(int a, int b) {
    return a + b;
}

double add(int a, int b) {
    return a + b;
}
```

This gives an error because the parameter list is the same.

---

# 2. Runtime Polymorphism

Runtime polymorphism means the method call is decided during program execution.

It is also called:

```text
Dynamic Binding
Late Binding
```

Runtime polymorphism is achieved using:

```text
Method Overriding
```

---

# Method Overriding

Method overriding means:

> **A child class provides its own implementation of a method that is already defined in the parent class.**

For method overriding:

* Method name must be same
* Parameters must be same
* Return type must be same or covariant
* Inheritance is required

---

# Example of Method Overriding

```java
class Animal {

    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

Usage:

```java
public class Main {

    public static void main(String[] args) {

        Animal a = new Dog();

        a.sound();
    }
}
```

Output:

```text
Dog barks
```

---

# Explanation

Here:

```java
Animal a = new Dog();
```

Reference type is:

```text
Animal
```

Object type is:

```text
Dog
```

During compilation:

* Compiler checks whether `sound()` exists in `Animal`.
* It exists, so compilation succeeds.

During runtime:

* JVM checks the actual object.
* Actual object is `Dog`.
* So `Dog` class `sound()` method executes.

This is runtime polymorphism.

---

# Runtime Binding / Dynamic Binding

Runtime binding means:

> **The method call is connected to the actual method implementation during runtime.**

Example:

```java
Animal a = new Dog();
a.sound();
```

Here, the method call is resolved at runtime because the JVM checks the actual object type.

Actual object:

```text
Dog
```

So:

```java
Dog.sound()
```

executes.

---

# Compile-Time Binding / Static Binding

Compile-time binding means:

> **The method call is connected to the method implementation during compilation.**

Example:

```java
Calculator c = new Calculator();
c.add(10, 20);
```

The compiler already knows that both arguments are integers.

So it selects:

```java
add(int, int)
```

This is compile-time binding.

---

# Compile-Time Binding vs Runtime Binding

| Compile-Time Binding                | Runtime Binding                |
| ----------------------------------- | ------------------------------ |
| Method call decided at compile time | Method call decided at runtime |
| Also called static binding          | Also called dynamic binding    |
| Achieved by method overloading      | Achieved by method overriding  |
| Compiler decides the method         | JVM decides the method         |
| Faster                              | Slightly slower                |
| Based on reference and parameters   | Based on actual object         |

---

# Overloading vs Overriding

| Method Overloading                                | Method Overriding                     |
| ------------------------------------------------- | ------------------------------------- |
| Same method name, different parameters            | Same method name, same parameters     |
| Happens in same class                             | Happens in parent-child classes       |
| Inheritance is not required                       | Inheritance is required               |
| Compile-time polymorphism                         | Runtime polymorphism                  |
| Static binding                                    | Dynamic binding                       |
| Return type can be different if parameters differ | Return type must be same or covariant |

---

# Complete Program Showing Both Types

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

class Animal {

    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {

    public static void main(String[] args) {

        Calculator c = new Calculator();

        System.out.println(c.add(10, 20));
        System.out.println(c.add(10, 20, 30));

        Animal a = new Dog();
        a.sound();
    }
}
```

Output:

```text
30
60
Dog barks
```

Explanation:

* `add()` is overloaded, so it shows compile-time polymorphism.
* `sound()` is overridden, so it shows runtime polymorphism.

---
# Rules of Method Overloading

## 1. Method name must be same

```java
add()
```

## 2. Parameters must be different

Difference can be in:

* Number of parameters
* Type of parameters
* Order of parameters

## 3. Return type alone is not enough

Changing only return type is invalid.

## 4. Access modifiers can be different

Example:

```java
public void show(int a) {}

private void show(double a) {}
```

## 5. Static methods can be overloaded

Example:

```java
static void show(int a) {}

static void show(String a) {}
```

## 6. Constructors can be overloaded

Example:

```java
class Student {

    Student() {}

    Student(String name) {}
}
```

# Important Rules of Method Overriding

## 1. Method name must be same

```java
void sound()
```

must be same in parent and child.

## 2. Parameters must be same

```java
void sound()
```

If parameters change, it becomes overloading, not overriding.

## 3. Inheritance is required

Without inheritance, overriding is not possible.

## 4. Private methods cannot be overridden

Private methods are not inherited.

## 5. Final methods cannot be overridden

Final means fixed.

## 6. Static methods cannot be overridden

Static methods belong to the class, not object.

They can be hidden, but not overridden.

## 7. Constructors cannot be overridden

Constructors are not inherited.

---

# Static Method Hiding

Static methods are not overridden.

Example:

```java
class Parent {

    static void show() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {

    static void show() {
        System.out.println("Child static method");
    }
}

public class Main {

    public static void main(String[] args) {

        Parent p = new Child();

        p.show();
    }
}
```

Output:

```text
Parent static method
```

Why?

Because static methods are resolved using reference type, not object type.

So this is method hiding, not overriding.

---

# Upcasting in Polymorphism

Upcasting means:

> **Assigning a child class object to a parent class reference.**

Example:

```java
Animal a = new Dog();
```

Here:

* `Dog` object is assigned to `Animal` reference.

Upcasting is automatic.

It is used for runtime polymorphism.

---

# Downcasting in Polymorphism

Downcasting means:

> **Converting a parent class reference back to a child class reference.**

Example:

```java
Animal a = new Dog();

Dog d = (Dog) a;
d.sound();
```

Downcasting is required when we want to access child-specific methods.

Example:

```java
class Dog extends Animal {

    void eat() {
        System.out.println("Dog eats bones");
    }
}
```

```java
Animal a = new Dog();

Dog d = (Dog) a;
d.eat();
```

---
# Why Downcasting Is Needed

This will give an error:

```java
Animal a = new Dog();

a.eat(); // Error
```

Because reference type is `Animal`, and `Animal` does not contain `eat()`.

So we downcast:

```java
Dog d = (Dog) a;

d.eat();
```

---
# Important Note About Downcasting

Wrong downcasting can cause runtime error.

Example:

```java
Animal a = new Animal();

Dog d = (Dog) a; // Runtime error
```

To avoid this, use `instanceof`.

```java
if (a instanceof Dog) {
    Dog d = (Dog) a;
}
```

---

# Polymorphism with Arrays

Polymorphism allows storing different child objects in a parent type array.

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
}

class Cat extends Animal {

    void sound() {
        System.out.println("Cat meows");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal[] animals = {
            new Dog(),
            new Cat(),
            new Animal()
        };

        for (Animal a : animals) {
            a.sound();
        }
    }
}
```

Output:

```text
Dog barks
Cat meows
Animal sound
```

This is a powerful use of runtime polymorphism.

---

# Polymorphism with Interfaces

Polymorphism can also be achieved using interfaces.

```java
interface Payment {

    void pay();
}

class CreditCardPayment implements Payment {

    public void pay() {
        System.out.println("Payment using Credit Card");
    }
}

class UPIPayment implements Payment {

    public void pay() {
        System.out.println("Payment using UPI");
    }
}

public class Main {

    public static void main(String[] args) {

        Payment p;

        p = new CreditCardPayment();
        p.pay();

        p = new UPIPayment();
        p.pay();
    }
}
```

Output:

```text
Payment using Credit Card
Payment using UPI
```

Explanation:

```java
Payment p = new CreditCardPayment();
Payment p = new UPIPayment();
```

Same reference type `Payment` points to different object types.

This is polymorphism.

---

# Real-World Programmatic Example — Payment System

```java
class Payment {

    void pay() {
        System.out.println("Generic payment");
    }
}

class CreditCard extends Payment {

    @Override
    void pay() {
        System.out.println("Payment done using Credit Card");
    }
}

class UPI extends Payment {

    @Override
    void pay() {
        System.out.println("Payment done using UPI");
    }
}

class NetBanking extends Payment {

    @Override
    void pay() {
        System.out.println("Payment done using Net Banking");
    }
}

public class Main {

    public static void main(String[] args) {

        Payment payment;

        payment = new CreditCard();
        payment.pay();

        payment = new UPI();
        payment.pay();

        payment = new NetBanking();
        payment.pay();
    }
}
```

Output:

```text
Payment done using Credit Card
Payment done using UPI
Payment done using Net Banking
```

Here:

* Same reference `payment`
* Different objects
* Different behavior

This is polymorphism.

---
# Covariant Return Type in Overriding

In method overriding, return type should be same or covariant.

Covariant return type means:

> The child class overriding method can return a child type of the parent method’s return type.

Example:

```java
class Animal {

}

class Dog extends Animal {

}

class Parent {

    Animal getAnimal() {
        return new Animal();
    }
}

class Child extends Parent {

    @Override
    Dog getAnimal() {
        return new Dog();
    }
}
```

This is allowed because:

```text
Dog IS-A Animal
```

So `Dog` is a covariant return type of `Animal`.

---
# Benefits of Polymorphism

## 1. Code Reusability

Same code can work with different objects.

## 2. Flexibility

New child classes can be added without changing existing logic.

## 3. Maintainability

Code becomes easier to maintain.

## 4. Scalability

Useful in large applications where many object types exist.

## 5. Loose Coupling

Code depends on parent type or interface, not exact child class.

---

# Difference Between Polymorphism and Inheritance

| Inheritance                                    | Polymorphism                                 |
| ---------------------------------------------- | -------------------------------------------- |
| One class acquires properties of another class | One object or method takes multiple forms    |
| Represents IS-A relationship                   | Uses IS-A relationship to behave differently |
| Achieved using `extends`                       | Achieved using overloading and overriding    |
| Example: Dog IS-A Animal                       | Animal reference points to Dog object        |

---

# Difference Between Polymorphism and Abstraction

| Abstraction                                | Polymorphism                          |
| ------------------------------------------ | ------------------------------------- |
| Hides implementation details               | Provides multiple forms               |
| Focuses on what to show                    | Focuses on how behavior changes       |
| Achieved using abstract classes/interfaces | Achieved using overloading/overriding |

---

# Difference Between Polymorphism and Encapsulation

| Encapsulation                      | Polymorphism                    |
| ---------------------------------- | ------------------------------- |
| Hides data                         | Provides multiple forms         |
| Focuses on security                | Focuses on flexibility          |
| Uses private variables and methods | Uses overloading and overriding |

---

# Important Interview Questions

## 1. What is polymorphism?

Polymorphism means many forms. It allows one method or object to behave differently in different situations.

## 2. What are the types of polymorphism?

There are two types:

* Compile-time polymorphism
* Runtime polymorphism

## 3. How is compile-time polymorphism achieved?

Using method overloading.

## 4. How is runtime polymorphism achieved?

Using method overriding.

## 5. What is method overloading?

Method overloading means having multiple methods with the same name but different parameters.

## 6. What is method overriding?

Method overriding means child class provides its own implementation of a parent class method.

## 7. Can method overloading happen by changing return type only?

No.

## 8. Can private methods be overridden?

No, because private methods are not inherited.

## 9. Can final methods be overridden?

No, because final methods cannot be changed.

## 10. Can static methods be overridden?

No, static methods are hidden, not overridden.

## 11. What is reference type?

Reference type is the class type written on the left side of object creation.

Example:

```java
Animal a = new Dog();
```

Here, `Animal` is reference type.

## 12. What is object type?

Object type is the actual class whose object is created using `new`.

Example:

```java
Animal a = new Dog();
```

Here, `Dog` is object type.

## 13. Which type decides accessible methods?

Reference type decides accessible methods.

## 14. Which type decides overridden method execution?

Object type decides overridden method execution.

## 15. What is static binding?

Static binding means method call is resolved at compile time.

## 16. What is dynamic binding?

Dynamic binding means method call is resolved at runtime.

---

# Tricky Interview Questions

## 1. Is overloading possible in different classes?

Yes, but commonly overloading happens in the same class. It can also happen in child class if inherited methods are overloaded.

## 2. Can main method be overloaded?

Yes.

```java
public static void main(String[] args) {}

public static void main(int a) {}
```

But JVM calls only:

```java
main(String[] args)
```

## 3. Can constructors be overloaded?

Yes.

```java
class Student {

    Student() {}

    Student(String name) {}
}
```

## 4. Can constructors be overridden?

No, because constructors are not inherited.

## 5. Can overloaded methods be final?

Yes.

## 6. Can overridden methods reduce visibility?

No.

Example:

```java
class Parent {
    public void show() {}
}

class Child extends Parent {
    private void show() {} // Error
}
```

## 7. Can overridden methods increase visibility?

Yes.

Example:

```java
class Parent {
    protected void show() {}
}

class Child extends Parent {
    public void show() {}
}
```

## 8. Can return type be changed in overriding?

Only if it is covariant.

Example:

```java
class Animal {}

class Dog extends Animal {}

class Parent {
    Animal getAnimal() {
        return new Animal();
    }
}

class Child extends Parent {
    Dog getAnimal() {
        return new Dog();
    }
}
```

This is allowed because `Dog` is a child of `Animal`.

---

# Most Important Line

```text
Reference type decides what methods are accessible.
Object type decides which overridden method executes.
```

---

# Final Definition

> **Polymorphism is the ability of an object or method to take multiple forms. It allows the same method name or same reference type to behave differently depending on the parameters or actual object type.**

---

# One-Line Quick Revision

```text
Polymorphism allows one method or object to behave in many different forms.
```

