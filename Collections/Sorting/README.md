# Complete Sorting in Java 

# 1. Introduction to Sorting

Sorting is the process of arranging data in a meaningful order.

In programming, data is often stored in random order.
Sorting helps us organize that data properly so that it becomes easier to read, search, process, and analyze.

The order may be:

* ascending order
* descending order
* alphabetical order
* salary order
* marks order
* age order

Sorting is one of the most important concepts in programming because almost every real-world application uses sorting internally.

---

# Real-Life Understanding of Sorting

Imagine a teacher arranging students inside a classroom.

The teacher may arrange students:

* according to roll number
* according to marks
* according to height
* according to attendance

This arrangement process is called sorting.

Similarly, Java also arranges data using sorting mechanisms.

---

# Why Sorting is Important

Suppose we have data stored randomly.

```java
50, 10, 30, 70, 20
```

This data looks unorganized.

After sorting:

```java
10, 20, 30, 50, 70
```

Now the data becomes clean and understandable.

Sorting is important because:

* searching becomes faster
* reports become readable
* ranking becomes easier
* binary search becomes possible
* data processing becomes efficient

---

# 2. Sorting in Java Collections

Java provides a framework called the Collection Framework.

The Collection Framework is used to store groups of objects.

Examples of collections are:

```java
ArrayList
LinkedList
Vector
HashSet
TreeSet
```

When we want to arrange data inside collections, Java provides sorting mechanisms.

---

# Collections Utility Class

Java provides a utility class called:

```java
Collections
```

This class contains many useful methods.

The most important method for sorting is:

```java
Collections.sort()
```

This method is used for sorting lists.

---

# Syntax of Sorting

```java
Collections.sort(list);
```

or

```java
Collections.sort(list, comparator);
```

---

# 3. The Most Important Concept in Sorting

The most important concept is:

# Sorting always depends on comparison.

Java repeatedly compares elements and decides:

> “Which element should come first?”

After comparison, Java rearranges elements.

So internally:

# Sorting = Comparison + Rearrangement

---

# Internal Working of Sorting

Suppose we have:

```java
[50, 10, 30]
```

Java internally performs comparisons like:

```text
50 compared with 10
50 compared with 30
10 compared with 30
```

After comparison, Java rearranges positions.

Finally:

```java
[10, 30, 50]
```

is produced.

---

# Internal Sorting Algorithms Used by Java

Java internally uses advanced sorting algorithms such as:

* TimSort
* MergeSort

Older Java versions also used QuickSort in some cases.

These algorithms repeatedly compare and rearrange elements internally.

---

# Very Important Internal Understanding

Sorting algorithms themselves do not understand business objects like:

* Student
* Employee
* Product

Sorting algorithms only understand:

```text
negative
positive
zero
```

These comparison signals are provided by:

* Comparable
* Comparator

---

# 4. Sorting Primitive Wrapper Classes

Java can directly sort classes such as:

```java
Integer
Double
Character
String
```

because these classes already implement Comparable internally.

---

# Example 1 — Sorting Integers

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        numbers.add(50);
        numbers.add(10);
        numbers.add(30);

        Collections.sort(numbers);

        System.out.println(numbers);
    }
}
```

---

# Output

```java
[10, 30, 50]
```

---

# Complete Explanation

The Integer class already knows how numbers should be compared.

When:

```java
Collections.sort(numbers);
```

is executed, Java internally compares numbers repeatedly.

Java internally performs operations like:

```text
50 compared with 10
50 compared with 30
10 compared with 30
```

After every comparison, Java rearranges the references internally until proper order is achieved.

---

# 5. Sorting Strings

Strings are sorted alphabetically.

---

# Example 2 — Sorting Strings

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("John");
        names.add("Alex");
        names.add("David");

        Collections.sort(names);

        System.out.println(names);
    }
}
```

---

# Output

```java
[Alex, David, John]
```

---

# Complete Explanation

The String class already implements Comparable internally.

Java internally uses:

