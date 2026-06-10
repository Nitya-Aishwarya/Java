# Chapter 3: LinkedList Internal Implementation

The Java Collections Framework designers recognized that not every application needs extremely fast random access. Many applications perform frequent insertions and deletions, especially at the beginning or middle of a collection. For such scenarios, an array-based structure becomes inefficient. To solve this problem, Java introduced `LinkedList`.

According to the Oracle Java documentation:

> "Linked list implementation of the List and Deque interfaces. Implements all optional list operations, and permits all elements (including null)."

Reference:

[https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedList.html](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedList.html)

This single sentence reveals something extremely important.

Unlike ArrayList, LinkedList is not only a List implementation.

It is simultaneously:

```text
List
Queue
Deque
```

This makes LinkedList one of the most versatile collections in Java.

---

# Why LinkedList Exists

To understand why LinkedList was created, consider the following ArrayList operation.

Suppose:

```text
A B C D E
```

stored inside an ArrayList.

Now we want:

```java
list.add(1, "X");
```

Result should become:

```text
A X B C D E
```

To achieve this, ArrayList must shift:

```text
B
C
D
E
```

one position to the right.

For a small list, this is not a problem.

However, imagine:

```text
1,000,000 elements
```

Shifting becomes expensive.

The designers of Java wanted a collection where insertion and deletion could happen without moving large numbers of elements.

This led to the design of LinkedList.

---

# Position of LinkedList in Collection Hierarchy

LinkedList follows a slightly different inheritance path than ArrayList.

ArrayList:

```text
AbstractList
     |
ArrayList
```

LinkedList:

```text
AbstractCollection
      |
AbstractList
      |
AbstractSequentialList
      |
LinkedList
```

Actual declaration:

```java
public class LinkedList<E>
       extends AbstractSequentialList<E>
       implements List<E>,
                  Deque<E>,
                  Cloneable,
                  Serializable
```

Notice something important.

LinkedList extends:

```java
AbstractSequentialList
```

instead of:

```java
AbstractList
```

directly.

This is because LinkedList is designed for:

```text
Sequential Access
```

rather than:

```text
Random Access
```

This distinction is fundamental.

---

# What is Sequential Access?

ArrayList allows:

```java
list.get(1000);
```

to access index 1000 immediately.

LinkedList cannot do this.

Instead:

```java
list.get(1000);
```

requires traversing nodes one by one.

Because of this behavior:

```text
ArrayList = Random Access

LinkedList = Sequential Access
```

The Java Collections Framework separates these concepts through different abstract classes.

---

# Internal Structure of LinkedList

Unlike ArrayList, LinkedList does not use:

```java
Object[]
```

Internally, LinkedList uses:

```text
Nodes
```

Every element is stored inside an independent node object.

Oracle JDK source contains:

```java
private static class Node<E>
{
    E item;

    Node<E> next;

    Node<E> prev;
}
```

This is the heart of LinkedList.

Every node stores three things:

```text
Actual Data

Reference To Next Node

Reference To Previous Node
```

Because it stores both next and previous references, LinkedList is called a:

```text
Doubly Linked List
```

---

# Visualizing LinkedList

Suppose we store:

```java
list.add("A");
list.add("B");
list.add("C");
```

Internally:

```text
null <- A <-> B <-> C -> null
```

Let's examine this carefully.

Node A:

```text
Data = A

Previous = null

Next = B
```

Node B:

```text
Data = B

Previous = A

Next = C
```

Node C:

```text
Data = C

Previous = B

Next = null
```

Every node knows:

```text
Who is before me?

Who is after me?
```

This is the foundation of LinkedList.

---

# first and last References

LinkedList maintains two important references:

```java
Node<E> first;

Node<E> last;
```

These references point to:

```text
First Node

Last Node
```

Example:

```text
first
  |
  V

A <-> B <-> C

             ^
             |
            last
```

Because LinkedList always knows the first and last node, insertion at either end becomes very efficient.

---

# Adding Elements to LinkedList

Suppose:

```java
LinkedList<String> list =
        new LinkedList<>();
```

Initially:

```text
first = null

last = null
```

No nodes exist.

---

# First Insertion

```java
list.add("A");
```

LinkedList creates:

```java
new Node<>("A");
```

Structure:

```text
null <- A -> null
```

Now:

```text
first = A

last = A
```

Because only one node exists.

---

# Second Insertion

```java
list.add("B");
```

A new node is created.

Before:

```text
A
```

After:

```text
A <-> B
```

Internally:

```java
A.next = B;

B.prev = A;
```

Then:

```java
last = B;
```

No shifting occurs.

No resizing occurs.

Only references change.

---

# Why add() is Efficient

ArrayList insertion:

```text
May Resize

May Shift Elements
```

