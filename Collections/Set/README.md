
# Chapter: Set in Java Collections Framework

## Introduction to Set

In the Java Collections Framework, a **Set** is one of the most important collection types used for storing groups of objects. The fundamental characteristic that distinguishes a Set from other collection types is that a Set does not allow duplicate elements. In other words, every element stored in a Set must be unique according to the rules defined by the implementation. This concept of uniqueness is the very foundation upon which the Set interface is built.

When developers work with collections of data, there are many situations in which duplicates are undesirable. Consider a university management system that stores student roll numbers. Since every student must have a unique roll number, storing duplicate roll numbers would create inconsistencies in the system. Similarly, in an e-commerce application, product IDs should be unique, and in a social networking platform, usernames or email addresses must not be repeated. In all such scenarios, the Set interface becomes an ideal choice because it automatically prevents duplicate entries.

The Set interface is part of the `java.util` package and belongs to the Java Collections Framework. It is not a class but an interface, meaning it only defines a contract that implementation classes must follow. The most important rule of this contract is that duplicates are not allowed.

For example:

```java
Set<String> names = new HashSet<>();

names.add("John");
names.add("David");
names.add("John");

System.out.println(names);
```

Even though the value `"John"` is added twice, the Set stores it only once. The output contains only unique values.

---

# Position of Set in the Collection Hierarchy

To understand Set properly, it is important to understand where it fits in the Java Collections Framework.

The root of the hierarchy is the `Iterable` interface. Any object that can be traversed using a loop implements this interface. The `Collection` interface extends `Iterable` and represents a group of objects. The Set interface extends Collection and specializes its behavior by enforcing uniqueness.

The hierarchy can be represented as:

```text
Iterable
    |
Collection
    |
Set
 |
 +---- HashSet
 |
 +---- LinkedHashSet
 |
 +---- SortedSet
          |
          +---- NavigableSet
                    |
                    +---- TreeSet
```

This hierarchy reflects the gradual addition of functionality. The Collection interface provides basic collection operations. Set adds uniqueness. SortedSet introduces automatic sorting. NavigableSet adds navigation capabilities such as finding the nearest higher or lower element. Finally, TreeSet provides a concrete implementation that combines all these features.

---

# Why Java Needed Set

Before understanding the internal implementations, it is useful to ask a simple question:

**Why did Java introduce Set when List already exists?**

A List is designed to maintain ordered collections of elements and allows duplicates. There are many situations where duplicates are perfectly valid. For example, a shopping cart may contain the same product multiple times, and a list of exam scores may contain repeated values.

However, there are many situations where duplicates must be prevented. Without Set, developers would need to manually check whether an element already exists before inserting it. This would increase code complexity and reduce performance. By introducing Set, Java provided a specialized collection that automatically enforces uniqueness.

The responsibility of preventing duplicates is therefore delegated to the collection itself rather than to the programmer.

---

# Core Principle of Set

The defining principle of a Set is uniqueness.

Whenever an element is added, the Set must determine whether that element already exists. If it does, the new insertion is rejected. If it does not, the element is stored.

Conceptually:

```text
Insert Element
      |
      V
Already Exists?
   /       \
 Yes       No
  |         |
Reject    Store
```

Although this logic appears simple, different Set implementations use very different internal mechanisms to answer the question:

> "Does this element already exist?"

The answer to that question determines the entire internal design of HashSet, LinkedHashSet, and TreeSet.

---
# Common Methods of Set

The `add()` method is used to add an element. It returns `true` if the element is added successfully, and it returns `false` if the element already exists.

```java
Set<String> set = new HashSet<>();

System.out.println(set.add("Java"));
System.out.println(set.add("Java"));
```

Output:

```text
true
false
```

The first time `"Java"` is added, so `true` is returned. The second time `"Java"` is already present, so it is not added again and `false` is returned.

The `remove()` method removes an element from the Set.

```java
set.remove("Java");
```

The `contains()` method checks whether an element is present or not.

```java
set.contains("Java");
```

