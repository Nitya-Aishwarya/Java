# Control Statements — Complete Theory

## Introduction to Control Statements

In programming, a program normally executes statements one after another in a sequential manner. However, real-world problems require programs to make decisions, repeat operations, and sometimes skip or transfer execution from one part of the program to another. To achieve this behavior, programming languages provide control statements.

Control statements are special statements that control the flow of execution of a program. They determine which statements should execute, how many times they should execute, and under what conditions execution should stop or continue.

Control statements improve the flexibility, intelligence, and efficiency of programs. Without control statements, programs would only execute line by line and would not be able to perform logical operations such as checking conditions, repeating tasks, or making decisions.

Control statements are mainly classified into three categories:

1. Decision-Making Statements
2. Looping or Iterative Statements
3. Jump Statements

---

# 1. Decision-Making Statements

Decision-making statements are used when a program needs to choose between different alternatives based on a condition. These statements evaluate conditions and execute specific blocks of code depending on whether the condition is true or false.

The decision-making statements in C are:

* if statement
* if-else statement
* nested if statement
* else-if ladder
* switch statement

---

# A. if Statement

The `if` statement is the simplest decision-making statement. It is used to execute a block of code only when a specified condition is true.

When the condition evaluates to true, the statements inside the `if` block are executed. If the condition evaluates to false, the statements are skipped and control moves to the next statement after the `if` block.

The `if` statement is generally used when there is only one condition to check.

## Syntax

```c
if(condition)
{
    statements;
}
```

## Example

```c
#include <stdio.h>

int main()
{
    int age = 20;

    if(age >= 18)
    {
        printf("Eligible to vote");
    }

    return 0;
}
```

## Explanation

In this program, the condition `age >= 18` is checked. Since the value of `age` is 20, the condition becomes true. Therefore, the statement inside the `if` block is executed and the message is displayed.

---

# B. if-else Statement

The `if-else` statement is used when there are two possible alternatives. One block of code executes if the condition is true, and another block executes if the condition is false.

This statement is commonly used in situations where the program must choose between two options.

## Syntax

```c
if(condition)
{
    statements1;
}
else
{
    statements2;
}
```

## Example

```c
#include <stdio.h>

int main()
{
    int number = 7;

    if(number % 2 == 0)
    {
        printf("Even Number");
    }
    else
    {
        printf("Odd Number");
    }

    return 0;
}
```

## Explanation

The program checks whether the number is divisible by 2. If the remainder is zero, the number is even. Otherwise, the number is odd. Since 7 is not divisible by 2, the `else` block executes.

---

# C. Nested if Statement

A nested `if` statement means placing one `if` statement inside another `if` statement. It is used when multiple conditions must be checked in a hierarchical manner.

The inner `if` statement executes only when the outer `if` condition becomes true.

## Example

```c
#include <stdio.h>

int main()
{
    int age = 25;
    int citizen = 1;

    if(age >= 18)
    {
        if(citizen == 1)
        {
            printf("Eligible to vote");
        }
    }

    return 0;
}
```

## Explanation

The program first checks whether the person is 18 years or older. If this condition is true, then the second condition checks whether the person is a citizen. Only when both conditions are true does the program print the message.

---

# D. else-if Ladder

The `else-if` ladder is used when multiple conditions need to be tested one after another. The conditions are checked from top to bottom. As soon as one condition becomes true, its corresponding block executes and the remaining conditions are skipped.

If none of the conditions are true, the `else` block executes.

## Syntax

```c
if(condition1)
{
}
else if(condition2)
{
}
else if(condition3)
{
}
else
{
}
```

## Example

```c
#include <stdio.h>

int main()
{
    int marks = 82;

    if(marks >= 90)
    {
        printf("Grade A");
    }
    else if(marks >= 75)
    {
        printf("Grade B");
    }
    else if(marks >= 50)
    {
        printf("Grade C");
    }
    else
    {
        printf("Fail");
    }

    return 0;
}
```

## Explanation

The program checks the marks against different ranges. Since the marks are 82, the condition `marks >= 75` becomes true and the corresponding block executes.

---

# E. switch Statement

The `switch` statement is a multi-way decision-making statement used when a variable or expression has multiple fixed possible values.

Instead of writing many `if-else` statements, a `switch` statement provides a cleaner and more readable structure.

The `switch` statement compares the value of an expression with different case values. When a matching case is found, the corresponding statements execute.

## Syntax

```c
switch(expression)
{
    case value1:
        statements;
        break;

    case value2:
        statements;
        break;

    default:
        statements;
}
```

## Example

```c
#include <stdio.h>

int main()
{
    int day = 3;

    switch(day)
    {
        case 1:
            printf("Monday");
            break;

        case 2:
            printf("Tuesday");
            break;

        case 3:
            printf("Wednesday");
            break;

        default:
            printf("Invalid Day");
    }

    return 0;
}
```

