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











