# Java Strings — Complete Detailed Theory Notes

# 1. Introduction to Strings in Java

A **String** in Java is an object that represents a sequence of characters. Strings are used to store text data such as names, messages, email addresses, file paths, URLs, passwords, JSON data, SQL queries, and many other forms of textual information.

Example:

```java
String name = "Java";
```

In the above statement:

* `name` is a reference variable.
* `"Java"` is the String value.
* The reference variable points to a String object.

Unlike primitive data types such as `int`, `char`, or `double`, a String in Java is an object because it belongs to the `String` class.

The `String` class is part of:

```java
java.lang.String
```

Since `java.lang` is automatically imported, we do not need to import the `String` class manually.

---

# 2. Why Strings are Important

Strings are one of the most commonly used data types in Java because most applications deal with text in some form.

Strings are used in:

* User input
* Login systems
* File handling
* URLs
* Database queries
* JSON/XML data
* APIs
* Logging systems
* Chat applications
* Configuration files

Examples:

```java
String username = "Nitya";
String email = "nitya@gmail.com";
String url = "https://example.com";
String query = "SELECT * FROM students";
```

Without Strings, handling textual information in applications would be difficult.

---

# 3. String Literal

A **String Literal** is a string value written directly inside double quotes.

Examples:

```java
"Java"
"Hello"
"Programming"
```

In this statement:

```java
String s = "Java";
```

`"Java"` is the String literal.

---

# 4. Internal Working of String Literals

When Java encounters a String literal, it stores it inside a special memory area called the **String Pool**.

Example:

```java
String s1 = "Java";
String s2 = "Java";
```

When `"Java"` is encountered for the first time:

1. JVM checks the String Pool.
2. If `"Java"` is not present, it creates the object in the pool.
3. `s1` points to that pooled object.

When the second statement executes:

```java
String s2 = "Java";
```

JVM again checks the String Pool.

Since `"Java"` already exists:

* No new object is created.
* `s2` points to the already existing pooled object.

Memory representation:

```text
Stack Memory:
s1 --------|
            |
s2 --------|------> "Java"

String Pool:
+----------------+
| "Java"         |
+----------------+
```

This is why:

```java
System.out.println(s1 == s2);
```

Output:

```java
true
```

Both references point to the same object.

---

# 5. What is String Pool?

The **String Pool** is a special memory area where Java stores String literals.

It is also called:

```text
String Constant Pool (SCP)
```

The main purpose of the String Pool is:

```text
Memory optimization and object reuse
```

Instead of creating duplicate String objects repeatedly, Java reuses already existing String literals.

---

# 6. Why String Pool is Needed

Consider the following example:

```java
String a = "Java";
String b = "Java";
String c = "Java";
```

Without the String Pool:

```text
3 separate objects would be created
```

This would waste memory unnecessarily.

With String Pool:

```text
Only one object is created
```

and all references point to that same object.

Memory:

```text
Stack:
a -----|
       |
b -----|-----> "Java"
       |
c -----|

String Pool:
+----------------+
| "Java"         |
+----------------+
```

This saves memory and improves performance.

---

# 7. Creating Strings using `new`

Strings can also be created using the `new` keyword.

Example:

```java
String s = new String("Java");
```

This behaves differently from String literals.

---

# 8. Internal Working of `new String("Java")`

Suppose `"Java"` is not already present in the String Pool.

When this line executes:

```java
String s = new String("Java");
```

the following steps happen internally:

## Step 1

`"Java"` is a String literal.

Therefore JVM checks the String Pool.

If `"Java"` is absent:

```text
"Java" is created in the String Pool
```

---

## Step 2

Because `new` is used:

```text
A new String object is created separately in heap memory
```

---

## Step 3

The reference variable `s` points to the heap object.

Memory representation:

```text
Stack:
s ------------------------+
                          |
                          v

Heap:
+----------------+
| "Java"         |
+----------------+

String Pool:
+----------------+
| "Java"         |
+----------------+
```

So potentially:

```text
2 objects are created
```

* One in String Pool
* One in Heap

---

# 9. Why Pool Object is Created Even When Using `new`

In:

```java
new String("Java")
```

the `"Java"` part is still a String literal.

Java has a rule:

```text
All String literals must be stored in String Pool
```

Conceptually:

```java
String temp = "Java";
String s = new String(temp);
```

The pool object exists because `"Java"` is a literal.

The heap object exists because the programmer explicitly requested a new object using `new`.

---

