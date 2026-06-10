# Chapter 4: Vector and Stack Internal Implementation — Complete Textbook-Level Explanation

In the previous chapters, we studied ArrayList and LinkedList and learned how modern List implementations work internally. However, before the Java Collections Framework was introduced in Java 1.2, Java already had a dynamic array implementation called **Vector**. Understanding Vector is important because many modern collection classes evolved from concepts first introduced in Vector. It is also important because Stack, another legacy collection class, is built directly on top of Vector.

According to the Oracle Java documentation:

> "The Vector class implements a growable array of objects."

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Vector.html](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Vector.html)

At first glance, this sounds almost identical to ArrayList. Both are dynamic arrays. Both allow indexed access. Both maintain insertion order. Both allow duplicates. Both internally use arrays.

However, there is one major difference that completely changes their behavior:

```text
Vector is synchronized.
ArrayList is not synchronized.
```

This single difference explains most of the design decisions behind Vector.

---

# Why Vector Was Introduced

When Java was first created, multithreaded programming was one of its major selling points.

Java designers wanted collection classes that could safely operate in environments where multiple threads accessed the same data structure simultaneously.

Before Collections Framework existed:

```text
Vector
Hashtable
```

were the primary collection classes.

The goal was:

```text
Dynamic Growth
+
Thread Safety
```

At that time, developers frequently shared collections between threads.

Therefore Java designers built synchronization directly into Vector.

---

# Position of Vector in Hierarchy

Hierarchy:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
Vector
   |
Stack
```

Declaration:

```java
public class Vector<E>
       extends AbstractList<E>
       implements List<E>,
                  RandomAccess,
                  Cloneable,
                  Serializable
```

Notice:

```text
Vector extends AbstractList
```

just like ArrayList.

Therefore Vector inherits:

```java
equals()
hashCode()
iterator()
listIterator()
subList()
modCount
```

from AbstractList.

---

# Internal Storage of Vector

Internally Vector uses:

```java
protected Object[] elementData;
```

This should look familiar.

ArrayList also uses:

```java
Object[] elementData;
```

Therefore:

```text
Vector
      ↓
Object[]
```

Internally Vector stores data inside a dynamic array.

Example:

```java
Vector<String> vector =
        new Vector<>();

vector.add("Java");
vector.add("Spring");
vector.add("Hibernate");
```

Internally:

```text
Index     Value
----------------
0         Java
1         Spring
2         Hibernate
```

Exactly like ArrayList.

---

# Default Capacity of Vector

Unlike modern ArrayList, Vector immediately creates storage.

Default capacity:

```text
10
```

Example:

```java
Vector<Integer> vector =
        new Vector<>();
```

Internally:

```text
Capacity = 10
Size = 0
```

The internal array already exists.

This differs from ArrayList, which initially uses an empty array and allocates storage only when the first element is inserted.

---

# Capacity Increment

One unique feature of Vector is:

```java
capacityIncrement
```

Field:

```java
protected int capacityIncrement;
```

This determines how Vector grows.

Suppose:

```java
Vector<Integer> vector =
        new Vector<>(10,5);
```

Meaning:

```text
Initial Capacity = 10
Growth Increment = 5
```

When full:

```text
10 → 15
15 → 20
20 → 25
```

Capacity grows by exactly:

```text
5
```

each time.

---

# Default Growth Behavior

If:

```java
new Vector<>();
```

is used without capacityIncrement,

Vector doubles capacity.

Example:

```text
10 → 20
20 → 40
40 → 80
```

This differs from ArrayList.

ArrayList:

```text
1.5x Growth
```

Vector:

```text
2x Growth
```

by default.

---

# Internal Working of add()

JavaDoc:

> Appends the specified element to the end of this Vector.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Vector.html#add(E)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Vector.html#add%28E%29)

Example:

```java
vector.add("Java");
```

Internally:

### Step 1

Check capacity.

### Step 2

Grow if necessary.

### Step 3

Store element.

```java
elementData[elementCount] =
        element;