The `size()` method returns the number of unique elements.

```java
set.size();
```

The `isEmpty()` method checks whether the Set is empty.

```java
set.isEmpty();
```

The `clear()` method removes all elements.

```java
set.clear();
```

The `iterator()` method is used to iterate through a Set.

```java
Iterator<String> iterator = set.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---


# HashSet: The Most Common Set Implementation

HashSet is the most widely used implementation of the Set interface. It is optimized for performance and provides very fast insertion, deletion, and search operations.

A HashSet does not guarantee any ordering of elements. The order in which elements are inserted may not be the order in which they are returned.

For example:

```java
HashSet<String> set = new HashSet<>();

set.add("Apple");
set.add("Banana");
set.add("Mango");

System.out.println(set);
```

The output may appear in a completely different order from the insertion order.

This behavior often surprises beginners, but it is a direct consequence of the internal data structure used by HashSet.


# 1. HashSet Internal Explanation

`HashSet` is the most commonly used implementation of the Set interface. It stores unique elements and does not maintain insertion order. This means the order in which you insert elements may not be the same order in which they are printed. `HashSet` is usually preferred when you want fast performance and do not care about order.

```java
Set<String> set = new HashSet<>();

set.add("Apple");
set.add("Banana");
set.add("Mango");
set.add("Apple");

System.out.println(set);
```

Possible output:

```text
[Apple, Mango, Banana]
```

The output order is not guaranteed.

Internally, `HashSet` uses a `HashMap`. This is one of the most important points to remember. When you create a `HashSet`, internally a `HashMap` is created.

```java
HashSet<String> set = new HashSet<>();
```

Internally, it behaves like this:

```java
HashMap<String, Object> map = new HashMap<>();
```

In a `HashMap`, data is stored in key-value format. But in a `HashSet`, we only care about values, not key-value pairs. So `HashSet` stores your element as the **key** inside the internal `HashMap`, and it stores a dummy object as the value.

Internally, the logic is similar to this:

```java
private transient HashMap<E, Object> map;

private static final Object PRESENT = new Object();

public boolean add(E e) {
    return map.put(e, PRESENT) == null;
}
```

So when you write:

```java
set.add("Java");
```

Internally it becomes:

```java
map.put("Java", PRESENT);
```

The value `PRESENT` is just a dummy object. It has no real meaning. The actual data is stored as the key.

```text
HashSet element internally in HashMap:

Key       Value
------------------
Java  ->  PRESENT
Python -> PRESENT
C++   ->  PRESENT
```

The reason `HashSet` does not allow duplicates is because `HashMap` does not allow duplicate keys. If you try to add `"Java"` again, internally it again calls:

```java
map.put("Java", PRESENT);
```

Since `"Java"` is already present as a key, `HashMap` does not create a new key. That is how `HashSet` prevents duplicates.

---

# How HashSet Adds an Element Internally

When you add an element to a `HashSet`, Java follows several internal steps. First, it calculates the hash code of the element using the `hashCode()` method. Then it uses that hash code to decide the bucket location where the element should be stored. A bucket is like an internal storage position inside the hash table.

```java
set.add("Java");
```

Internally:

```java
"Java".hashCode();
```

After calculating the hash code, Java decides the bucket index. The bucket index tells Java where to place the element inside the internal table.

Conceptually:

```text
hashCode -> bucket index -> store element
```

If the bucket is empty, the element is stored there directly. If the bucket already has some element, Java checks whether the existing element is equal to the new element. For this, Java uses the `equals()` method.

So `HashSet` uses both:

```text
hashCode()
equals()
```

`hashCode()` is used to find the bucket, and `equals()` is used to check whether two objects are actually equal.

---

# HashSet Duplicate Checking

Suppose we write:

```java
Set<String> set = new HashSet<>();