# 10. String Literal vs `new String()`

| Feature                | String Literal | `new String()`       |
| ---------------------- | -------------- | -------------------- |
| Example                | `"Java"`       | `new String("Java")` |
| Stored In              | String Pool    | Heap                 |
| Reuses existing object | Yes            | No                   |
| Memory efficient       | Yes            | No                   |
| Recommended            | Yes            | Usually No           |

Example:

```java
String s1 = "Java";
String s2 = "Java";
String s3 = new String("Java");

System.out.println(s1 == s2);
System.out.println(s1 == s3);
System.out.println(s1.equals(s3));
```

Output:

```java
true
false
true
```

Explanation:

* `s1 == s2` → true because both point to same pooled object.
* `s1 == s3` → false because `s3` points to heap object.
* `equals()` → true because content is same.

---

# 11. Stack Memory, Heap Memory, and String Pool

To understand Strings properly, memory structure is important.

---

# Stack Memory

Stack stores:

* Local variables
* References

Example:

```java
String s = "Java";
```

`s` reference is stored in stack memory.

---

# Heap Memory

Heap stores objects.

String objects are stored in heap memory.

---

# String Pool

String Pool is a special area inside heap memory that stores reusable String literals.

Memory structure:

```text
Stack:
+----------------+
| s reference    |
+----------------+

Heap:
+------------------------------------+
| String Pool                        |
| +------------------------------+   |
| | "Java"                       |   |
| +------------------------------+   |
|                                    |
| Other Objects                      |
+------------------------------------+
```

---

# 12. Immutability

## What is Immutable?

Immutable means:

```text
Object cannot be changed after creation
```

String objects are immutable.

---

# 13. Internal Working of Immutability

Example:

```java
String s = "Hello";
s.concat(" World");

System.out.println(s);
```

Output:

```java
Hello
```

Why?

Because:

```text
concat() does not modify the original object
```

Instead:

```text
A new object is created
```

Original object:

```text
"Hello"
```

New object:

```text
"Hello World"
```

But since we did not store the new object, it gets discarded.

Correct version:

```java
String s = "Hello";
s = s.concat(" World");

System.out.println(s);
```

Output:

```java
Hello World
```

Now `s` points to the new object.

---

# 14. Memory Representation of Immutability

Before concatenation:

```text
s ------> "Hello"
```

After:

```text
Old Object:
"Hello"

New Object:
"Hello World"

s ------> "Hello World"
```

The original object is never modified.

Only the reference changes.

---

# 15. Why Strings are Immutable

Strings are immutable for several important reasons.

---

# 15.1 Security

Strings are used in:

* Database URLs
* File paths
* Network connections
* Configuration settings
* Authentication systems

Example:

```java
String path = "/admin/data";
```

If Strings were mutable, attackers could modify important values after validation.

Immutability prevents this.

---

# 15.2 String Pool Safety

Example:

```java
String s1 = "Java";
String s2 = "Java";
```

Both point to same pooled object.

If String were mutable and `s1` changed the value:

```text
s2 would also change automatically
```

That would be dangerous.

Immutability protects shared pooled objects.

---

# 15.3 Thread Safety

Immutable objects are naturally thread-safe.

Example:

```java
String message = "Hello";
```

Many threads can use the same String safely because no thread can modify it.

---

# 15.4 HashMap Key Stability

Strings are heavily used as HashMap keys.

Example:

```java
Map<String, Integer> map = new HashMap<>();
map.put("Java", 100);
```

HashMap depends on hash codes.

If String values changed after insertion:

```text
Hash code would change
```

and HashMap might fail to locate the key.

Immutability guarantees stable hash codes.

---

# 16. Mutable Objects

Mutable means:

```text
Object content can change after creation
```

Examples:

* StringBuilder
* StringBuffer

Example:

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");

System.out.println(sb);
```

Output:

```java
Hello World
```

The same object is modified.

---

# 17. Comparing Strings

Strings can be compared in multiple ways.

---

# 17.1 Using `==`

`==` compares references.

It checks whether two variables point to the same object.

Example:

```java
String s1 = "Java";
String s2 = "Java";

System.out.println(s1 == s2);
```

Output:

```java
true
```

Both point to same pooled object.

---

Example:

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2);
```

Output:

```java
false
```

Different heap objects.

---

# 17.2 Using `equals()`

`equals()` compares content.