## Explanation

The value of `day` is 3. Therefore, `case 3` matches and the statement `Wednesday` is displayed.

The `break` statement terminates the switch block and prevents execution of the remaining cases.

The `default` case executes when no case matches.

---

# Difference Between if-else and switch

| if-else                         | switch                                        |
| ------------------------------- | --------------------------------------------- |
| Used for ranges and conditions  | Used for fixed values                         |
| Supports logical operators      | Does not support logical expressions directly |
| Suitable for complex conditions | Suitable for menu-driven programs             |
| Slower for many conditions      | Faster and more readable                      |

---

# 2. Looping Statements

Looping statements are used to execute a block of code repeatedly until a specified condition becomes false.

Loops reduce code repetition and make programs shorter, cleaner, and easier to maintain.

The looping statements in C are:

* while loop
* do-while loop
* for loop

---

# A. while Loop

The `while` loop is an entry-controlled loop. The condition is checked before executing the loop body.

If the condition is true, the loop executes. If the condition is false initially, the loop body does not execute even once.

## Syntax

```c
while(condition)
{
    statements;
}
```

## Example

```c
#include <stdio.h>

int main()
{
    int i = 1;

    while(i <= 5)
    {
        printf("%d\n", i);
        i++;
    }

    return 0;
}
```

## Explanation

The loop starts with `i = 1`. The condition `i <= 5` is checked before every iteration. The loop continues until the condition becomes false.

---

# B. do-while Loop

The `do-while` loop is an exit-controlled loop. The loop body executes first, and the condition is checked afterward.

Therefore, the `do-while` loop executes at least once even if the condition is false initially.

## Syntax

```c
do
{
    statements;
}
while(condition);
```

## Example

```c
#include <stdio.h>

int main()
{
    int i = 1;

    do
    {
        printf("%d\n", i);
        i++;
    }
    while(i <= 5);

    return 0;
}
```

## Explanation

The statements inside the loop execute first. After execution, the condition is checked. If true, the loop repeats.

---

# Difference Between while and do-while

| while                   | do-while                          |
| ----------------------- | --------------------------------- |
| Condition checked first | Condition checked after execution |
| May execute zero times  | Executes at least once            |
| Entry-controlled loop   | Exit-controlled loop              |

---

# C. for Loop

The `for` loop is used when the number of iterations is known in advance.

It combines initialization, condition checking, and increment/decrement in a single statement.

## Syntax

```c
for(initialization; condition; increment/decrement)
{
    statements;
}
```

## Example

```c
#include <stdio.h>

int main()
{
    int i;

    for(i = 1; i <= 5; i++)
    {
        printf("%d\n", i);
    }

    return 0;
}
```

## Explanation

The loop initializes `i` to 1. The condition `i <= 5` is checked before every iteration. After each iteration, `i` is incremented.

---

# Infinite Loop

An infinite loop is a loop whose condition never becomes false. Such loops continue forever unless interrupted externally.

## Example

```c
while(1)
{
    printf("Hello");
}
```

Infinite loops are used in operating systems, servers, and embedded systems where continuous execution is required.

---

# Nested Loops

A nested loop is a loop inside another loop. Nested loops are commonly used for patterns, matrices, and multidimensional arrays.

## Example

```c
#include <stdio.h>

int main()
{
    int i, j;

    for(i = 1; i <= 3; i++)
    {
        for(j = 1; j <= 5; j++)
        {
            printf("*");
        }

        printf("\n");
    }

    return 0;
}
```

## Explanation

The outer loop controls rows, and the inner loop controls columns. Therefore, stars are printed in a rectangular pattern.

---

# 3. Jump Statements

Jump statements transfer control from one part of a program to another.

The jump statements in C are:

* break
* continue
* goto
* return

---

# A. break Statement

The `break` statement immediately terminates the nearest loop or switch statement.

Control moves to the statement following the loop or switch.

## Example

```c
#include <stdio.h>

int main()
{
    int i;

    for(i = 1; i <= 10; i++)
    {
        if(i == 5)
        {
            break;
        }

        printf("%d\n", i);
    }

    return 0;
}
```

## Explanation

When the value of `i` becomes 5, the `break` statement terminates the loop immediately.

---

# B. continue Statement

The `continue` statement skips the current iteration of a loop and transfers control to the next iteration.

## Example

```c
#include <stdio.h>

int main()
{
    int i;

    for(i = 1; i <= 5; i++)
    {
        if(i == 3)
        {
            continue;
        }

        printf("%d\n", i);
    }

    return 0;
}
```

## Explanation

When `i` becomes 3, the `continue` statement skips the remaining statements of that iteration.

---

# C. goto Statement

