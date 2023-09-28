# =============================================================================
# 1. Write recursive function, q1(n), that takes a positive integer as input and returns a list of numbers as described below.
# For 1 through 5, q1 produces:
# >>> q1(1)
# [1]
# >>> q1(2)
# [1, 4, 1]
# >>> q1(3)
# [1, 4, 1, 9, 1, 4, 1]
# >>> q1(4)
# [1, 4, 1, 9, 1, 4, 1, 16, 1, 4, 1, 9, 1, 4, 1]
# >>> q1(5)
# [1, 4, 1, 9, 1, 4, 1, 16, 1, 4, 1, 9, 1, 4, 1, 25, 1, 4, 1, 9, 1, 4, 1, 16, 1, 4, 1, 9, 1, 4, 1]
# 2. Implement recursive function q2(n, listOfStrings) that takes a positive number, n, and a list of strings as input and returns the number of strings in the list with length less than n. The function must be recursive and must contain no loops.
# 3. Write a recursive function q3(item1, item2) that returns True or False depending on whether or not the two given input items are "similar" according to the following definition.
# Two items are similar if:
# they are the same type or both are numbers (i.e. int or float type), and
# if they are lists, they are of the same length and each pair of corresponding list items (i.e. items with the same index) is similar.
# =============================================================================
def q1(n):
    if n < 1:
        return []
    else:
        return q1(n-1) + [n**2] + q1(n-1)
  
def q2(n, listOfStrings):
    total = 0
    if listOfStrings != []:
        count = q2(n,listOfStrings[1:]) 
        if len(listOfStrings[0]) < n:
            total = 1 + count
        else:
            total = count
    return total
def q3(item1, item2):
    if type(item1) == type(item2):
        if type(item1) == list:
            if len(item1)!= len(item2):
                return False
            else:
                length = len(item1)
                index = 0
                result = True
                while index < length:
                    if q3(item1[index],item2[index]) == True:
                        index += 1
                    else:
                        result = False
                        index +=1
                return result
        else:
            return True
    if type(item1) == float and type(item2) == int:
        return True
    if type(item1) == int and type(item2) == float:
        return True
    else:   
        return False