Example:

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1.equals(s2));
```

Output:

```java
true
```

Because both contain `"Java"`.

---

# 17.3 Using `equalsIgnoreCase()`

This ignores uppercase/lowercase differences.

Example:

```java
String s1 = "java";
String s2 = "JAVA";

System.out.println(s1.equalsIgnoreCase(s2));
```

Output:

```java
true
```

---

# 17.4 Using `compareTo()`

`compareTo()` compares strings lexicographically (dictionary order).

Example:

```java
System.out.println("Apple".compareTo("Banana"));
```

Output:

```text
negative value
```

Rules:

```text
0        → equal
negative → first smaller
positive → first greater
```

---

# 18. Concatenating Strings

Concatenation means joining strings.

---

# 18.1 Using `+`

Example:

```java
String s = "Hello" + " World";
```

Output:

```java
Hello World
```

---

# 18.2 Using `concat()`

Example:

```java
String s = "Hello";
String result = s.concat(" World");
```

Output:

```java
Hello World
```

Since String is immutable:

```text
concat() creates a new object
```

---

# 19. Internal Working of Concatenation

Example:

```java
String s = "Hello";
s = s + " World";
```

Internally similar to:

```java
s = new StringBuilder()
        .append(s)
        .append(" World")
        .toString();
```

---

# 20. Why String Concatenation in Loops is Inefficient

Example:

```java
String s = "";

for(int i = 0; i < 1000; i++) {
    s += i;
}
```

Each iteration creates a new object.

Memory concept:

```text
Iteration 1:
"" + 0 → "0"

Iteration 2:
"0" + 1 → "01"

Iteration 3:
"01" + 2 → "012"
```

Many temporary objects are created.

This wastes memory and reduces performance.

---

# 21. StringBuilder

`StringBuilder` is a mutable sequence of characters.

It is used when Strings need repeated modifications.

Example:

```java
StringBuilder sb = new StringBuilder("Hello");

sb.append(" World");

System.out.println(sb);
```

Output:

```java
Hello World
```

---

# 22. Why StringBuilder is Faster

Because:

```text
Same object is modified repeatedly
```

No unnecessary object creation happens.

This makes StringBuilder much faster than String for repeated modifications.

---

# 23. Internal Implementation of StringBuilder

StringBuilder internally uses:

```text
Resizable character array
```

Conceptually:

```text
['H','e','l','l','o']
```

When capacity becomes full:

```text
New larger array is created
Data is copied
```

Capacity increase formula:

```text
newCapacity = oldCapacity * 2 + 2
```

---

# 24. Important StringBuilder Methods

## append()

Adds text at end.

```java
sb.append(" Java");
```

---

## insert()

Inserts text.

```java
sb.insert(5, "XYZ");
```

---

## delete()

Deletes characters.

```java
sb.delete(0, 3);
```

---

## reverse()

Reverses characters.

```java
sb.reverse();
```

---

## replace()

Replaces characters.

```java
sb.replace(0, 4, "Python");
```

---

# 25. StringBuffer

StringBuffer is similar to StringBuilder but thread-safe.

Example:

```java
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");
```

---

# 26. StringBuilder vs StringBuffer

| Feature         | StringBuilder   | StringBuffer   |
| --------------- | --------------- | -------------- |
| Thread-safe     | No              | Yes            |
| Synchronization | No              | Yes            |
| Speed           | Faster          | Slower         |
| Best Use        | Single-threaded | Multi-threaded |

---

# 27. Synchronization

Synchronization means:

```text
Only one thread can access a method at a time
```

StringBuffer methods are synchronized.

This makes it thread-safe but slower.

---

# 28. Substring

Substring means extracting part of a string.

---

# substring(start)

Example:

```java
String s = "Programming";

System.out.println(s.substring(3));
```

Output:

```java
gramming
```

---

# substring(start, end)

Example:

```java
System.out.println(s.substring(0, 6));
```

Output:

```java
Progra
```

Rule:

```text
Start index included
End index excluded
```

---

# 29. Formatting Strings

Formatting helps create structured output.

---

# Using printf()

```java
System.out.printf("Name: %s Age: %d", "Nitya", 22);
```

---

# Using String.format()

```java
String result = String.format("Price: %.2f", 99.456);
```

Output:

```java
Price: 99.46
```

---

# Common Format Specifiers

| Specifier | Meaning               |
| --------- | --------------------- |
| %s        | String                |
| %d        | Integer               |
| %f        | Float                 |
| %.2f      | Float with 2 decimals |
| %c        | Character             |
| %b        | Boolean               |
