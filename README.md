# sorting tool
## Sort numbers/lines/words by command-line arguments with input from files or users.

#### There are 4 command-line arguments to sort: `-dataType` (`long, word, line`); `-sortingType` (`natural, byCount`); `-inputFile` (name of file); `-outputFile` (name of file).

---
##### Example 1 - sorting longs by count (`>` is user input):
---
```
> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
-2: 1 time(s), 14%
4: 1 time(s), 14%
33: 1 time(s), 14%
42: 1 time(s), 14%
1: 3 time(s), 43%
```
---
##### Example 2 - sorting numbers naturally (`>` is user input):
---
```
> 1 -2   33 4
> 42
> 1                 1
Total numbers: 7.
Sorted data: -2 1 1 1 4 33 42 
```
---
##### Example 3 - sorting words naturally (`>` is user input):
---
```
> 1 -2   33 4
> 42
> 1                 1
Total words: 7.
Sorted data: -2 1 1 1 33 4 42 
```
---
##### Example 4 - sorting lines naturally (`>` is user input):
---
```
> 1 -2   33 4
> 42
> 1                 1
Total lines: 3
Sorted data:
1                 1
1 -2   33 4
42
```
