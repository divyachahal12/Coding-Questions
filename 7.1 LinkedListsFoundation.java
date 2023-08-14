/*
ARRAYS

Let's suppose we declare the following array:
int[] arr = new int[10];
Since 1 integer is 4 bytes, therefore, for 10 integers 40 bytes are needed.
We have a stack and a heap present in the memory.
Suppose the heap is filled at positions where there are green areas and in between those 20 bytes are free at places
If we want to make an arr of 40 bytes then it won't be possible to do so because an array is continuous and continuous memory spaces are not available in this heap.
Had the memory been continuos then we would have been able to construct our desired array since 40 bytes are available continuously in the heap here.
For this situation the array is formed in those 40 bytes as shown in the figure below and the stack would have stored the address of the first element of arr , say 4k.

Now, we'll see how the linked list is different from it.

LINKED LISTS

For a linked list, had the memory in the heap not been contiguous, then we would not have had any problem in forming it.
As you can see in the above figure, the list is "linked" despite that the memory is not continuous. 
Do you see those small squares with 2 compartments? They are called nodes.

There are 2 parts in a node: Data and Address of the next node.
You can see, each element stores the address of the next element. 
For example, the first node at the address 4k stores the address of the next element at 5k, the node at 5k stores the address of its next element 6k and so on. 
As there are no more elements after the last node , therefore the address part of it is left "null".
Also the stack stores the address of the first element of the linked list i.e. 4k.
Hence we conclude that a linked list is a non-continuous type of data structure. 
This means that a Linked List utilizes space when there is a fragmented memory.

Disadvantage of Linked List over Arrays in terms of memory :
Even though it is advantageous to use Linked Lists for space conservation in heap, 
we notice that since each node stores 2 values: data and address, therefore each node requires 4bytes+ 4 bytes=8 bytes of memory. 
The memory used in arrays for each element was only 4 bytes. 
Here, the space of linked lists is noticed to be more.
*/
public static class Node{
  int data;
  Node next;
}

public static class LinkedList{
  Node head;
  Node tail;
  int size;
}

//Add Last In Linked List
/*
The problem deals with adding a node to the end of a linked list. 
For this problem, we are already given a class with head, tail, and size as data members and we wish to add a node at the end. 
The other way to see this problem is that we need to add a node after the tail node. Keeping this in mind there can be two cases:

1. When tail != null

This case deals with a non-empty linked list.
Let us assume a Linked List [1 -> 2 -> 3 -> 4]. 
The given list has head pointing to the node with value 1 and tail pointing at the node with value 4 and the size of the linked list is 4. 
Now suppose we need to add a node 5 at the end of the linked list. 
Now as we know that every node stores the address of its next node in a singly linked list,
so it means that the node with value 1 stores the address of the node with value 2 and so on, 
which also concludes that the node with value 4 stores null as there is no node ahead of it. 
Therefore, all we need to do is to update the address of the tail (i.e. the node with value 4) to point to a new node 
(i.e. the node with value 5) which makes our linked list [1 -> 2 -> 3 -> 4 -> 5]. 
Now all that is left to do is to update the tail pointer to the node with value 5 and increment the size of the list.

2. When tail == null

This is the case of an empty linked list.
This implies that both the head and the tail pointer points to null and the size of our linked list is 0. 
So here we just need to add our new node to the list which will act as both head and tail 
(as a list of size 1 has only one node with head and tail both pointing to that node) and we need to increment the size of the list.

Time Complexity: O(1)
The time complexity for this function is constant,
since all we have to do is to update the tail pointer and increment the size of the list which has nothing to do with the number of elements present in the list.

Had it been the case where we have not been provided with the tail pointer,then we would have to traverse the entire list to reach the end node of the list,
which would take a time complexity proportional to the length of the list and then we would insert a node and increment the size, 
so the total time complexity would have been O(n) in that case, where n is the length of the linked list.

Space Complexity: O(1)
As we only take a space to create a new node which is also independent of the length of the linked list.
*/
void addLast(int val) {
      Node temp = new Node();
      temp.data = val;
      temp.next = null;
      
      if(size == 0){        //for empty linked list
          head = tail = temp;
      }else{                //when already something is present in ll
          tail.next = temp;//prev tail's next would be new node formed
          tail = temp;     //and tail would be assigned the new node formed
      }
      
      size++;
    }
  }

//display a LL
/*
The first function expects a return value equal to the size of the list. 
As we already know that we have a size data member so here we just need to return it.
The second function expects an output that displays the linked list. 
To achieve this, we need to traverse the entire list to get every element and print its value, 
for that we can take a temporary node starting from the head and incrementing itself until it becomes null, and for every node, we print the node data.

Time Complexity
The time complexity for the size() function is constant (or O(1)) since all we have to do is to return the data member size.
The time complexity for the display() function is O(n) where n is the length of the list, as here we visit each node of the list which is dependent on the length of the list.

Space Complexity: O(1)
The space complexity for both the functions is constant.
*/
public void display(){
      Node temp = head;
      while(temp != null){
          System.out.print(temp.data+" ");
          temp = temp.next;
      }
      System.out.println();
      
 }

