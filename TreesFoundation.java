/*-----------------------GENERIC TREES--------------------------------*/

private class Node{
      int data;
      ArrayList<Node> children = new ArrayList<>();
}

//Generic Tree Constructor
public static void construct(){
      int[] arr = = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
      
      Stack<Node> st = new Stack<>();
      Node root = null;
      for(int i = 0; i < arr.length; arr++){
          if(arr[i] == -1){
              st.pop();
          }else{
              Node t = new Node();
              t.data = arr[i];
              if(st.size() > 0){
                  st.peek().children.add(t);
              }else{
                  root = t;
              }
              st.push(t);
          }
      }
  }

//Display a Generic Tree
/*
  We have faith that root will print itself and it's children, 
  and children will print themselves and their children
  d(10) -> s(10) + d(20) + d(30) + d(40)
  */
  public static void display(Node node){
      String str = node.data + " -> ";
      for(Node child : node.children){
          str += child.data + ",";
      }
      
      str += ".";
      System.out.println(str);
      
      for(Node child : node.children){
          display(child);
      }
  }

/*OUTPUT
10 -> 20, 30, 40,. V
20 -> 50, 60,. ~
50->.
60 -> .
30-570,80, 90, V
70 -> ."
80->110, 120,. V
110 ->.
120 ->.
90 -> .\
40 -> 100,.
100 ->.
*/

//Size Of Generic Tree
//The function is expected to count the number of nodes in the tree and return it.
public static int size(Node node){
      int s = 0;
      for(Node child: node.children){
          int childSize = size(child);
          s += childSize; 
      }
      s += 1;//for itself
      return s;
}
 
//Maximum In A Generic Tree
//The function is expected to find the node with maximum value and return it.
  public static int max(Node node) {
      int m = node.data;
      for(Node child : node.children){
          int childMax = max(child);
          if(childMax > m){
              m = childMax;
          }
      }
      return m;
  }
//same as
private static int max(Node node) {
  int m = Integer.MIN_VALUE;

  for (Node child : node.children) {
    int cm = max(child);
    m = Math.max(m, cm);
  }
  m = Math.max(m, node.data);
  return m;
}

//Height Of A Generic Tree
/* The function is expected to find the height of tree. 
   Depth of a node is defined as the number of edges it is away from the root (depth of root is 0). 
   Height of a tree is defined as depth of deepest node.
*/
  public static int height(Node node) {
        int ht = -1;
        for(Node child : node.children){
            int cht = height(child);
            ht = Math.max(ht, cht);
        }
        ht += 1;
        return ht;
  }

//Generic Tree - Traversals (pre-order, Post-order)
/*
The function is expected to visit every node. While traversing the function must print following content in different situations.
   2.1. When the control reaches the node for the first time -> "Node Pre" node.data.
   2.2. Before the control leaves for a child node from a node -> "Edge Pre" 
   node.data--child.data.
   2.3. After the control comes back to a node from a child -> "Edge Post" node.data- 
   -child.data.
    2.4. When the control is about to leave node, after the children have been visited 
    -> "Node Post" node.data.
*/
  public static void traversals(Node node){
    System.out.println("Node Pre " + node.data);
    for(Node child : node.children){
        System.out.println("Edge Pre " + node.data + "--" + child.data);
        traversals(child);
        System.out.println("Edge Post " + node.data + "--" + child.data);
    }
    System.out.println("Node Post " + node.data);
  }

/*OUTPUT
Node Pre 20
Node Post 20
Edge Post 10--20
Edge Pre 10--30
Node Pre 30
Edge Pre 30--50
Node Pre 50
Node Post 50
Edge Post 30--50
Edge Pre 30--60
Node Pre 60
Node Post 60
Edge Post 30--60
Node Post 30
Edge Post 10--30
Edge Pre 10--40
Node Pre 40
Node Post 40
Edge Post 10--40
Node Post 10
*/

//Level-order Of Generic Tree
//The function is expected to visit every node in "levelorder fashion".
/*
we use a rule; RPA: Remove, Print and Add to operate the queue.
A while loop is applied which runs until the queue is emptied.
An element (of type node) is removed from the queue and stored in the node.
The node which was removed in the previous step, its data gets printed in this step.
Then the children of this node are added to the queue using a for loop.
*/
  public static void levelOrder(Node node){
    Queue<Node> q = new ArrayDeque<>();
    q.add(node);
    
    while(q.size()> 0){
        node = q.remove();
        System.out.print(node.data + " ");
        for(Node child : node.children){
            q.add(child);
        }
    }
    System.out.print(".");
  }

