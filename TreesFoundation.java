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
    int cicl = mq.size();//children in current level
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


//Mirror A Generic Tree
/*
The function is expected to create a mirror image of the tree. 

To achieve this mirror image we will make the use of recursion. 
The basic idea here is to reverse the order of nodes at every level. 
To achieve this we will have to start reversing the levels from lowest to the topmost hence post traversal should be adopted. 
If we reverse the above levels first, 
      then we would get the wrong output as the reverse of the above levels will change the positioning of nodes at below levels 
      hence we start reversing from the lowermost level coming way back to the root. 
To reverse the order of nodes we can simply reverse the ArrayList containing the children for every node.

Build Faith : We must have faith that if our code can give us an output for the entire generic tree, 
              then it can definitely give as an output its subtree.
              Those subtrees of that generic tree are considered where each root of that sub tree is a child of the root of the original generic tree. 
The families of these sub trees should get reversed for getting the desired output. 

Time Complexity: O(n)
The time complexity for the function is linear as we post traversing the tree.

Space Complexity: O(logn)
The space complexity for the function is equal to the height of the tree due to the recursion stack.
*/
  public static void mirror(Node node){
        for(Node child : node.children){
            mirror(child);
        }
        
        Collections.reverse(node.children);
  }

//Remove Leaves In Generic Tree
/*
The function is expected to remove all leaves from the tree.
 
To achieve this we will be using preorder traversal and for every node, 
we will be traversing all its children in the order of right to left and check each child to be a leaf or not, 
if yes then we would remove that node from the ArrayList of children.

The point here to note that we must traverse the children ArrayList from right to left direction, 
the reason being if we traverse from left to right, then say for our above case for i = 0 we will be at the node with value 40 which is a leaf node, 
deleting it will now alter the positioning of other nodes present in the ArrayList forming {30, 20} 
and we also increment i =1 hence we get the node with value 20, 
if we observe then we have missed node 30 hence we can get wrong results, 
to prevent this situation we will be traversing in reverse order so that even if the order gets changed we would still always be at the correct index.
*/
  public static void removeLeaves(Node node) {
      for(int i = node.children.size() -1; i >= 0; i--){
          Node child = node.children.get(i);
          if(child.children.size() == 0){
              node.children.remove(child);
          }
      }
      for(Node child: node.children){
          removeLeaves(child);
      }  
  }
/*
Time Complexity: O(n)
The time complexity for the function is linear as we are using preorder traversal.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/

//Linearize A Generic Tree
/*
The problem here is to linearize the given input generic tree in a pre-order fashion where every node shall have only one child.

The problem can be solved using recursion. 
We keep our faith in recursion that whenever we call our linearize() function to our children it creates a linearize subtree. 
Now if we call our linearize() function for all the children of our root node then we get all linearized subtrees. 
Now our task at hand is to linearize the tree with respect to the root node. 
As we know that we say that our tree is linearized when we have only one child remaining,
so we will iterate over our root node children until we have a single child remaining. 
While iterating we will do the following:

1. Remove the last child from the root node.

2. Fetch the second last child from the root node.

3. Get the tail of the second last child by calling a function which traverse over the subtree until it reaches the leaf node.

4. Attach the last child at the tail of the second last child.

5. Repeat the steps until root has only one child left.

This approach has a time complexity of O(n2) as for every node we are getting the tail of its subtree child which is also a linear function.
*/
  public static void linearize(Node node) {
    for (Node child : node.children) {
      linearize(child);
    }
    while (node.children.size() > 1) { //run loop till the children arraylist has only 1 child for root node in it
      Node lc = node.children.remove(node.children.size() - 1);//lastChild
      Node slc = node.children.get(node.children.size() - 1);//SecondLastChild
      Node tail = GetTail(slc);
      tail.children.add(lc);
    }
  }

  //tail would be that node in Second last child, which has 0 children of itself, so that we can add last child of original node as its child
  private static Node GetTail(Node node) {
    while (node.children.size() == 1) {
      node = node.children.get(0);
    }
    return node;
  }

//*******Do its optimisation code for O(N) time complexity from video

//Find In Generic Tree
/*
The problem is relatively simple and based on traversals on the tree. 
Here we only need to traverse the tree and check for every node whether the current node is the desired node or not. 
If yes, then we return true and if we reach the end of the branch then we return false, 
if in any case, we get true then we can simply return true without checking the rest of the branches.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the given tree due to the recursion stack.
*/
  public static boolean find(Node node, int data) {
      if(node.data == data){
          return true;
      }
      for(Node child : node.children){
          boolean flag = find(child, data);
          if(flag == true){
              return true;
          }
      }
      return false;
  }

//Node To Root Path In Generic Tree
/*
The function is expected to return in form of linked list the path from element to root, if the element with data is found.

The problem is based on traversals on the tree. 
Here we need to traverse the tree and check for every node whether the current node is the desired node or not. 
If yes, then we return an ArrayList having the node value in it 
and if we reach the end of the branch then we return an empty ArrayList 
if in any case, we get a non-empty ArrayList then we add the current node to the list 
and return immediately without considering the following branches as the search there would be redundant. 
So in the end, we will have a path from the desired node then way back to the root node. 
This problem is similar to our previous problem, 
Find in a generic tree where we are finding the desired node if found 
then instead of returning a Boolean this time we are returning an ArrayList containing the path.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the given tree due to the recursion stack.
*/
 public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        if(node.data == data){
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(Node child : node.children){
            ans = nodeToRootPath(child, data);
            if(ans.size() != 0){
                ans.add(node.data);
                return ans;
            }
        }
        return ans;
 }

//Lowest Common Ancestor (generic Tree)
/*
The function is expected to return the lowest common ancestor of two data values that are passed to it.
 
The lowest common ancestor is the lowermost common node after which the branches for both nodes differ. 
Alternatively, it can also be defined as the last common node in the node to the root path for both given nodes.

The problem is based on the node to root path problem discussed earlier. 
Our definition for LCA clearly shows its dependency on the node to the root path, hence the previous problem is a strong pre-requisite for this problem. 
To achieve our solution we need to get the node to root path for both nodes, 
and thereafter all we need to do is to traverse the paths simultaneously till the point when the nodes are not common. 
At this moment the node just before the current node will be our lowest common ancestor.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(n)
The space complexity for the function is linear as we are maintaining two paths which in the worst case can be equal to the size of the tree. 
In addition to this, some extra space is also consumed due to the recursion stack.
*/
  public static int lca(Node node, int d1, int d2) {
      ArrayList<Integer> p1 = nodeToRootPath(node, d1);
      ArrayList<Integer> p2 = nodeToRootPath(node, d2);
      int i = p1.size() - 1;
      int j = p2.size() - 1;
      while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
          i--;
          j--;
      }
      //to go back to previous same ancestor
      i++;
      j++;
      return p1.get(i);
  }

