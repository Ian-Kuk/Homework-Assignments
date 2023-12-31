# -*- coding: utf-8 -*-
"""ENGR2995 - Homework 01 - Python programming language.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1ZNtlBvLv1Hbps8Vi4VYwd1wq7Hhtcnm7

# Python programming language

This is high-level overview of the Python programming language presented mostly from-scratch but may require understanding of core programming concepts covered in previous course. You are responsible for reading and understanding the handout material (try modifying the code and re-running it to test your understanding!) as well as completing the exercises (`Exercises`) listed after each main heading (`1. Basics`, `2. Collections`, `3. Conditionals and Iteration`)

## 1. Basics

### Programs

What is a program?

- **input**: get data from keyboard, disk, or device.
- **output**: display data on the screen or send data to file/device.
- **math**: perform mathematical operations (addition/multiplication/etc.)
- **conditional execution**: check for certain conditions and execute the appropriate sequence of statements.
- **repetition**: perform some action repeatedly with some variation.

### Values and types

- **value**: fundamental "thing" (letters/numbers) that a program manipulates. Stored in a *variable* or computed from an *expression*.
- **type**: the type of a value determines how it can be used in expressions. Example: integers (`int`), floating point numbers (`float`), boolean (`bool`), and strings (`str`). The type of a value is *inferred* rather than declared. Note the difference in how we declare 4 as either an integer, float, or string. Integers have no decimal point. Floats *must* have a decimal point. Strings are enclosed in either single (`'`) or double (`"`) quotes.
"""

# this cell show how to represent the value 4 as an integer
print(4)
type(4)

# this cell show how to represent the value 4 as a floating point number
print(4.)
type(4.)

# this cell show how to represent the value 4 as a string
print("4")
type("4")

# this cell demonstrates that commas are used to separate argument to a function
# and can't be used in a floating point or integer value
print(1,000,000)

# example of a boolean
print(True)
type(True)

"""### Variables and variable names

A **variable** is a name that refers to a value. The **assignment statement** creates new variables and assigns values. **Variable names** should "document" what the variable is used for.
- Can use letters and numbers (must start with a letter)
- Case-sensitive.
- Arbitrary length.
- Can use underscore (`_`) to separate words.
- Cannot use `python` keywords
"""

# an assignment statement contains the equal "=" sign.
# the left-hand side should be a variable name
# the right-hand side should be a value
x = 4
print(x)
type(x)

a_descriptive_variable_name = "easier to read"

# incompatible variable names are an example of a SyntaxErorr
# uncomment the next line to run
#more$ = 1000000

# this cell prints out a complete list of python keywords
import keyword
print(keyword.kwlist)

"""### Statements and expressions

**Statements** are instructions the `python` interpreter can execute. `python` executes statements and displays the result if there is one. Can you identify what statements/results we have seen so far?

Code blocks contain a sequence of statements. The results appear one at a time as the statements execute.
"""

print(1)
x = 2
print(x)

"""### Operators and expressions

**operators**: symbols that represent computations. The evaluation of expressions containing operators follow common order-of-operations rules (PEMDAS). Some common operators are given below.
- `+`: addition
- `-`: subtraction
- `*`: multiplication
- `/`: division
- `(` `)`: parenthesis
- `**`: exponentiation
- `//`: floor division
- `%`: modulus

Operators can be combined with assignment statements (ex: `+=`, `-=`, `*=`) to perform tasks such as variable incrementing or decrementing.
"""

