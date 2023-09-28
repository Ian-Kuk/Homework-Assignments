# =============================================================================
# IMPORTANT in order to run this program you also need the SMScollection file in the same folder, it will also be provided on the git repository
# 1. (2 points) Write function q1(infoDict, listOfLists). listOfLists is a list of zero or more lists of zero or more numbers. infoDict is a dictionary with numbers as keys. The values associated with keys can be of any type.
# 
# A number is considered "red" if the number is a key in the dictionary and has value "red". A number is considered "blue" if it is a key in the dictionary and has value "blue". Other numbers, whether or not they exist as keys in the dictionary, are considered "green".
# 
# A sublist of listOfLists is considered "red" if contains more red items than blue ones and more red items than green ones. A sublist is considered "blue" if it contains more blue items than red ones and more blue items than green ones. Other sublists are considered "green".
# 
# q1 returns a list that is the same length as listOfLists and such that item i is
# the largest element in listOfLists[i] if listOfLists[i] is red,
# the smallest element in listOfLists[i] if listOfLists[i] is blue,
# the most common element in listOfLists[i] if listOfLists[i] is green (if there is a tie for most common, take the smallest one, and use None if there are no elements)
# NOTE: you may not use Python's .count() method, nor the min() or max() functions. You may use the .sort() method or sorted() function.
# 
# 2.
# a. Write program q2(filename, minWordLengthToConsider = 1) that analyzes word frequencies in real-world text messages.
# 
# Text file SMScollection.txt contains 5574 SMS messages. There is additional information about the contents of the file in the associated "readme" file readmeSMScollection.txt, written by the creators of the dataset. The data was originally from this no-longer-working link: http://www.dt.fee.unicamp.br/~tiago/smsspamcollection/. Some information about the data set and its initial investigators is now here.
# 
# Each line of the file is represents one SMS/text message. The first item on every line is a label - 'ham' or 'spam' - indicating whether that line's SMS is considered spam or not. The rest of the line contains the text of the SMS/message. For example:
# spam  Congrats! 1 year special cinema pass for 2 is yours. call 09061209465 now! Call ...
# ham	Sorry, I'll call later in meeting.
# At the end, your program must print summary information, including at least:
# the number of ham and number of spam messages
# the total number of words found in ham messages and in spam messages
# the total number of different words found in ham messages, and the total number of different words in spam messages
# information, for both ham and for spam, about the ten (at least) most frequently occurring words that are at least minWordLengthToConsider characters long. This information must include both the count of the number of occurrences and the relative frequency of a word's occurrence as a percentage (how many times that word appears out of the total number of words in the relevant message set. For example, if "you" appeared 80 times in ham, out of 1250 total words in all the ham messages, the frequency would be 6.4%).
# the average length (in words, not characters) of ham messages and of spam messages
# Feel free to compute and print out additional information as well.
# 
# To accomplish this, your q2 function should:
# read all of the data from the input file
# extract individual words from the messages. This should include an effort to get ride of "extras" such as periods, commas, question and exclamation marks, and other characters that aren't part of a word. You should probably also ignore capitalization. Thus in the sample spam message above, you probably want to treat "Congrats!" as "congrats" in your frequency analysis. Note: the string strip() method is very useful for this. I strongly recommend you do not use the replace() method.
# build two dictionaries (Note: using dictionaries is required for full credit on this assignment), one for frequencies of words appearing in spam messages, one for frequencies of words from ham messages.
# extract the most frequently occurring words (of length at least minWordLengthToConsider)
# compute and print summary information
# =============================================================================
def q1(infoDict, listOfLists):
    keyNumbers = sorted(infoDict)
    finalList = []
    for lists in listOfLists:
        redCount = 0
        blueCount = 0
        greenCount = 0
        maxNumber = 0
        sortLists = sorted(lists)
        for numbers in lists:
            if numbers in keyNumbers:
                if infoDict[numbers] == 'red' :
                    redCount += 1
                if infoDict[numbers] == 'blue':
                    blueCount += 1
                if infoDict[numbers] != 'red' and infoDict[numbers] != 'blue':
                    greenCount += 1
            else:
                greenCount += 1
        if redCount > blueCount and redCount > greenCount:
            listNumbers = sorted(lists, reverse = True)
            finalList.append(listNumbers[0])
        elif blueCount > redCount and blueCount > greenCount:
            listNumbers = sorted(lists)
            finalList.append(listNumbers[0])
        else:
            for numbers in sortLists:
                count = 0
                commonNumber = numbers
                for numbers in lists:
                    if commonNumber == numbers:
                        count += 1
                if count > maxNumber:
                    maxNumber = count
                    numberSave = commonNumber
            if lists:
                finalList.append(numberSave)
            else:
                finalList.append('None')
                    
    return finalList