//Distance Between Two Nodes In A Generic Tree
/*
The problem here is to get the distance between the given two nodes present in the given input generic tree.

The problem is based on the node to root path and lowest common ancestor problems discussed earlier. 
Hence the previous problems are a strong pre-requisite for this problem. 
To achieve our solution we need to get the node to root path for both nodes, 
and thereafter all we need to do is to traverse the paths simultaneously till the point when the nodes are not common. 
At this moment the distance between both the nodes is the sum of the number of nodes in between the LCA and the desired nodes.
*/
  public static int distanceBetweenNodes(Node node, int d1, int d2){
      ArrayList<Integer> p1 = nodeToRootPath(node, d1);
      ArrayList<Integer> p2 = nodeToRootPath(node, d2);
      int i = p1.size() - 1;
      int j = p2.size() - 1;
      while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
          i--;
          j--;
      }
      i++;
      j++;
      //ans would be adding the distances between the common node to the given nodes
      return i+j;
  }

//Are Trees Similar In Shape
/*
The problem here is to check whether the given two input generic trees are similar in shape or not. 
The similarity in shape means that they have the same number of nodes at each level and the same connections for every node, values of the node are not a consideration for this particular problem.

The idea is to traverse both the trees simultaneously and check for every node if the number of children for both the nodes are the same or not, 
if not then there is no way that the tree can have a similar shape hence we return a false value and return from the code, 
but if yes, we continue to recursively call for all children of the nodes to check whether the subtree are similar in shape or not.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  public static boolean areSimilar(Node n1, Node n2) {
      if(n1.children.size() != n2.children.size()){
          return false;
      }
      for(int i = 0; i < n1.children.size() - 1; i++){
          Node c1 = n1.children.get(i);
          Node c2 = n2.children.get(i);
          if(areSimilar(c1, c2) == false){
              return false;
          }
      }
      return true;
  }

//Are Trees Mirror In Shape
/*
The problem here is to check whether the given two input generic trees are the mirror in shape or not. 
The mirror similarity in shape means that they have a similar shape when one of them is converted to its mirror image,
values of the node are not a consideration for this particular problem.

Our previous problems, are trees similar in shape and is tree mirror, are a strong pre-requisite for this problem. 
There we check for the number of children nodes for the same position for both the trees, 
but for this problem the conditions are different since we check for the mirror image, 
so the idea is to treat one of the trees in a mirror-fashion and then apply similarity logic. 
This implies that we are here only checking similarity in shape for a mirror-imaged tree 
and a tree that is considered to be similar to the former trees mirror image. 
Now as we know that the mirror image is achieved by reversing every node at each level, 
hence, say for a particular node we will be comparing the first node of one tree with the last node of the other tree, 
this step ensures that we are comparing taking the mirror image of one of the tree. 
If at any point we get that the shapes are not similar then we return false, 
as if one of the subtrees fails then the entire tree is destined to fail because there always will exist an irregularity.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  public static boolean areMirror(Node n1, Node n2) {
      if(n1.children.size() != n2.children.size()){
          return false;
      }
      for(int i = 0; i < n1.children.size(); i++){
          int j = n1.children.size() - 1 - i;
          Node c1 = n1.children.get(i);
          Node c2 = n2.children.get(j);
          if(areMirror(c1, c2) == false){
              return false;
          }
      }
      return true;
  }

//Is Generic Tree Symmetric
/*
The problem here is to find whether the given tree is symmetric or not.

When we fold the tree along its vertical axis passing through the root node, 
if the left half overlaps the right half then the tree is said to be symmetric. 
If we carefully observe then alternatively we can also say 
that a tree is symmetric whenever the left half of the tree is the mirror image of the right half of the tree. 
This happens since when we say that we fold the tree, 
this means that we are inverting the tree of the left half and then superimposing on the right half, 
which is the same as having a mirror image superimposed.

Hence our problem is now reduced to only finding whether our left subtree is the mirror image of the right subtree or not, 
alternatively, we can also check for whether our tree is the mirror image of itself or not.

The Are trees mirror in shape problem, which we have discussed earlier is a pre-requisite for this problem.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  public static boolean isMirror(Node n1, Node n2){
      if(n1.children.size() != n2.children.size()){
          return false;
      }
      for(int i = 0; i < n1.children.size(); i++){
          int j = n1.children.size() - 1 - i;
          Node c1 = n1.children.get(i);
          Node c2 = n2.children.get(j);
          if(isMirror(c1, c2) == false){
              return false;
          }
      }
      return true;
  }
  public static boolean IsSymmetric(Node node) {
      return isMirror(node, node);
  }

//Multisolver for Generic Tree
/*
Previously we calculated size, height, min and max using recursion.

Here, we will use data members and will keep changing their values. That is we are going to traverse through a tree but not return anything.

Observe that we kept the Node class, display function and construction function as the same.

We want you to focus on the multisolver function.

1.We declare some properties of size, min, max and height. 
These values are always available in heap and do not need to be passed in recursion.
2.We define our previously declared properties in the main function with default values. 
Hence, size is initialized as 0, min as +8, max as -8 and height as 0.
3.The function of multisolver is passed 2 parameters, a node and depth. This depth variable is present in the stack.
4.We change the values of our previously defined variables in the pre area of our recursive function. 
Here, with each traversal the size is increased by one. The min and max values are compared with the data of the node and accordingly changed. 
And the value of height is the maximum of previous height and the depth. 
Height is declared in heap and Depth is in stack.
5.The function multisolver is called recursively for all the children of the node and the depth is increased with each call.

Time Complexity:
O(n)
Space Complexity:
O(1)
*/
import java.io.*;

import java.util.*;

public class Main {
  private static class Node {
    int data;
    ArrayList< Node> children = new ArrayList< >();
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack< Node> st = new Stack< >();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }
  //******************************MULTISOLVER*******************************