//Remove First in LL
/*
You are required to complete the body of removeFirst function removeFirst - 
This function is required to remove the first element from Linked List. 
Also, if there is only one element, this should set head and tail to null. 
If there are no elements, this should print "List is empty".

To understand the problem better we need to understand the following cases:

1. When size > 1
This is the case when our list is not empty. 
Say for example our list is 1 -> 2 - > 3 -> 4 -> 5 with head at node with value 1,tail at node with value 5 and size of list as 5.
Now when we called our removeFirst() function it gives us an updated list 
which looks like 2 -> 3 -> 4 -> 5 where head points at the node with value 2, tail points at the node with value 5, and size of the list become 4. 
Now to achieve this we need to do two things, 
firstly to update the head to its next node and 
then decrement the size of the list.

2. When size == 0
This is the case when our list is empty. 
As we can not remove an element from an empty list so in this case we just need to prompt an error message and return without changing the data members of the linked list class.

3. When size == 1
This is the case when our list has only one node. 
Removing this node will result in an empty list. 
As we know that for an empty list both head and tail point to null and the size is equal to zero, so we just need to update the data members in this case.

Why Case 1 and Case 3 are different?
They are different since for Case 3 we need to update the tail pointer too as deleting a list with one node results in an empty list
whereas in Case 1 deleting the first node does not affect the last node, hence different.

Time Complexity: O(1)
The time complexity for the function is constant as only shifting of the head pointer is involved in the operation which is independent of the length of the linked list.

Space Complexity: O(1)
The space complexity for the function is constant as we have not used any extra space for our algorithm.
*/
public void removeFirst(){
        if(size == 0) {
          System.out.println("List is empty");
        }
        else if(size == 1){
            head = tail = null;
            size = 0;
        }else{
            head = head.next;
            size--;
        }
      
    }
  }

//get value in LL
/*
Get First
The problem deals with retrieving the first node data from the linked list. The problem can be in one of the two cases:

1. Size != 0
This implies that our list is not empty so in that case we simply need to return the data present at our head node.

2. Size == 0
This is the case when our list is empty which implies that there are no nodes in our list, in that case, we can not return any relevant data so we prompt an error message displaying "List is empty" and return -1 as a default value.

Time Complexity: O(1)
The time complexity for the function is constant as we only need to return the data present at our head node.

Space Complexity: O(1)
The space complexity for the function is constant as we have not used any extra space for our algorithm.
*/
public int getFirst() {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      } else {
        return head.data;
      }
}

/*
Get Last
The problem deals with retrieving the last node data from the linked list. The problem can be in one of the two cases:

1. Size != 0
This implies that our list is not empty so in that case we simply need to return the data present at our tail node.

2. Size == 0
This is the case when our list is empty which implies that there are no nodes in our list, 
in that case, we can not return any relevant data so we prompt an error message displaying "List is empty" and return -1 as a default value.

Time Complexity: O(1)
The time complexity for the function is constant as we only need to return the data present at our tail node.

Space Complexity: O(1)
The space complexity for the function is constant as we have not used any extra space for our algorithm.
*/

    public int getLast() {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      } else {
        return tail.data;
      }
    }

/*
Get At
The problem deals with retrieving the node data at the desired index from the linked list. The problem can be in one of the cases:

1. Size != 0 (General Case)
This implies that our list is not empty so in that case, we need to traverse to the desired index and then return the value. 
To achieve this we will take a temporary variable initialized with head pointer and then iterate it to reach the desired node and then return with the data at the current node.

2. Size == 0
This is the case when our list is empty which implies that there are no nodes in our list, 
in that case, we can not return any relevant data so we prompt an error message displaying "List is empty" and return -1 as a default value.

3. If index < 0 || index >= size
This is the case when our input parameter is out of range as our index parameter can have values from 0 <= index < size to satisfy 0 based indexing in programming. 
So in this case we will prompt an error message "Invalid Arguments." and return 1 as the default value.

Time Complexity: O(n)
The time complexity for the function is linear as we are traversing over the linked list to reach the desired index which is dependent on the length of the linked list.

Space Complexity: O(1)
The space complexity for the function is constant as we have only used a temporary variable for our algorithm.
*/
    public int getAt(int idx) {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      }else if(idx < 0 || idx >= size){
          System.out.println("Invalid arguments");
          return -1;
      } 
      else {
        Node temp = head;
        for (int i = 0; i < idx; i++) {
          temp = temp.next;
        }
        //or use while loop--|
//         while(idx > 0){
//             temp = temp.next;
//             idx--;
//         }
        return temp.data;
      }
    }

  //Add First in LL
/*
The problem can be categorized into two different cases:

1. Size != 0
This is the case when our list is not empty. 
So we need to create a new node and store the input data, 
now the next thing to keep in mind is that this new node will now act as the head of the linked list so the head should be updated, 
also the new node should now point to the next node which was the previous head and lastly, the size of the list should be incremented.

2. Size == 0
This is the case when our list is empty.
For that adding a new node to the list will make the length of the list as 1. 
Now as we already know that for an empty list head = tail = null and size = 0, 
so after adding the new node both head and tail should point to our new node and size should also be incremented.

Time Complexity: O(1)
The time complexity for the function is constant as the only operation include here is updation of data members.

Space Complexity: O(1)
The space complexity for the function is constant as we have only created a new node variable that takes constant space.
*/
   public void addFirst(int val) {

      Node first = new Node();
      first.data = val;
      first.next = head;
      head = first;

      if(size == 0){
         tail = first;
      }
      size++;
   }