set.add("Java");
set.add("Java");
```

When the first `"Java"` is added, Java calculates its hash code and stores it in the correct bucket. When the second `"Java"` is added, Java again calculates the hash code. Because both strings are the same, they produce the same hash code and go to the same bucket. Then Java uses `equals()` to compare the existing `"Java"` with the new `"Java"`. Since both are equal, the second value is rejected.

This means that in `HashSet`, duplicate checking depends on `hashCode()` and `equals()`.

---

# HashSet with Custom Objects

When we use custom objects inside a `HashSet`, we must override `hashCode()` and `equals()` properly. Otherwise, the Set may allow duplicate-looking objects.

```java
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Now:

```java
Set<Employee> employees = new HashSet<>();

employees.add(new Employee(101, "Ravi"));
employees.add(new Employee(101, "Ravi"));

System.out.println(employees.size());
```

The output may be:

```text
2
```

Even though both objects have the same data, Java treats them as different objects because they are created separately in memory. To fix this, we override `hashCode()` and `equals()`.

```java
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Employee other = (Employee) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
```

Now, if two employees have the same `id`, the `HashSet` treats them as duplicates.

```java
Set<Employee> employees = new HashSet<>();

employees.add(new Employee(101, "Ravi"));
employees.add(new Employee(101, "Ravi"));

System.out.println(employees.size());
```

Output:

```text
1
```

---

# HashSet Collision Handling

A collision happens when two different objects produce the same bucket index. This does not always mean they are equal. It only means they landed in the same bucket.

For example:

```text
Object A -> bucket 5
Object B -> bucket 5
```

Now Java must store both objects in the same bucket. In older versions of Java, objects in the same bucket were stored using a linked list. From Java 8 onwards, if too many elements are stored in the same bucket, the linked list can be converted into a balanced tree structure for better performance. This process is called **treeification**.

So internally, a bucket may contain:

```text
One element
or
Linked list of elements
or
Tree structure if collisions are high
```

This helps `HashSet` maintain good performance even when collisions happen.

---

# HashSet Performance

For most cases, `HashSet` gives constant time performance.

```text
add()      -> O(1) average
remove()   -> O(1) average
contains() -> O(1) average
```

In the worst case, if many collisions happen, performance may reduce. But Java 8 improved this by converting long linked lists into trees, so performance becomes better than older versions.

---

# 2. LinkedHashSet Internal Explanation

`LinkedHashSet` is another implementation of Set. It also stores only unique elements, but unlike `HashSet`, it maintains insertion order. This means the order in which you insert elements is the same order in which they are returned during iteration.

```java
Set<String> set = new LinkedHashSet<>();

set.add("Apple");
set.add("Banana");
set.add("Mango");
set.add("Apple");

System.out.println(set);
```

Output:

```text
[Apple, Banana, Mango]
```

Here, `"Apple"` is added twice, but it is stored only once. Also, the insertion order is maintained.

Internally, `LinkedHashSet` uses a `LinkedHashMap`. A `LinkedHashMap` is similar to a `HashMap`, but it also maintains a doubly linked list to preserve insertion order.

Conceptually, it has two structures working together:

```text
Hash table for uniqueness
Doubly linked list for insertion order
```

The hash table helps it check duplicates quickly, and the linked list helps it remember the order in which elements were inserted.

When you write:

```java
set.add("Java");
```

Internally, it behaves like:

```java
linkedHashMap.put("Java", PRESENT);
```

Again, the element is stored as the key, and a dummy object is stored as the value.

---

# How LinkedHashSet Maintains Order

Suppose we add:

```java
Set<String> set = new LinkedHashSet<>();

set.add("A");
set.add("B");
set.add("C");
```

Internally, hashing is used for storage and duplicate checking. At the same time, a linked structure is maintained like this:

```text
A <-> B <-> C
```

This doubly linked list keeps track of insertion order. So when we print the Set, it follows this linked order.

```java
System.out.println(set);
```

Output:

```text
[A, B, C]
```

If we add `"B"` again:

```java
set.add("B");
```

It is not added again because it already exists. The order also remains the same.

```text
A <-> B <-> C
```

---

# LinkedHashSet Duplicate Checking