  static int size;        //1
  static int min;
  static int max;
  static int height;

  public static void multisolver(Node node, int depth) { //3

    size++;  //4
    min = Math.min(min, node.data);
    max = Math.max(max, node.data);
    height = Math.max(height, depth);

    for (Node child : node.children) { //5
      multisolver(child, depth + 1);
    }
  }


  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);

    size = 0;         //2
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
    height = 0;

    multisolver(root, 0);

    System.out.println("Size=" + size);
    System.out.println("Min=" + min);
    System.out.println("Max=" + max);
    System.out.println("Height=" + height);
  }

}

//Predecessor And Successor Of An Element
/*
The problem here is to find the preorder predecessor and successor for the given node in a given input generic tree.

The intuition behind this problem is to apply a preorder traversal to the generic tree. 
Now that we are traversing the tree we need to constantly check for the nodes to be predecessor, current node, or successor if any. 
So for that, we will be making use of some global variables. 
The idea behind using globals variables is that it eases our task in recursive calls, as it avoids extra parameters during function calls.

Here we will maintain a state variable that will let us know whether this node is a predecessor or successor. 
Initially, we set the value of the state variable to be 0. Now we traverse the tree in a preorder fashion till the point we reach the current node. 
When we reach the current node we immediately increment the state variable which ensures that our predecessor global variable remains fixed from now on. 
Now similar is the case immediately after the current element, 
  as the next node after the current element is our successor so we store the result and increment the state variable, and hence we get our results.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  static Node predecessor;
  static Node successor;
  static int state;
  public static void predecessorAndSuccessor(Node node, int data) {
      if(state == 0){
          if(node.data == data){
              state = 1;
          }else{
              predecessor = node;
          }
      }else if(state == 1){
          successor = node;
          state++;
      }
      for(Node child : node.children){
          predecessorAndSuccessor(child, data);
      }
  }

//Ceil And Floor In Generic Tree
/*
The problem here is to find the ceil and floor values for the given node in the input generic tree.

To understand the problem better firstly we will try to understand the terms ceil and floor. 
The ceil() function returns the smallest integer value that is bigger than or equal to a number. 
The floor() function returns the closest integer less than or equal to a given number.

The idea here is to traverse the tree, either in a preorder fashion or in a postorder fashion, 
the way of traversal does not matter, all it matters is that we consider every possible node. 
Now for every node we just need to check whether the node can be a possible ceil value or floor value or not, 
if yes then we need to update our corresponding variables for the same.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  static int ceil;//ceil is just above i.e. smallest among all larger values
  static int floor;//floor is just below i.e. largest among all smaller values
  public static void ceilAndFloor(Node node, int data) {
      if(node.data > data){//larger values
          if(node.data < ceil){
              ceil = node.data;
          }
      }
      else if(node.data < data){//smaller values
          if(node.data > floor){
              floor = node.data;
          }
      }
      for(Node child : node.children){
          ceilAndFloor(child, data);
      }
  }

//Kth Largest Element In Tree
/*
The problem here is to find the kTh largest element in the input generic tree.

As we already know that the floor() function returns the closest integer less than or equal to a given number. 
This means if we find the floor for Integer.MAX_VALUE then we would get the largest element in the tree, 
and if we find the floor of the largest element of the tree then we would always get the second largest value from the tree and so on. 
This implies that if we get the floor of the (k-1)Th element then we would get the kTh largest element in the given generic tree.

So here we need to exploit this property of floor() function to get our kTh largest element in the tree.

Time Complexity: O(k*n)
The time complexity for the function is k times linear as tree traversal is called k times to find the kTh largest element in the tree.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  public static int kthLargest(Node node, int k){
      floor = Integer.MIN_VALUE;
      int factor = Integer.MAX_VALUE;
      for(int i = 0; i < k; i++){
          ceilAndFloor(node, factor);
          factor = floor;
          floor = Integer.MIN_VALUE;
      }
      return factor;
  }

//Node With Maximum Subtree Sum
/*
The problem here is to get the node having the maximum subtree sum in the given input generic tree.

The idea here is to traverse the tree in a postorder fashion and for every node, 
we need to calculate the sum of all the subtrees from this node, 
after getting the sum we need to compare it with the global maxSum 
if it exceeds the global maxSum then we need to update the maxSum and assign this node as the node with the maximum sum, 
we need to do this step for each node on the tree and thereafter the node and the sum left with us at the end will be our desired result. 
Also for every node, we need to return the subtree sum so that it can be used later on by its ancestors for computation for their subtree sum, 
without visiting all the child nodes, again and again.

This return step ensures that our algorithm has linear time complexity. 
The other approach would be to calculate the sum for every node by calling the sum() function which would take the total time complexity of our code to n2.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the generic tree.
*/
  static int maxSumNode = 0;
  static int maxSum = Integer.MIN_VALUE;
  
  static int returnSumAndCalcMaxSumNode(Node node){
      int sum = 0;
      for(Node child : node.children){
          int childSum = returnSumAndCalcMaxSumNode(child);
          sum += childSum;
      }
      sum += node.data;
      if(sum > maxSum){
          maxSumNode = node.data;
          maxSum = sum;
      }
      return sum;
  }

//Diameter Of Generic Tree ***** IMP
/*
The problem here is to get the lowest common ancestor for the given two nodes present in the given input generic tree.

The maximum number of edges between any two nodes of a generic tree is called the diameter of the tree.

Suppose we wish to find the diameter from any particular node then as we know that we have to maximize the number of edges between any two nodes,
this implies that to maximize the number of edges we have to always consider the leaf nodes. 
If we say that without a leaf node we can form a diameter, 
then if we consider the leaf node of that branch we will be taking care of additional nodes after our so-called end node 
and henceforth our diameter result would get incremented, hence leaf nodes can only make diameter endpoints. 
Now we wish to find a diameter that passes through our current node. 
This can be found by adding the deepest subtree and second deepest subtree and adding 2 to their sum. 
Getting the deepest and second deepest subtree ensures that we are taking maximum possible edges from the current node and 2 is added to link both leaves.

Now we can recurse this approach for every node in our tree as our diameter need not always pass through the root node. 
So at each node, we calculate the diameter from the current node and compare it with the global maximum 
and then we return the height of our subtree which can, later on, be used by our ancestor nodes to calculate their diameter and height. 

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(h)
The space complexity for the function is proportional to the height of the generic tree due to the recursion stack.
*/
  static int dia = Integer.MIN_VALUE;
  static int calcDiaReturnHeight(Node node) {
    int dch = -1;//deepestChildHeight
    int sdch = -1;//secondDeepestChildHeight
    for (Node child : node.children) {
      int childHeight = calcDiaReturnHeight(child);
      if (childHeight > dch) {
        sdch = dch;
        dch = childHeight;
      } else if (childHeight > sdch) {
        sdch = childHeight;
      }
    }
    int cand = dch + sdch + 2;//candidate
    if (cand > dia) {
      dia = cand;
    }
    dch += 1;
    return dch;
  }

