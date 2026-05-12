# Complete Java Notes — Arrays, Memory, References, `toString()`, Binary Search, Methods, and Varargs

---

# 1. Introduction to Java Memory and Variables

To properly understand arrays, objects, references, method calls, `toString()`, and pass-by-value, we must first understand how Java manages memory internally.

Java mainly uses two important memory areas during program execution:

| Memory Area  | Purpose                                                                |
| ------------ | ---------------------------------------------------------------------- |
| Stack Memory | Stores method calls, local variables, primitive values, and references |
| Heap Memory  | Stores objects, arrays, and strings                                    |

Both memory areas work together continuously while a Java program runs.

---

# 2. Stack Memory

Stack memory is responsible for:

* method execution,
* local variables,
* primitive variables,
* and reference variables.

Every time a method is called, Java creates a separate stack frame.

A stack frame contains:

* method parameters,
* local variables,
* temporary data.

When the method finishes, its stack frame is automatically removed.

---

## Example

```java
int a = 10;
```

Internally:

```text
STACK
-------------
a = 10
```

Explanation:

* Variable `a` directly stores actual value `10`.
* Primitive variables contain actual data.
* No object is created.
* No reference is involved.

---

# 3. Heap Memory

Heap memory stores all objects created during runtime.

This includes:

* arrays,
* strings,
* objects,
* collections.

Objects are never stored directly inside variables.

Instead, variables store references (addresses) pointing to heap objects.

---

## Example

```java
Person p = new Person();
```

Internally:

```text
STACK
-------------
p → 0x100

HEAP
-------------
0x100 → Person object
```

Explanation:

* The actual `Person` object exists inside heap memory.
* Variable `p` stores only the address/reference.
* `0x100` symbolically represents object location.

---

# 4. Primitive Types and Non-Primitive Types

Java datatypes are divided into two categories.

---

# Primitive Types

Primitive types directly store actual values.

Examples:

* int
* double
* char
* boolean

Example:

```java
int n = 5;
```

Internally:

```text
STACK
-------------
n = 5
```

---

# Non-Primitive Types

Non-primitives store references to heap objects.

Examples:

* arrays
* strings
* objects

Example:

```java
String s = "Hello";
```

Internally:

```text
STACK
-------------
s → 0x200

HEAP
-------------
0x200 → "Hello"
```

---

# 5. Arrays in Java

An array is a data structure used to store multiple values of the same datatype in a single variable.

Instead of creating many separate variables, arrays group related data together.

---

## Example

```java
int[] numbers = {10, 20, 30, 40};
```

This creates an integer array containing four elements.

---

# Important Characteristics of Arrays

* Arrays store elements of same datatype.
* Arrays have fixed size after creation.
* Arrays use zero-based indexing.
* Arrays are objects in Java.
* Arrays are stored in heap memory.
* Array variables store references.

---

# 6. Why Array Indexing Starts from 0

Array indexing starts from `0` because arrays internally use memory addresses.

The index represents the offset distance from the starting memory location.

Formula:

\text{Address of Element} = \text{Base Address} + (\text{Index} \times \text{Element Size})

For first element:

```text
base + (0 × size) = base
```

So first element naturally gets index `0`.

---

# 7. Internal Memory Structure of Arrays

Example:

```java
int[] arr = {1,2,3};
```

Internally:

```text
STACK
-------------
arr → 0x300

HEAP
-------------
0x300 → [1,2,3]
```

Explanation:

* Actual array object exists in heap memory.
* `arr` stores only reference.
* Arrays are non-primitive objects.

---

# 8. Arrays are Objects in Java

In Java, everything that is not primitive is an object.

Arrays are special objects automatically created by JVM.

Because arrays are objects:

* they inherit from `Object`,
* they use references,
* they contain metadata,
* they inherit methods.

---

# 9. Internal Hierarchy of Arrays

```text
Object
   ↑
Array Object
   ↑
int[]
double[]
String[]
```

All arrays ultimately inherit from:

```java
java.lang.Object
```

---

# 10. Accessing Array Elements

Elements are accessed using indexes.

Syntax:

