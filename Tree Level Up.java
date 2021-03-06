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
    public static boolean nodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans){
        if(root == null){
            return false;
        }
        
        //required data node
        if(root.val == data){
            ans.add(root);
            return true;
        }
        
        boolean res = nodeToRootPath( root.left, data, ans) || nodeToRootPath(root.right, data, ans);
        
        //for remaining parent nodes
        if(res){
            ans.add(root);
        }
        
        return ans;
        
    }
    
    //Node to Root Path ---> Return type 
    
    
    

    
    
}












