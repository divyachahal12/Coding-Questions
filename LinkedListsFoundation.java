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