```java
arrayName[index]
```

Example:

```java
System.out.println(arr[1]);
```

Output:

```text
2
```

---

# 11. Iterating Arrays

Iteration means visiting every element one-by-one.

---

# Traditional `for` Loop

```java
for(int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

Explanation:

* `i = 0` starts from first index.
* `i < arr.length` prevents invalid access.
* `i++` moves to next index.

---

# Enhanced `for-each` Loop

```java
for(int num : arr) {
    System.out.println(num);
}
```

Explanation:

* `num` temporarily stores each element.
* Loop automatically traverses array.

---

# Internal Working of Enhanced For Loop

Compiler internally converts:

```java
for(int n : arr)
```

approximately into:

```java
for(int i = 0; i < arr.length; i++) {
    int n = arr[i];
}
```

---

# 12. Array Length Property

Arrays internally store their length information.

Example:

```java
arr.length
```

Important:

* arrays use `.length`
* strings use `.length()`

Reason:

* array length is property/field,
* string length is method.

---

# 13. The `Arrays` Utility Class

Java provides helper utility class:

```java
import java.util.Arrays;
```

This class contains static utility methods for:

* printing arrays,
* sorting arrays,
* searching arrays,
* comparing arrays,
* copying arrays.

---

# Arrays Class Hierarchy

```text
Object
   ↑
Arrays Utility Class
```

The `Arrays` class mainly contains static methods.

---

# 14. Understanding `toString()` Clearly

This is one of the most important beginner concepts.

---

# Problem Without `Arrays.toString()`

Example:

```java
int[] arr = {1,2,3};