//Levelorder Linewise (generic Tree)
/*The function is expected to visit every node in "levelorder fashion" and print them from left to right (level by level). 
All nodes on same level should be separated by a space. Different levels should be on separate lines

OUTPUT
10 
20 30 40 
50 60 70 80 90 100 
110 120

we take a queue which contains the node to be considered after the current node, 
and here we also maintain another queue say children queue, which will be storing the children until the main queue becomes empty. 
This step ensures that whenever our main queue gets empty then we have filled the next level of nodes present in the child queue. 
So the only step that needs to be done is to put all the contents of the child queue to the main queue 
and insert a new line to segregate the two levels from each other.
*/
  public static void levelOrderLinewise(Node node){
    Queue<Node> mainQ = new ArrayDeque<>();
    mainQ.add(node);
    Queue<Node> childQ = new ArrayDeque<>();
    while(mainQ.size() > 0){
        node = mainQ.remove();
        System.out.print(node.data + " ");
        for(Node child : node.children){
          childQ.add(child);
        }
        
        if(mainQ.size()==0){
            mainQ = childQ;
            childQ = new ArrayDeque<>();
            System.out.println();
        }
    }
  }

//Levelorder Linewise Zig Zag
/*
The function is expected to visit every node in "levelorder fashion" but in a zig-zag manner i.e. 
1st level should be visited from left to right, 
2nd level should be visited from right to left and so on. 
All nodes on same level should be separated by a space.
Different levels should be on separate lines. 

OUTPUT
10 
40 30 20 
50 60 70 80 90 100 
120 110

The point here to note is that whenever our mStack becomes empty it implies that we have completed the current level completely a
nd hence we need to move to the next level. 
This movement can be achieved only if we have a valid zig-zag order of nodes present of the next level somewhere, 
here comes our cStack, which if we observe then it already has stored the next level alternately from the above level, 
so all we need to do is to point our mStack to cStack and make our cStack empty. 
This ensures that we are already at the next level and now cStack will be storing the next level further down.
*/
  public static void levelOrderLinewiseZZ(Node node){
      Stack<Node> mainS = new Stack<>();
      mainS.push(node);
      Stack<Node> childS = new Stack<>();
      int level = 1;//for our convinience starting with 1 in dry run
      while(mainS.size() > 0){
          node = mainS.pop();
          System.out.print(node.data + " ");
          if(level % 2 == 1){//from left side
              for(int i = 0; i < node.children.size(); i++){
                  Node child = node.children.get(i);
                  childS.push(child);
              }
          }else{//from right side
              for(int i = node.children.size() - 1; i >= 0; i--){
                  Node child = node.children.get(i);
                  childS.push(child);
              }
          }
          if(mainS.size() == 0){
              mainS = childS;
              childS = new Stack<>();
              level++;//moving to next level for zigzag
              System.out.println();
          }
      }
  }

//Level Order Traversals More Approaches
/*
Solution 1: Using Two Queues

This solution, which uses two queues, (main queue and child queue),

we take a queue which contains the node to be considered after the current node, 
and here we also maintain another queue say children queue, which will be storing the children until the main queue becomes empty. 
This step ensures that whenever our main queue gets empty then we have filled the next level of nodes present in the child queue. 
So the only step that needs to be done is to put all the contents of the child queue to the main queue 
and insert a new line to segregate the two levels from each other.
*/
  public static void levelOrderLinewise(Node node){
    Queue<Node> mainQ = new ArrayDeque<>();
    mainQ.add(node);
    Queue<Node> childQ = new ArrayDeque<>();
    while(mainQ.size() > 0){
        node = mainQ.remove();
        System.out.print(node.data + " ");
        for(Node child : node.children){
          childQ.add(child);
        }
        
        if(mainQ.size()==0){
            mainQ = childQ;
            childQ = new ArrayDeque<>();
            System.out.println();
        }
    }
  }

