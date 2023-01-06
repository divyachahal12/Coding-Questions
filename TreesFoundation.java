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

//