```java
compareTo()
```

for comparison.

Java compares strings alphabetically using Unicode values.

For example:

```java
"John".compareTo("Alex")
```

returns a positive value because `"John"` comes after `"Alex"` alphabetically.

Therefore Java places `"Alex"` before `"John"`.

---

# 6. Problem with Custom Objects

Suppose we create our own class:

```java
class Student {

    int id;
    String name;
    int marks;
}
```

Now Java becomes confused.

Java asks:

```text
How should Student objects be sorted?
```

Java does not know whether sorting should happen by:

* id
* name
* marks

Therefore we must provide comparison logic manually.

---

# 7. Comparable Interface

Comparable is used when objects have one natural default sorting order.

In Comparable, the object itself defines comparison logic.

---

# Real-Life Understanding of Comparable

Suppose students in a school are always arranged according to roll number.

That fixed natural ordering is Comparable.

The students themselves know their ordering rule.

---

# Comparable Syntax

```java
class Student implements Comparable<Student>
```

---

# compareTo() Method

```java
public int compareTo(Student s)
```

This method compares the current object with another object.

---

# Meaning of Return Values

If compareTo() returns:

* negative value → current object smaller
* positive value → current object greater
* zero → both objects equal

---

# Example 3 — Sorting Students by ID Using Comparable

```java
import java.util.*;

class Student implements Comparable<Student> {

    int id;
    String name;
    int marks;

    Student(int id, String name, int marks) {

        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student s) {

        return this.id - s.id;
    }
}

public class Main {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student(103, "John", 85));
        students.add(new Student(101, "David", 92));
        students.add(new Student(102, "Alex", 78));

        Collections.sort(students);

        for(Student s : students) {

            System.out.println(
                s.id + " " +
                s.name + " " +
                s.marks
            );
        }
    }
}
```

---

# Output

```java
101 David 92
102 Alex 78
103 John 85
```

---

# Complete Explanation

Here, Student objects know how they should be sorted.

The comparison logic exists inside the Student class.

When:

```java
Collections.sort(students);
```

is executed, Java repeatedly calls:

```java
compareTo()
```

Suppose Java compares:

```text
103 and 101
```

Internally:

```java
103 - 101 = 2
```

Positive value means:

```text
103 is greater than 101
```

So Java places 101 before 103.

This process continues repeatedly until all students become sorted.

---

# Limitation of Comparable

Comparable supports only one default sorting logic.

For example, Student objects can naturally sort by:

* id

OR

* name

OR

* marks

but not all dynamically.

This limitation leads to Comparator.

---

# 8. Comparator Interface

Comparator is used for flexible custom sorting.

Comparator keeps comparison logic outside the class.

---

# Real-Life Understanding of Comparator

Suppose a teacher says:

```text
Today arrange students by marks.
Tomorrow arrange students by names.
```

This changing sorting logic is Comparator.

---

# Comparator Syntax

```java
class NameComparator
implements Comparator<Student>
```

---

# compare() Method

```java
public int compare(Student s1,
                   Student s2)
```

This method compares two external objects.

---

# Example 4 — Sorting Students by Name

```java
import java.util.*;

class Student {

    int id;
    String name;
    int marks;

    Student(int id,
            String name,
            int marks) {

        this.id = id;
        this.name = name;
        this.marks = marks;
    }
}

class NameComparator
implements Comparator<Student> {

    @Override
    public int compare(Student s1,
                       Student s2) {

        return s1.name.compareTo(
                s2.name);
    }
}

public class Main {

    public static void main(String[] args) {

        List<Student> students =
                new ArrayList<>();

        students.add(
            new Student(103,
            "John", 85));

        students.add(
            new Student(101,
            "David", 92));

        students.add(
            new Student(102,
            "Alex", 78));

        Collections.sort(
            students,
            new NameComparator());

        for(Student s : students) {

            System.out.println(
                s.id + " " +
                s.name);
        }
    }
}
```

---

# Output