`LinkedHashSet` also uses `hashCode()` and `equals()` for duplicate checking because it is based on hashing. So for custom objects, we must override `hashCode()` and `equals()` just like in `HashSet`.

```java
Set<Employee> employees = new LinkedHashSet<>();

employees.add(new Employee(101, "Ravi"));
employees.add(new Employee(101, "Ravi"));
```

If `hashCode()` and `equals()` are properly overridden, only one employee will be stored.

---

# LinkedHashSet Performance

`LinkedHashSet` is slightly slower than `HashSet` because it maintains insertion order using a linked list. However, the performance is still very good.

```text
add()      -> O(1) average
remove()   -> O(1) average
contains() -> O(1) average
```

Use `LinkedHashSet` when you want uniqueness and insertion order.

---

# 3. TreeSet Internal Explanation

Before we can truly understand **TreeSet**, we must first understand the concepts of **SortedSet** and **NavigableSet**, because TreeSet was designed as the final concrete implementation of these interfaces. Many beginners directly learn TreeSet and memorize that it stores data in sorted order, but they often miss the design philosophy behind it. Java's collection framework was carefully designed using abstraction, and TreeSet is the result of several layers of functionality being added gradually.

The hierarchy looks like this:

```text
Iterable
    |
Collection
    |
Set
    |
SortedSet
    |
NavigableSet
    |
TreeSet
```

This means that when we create a TreeSet object, we are not only getting the features of TreeSet itself, but also the features defined by Set, SortedSet, and NavigableSet. Understanding these interfaces is therefore essential to understanding TreeSet completely.

---

# Understanding the Need for SortedSet

The original Set interface guarantees uniqueness. If you store elements inside a Set, duplicate values are automatically prevented. However, Set itself does not make any promise regarding the ordering of elements.

Consider the following example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Possible output:

```text
[20, 50, 10, 30]
```

Notice something important. The elements are unique, but they are not sorted. The order appears unpredictable because HashSet uses hashing internally.

Now imagine a banking application storing account numbers, or a university system storing student rankings, or an online shopping application displaying product prices. In such systems, data often needs to remain sorted automatically. Developers should not have to manually sort the collection every time they insert an element.

This requirement led to the introduction of the **SortedSet** interface.

---

# What is SortedSet?

SortedSet is an interface that extends Set and adds the guarantee that all elements will remain sorted automatically.

The declaration looks like this:

```java
public interface SortedSet<E> extends Set<E>
```

The most important characteristic of a SortedSet is that every element stored inside it is arranged according to a sorting rule. This sorting rule may be the natural ordering of the elements or a custom ordering defined by the programmer.

The moment an element is inserted, the collection automatically places it in its proper sorted position.

Consider the following example:

```java
SortedSet<Integer> numbers = new TreeSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 50]
```

Notice that the insertion order was:

```text
50
10
30
20
```

but the stored order became:

```text
10
20
30
50
```

This happens because SortedSet ensures that elements are always maintained in sorted order.

---

# Natural Ordering in SortedSet

When Java sorts elements automatically, it needs some way to compare them. For predefined classes such as Integer, String, Double, Character, and LocalDate, Java already knows how to compare objects because these classes implement the Comparable interface.

For example:

```java
Integer a = 10;
Integer b = 20;

System.out.println(a.compareTo(b));
```

Output:

```text
-1
```

A negative value indicates that 10 is smaller than 20.

Because Integer implements Comparable, TreeSet knows exactly where to place each integer during insertion.

Similarly, String objects are sorted alphabetically:

```java
TreeSet<String> names = new TreeSet<>();

names.add("David");
names.add("John");
names.add("Alice");

System.out.println(names);
```

Output:

```text
[Alice, David, John]
```

The elements are automatically arranged according to alphabetical order.

---

# Methods Introduced by SortedSet

SortedSet adds several important methods that allow developers to work with sorted data efficiently.

The `first()` method returns the smallest element.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);

