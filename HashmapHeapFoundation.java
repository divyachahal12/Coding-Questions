/*
A hashmap is a data structure which has an amazing property that most of the operations which we perform on it are done in O(1) time complexity. 
The data is stored in a hash-map in the form of key-value pairs.

Set<String> keys = hm.keySet(); for allkeys of Hashmap used for loop
*/

//Highest Frequency Character
     public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i = 0; i < str.length(); i++){
            if(hm.containsKey(str.charAt(i))){
                int oldFreq = hm.get(str.charAt(i));
                int newFreq = oldFreq + 1;
                hm.put(str.charAt(i), newFreq);
            }else{
                hm.put(str.charAt(i), 1);
            }
        }
        char mostFreqChar = str.charAt(0);        
        for(Character key: hm.keySet()){
            if(hm.get(key) > hm.get(mostFreqChar)){
                mostFreqChar = key;
            }
        }
        System.out.println(mostFreqChar);
    }

//Get Common Elements - 1
/*
You are required to print all elements of a2 which are also present in a1 (in order of their occurence in a2). 
Make sure to not print duplicates (a2 may have same value present many times).
Note -> Don't assume the arrays to be sorted.
*/
public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n1 = sc.nextInt();
    int[] a1 = new int[n1];
    for(int i = 0; i < n1; i++){
        a1[i] = sc.nextInt();
    }
    int n2 = sc.nextInt();
    int[] a2 = new int[n2];
    for(int i = 0; i < n2; i++){
        a2[i] = sc.nextInt();
    }
    HashMap<Integer, Integer> hm = new HashMap<>();
    for(int val: a1){
        if(hm.containsKey(val)){
//             int of = hm.get(val);
//             int nf = of + 1;
//             hm.put(val, nf);
          hm.put(val, hm.get(val) + 1);
        }else{
            hm.put(val, 1);
        }
    }
    for(int val: a2){
        if(hm.containsKey(val)){
            System.out.println(val);
            hm.remove(val);
        }
    }
 }


//Get Common Elements - 2
/*
You are required to find the intersection of a1 and a2. To get an idea check the example below:
 
if a1 -> 1 1 2 2 2 3 5
and a2 -> 1 1 1 2 2 4 5
intersection is -> 1 1 2 2 5

Note -> Don't assume the arrays to be sorted.
*/
public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n1 = sc.nextInt();
    int[] a1 = new int[n1];
    for(int i = 0; i < n1; i++){
        a1[i] = sc.nextInt();
    }
    int n2 = sc.nextInt();
    int[] a2 = new int[n2];
    for(int i = 0; i < n2; i++){
        a2[i] = sc.nextInt();
    }
    HashMap<Integer, Integer> fmap = new HashMap<>();
    for(int val: a1){
        if(fmap.containsKey(val)){
            fmap.put(val, fmap.get(val) + 1);
        }else{
            fmap.put(val, 1);
        }
    }
    for(int val: a2){
        if(fmap.containsKey(val) && fmap.get(val) > 0){
            System.out.println(val);
            fmap.put(val, fmap.get(val) - 1);
        } 
    }
 }


//Longest Consecutive Sequence Of Elements
/*
You are required to print the longest sequence of consecutive elements in the array (ignoring duplicates).

Note -> In case there are two sequences of equal length (and they are also the longest), then print the one for which the starting point of which occurs first in the array.

This "true" represents that its corresponding element is the starting element of a desired sequence.

Now we iterate through the entire loop and check whether a number, 1 less than that element is present in the hashmap or not.
If the element is not present then we do nothing.
For example, if we are at the element 10 of the array, then we check whether the number previous to 10 i.e. 10-1=9, is present in the hashmap or not.

If we find out that the previous number is present in the hashmap, then it could not be the starting of the consecutive sequence. 
Hence we mark that element as "false".
Hence, since 9 is present in the hashmap, therefore 10 is marked as "false".

Had the previous number not been present in the hashmap, the current element of the array would have been left as "true".

Now, the elements marked as "true" are those elements which are actually at the starting index of a desired consecutive sequence.

Now we run a third loop for all the elements and choose those elements which are marked "true".

For every "true" element, we find all its consecutive elements in the hashmap. 
For example, for the element "5", the consecutive sequence becomes 5-6. The size of this sequence is 2. 
This size is stored as "ml" and the starting element is stored as "mval".

Now the next sequence with "1" as the starting element is 1-2-3. 
The size of the sequence is 3. Since this size is greater than "ml" length, therefore, 3 is the new "ml" and the new "mval" is 1.

This goes on until we find the max length out of all the sequences i.e. 5 for the sequence 8-9-10-11-12. 
Hence "ml" is 5 and "mval" is 8.

Now all consecutive elements of the final "mval" till "ml" are printed.

*/
public static void main(String[] args) throws Exception {
   Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[]a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scn.nextInt();
    }
    HashMap < Integer, Boolean> hm = new HashMap();
    for (int val : a) {
      hm.put(val, true);
    }
    for (int val : a) {
      if (hm.containsKey(val - 1)) {
        hm.put(val, false);
      }
    }
    int ml = 0;//maxLength
    int mval = 0;//maxValStaetingPoint
    for (int val : a) {
      if (hm.get(val) == true)
      {
        int tl = 1;//tempLength
        int tval = val;//tempVal
        while (hm.containsKey(tval + tl)) {
          tl++;
        }
        if (tl > ml) {
          ml = tl;
          mval = val;
        }
      }
    }
    for (int i = 0; i < ml; i++)
    {
      System.out.println(mval + i);

    }
 }