//Add at Index in LL
/*
The problem can be categorized into different cases:

1. Index < 0 || Index > Size
This is the case when our input parameter is out of range as our index parameter can have values from 0 <= index <= size
(here, index == size is valid as we can add an element at the index == size which is equivalent to adding an element at the end of the list) 
to satisfy 0 based indexing in programming. 
So in this case we will prompt an error message "Invalid Arguments".

2. Index == 0
This is the case when we wish to add an element at the beginning of the linked list
which can also be referred to as the addFirst() function which we discussed in the previous problem, 
so here we simply call that function and return.

3. Index == Size
This is the case when we wish to add a node at the end of the linked list
which can also be referred to as the addLast() function which we discussed in the previous problem, 
so here we simply call that function and return.

4. 0 <  Index< Size
This is the general case for this problem as in this case the new node acquires the position in between the nodes of our linked list.
As we wish to add the node at a particular index so we need to traverse the list until we reach the desired index and
then we will have to insert the node which can be done by following these simple steps:
a. Create a new node and update its data members (say newNode)
b. Create a temporary reference pointing at the head (say curr)
c. Traverse from head to the node before the desired index
d. Save the next address in a variable (say saveNode)
e. Update the next data member of curr to newNode
f. Update the next data member of newNode to saveNode
g. Return

Time Complexity: O(n)
The time complexity for the function is linear as we need to traverse the list to reach the desired location and then perform insertion, 
which is dependent on the length of the linked list.

Space Complexity: O(1)
The space complexity for the function is constant as we have only created reference variables that take constant space.
*/
public void addAt(int idx, int val) {
  if (idx < 0 || idx > size) {
    System.out.println("Invalid arguments");

  } else if (idx == 0) {
    Node temp = new Node();
      temp.data = val;
      temp.next = head;
      head = temp;
      
      if(size == 0){
        tail = temp;
      }

      size++;
    
  } else if (idx == size) {
    tail.next = nodeToadd;
    tail = nodeToadd;
  } else {
    Node node = new Node();
    node.data = val;

    Node temp = head;
    for (int i = 0; i < idx - 1; i++) {
      temp = temp.next;
    }
    node.next = temp.next;

    temp.next = node;
    size++;
  }
}

//Remove last in LL
/*
1. When size == 0
This is the case when our list is empty. 
As we can not remove an element from an empty list
so in this case we just need to prompt an error message and return without changing the data members of the linked list class.

2. When size == 1
This is the case when our list has only one node. 
Removing this node will result in an empty list. 
As we know that for an empty list both head and tail points to null and the size is equal to zero, 
so we just need to update the data members in this case.

3. When size > 1
This is the general case of our problem. 
Earlier in our removeFirst() problem we just update our head with head.next and we get our result, 
but in a singly linked list there is no way to do something like this, tail = tail.prev so we need to go the traditional way, 
i.e. by traversing the list up to the node before tail node and then set its next data member to null and 
we also need to decrement the size of our list.

Time Complexity: O(n)
The time complexity for the function is linear as the traversal of the linked list is involved.

Space Complexity: O(1)
The space complexity for the function is constant as we have only used referenced variables in our algorithm.
*/
public void removeLast(){
      if(size == 0){
          System.out.println("List is empty");
          return;
      }else if(size == 1){
          head = tail = null;
      }else{
          Node temp = head;
          for(int i = 0; i <size-2; i++){
            temp = temp.next;
          }
          tail = temp;
          temp.next = null;
      }
      size--;
 }

//Remove at Index in LL
/*
The problem can be categorized into the following cases:

1. Index < 0 || Index >= Size
This is the case when the input parameter is out of range, 
as to follow 0 based indexing the index parameter should be in the range of )<= Index < Size which is clearly violated in this case. 
So, here we would simply prompt an error message and return.

2. Index == 0
This is the case of removing the first element from the list which we have already implemented in previous lessons,
hence here we could just call our removeFirst()  function and return.

3. Index == Size - 1
This is the case of removing the last element from the list which we have already implemented in previous lessons,
hence here we could just call our removeLast()  function and return.

4. 0 < Index < Size -1
This is the general case of our problem where the element to be removed is present in between the elements of the list.
To remove an element from the list, we need the node previous to it and the node next to the node to be deleted. 
So here we can traverse the list till we reach the previous node and
we can update the next data member of the previous node by pointing it to the next node of its next node 
i.e., prevNode.next = prevNode.next.next(which is the same ascurrNode.nextwherecurrNodeis the node to be deleted).
After updating the list all we need to do is to decrement the size of our list and then return.

Time Complexity: O(n)
The time complexity for the function is linear as the traversal of the linked list is involved.
Space Complexity: O(1)
The space complexity for the function is constant as we have only used referenced variables in our algorithm.
*/
public void removeAt(int idx) {
  if (idx < 0 || idx >= size) {
    System.out.println("Invalid arguments");

  } else if (idx == 0) {
    removeFirst();
  } else if (idx == size - 1) {
    removeLast();
  } else {
    Node temp = head;
    for (int i = 0; i < idx - 1; i++) {
      temp = temp.next;
    }

    temp.next = temp.next.next;
    size--;
  }
}

