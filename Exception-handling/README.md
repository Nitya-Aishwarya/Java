```
### EXCEPTION HANDLING
```
# 1. What is Exception Handling?

Exception handling is a mechanism in Java used to handle abnormal situations that occur while a program is running.

A Java program normally executes line by line. This is called the normal flow of execution. But sometimes a problem occurs during execution, such as dividing a number by zero, accessing an invalid array index, opening a missing file, or using a null object. These problems disturb the normal flow of the program.

Exception handling helps the program handle these problems safely instead of stopping suddenly.

Example:

```java
try {
    int result = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}

System.out.println("Program continues");
```

Output:

```text
Cannot divide by zero
Program continues
```

Here, the program does not crash. The exception is caught and handled.

# 2. What is an Exception?

An exception is an abnormal event that occurs during program execution.

In Java, an exception is not just a message. It is an object created by the JVM when something goes wrong.

Example:

```java
int x = 10 / 0;
```

This causes an exception because division by zero is not allowed.

Internally, Java creates an object like this:

```java
new ArithmeticException("/ by zero");
```

This object contains information such as the exception name, error message, line number, and stack trace.

So, an exception is a runtime problem represented as an object.

# 3. What is an Error?

An error is a serious problem caused by the JVM, system, or memory.

Errors are usually not recoverable. A normal application is not expected to handle errors.

Examples of errors are:

```text
StackOverflowError
OutOfMemoryError
VirtualMachineError
```

Example:

```java
public class Main {
    static void test() {
        test();
    }

    public static void main(String[] args) {
        test();
    }
}
```

This causes:

```text
StackOverflowError
```

because the method keeps calling itself again and again until stack memory is full.

# 4. Difference Between Exception and Error

| Exception                                   | Error                                         |
| ------------------------------------------- | --------------------------------------------- |
| It is usually recoverable.                  | It is usually not recoverable.                |
| It is caused by application-level problems. | It is caused by JVM or system-level problems. |
| It can be handled using try-catch.          | It is generally not handled.                  |
| Example: `ArithmeticException`              | Example: `OutOfMemoryError`                   |

Example of exception:

```java
int x = 10 / 0;
```

This gives `ArithmeticException`.

Example of error:

```java
static void test() {
    test();
}
```

This gives `StackOverflowError`.

# 5. Java Exception Hierarchy

Java organizes exceptions and errors using inheritance.

The hierarchy is:

```text
Object
   |
Throwable
   |
   |--- Exception
   |       |
   |       |--- Checked Exceptions
   |       |       |--- IOException
   |       |       |--- SQLException
   |       |       |--- ClassNotFoundException
   |       |
   |       |--- RuntimeException
   |               |--- ArithmeticException
   |               |--- NullPointerException
   |               |--- ArrayIndexOutOfBoundsException
   |               |--- NumberFormatException
   |
   |--- Error
           |--- StackOverflowError
           |--- OutOfMemoryError
           |--- VirtualMachineError
```

# 6. Throwable Class

`Throwable` is the parent class of both `Exception` and `Error`.

Anything that can be thrown or caught in Java must be a subclass of `Throwable`.

Important methods of `Throwable` are:

```java
getMessage()
```

This returns only the error message.

```java
printStackTrace()
```

This prints the complete error details, including the line number and method call path.

```java
toString()
```

This returns the exception name along with the message.

Example:

```java
try {
    int x = 10 / 0;
}
catch (Exception e) {
    System.out.println(e.getMessage());
    System.out.println(e.toString());
    e.printStackTrace();
}
```

# 7. Types of Exceptions

Java exceptions are mainly divided into two types:

```text
1. Checked Exceptions
2. Unchecked Exceptions
```

# 8. Checked Exceptions

Checked exceptions are checked by the compiler at compile time.

The compiler forces the programmer to handle them using `try-catch` or declare them using `throws`.

Checked exceptions usually occur when the program deals with external resources like files, databases, or networks.

Example:

```java
FileReader file = new FileReader("abc.txt");
```

This may cause `FileNotFoundException`, because the file may not exist.

So Java forces us to handle it:

```java
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("abc.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
```

Examples of checked exceptions:

```text
IOException
SQLException
ClassNotFoundException
FileNotFoundException
```

# 9. Unchecked Exceptions / Runtime Exceptions

Unchecked exceptions occur during program execution.

The compiler does not force the programmer to handle them.

They are usually caused by programming mistakes.

Examples:

```java
int x = 10 / 0;
```

This gives `ArithmeticException`.

```java
String s = null;
s.length();
```

This gives `NullPointerException`.

```java
int[] arr = {1, 2, 3};
System.out.println(arr[5]);
```

This gives `ArrayIndexOutOfBoundsException`.

```java
int n = Integer.parseInt("abc");
```

This gives `NumberFormatException`.

Runtime exceptions should usually be prevented by writing correct logic.

