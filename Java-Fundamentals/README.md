# 1. Introduction to Java

Java is a high-level, object-oriented programming language developed by Sun Microsystems (now owned by Oracle). It is designed to be platform-independent, which means that a Java program can run on any system without modification, as long as that system has a Java Virtual Machine (JVM).

One of the most important principles of Java is:

> **"Write Once, Run Anywhere (WORA)"**

This is achieved through the use of bytecode, which is executed by the JVM instead of directly by the operating system.

---

# 2. Features of Java

Java provides several important features that make it powerful and widely used:

- **Platform Independence:** Java code is compiled into bytecode, which can run on any system with a JVM.  
- **Object-Oriented:** Java follows the principles of object-oriented programming such as encapsulation, inheritance, and polymorphism.  
- **Simple and Easy to Learn:** Java removes complex features like pointers and provides automatic memory management.  
- **Secure:** Java programs run inside the JVM, which provides a secure execution environment.  
- **Robust:** Java includes strong memory management and exception handling mechanisms.  
- **Multithreaded:** Java supports concurrent execution of multiple threads.  
- **Portable:** Java programs can be easily transferred between different environments.  

---

# 3. Java Architecture (JDK, JRE, JVM)

Understanding Java architecture is essential to understand how Java programs work.

## 3.1 Java Development Kit (JDK)

The JDK is a complete package used for developing Java applications. It includes:

- The Java compiler (`javac`)
- The Java Runtime Environment (JRE)
- Development tools such as debuggers  

In simple terms, the JDK is used to **write and compile Java programs**.

---

## 3.2 Java Runtime Environment (JRE)

The JRE provides the environment required to run Java programs. It includes:

- JVM (Java Virtual Machine)
- Core libraries  

The JRE is used to **execute Java programs**.

---

## 3.3 Java Virtual Machine (JVM)

The JVM is the component that actually runs Java bytecode. It converts bytecode into machine-level instructions that the system can understand.

---

## 3.4 Execution Flow

The execution of a Java program follows these steps:

1. The source code is written in a `.java` file.  
2. The Java compiler (`javac`) compiles the code into bytecode (`.class` file).  
3. The JVM executes the bytecode and produces the output.  

---

# 4. Structure of a Java Program

A basic Java program looks like this:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```
## Explanation of Java Program

- `public class Main` defines a class named `Main`. Every Java program must contain at least one class because Java is an object-oriented language and everything is written inside classes.

- `public static void main(String[] args)` is the entry point of the program. The JVM starts execution from this method.

- `System.out.println()` is used to print output to the console.

The `main` method is where the execution of the program begins.