```

### Step 4

Increment count.

```java
elementCount++;
```

### Step 5

Increment:

```java
modCount++;
```

---

# Synchronization in Vector

The biggest difference from ArrayList.

Look at actual Vector methods.

Example:

```java
public synchronized E get(int index)
```

Notice:

```java
synchronized
```

Another:

```java
public synchronized boolean add(E e)
```

Again:

```java
synchronized
```

Every critical method is synchronized.

This means:

```text
Only one thread can execute
Vector methods at a time.
```

---

# Why Synchronization Matters

Suppose two threads execute:

```java
vector.add("A");
```

simultaneously.

Without synchronization:

```text
Race Condition
```

may occur.

Both threads may attempt to modify:

```java
elementCount
```

at the same time.

This can corrupt internal state.

Synchronization prevents this.

Only one thread enters the method at a time.

---

# Why Vector Became Less Popular

Synchronization provides safety.

However:

```text
Synchronization = Overhead
```

Every method call must:

```text
Acquire Lock
Execute
Release Lock
```

Even when only one thread exists.

Therefore:

```text
ArrayList
```

became faster.

Modern Java usually prefers:

```java
ArrayList
```

for single-threaded use.

Or:

```java
Collections.synchronizedList()
```

or concurrent collections for multithreaded use.

---

# Random Access in Vector

Like ArrayList:

```java
implements RandomAccess
```

Therefore:

```java
vector.get(1000);
```

directly accesses:

```java
elementData[1000];
```

Complexity:

```text
O(1)
```

---

# Internal Working of remove()

Suppose:

```text
A B C D
```

Remove:

```java
vector.remove(1);
```

Removing:

```text
B
```

Vector shifts:

```text
C ← left
D ← left
```

Just like ArrayList.

Internally:

```java
System.arraycopy()
```

is used.

Complexity:

```text
O(n)
```

---

# Fail-Fast Iterators

Vector inherits:

```java
modCount
```

from AbstractList.

Iterator stores:

```java
expectedModCount
```

During iteration:

```java
if(modCount != expectedModCount)
```

Throw:

```java
ConcurrentModificationException
```

Even though Vector is synchronized, its iterators are still fail-fast.

---

# Introduction to Stack

Before Java Collections Framework introduced Deque, Java provided:

```java
Stack
```

Stack represents:

```text
LIFO
Last In First Out
```

The most recently added element is removed first.

Oracle documentation:

> The Stack class represents a last-in-first-out stack of objects.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Stack.html](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Stack.html)

---

# Hierarchy of Stack

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
Vector
   |
Stack
```

Declaration:

```java
public class Stack<E>
       extends Vector<E>
```

This means:

```text
Stack IS-A Vector
```

All Vector behavior automatically exists inside Stack.

---

# Internal Structure of Stack

Because Stack extends Vector:

```text
Stack
      ↓
Vector
      ↓
Object[]
```

Stack internally uses:

```java
Object[]
```

for storage.

Example:

```java
Stack<Integer> stack =
        new Stack<>();
```

Internally:

```text
Index     Value
----------------
0         10
1         20
2         30
```

Exactly like Vector.

---

# push()

JavaDoc:

> Pushes an item onto the top of this stack.

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Stack.html#push(E)](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Stack.html#push%28E%29)

Example:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

Logical structure:

```text
TOP
 |
30
20
10
```

Internally:

```java
addElement(item);
```

from Vector.

---

# pop()

JavaDoc:

> Removes the object at the top of this stack.

Example:

```java
stack.pop();
```

Removes:

```text
30
```

Result:

```text
TOP
 |
20
10
```

Internally:

### Step 1

Retrieve last element.

### Step 2

Remove last element.

### Step 3

Return value.

Complexity:

```text
O(1)
```

---

# peek()

JavaDoc:

> Looks at the object at the top of this stack without removing it.

Example:

```java
stack.peek();
```

Output:

```text
20
```

Stack unchanged.

---

# search()

Unique Stack method.

Example:

```java
stack.search(10);
```

Returns:

```text
Position from top
```

Not index.

Example:

```text
TOP
20
10
```

Output:

```text
2
```

because 10 is two positions from the top.

---

# Why Stack Is Considered Legacy

Modern Java documentation recommends:

```java
Deque
```

instead of Stack.

Reason:

```text
ArrayDeque
```

provides:

```text
Better Performance
No Legacy Design
More Flexible API
```

Example:

```java
Deque<Integer> stack =
        new ArrayDeque<>();

stack.push(10);
stack.push(20);

stack.pop();
```

Same behavior.

Better implementation.

---

# Memory Layout of Vector and Stack

Vector:

```text
Vector Object
       |
elementData[]
       |
--------------------------------
| A | B | C | D | null | ...
--------------------------------
```

Stack:

```text
Stack
   |
Vector
   |
elementData[]
```

Same storage.

Different semantics.

---

# Complexity Summary

### Vector

```text
get()         O(1)

set()         O(1)

add(end)      O(1) amortized

add(index)    O(n)

remove()      O(n)

contains()    O(n)
```

### Stack

```text
push()        O(1)

pop()         O(1)

peek()        O(1)

search()      O(n)
```

---

# Complete Mental Model

```text
Vector
   |
Object[]
   |
Synchronized
   |
2x Growth
   |
RandomAccess

Stack
   |
Vector
   |
Object[]
   |
LIFO
   |
push()
pop()
peek()
```