public void removeFirst(){
        if(size == 0) {
          System.out.println("List is empty");
        }
        else if(size == 1){
            head = tail = null;
            size = 0;
        }else{
            head = head.next;
            size--;
        }
      
    }
  }

public void removeLast(){
      if(size == 0){
          System.out.println("List is empty");
          return;
      }else if(size == 1){
          head = tail = null;
      }else{
          Node temp = head;
          for(int i = 0; i <size-2; i++){
            temp = temp.next;
          }
          tail = temp;
          temp.next = null;
      }
      size--;
 }

//Reverse a LL (data iterative)
/*
The problem deals with reversing the given linked list, here we will use a data iterative approach, 
i.e. instead of reversing the list, we will modify the elements of the list to get our result. 

When we need to reverse a list, deep down what needed to be done is that the last node should be now the first node and the first node should now be the last node. 
Similarly, the second node and the second last node should also be interchanged, and so on. 
Now we will implement this approach on linked list data. 
For that, we will keep a left index (say li) at 0 and a right index (say ri) at size - 1. 
What we wish to do is to swap the elements at li and ri and update li and ri upto the point when li becomes equal to ri.

Points to note here
1. If we were to run the loop till end instead of till middle then at li == ri we would get our reverse list 
but moving forward to end we would again have reversed our list and then we would have gotten the original unreversed list as our result.
2. At li == ri we break the loop as swapping the data at same index changes nothing.
3. Incrementing li means moving from the first node to the second then to the third and so on.
4. Decrementing ri means moving from the last node to second last then to third last and so on.
5. This incrementation and decrementation ensure that we swap correct pairs like the first node with last, the second node with second last, and so on.
6. The actual time complexity is O(n/2) which is effectively equal to O(n).

Time Complexity: O(n)
The time complexity for the function is linear as we need to traverse the entire list to swap every element in the list.
Space Complexity: O(1)
The space complexity for the function is constant as we have only used referenced variables in our algorithm.
*/
private Node getNodeAt(int idx) {
  Node temp = head;

  for (int i = 0; i < idx; i++) {
    temp = temp.next;
  }
  return temp;
}
public void reverseDI() {
  int li = 0;
  int ri = size - 1;
  while (li < ri) {
    Node left = getNodeAt(li);
    Node right = getNodeAt(ri);
    int temp = left.data;
    left.data = right.data;
    right.data = temp;
    li++;
    ri--;
  }
}

/*******************IMPORTANT*********************/
//Reverse LL(Pointer Iterative); do DRY run to understand
public void reversePI(){
  /*
The problem deals with reversing the given linked list, here we will use a pointer iterative approach,
i.e. we will modify our list to reverse it and we would not change the node data. 
There can be two ways to reverse a linked list:
The difference in both these methods is that in the data iterative method we are not changing the linked list orientation,
we were only replicating the output elements whereas in the pointer iterative approach we aim to redesign our list to reverse it.

The idea behind the pointer iterative is to simply point all nodes to its previous nodes.
This can be implemented by having two pointers, 
one for storing the previous node and the other for storing the next node, 
so that for every node the current node can point to the previous node and then can move forward to the next node. 
The next node is needed to prevent data loss due to the reassignment of the next data member of the current node.

Time Complexity: O(n)
The time complexity for the function is linear as we need to traverse the entire list to change its orientation (or next data member re-allocation).
Space Complexity: O(1)
The space complexity for the function is constant as we have only used referenced variables in our algorithm.
  */
        if(size == 0){
            return;
        }
      Node prev = null;
      Node curr = head;
      while(curr != null){
          Node next = curr.next;
          curr.next = prev;
          prev = curr;
          curr = next;
          
      }
      Node temp = head;
      head = tail;
      tail = temp;
 }