//***********************IMPORTANT*******************************************************

//K Largest Elements
/*
You are required to find and print the k largest elements of array in increasing order.
*/

public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int[] arr = new int[n];

      for (int i = 0; i < n; i++) {
         arr[i] = Integer.parseInt(br.readLine());
      }

      int k = Integer.parseInt(br.readLine());
      
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for(int i = 0; i < arr.length; i++){
          if(i < k){
              pq.add(arr[i]);
          }else{
              if(arr[i] > pq.peek()){
                  pq.remove();
                  pq.add(arr[i]);
              }
          }
      }
      
      while(pq.size() > 0){
          System.out.println(pq.remove());
      }
}


//Sort K-sorted Array
/*
The array is nearly sorted. Every element is at-max displaced k spots left or right to it's position in the sorted array. 
Hence it is being called k-sorted array.
4. You are required to sort and print the sorted array.

Note -> You can use at-max k extra space and nlogk time complexity.

Algorithm:
We create a priority queue of size k+1. We insert the first k+1 elements in it.
Now, we remove one element from the priority queue. This will be the smallest element as the removal from the priority queue gives the smallest element or the element of highest priority. Now, we have k elements remaining in the priority queue.
So, we insert the next element in the priority queue and the size again becomes k+1. Now we repeat the same procedure until we cover the entire array.
If the array is complete and some elements are left in the priority queue, we empty the priority queue completely and stop the procedure.

Let us say that we have a funnel. This funnel can filter out the element of least value. 
So, we made a funnel of size k+1=2+1=3 in this case and kept it at the 0th index. 
So, it can accommodate three elements 2,3 and 1. 
Since this funnel can filter out the smallest element, we get element 1 out from this funnel. 
we can apply the same technique till we reach the end of the array. 
We have to keep repeating this procedure until we have nothing left to put into this funnel.
we are in a situation where we do not have any elements left in the array to fill into the funnel. 
So, we will now keep on removing the elements from the funnel till it becomes empty.
Now the funnel is empty and we have also traversed the array completely. So, we get the elements sorted.
So, what is the funnel here? Yes, it is the priority queue that we have used to filter out the element of the smallest value every time.
*/
public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int[] arr = new int[n];

      for (int i = 0; i < n; i++) {
         arr[i] = Integer.parseInt(br.readLine());
      }

      int k = Integer.parseInt(br.readLine());
      
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for(int i = 0; i <= k; i++){
          pq.add(arr[i]);
      }
      for(int i = k+1; i < arr.length; i++){
          System.out.println(pq.remove());
          pq.add(arr[i]);
      }
      while(pq.size() > 0){
          System.out.println(pq.remove());
      }
}

//***********************IMPORTANT*******************************************************

//Median Priority Queue
/*
You are required to complete the code of our MedianPriorityQueue class. The class should mimic the behaviour of a PriorityQueue and give highest priority to median of it's data.
Note -> If there are even number of elements in the MedianPriorityQueue, consider the smaller median as median value.
*/
  public static class MedianPriorityQueue {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianPriorityQueue() {
      left = new PriorityQueue<>(Collections.reverseOrder());
      right = new PriorityQueue<>();
    }

    public void add(int val) {
      if(right.size() > 0 && val > right.peek()){
          right.add(val);
      }else{
          left.add(val);
      }
      
      if(left.size() - right.size() == 2){
          right.add(left.remove());
      }else if(right.size() - left.size() == 2){
        left.add(right.remove());
      }    
    }

    public int remove() {
      if(this.size() == 0){
          System.out.println("Underflow");
          return -1;
      }else if(left.size() >= right.size()){
          return left.remove();
      }else{
          return right.remove();
      }
    }

    public int peek() {
      if(this.size() == 0){
          System.out.println("Underflow");
          return -1;
      }else if(left.size() >= right.size()){
          return left.peek();
      }else{
          return right.peek();
      }
    }

    public int size() {
      return left.size() + right.size();
    }
  }