# 10. What Happens Internally When an Exception Occurs?

Suppose this code executes:

```java
int x = 10 / 0;
```

The internal process is:

```text
1. JVM detects division by zero.
2. JVM creates an ArithmeticException object.
3. JVM throws that exception object.
4. Normal execution stops.
5. JVM searches for a matching catch block.
6. If a matching catch block is found, it executes.
7. If no catch block is found, the program terminates.
```

This is the basic working of exception handling.

# 11. try Block

The `try` block contains code that may cause an exception.

Syntax:

```java
try {
    // risky code
}
```

Example:

```java
try {
    int x = 10 / 0;
}
```

The JVM watches the code inside the `try` block. If an exception occurs, the remaining statements inside the `try` block are skipped.

Example:

```java
try {
    int x = 10 / 0;
    System.out.println("Hello");
}
catch (ArithmeticException e) {
    System.out.println("Exception handled");
}
```

Output:

```text
Exception handled
```

`Hello` is not printed because the exception occurred before that line.

# 12. catch Block

The `catch` block is used to handle an exception that occurs inside the `try` block.

Syntax:

```java
catch (ExceptionType e) {
    // handling code
}
```

Example:

```java
try {
    int x = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

Here, `ArithmeticException` is the exception type and `e` is the exception object.

The object `e` contains details about the exception.

Example:

```java
try {
    int x = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println(e.getMessage());
}
```

Output:

```text
/ by zero
```

The catch block prevents the program from terminating suddenly.

# 13. Generic catch Block

A generic catch block catches many types of exceptions.

Example:

```java
catch (Exception e)
```

Since `Exception` is the parent class of many exceptions, it can catch different exception types.

Example:

```java
try {
    int[] arr = {1, 2};
    System.out.println(arr[5]);
}
catch (Exception e) {
    System.out.println("Something went wrong");
}
```

Output:

```text
Something went wrong
```

However, it is better to catch specific exceptions when possible.

# 14. Multiple catch Blocks

A single `try` block can have multiple `catch` blocks.

Example:

```java
try {
    int[] arr = {1, 2};
    System.out.println(arr[5]);
}
catch (ArithmeticException e) {
    System.out.println("Arithmetic problem");
}
catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Array index problem");
}
catch (Exception e) {
    System.out.println("General problem");
}
```

Output:

```text
Array index problem
```

Java checks the catch blocks from top to bottom. The first matching catch block executes.

Important rule:

Specific catch blocks must come before general catch blocks.

Correct:

```java
catch (ArithmeticException e) {
}
catch (Exception e) {
}
```

Wrong:

```java
catch (Exception e) {
}
catch (ArithmeticException e) {
}
```

The second catch block becomes unreachable because `Exception` already catches everything.

# 15. finally Block

The `finally` block always executes after `try` and `catch`.

It executes whether an exception occurs or not.

Syntax:

```java
try {
    // risky code
}
catch (Exception e) {
    // handling code
}
finally {
    // cleanup code
}
```

Example:

```java
try {
    int x = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Exception handled");
}
finally {
    System.out.println("Finally block executed");
}
```

Output:

```text
Exception handled
Finally block executed
```

Example without exception:

```java
try {
    int x = 10 / 2;
    System.out.println(x);
}
catch (Exception e) {
    System.out.println("Exception handled");
}
finally {
    System.out.println("Finally block executed");
}
```

Output:

```text
5
Finally block executed
```

The main purpose of `finally` is cleanup.

It is used to close files, close database connections, close sockets, or release resources.

Example:

```java
finally {
    System.out.println("Closing resources");
}
```

# 16. Can finally Execute Without catch?

Yes. A `try` block can be followed by `finally` without `catch`.

Example:

```java
try {
    int x = 10 / 2;
}
finally {
    System.out.println("Finally executed");
}
```

This is valid.

# 17. When finally May Not Execute

Normally, `finally` always executes.

But it may not execute in rare cases, such as:

```java
System.exit(0);
```

Example:

```java
try {
    System.exit(0);
}
finally {
    System.out.println("Finally");
}
```

Here, JVM shuts down immediately, so `finally` does not execute.

# 18. throw Keyword

The `throw` keyword is used to manually throw an exception.

It is used inside a method or block.

Syntax:

```java
throw new ExceptionType("message");
```

Example:

```java
public class Main {
    public static void main(String[] args) {
        int age = 15;

        if (age < 18) {
            throw new ArithmeticException("Not eligible to vote");
        }

        System.out.println("Eligible");
    }
}
```

Output:

```text
Exception in thread "main" java.lang.ArithmeticException: Not eligible to vote
```

Here, the programmer manually throws an exception.

`throw` is used when we want to say that a particular condition is invalid.

# 19. throw with try-catch

We can also handle manually thrown exceptions.

Example:

```java
public class Main {
    public static void main(String[] args) {
        try {
            int age = 15;

            if (age < 18) {
                throw new ArithmeticException("Underage");
            }
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

Output:

```text
Underage
```

# 20. throws Keyword

The `throws` keyword is used in a method declaration.

It tells the caller that the method may throw an exception.

Syntax:

```java
returnType methodName() throws ExceptionType {
    // code
}
```

Example:

```java
import java.io.*;

public class Main {

    static void readFile() throws IOException {
        FileReader file = new FileReader("abc.txt");
    }

    public static void main(String[] args) {
        try {
            readFile();
        }
        catch (IOException e) {
            System.out.println("File problem");
        }
    }
}
```

Here, `readFile()` may throw `IOException`. The method does not handle it itself. It declares it using `throws`. The caller method handles it using `try-catch`.

Important point:

`throws` does not actually throw an exception. It only declares that an exception may occur.

# 21. Difference Between throw and throws

| throw                                  | throws                                    |
| -------------------------------------- | ----------------------------------------- |
| It actually throws an exception.       | It declares possible exceptions.          |
| It is used inside a method.            | It is used in method declaration.         |
| It is followed by an exception object. | It is followed by exception class names.  |
| Example: `throw new IOException();`    | Example: `void test() throws IOException` |

# 22. Exception Propagation

Exception propagation means an exception moves from the current method to the caller method if it is not handled.

Example:

```java
public class Main {

    static void method3() {
        int x = 10 / 0;
    }

    static void method2() {
        method3();
    }

    static void method1() {
        method2();
    }

    public static void main(String[] args) {
        try {
            method1();
        }
        catch (Exception e) {
            System.out.println("Exception handled in main");
        }
    }
}
```

Flow:

```text
method3 → method2 → method1 → main
```

The exception occurs in `method3`. Since it is not handled there, it moves to `method2`. Then to `method1`. Finally, it reaches `main`, where it is handled.

# 23. Stack Unwinding

Stack unwinding is the process of removing method calls from the stack when an exception propagates.

Before exception:

```text
main()
  method1()
    method2()
      method3()
```

If an exception occurs in `method3`, Java starts removing methods from the stack until a matching catch block is found.

This process is called stack unwinding.

# 24. Your charAt Example

Code:

```java
try {
    System.out.println(s1.charAt(5));
}
catch (Exception ex) {
    System.out.println("Invalid");
}
```

Explanation:

`charAt(5)` tries to access the character at index `5`.

Java strings use zero-based indexing.

Example:

```java
String s1 = "Hello";
```

Indexes:

```text
H  e  l  l  o
0  1  2  3  4
```

Here, valid indexes are `0` to `4`.

So:

```java
s1.charAt(5)
```

is invalid and causes:

```text
StringIndexOutOfBoundsException
```

The catch block catches the exception and prints:

```text
Invalid
```

Better version:

```java
try {
    System.out.println(s1.charAt(5));
}
catch (StringIndexOutOfBoundsException ex) {
    System.out.println("Invalid index");
}
```

This is better because it catches the exact exception.

# 25. Complete Flow of Exception Handling

The complete flow is:

```text
1. Risky code is placed inside try block.
2. JVM executes the try block.
3. If no exception occurs, catch block is skipped.
4. If exception occurs, JVM creates an exception object.
5. JVM throws the exception object.
6. Normal execution stops.
7. JVM searches for a matching catch block.
8. If catch block is found, it executes.
9. finally block executes if present.
10. Program continues if exception is handled.
11. Program terminates if exception is not handled.
```

# 26. Full Example

```java
public class Main {
    public static void main(String[] args) {

        try {
            String s1 = "Hello";
            System.out.println(s1.charAt(5));
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid string index");
        }
        catch (Exception e) {
            System.out.println("General exception");
        }
        finally {
            System.out.println("Finally block executed");
        }

        System.out.println("Program continues");
    }
}
```

Output:

```text
Invalid string index
Finally block executed
Program continues
```

Explanation:

The string `"Hello"` has indexes `0` to `4`. Index `5` is invalid. Java throws `StringIndexOutOfBoundsException`. The matching catch block handles it. Then the finally block executes. After that, the program continues.

# 27. Advantages of Exception Handling

Exception handling prevents abrupt program termination.

It separates normal code from error-handling code.

It improves program readability.

It helps in debugging by giving useful exception details.

It makes programs more reliable and maintainable.

It allows recovery from abnormal situations.

# 28. Important Final Summary

`try` contains risky code.

`catch` handles the exception.

`finally` always executes and is used for cleanup.

`throw` manually throws an exception.

`throws` declares that a method may throw an exception.

`Exception` represents recoverable problems.

`Error` represents serious system or JVM failures.

`Throwable` is the parent class of both `Exception` and `Error`.

Checked exceptions are checked by the compiler.

Unchecked exceptions occur during runtime and are usually programming mistakes.
