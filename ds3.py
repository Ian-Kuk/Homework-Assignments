# CS 1210, Fall 2021, Discussion Section 3

#
# TODAY'S WORK: The TA will demonstrate some of the parts in this file.
# Students will be graded on correct completion of Parts 5 and 6 (but
# the entire file must load into Python without error)
#
# PART 1. STUDENTS: Download three files from the Discussion Sections section
# of the course website:
# - this file, ds3.py
# - wordsMany.txt, a set of 113,809 words permitted in crossword puzzles
#   from Moby Project (now at Project Gutenberg).
#        see http://icon.shef.ac.uk/Moby/mwords.html
#        and http://www.gutenberg.org/ebooks/3201
# - wordsFew.txt, a file containing three words
#
# PUT ALL THREE FILES IN THE SAME FOLDER
#
# PART 2. TA: demonstrate, explain the testWords function below.
# It is not necessary for students to understand how opening/reading files
# works right now.  Students only need to be able to use the function and
# modify the non-file-related part slightly.
#
# Run testWords on a couple examples:
#  1) with returnTrue(word) as the if condition,
#          testWords("wordsFew.txt") prints three words
#          NOTE: don't run with returnTrue(word) on wordsMany.txt.
#                You don't want to print 113,809 words!
#  2) with 'qi' in word
#     as the if condition, testWords('wordsMany.txt') prints eight words
#
def testWords(filename):
    fileStream = open(filename, 'r')
    for line in fileStream:
        word = line.strip()
        if returnTrue(word):
        #if 'qi' in word:
        #if hasMoreThanTwentyChars(word):
        #if hasThreeConsecPairs(word):
        #if hasFourConsecVowels(word):
            print(word)

# not a useful function except to understand basic structure of
# testWords above.
#
# Always returns True.
#
def returnTrue(string):
    return True

# PART 3. TA: finish this hasMoreThanTwentyChars(word) stub below
#
#     1) test it by calling it directly on a few words
#     2) test if more fully by calling it in testWords and
#                        calling testWords with wordsMany.txt
#        This should yield all words in wordsMany.txt longer than 20 chars.
#
#         Expected output:
#
#         >>> testWords("wordsMany.txt")
#         counterdemonstrations
#         hyperaggressivenesses
#         microminiaturizations
#
def hasMoreThanTwentyChars(word):
    # change this
    return False

#
# PART 4. TA: describe and demonstrate solution to the following problem
#             posed on the well-known Car Talk radio show:
#
#        Problem: Find all words that contain three consecutive pairs of double letters.
#
#        Example: committee is *not* such as word.  There are three sets of double letters, but
#        there is an 'i' between the first ('mm') and second ('tt')
#
#        TA: After describing this code, execute testWords("wordsMany.txt") with
#            using if hasThreeConsecPairs(word):
#            instead of if returnTrue(word):
#            Four words should be printed out of the 113,809
#
#     Solution
#
def hasThreeConsecPairs(word):
    result = False
    index = 0
    while (index + 5) < len(word):
        if ((word[index] == word[index + 1]) and
            (word[index + 2] == word[index + 3]) and
            (word[index + 4] == word[index + 5])):
            result = True
            break
        index = index + 1
    return result

# PART 5. Write function hasMoreVowelsThanNonVowels(word)
# that returns True if the given word contains more vowels than non-vowels
# (consider only lowercase 'a', 'e', 'i', 'o', 'u' vowels, not 'y' and not
# any upper case occurrences of the vowels), and False otherwise.
#
# For example
# >>> hasMoreVowelsThanNonVowels("aaa")
# True
# >>> hasMoreVowelsThanNonVowels("aabb")
# False
# >>> hasMoreVowelsThanNonVowels("aaAAA")
# False
# The last one is False because 'A's are considered non-vowels.
# 
# You may not use any string methods for this problem. Just count the vowels
# using a loop!
#
# change this to be correct
def hasMoreVowelsThanNonVowels(word):
    result = False
    return result

# PART 6. Write function hasFourConsecVowels(word)
# that returns True if the given word contains four (or more) vowels in a row
# (consider only lowercase 'a', 'e', 'i', 'o', 'u' vowels, not 'y')
#
# FOR YOUR TESTING:
# Test your code on the big wordsMany.txt file by using 
#         if hasFourConsecVowels(word):
# instead of other 'if's in testWords, and calling testWords("wordsMany.txt")
#
# You might be surprised to see how many English words have four consecutive vowels!

# change this to be correct
def hasFourConsecVowels(word):
    result = False
    return result