//Iterative Preorder And Postorder Of Generic Tree
/*
The problem here is to traverse the given input generic tree iteratively. We need to perform both pre-order and post-order traversal iteratively.

To achieve this, we are creating a class that will be storing the node along with its state (integer data type). 
The state represents the Eulers tree state at any instance of time. 
If state == -1 then it would mean that we are visiting this node for the first time hence a pre-order state 
and when the state value ranges from 0 to [node.children.size() - 1] 
      these are the values indicating that we need to put the (state) index child in the stack (call for this child in case of the recursion). 
When state == node.children.size() this means that we have considered all the children of this node, 
      this also implies that this node is in post-order condition.

The algorithm deals with taking a stack that will mimic the recursion stack for our traversal and initially we will be pushing (root, -1). 
Now we will iterate over the stack till the stack becomes empty and for every iteration, 
we will consider the state of the top of the stack node and its state will decide the action to be taken as mentioned above.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the stack.
*/
  static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }
  public static void IterativePreandPostOrder(Node node) {
    Stack<Pair> st = new Stack<>();
    st.push(new Pair(node, -1));
            String pre = "";
            String post = "";

    while (st.size() > 0) {
    Pair top = st.peek();
      if (top.state == -1) {
        pre += top.node.data + " ";
        top.state++;
      } else if (top.state == top.node.children.size()) {
        post += top.node.data + " ";
        st.pop();
      } else {
        Pair childPair = new Pair(top.node.children.get(top.state), -1);
        st.push(childPair);
        top.state++;//top is still parent node here, it'll change to its child in next iteration
      }
    }
    System.out.println(pre);
    System.out.println(post);
  }

//ITERABLE AND ITERATOR
/*
We wish to print the tree in a preorder using a command
We want the function to directly return all the elements in the tree in preorder without the use of indices. 
GENERIC TREE CLASS:
We make this class to represent the entire generic tree.
We wrap the root property in this class. This is done so that we can apply our loop on the "tree" and not on the "root". 
It has no other reason other than to be semantically correct.

INTERFACES :
An interface is a mechanism which contains just the method of those functions but not the body.
The purpose of these interfaces is to define the signature of the functions.

OBJECT OF INTERFACE:
We cannot make an object of the Interface using "new".
I obj=new I ( ); is NOT the correct way of making an object for an Interface I.
Hence, in the reference/object of Interface we catch the instance of the class implementing it. Then we can call the required function for our object

ITERABLE :
In Java an Interface called "Iterable" is present. We implement this interface when we want to use a loop like the one we want for our generic tree.
It has only a function declared in its body which returns an object of type Iterator and the name of the function is "iterator()". It doesn't take any parameters.
Hence, the class of Generic Tree implements this Iterable interface. We also define this iterator function in our class.

ITERATOR :
Iterator interface contains 2 important functions:
hasNext()- It returns a Boolean value which tells us whether the next value is present or not.
next()- It returns the next value.
we check if the Iterator "gti" has a next value and if it does then we print that next value of "gti".
We now define the hasNext() and next() functions for the class "GTPreorderIterator" which implements the Iterator interface.
*/
public static class GenericTree implements Iterable< Integer> {
  Node root;

  GenericTree(Node root) {
    this.root = root
  }
  public Iterator< Integer> iterator() {
    Iterator< Integer> obj = new GTPreorderIterator();
    return obj;
  }
}
//hasNext() function
public boolean hasNext()
{
  if (nval == null)
  {
    return false;

  } else
  {
    return false;
  }
}

//next() function
public Integer next() {
  Integer fr = nval;
  // moves nval forward, if not possible sets it to null
  nval = null;
  while (st.size() > 0) {
    Pair top = st.peek();
    if (top.state == -1) {
      nval = top.node.data;
      top.state++;
      break;
    }
    else if (top.state >= 0 && top.state < top.node.children.size()) {
      Pair cp = new Pair(top.node.children.get(top.state), -1);
      st.push(cp);
      top.state++;

    }
    else {
      st.pop();
    }
  }
  return fr;
}
}

//CLASS PAIR
private static class Pair {
  Node node;
  int state;
  Pair(Node node, int state) {
    this.node = node;
    this.state = state;
  }
}








/*-----------------------BINARY TREES--------------------------------*/
public class Main{
      public static class Node{
            int data;
            Node left;
            Node right;
            Node(int data, Node left, Node right){
                  this.data = data;
                  this.left = left;
                  this.right = right;
            }
      }
      
}

//Binary Tree Constructor
/*
The algorithm deals with taking a stack and initially we will be pushing (root, 1).

To achieve this, we create a class that will be storing the node along with its state (integer data type). 
The state represents the Euler's tree state at any instance of time.

If state == 1 of the node at the top of the stack then it means that the next element of the array will represent the left of the top node. 
And therefore we will place it at the left of this node.

After that we increment the value of state to 2 and also push the new node into the stack with stage initialized to 1.

In addition, when the state value is 2, it indicates that the next element of the array is the right of the node at top. 
And therefore we will place it at the right of this node.

After that we increment the value of state to 3 and also push the new node into the stack with stage initialized to 1.

Furthermore, when state == 3 this means that it is time to pop the node out of the stack as at this point
we have dealt with both right and left child and placed them at their correct position.

Now we will iterate over the stack till the stack becomes empty 
and for every iteration we will consider the state of the top of the stack node and its state will decide the action to be taken as mentioned above.

Time Complexity:O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity. Also each element is pushed and popped out only once.

Space Complexity:O(n)
The space complexity for the function is proportional to the space used by stack, which can be O(n) at max.//doubt
*/
public static class Pair{
    Node node;
    int state;
    Pair(Node node, int state){
        this.node = node;
        this.state = state;
    }
}
public static void main(String[] args) throws Exception{
    Integer arr[] = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
    
    Node root = new Node(arr[0], null, null);
    Pair rootPair = new Pair(root, 1);
    Stack<Pair> st = new Stack<>();
    st.push(rootPair);
    int idx = 0;
    //state 1 for left, state 2 for right
    while(st.size() > 0){
        Pair top = st.peek();
        if(top.state == 1){
            idx++;
            if(arr[idx] != null){
                top.node.left = new Node(arr[idx], null, null);
                Pair leftPair = new Pair(top.node.left, 1);
                st.push(leftPair);
            }else{//arr[idx] == null
                top.node.left = null;
            }
            top.state++;
        }else if(top.state == 2){
            idx++;
            if(arr[idx] != null){
                top.node.right = new Node(arr[idx], null, null);
                Pair rightPair = new Pair(top.node.right, 1);
                st.push(rightPair);
            }else{//arr[idx] == null
                top.node.right = null;
            }
            top.state++;
        }else{//state == 3
            st.pop();
        }
    }
}

