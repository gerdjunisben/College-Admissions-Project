For the accounts they are held in a binary search tree sorted by email string since each account has a unique email and 
I do not know how many accounts there will be. So searching a student has an efficiency of O(log(n)) as well 
as inserting a student. 

For the waiting list I used a heap (api is called priorityQueue) which has an efficiency of O(nlog(n)) when
getting queue position and O(log(n)) when inserting.Also since I do not know how many people there would be, an 
array would not be efficient.

For the dictionary I used a hash table since I know how many elements there will be and it has an efficiency of O(1) to check if something 
is there. It does take a little to add all 99171 elements to the array so I saved it to a file too.

The final data structure I used was a linked list for reading the file because I do not plan on traversing it any way but forward and
I don't know how many words there are going to be. So for checking typos,sentences and total words it has an effeciency of O(N) while 
for syllables it's O(N^2) since it needs to read each character in every word (nested loops).