System.out.println(numbers.first());
```

Output:

```text
10
```

Internally, TreeSet traverses the leftmost branch of the tree because the smallest element always resides at the far left of a binary search tree.

The `last()` method returns the largest element.

```java
System.out.println(numbers.last());
```

Output:

```text
50
```

Internally, TreeSet traverses the rightmost branch because the largest element always resides at the far right.

The `headSet()` method returns all elements smaller than a given value.

```java
System.out.println(numbers.headSet(30));
```

Output:

```text
[10]
```

The `tailSet()` method returns all elements greater than or equal to the specified value.

```java
System.out.println(numbers.tailSet(30));
```

Output:

```text
[30, 50]
```

The `subSet()` method returns a specific range.

```java
TreeSet<Integer> values = new TreeSet<>();

values.add(10);
values.add(20);
values.add(30);
values.add(40);
values.add(50);

System.out.println(values.subSet(20, 50));
```

Output:

```text
[20, 30, 40]
```

These operations become extremely efficient because TreeSet stores data in a tree structure rather than a simple array.

---

# Why NavigableSet Was Introduced

Although SortedSet provided automatic sorting, developers often needed more advanced navigation capabilities.

Consider a scenario where a company stores employee IDs in sorted order.

```text
10
20
30
40
50
```

Suppose a developer wants to find the next employee ID greater than 25.

The answer should be:

```text
30
```

Similarly, the developer might want:

* The nearest larger value
* The nearest smaller value
* The smallest value
* The largest value
* The ability to remove boundary values

SortedSet did not provide direct support for these operations. To solve this problem, Java introduced the **NavigableSet** interface.

---

# What is NavigableSet?

NavigableSet extends SortedSet and provides navigation methods for moving through a sorted collection.

```java
public interface NavigableSet<E>
       extends SortedSet<E>
```

The purpose of NavigableSet is to allow developers to efficiently locate neighboring elements without writing custom search algorithms.

TreeSet is the most commonly used implementation of NavigableSet.

This means TreeSet automatically inherits all navigation capabilities.

---

# Important Methods of NavigableSet

The `higher()` method returns the smallest element strictly greater than the specified value.

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(10);
set.add(20);
set.add(30);
set.add(40);

System.out.println(set.higher(20));
```

Output:

```text
30
```

The `lower()` method returns the largest element strictly smaller than the specified value.

```java
System.out.println(set.lower(20));
```

Output:

```text
10
```

The `ceiling()` method returns the specified value if it exists; otherwise, it returns the next greater element.

```java
System.out.println(set.ceiling(25));
```

Output:

```text
30
```

The `floor()` method returns the specified value if it exists; otherwise, it returns the next smaller element.

```java
System.out.println(set.floor(25));
```

Output:

```text
20
```

The `first()` method returns the smallest element.

```java
System.out.println(set.first());
```

Output:

```text
10
```

The `last()` method returns the largest element.

```java
System.out.println(set.last());
```

Output:

```text
40
```
---
These methods are particularly useful in scheduling systems, booking applications, reservation systems, and database indexing operations.

---
# Why AbstractSet Exists

Before understanding TreeSet, we must understand AbstractSet because TreeSet directly extends it.

Actual declaration:

```java
public class TreeSet<E>
       extends AbstractSet<E>
       implements NavigableSet<E>
```

Many developers ignore AbstractSet because they rarely use it directly. However, it plays an important role in the internal design of the Collections Framework.

Java designers noticed that all Set implementations require certain common behaviors.

For example:

* equals()
* hashCode()
* removeAll()

Every Set implementation should behave the same way for these methods.

Without AbstractSet:

```text
HashSet would implement equals()
TreeSet would implement equals()
LinkedHashSet would implement equals()
```

The same code would be duplicated multiple times.

To avoid duplication, Java created AbstractSet.

AbstractSet provides common Set behavior so that subclasses can focus only on their storage mechanism.

---

# What AbstractSet Provides

AbstractSet already implements:

```java
equals()
hashCode()
removeAll()
```

For example:

