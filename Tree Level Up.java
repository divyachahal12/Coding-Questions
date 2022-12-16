//making our tree class
public static class TreeNode(){
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    
    TreeNode(int val){
        this.val = val;
    }
    
    
    //size of tree = number of nodes in tree
    public static int size(TreeNode root){
        // if(root == null)
        //     return 0;
        // int ans = size(root.left) + size(root.right) + 1;
        // return ans;
        
        //is same as below line
        
        return root == null ? 0 :  size(root.left) + size(root.right) + 1;
    }
    
    
    //height or depth of a tree is same, seen from different perspectives
    //by default height is wrt to edge, i.e. no. of edges
    public static int height(TreeNode root){
        // if(root == null)
        //     return -1;
        // int ans =  Math.max(height(root.left), height(root.right))+1;  
        // we have faith that left and right childs will give their max heights, and 
        // total height of tree would be max of left child or right child + 1(for root itself)
        // return ans;
        
        //is same as below line
        return root == null ? -1 :  Math.max(height(root.left), height(root.right))+1;
    }
    
    
    //max value node in tree
    public static int maxValueNode(TreeNode root){
        // if(root == null)
        //     return -(int)1e9;
        // int ans = Math.max(root.val, Math.max(maxValueNode(root.left), maxValueNode(root.right)));
        // return ans;
        
        //is same as below line
        
        return root == null ? -(int)1e9 :  Math.max(root.val, Math.max(maxValueNode(root.left), maxValueNode(root.right)));
    }
    
    //min value node in tree
    public static int minValueNode(TreeNode root){
        // if(root == null)
        //     return (int)1e9;
        // int ans = Math.min(root.val, Math.min(minValueNode(root.left), minValueNode(root.right)));
        // return ans;
        
        //is same as below line
        
        return root == null ? (int)1e9 :  Math.min(root.val, Math.min(minValueNode(root.left), minValueNode(root.right)));
    }
    
    //find if a given data value is present in tree or not
    public static boolean find(TreeNode root, int data){
        if(root == null)
            return false;
        if(root.val == data)
            return true;
        
        return find(root.left, data) || find(root.right, data);// || operator if any one place true is returned then compiler wont check further condition
    }
    
    //Node to Root Path
    public static ArrayList<TreeNode> nodeToRootPath(TreeNode node, int data) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        boolean res = ntr(node, data, ans);
        return ans;
    }
  
    public static boolean ntr(TreeNode node, int data, ArrayList<TreeNode> ans){
        if(node == null){
           return false;
        }

        if( node.val == data){
           ans.add(node);
           return true;
        }

        boolean res = ntr(node.left, data, ans) || ntr(node.right, data, ans);

        if(res){
           ans.add(node);
        }

        return res;

      }

    //Node to Root Path ---> Return type (do dry run)
    public static ArrayList<TreeNode> nodeToRootPath(TreeNode node, int data) {
  
    if(node == null){
        return new ArrayList<>();
    }
    
    if(node.val == data){
        ArrayList<TreeNode> base = new ArrayList<>();
        base.add(node);
        return base;
    }
    
    ArrayList<TreeNode> left = nodeToRootPath(node.left, data);
    if(left.size() != 0){
        left.add(node);
        return left;
    }
    
    ArrayList<TreeNode> right = nodeToRootPath(node.right, data);
    if(right.size() != 0){
        right.add(node);
        return right;
    }
    
    return new ArrayList<>();
    
  }
    
    
  //Root to All Leaf Node Path
  public static void RootToAllLeafLeaffPath(TreeNode root, ArraList<ArrayList<Integer>> ans, ArrayList<Integer> smallAns){
      if(root == null) return;
      
      if(root.left == null && root.right == null){//leaf node
          ArrayList<Integer> base = new ArrayList<>(smallAns);
          base.add(root.val);
          ans.add(base);
          return;
        
      }
      smallAns.add(root.val);
      //left call
      RootToAllLeafLeaffPath(root.left, ans, smallAns);
      //right call
      RootToAllLeafLeaffPath(root.right, ans, smallAns);
      //backtracking from last node
      smallAns.remove(smallAns.size()-1);
  }
    
  public static ArrayList<ArrayList<Integer>> RootToAllLeafLeaffPath(TreeNode root){
      ArraList<ArrayList<Integer>> ans = new ArrayList<>();
      ArrayList<Integer> smallAns = new ArrayList<>();
      
      RootToAllLeafLeaffPath(root, ans, smallAns);//calling above function
      
      return ans;
          
  }

  //All Single Child Parent in Binary Tree
  public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
    if(root == null ||(root.left == null && root.right == null)) return;
    
    if(root.left == null || root.right == null){
        ans.add(root.val);
        
    }
    
    exactlyOneChild(root.left, ans);
    exactlyOneChild(root.right, ans);
  
  }
  
  public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
    ArrayList<Integer> ans = new ArrayList<>();
    exactlyOneChild(root, ans);
    
    return ans;
  }
    
  //Count All Single Child Parent In Binary Tree  
  public static int countExactlyOneChild(TreeNode node) {
      
    if(node == null ||(node.left == null && node.right == null)){
          return 0;
    }
    int leftcount = countExactlyOneChild(node.left);
    int rightcount = countExactlyOneChild(node.right);
    
    int ans = leftcount + rightcount;
    if(node.left == null || node.right == null){
        ans += 1;
    }
    return ans;

  }
    
  
    
    
}