//Display Binary Tree
/*
IMP-> Generic tree don't have null node base as calls are made till the children.size() != 0, But for Binary Tree we need to have null node as base case

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(n)
The space complexity for the function is proportional to the space used by the recursion stack, which can be O(n) at max.//doubt
*/
public static void display(Node root){
    if(node == null){
        return;
    }
    //self work
    String str = "";
    str += node.left == null ? "." : node.left.data +"";//if(node.left == null){str += "."; }else{ str += node.left.data + "";}
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data +"";
    System.out.println(str);
    
    //call left child
    display(node.left);
    //call right child
    display(node.right);
}

//Size, Sum, Maximum And Height Of A Binary Tree
/*
Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  public static int size(Node node) {
      if(node == null){
          return 0;
      }
      return size(node.left) + size(node.right) + 1;
  }

  public static int sum(Node node) {
      if(node == null){
          return 0;
      }
      return sum(node.left) + sum(node.right) + node.data;
  }

  public static int max(Node node) {
      if(node == null){
          return Integer.MIN_VALUE;
      }
      return Math.max(node.data, Math.max(max(node.left), max(node.right)));
  }

  public static int height(Node node) {
      if(node == null){
          return -1;
      }
      return Math.max(height(node.left), height(node.right)) + 1;
  }

//Traversals in a Binary Tree
  public static void traversals(Node node){
      if(node == null){
          return;
      }
      System.out.println(node.data + " in PreOrder");
      traversals(node.left);
      System.out.println(node.data + " in InOrder");
      traversals(node.right);
      System.out.println(node.data + " in PostOrder");
  }

//Levelorder Traversal Of Binary Tree
/*
We will use Queue to solve this problem.
After that we use the rule; RPA: Remove, Print and Add to operate the queue as we have seen in Generic Trees.
This rule says that, remove the element from the queue, print that element and then add its children to the queue.
Here, we continue this process for all the elements of a queue at a time. This means that the loop is iterated for all the children at a level.
Initially the root of the generic tree is added to the Queue. 

Time Complexity:O(n)
The time complexity is linear due to the n number of nodes in the binary tree.

Space Complexity:O(n)
The space complexity is linear due to the n number of nodes in the binary tree.
*/
  public static void levelOrder(Node node) {
    Queue<Node> mainQueue = new ArrayDeque<>();
    mainQueue.add(node);
    while(mainQueue.size() > 0){
        int count = mainQueue.size();
        for(int i = 0; i < count; i++){
            node = mainQueue.remove();
            System.out.print(node.data +" ");
            if(node.left != null){
                mainQueue.add(node.left);
            }
            if(node.right != null){
                mainQueue.add(node.right);
            }
        }
        System.out.println();
    }
  }

//Iterative Pre, Post And Inorder Traversals Of Binary Tree
/*
Time Complexity:O(n)
The time complexity is linear due to the traversals in the Binary Tree.

Space Complexity:O(n)
The space complexity is linear due to the use of stack space.
*/
  public static void iterativePrePostInTraversal(Node node) {
    Stack<Pair> st = new Stack<>();
    Pair rootPair = new Pair(node, 1);
    st.push(rootPair);
    String pre = "";
    String in = "";
    String post = "";
    while (st.size() != 0) {
      Pair top = st.peek();
      if (top.state == 1) { //pre, s++, left add
        pre += top.node.data + " ";
        top.state++;

        if (top.node.left != null) {
          Pair lc = new Pair(top.node.left, 1);
          st.push(lc);
        }
      } else if (top.state == 2) { //in, s++, right add
        in += top.node.data + " ";
        top.state++;

        if (top.node.right != null) {
          Pair rc = new Pair(top.node.right, 1);
          st.push(rc);
        }
      } else { //post, pop
        post += top.node.data + " ";
        st.pop();
      }
    }

    System.out.println(pre);
    System.out.println(in);
    System.out.println(post);
  }

//Find And Node to rootpath In Binary Tree
/*
The problem is based on traversals on the tree. 
Here we only need to traverse the tree and check for every node whether the current node is the desired node or not. 
If yes, then we return true and if we reach the end of the branch then we return false, 
if in any case, we get true then we can simply return true without checking the rest of the branches.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  public static boolean find(Node node, int data, ArrayList<Integer> ans){
      if(node == null){
          return false;
      }
      if(node.data == data){
          ans.add(node.data);
          return true;
      }
      boolean flc = find(node.left, data, ans);
      if(flc == true){
          ans.add(node.data);
          return true;
      }
      boolean frc = find(node.right, data, ans);
      if(frc == true){
          ans.add(node.data);
          return true;
      }
      return false;
  }

  public static ArrayList<Integer> nodeToRootPath(Node node, int data){
      ArrayList<Integer> ans = new ArrayList<>();
      boolean res = find(node, data, ans);
      return ans;
  }

//Print K Levels Down
/*
The problem here deals with printing the nodes which are at a distance k away from the root node in a preorder fashion.

To achieve this we will use preorder traversal on the binary tree. 
For every node we will check whether the distance from the root node is K or not, 
if yes then we would print the node and return, as there is no point going to levels below K. 
If not, then we would recursively call for left subtree and right subtree which are a level down. 
Moreover, our base case will handle cases when we hit a null node or if K becomes less than 0, then in this case we will simply return from function calls.
Passing K as a function argument helps us to check the distance of the current node from the root node without calling any helper function 
to calculate the distance between two nodes, this ensures that our algorithm works in linear time complexity.

Time Complexity: O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity: O(logn)
The space complexity for the function is proportional to the height of the tree due to the recursion stack.
*/
  public static void printKLevelsDown(Node node, int k){
      if( node == null || k < 0){
          return;
      }
      if(k == 0){
          System.out.println(node.data);
      }
      printKLevelsDown(node.left, k - 1);
      printKLevelsDown(node.right, k - 1);
  }

