								README
					
PROJECT NAME: Simple Document Profiling by Token Analysis
AUTHOR: Ming Zhang
DATE: 4/23/2015

(1) Get project source code: 
git clone https://github.com/mizhang118/TokenAnalysis.git

(2) Overview:
Document profiling can be used to figure the "similarity" among documents, and also can be
used to guess the "meanings", "authors" and other attributes of a unknown documents. The 
simple use case is for teachers to find similar source code from student's course project 
assignments in order to detect fraud. Some sophisticated use cases, such as push right 
advertisements to a person by profiling the web pages they view.

(3) Build and Kickoff the program:
mvn clean install
java ToenAnalysis 4 /User/mz/andromeda/andormeda-core/javamodule/main/src

first param defines token size for analysis, second param define the input file directory.
The program will pickup all files under the input directory and do pairwise analysis among
all files.

(4) Algorithm:
Transfer a document file into a string, define a token size (3 - 10), and count the appearance
times of each token in the document to get a map, such as:

TOKEN_1		3
TOKEN_2		1
TOKEN_3		2
TOKEN_4		8
....

Figure out the similarity of two documents by linear regression of the appears numbers of same 
token in the two documents. Use regression R value to represent the similarity of the two
documents.

(5) Software design:
- Source-sink data flow:
- Filter design pattern:
- Abstract template design pattern:

Partitions the data processing into multiple steps that have similar data processing procedure
with different processing algorithm. Keep each step simple and easily achievable. Data processing
procedure (skeleton) of all steps is done in the framework, and data algorithm of each step 
is done in each step.

Input directory --> a set of input files --> a set of strings --> token analysis
--> linear modeling --> report