System.out.println(arr);
```

Output:

```text
[I@36baf30c
```

This happens because Java is not printing actual array elements.

---

# Why This Happens

Arrays are objects.

When an object is printed:

```java
System.out.println(object);
```

Java internally calls:

```java
object.toString();
```

---

# `toString()` Hierarchy

Every Java class inherits:

```java
public String toString()
```

from:

```java
java.lang.Object
```

Hierarchy:

```text
Object
   └── toString()
```

Arrays inherit this method because arrays are objects.

---

# Default `Object.toString()` Implementation

Internally behaves similar to:

```java
getClass().getName() + '@' + Integer.toHexString(hashCode())
```

---

# Understanding Array Output

Example:

```text
[I@36baf30c
```

Meaning:

| Part       | Meaning          |
| ---------- | ---------------- |
| `[`        | array            |
| `I`        | integer datatype |
| `@`        | separator        |
| `36baf30c` | hashcode         |

---

# Why Arrays Print Strange Values

Arrays do NOT override `toString()`.

So arrays inherit default implementation from `Object`.

---

# 15. Internal Working of `Arrays.toString()`

`Arrays.toString()` manually creates readable string representation.

Example:

```java
System.out.println(Arrays.toString(arr));
```

Output:

```text
[1, 2, 3]
```

---

# Internal Logic of `Arrays.toString()`

Internally Java performs steps similar to:

```java
StringBuilder sb = new StringBuilder();

sb.append("[");

for each element:
    append element
    append comma

sb.append("]");
```

Then Java returns final readable string.

---

# Important Understanding

`Arrays.toString()`:

* does NOT modify array,
* only creates readable string representation.

---

# 16. Common Arrays Methods

---

# `Arrays.sort()`

Sorts array in ascending order.

Example:

```java
Arrays.sort(nums);
```

---

# `Arrays.binarySearch()`

Searches efficiently in sorted arrays.

Example:

```java
Arrays.binarySearch(nums, 5);
```

---

# `Arrays.equals()`

Compares arrays element-by-element.

---

# `Arrays.copyOf()`

Creates copied arrays.

---

# 17. Binary Search

Binary Search is an efficient searching algorithm used on sorted arrays.

Instead of checking elements one-by-one, it repeatedly divides search space into half.

---

# Important Condition

Binary Search requires sorted array.

---

# Binary Search Process

Example:

```text
[1,3,5,7,9]
```

Search for `7`.

---

## Step 1

```text
low = 0
high = 4
```

---

## Step 2

Middle index:

\text{mid} = \frac{\text{low}+\text{high}}{2}

Result:

```text
mid = 2
```

Value:

```text
arr[2] = 5
```

---

## Step 3

Since:

```text
7 > 5
```

ignore left half.

---

## Step 4

Search remaining right half.

---

# Binary Search Time Complexity

```text
O(log n)
```

Because every step removes half the elements.

---

# 18. Integer Overflow Problem

Java `int` range:

```text
-2,147,483,648 to 2,147,483,647
```

Suppose:

```java
low = 2_000_000_000;
high = 2_100_000_000;
```

Then:

```java
low + high
```

becomes too large for integer storage.

This causes integer overflow.

---

# 19. Safer Mid Formula

Unsafe formula:

\frac{\text{low}+\text{high}}{2}

Safer formula:

\text{mid}=\text{low}+\frac{(\text{high}-\text{low})}{2}

---

# Why Safer Formula Works

Instead of adding two huge numbers first:

* it calculates smaller difference first,
* preventing overflow.

---

# Derivation of Safe Formula

Start:

\text{mid}=\frac{\text{low}+\text{high}}{2}

Rewrite:

```text
high = low + (high - low)
```

Substitute:

```text
mid = (low + low + (high - low))/2
```

Simplify:

```text
mid = low + (high - low)/2
```

---

# 20. Binary Search Program

```java
static int binarySearch(int[] arr, int target) {

    int low = 0;
    int high = arr.length - 1;

    while(low <= high) {

        int mid = low + (high - low) / 2;

        if(arr[mid] == target) {
            return mid;
        }

        else if(target > arr[mid]) {
            low = mid + 1;
        }

        else {
            high = mid - 1;
        }
    }

    return -1;
}
```

---

# 21. Method Calls and Stack Frames

Every method call creates a separate stack frame.

Example:

```java
change(n);
```

Internally:

```text
main()
   ↓
change()
```

Each method gets:

* separate local variables,
* separate parameters,
* separate temporary data.

---

# 22. Java is ALWAYS Pass-by-Value

This is the most important rule.

Java NEVER truly uses pass-by-reference.

Java ALWAYS passes copies.

---

# What Java Copies

| Type      | What Java Copies |
| --------- | ---------------- |
| Primitive | actual value     |
| Object    | reference value  |

---

# Important Understanding

Java copies:

* the value stored inside variable.

For primitive variables:

* stored value is actual data.

For object variables:

* stored value is reference/address.

---

# 23. Primitive Passing Example

```java
static void change(int x) {
    x = 20;
}

public static void main(String[] args) {

    int a = 10;

    change(a);

    System.out.println(a);
}
```

Output:

```text
10
```

---

# Internal Working

Before method call:

```text
STACK
-------------
main():
a = 10
```

Method call copies value:

```text
x = 10
```

New stack frame:

```text
STACK
-------------
main():
a = 10

change():
x = 10
```

Modification:

```java
x = 20;
```

Now:

```text
STACK
-------------
main():
a = 10

change():
x = 20
```

Original variable unchanged.

---

# Conclusion for Primitive Types

Primitive variables:

* store actual values,
* Java copies actual values,
* changes affect only local copy.

---

# 24. Object Passing Example

```java
class Person {
    String name;
}

static void change(Person p) {
    p.name = "Ram";
}
```

---

```java
Person person = new Person();
person.name = "Shyam";

change(person);

System.out.println(person.name);
```

Output:

```text
Ram
```

---

# Internal Working

Initially:

```text
STACK
-------------
person → 0x100

HEAP
-------------
0x100 → Person("Shyam")
```

Method call copies reference value:

```text
p = 0x100
```

Now:

```text
STACK
-------------
person → 0x100
p      → 0x100
```

Both references point to SAME object.

Modification:

```java
p.name = "Ram";
```

Heap becomes:

```text
0x100 → Person("Ram")
```

Both references observe updated object.

---

# Important Understanding

Java copied:

* reference value,
* NOT actual object,
* NOT original variable.

---

# 25. VERY IMPORTANT DIFFERENCE

There are two different operations.

| Operation              | Result                  |
| ---------------------- | ----------------------- |
| Modifying object state | affects shared object   |
| Reassigning reference  | affects only local copy |

---

# ✔ Modifying Object State

Example:

```java
p.name = "Ram";
```

This changes heap object itself.

Since both references point to same object:

* changes become visible everywhere.

---

# ❌ Reassigning Reference

Example:

```java
p = new Person();
```

This changes only local copied reference.

Original caller reference remains unchanged.

---

# 26. Proof Java is NOT Pass-by-Reference

```java
static void change(Person p) {

    p = new Person();

    p.name = "Ram";
}
```

---

```java
Person person = new Person();
person.name = "Shyam";

change(person);

System.out.println(person.name);
```

Output:

```text
Shyam
```

---

# Internal Explanation

Initially:

```text
person → 0x100 → Person("Shyam")
p      → 0x100
```

Then:

```java
p = new Person();
```

creates:

```text
0x200 → Person(null)
```

Now:

```text
person → 0x100 → Person("Shyam")

p      → 0x200 → Person(null)
```

Then:

```java
p.name = "Ram";
```

changes NEW object only.

Original object remains unchanged.

---

# Why This Proves Java is NOT Pass-by-Reference

In true pass-by-reference:

* parameter becomes alias to original variable.

So:

```java
p = new Person();
```

would also change caller reference.

But Java never behaves this way.

Therefore:

```text
Java is NOT pass-by-reference.
```

---

# 27. Actual Java Memory Diagram

Before method call:

```text
person ─────→ Person object
              name = "Shyam"
```

During method call:

```text
person ────┐
           ↓
        Person object
           ↑
p ─────────┘
```

Both references point to same object.

After object modification:

```java
p.name = "Ram";
```

Both references observe updated object.

After reassignment:

```java
p = new Person();
```

Now:

```text
person ─→ old object

p      ─→ new object
```

Only local copied reference changed.

---

# 28. Passing Arrays to Methods

Arrays can be passed as parameters.

Example:

```java
static void printArray(int[] arr) {

    for(int n : arr) {
        System.out.println(n);
    }
}
```

Method call:

```java
printArray(numbers);
```

Arrays behave exactly like objects because arrays are objects.

---

# 29. Returning Arrays from Methods

Methods can return arrays.

Example:

```java
static int[] createArray() {

    int[] arr = {1,2,3};

    return arr;
}
```

Receiving returned array:

```java
int[] result = createArray();
```

---

# 30. Varargs (`...`)

Varargs means variable number of arguments.

Syntax:

```java
datatype... variableName
```

Example:

```java
static int sum(int... nums)
```

---

# Varargs Example

```java
static int sum(int... nums) {

    int total = 0;

    for(int n : nums) {
        total += n;
    }

    return total;
}
```

Calls:

```java
sum(1,2,3);
sum(5,10);
sum();
```

---

# Internal Working of Varargs

Compiler internally converts:

```java
sum(1,2,3);
```

into:

```java
sum(new int[]{1,2,3});
```

So varargs are internally arrays.

---

# Rules of Varargs

---

## Varargs Must Be Last Parameter

Correct:

```java
void test(String name, int... nums)
```

Wrong:

```java
void test(int... nums, String name)
```

---

## Only One Varargs Allowed

Wrong:

```java
void test(int... a, int... b)
```

---

# 31. Final Core Concepts

## Arrays

```text
Arrays are heap objects accessed using references.
```

---

## `Arrays.toString()`

```text
Creates readable string representation manually.
```

---

## Binary Search

```text
Repeatedly halves sorted search space.
```

---

## Primitive Passing

```text
Copies actual values.
```

---

## Object Passing

```text
Copies reference values.
```

---

## Java Passing Mechanism

```text
Java is ALWAYS pass-by-value.
```

---

## Object Modification

```text
Object contents change because multiple references point to same heap object.
```

---

## Varargs

```text
Compiler converts varargs into arrays internally.
```

---

# 🔥 Ultimate One-Line Memory Trick

```text
Primitive → copy of data
Object → copy of reference
BUT ALWAYS → copy
```