//Print Nodes K Distance Away
/*
The function is expected to print all nodes which are k distance away in any direction from node with value equal to data.

Time Complexity:O(n)
*/
  public static ArrayList<Node> findNodeToRoot(Node node, int data){
      if(node == null){
          return new ArrayList<>();
      }
      if(node.data == data){
          ArrayList<Node> ans = new ArrayList<>();
          ans.add(node);
          return ans;
      }
      ArrayList<Node> leftChild = findNodeToRoot(node.left, data);
      if(leftChild.size() != 0){
          leftChild.add(node);
          return leftChild;
      }
      ArrayList<Node> rightChild = findNodeToRoot(node.right, data);
      if(rightChild.size() != 0){
          rightChild.add(node);
          return rightChild;
      }
      return new ArrayList<>();
  }
  public static void printKLevelsDown(Node node, int k, Node block){
      if(node == null || k < 0 || node == block){
          return;
      }
      if(k == 0){
          System.out.println(node.data);
          return;
      }
      printKLevelsDown(node.left, k - 1, block);
      printKLevelsDown(node.right, k - 1, block);
  }
  public static void printKNodesFar(Node node, int data, int k) {
      ArrayList<Node> path = findNodeToRoot(node, data);
      for(int i = 0; i < path.size(); i++){
          printKLevelsDown(path.get(i), k - i, i == 0 ? null : path.get(i - 1));
      }
  }

//Path To Leaf From Root In Range
/*
The function is expected to print all paths from root to leaves which have sum of nodes in range from lo to hi (both inclusive).

We check whether we have reached the leaf node by checking if both its children are null.
We need to add the current node to the sum because it wasn't included in the previous recursion call.
Now we check whether the sum of the given path lies in the given range between lo and hi (both inclusive). 
If it does then the path is printed by adding the current node to the path because we didn't include that node when we made our previous recursion call.
We call the recursive function on the left node of the current node. 
The data of that node is included in the path so far and arithmetically added to the sum so far. 
Similarly , it is called on the right node of the current node. 
The data of that node is included in the path so far and arithmetically added to the sum so far
*/
  public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
      if(node == null){
          return;
      }
      if(node.left == null && node.right == null){
          sum += node.data;
          if(sum >= lo && sum <= hi){
              System.out.println(path + node.data);
          }
          return;
      }
      pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
      pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
  }

//Transform To Left-cloned Tree
/*
The function is expected to create a new node for every node equal in value to it and inserted between itself and it's left child.

Expectation: I need my entire tree rooted at node to transform into a left cloned tree.
      createLeftCloneTree(node)
Faith: I will have faith on the left and right subtrees that they know how to transform into a left-cloned tree.
      leftcloned = createLeftCloneTree(node.left)
      rightcloned = createLeftCloneTree(node.right)
Meeting Expectation using Faith:
Now, we have left-cloned tree of left subtree in leftcloned and left-cloned tree of right subtree in rightcloned nodes.
So, we need to do some work in POSTORDER in order to left-clone the entire tree rooted at node.

First, we will create a duplicate node of the root node with the same value. 
Since the inserted node should be sandwiched between the root node and the left subtree, 
we will make the left child of this node as leftcloned, and update the root node's left child as this new node.
      node.left = new Node(node.data, leftcloned, null)
Since, the right subtree will not contain the duplicate node of root, hence we can directly update the root node's right as rightcloned.
      node.right = rightcloned

Time Complexity:O(n)
We are traversing the entire tree and duplicating each node, hence the total time complexity is O(n) where n = number of nodes in the tree.

Space Complexity:
If we take the space taken by the duplicate nodes created, then the space complexity will be O(n).
Also, the recursion stack space will take O(d) space where d = maximum depth of the binary tree.
*/
  public static Node createLeftCloneTree(Node node){
      if(node == null){
          return null;
      }
      Node leftCloneRoot = createLeftCloneTree(node.left);
      Node rightCloneRoot = createLeftCloneTree(node.right);
      
      Node newNode = new Node(node.data, leftCloneRoot, null);
      node.left = newNode;
      node.right = rightCloneRoot;
      
      return node;
  }

//Transform To Normal From Left-cloned Tree
/*
The function is expected to convert a left-cloned tree back to it's original form. The left cloned tree is dicussed in previous question. 
In a left-clone tree a new node for every node equal in value to it is inserted between itself and it's left child

Try to think and do reverse engineering, so that you can get back the normal binary tree. 
We need to delete all the duplicate nodes that are present in the left-cloned binary tree.

So, let us try to find the recursive solution using the low-level thinking of defining expectations, faith and meeting expectation with faith.
Expectation: We expect that the entire left-cloned binary tree rooted at the node will transform back to a normal binary tree.
      transBackFromLeftClonedTree (node)
Faith: We will keep on the smaller subproblems, 
      i.e. we will have complete belief that the left-cloned left subtree 
      and the right-cloned right subtree know how to transform back to a normal binary tree.
But, there is a slight twist here. What should be the left child of the current node. Should we take left as node.left?
No, since the direct left child of the root node is it's duplicate node only, hence the left subtree is not node.left but node.left.left.
Try to think about it for a minute, why node.left.left will give us back the original left child 
and how we will be able to get rid of the duplicate node easily.
      node.left = transBackFromLeftClonedTree (node.left.left)
      node.right = transBackFromLeftClonedTree (node.right)
Meeting Expectation with Faith: We needed to delete the duplicate node of the current root, which is present as the direct left child.
By updating node's left as normal tree of node.left.left, we have automatically deleted the duplicate node of the root.
Why? It is because Java has an automatic garbage collection mechanism, so we need not worry about the deallocation of memory.
Thus, we can simply return the root node: return node

Time Complexity:O(n)
We are traversing only n/2 nodes (skipping the duplicate nodes), hence total time complexity will be O(n/2) = O(n).

Space Complexity:
We are not taking any extra space, in fact we are freeing up some space by deleting duplicate nodes. Hence, the solution takes O(1) auxiliary space.
Although, there is still recursion call stack space of O(d) where d = maximum depth of the tree.
*/
  public static Node transBackFromLeftClonedTree(Node node){
      if(node == null){
          return node;
      }
      Node leftNormalNode = transBackFromLeftClonedTree(node.left.left);
      Node rightNormalNode = transBackFromLeftClonedTree(node.right);
      
      node.left = leftNormalNode;
      node.right = rightNormalNode;
      
      return node;
  }

