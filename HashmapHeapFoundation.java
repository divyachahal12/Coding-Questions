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
