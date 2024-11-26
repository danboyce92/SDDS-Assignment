# SDDS-Assignment 

*Daniel Boyce G-00438851*

## Description
This project is a CLI program that analyzes and modifies text input by replacing less common words with more widely used and easier to understand alternatives. 

## How to use


## Features


## Build Notes
The idea for this project. Similar to the last project in that it is a CLI program that takes an input and makes a modification, saves the output to a designated location.

Where to begin?

-Copy the User interface from my last project. ✔ <br/>
-Remove parts that aren't appropriate. ✔

What does this app actually need to do???  <br/>
It needs to point to a txt file. <br/>
It needs to go through every word in text file and replace them with the closest word in the Google 1000 list

Next steps
Isolate problems
Create a class that processes an embeddings file
This file will need to be able to process the embedding file and google 1000 file.
**Is there an opportunity for reuse here?**
- Have a component that splits up the words in a txt file and returns an array?
- This could be used in the Extractor for the embeddings file, the google 1000, and also the txt file to be modified. 

**Remember to make everything lowercase**
Create a Set that holds the google 1000. ✔
Create a Map that holds the embeddings for the google 1000 words.
Do i need to create the set if I'm just going to create the map afterwards?


## QUESTIONS?
Should the input be raw user input or a path to a txt file? YES
Should the user be allowed to set the filepath for the Embeddings file and or the Google file or will that be hardcoded and only allow user to assign output location and input text location?
If a word in the text file is not found in the embeddings list, should it just return what is in the text file? How is this error handled?




# Go through entire app sequentially, what are the steps necessary?

Runner will start user interface. <br/>
1 - Allow user to set the filepath for Input text file <br/>
2 - Allow user to set the filepath for Output result location <br/>
3 - Run program and make modification <br/> 
4 - Allow user to toggle between similarity algorithm A, B, C(Possibility) <br/>
5 - Allow user to edit the filepath of the embeddings file (Possibility) <br/>
6 - Allow user to edit the filepath of the common words file (Possibility) <br/>
0 - Exit <br/>

WHAT SET UP IS REQUIRED?? <br/>

Input text file provided. <br/>
Output file destination provided. <br/>


Create a wordEmbeddings Array (Just words) <br/>
Create an embeddings Array (Just embeddings) <br/>
Create a googleWords Array (Just words) <br/>
Create a googleEmbeddings (Just embeddings) <br/>
CAN I AVOID THE ABOVE IN ORDER TO MAKE THE APP MORE EFFICIENT? <br/>


Create a Map that holds all embedding words and embeddings. <br/>

Create a Map that holds all 1000 common words and their embedding. <br/>

**Can all steps above be done on interface start up?**

When the run button is clicked, the program will need to do the following : <br/>

Parse through the input file provided and push each individual word into an array. (Account for any additional chars like . , and lowercase everything) <br/>

(CONSIDERATION : How do I return things like . , and uppercase afterward?) <br/>

Iterate through array (through each word in given input). Create a second array that contains each word's embedding {THIS IS INEFFICIENT. Instead check if word exists in googleWordMap if so take the embedding value otherwise use arrays} <br/>

Now iterate through googleWordArray again. If word !exist in googleWordMap perform similarity algorithm on googleEmbeddingArray and choose most similar word to replace it. Each iteration should add a word to the result array <br/>

**WHAT DOES THE SIMILARITY ALGORITHM CONSIST OF?** 
Very similar to the last project, regarding the word that needs to be swapped. Take that words embedding, and compare it against all the embeddings in the googleEmbeddingsArray. Take the top pick and use the googleEmbeddingsArray index to find the associated word via googleWordArray

For now just print result array to output destination and worry about formatting when we get to this point. <br/>




Create the similarity check in isolation.

**SPANNER IN THE WORKS... NOT ALL GOOGLE_WORDS EXIST IN THE TOTALLIST**




 