//Linked List To Stack Adapter
/*As data members, you've a linkedlist available in the class.
3. Here is the list of functions that you are supposed to complete
    3.1. push -> Should accept new data in LIFO manner
    3.2. pop -> Should remove and return data in LIFO manner. If not 
     available, print "Stack underflow" and return -1.
    3.3. top -> Should return data in LIFO manner. If not available, print 
    "Stack underflow" and return -1.
    3.4. size -> Should return the number of elements available in the 
    stack

A stack is a data structure just like a list but with restricted access. 
Say for example we have a pile of books, 
whenever we wish to add a book to the pile we do it at the top of the pile and 
whenever we wish to remove a book from the pile, removing it from the top seems sensible.
Here pile can be referred to as a stack and books can be referred to as elements.
It is restricted in the sense that insertion (of elements) or deletion (of elements) can take place only at the top of the stack.
Also, users can not access any other data (or node) except the topmost element. 
This means that stack has a LIFO property (Last InFirst Out).

The problem here deals with modifying a linked list to create a stack.
Our task is to implement LLToStackAdapter class, so for that first,
we need to have a list to store data, so our data member in this class would be a linked list.
*/
public static class LLToStackAdapter {
    LinkedList<Integer> list;
  
/* As we know that every class has a constructor, here we will write our constructor which will initialize our list.*/
    public LLToStackAdapter() {
      list = new LinkedList<>();
    }
/* size() function which returns the current size of our stack, for that we can simply use the in-built size() function of the LinkedList class and return it.*/  
    int size() {
      return list.size();
    }
/*
push() function which takes a single argument valwhich contains the element that needs to be added at the top of the stack. 
For that, we simply add the element at the beginning of our list by using the in-build addFirst() function.
*/
    void push(int val) {
      list.addFirst(val);
    }
/*
 pop() function whose task is to remove the element present at the top of the stack.
 In our class we have considered the head node as the top of the stack, 
 now we simply need to remove the node at the head of the linked list and 
 we also need to handle the condition when our list is empty as we can not retrieve any information from an empty list.
 Here updation of head and size are taken care of by the in-built LinkedList class we just need to use the functions to get relevant results.
*/
    int pop() {
      if(size() ==0){
          System.out.println("Stack underflow");
          return -1;
      }
      return list.removeFirst();
    }
/*
 top() function whose task is to return the element present at the top of the stack. 
 In our class we have considered head as the top of the stack,
 now we simply need to return the data present at the head of the linked list and 
 we also need to handle the condition when our list is empty as we can not retrieve any information from an empty list.
*/
    int top() {
        if(size() ==0){
          System.out.println("Stack underflow");
          return -1;
      }
      return list.getFirst();
    }
  }
/* Time Complexity: O(1)
The time complexity for every function in our class is linear as the operations involved here are only on the head node and not on the entire list.*/

//Linked List To Queue Adapter
/*
 As data members, you've a linkedlist available in the class.
3. Here is the list of functions that you are supposed to complete
     3.1. add -> Should accept new data in FIFO manner
     3.2. remove -> Should remove and return data in FIFO manner. If not available, 
     print "Queue underflow" and return -1.
     3.3. peek -> Should return data in FIFO manner. If not available, print "Queue 
     underflow" and return -1.
     3.4. size -> Should return the number of elements available in the queue

A queue is a data structure just like a list but with restricted access. 
Say for example we have a queue of customers at a shop, here the first person who comes in gets out first and the later ones follow. 
This representation is similar to the queue data structure wherein an element is inserted at the end of the list and removed from the beginning. 
It is restricted in the sense that insertion (of elements) or deletion (of elements) can take place only at the specific positions. 
Also, users can not access any other elements (or nodes) except the frontmost element.
This means that the queue has a FIFO property (FirstIn First Out).

The problem here deals with modifying a linked list to create a queue.
Our task is to implement LLToQueueAdapter class, so for that first, 
we need to have a list to store data, so our data member in this class would be a linked list.
*/
public static class LLToQueueAdapter {
    LinkedList<Integer> list;

    public LLToQueueAdapter() {
      list = new LinkedList<>();
    }
/* size() function which returns the current size of our queue, for that we can simply use the in-built size() function of the LinkedList class and return it.*/
    int size() {
      return list.size();
    }
/* add() function which takes a single argument valwhich contains the element that needs to be added at the end of the queue. 
   For that, we simply add the element at the end of our list by using the in-build addLast() function.*/
    void add(int val) {
      list.addLast(val);
    }
/*
remove() function whose task is to remove the element present at the front of the queue. 
In our class we have considered the head node as the front of the queue, 
now we simply need to remove the node at the head of the linked list and 
we also need to handle the condition when our list is empty as we can not retrieve any information from an empty list.
*/
    int remove() {
      if(size() == 0){
          System.out.println("Queue underflow");
          return -1;
      }
      return list.removeFirst();
    }
/*
The next function is the peek() function whose task is to return the element present at the front of the queue. 
We simply need to return the data present at the head of the linked list and
we also need to handle the condition when our list is empty as we can not retrieve any information from an empty list.
*/
    int peek() {
      if(size() == 0){
          System.out.println("Queue underflow");
          return -1;
      }
      return list.getFirst();
    }
  }
/*
Time Complexity: O(1)
The time complexity for every function in our class is linear as
the operations involved here are insertion at the tail, removal at head, size incrementation or decrementation which are all constant operations.
*/

//Kth Node From End Of Linked List
/*
You are required to complete the body of kthFromLast function. 
The function should be an iterative function and should return the kth node from end of linked list. 
Also, make sure to not use size data member directly or indirectly (by calculating size via making a traversal). 
k is a 0-based index. Assume that valid values of k will be passed.




*/
public int kthFromLast(int k) {
  Node fast = head;//first pointer
  Node slow = head;//second pointer
  
  for (int i = 0; i < k; i++) {
    fast = fast.next;
  }
  while (fast != tail) {
    slow = slow.next;
    fast = fast.next;
  }
  return slow.data;
}

//Mid Of Linked List
/*
You are required to complete the body of mid function. 
The function should be an iterative function and should return the mid of linked list.
Also, make sure to not use size data member directly or indirectly (by calculating size via making a traversal).
In linked list of odd size, mid is unambigous. In linked list of even size, consider end of first half as mid.
*/
public int mid() {
      Node slow = head;
      Node fast = head;
      while(fast.next != null && fast.next.next != null){
          slow = slow.next;
          fast = fast.next.next;
      }
      return slow.data;
}