```java
Set<Integer> set1 = new HashSet<>();
set1.add(10);
set1.add(20);

Set<Integer> set2 = new TreeSet<>();
set2.add(20);
set2.add(10);

System.out.println(set1.equals(set2));
```

Output:

```text
true
```

Although the implementations are different, AbstractSet ensures that equality follows Set rules.

A Set is considered equal to another Set if both contain the same elements regardless of order.

---

# Introduction to TreeSet

After understanding Set, SortedSet, and NavigableSet, we can finally understand TreeSet.

TreeSet is the concrete class that implements NavigableSet.

```java
public class TreeSet<E>
       extends AbstractSet<E>
       implements NavigableSet<E>
```

TreeSet provides four major guarantees:

1. Elements are unique.
2. Elements are automatically sorted.
3. Navigation methods are available.
4. Searching is efficient.

These capabilities make TreeSet one of the most sophisticated collection implementations in the Java Collections Framework.

---

# Internal Implementation of TreeSet

Many developers assume that TreeSet directly manages its own tree structure. In reality, TreeSet delegates all storage responsibilities to another collection class called TreeMap.

The relationship is:

```text
TreeSet
   |
   V
TreeMap
   |
   V
Red-Black Tree
```

Internally, TreeSet contains a TreeMap.

The actual source code looks similar to:

```java
private transient NavigableMap<E,Object> m;
```

When a TreeSet object is created:

```java
TreeSet<Integer> set = new TreeSet<>();
```

internally Java creates:

```java
TreeMap<Integer,Object> map =
        new TreeMap<>();
```

The actual elements are stored as keys inside the TreeMap.

Just like HashSet uses HashMap, TreeSet uses TreeMap.

---

# Why TreeSet Uses TreeMap

TreeMap already provides:

* Sorted storage
* Fast searching
* Automatic balancing
* Navigation support

Rather than implementing all these features again, TreeSet simply reuses TreeMap.

When we write:

```java
set.add(100);
```

internally TreeSet performs:

```java
map.put(100, PRESENT);
```

where `PRESENT` is a dummy object.

Conceptually:

```text
100 -> PRESENT
200 -> PRESENT
300 -> PRESENT
```

The keys represent the actual elements of the Set.

The values are meaningless placeholders.

---

# Red-Black Tree: The Foundation of TreeSet

The real power of TreeSet comes from the Red-Black Tree used internally by TreeMap.

A Red-Black Tree is a self-balancing binary search tree.

In a binary search tree:

* Smaller values go left.
* Larger values go right.

For example:

```text
        50
       /  \
     20    80
    /
   10
```

This arrangement allows searching to happen much faster than scanning a list element by element.

However, a normal binary search tree can become unbalanced.

For example:

```text
10
 \
 20
  \
   30
    \
     40
```

This structure behaves almost like a linked list.

Search performance degrades significantly.

To solve this problem, TreeMap uses a Red-Black Tree.

A Red-Black Tree automatically performs balancing operations whenever elements are inserted or removed.

These balancing operations include:

* Left rotation
* Right rotation
* Recoloring

As a result, the tree remains balanced and searching remains efficient.

The height of the tree remains approximately:

```text
O(log n)
```

which ensures good performance.

---

# How TreeSet Inserts Elements

Suppose we insert:

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(50);
set.add(20);
set.add(80);
set.add(10);
```

When 50 is inserted:

```text
50
```

When 20 is inserted:

```text
   50
  /
20
```

TreeSet compares:

```java
20.compareTo(50)
```

Since the result is negative, 20 is placed on the left.

When 80 is inserted:

```text
   50
  /  \
20   80
```

TreeSet compares:

```java
80.compareTo(50)
```

Since the result is positive, 80 is placed on the right.

When 10 is inserted:

```text
      50
     /  \
   20    80
  /
10
```

After insertion, the Red-Black Tree algorithms may perform rotations and recoloring to maintain balance.

---

# How TreeSet Detects Duplicates

This is one of the most important concepts in TreeSet.

HashSet uses:

```text
hashCode()
equals()
```

TreeSet does not.

TreeSet uses:

```text
compareTo()
or
Comparator
```

Suppose:

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(10);
set.add(10);
```

