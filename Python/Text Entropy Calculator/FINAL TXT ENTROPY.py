import re
import sys
import math
import os
from math import log

d= os.path.dirname(__file__) if "__file__" in locals() else os.getcwd() 
text= open(os.path.join(d,'test.txt'), encoding="utf-8").read()

#function to compute the base-2 logarithms of a floating point number.
def log2(number):
    return math.log(number) /log(2)

# function to normalize the text.
cleaner= re.compile('[^a-z]+')
def clean(text):
    return cleaner. sub(' ',text)

#letter
def letter(str1): 
    dict={}
    for n in str1:
        keys = dict.keys()
        if n in keys :
            dict[n]+=1
        else:
            dict[n]=1
    return dict

#dictionary for letter counts
letter_frequency={}
#read the normalize input text

#count letter frequencies for letter in text
if letter in letter_frequency:
    letter_frequency[letter]+= 1
else:
    letter_frequency[letter]=1

print(len(text))
#calculate entropy
length_sum = 0.0
for letter in letter_frequency:
    probability= float(letter_frequency[letter])/len(text)
    length_sum += probability * log2(probability)

#output
sys.stdout.write('Entropy: %f bits per chararacter\n' %(-length_sum))