//Print Single Child Nodes
/*
The function is expected to print in separate lines, all such nodes which are only child of their parent. Use preorder for traversal.

High-Level Thinking:
Expectation: We expect that the function printSingleChildNodes(n,p) 
             will print all those nodes who are the only child of their parents where n is the node and p is its parent.
Faith: Have a look at the image shown below (fig-2). 
       We have faith on the recursion that if we call upon the left sub-tree, 
       we will be able to print all the single child nodes in the left subtree 
       and if we call upon the right subtree then recursion will print all the child nodes of the right subtree. 
       As discussed above also, the root node cannot be a single child node as it has no parent. 
       So, we don't need to check anything for the root node. So, there is no need for any relation in this recursion.
Note: We have been applying this check for every node and we will apply this check for every node except the root node. 
      This is because the root node does not have any parent. 
      So, we can say that we will apply this check only if the parent is not equal to null i.e. the node is not a root node.
Time Complexity:
The time complexity of the above code is O(n) as we are traversing every node in the binary tree to check whether it is a single child or not.
Space Complexity:
The space complexity of the above solution is O(1) as we are not using any extra data structure or memory. 
If we consider the recursion space then 
      the time complexity of the above solution will become O(log2n) as the maximum height of the stack at any point of time will be O(log2n) 
      which is also the height of the tree.
*/
  public static void printSingleChildNodes(Node node, Node parent){
      if(node == null){
          return;
      }
      if(parent != null && parent.left == node && parent.right == null){
          System.out.println(node.data);
      }else if(parent != null && parent.left == null && parent.right == node){
          System.out.println(node.data);
      }
      printSingleChildNodes(node.left, node);
      printSingleChildNodes(node.right, node);
  }

//Remove Leaves In Binary Tree
/*
High-Level Thinking:
Expectation: We expect that the function removeLeaves() when passed with the root node as a parameter will remove all the leaf nodes from the tree 
             and return the new root node for the tree.
Faith:  We have to remove the leaf nodes from the tree. 
So, we say that if we remove all the leaf nodes from the left subtree and the right subtree then all the leaves will be removed from the tree.

Time Complexity:
The time complexity of the above solution is O(n) as we are visiting every node in the tree to check whether it is a leaf node or not.

Space Complexity:
The space complexity of the above solution is O(1) as we are not using any extra data structure or memory. 
If we consider the recursion space then the time complexity of the above solution will become O(log2n) 
as the maximum height of the stack at any point of time will be O(log2n) which is also the height of the tree.

*/
  public static Node removeLeaves(Node node){
      if(node == null){
          return null;
      }
      if(node.left == null && node.right == null){
          return null;
      }
      node.left = removeLeaves(node.left);
      node.right = removeLeaves(node.right);
      return node;
  }

//Diameter Of A Binary Tree
/*
The function is expected to return the number of edges between two nodes which are farthest from each other in terms of edges.
The diameter is defined as the maximum number of edges between any two nodes in the tree.
We need to maximize the number of edges between any two nodes to calculate diameter.
To be noted that to maximize the number of edges we have to always consider the leaf nodes.
Now we wish to find the diameter that passes through our current node.
This can be found by adding the height of the deepest node in the left subtree and height of deepest node in right subtree and adding 2 to their sum. 
We call this as factor 1.
But there is a possibility that a larger diameter is present entirely in either the left subtree or right subtree.
Let's call factor 2 to be the diameter in the left subtree. So this represents the scenario when both the deepest nodes lie in the left subtree.
And factor 3 is the diameter in the right subtree. This represents the scenario when both the deepest nodes lie in the right subtree.
The final result will be the maximum of these three factors.
Now we can recurse this approach for every node in our tree as our diameter does not always pass through the root node. 
So at each node, we calculate the diameter using all three factors and compare them and return the maximum value.

Time Complexity:O(n^2)
The time complexity for the function is exponential as we have discussed this above.

Space Complexity:O(n)
The space complexity for the function is proportional to the height of the recursion stack which can be O(n) at max.

Yes, that works right but this is not the efficient way of doing it. 
Why?
Initially we make 2 recursive calls for left diameter (ld) and right diameter (rd), 
until this it's still alright but when we calculate factor 1 (f1) and make 2 more recursive calls to calculate height of left and right subtree 
then the time complexity of the function immediately shoots from O(n) to O(n^2).

What do we do now?
Our above function is making recursive calls separately for calculating diameter and height of both left and right subtree.
If somehow, we manage to calculate both diameter and height in the same recursive call then we may reduce the time complexity to O(n).
*/
  public static int height(Node node) {
    if (node == null) {
      return -1;
    }

    int lh = height(node.left);
    int rh = height(node.right);

    int th = Math.max(lh, rh) + 1;
    return th;
  }

  public static int diameter1(Node node) {
      if(node == null){
          return 0;
      }
      //left max distance b/w nodes
      int ld = diameter1(node.left);
      //right max distance b/w nodes
      int rd = diameter1(node.right);
      //distance b/w left's and right's deepest nodes
      int f = height(node.left) + height(node.right) + 2;
      
      int ans = Math.max(f, Math.max(ld, rd));
      return ans;
  }