//Merge Two Sorted Linked Lists
public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
            Node one = l1.head;
            Node two = l2.head;
            
            LinkedList ans = new LinkedList();
            while(one != null && two != null){
                if(one.data < two.data){
                    ans.addLast(one.data);
                    one = one.next;
                }else{
                    ans.addLast(two.data);
                    two = two.next;
                }
            }
            while(one != null){
                ans.addLast(one.data);
                one = one.next;
            }
            
            while(two != null){
                ans.addLast(two.data);
                two = two.next;
            }
            
            return ans;
  }


//To Merge Sort A Linked List
public static LinkedList mergeSort(Node head, Node tail){
      if(head == tail){
        LinkedList base = new LinkedList();
        base.addLast(head.data);
        return base;
      }
        
      Node mid = midNode(head, tail);
      LinkedList firstSortedHalf = mergeSort(head, mid);
      LinkedList secondSortedHalf = mergeSort(mid.next, tail);
      LinkedList completeList = mergeTwoSortedLists(firstSortedHalf, secondSortedHalf);
      
      return completeList;
}

public static Node midNode(Node head, Node tail){
        Node fast = head;
        Node slow = head;
        
        while(fast.next != tail && fast != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
}

public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
      LinkedList ml = new LinkedList();

      Node one = l1.head;
      Node two = l2.head;
      while (one != null && two != null) {
        if (one.data < two.data) {
          ml.addLast(one.data);
          one = one.next;
        } else {
          ml.addLast(two.data);
          two = two.next;
        }
      }

      while (one != null) {
        ml.addLast(one.data);
        one = one.next;
      }

      while (two != null) {
        ml.addLast(two.data);
        two = two.next;
      }

      return ml;
}

//Remove Duplicates in a Sorted List
/*
The problem deals with removing duplicate elements in a sorted linked list. 
For example, we have a list 1 -> 2 -> 2 -> 2 -> 3 -> 4 -> 5 -> 5, then the output list should be 1 -> 2 -> 3 -> 4 -> 5.

The idea here is to check for every element whether it is already present in the list or not and then making a selection for the same. 
As here we know that the list is sorted so checking the lastmost element can make us ensure that whether the list contains the current element or not.
To achieve this we are going to create our new list, where we will store our output list. 
Now we will run a loop up to the point when our list becomes empty and for every iteration, we will select the first node and remove it from the list.
Now in our output list, we would check whether the element is already present in the list or not, 
  if present then we would discard it 
  else we would add that element to the list.
*/
public void removeDuplicates(){
      LinkedList res = new LinkedList();
      while(this.size() != 0){
          int val = this.getFirst();//this.head.data;
          this.removeFirst();
          if(res.size() ==0 || val != res.tail.data){
              res.addLast(val);
          } 
      }
      this.head = res.head;
      this.tail = res.tail;
      this.size = res.size;
}


//Odd Even LL
/*
The problem deals with modifying the input list such that all odd values follow all even values. 
For example the list is 1 -> 2 -> 3 -> 4 -> 5 -> 7 -> 6 -> 9 -> 8 so our 
output should be 1 -> 3 -> 5 -> 7 -> 9 -> 2 -> 4 -> 6 -> 8.

To achieve this we will be taking two lists, one for storing odd values and the other for storing even values. 
Now we iterate over the input list and segregate every element by checking whether it is an odd or even element. 
Later on, we combine both the list by adding an even list at the tail of the odd list and then update the linked list data member by this resultant list.
After segregation, we have taken three cases:

1. odd.size() > 0 and even.size() > 0
2. odd.size() > 0 and even.size() == 0
3. odd.size() == 0 and even.size() > 0
*/
    public void oddEven(){
      LinkedList odd = new LinkedList();
      LinkedList even = new LinkedList();
      
      while(this.size() != 0 ){
          int val = this.getFirst();
          this.removeFirst();
          
          if(val % 2 == 0){//even
            even.addLast(val);  
          }else{
              odd.addLast(val);
          }
          
      }
      if(odd.size() > 0 && even.size() > 0){
          odd.tail.next = even.head;
          this.head = odd.head;
          this.tail = even.tail;
          this.size = odd.size + even.size;
      }else if(odd.size > 0){
          this.head = odd.head;
          this.tail = odd.tail;
          this.size = odd.size;
      }else if(even.size > 0){
          this.head = even.head;
          this.tail = even.tail;
          this.size = even.size;
      }
    }


