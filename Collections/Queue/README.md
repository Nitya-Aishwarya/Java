# Queue in Java — Internal Implementation

A **Queue** in Java is a collection designed for holding elements before processing them. Unlike `List`, which focuses on index-based access, and unlike `Set`, which focuses on uniqueness, a `Queue` focuses on **processing order**. The official Java docs describe `Queue` as a collection used to hold elements before processing, and they also mention that queue ordering depends on the implementation. In normal queues, this is usually FIFO, but in priority queues, the head is chosen by priority instead of insertion order. ([Oracle Docs][1])

The hierarchy is:

```text
Iterable
   |
Collection
   |
Queue
   |
   |--- Deque
   |     |--- ArrayDeque
   |     |--- LinkedList
   |
   |--- PriorityQueue
```

## Queue Interface

`Queue` is an interface, so it does not store elements by itself. It only defines behavior. The actual internal implementation depends on whether you use `LinkedList`, `ArrayDeque`, or `PriorityQueue`.

```java
Queue<String> queue = new LinkedList<>();
```

Here, the reference type is `Queue`, but the actual object is `LinkedList`. So internally, the storage is done by `LinkedList`, not by `Queue`.

A normal queue usually follows **FIFO**, which means **First In, First Out**. The element inserted first is removed first.

```java
Queue<String> queue = new LinkedList<>();

queue.offer("A");
queue.offer("B");
queue.offer("C");

System.out.println(queue.poll());
System.out.println(queue.poll());
```

Output:

```text
A
B
```

Here, `"A"` was inserted first, so it is removed first.

## Queue Methods

Queue provides two styles of methods. One style throws an exception when the operation fails, and the other style returns a special value such as `false` or `null`. The Java docs specifically mention this difference: `remove()` throws an exception if the queue is empty, while `poll()` returns `null`; similarly, `element()` throws an exception if empty, while `peek()` returns `null`. ([Oracle Docs][1])

```text
Operation    Exception Method    Special-Value Method
-----------------------------------------------------
Insert       add(e)              offer(e)
Remove       remove()            poll()
Examine      element()           peek()
```

In normal programming, `offer()`, `poll()`, and `peek()` are usually preferred because they handle failure more safely.

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);

System.out.println(queue.peek()); // 10
System.out.println(queue.poll()); // 10
System.out.println(queue.poll()); // 20
System.out.println(queue.poll()); // null
```

## LinkedList as Queue

When you write:

```java
Queue<String> queue = new LinkedList<>();
```

the queue is internally implemented using a **doubly linked list**. The official Java docs describe `LinkedList` as a doubly-linked list implementation of both `List` and `Deque`. They also mention that it permits all elements, including `null`. ([Oracle Docs][2])

Internally, `LinkedList` stores each element inside a node.

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}
```

Each node contains the actual value, a reference to the next node, and a reference to the previous node.

If we insert:

```java
queue.offer("A");
queue.offer("B");
queue.offer("C");
```

internally it looks like this:

```text
first                         last
  |                            |
  v                            v
null <- A <-> B <-> C -> null
```

When `offer("D")` is called, `LinkedList` creates a new node and attaches it at the end.

```text
null <- A <-> B <-> C <-> D -> null
```

This insertion at the tail is efficient because `LinkedList` maintains a reference to the last node.

When `poll()` is called, the first node is removed.

Before:

```text
A <-> B <-> C <-> D
```

After `poll()`:

```text
B <-> C <-> D
```

The `first` reference moves from `A` to `B`, and the old node becomes eligible for garbage collection.

For queue operations, `LinkedList` gives efficient insertion at the end and removal from the front.

```text
offer()  -> O(1)
poll()   -> O(1)
peek()   -> O(1)
```

However, `LinkedList` has extra memory overhead because every element needs a separate node object with `prev` and `next` references.

## Deque

`Deque` means **Double Ended Queue**. It extends `Queue` and allows insertion and removal from both ends.

```java
Deque<String> deque = new ArrayDeque<>();

deque.offerFirst("A");
deque.offerLast("B");
deque.offerFirst("C");

System.out.println(deque);
```

Output:

```text
[C, A, B]
```

A normal queue removes from the front and inserts at the rear, but a deque can work from both front and rear.

Important methods are:

```java
offerFirst(e)
offerLast(e)
pollFirst()
pollLast()
peekFirst()
peekLast()
```

## ArrayDeque Internal Implementation

`ArrayDeque` is usually the best general-purpose queue implementation in modern Java. The official Java docs describe it as a **resizable-array implementation of the Deque interface**, with no fixed capacity restrictions because it grows as needed. The docs also mention that it is not thread-safe, prohibits `null`, and is usually faster than `LinkedList` when used as a queue. ([Oracle Docs][3])

Internally, `ArrayDeque` uses an array, but not like a normal `ArrayList`. It uses a **circular array**.

Conceptually:

```java
Object[] elements;
int head;
int tail;
```

The `head` points to the front of the deque, and the `tail` points to the position where the next element will be inserted at the rear.

Imagine the internal array has capacity 8:

```text
Index:   0   1   2   3   4   5   6   7
Value:   _   _   A   B   C   _   _   _
             head        tail
```

If we remove from the front using `pollFirst()`, the `head` moves forward. No shifting happens.

Before:

```text
Index:   0   1   2   3   4   5   6   7
Value:   _   _   A   B   C   _   _   _
                 H           T
```

After removing `A`:

```text
Index:   0   1   2   3   4   5   6   7
Value:   _   _   _   B   C   _   _   _
                     H       T
```