/* EFFICIENT APPROACH */
/*
To start with, this function has a return type of DPair, so that each recursive call can return both height and diameter to further calculate our result.

Also it's obvious that two recursive calls will be made to both left and right of the given node 
and since the return type is Dpair we store the result of each call in variables left pair (lp) and right pair (rp) of type Dpair.

We have faith that both these pairs store the information i.e. diameter and height at each call, which we need to calculate our result, 
so we do the required calculation by accessing this stored information.
As at leaf nodes no further calls could be made so whenever node becomes null, 
we will initialize a Dpair bp (base pair)and set the values of height and diameter as -1 (because there is no node) and 0 respectively 
and return this base pair.

Time Complexity:O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity:O(n)
The space complexity for the function is proportional to the height of the recursion stack which can be O(n) at max.
*/
  static class DPair{
      int ht;
      int dia;   
  }
  public static DPair diameter(Node node){
      if(node == null){
          DPair base = new DPair();
          base.ht = -1;
          base.dia = 0;
          return base;
      }
      DPair lp = diameter(node.left);
      DPair rp = diameter(node.right);
      
      DPair myPair = new DPair();
      myPair.ht = Math.max(lp.ht, rp.ht) + 1;
      myPair.dia = Math.max(lp.ht + rp.ht + 2, Math.max(lp.dia, rp.dia));
      
      return myPair;
  }
  public static int diameter1(Node node) {
      if(node == null){
          return 0;
      }
      DPair ans = diameter(node);
      return ans.dia;
  }

//Tilt Of Binary Tree
/*
 The function is expected to set the value of data member "tilt". 
 "tilt" of a node is the absolute value of difference between sum of nodes in it's left subtree and right subtree. 
 "tilt" of the whole tree is represented as the sum of "tilt"s of all it's nodes.
 
To calculate total tilt of the binary tree, we travel the tree using postorder:

First of all, at a particular node, we calculate the sum of all nodes of it's left subtree.
Then we calculate the sum of all nodes of it's right subtree.
Then we calculate the total sum, which is the sum of that node value plus the above calculated sums.
After that, we calculate the tilt at the current node, which is the modulus of difference of sum of all nodes of left subtree
      and sum of all nodes of right subtree.
And simultaneously we will keep the track of total tilt so far and keep updating it.
Not to forget about the base case, since we will be returning the total sum of each node. 
      So the base case hits when the node becomes null, when the leaf node makes further calls.
So, we return 0 as the total sum of null nodes.

Time Complexity:O(n)
The time complexity for the function is linear as tree traversal is involved which is linear in terms of time complexity.

Space Complexity:O(n)
The space complexity for the function is proportional to the height of the recursion stack which can be O(n) at max.
*/
  static int tilt = 0;
  public static int tilt(Node node){
      if(node == null){
          return 0;
      }
      int ls = tilt(node.left);
      int rs = tilt(node.right);
      
      int ltilt = Math.abs(ls - rs);
      tilt += ltilt;
      int ts = ls + rs + node.data;
      return ts;
  }

//Is A Binary Search Tree
/*
You are required to check if the tree is a Binary Search Tree (BST) as well. 
In a BST every node has a value greater than all nodes on it's left side and smaller value than all node on it's right side.

Expectation: We expect that if we pass the root node to the isBST(node) function, 
             we will get true if the tree is a BST and we will get false if it is not a BST.
Faith: A tree is said to be a binary search tree if it is a binary search tree at its root. 
       What does this mean? It means that the value inside the left child of the root node should be less than the value of the root node 
       and the value inside the right child of the root node should be greater than the value inside the root node. 
       This is not sufficient to make a tree a binary search tree. 
       It will be a BST if its left subtree and right subtree both are Binary Search Trees too.

So, we have a faith that when we call the function isBST(node) for the left child and right child of any node, 
it will tell us whether the left/right sub-tree is a BST or not. 
To keep it simple, we say that a binary tree is a BST as a node only when the data of the node is greater than its left child's data 
and less than its right child's data. 
But a binary tree is a BST as a tree when it is a BST as a node and its left and right subtrees are also BSTs. 
There is one more thing that we would like to add in our faith. What is that? 
We have faith that we will also get the minimum value and the maximum value in a sub-tree when we make a recursive call. 
What do we need the minimum and maximum value in a sub-tree for?
Let us say we want to find whether the above tree is a BST or not. 
For that, we have already made recursive calls to the left subtree and the right subtree of the root node (50). 
We know that the given tree will be a BST if the values in the left subtree are smaller than the value in the root node i.e. 50. 
So, we find the maximum value in the left sub-tree. 
If the root node value is greater than the max value in the left sub-tree then definitely it will be greater than all the values in the left sub-tree. 
Similarly, the value in the root node should be less than the value in the right subtree. 
So, if the value in the root node is less than the minimum value in the right subtree then definitely it will be less than all the values in the right subtree. 
This is why we are finding the maximum and minimum value for every sub-tree in the recursive calls. 
Still, we have something left. 
Why are we finding the minimum value in the left subtree if we only have to compare the root node to the max value for the left sub-tree? 
Similarly, why are we finding the max value in the right subtree if we are only using the min value? 
So, the minimum value in the left subtree a
nd the maximum value in the right subtree is calculated so that we can easily find the min/max value of the next node. 
Conclusion: Our faith is that recursion will tell us whether the subtree of a tree is a BST or not 
            and also give us the min and max values of that sub-tree.
Relation: So, the relation should be clear by now. We will check whether a tree is a BST as a node (you already know what this means) 
          and we will apply our faith in recursion to check whether the left subtree and right subtree are BST or not.

Time Complexity:
The time complexity for this solution is O(n) as we have visited every node in the binary tree to check whether its sub-tree is a BST or not.
Space Complexity:
The space complexity for the above code is O(1) as we have not used any extra data structure. 
If we consider the recursion space then the space complexity will be O(log2n) because this is the max height of the stack at any time during the recursion.
This is equal to the height of the tree.
*/
 static class BSTPair {
     boolean isBST;
     int min;
     int max;
 }
 
 public static BSTPair checkBST(Node node){
     if(node == null){
         BSTPair bp = new BSTPair();
         bp.isBST = true;
         bp.min = Integer.MAX_VALUE;
         bp.max = Integer.MIN_VALUE;
         return bp;
     }
     BSTPair lp = checkBST(node.left);
     BSTPair rp = checkBST(node.right);
     //work in post order
     BSTPair mp = new BSTPair();
     mp.isBST = (lp.isBST == true) && (rp.isBST == true) && (node.data >= lp.max && node.data <= rp.min);
     mp.min = Math.min(node.data, Math.min(lp.min, rp.min));
     mp.max = Math.max(node.data, Math.max(lp.max, rp.max));
     
     return mp;
 }

//