Internally:

```java
10.compareTo(10)
```

returns:

```text
0
```

A comparison result of zero tells TreeSet that both objects are considered equal.

Therefore, the second value is rejected.

This means that duplicate detection in TreeSet is entirely based on comparison logic.

---

# Performance Characteristics of TreeSet

Because TreeSet uses a Red-Black Tree internally, its major operations have logarithmic complexity.

```text
add()      O(log n)
remove()   O(log n)
contains() O(log n)
```

This is slower than HashSet, which usually operates in constant time.

However, TreeSet provides automatic sorting and navigation features that HashSet cannot provide.

Therefore, TreeSet is chosen when ordering and range-based operations are more important than raw insertion speed.

---
`TreeSet` is a Set implementation that stores unique elements in sorted order. Unlike `HashSet` and `LinkedHashSet`, `TreeSet` does not use hashing. Internally, it uses a `TreeMap`, which is based on a Red-Black Tree.

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

The elements are automatically sorted in ascending order.

Internally, `TreeSet` behaves like this:

```java
TreeMap<Integer, Object> map = new TreeMap<>();
```

When you add an element:

```java
numbers.add(10);
```

Internally:

```java
map.put(10, PRESENT);
```

Again, the element is stored as the key, and a dummy object is stored as the value.

---



# Comparable in TreeSet

`TreeSet` needs comparison logic because it must decide the sorted position of every element. For predefined classes like `Integer`, `String`, and `Double`, Java already knows how to compare them because these classes implement the `Comparable` interface.

The `Comparable` interface has one method:

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

The `compareTo()` method returns:

```text
negative value -> current object is smaller
zero           -> both objects are equal
positive value -> current object is greater
```

Example:

```java
Integer a = 10;
Integer b = 20;

System.out.println(a.compareTo(b));
```

Output:

```text
-1
```

This means `10` is smaller than `20`.

When we add values to a `TreeSet`, internally comparison happens using `compareTo()`.

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(50);
set.add(20);
set.add(80);
```

When `20` is added, TreeSet compares it with `50`.

```java
20.compareTo(50);
```

This returns a negative value, so `20` goes to the left side. When `80` is added:

```java
80.compareTo(50);
```

This returns a positive value, so `80` goes to the right side.

---

# TreeSet with Custom Objects

If we store custom objects in a `TreeSet`, Java does not automatically know how to sort them. For example:

```java
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Now:

```java
Set<Employee> employees = new TreeSet<>();

employees.add(new Employee(101, "Ravi"));
employees.add(new Employee(102, "Priya"));
```

This will throw a `ClassCastException` because TreeSet does not know how to compare `Employee` objects. Should it sort by `id`, by `name`, or by something else? Java cannot decide by itself.

To solve this, we implement `Comparable`.

```java
class Employee implements Comparable<Employee> {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
```

Now:

```java
Set<Employee> employees = new TreeSet<>();

employees.add(new Employee(103, "Ravi"));
employees.add(new Employee(101, "Priya"));
employees.add(new Employee(102, "Amit"));

System.out.println(employees);
```

Output:

```text
[101 Priya, 102 Amit, 103 Ravi]
```

The employees are sorted by `id`.

---

# Duplicate Checking in TreeSet

This is a very important interview point. `HashSet` uses `hashCode()` and `equals()` to check duplicates. But `TreeSet` mainly uses `compareTo()` or `Comparator` to check duplicates.

If `compareTo()` returns `0`, TreeSet treats the objects as duplicates.

```java
class Employee implements Comparable<Employee> {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }
}
```

Now:

```java
Set<Employee> employees = new TreeSet<>();

employees.add(new Employee(101, "Ravi"));
employees.add(new Employee(101, "Priya"));
```

Here, both employees have the same `id`. So:

```java
Integer.compare(101, 101);
```

returns:

```text
0
```

TreeSet treats them as duplicates and stores only one object.