LinkedList insertion:

```text
Create Node

Update References
```

Therefore:

```text
addLast() = O(1)
```

---

# Internal Working of get(index)

This is where LinkedList differs dramatically from ArrayList.

Suppose:

```java
list.get(2);
```

ArrayList:

```text
Direct Array Access
```

LinkedList:

```text
Node Traversal
```

Java must move:

```text
Node 0
 ↓
Node 1
 ↓
Node 2
```

until the requested position is found.

---

# Traversal Optimization

Many developers think LinkedList always starts from the first node.

This is incorrect.

LinkedList uses an optimization.

Suppose:

```java
list.get(index);
```

Java checks:

```java
if(index < size/2)
```

start from:

```text
first
```

otherwise:

```text
last
```

Example:

List size:

```text
1000
```

Request:

```java
get(900)
```

Instead of traversing:

```text
0 → 1 → 2 → ... → 900
```

Java starts from:

```text
999 → 998 → 997 ...
```

This reduces traversal cost significantly.

---

# Complexity of get()

Even with optimization:

```text
Traversal Required
```

Therefore:

```text
get() = O(n)
```

This is LinkedList's biggest weakness.

---

# Internal Working of add(index)

Suppose:

```text
A <-> B <-> D
```

Insert:

```java
list.add(2,"C");
```

Desired:

```text
A <-> B <-> C <-> D
```

Java performs:

### Step 1

Find position.

Traverse nodes.

### Step 2

Create new node.

```java
new Node<>("C");
```

### Step 3

Update references.

Before:

```text
B.next = D

D.prev = B
```

After:

```text
B.next = C

C.prev = B

C.next = D

D.prev = C
```

No shifting.

Only references updated.

---

# Internal Working of remove()

Suppose:

```text
A <-> B <-> C
```

Remove:

```java
list.remove(1);
```

Node B removed.

Before:

```text
A.next = B

B.next = C

C.prev = B
```

After:

```text
A.next = C

C.prev = A
```

Node B becomes unreachable.

Garbage Collector removes it later.

---

# Why remove() Appears Fast

Actual unlinking:

```text
O(1)
```

However:

```text
Finding Node
```

requires traversal.

Therefore:

```text
remove(index) = O(n)
```

overall.

---

# LinkedList as Queue

Because LinkedList implements:

```java
Deque
```

it supports queue operations.

Example:

```java
Queue<String> queue =
        new LinkedList<>();
```

Insertion:

```java
queue.offer("A");
```

Internally:

```text
Add At Tail
```

Removal:

```java
queue.poll();
```

Internally:

```text
Remove From Head
```

Both:

```text
O(1)
```

---

# LinkedList as Deque

LinkedList also supports:

```java
addFirst()
addLast()

removeFirst()
removeLast()

peekFirst()
peekLast()
```

Example:

```java
list.addFirst("A");
```

New node becomes:

```text
first
```

No traversal required.

Complexity:

```text
O(1)
```

---

# Memory Overhead

ArrayList stores:

```text
Element Only
```

LinkedList stores:

```text
Element

Previous Reference

Next Reference
```

Each node therefore consumes significantly more memory.

Example:

```text
ArrayList

[A][B][C]
```

vs

```text
LinkedList

A <-> B <-> C
```

The references consume extra memory.

This is why LinkedList has higher memory overhead.

---

# Why LinkedList Does NOT Implement RandomAccess

ArrayList:

```java
implements RandomAccess
```

LinkedList:

```java
does NOT implement RandomAccess
```

because:

```text
get(index)
```

is not constant time.

Java algorithms can use this information to choose more efficient strategies.

---

# Fail-Fast Iterators

LinkedList inherits:

```java
modCount
```

from AbstractList.

Whenever structure changes:

```java
modCount++;
```

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

Exactly the same fail-fast mechanism used by ArrayList.

---

# Complete Memory Layout

```text
LinkedList Object
       |
--------------------------------
| first Reference             |
| last Reference              |
| size                        |
--------------------------------
       |
       V

null <- A <-> B <-> C -> null
```

Every node contains:

```text
Data

Previous Pointer

Next Pointer
```

---

# Complexity Summary

```text
get()             O(n)

set()             O(n)

addLast()         O(1)

addFirst()        O(1)

removeFirst()     O(1)

removeLast()      O(1)

add(index)        O(n)

remove(index)     O(n)

contains()        O(n)
```

---

# Complete Mental Model

```text
LinkedList
      |
Doubly Linked List
      |
Node
 ├── Data
 ├── Prev
 └── Next
      |
first Reference
last Reference
      |
Sequential Access
      |
Queue + Deque Support
      |
No Resizing
No Shifting
      |
Higher Memory Usage
```
