# Chapter 2: ArrayList Internal Implementation — Complete Textbook-Level Explanation

According to the official Oracle Java documentation, `ArrayList` is a **resizable-array implementation of the List interface**. This description appears simple, but it contains the entire design philosophy of ArrayList. Java designers wanted a collection that behaves like an array from the programmer's perspective while eliminating the biggest limitation of arrays, namely their fixed size. Instead of forcing developers to manually create larger arrays and copy elements, ArrayList performs these tasks automatically. This gives developers the convenience of dynamic growth while still retaining the speed advantages of array-based storage.

Java Documentation Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html)

---

# Why ArrayList Exists

Before Collections Framework was introduced, developers primarily used arrays.

```java
String[] names = new String[5];
```

Arrays are extremely efficient because they store elements in contiguous memory locations. This allows direct index access.

```java
names[3];
```

The JVM can immediately calculate the memory location of index 3 and retrieve the element.

However, arrays suffer from one major limitation.

```text
Fixed Size
```

If an application needs to store more elements than the array can hold, a larger array must be created manually.

```text
Create New Array
Copy Old Elements
Discard Old Array
```

This process is cumbersome and error-prone.

ArrayList was designed to solve this problem.

It preserves the speed benefits of arrays while automatically handling growth and resizing.

---

# Declaration of ArrayList

The official declaration is:

```java
public class ArrayList<E>
       extends AbstractList<E>
       implements List<E>,
                  RandomAccess,
                  Cloneable,
                  Serializable
```

Several parts of this declaration are important.

### Extends AbstractList

ArrayList inherits common List behavior such as:

```java
equals()
hashCode()
iterator()
listIterator()
subList()
```

from AbstractList.

---

### Implements List

This means ArrayList must fulfill the List contract.

It must support:

```java
add()
remove()
get()
set()
indexOf()
```

and other List operations.

---

### Implements RandomAccess

This is a marker interface.

Java documentation states that RandomAccess indicates fast random access.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/RandomAccess.html](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/RandomAccess.html)

This tells algorithms:

```text
ArrayList supports fast index lookup.
```

---

### Implements Cloneable

Supports:

```java
clone()
```

---

### Implements Serializable

Supports object serialization.

---

# Internal Storage of ArrayList

The most important field inside ArrayList is:

```java
transient Object[] elementData;
```

This field is the actual storage location.

Internally:

```text
ArrayList
      |
      V
Object[]
```

Everything stored inside an ArrayList eventually ends up inside this array.

Suppose we create:

```java
ArrayList<String> list =
        new ArrayList<>();
```

and add:

```java
list.add("Java");
list.add("Spring");
list.add("Hibernate");
```

Internally:

```text
Index     Value
----------------
0         Java
1         Spring
2         Hibernate
3         null
4         null
5         null
...
```

This structure is nearly identical to a normal array.

---

# Size vs Capacity

One of the most misunderstood ArrayList concepts is the difference between size and capacity.

### Size

Number of actual elements.

Example:

```java
list.add("Java");
list.add("Spring");
```

Size:

```text
2
```

because only two elements exist.

---

### Capacity

Number of elements the internal array can hold before resizing.

Example:

```text
Capacity = 10
```

means:

```text
10 storage slots available
```

even if only 2 elements exist.

Therefore:

```text
Size = 2
Capacity = 10
```

is perfectly possible.

---

# Default Capacity

Many developers believe:

```text
ArrayList default capacity = 10
```

This statement is incomplete.

In modern Java:

```java
new ArrayList<>();
```

does not immediately allocate an array of size 10.

Internally:

```java
elementData =
DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
```

which is a shared empty array.

At this point:

```text
Size = 0
Capacity = 0
```

No real storage has been allocated yet.

This optimization reduces memory consumption.

---

# First Insertion

Suppose:

```java
ArrayList<String> list =
        new ArrayList<>();

list.add("Java");
```

Now ArrayList needs actual storage.

Java allocates:

```text
Capacity = 10
```

Internal structure:

```text
Index     Value
----------------
0         Java
1         null
2         null
3         null
...
9         null
```

At this moment:

```text
Size = 1
Capacity = 10
```

---

# Internal Working of add(E e)

The Java documentation states:

> Appends the specified element to the end of this list.

Although this sounds simple, internally several operations occur.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#add(E)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#add%28E%29)

When:

```java
list.add("Java");
```

is executed:

### Step 1

Check whether sufficient capacity exists.

```java
ensureCapacityInternal();
```

---

### Step 2

Resize if necessary.

If the internal array is full:

```java
grow();
```

is invoked.

---

### Step 3

Store element.

```java
elementData[size] = e;
```

---

### Step 4

Increase size.

```java
size++;
```

---

### Step 5

Increment modification count.

```java
modCount++;
```

