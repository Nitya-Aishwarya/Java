## Chapter 1: List Collection in Java — Interface and AbstractList

The `List` collection in Java is one of the most important parts of the Java Collections Framework because it represents the idea of a **sequence**. According to the official Java documentation, `List` is an ordered collection, also known as a sequence, and the user has precise control over where each element is inserted. This definition is very important because it tells us that a List is not just a group of objects. It is a group of objects arranged in a specific order, where each element has a position. The Java documentation also explains that the `List` interface provides indexed access methods and that lists are zero-based, just like arrays. This means the first element is at index `0`, the second element is at index `1`, and so on. ([Oracle Docs][1])

A `List` is used when the order of elements matters and when duplicate elements are allowed. For example, if we are building a playlist application, the order of songs matters because the first song should play first, the second song should play second, and so on. Also, the same song may appear more than once in the playlist. In this situation, a `Set` is not suitable because a Set removes duplicates. A `Queue` is also not the best choice because a Queue focuses on processing order rather than positional access. A `List` is the correct structure because it preserves insertion order, allows duplicates, and lets us access elements using indexes.

```java
List<String> songs = new ArrayList<>();

songs.add("Song A");
songs.add("Song B");
songs.add("Song A");

System.out.println(songs);
```

Output:

```text
[Song A, Song B, Song A]
```

In this example, `"Song A"` appears twice, and both values are stored. This is because the `List` interface does not enforce uniqueness. It treats each inserted element as a separate occurrence, even if the value is the same. This behavior is very different from `Set`, where duplicate values are rejected.

The hierarchy of `List` begins with `Iterable`, then `Collection`, and then `List`. The `Collection` interface is the root interface for many collection types in Java, and the official documentation describes it as a member of the Java Collections Framework. `List` extends `Collection`, which means every List is also a Collection, but it adds extra behavior related to ordering and index-based access. ([Oracle Docs][2])

```text
Iterable
   |
Collection
   |
List
```

At the implementation level, the important classes are `ArrayList`, `LinkedList`, `Vector`, and `Stack`.

```text
List
 |
 |--- ArrayList
 |
 |--- LinkedList
 |
 |--- Vector
        |
        |--- Stack
```

The `List` interface itself does not store data. This is a very important internal point. An interface only defines behavior. It says what operations should be available, but it does not decide how elements are stored in memory. When we write this:

```java
List<String> list = new ArrayList<>();
```

the reference type is `List`, but the actual object is `ArrayList`. Therefore, internally the data is stored using the internal mechanism of `ArrayList`, not by `List` itself. If we write this instead:

```java
List<String> list = new LinkedList<>();
```

then the same `List` methods are available, but internally the elements are stored using the internal mechanism of `LinkedList`. This is one of the main strengths of Java Collections: the interface gives us a common way to work with data, while the implementation decides the internal storage strategy.

The most important methods of the `List` interface are positional methods. These methods include `add(E e)`, `add(int index, E element)`, `get(int index)`, `set(int index, E element)`, `remove(int index)`, `indexOf(Object o)`, and `lastIndexOf(Object o)`. The Java documentation specifically mentions that the List interface provides positional access methods, and it also warns that these operations may take different amounts of time depending on the implementation. For example, `get(index)` is very fast in `ArrayList`, but it can be slower in `LinkedList` because LinkedList must traverse nodes. ([Oracle Docs][3])

The `add(E e)` method appends an element to the end of the list. From the user’s point of view, this looks simple, but internally the behavior depends on the implementation. In `ArrayList`, the element is placed in the next available position of an internal array. In `LinkedList`, a new node is created and connected at the end of the linked structure.

```java
List<String> list = new ArrayList<>();

list.add("Java");
list.add("Spring");
list.add("Hibernate");

System.out.println(list);
```

Output:

```text
[Java, Spring, Hibernate]
```

The `add(int index, E element)` method inserts an element at a specific position. This is one of the methods that makes List different from many other collection types. When we insert at a specific index, all elements after that index must move logically to make space for the new element. In an `ArrayList`, this means actual shifting inside the internal array. In a `LinkedList`, Java must first reach the required position by traversing nodes, and then it changes node references.

```java
List<String> list = new ArrayList<>();

list.add("Java");
list.add("Hibernate");

list.add(1, "Spring");

System.out.println(list);
```

Output:

```text
[Java, Spring, Hibernate]
```

The `get(int index)` method returns the element at the specified position. This method is central to List because List is index-based. In an `ArrayList`, this operation directly accesses the internal array at the given index. In a `LinkedList`, Java cannot directly jump to the index; it must move node by node until it reaches the required position.

```java
System.out.println(list.get(1));
```

Output:

```text
Spring
```

The `set(int index, E element)` method replaces the element at a given position. This does not increase or decrease the size of the list. It simply changes the value stored at an existing index.

```java
list.set(1, "Spring Boot");

System.out.println(list);
```

Output:

```text
[Java, Spring Boot, Hibernate]
```

The `remove(int index)` method removes the element at a specific position. In `ArrayList`, after removing an element, all elements to the right must shift one position to the left. In `LinkedList`, Java changes references between nodes after finding the node to remove.

```java
list.remove(1);

System.out.println(list);
```

Output:

```text
[Java, Hibernate]
```

The `indexOf(Object o)` method searches from the beginning and returns the index of the first occurrence. The `lastIndexOf(Object o)` method searches from the end and returns the index of the last occurrence. These methods matter because Lists allow duplicates, so the same value can appear in multiple positions.

```java
List<String> names = new ArrayList<>();

names.add("John");
names.add("David");
names.add("John");

System.out.println(names.indexOf("John"));
System.out.println(names.lastIndexOf("John"));
```

Output:

```text
0
2
```