def q2(filename, minWordLengthToConsider = 1) :
    spamDic = {}
    hamDic = {}
    file = open(filename, encoding = "utf-8")
    hamCount = 0
    spamCount = 0
    hamWords = 0
    spamWords = 0
    hamUnique = 0
    spamUnique = 0
    for line in file:
        lineAsList = line.split()
        if lineAsList[0] == 'ham':
            hamCount += 1
        if lineAsList[0] == 'spam':
            spamCount += 1
        for words in lineAsList:
            hamKeyCheck = hamDic.keys()
            spamKeyCheck = spamDic.keys()
            words = words.lower()
            words.strip(".")
            words.strip(',')
            words.strip('?')
            words.strip('!')
            if lineAsList [0] == 'ham':
                hamWords = hamWords + 1
                if words in hamKeyCheck:
                    hamDic[words] = hamDic[words] + 1
                else:
                    hamDic[words] = 1
                    hamUnique += 1
            if lineAsList[0] == 'spam':
                spamWords = spamWords + 1
                if words in spamKeyCheck:
                    spamDic[words] = spamDic[words] + 1
                else:
                    spamDic[words] = 1
                    spamUnique += 1   
    if spamDic['spam'] == spamCount:
        del spamDic['spam']
    else:
        spamDic['spam'] = spamDic['spam'] - spamCount
    if hamDic['ham'] == hamCount:
        del hamDic['ham']
    else:
        hamDic['ham'] = hamDic['ham'] - hamCount
    spamUnique = len(spamDic)
    hamUnique = len(hamDic)
    sortedHam = dict(sorted(hamDic.items(), key=lambda item: item[1], reverse = True))
    sortedSpam = dict(sorted(spamDic.items(), key=lambda item: item[1], reverse = True))
    finalSpam = spamWords - spamCount
    finalHam = hamWords - hamCount
    avgHam = finalHam / hamCount
    avgSpam = finalSpam / spamCount
    print (f'The data consists of  {hamCount} ham messages and {spamCount} spam messages' )
    print (f'Average length of a ham message was {(avgHam):.2f}')
    print (f'Average length of a spam message was {(avgSpam):.2f}')
    print (f'Unique spam word: {(spamUnique)} total spam words: {(finalSpam)}')
    print (f'Unique ham word: {(hamUnique)} total ham words: {(finalHam)}')
    stopper1 = 0
    stopper2 = 0
    print('Ham')
    for wordCheck in sortedHam:
        if len(wordCheck) >= minWordLengthToConsider:
            print(f'"{wordCheck}" \t occured  {sortedHam[wordCheck]} times at a frequency of {((sortedHam[wordCheck] / finalHam) *100):.2f}%' )
            stopper1 += 1
        if stopper1 == 10:
            break
    print('Spam')
    for wordCheck2 in sortedSpam:
        if len(wordCheck2) >= minWordLengthToConsider:
            print(f'"{wordCheck2}" \t occured  {sortedSpam[wordCheck2]} times at a frequency of {((sortedSpam[wordCheck2] / finalSpam) *100):.2f}%' )
            stopper2 += 1
        if stopper2 == 10:
            break