//***********************IMPORTANT*******************************************************

//MergeK Sorted Lists
/*
1. You are given a list of lists, where each list is sorted.
2. You are required to complete the body of mergeKSortedLists function. The function is expected to merge k sorted lists to create one sorted list.

https://www.pepcoding.com/resources/online-java-foundation/hashmap-and-heap/merge_k_sorted_lists/topic

Approach:
The problem here deals with merging given K sorted Lists. Let's discuss the steps to achieve the output:

We will use the advantage that the given lists are sorted.
So first of all, we pick the first element of each list and add it in a box.
It is for sure that the smallest of these elements present in this box will be,
     the most deserving candidate for the first position in the final list which needs to be sorted.
So we pick the smallest element out of this box and place it at the first position in the list.
And after that we again add an element to the box from the same list, to which the removed (smallest) element belonged.
We repeat this step until the box is completely empty.
And at the end, the final list will have all the elements in the sorted order.

--------------------------------------------------------------------------------------------------
How to Code this?

First of all we will define an array list, rv to store the final result and a Priority Queue, pq (which is basically the box).
Then we add the first element of all the given lists into pq.
Now we run a while loop until the size of pq becomes 0.
After this we remove() from pq and store its data to the rv.
WHY? Because this will be the smallest value, 
     as initially only first values of lists were present in the pq and remove() returns the value of the smallest of these first elements.
Then we place the next element of the list, to which the above removed element belonged, only if that was not the last element of its list.
But HOW do we keep track of the list of the element, which was removed and whether this was the last element or not?

Well for this we create a class Pair.
➢ This Pair class has li, data and di as its data members.

➢ Li stands for list index. As these K sorted lists will be provided in an arraylist of lists. 
  So li will store the index of the li(th) list of arraylist. And therefore helps us to keep track of its position in the arraylist.

➢ Data is the value, which is an element of the li(th) list of the arraylist.

➢ And di represents the index of data in the li(th) list of arraylist.
And to make use of this class; while defining the Priority Queue, we make it of type Pair.
And every time we need, 
we can access any list corresponding to certain data and also check for validation of data position using di to add more pairs to the pq.
But wait! What about getting the smallest value pair while removing from pq?

If we just added the elements of lists to pq, then they would have, 
by default, arranged themselves in rank order (increasing) and returned the smallest value whenever remove() or peek() would have been called.
But now we have Pairs instead of Integers.

So HOW do we achieve that?

Well we can achieve this by implementing Comparable in call Pair.And modifying compareTo( Pair o) function by returning this.data - o.data.

Yes! This works.

And pq gets sorted on the basis of data's value. And Pair with smaller value has more priority as defined by compareTo() function.

Analysis:

Time Complexity:
The time complexity for this approach is O(nlogn) where n is the time to traverse the tree and logn for calling find() function for every node.
Space Complexity:
The space complexity for the function is proportional to the height of the tree due to the recursion stack. (//doubt)

*/

//li --> list index; di --> data index
public class Main {
    public static class Pair implements Comparable<Pair>{
        int li;
        int di;
        int val;
        
        Pair(int li, int di, int val){
            this.li = li;
            this.di = di;
            this.val = val;
        }
        
        public int compareTo(Pair o){
            return this.val - o.val;
        }
    }
   public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
      ArrayList<Integer> rv = new ArrayList<>();

      PriorityQueue<Pair> pq = new PriorityQueue<>();
      for(int i = 0; i < lists.size() ; i++){
          Pair p = new Pair(i, 0, lists.get(i).get(0));
          pq.add(p);
      }
      
      while(pq.size()>0){
          Pair p = pq.remove();
          rv.add(p.val);
          p.di++;
          
          if(p.di < lists.get(p.li).size()){
              p.val = lists.get(p.li).get(p.di);
              pq.add(p);
          }
      }

      return rv;
   }
}