//K Reverse In Linked List
/*
The problem deals with reversing an input list in the groups of given size. 
For example, suppose we have a list 1 ->2 ->3 ->4 ->5 ->6 ->7 ->8 ->9 ->10 ->11 and we have value of k = 3 then our output list should be reversed in group of size 3. 
Hence the output list will look like:

3 ->2 ->1 ->6 ->5 ->4 ->9 ->8 ->7 ->10 ->11.

A vague idea about approaching this problem is to select every group of size k and reverse it until we reach the end, and somehow coagulate the resulting reversed groups to get our result.

Now to achieve this, we are going to create two lists, one to store the final result and the other to store the temporary reversed groups. 
Our operations continues until we traverse the entire list or here until the size of the input list is greater than zero.

Now there can be two cases where our selected group can lie:

1. this.size() >= k

This is the case when the remaining list is of length greater than or equal to the group size. 
This implies that in this case, we need to select a group of size k and reverse it. 
This can be achieved by selecting every element from the input list (of the selected group) and remove it from the input list 
and add it to the head of the temporary list, doing this for k times will result in a group of size k reversed and stored in a temporary list, 
say, we have,

Input List: 1 -> 2 -> 3 -> 4 -> 5

then after the above-mentioned operations, we would have:

Remaining Input List: 4 -> 5

Temporary List: 3  -> 2 -> 1

2. this.size() < k

This is the case when the remaining list is of length is less than the given group size. 
In this case, we need not reverse the list, rather we have to simply add this list directly to our output list and get our final result.

All the groups are firstly stored in a temporary list and then are added to the output list where we can have two cases:

1. Output List is Empty

This is the case when we have operated on our first group. 
Here we simply need to have the temporary list as our output list as this group will be the starting elements of our result

2. Output List is not Empty

This is the latter case when we already have elements in our output list. 
In this case, we need to add elements of the temporary list at the end of the output list and later update the tail and size of the output list.

Lastly, we need to update the output list to the linked list data member.
*/
public void kReverse(int k) {
  LinkedList prev = null;

  while (this.size > 0) {
    LinkedList curr = new LinkedList();
    if (this.size >= k) {
      for (int i = 0; i < k; i++) {
        int val = this.getFirst();
        this.removeFirst();
        curr.addFirst(val);
      }
    } else {
      int originalSize = this.size;
      for (int i = 0; i < originalSize; i++) {
        int val = this.getFirst();
        this.removeFirst();
        curr.addLast(val);
      }
    }
    if (prev == null) {
      prev = curr;
    } else {
      prev.tail.next = curr.head;
      prev.tail = curr.tail;
      prev.size += curr.size;
    }
  }
  this.head = prev.head;
  this.tail = prev.tail;
  this.size = prev.size;
}

//Display Reverse (recursive) - Linked List
/*
The problem deals with displaying the reversed linked list, recursively this time. In earlier problems, 
we developed an iterative solution but here we will come up with a recursive approach. 
The idea is simple where we just need to traverse the list till the end using recursion and after we hit the base case 
and start returning the function calls in recursion stack, then at that point we will print the corresponding element. 
This is similar to the print decreasing problem we discussed in recursion level 1.
*/
private void displayReverseHelper(Node node) {
  if (node == null) {
    return;

  }
  displayReverseHelper(node.next);
  System.out.print(node.data + " ");
}
public void displayReverse() {
  displayReverseHelper(head);
  System.out.println();
}


//Reverse Linked List (pointer - Recursive)
/*
The problem deals with displaying the reversed linked list recursively with the help of pointers, 
in other words, we need to direct the node pointers to develop the reversed list.

The only way to reverse the list using pointers is to point the node next to the current node to the current node. 
Here we will also use this approach but recursively. For that, we traverse our list to the end, and then for every function return, 
we point the next node to the current node. 
Recursion here ensures that we do not lose any data and our data remains unchanged.

To sum, all we need to do here is to apply recursion to traverse the list, 
and while returning ensure that we point the next node to the current node. 
Now after this, the head node needs to be tackled separately as there is no node behind it where it would point so we need to point it to null. 
Now all that is left is to update the data member linked list with the reversed list.
*/
private void reversePRHelper(Node node){
      if(node == tail){
          return;
      }
      reversePRHelper(node.next);
      
      if(node == tail){
          //do nothing
      }else{
          node.next.next = node;//(node.next).next = node
      }
}

public void reversePR(){
      reversePRHelper(head);
      head.next = null;
      Node temp = head;
      head = tail;
      tail = temp;
}


//is LL a Palindrome
/*
The problem deals with checking whether the given list is a palindrome or not. 
A list is a palindrome when there is no difference in iterating the list either from start to end or vice-versa.

To check for the palindrome, we need to compare the first node with the last node, similarly, the second node with the second last node, and so on. 
We can achieve this with the help of recursion by traversing the list till we reach the base case, this ensures that we are at the end of the linked list. 
Now we keep a global variable which points at the head. 
Comparing global variable and last node, 
  if true, then we update the global variable to next node, and returning from function ensures that we go back to the previous node, 
  and if false then irrespective of future comparisons the list can not be palindromic hence non-palindromic.
*/
    Node left;//pointer from left, declared globally

    public boolean IsPalindromeHelper(Node right) {//pointer from right
        if(right == null){
            return true;
        }
        boolean res = IsPalindromeHelper(right.next);
            if(res == false){
                return false;
            }else if(left.data != right.data){
                return false;
            }else{
                left = left.next;
                return true;
            }
    }
    
    public boolean IsPalindrome() {
        left = head;
        return IsPalindromeHelper(head);
      
    }