This means in `TreeSet`, duplicate checking is based on comparison result, not primarily on `equals()`.

---

# Comparator in TreeSet

`Comparable` gives natural sorting order. But sometimes we want different sorting orders. For example, one time we may want to sort employees by `id`, and another time we may want to sort employees by `name`. In such cases, we use `Comparator`.

```java
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
```

Sort by name:

```java
Comparator<Employee> nameComparator = new Comparator<Employee>() {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.name.compareTo(e2.name);
    }
};

Set<Employee> employees = new TreeSet<>(nameComparator);

employees.add(new Employee(103, "Ravi"));
employees.add(new Employee(101, "Amit"));
employees.add(new Employee(102, "Priya"));

System.out.println(employees);
```

Output:

```text
[101 Amit, 102 Priya, 103 Ravi]
```

Here, sorting is done by name, not by id.

Using lambda:

```java
Set<Employee> employees = new TreeSet<>(
    (e1, e2) -> e1.name.compareTo(e2.name)
);
```

---


# Difference Between HashSet, LinkedHashSet, and TreeSet

`HashSet` is best when you want unique elements and fast performance, but you do not care about order. It internally uses `HashMap`. It uses `hashCode()` and `equals()` to check duplicates. It allows one `null` value.

`LinkedHashSet` is best when you want unique elements and insertion order. It internally uses `LinkedHashMap`. It also uses `hashCode()` and `equals()` to check duplicates. It allows one `null` value.

`TreeSet` is best when you want unique elements in sorted order. It internally uses `TreeMap`. It uses `compareTo()` or `Comparator` for sorting and duplicate checking. It does not allow `null` in normal sorting because null cannot be compared with other values.

```text
HashSet
- Internal structure: HashMap
- Order: No guaranteed order
- Duplicate checking: hashCode() and equals()
- Performance: O(1) average
- Null: Allows one null

LinkedHashSet
- Internal structure: LinkedHashMap
- Order: Insertion order
- Duplicate checking: hashCode() and equals()
- Performance: O(1) average
- Null: Allows one null

TreeSet
- Internal structure: TreeMap
- Order: Sorted order
- Duplicate checking: compareTo() or Comparator
- Performance: O(log n)
- Null: Does not allow null in normal usage
```

---

# Complete Example Comparing All Three

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(30);
        hashSet.add(10);
        hashSet.add(20);
        hashSet.add(10);

        System.out.println("HashSet: " + hashSet);

        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(30);
        linkedHashSet.add(10);
        linkedHashSet.add(20);
        linkedHashSet.add(10);

        System.out.println("LinkedHashSet: " + linkedHashSet);

        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(10);

        System.out.println("TreeSet: " + treeSet);
    }
}
```

Possible output:

```text
HashSet: [20, 10, 30]
LinkedHashSet: [30, 10, 20]
TreeSet: [10, 20, 30]
```

Here, all three remove duplicates. The difference is only in ordering and internal implementation.

---

# Final Interview Summary

A Set is a collection that stores only unique elements. It is an interface that extends `Collection`. The main implementations are `HashSet`, `LinkedHashSet`, and `TreeSet`.

`HashSet` internally uses `HashMap`. It stores elements as keys and uses a dummy object as value. It does not maintain order. It uses `hashCode()` to find the bucket and `equals()` to check duplicates.

`LinkedHashSet` internally uses `LinkedHashMap`. It works like `HashSet`, but it also maintains a doubly linked list to preserve insertion order.

`TreeSet` internally uses `TreeMap`. It stores elements in sorted order using a Red-Black Tree. It does not use `hashCode()` and `equals()` mainly for duplicate checking. Instead, it uses `compareTo()` from `Comparable` or `compare()` from `Comparator`. If comparison returns `0`, TreeSet treats the elements as duplicates.

The simplest memory trick is:

```text
HashSet       -> Unique + Fast + No order
LinkedHashSet -> Unique + Insertion order
TreeSet       -> Unique + Sorted order
```