//Write Priority Queue Using Heap
/*

https://www.pepcoding.com/resources/online-java-foundation/hashmap-and-heap/priority_queue_using_heap/topic

Here is the list of functions that you are supposed to complete:
    2.1. add -> Should accept new data.
    2.2. remove -> Should remove and return smallest value, if available or print 
     "Underflow" otherwise and return -1.
     2.3. peek -> Should return smallest value, if available or print "Underflow" 
     otherwise and return -1.
     2.4. size -> Should return the number of elements available.
     
-------------------------------------------------------------------------------    
The most efficient way to code our different functions is the one where add(data) and remove() takes O(log n) and peek() takes constant time. 
So here we will learn about this way.

To achieve this we use a Heap data structure.
Heap is a Binary Tree based data structure with two additional properties. And those properties are:

Heap Order Priority (HOP):
➢ What is it?

This means that the priority of a parent is always greater than its child. And to be specific, the priority of either child is not pre decided.

➢ Why is it?

This property basically helps us to achieve the most efficient time complexity of the peek() function which is constant (O(n)).
Complete Binary Tree (CBT):
➢ What is it?

Supposing that the height of the tree is h, then according to this property, at least h-1 levels of the Heap should be completely filled. 
And the last level should be filled from left to right.

➢ Why is it?

Use of this property again helps us to achieve the most efficient time complexity of add(data) and remove() functions when used together, 
which is O(log n) of each.
It is important that you know, the data structure used for storage by the heap is Array List.
Now you may say that, above, we said Heap is a Binary Tree based data structure. Yes! We said that and this may be a little confusing.
But please pay attention. We said that it's a Binary Tree based data structure and with this we implied on its visualization part.
We can visualize it as a binary tree as well.
But using the view of Binary Tree makes the implementation look easy. Let's say the Array List view is the usual view and Binary Tree has an unusual view.
Keeping the properties of Heap in mind and trying to make the functions efficient, let's get started.

ADD(DATA):
Suppose we have to add 15 to the above tree. We should add this element to the array list such that properties of Heap are not violated.
➢ We start with placing the element at the end of the array list.

➢ At this point, in the Binary Tree representation of this tree, 
  it can be seen that the CBT property is still valid but HOP property has been violated as 15 has more priority than its parent 35.

➢ So to honor the HOP property, it is necessary that we Up-heapify a Binary Tree representation and simultaneously make changes in Array List.

➢ To up-heapify the binary tree, we start with swapping the parent's data with the child's data 

If HOP is still applicable then it means the element with highest priority will be present at index 0 of Array List, 
which can be accessed in the constant time. Making the time complexity for peek() function O(1).
And talking about time complexity of add(data), 
it takes constant time to add the element at the last in an Array List and log n time (because of tree's height) at max to up-heapify the added data.
Making the overall time complexity O(log n).
So to conclude, we can say that because of Heap's CBT property, 
we could use the array list's implementation and because of which we could run the indices formulas.
By which we could access the parent's index and therefore swap the data and up heapify the tree. 
And that's why we could do this in order of O(log n).

REMOVE():
➢ Why didn't we remove this element directly from the first index?
Because doing so will take O(n) time. As after removing the element we will need to shift each element to the left.

➢ Now we make use of down-heapify to honor the HOP property.

➢ So to use down-heapify, if the parent's priority is less than either child, we swap the parent's data with it's child's with maximum priority.
➢ Now we again check, if the new position of 35 honors the HOP property or not.

To get at the peek at any time we just call peek().
It is a function of O(1) that is constant time.
Inside this function, first of all we handle a special case; if the size of the list is 0.
If the size of the list is 0 then we print underflow and return -1.
Otherwise we return the value at the zeroth index (root of the tree basically).

To get at the size of the priority queue at any time we just call size().
It is a function of O(1) that is constant time.
Inside this function, we simply return the size of the arraylist in use.

*/
public static class PriorityQueue {
    ArrayList<Integer> data;

    public PriorityQueue() {
      data = new ArrayList<>();
    }

    public void add(int val) {
      data.add(val);
      upHeapify(data.size() - 1);
      
    }
    private void upHeapify(int i){//index of last child a.k.a. last element added
        if(i == 0){
            return;
        }
         int pi = (i - 1)/2;//pi --> parent index
         if(data.get(i) < data.get(pi)){
             swap(i, pi);
             upHeapify(pi);
         }
    }
    
    private void swap(int i, int j){//i and j are index in arraylist
        int ith = data.get(i);
        int jth = data.get(j);
        data.set(i, jth);//for swapping in arraylist
        data.set(j, ith);
    }

    public int remove() {
      if(this.size() == 0){
          System.out.println("Underflow");
          return -1;
      }
      swap(0, data.size() - 1);
      int val = data.remove(data.size() - 1);//last element in arrayList a.k.a. last leaf node
      downHeapify(0);
      return val;
    }
    
    private void downHeapify(int i){
        //to be in heap root element i.e. at index 0 should have highest priority  which is min value
        int mini = i;//mini --> min value idx
        
        int lci = 2*i + 1;//left child idx
        if(lci < data.size() && data.get(lci) < data.get(mini)){
            mini = lci;
        }
        
        int rci = 2*i + 2;//right child idx
        if(rci < data.size() && data.get(rci) < data.get(mini)){
            mini = rci;
        }
        
        if(mini != i){
            swap(mini, i);
            downHeapify(mini);
        }
    }

    public int peek() {
      if(this.size() == 0){
          System.out.println("Underflow");
          return -1;
      }
      
      return data.get(0);
    }

    public int size() {
      return data.size();
    }
}
