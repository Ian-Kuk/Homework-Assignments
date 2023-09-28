# =============================================================================
# 1. Write a function, q1(inputString, minLetter), that takes as input a string of letters and returns six things: the lexicographically smallest letter (z/Z > y/Y > ... > a/A) greater or equal to minLetter, the smallest index at which that letter occurs, the third smallest letter greater than or equal to minLetter, the smallest index at which that letter occurs, the most common letter, and how many times the most common letter occurs. The third smallest letter must be distinct from the second smallest which must be distinct from the smallest. E.g. 'b' is the second smallest and 'c' is the third smallest in 'ababdc'
# 
# Ignore case during computation. E.g. 'z' and 'Z' are considered the same letter. Use lower case when returning letters.
# 
# Return None for smallest and third smallest letters and corresponding indices when appropriate.
# 
# You may not assume that the input string contains at least three different characters (or even any characters at all). Make sure to recognize various situations where fewer than three different letters appear in the string, and where there is no third smallest or smallest greater than minLetter.
# 
# If two or more letters tie for most common, return the lexicographically largest one.
# 2. Implement function, q2(L, goalX, goalY), that takes a non-empty list, L, of [x,y] pairs, and two goal numbers, and returns tuple (closestXY, XorY) where closestXY is the item from L whose x distance from goal X or whose y distance from goalY is the minimum among all distances, and XorY is 'XMIN' if x distance is minimized and 'YMIN' otherwise. You may assume there is a unique answer. Note: You may not use built-in min or max functions.
# 3. Implement function q3(L) that takes as input a (possibly empty) list of (possibly empty) lists of numbers and returns a three-element list. The first element is a list of the sums of corresponding lists in L, the second element is the number of lists in L that contain more positive than negative values, and the third element is the maximum value among all items in all lists (or None if there is no maximum). Notes: you may NOT use Python's sum(), min(), or max() functions; instead, compute sums and max within a loop or loops. 0 (zero) is neither positive nor negative!
# =============================================================================
def q1(inputString, minLetter):
    index1,index2,index3,index4,check1,check2,letterCount,letterMax,finalCount = 0,0,0,0,0,0,0,0,0,
    firstLetter,secondLetter,thirdLetter,firstPlace,thirdPlace,highLetter,tempLetter = None,None,None,None,None,None,None
    inputString = inputString.lower()
    sort = sorted(inputString)
    containsLetters = inputString.islower()
    while (len(inputString) > index1):
        if (inputString[index1] >= minLetter): 
            if (firstLetter == None ):
                firstLetter = inputString[index1]
                index1 += 1
            elif (inputString[index1] < firstLetter):
                firstLetter = inputString[index1]
                index1 += 1
            else:
                index1 +=1   
        else:
            index1 +=1
    while (len(inputString) > index2):
        if(firstLetter!= None):
            if (inputString[index2] > firstLetter):
                if (secondLetter == None):
                    secondLetter = inputString[index2]
                    index2 += 1
                elif(inputString[index2] < secondLetter):
                    secondLetter = inputString[index2]
                    index2 += 1
                else:
                    index2 += 1
            else:
                index2 +=1
        else:
            index2 += 1
    while (len(inputString) > index3):
        if(secondLetter != None):
            if(inputString[index3] > secondLetter):
                if(thirdLetter==None):
                    thirdLetter = inputString[index3]
                    index3 += 1
                elif(inputString[index3]< thirdLetter):
                    thirdLetter = inputString[index3]
                    index3 += 1
                else:
                    index3 +=1
            else:
                index3+= 1
        else:
            index3+= 1
    while (len(inputString) > check1):
        if inputString[check1] == firstLetter:
            firstPlace = check1
            check1 += 1
            break
        else:
            check1 += 1
    while (len(inputString) > check2):
        if inputString[check2] == thirdLetter:
            thirdPlace = check2
            check2 += 1
            break
        else:
            check2 +=1
    while (len(inputString) > index4):
        if (index4 + 1) < (len(sort)):
            tempLetter = sort[index4]
            if sort[index4 + 1] == tempLetter:
                letterCount += 1
                if letterCount >= letterMax:
                    letterMax = letterCount
                    finalCount = letterMax + 1
                    highLetter = sort[index4]
                    index4 +=1
                else:
                    index4 +=1
            else:
                letterCount = 0
                index4 +=1        
        else:
            break  
    if letterMax == 0 and letterCount == 0 and containsLetters == True:
        highLetter = sort[(len(sort) - 1)]
        finalCount = 1       
    return firstLetter,firstPlace,thirdLetter,thirdPlace,highLetter,finalCount
def q2(L, goalX, goalY):
    index,index2,yValue,xValue,xPosition,yPosition = 0,0,None,None,None,None
    while (len(L)> index):
        if xValue == None:
            xValue = abs(L[index][0] - goalX)
            xPosition  = L[index]
            index += 1
        elif abs(L[index][0] - goalX) < xValue:
            xPosition = L[index]
            xValue = abs(L[index][0] - goalX)
            index += 1
        else:
            index +=1
    while (len(L)> index2):
        if yValue == None:
            yValue = abs(L[index2][1] - goalY)
            yPosition  = L[index2]
            index2 += 1
        elif abs(L[index2][1] - goalY) < yValue:
            yPosition = L[index2]
            yValue = abs(L[index2][1] - goalY)
            index2 += 1
        else:
            index2 +=1
    if xValue < yValue:
        return xPosition, 'XMIN'
    else:
        return yPosition, 'YMIN'
def q3(L):
    maxNum,posIndex,index1 = None,0,0
    sumIndex =[]
    while (len(L)> index1):
        positive,negaitive,index2 = 0,0,0
        sums = 0
        while(len(L[index1])> index2):
            sums += L[index1][index2]
            if L[index1][index2] > 0:
                positive +=1
            if L[index1][index2] < 0:
                negaitive +=1
            if maxNum == None:
                maxNum = L[index1][index2]
            if L[index1][index2] > maxNum:
                maxNum = L[index1][index2]
            index2 += 1
        if positive > negaitive:
            posIndex = posIndex + 1
        sumIndex.append(sums)
        final = [sumIndex,posIndex,maxNum]
        index1 +=1
    return final