//Fold a LL
/*
The problem deals with rearranging the linked list or folding the list. 
Suppose we have a list of length n indexed from 0 to (n-1) then, 
our output list should be of the format: 0th Index -> (n-1)th Index -> 1st Index -> (n-2)th Index -> 2nd Index -> (n-3)th Index ... and so on.

Say for example a list 1->2->3->4->5 
will fold as 1->5->2->4->3 
and a list with even length 
1->2->3->4->5->6 
will fold as 1->6->2->5->3->4.

To solve this problem, we can make use of recursion. 
Here we will keep a global pointer that will point to the tail of our output resultant list. 
Now during back - recursion (or function return from recursion stack) we will do the following operations:

1. (index+1)> size/2

This is the case when we need to fold the list. 
This case implies that the node is at the right half of the list and hence needs to be rearranged. 
So for that, we will add it to the tail of the output list and update its next pointer.

2. (index+1) == size/2

This is the case for the last element in the output list so for that we simply need to add this element and set its next pointer to null.

3. (index+1)< size/2

This is the case when our element was on the left half, these are the elements that are already taken care of so we need not consider them and return.
*/

    Node left;
    private void foldHelper(Node right, int floor) {
      if(right == null){
          return;
      }
      foldHelper(right.next, floor+1);
      if(floor > size/2){
        Node temp = left.next;
        left.next = right;
        right.next = temp;
        left = temp;//left.next.next
      }else if(floor == size/2){
          tail = right;
          tail.next = null;
      }
    }
    public void fold() {
        left = head;
      foldHelper(head, 0);
    }


//Add two LL
/*
The problem here deals with adding two linked list wherein we are not allowed the following operations:

1. We are not allowed convert the list to integers

2. We are not allowed to convert the list to arrays

3. We are not allowed to reverse the list

To solve this problem we will use a recursive approach. 
The idea here is that we need to add the list 
but as we know that addition is only possible when traversing from the end like adding firstly the unit place elements 
and then carry forwarding to the next place and so on. 
This generalized process here we will try to implement using recursion as we are not allowed to reverse the list.

Here we will be passing the current element and its place as parameters to the recursive function 
and we will make recursive calls till we hit the base case. Our recursive function is built to return the carry forward number if any. 
So for the base case when we have no elements after the unit place (which is true) then we return 0. 
Now for the unit place, we have supposed both the numbers so we add the unit places with the carry number 
and then add the resultant number at the head of the global resultant linked list. 
Adding it to the head always ensure that firstly unit place is placed at head later on the tenth place is added a
nd then hundredth place and so on this makes our resultant list to be in order and hence no need to reverse the obtained result.

To summarize we are traversing over the lists until we hit the base case, 
thereafter when we are popping out from the recursive stack we are entering the result at its correct place 
and returning the carry value. Also, we are keeping a track of lists with different digits to avoid miscalculation, 
as in that case when one element is not long enough to avoid error we are not considering it (this explains the if conditions in the code).
*/
public static int addLLHelper(Node one, int placeValue1, Node two, int placeValue2, LinkedList res) {
      if(one == null && two == null){
          return 0;
      }
      int data = 0;
      
      if(placeValue1 > placeValue2){
          int oldCarry = addLLHelper(one.next, placeValue1 -1, two, placeValue2, res);
          data = one.data + oldCarry;
          
      }else if(placeValue1 < placeValue2){
          int oldCarry = addLLHelper(one, placeValue1, two.next, placeValue2-1, res);
          data = two.data + oldCarry;
      }else{//placeValue1 == placeValue2
          int oldCarry = addLLHelper(one.next, placeValue1-1, two.next, placeValue2-1, res);
          data = one.data +two.data + oldCarry;
      }
      
      int newData = data % 10;
      res.addFirst(newData);
      int newCarry = data / 10;
      return newCarry;
    }
    
    public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
      LinkedList res = new LinkedList();
      int oldCarry = addLLHelper(one.head, one.size, two.head, two.size, res);
      if(oldCarry>0){
          res.addFirst(oldCarry);
      }
      return res;
    }


//Intersection Point(Node) of LL
/*
The problem deals with finding the intersection point in a Y - shaped linked list. For example, given the linked list:

We have head1 as a -> b -> c -> d -> e -> f having a length of 6 and head2 as g -> d -> e -> f having a length of 4. 
Here we can see that the intersection point is a node with value d. 
To get this using code we will build our logic which goes like this:

1. head1.size > head2.size

This is the case when the distance of head1 from the intersection point is greater than that of head2, 
so to compensate we will traverse head1 to the point where the distance for both pointers becomes equal.

2. head1.size < head2.size

This is the case when the distance of head2 from the intersection point is greater than that of head1, 
so to compensate we will traverse head2 to the point where the distance for both pointers becomes equal.

When we have both pointers equidistance from the intersection point 
then we will traverse both lists at the same pace until we reach a common node and the first common node will be our intersection point.

So for the above list, firstly we would traverse list head1 until head1 points at node c.
Now we will move both pointers to the next node and hence we reach a common node i.e. in this case node, 
hence node d is our resultant intersection point.
*/
    public static int findIntersection(LinkedList one, LinkedList two){
      Node t1 = one.head;
      Node t2 = two.head;
      
      int diff = Math.abs(one.size - two.size);
      if(one.size > two.size){
          for(int i = 0; i < diff; i++){
              t1 = t1.next;
          }
      }else{
          for(int i = 0; i < diff; i++){
              t2 = t2.next;
          }
      }
      while(t1 != t2){
          t1 = t1.next;
          t2 = t2.next;
      }
      
      return t1.data;
    }
