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


  