The `goto` statement transfers program control to a labeled statement.

Although it provides direct jumping, excessive use of `goto` makes programs difficult to understand and maintain.

## Syntax

```c
goto label;

label:
statements;
```

## Example

```c
#include <stdio.h>

int main()
{
    int i = 1;

start:

    printf("%d\n", i);
    i++;

    if(i <= 5)
    {
        goto start;
    }

    return 0;
}
```

## Explanation

The program repeatedly jumps to the label `start` until the condition becomes false.

---

# D. return Statement

The `return` statement terminates a function and optionally sends a value back to the calling function.

## Example

```c
#include <stdio.h>

int add(int a, int b)
{
    return a + b;
}

int main()
{
    int result = add(5, 3);

    printf("%d", result);

    return 0;
}
```

## Explanation

The function `add()` returns the sum of two numbers to the `main()` function.

---

# Advantages of Control Statements

1. Improve program efficiency
2. Reduce code repetition
3. Help implement logical decisions
4. Make programs flexible and dynamic
5. Simplify complex problem-solving

---

# Disadvantages of Improper Use

1. Excessive nesting reduces readability
2. Improper loops may create infinite loops
3. Excessive use of `goto` leads to confusing programs

---

# Conclusion

Control statements are fundamental building blocks of programming. They allow programs to make decisions, repeat tasks, and control execution flow effectively. Decision-making statements help programs choose actions based on conditions, looping statements help perform repetitive tasks efficiently, and jump statements alter the normal flow of execution when required.

A strong understanding of control statements is essential for writing logical, efficient, and structured programs in C.
# Interview-Level Questions on Control Statements

---

# Basic Interview Questions

## 1. What are control statements in C?

Control statements are statements that control the flow of execution of a program. They determine the order in which instructions are executed and help the program make decisions, repeat tasks, or jump from one part of the program to another.

---

## 2. What are the types of control statements?

Control statements are classified into three categories:

1. Decision-making statements
2. Looping statements
3. Jump statements

---

## 3. What are decision-making statements?

Decision-making statements allow the program to choose different paths of execution based on conditions.

Examples:

* if
* if-else
* nested if
* else-if ladder
* switch

---

## 4. What are looping statements?

Looping statements repeatedly execute a block of code until a condition becomes false.

Examples:

* while
* do-while
* for

---

## 5. What are jump statements?

Jump statements transfer control from one part of a program to another.

Examples:

* break
* continue
* goto
* return

---

# Questions on if Statement

## 6. What is an if statement?

The `if` statement is a conditional statement used to execute a block of code only when a specified condition is true.

---

## 7. What happens when the condition in an if statement is false?

When the condition is false, the statements inside the `if` block are skipped.

---

## 8. Can we use multiple conditions in an if statement?

Yes. Multiple conditions can be combined using logical operators such as:

* `&&` (AND)
* `||` (OR)
* `!` (NOT)

Example:

```c id="5a0y9u"
if(age > 18 && citizen == 1)
{
    printf("Eligible");
}
```

---

# Questions on if-else

## 9. Difference between if and if-else?

| if                                       | if-else                                   |
| ---------------------------------------- | ----------------------------------------- |
| Executes block only if condition is true | Provides two alternatives                 |
| No action if condition is false          | Executes else block if condition is false |

---

## 10. What is nested if?

A nested `if` means placing one `if` statement inside another `if` statement.

It is used when multiple dependent conditions need to be checked.

---

## 11. What is an else-if ladder?

An `else-if` ladder is a sequence of conditions checked one after another. The first true condition executes its corresponding block.

---

# Questions on switch Statement

## 12. What is a switch statement?

The `switch` statement is a multi-way branching statement used to select one block of code among many alternatives.

---

## 13. Why is break used in switch?

The `break` statement terminates the switch block and prevents fall-through to the next case.

---

## 14. What is fall-through in switch?

If `break` is omitted, execution continues into the next case. This behavior is called fall-through.

Example:

```c id="c5i68r"
switch(x)
{
    case 1:
        printf("One");

    case 2:
        printf("Two");
}
```

If `x = 1`, both `One` and `Two` will print.

---

## 15. What is the purpose of default in switch?

The `default` case executes when none of the cases match the expression.

---

## 16. Which data types are allowed in switch?

In C, `switch` supports:

* int
* char
* enum

Floating-point values are not allowed.

---

## 17. Difference between switch and if-else?

| switch                  | if-else                        |
| ----------------------- | ------------------------------ |
| Used for fixed values   | Used for ranges and conditions |
| More readable for menus | Better for complex logic       |
| Faster in some cases    | Slower for many conditions     |

---

# Questions on Loops

## 18. What is a loop?

A loop is a control structure that repeatedly executes a block of code while a condition remains true.

---

## 19. What is an infinite loop?