The Java documentation also mentions that `List` provides a special iterator called `ListIterator`. A normal `Iterator` moves forward and allows removal, but `ListIterator` is more powerful because it supports bidirectional traversal, insertion, and replacement while iterating. This is possible because List has a clear sequence and index positions. ([Oracle Docs][1])

Now we need to understand `AbstractList`, because it is the base class that provides common List behavior for many implementations. The official Java documentation describes `AbstractList` as a skeletal implementation of the `List` interface. It says that this class minimizes the effort required to implement a List backed by a random-access data store, such as an array. It also says that for sequential-access data, such as a linked list, `AbstractSequentialList` should usually be preferred. This is a very important design point because Java separates random-access list behavior from sequential-access list behavior. ([Oracle Docs][4])

The hierarchy looks like this:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
ArrayList
```

`AbstractList` exists because many List implementations need the same common behavior. For example, every List must have a meaningful `equals()` method, every List must have a `hashCode()` method, and every List must support iteration. If every implementation had to write this logic separately, the same code would be repeated in `ArrayList`, `Vector`, and other List classes. To avoid this duplication, Java provides `AbstractList`.

The key idea is that `AbstractList` does not decide the exact storage structure. It does not say whether data should be stored in an array, a linked list, or another structure. Instead, it provides common logic that can work on top of basic methods such as `get(int index)`, `set(int index, E element)`, `add(int index, E element)`, and `remove(int index)`. The Java documentation explicitly notes that the iterator and list iterator in `AbstractList` are implemented on top of these random-access methods. ([Oracle Docs][4])

One of the most important methods provided by `AbstractList` is `equals()`. For a List, equality is based on both the elements and their order. This is different from Set. In a Set, order usually does not matter. In a List, order matters completely.

```java
List<Integer> list1 = Arrays.asList(10, 20);
List<Integer> list2 = Arrays.asList(10, 20);
List<Integer> list3 = Arrays.asList(20, 10);

System.out.println(list1.equals(list2));
System.out.println(list1.equals(list3));
```

Output:

```text
true
false
```

The first comparison returns `true` because both lists contain the same elements in the same order. The second comparison returns `false` because the order is different. Even though `list1` and `list3` contain the same values, they are not equal as Lists because their sequence is different.

`AbstractList` also provides a List-style `hashCode()` implementation. This hash code depends on the elements and their order. This is necessary because Java requires that if two objects are equal according to `equals()`, they must produce the same hash code. Since List equality depends on order, List hash code must also depend on order.

Another extremely important internal field in `AbstractList` is `modCount`.

```java
protected transient int modCount;
```

This field counts structural modifications. A structural modification means a change that affects the size of the list or otherwise changes its internal structure in a way that affects iteration. Examples include `add()`, `remove()`, and `clear()`. When such an operation happens, `modCount` is increased.

```text
add element     -> modCount++
remove element  -> modCount++
clear list      -> modCount++
```

This field is used by fail-fast iterators. When an iterator is created, it stores the current value of `modCount` in another variable usually called `expectedModCount`. During iteration, the iterator compares the current `modCount` with its stored `expectedModCount`. If they are different, it means the list was structurally modified outside the iterator, so Java throws `ConcurrentModificationException`.

```java
List<String> list = new ArrayList<>();

list.add("A");
list.add("B");

for (String value : list) {
    list.add("C");
}
```

This code throws:

```text
ConcurrentModificationException
```

This happens because the enhanced for-loop internally uses an iterator. While the iterator is moving through the list, the list is modified directly using `list.add("C")`. That changes `modCount`, but the iterator’s `expectedModCount` does not match it anymore. Java detects this mismatch and fails fast.

The correct way to remove elements during iteration is to use the iterator’s own `remove()` method.

```java
Iterator<String> iterator = list.iterator();

while (iterator.hasNext()) {
    String value = iterator.next();

    if (value.equals("A")) {
        iterator.remove();
    }
}
```

This works because the iterator updates its internal expected modification count when its own `remove()` method is used.

So, at the end of this first chapter, the internal picture is this: `List` is the interface that defines ordered, index-based collection behavior. It does not store data. `AbstractList` is a skeletal implementation that provides common behavior such as equality, hash code generation, iterators, list iterators, sub-list behavior, and fail-fast support through `modCount`. Concrete classes such as `ArrayList` and `Vector` build on this base and provide the actual storage mechanism. `LinkedList` follows a slightly different path through `AbstractSequentialList`, because its internal storage is sequential rather than random-access based.

The complete mental model for this part is:

```text
List
 |
 |  Defines ordered, index-based behavior
 |
AbstractList
 |
 |  Provides common List logic
 |  equals()
 |  hashCode()
 |  iterator()
 |  listIterator()
 |  subList()
 |  modCount
 |
ArrayList / Vector
 |
Actual storage implementation
```

Next chapter should be **ArrayList internal implementation**, where we will go deeply into `Object[] elementData`, default capacity, lazy initialization, size vs capacity, resizing, `add()`, `get()`, `set()`, `remove()`, shifting, `System.arraycopy()`, `RandomAccess`, fail-fast iterators, and JavaDoc references.

[1]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html?utm_source=chatgpt.com "List (Java SE 21 & JDK 21) - Oracle"
[2]: https://docs.oracle.com/en/java/javase/21/docs/api//java.base/java/util/Collection.html?utm_source=chatgpt.com "Collection (Java SE 21 & JDK 21) - Oracle"
[3]: https://docs.oracle.com/javase/8/docs/api/java/util/List.html?utm_source=chatgpt.com "List (Java Platform SE 8 ) - Oracle Help Center"
[4]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/AbstractList.html?utm_source=chatgpt.com "AbstractList (Java SE 21 & JDK 21) - Oracle"
