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
public void display(){
      Node temp = head;
      while(temp != null){
          System.out.print(temp.data+" ");
          temp = temp.next;
      }
      System.out.println();
      
 }

//Remove First in LL
// You are required to complete the body of removeFirst function 
//          removeFirst - This function is required to remove the first element from 
//           Linked List. Also, if there is only one element, this should set head and tail to 
//           null. If there are no elements, this should print "List is empty".
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

//get valur in LL
public int getFirst() {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      } else {
        return head.data;
      }
    }

    public int getLast() {
      if (size == 0) {
        System.out.println("List is empty");
        return -1;
      } else {
        return tail.data;
      }
    }

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
*/
public static class LLToStackAdapter {
    LinkedList<Integer> list;

    public LLToStackAdapter() {
      list = new LinkedList<>();
    }


    int size() {
      return list.size();
    }

    void push(int val) {
      list.addFirst(val);
    }

    int pop() {
      if(size() ==0){
          System.out.println("Stack underflow");
          return -1;
      }
      return list.removeFirst();
    }

    int top() {
        if(size() ==0){
          System.out.println("Stack underflow");
          return -1;
      }
      return list.getFirst();
    }
  }


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
*/
public static class LLToQueueAdapter {
    LinkedList<Integer> list;

    public LLToQueueAdapter() {
      list = new LinkedList<>();
    }

    int size() {
      return list.size();
    }

    void add(int val) {
      list.addLast(val);
    }

    int remove() {
      if(size() == 0){
          System.out.println("Queue underflow");
          return -1;
      }
      return list.removeFirst();
    }

    int peek() {
      if(size() == 0){
          System.out.println("Queue underflow");
          return -1;
      }
      return list.getFirst();
    }
  }

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


//
