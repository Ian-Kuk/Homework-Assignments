# =============================================================================
# Note: you may not use lists, dictionaries, list comprehensions, or similar Python constructs not taught yet in this course.
# 1. Write function q1(origString, repeatCount, lettersToRepeat) that takes as input a string of characters, origString, a non-negative integer, repeatCount, and string of lower-case letters, lettersToRepeat, and returns a new string such that each letter in origString that occurs (ignoring case!) in lettersToRepeat is replaced by repeatCount consecutive copies of that letter (with the same case as in the original).
# NOTE: You may not use any string methods except for lower(). For example,
# 2. Implement function q2(num, string1, string2) so that it returns True if the input strings are the same length and differ at exactly num character positions, and returns False otherwise.
# 3. Implement q3(inputString, minLetter, lettersToIgnore, specialLetter) so that it returns three things:
# the smallest letter in inputString that is both greater than minLetter and not in lettersToIgnore, or None if no such letter exists
# the highest index at which that smallest letter occurs, or None if such a letter does not exist
# True if specialLetter occurs an odd number of times in inputString, False otherwise
# inputString is a string a zero or more lower case letters, minLetter is a lower case letter, lettersToIgnore is a string of zero or more lower case letters, and special letter is a lower case letter NOT in lettersToIgnore. Use a simple while loop. You may not use any string methods (such as .count()), but you may use the 'in' or 'not in' operators.
#=========================================================
def q1(origString, repeatCount, lettersToRepeat):
    result = ""
    stop = 0
    while(len(origString)>stop):
        if origString[stop].lower() in lettersToRepeat :
            result = result + (origString[stop]*repeatCount)
        else:
            result = result + origString[stop]
        stop = stop +1
    return result
def q2(num,string1,string2):
    stop = 0
    compare = 0
    if len(string1)== len(string2):
        while (len(string1)>stop):
            if string1[stop] == string2[stop]:
                stop = stop + 1
            else:
                compare = compare + 1
                stop = stop + 1
    if len(string1) != len(string2):
        return False
    if compare == num:
        return True
    else:
        return False
def q3(inputString, minLetter, lettersToIgnore,specialLetter):
    smallest,smallestPlace,index,specialLetterAmount,specialStatus = None,None,0,0,False
    if len(inputString) > 0:
        while len(inputString) > index:
            if inputString[index] > minLetter and inputString[index] not in lettersToIgnore:
                if smallest == None:
                    smallest = inputString[index]
                    smallestPlace = index
                elif inputString[index] <= smallest:
                    smallest = inputString[index]
                    smallestPlace = index
                if inputString[index] == smallest:
                    smallestPlace = index
            if inputString[index] == specialLetter:
                specialLetterAmount += 1
            index += 1
        if specialLetterAmount % 2 > 0:
            specialStatus = True
    return smallest, smallestPlace, specialStatus