If we add more elements and the tail reaches the end of the array, it wraps around to the beginning.

```text
Index:   0   1   2   3   4   5   6   7
Value:   F   G   _   B   C   D   E   _
         T           H
```

This wrap-around behavior is why it is called a circular array. It avoids shifting elements and makes both front and rear operations very efficient.

Most important operations are amortized constant time:

```text
offerFirst()  -> O(1)
offerLast()   -> O(1)
pollFirst()   -> O(1)
pollLast()    -> O(1)
peekFirst()   -> O(1)
peekLast()    -> O(1)
```

`ArrayDeque` does not allow `null`, because methods like `poll()` return `null` to indicate that the deque is empty. If null values were allowed, Java could not clearly distinguish between “empty queue” and “actual null element.”

## PriorityQueue Internal Implementation

`PriorityQueue` is very different from `LinkedList` and `ArrayDeque`. A normal queue usually follows FIFO, but `PriorityQueue` removes elements according to priority. The official Java docs describe `PriorityQueue` as an **unbounded priority queue based on a priority heap**, ordered either by natural ordering or by a `Comparator`. The docs also state that it does not permit `null`, and its head is the least element according to the ordering. ([Oracle Docs][4])

Example:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(50);
pq.offer(10);
pq.offer(30);
pq.offer(20);

while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

Output:

```text
10
20
30
50
```

Even though `50` was inserted first, `10` comes out first because `PriorityQueue` is ordered by priority, not insertion order.

Internally, `PriorityQueue` uses an array to represent a **binary heap**, specifically a min-heap by default.

A min-heap follows this rule:

```text
Parent <= Children
```

Example heap:

```text
        10
       /  \
     20    30
    /
   50
```

The smallest element is always at the root. That root is the head of the priority queue.

Although this is logically a tree, it is stored inside an array.

```text
Index:  0   1   2   3
Value: 10  20  30  50
```

For an element at index `i`:

```text
left child  = 2 * i + 1
right child = 2 * i + 2
parent      = (i - 1) / 2
```

When a new element is inserted, it is first placed at the end of the heap array. Then Java compares it with its parent. If the new element has higher priority, it moves upward. This is called **heapify up** or **sift up**.

Example:

```java
pq.offer(5);
```

The value `5` is inserted at the end, then compared with its parent. Since `5` is smaller, it moves upward until the heap property is restored.

When `poll()` is called, Java removes the root element because the root has the highest priority. Then the last element is moved to the root position. After that, Java compares it with its children and moves it downward until the heap property is restored. This is called **heapify down** or **sift down**.

Because heap height is logarithmic:

```text
offer() -> O(log n)
poll()  -> O(log n)
peek()  -> O(1)
```

`peek()` is `O(1)` because the highest-priority element is always at index 0.

## PriorityQueue with Comparator

By default, `PriorityQueue<Integer>` behaves like a min-heap, so the smallest number comes first.

If you want the largest number first, you provide a comparator.

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.reverseOrder());

pq.offer(50);
pq.offer(10);
pq.offer(30);

System.out.println(pq.poll());
```

Output:

```text
50
```

Now the queue behaves like a max-priority queue.

For custom objects:

```java
class Patient {
    String name;
    int severity;

    Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    public String toString() {
        return name + " " + severity;
    }
}
```

```java
PriorityQueue<Patient> emergencyQueue =
        new PriorityQueue<>(
            (p1, p2) -> Integer.compare(p2.severity, p1.severity)
        );

emergencyQueue.offer(new Patient("John", 3));
emergencyQueue.offer(new Patient("Alice", 8));
emergencyQueue.offer(new Patient("David", 5));

System.out.println(emergencyQueue.poll());
```

Output:

```text
Alice 8
```

Here, the patient with the highest severity is processed first.

## Internal Comparison of Queue Implementations

```text
Implementation   Internal Structure        Ordering
------------------------------------------------------------
LinkedList       Doubly linked list        FIFO / Deque order
ArrayDeque       Circular resizable array  FIFO / Deque order
PriorityQueue    Binary heap array         Priority order
```

`LinkedList` is flexible because it implements both `List` and `Deque`, but it uses extra memory for node links. `ArrayDeque` is usually faster for normal queue and stack operations because it uses a compact circular array. `PriorityQueue` should be used only when elements must be processed by priority rather than arrival order.

## Final Summary

A `Queue` is an interface that defines processing-oriented collection behavior. It does not store data by itself. Its implementation decides the internal structure.

`LinkedList` implements queue behavior using a doubly linked list, where insertion at the tail and removal from the head are efficient. `ArrayDeque` implements queue and deque behavior using a circular resizable array, making it usually faster and more memory-efficient than `LinkedList` for queue operations. `PriorityQueue` implements priority-based processing using a binary heap stored inside an array, where the smallest element by natural ordering, or the highest-priority element by comparator, becomes the head of the queue.

The memory trick is:

```text
Queue         -> Processing order
LinkedList    -> Doubly linked nodes
ArrayDeque    -> Circular array
PriorityQueue -> Binary heap
```

[1]: https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html?utm_source=chatgpt.com "Queue (Java Platform SE 8 ) - Oracle Help Center"
[2]: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html?utm_source=chatgpt.com "LinkedList (Java Platform SE 8 ) - Oracle"
[3]: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html?utm_source=chatgpt.com "ArrayDeque (Java Platform SE 8 ) - Oracle"
[4]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/PriorityQueue.html?utm_source=chatgpt.com "PriorityQueue (Java SE 21 & JDK 21) - Oracle"