/*
Solution 2: Using Dummy Node:
Idea: We can mark the end of the current level by inserting a dummy node (with value -1), 
      which can help us know that the next node to be popped will be in the next level.

Initially, we will insert the root node and a dummy node as well before the while loop.
Now, we will run the while loop until the queue becomes empty.
Dequeue the front element of the queue. Print the node value, and now, there will be two cases:
If the current element is not a dummy node (value is not -1), then simply enqueue all the children of the node in the queue.
Else, the dummy node marks the end of the current level. Hence, we should print a new line, 
      and also enqueue another dummy node at the end of the queue (to mark the end of the next level).
But, before printing a new line and adding another dummy node, just check whether the queue is empty or not. 
      If the queue is empty, we just need to break as the current level is the last one and there are no more levels to be processed.
      
Important Note: If we push another dummy node for the last level also, then we will get stuck in an infinite loop, 
      as we will keep on popping one and adding another dummy node, and the size of the queue will never become 0.
*/
public static void levelOrderLinewise(Node node) {
  Queue < Node > mq = new ArrayDeque < > ( );

  mq.add(node);
  mq.add(new Node (-1));

  while (mq.size() > 0) {
    node = mq.remove();
    if (node.data != -1) {
      System.out.print(node.data + " ");

      for (Node child : node.children) {
        mq.add(child);
      }
    } else {
      if (mq.size() > 0) {
        mq.add(new Node(-1));
        System.out.println();
      }
    }
  }
}

/*
Solution 3: Using Size Variable:
Idea: We can store the size of the current level in an integer variable. 
      Then, we can run a loop equal to size times and push the children nodes (next level) in the queue.

By storing the size in another variable, we will not have to worry about marking the pointer of the end of the current level, 
as we will run a loop for size number of times.

Initially, we will insert the root node in the queue before the while loop.
Now, we will run the while loop until the queue becomes empty.
Store the size of queue (number of nodes in current level) in an integer variable size.
Run a loop for size number of times.
Dequeue one element from the front of the queue, print the node value and enqueue all of it's children nodes in the queue.
Print a newline to output the next level in the next line.

Important Note: We have stored size and not used mq.size() directly in the for loop, as it will keep on increasing after adding the child nodes, 
                and eventually all nodes will get printed in the same level.
*/
public static void levelOrderLinewise3(Node node) {
  Queue < Node > mq = new ArrayDeque < > ( );

  mq.add(node);

  while (mq.size() > 0) {
    int cicl = mq.size();
    for (int i = 0; i < cicl; i++) {
      node = mq.remove();
      System.out.print(node.data + " ");

      for (Node child : node.children) {
        mq.add(child);
      }
    }
    System.out.println();
  }
}

/*
Solution 4: Using Pair Node:
Idea: Instead of just pushing the node's value, we can also push it along with its level number, i.e. make a pair object of node and level. 
      Whenever we encounter a node with a level greater than the level of the previous node, we will print a new line also after printing the node's value.

Initially, we will insert the root node with it's level as 1.
We will initialize the current level in an integer variable as 1.
Now, we will run the while loop until the queue becomes empty.
Dequeue the front element of the queue. Print the node value. Push all the children of the nodes with level 1 more than the node's level.
There will be two cases:
If the node's level is equal to the current level, then do not do anything else.
If the node's level is greater than the current level, then also print a new line and update the current level as the node's level.

Important Note: This method takes extra memory for storing levels of the node. But the space complexity will remain the same, and that is O(n).
*/
public static void levelOrderLinewise4(Node node) {
  Queue < Pair > mq = new ArrayDeque < > ( );

  mq.add(new Pair(node, 1));

  int level = 1;
  while (mq.size() > 0) {
    Pair p = mq.remove();
    if (p.level > level) {
      level = p.level;
      System.out.println();
    }

    System.out.print(p.node.data + " ");
    for (Node child : p.node.children) {
      Pair cp = new Pair(child, p.level + 1);
      mq.add(cp);
    }
  }
}

/*
Time Complexity:
O(n)
All the approaches will have equivalent time complexity, as we will be pushing/popping each node exactly once in all the approaches. 
Thus, the time complexity will be O(n) where n = number of nodes in a generic tree.

Space Complexity:
O(n)
We are using an auxiliary queue data structure for level order traversal. 
At any time, the maximum size of the queue will be equal to the maximum nodes in any level of the generic tree. Thus, O(n) auxiliary space is used.
*/