print("5+4=",5+4)
print("5-4=",5-4)
print("5*4=",5*4)
print("5/4=",5/4)
print("5**(4/2)=",5**(4/2))
print("5//4=",5//4)
print("5%4=",5%4)

a = 1
b = 0
a -= 1
b += 1
print("a =", a)
print("b =", b)

"""### Function calls

**Functions** contain code that is run by performing a function call. We've already seen several examples of built-in Python function like `type` and `print`. Functions are run by calling the function name followed by a set of parentheses. Functions may accept input (**arguments**, enclosed in the parantheses of the function call) and return output (**return value**, specified by a return statement at the end of function)

In this example, we are calling the `type` function. We pass in the argument `"32"`, a string-typed value. The return value is an object `<type 'string'>` that indicates the data type of the value passed as an argument to the `type` function. We can assign return values to variables using an assignment statement.

Some functions, like `print`, do not have a return value. The `print` function here simply displays the argument passed in and returns a `None` value.
"""

type("32")

type_of_32 = type("32")
print(type_of_32)

x = print(3)
print(x)

"""### Modules

A module is a file that contains a collection of function definitions. Modules can be imported in their entirety, or select functions or submodules can be import individually. There are many [default modules](https://docs.python.org/3/py-modindex.html) distributed with python, and others can be installed with a python package manager such as `pip` or `conda`. The python environment in Colab contains a loadout of common data science and machine learning packages in addition to the default python modules. We have some examples of importing an entire library (`math`), calling imported math functions using "dot notation", and importing a select function.
"""

# the math module
import math

# a list of function contained in the math module
help(math)

decibel = math.log10(17.0)
angle = 1.5
height = math.sin(angle)

# numpy is an array-based computing library
# sklearn is a machine learning library, which contains the function train_test_split within the model_selection submodule
import numpy as np
from sklearn.model_selection import train_test_split

# this creates an array of zeros of shape (100, 3)
X = np.zeros((100, 3))
# this splits the newly created array
X_train, X_test = train_test_split(X)

"""### Defining functions

We can "define" our own functions, as well. A valid function definition requires the python keyword `def`, the function name, indented statements and/or expressions, and optionally function arguments enclosed in parenthesis followed by a colon. The python interpreter determines the end of a function from or the next line of unindented code. A `return` statement may also be used to end the execution of a function and return some result(s).

```python
def function_name(argument1, argument2, ..):
  val1 = indented_statement_1
  val2 = indented_statement_2
  return val1, val2

```
"""

def print_twice(x):
  print(x, x)
#this unindented line ends the function definition
y = 10
# example of function with no return statement
print_twice(y)

def cat(x, y):
  return x+y

x = "Thermo"
y = "dynamics"
# example of a function with a return statement
xy = cat(x, y)
print(xy)

"""### Debugging errors

There are three main types of errors to be aware of:

1. **Syntax errors**: the code is invalid due to errors regarding the structure of the program and the rules about that structure. Example: variable names violate convention, there is a typo in a function call or variable reference, improper indentation
2. **Runtime errors**: the code is valid but something "bad" happens at program execution. Example: you try to open a file that is missing
3. **Semantic errors**: the code is valid but the program you wrote is not the program you wanted. Example: switching two variable name references in a function definition, incorrect setting of keyword function arguments.

The tried-and-true method to begin the process of debugging syntax and runtime errors is to copy and paste the error message into Google search and find the most relevant StackOverflow discussion thread.

## Exercises

### 1. Compute the area of a triangle

Write a function to compute the area of an arbitrary triangle using the lengths of the three sides (`a`, `b`, `c`). Use the function to determine area of a triangle with side lengths of 3 cm, 5 cm, and 7 cm.
"""



"""### 2. Debug the code

The following code cells all contain errors. Fix the errors so each code cell runs successfully. Some cells have comments regarding the intending outcome of the operation that should be followed.
"""

today = "Friday"

import math
x = 10
print(math.log10(x))

def my_function(x):
  print(x+1)
  print(x+2)
  print(x+3)

my_function(0)

# in this function, we'd like to multiply two numbers together and return the modulo 3
def my_operation(a, b):
  c = a*b
  return c%3
my_operation(4,6)

# in this function, we'd like to return the square root of the square root of a number
import math
def double_sqrt(x):
  y = math.sqrt(x)
  return math.sqrt(y)
double_sqrt(8)

"""## 2. Collections

### Lists

- **list**: an ordered set of values, where each value is identified by an index. A sequence.
- The values that make up a list are called **elements**.
- Elements in a list don't have to be the same data type. Lists can be nested within lists.

### List values

Lists are constructed by placing elements within `[` `]` square brackets, each element separated by a comma `,`.
"""

list_of_integers = [1,2,3,4]
list_of_strings = ['H', 'He', 'Li', 'Be']
nested_list = [[1,2,3],[4,5,6],[7,8,9]]

"""Lists can be constructed from built-in functions. The `range(start, stop, step)` function is used to generate a sequence. The `list` function converts the sequence to a python `list` object."""

# range(1, 5) gives sequence starting at 1 going up to but not including 5
print(list(range(1, 5)))
# range(10) gives sequence starting at 0 going up to but not including 10
print(list(range(10)))
# range(1, 10, 2) gives sequences from 1 up to but not including 10 by 2's
print(list(range(1, 10, 2)))

"""### Accessing elements of lists

Lists are zero-indexed by integers. Lists can be indexed from the start (positive integer) or from the end (negative integer). Trying to pass an index that is beyond the size of the list returns an error message ("list index out of range").
"""

numbers = [17, 5]
# indexing from the start
print(numbers[0])
print(numbers[1])
# indexing from the end
print(numbers[-1])
print(numbers[-2])

# uncomment the line below for the error
#print(numbers[2])

# len is built-in function to find the length of a list
n_elements = len(numbers)
print(n_elements)

# determining list "membership"
list_of_strings = ['H', 'He', 'Li', 'Be']
h_in_list = 'H' in list_of_strings
c_in_list = 'C' in list_of_strings
print(h_in_list, c_in_list)

"""### Slicing

The `slice(start, stop, step)` function is used to select elements from a sequence or list. The is a simplified list slicing notation `my_list[start:stop:step]`. Remember that lists are zero-indexed i.e. the first element has an index of 0.
"""

a = ['a', 'b', 'c', 'd', 'e', 'f']

# select element 1 up to but not including element 3
print(a[1:3])
print(a[slice(1, 3)])

# select element 0 up to but not including element 4
a[:4]

# select element 3 up to the end of the sequence
a[3:]

# a simple way to reverse a sequence
# select element 0 up to the end, with a step size of -1
a[::-1]

"""### List operations

Some operators (`+`, `*`) work with python lists objects. The `+` operator used with two lists concantenates the two lists. The `*` operator used with a list and an integer concatenates several copies of the same list.
"""

a = [1, 2, 3]
b = [4, 5, 6]
# add the elements of b to the end of list a
ab = a+b
print(ab)

# you can use the + operator to add elements to the end of a list
c = ab + [7]
print(c)

# copy list a three times
print(a*3)

"""### List methods

You can find a list of `list` methods or dot-functions [here](https://docs.python.org/3/tutorial/datastructures.html#more-on-lists). Any `list` object can use these functions.
"""

# add an element to a list
c.append(8)
print(c)

# remove an element by index
_ = c.pop(-1)
print(c)

# sort a list
d = [4, 1, 3, 2]
d.sort()
print(d)

"""### Modifying elements of lists

Lists are mutable -- you can change the values of elements
"""

a = ['a', 'b', 'c', 'd', 'e', 'f']

# change a single element
a[-1] = 'z'
print(a[-1])

# change a slice
a[1:3] = ['x', 'y']
print(a[1:3])

print(a)

# delete a slice
del(a[1:5])
print(a)

# this assignment statement creates a reference to list "d" rather than a copy
x = d
x[3] = 10
print(d) # oops

# copy the list if you intend to change the elements
d[3] = 4
x = d.copy()
x[3] = 10
print(d)

"""### Tuples

Constructed by placing elements between `(` `)` parenthesis, each element separated by commas `,`. Lists largely contain the same functionality as lists, but are **immutable**. One common use for tuples is tuple assignment, or the unpacking of the elements in a sequence into a set of individual variables.
"""

# an example of a tuple
a_tuple = (1, 2, 3, 4)
print(a_tuple)

# you can slice but don't try to change an element
a_tuple_slice = a_tuple[slice(2)]
print(a_tuple_slice)

# an example of tuple assignment
a, b, c = [1, 2, 3]
print(a)

"""### Dictionaries

A **dictionary** is an unordered set of key-value pairs. The key functions similarly to the index in lists and tuples i.e. it is used to access values or elements. Dictionaries can be constructed by placing key-value pairs between `{` `}` curly braces, each key-value pair separated by commas. The key-value pair is formatted like `key:value` using the colon.
"""

periodic_table = {'H': 'Hydrogen', 'He': 'Helium'}

"""### Dictionary Methods"""

# key-value pairs can be added
periodic_table['Li'] = 'Lithium'

# key-value pairs can be deleted
periodic_table['G'] = 'Gomesium'
del(periodic_table['G'])

# print the keys of a dictionary
print(periodic_table.keys())

# print the values of a dictionary
print(periodic_table.values())

# print the key-value pairs as a sequence tuples
print(periodic_table.items())

"""## Exercises

### 1. A list of numbers

Create a list containing atleast 5 integers and 5 floating point numbers. In these questions, indexing starts at element 0 i.e. zero-indexed.

1.   Print out the list in reverse.
2.   Print out the final three elements.
3.   Print out every third element starting at element 1.
"""

a = [1,2,3,4,5,6.0,7.0,8.0,9.0,10.0]
b = a.copy()
b.reverse()
print(b)
print(a[len(a)-3:len(a)])
print(a[::3])

"""### 2. Mascots

Create a dictionary containing the names of each Big 10 University (i.e. University of Iowa) as the dictionary keys and the mascot of each university (i.e. Herky the Hawk) as the value. Print out the dictionary.
"""

bigTen = {'University of Iowa': 'Herky the Hawk', 'University of Michigan' : 'Sparty', 'Ohio State' : 'Brutus Buckeye' , 'Penn State' : 'Nittany Lions', 'Rutgers University' : 'Scarlet Knight', 'University of Minnesota':'Goldy Gopher','Purdue University':'Purdue Pete','University of Illinois':'Chief Illiniwek','University of Wisconsin':'Buckey Badger','Indiana University':'Hoosiers','University of Maryland':'Testudo','University of Nebraska-Lincoln' : 'Herbie Husker','Northwestern University': 'Willie the Wildcat' }
print(bigTen)

"""## 3. Conditionals and Iteration

### Comparison operators

Comparison operators are used to create conditional statements. These statements result in a boolean variable, either `True` or `False`.

- `==`: equal
- `!=`: not equal
- `>`: greater than
- `<`: less than
- `>=`: greater than or equal to
- `<=`: less than or equal to
"""

x = 1

print(x>0)

print(x!=1)

"""### Logical operators

Logical operators are used to chain comparison statements together to create compound conditional statements.

- `and`: booleans on either side of `and` should be `True` else the statement is `False`.
- `not`: takes the complement of a boolean variable e.g. `not True` is `False`.
- `or`: one boolean on either side of `or` should be `True` else the statement is `False`.

To evaluate compound conditional statements, evaluate the comparison statements first then evaluate the logical statements.

Example:
```python
x = 1
x > 0 and x < 2
```
evaluates to
```python
True and True
```
evaluates to
```python
True
```
"""

print(x>0 and x<2)

print(not x>0)

print(x>0 or x<0)

"""### Conditional execution

Conditional execution in python is controlled by `if` statements. If statements are composed of a conditional header statement and an indented code block. If the conditional header statement evaluates to `False`, the code in the `else` code block to run instead. The `else` code block is optional.

```python
if CONDITIONAL_HEADER_STATEMENT:
  CONDITIONALLY_TRUE_STATEMENT_1
  CONDITIONALLY_TRUE_STATEMENT_2
else:
  CONDITIONALLY_FALSE_STATEMENT_1
  CONDITIONALLY_FALSE_STATEMENT_2
```
"""

if x>0:
  print("x is positive")

# note that everything inside the function definition is indented once
def parity(x):
  # note that the conditionally true/false statements are indented twice
  if x%2 == 0:
    print(x, "is even")
  else:
    print(x, "is odd")

parity(x)

"""### Chained conditionals

We can chain multiple conditional header statements together with an else-if or `elif` statement. These statements are evaluated sequentially downward until one statement evaluates to `True`. Once a conditional statement is evaluated to `True`, he following `elif` or `else` statements are skipped. There is no limit to the number of `elif` statements. It is good practice to end with `else`.
"""

def number_comparison(x, y):
  if x<y:
    print(x, "is less than", y)
  elif x>y:
    print(x, "is greater than", y)
  else:
    print(x, "is equal to", y)

number_comparison(10, 20)

"""### Nested conditionals

Sometimes it is simpler and easier to read when nesting conditionals within one another rather than writing a long list of `elif` statements or long conditional header statements. Sometimes not.
"""

def nested_number_comparison(x, y):
  if x==y:
    print(x, "is equal to", y)
  else:
    if x<y:
      print(x, "is less than", y)
    else:
      print(x, "is greater than", y)

nested_number_comparison(10, 20)

"""### The `while` loop

The purpose of a `while` loop is to perform some computation repeatedly, with or without variation, until a stopping criteria is reached. A `while` loop is typically formatted like

```python
while CONDITION:
  STATEMENT_1
  STATEMENT_2
else: # optional
  ELSE_STATEMENT_1
  ELSE_STATEMENT_2
```

In this example, `STATEMENT_1` and `STATEMENT_2` are evaluated repeatedly until `CONDITION` evaluates to `False`. The `STATEMENT`s may or may not involve variables that change over the course of the evaluation of the code block within the `while` loop. When `CONDITION` evaluates to `False`, the code in the optional `else` block is executed.
"""

def increment_and_compare(a, b):
  while a<b:
    print(a)
    a += 1
  else:
    print("I'm in the else loop")

a = 0
b = 10
increment_and_compare(a, b)

"""### `break`, `continue`, and `pass`

- **break**:  jump out of the closest enclosing loop
- **continue**: go back to the top of the closest enclosing loop
- **pass**: do nothing

```python
while TEST1:
  STATEMENTS
  if TEST2: break # exit while loop
  if TEST3: continue # go back to TEST1
else:
  # these statements run if we didn't "break"
  STATEMENTS
```
"""

x = 10
while x: # NOTE: any number != 0 will be interpreted as True ...
  x = x-1
  # this should return to the top if x is odd
  if x%2 != 0: continue
  # we should print if x is even and greater than equal to zero
  print(x)

# this test will never evaluate False
while True:
  name = input('Enter name: ')
  # the only way to exit this loop is trigger the "break"
  if name == 'stop': break
  print("Hello", name)

"""### The `for` loop

The purpose of a `for` loop is to repeatly perform some computation, with or without variation, for a fixed number of iteration determined by the size of a sequence object (`list`, `tuple`, `str`). A simple `for` loop looks like:

```python
for TARGET in OBJECT:
  STATEMENTS
```

Here, `OBJECT` is an iterable object, like a `list`. In the case of a `list`, the elements of the list are, starting at index 0 and going to the end of the list,  iteratively assigned to the variable name `TARGET`. The `STATEMENTS` typically involve some computation using the variable name reference `TARGET`. A more-complicated `for` loop could look like:

```python
for TARGET in OBJECT:
  STATEMENTS
  if TEST1: break # exit loop
  if TEST2: continue # back to the top of the for loop with next TARGET
else: # optional, runs at the end
  STATEMENTS
```
"""

symbols = ['H', 'He', 'Li', 'Be']
for x in symbols:
  print(x)

for i in range(5):
  print(i)

for i in range(4):
  print(symbols[i])

"""### List comprehension

List comprehension is a fast technique for constructing a list from an iterable object, such as another list. The syntax is similar to the `for` loop and can involve conditional execution.
"""

# construct a list from a range object, doubling each element before adding it to the new list
mylist = [2*i for i in range(10)]
print(mylist)

# this time, only double the even numbers and discard the odd numbers
mylist = [2*i for i in range(10) if i%2==0]
print(mylist)

"""### More iterable objects"""

# the enumerate() function wraps an iterable object and passes back both indices and elements
for idx, elem in enumerate(symbols): # passed back as a tuple, assigned to idx and elem by tuple assignment
  print("List index:", idx, "Element symbol", elem)

names = ['Hydrogen', 'Helium', 'Lithium', 'Beryllium']
# the zip() function performs an element-wise combination of two or more iterable objects
for symbol, name in zip(symbols, names):
  print("Element name", name, "with symbol", symbol)

# The .items() dot-function of the dictionary object passes back a sequence of (key, value) paired tuples that can be iterated.
D = {'k1': 'v1', 'k2': 'v2', 'k3': 'v3'}
for key, value in D.items():
  print(key, value)

"""## Exercises

### 1. Prime numbers

Write a function that determines all prime numbers less than 100. A prime number is an integer that is evenly divisible only by 1 and itself. Return the set of primes less than 100 as a `list`.
"""

primeList = []
def findPrime(lower, upper):
  for number in range(lower, upper+1):
    if number > 1:
      for divisor in range (2,number):
        if(number%divisor) == 0:
          break
      else:
        primeList.append(number)
  print(primeList)
findPrime(1,100)

"""### 2. Fibonacci numbers

The Fibonacci sequence is defined by the recurrance relation:

$$
F_0 = 0, F_1 = 1\\
F_n = F_{n-1} + F_{n-2} (n>1)
$$

Write a function to compute the first 20 Fibonacci numbers. Return the 20 numbers as a `list`.
"""

def Fibonacci(n):
  fibonnacciList = [0,1]
  n0 = 0
  n1 = 1
  if(n == 0):
    fibonnacciList = [0]
  else:
    for i in range(1,n):
      n2 = n1 + n0
      n0 = n1
      n1 = n2
      fibonnacciList.append(n2)
  print(fibonnacciList)
Fibonacci(20)