This supports fail-fast iterators.

---

# Dynamic Resizing

Suppose capacity:

```text
10
```

and all slots become occupied.

```text
1
2
3
...
10
```

Now:

```java
list.add(11);
```

requires more space.

ArrayList must grow.

---

# Growth Algorithm

The JDK growth formula is approximately:

```java
newCapacity =
oldCapacity + (oldCapacity >> 1);
```

This means:

```text
10 → 15
15 → 22
22 → 33
33 → 49
49 → 73
```

Capacity grows by approximately:

```text
1.5x
```

each time.

---

# Why Not Double Capacity?

Many developers ask:

```text
Why not 2x?
```

The answer involves memory efficiency.

Doubling:

```text
10 → 20 → 40 → 80
```

wastes more memory.

Growing by 1.5x provides a balance between:

```text
Memory Usage
Resize Frequency
```

---

# Internal Resize Process

When growth occurs:

### Step 1

Create larger array.

```java
new Object[newCapacity]
```

---

### Step 2

Copy old elements.

Using:

```java
Arrays.copyOf()
```

---

### Step 3

Replace old array reference.

```java
elementData = newArray;
```

---

### Step 4

Insert new element.

The old array becomes eligible for garbage collection.

---

# Internal Working of get(int index)

Java documentation:

> Returns the element at the specified position.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#get(int)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#get%28int%29)

When:

```java
list.get(2);
```

is executed:

ArrayList performs:

```java
elementData[2];
```

No traversal.

No iteration.

No searching.

Direct array access.

This is why:

```text
get() = O(1)
```

---

# Why ArrayList is Fast for Reading

Suppose:

```java
list.get(100000);
```

Even though the index is huge:

```text
100000
```

Java directly calculates:

```text
Memory Address
```

for that index.

This is the advantage of contiguous array storage.

---

# Internal Working of set(int index,E element)

Java documentation:

> Replaces the element at the specified position.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#set(int,E)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#set%28int,E%29)

Suppose:

```java
list.set(1,"Spring Boot");
```

Internally:

```java
elementData[1] = "Spring Boot";
```

Old value:

```text
Spring
```

is replaced.

No shifting.

No resizing.

Complexity:

```text
O(1)
```

---

# Internal Working of remove(int index)

Java documentation:

> Removes the element at the specified position.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#remove(int)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#remove%28int%29)

Suppose:

```text
Index     Value
----------------
0         A
1         B
2         C
3         D
```

Now:

```java
list.remove(1);
```

removes:

```text
B
```

However arrays cannot contain gaps.

Java must shift:

```text
C ← left
D ← left
```

Result:

```text
A C D
```

Internally:

```java
System.arraycopy()
```

is used.

Because shifting occurs:

```text
remove() = O(n)
```

---

# Internal Working of add(int index,E element)

Suppose:

```text
A B C D
```

Insert:

```java
list.add(1,"X");
```

Result should be:

```text
A X B C D
```

Java must shift:

```text
D → right
C → right
B → right
```

Then insert:

```text
X
```

Internally:

```java
System.arraycopy()
```

performs the shift.

Therefore:

```text
add(index) = O(n)
```

---

# Internal Working of contains()

Java documentation:

> Returns true if this list contains the specified element.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#contains(java.lang.Object)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html#contains%28java.lang.Object%29)

Internally ArrayList performs:

```text
Linear Search
```

Starting from:

```text
Index 0
```

and moving forward.

Comparison uses:

```java
equals()
```

Complexity:

```text
O(n)
```

---

# Fail-Fast Iterators

ArrayList inherits:

```java
modCount
```

from AbstractList.

When iterator created:

```java
Iterator<String> itr =
        list.iterator();
```

iterator stores:

```java
expectedModCount
```

During iteration:

```java
if(modCount != expectedModCount)
```

throw:

```java
ConcurrentModificationException
```

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ConcurrentModificationException.html](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ConcurrentModificationException.html)

This is called:

```text
Fail-Fast Behavior
```

---

# Memory Layout of ArrayList

Conceptually:

```text
ArrayList Object
        |
        V
elementData[]
        |
------------------------------------------------
| A | B | C | null | null | null | null | ...
------------------------------------------------
```

Because elements are stored contiguously:

```text
Better Cache Performance
Faster Reads
Lower Memory Overhead
```

compared to linked structures.

---

# Complete Mental Model

```text
ArrayList
   |
Object[]
   |
Dynamic Resizing
   |
Capacity Growth
   |
1.5x Expansion
   |
Fast Random Access
   |
Shifting on Insert/Delete
   |
Fail-Fast Iterators
```

### Complexity Summary

```text
get()           O(1)
set()           O(1)
add(end)        O(1) amortized
add(index)      O(n)
remove()        O(n)
contains()      O(n)
```