```java
102 Alex
101 David
103 John
```

---

# Complete Explanation

Java repeatedly calls:

```java
compare(s1, s2)
```

Suppose Java compares:

```java
"John".compareTo("David")
```

This returns a positive value because `"John"` comes after `"David"` alphabetically.

Therefore Java places `"David"` before `"John"`.

---

# 9. Descending Order Sorting

Descending order means arranging from largest to smallest.

---

# Example 5 — Descending Integer Sorting

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> numbers =
            Arrays.asList(50, 10, 30);

        Collections.sort(
            numbers,
            Collections.reverseOrder());

        System.out.println(numbers);
    }
}
```

---

# Output

```java
[50, 30, 10]
```

---

# Complete Explanation

Normally sorting happens ascending.

`reverseOrder()` reverses comparison direction internally.

So the largest element appears first.

---

# 10. Sorting Using Java 8 Lambda Expressions

Java 8 introduced lambda expressions to simplify sorting.

Instead of creating separate Comparator classes, we can directly write comparison logic.

---

# Example 6 — Lambda Sorting

```java
students.sort(
    (s1, s2) ->
        s1.name.compareTo(s2.name)
);
```

---

# Complete Explanation

This lambda expression means:

```text
Compare student s1 and student s2
using their names.
```

This removes the need for separate Comparator classes.

---

# 11. Comparator.comparing()

Modern Java commonly uses:

```java
Comparator.comparing()
```

---

# Example 7 — Sorting by Marks

```java
students.sort(
    Comparator.comparing(
        s -> s.marks
    )
);
```

---

# Example 8 — Descending Sorting

```java
students.sort(
    Comparator.comparing(
        (Student s) -> s.marks
    ).reversed()
);
```

---

# Output

```java
David 92
John 85
Alex 78
```

---

# 12. Sorting Using Streams

Java 8 introduced Streams for functional-style data processing.

Streams provide:

```java
sorted()
```

method for sorting.

---

# Example 9 — Stream Sorting

```java
List<Integer> result =

numbers.stream()
       .sorted()
       .collect(Collectors.toList());
```

---

# Internal Flow of Stream Sorting

```text
Collection
   ↓
Stream
   ↓
sorted()
   ↓
collect()
```

---

# Important Stream Concept

Streams do NOT modify original collection.

Streams create new sorted pipelines.

---

# Example

Original list:

```java
[50, 10, 30]
```

Sorted stream result:

```java
[10, 30, 50]
```

Original list remains unchanged.

---

# 13. TreeSet Sorting

TreeSet automatically stores elements in sorted order.

---

# Example 10 — TreeSet Sorting

```java
TreeSet<Integer> set =
        new TreeSet<>();

set.add(50);
set.add(10);
set.add(30);

System.out.println(set);
```

---

# Output

```java
[10, 30, 50]
```

---

# Complete Explanation

TreeSet internally uses a Red-Black Tree structure.

While inserting elements, TreeSet continuously compares values and places them in sorted positions.

That is why TreeSet always stores elements in sorted order.

---

# 14. Internal Memory Concept During Sorting

During sorting, Java does NOT recreate objects.

Java only rearranges references.

---

# Before Sorting

```text
ref1 → Student103
ref2 → Student101
```

---

# After Sorting

```text
ref1 → Student101
ref2 → Student103
```

The objects remain the same.

Only their positions change.

---

# 15. What Happens Without Comparison Logic

If we try to sort custom objects without Comparable or Comparator:

```java
Collections.sort(studentList);
```

Java throws:

```java
ClassCastException
```

because Java does not know how to compare Student objects.

---

# Final Core Understanding

Sorting in Java means arranging data in meaningful order.

Internally, sorting always works through repeated comparison and rearrangement.

Comparable provides natural self-comparison behavior.

Comparator provides flexible external comparison behavior.

Streams provide modern functional sorting.

TreeSet provides automatic sorted storage.

Everything in Java sorting finally depends on:

# comparison + rearrangement