An infinite loop is a loop that never terminates because its condition never becomes false.

Example:

```c id="zwu5ol"
while(1)
{
}
```

---

## 20. Difference between while and do-while?

| while                              | do-while                          |
| ---------------------------------- | --------------------------------- |
| Condition checked before execution | Condition checked after execution |
| May execute zero times             | Executes at least once            |
| Entry-controlled loop              | Exit-controlled loop              |

---

## 21. When should we use a for loop?

A `for` loop is preferred when the number of iterations is known beforehand.

---

## 22. Can we write a for loop without initialization, condition, or increment?

Yes.

Example:

```c id="0dbi4m"
for( ; ; )
{
    printf("Infinite Loop");
}
```

This creates an infinite loop.

---

## 23. Which loop is fastest in C?

Generally, the `for` loop is considered faster and more compact because initialization, condition, and increment are written together.

However, performance differences are usually very small.

---

## 24. What is a nested loop?

A nested loop is a loop inside another loop.

The outer loop controls rows, and the inner loop controls columns in pattern problems.

---

## 25. What are entry-controlled and exit-controlled loops?

### Entry-Controlled Loop

Condition is checked before execution.

Examples:

* while
* for

### Exit-Controlled Loop

Condition is checked after execution.

Example:

* do-while

---

# Questions on break and continue

## 26. What is break statement?

The `break` statement immediately terminates the nearest loop or switch statement.

---

## 27. What is continue statement?

The `continue` statement skips the current iteration and transfers control to the next iteration of the loop.

---

## 28. Difference between break and continue?

| break                      | continue                        |
| -------------------------- | ------------------------------- |
| Terminates loop completely | Skips current iteration         |
| Control exits the loop     | Control moves to next iteration |

---

# Questions on goto

## 29. What is goto statement?

The `goto` statement transfers control directly to a labeled statement.

---

## 30. Why is goto discouraged?

The `goto` statement makes programs difficult to read, debug, and maintain. Excessive use may create spaghetti code.

---

# Questions on return

## 31. What is return statement?

The `return` statement terminates a function and optionally returns a value to the calling function.

---

## 32. Can a function have multiple return statements?

Yes. A function may contain multiple `return` statements depending on conditions.

---

# Conceptual and Advanced Questions

## 33. Which loop is guaranteed to execute at least once?

The `do-while` loop executes at least once because the condition is checked after execution.

---

## 34. Can we use break outside a loop?

No. Using `break` outside a loop or switch generates an error.

---

## 35. Can continue be used in switch?

No. `continue` is only meaningful inside loops.

---

## 36. Can we nest switch statements?

Yes. A `switch` statement can be placed inside another `switch`.

---

## 37. Which control statement is best for menu-driven programs?

The `switch` statement is best for menu-driven programs because it handles multiple fixed options clearly.

---

## 38. What happens if break is not used inside loops?

The loop continues execution normally unless the condition becomes false.

---

## 39. What is the difference between syntax error and logical error in loops?

| Syntax Error            | Logical Error                       |
| ----------------------- | ----------------------------------- |
| Violates language rules | Program runs but gives wrong output |
| Detected by compiler    | Detected during execution           |

---

## 40. How do control statements improve programming?

Control statements:

* Reduce repetition
* Improve efficiency
* Support decision-making
* Make programs dynamic and interactive

---

# Output-Based Interview Questions

## 41. Predict the Output

```c id="9wzvw7"
int i;

for(i = 1; i <= 5; i++)
{
    if(i == 3)
        break;

    printf("%d ", i);
}
```

## Output

```text id="7ff58g"
1 2
```

---

## 42. Predict the Output

```c id="w6sl0h"
int i = 1;

while(i <= 3)
{
    printf("%d ", i);
    i++;
}
```

## Output

```text id="4v6vfg"
1 2 3
```

---

## 43. Predict the Output

```c id="1spjlwm"
int i = 1;

do
{
    printf("%d ", i);
    i++;
}
while(i < 1);
```

## Output

```text id="eljlwm"
1
```

## Explanation

The `do-while` loop executes once before checking the condition.

---

# Programming-Based Interview Questions

## 44. Write a program to check whether a number is prime.

---

## 45. Write a program to print Fibonacci series using loops.

---

## 46. Write a menu-driven calculator using switch statement.

---

## 47. Write a program to reverse a number using loops.

---

## 48. Write a program to print patterns using nested loops.

---

# HR + Conceptual Combined Questions

## 49. Why are control statements important in programming?

Control statements make programs intelligent by enabling decision-making, repetition, and flexible execution flow.

---

## 50. Which control statement do you use most frequently and why?

Usually:

* `if-else` for decisions
* `for` loop for iterations
* `switch` for menu-driven applications

because they improve readability and program structure.
