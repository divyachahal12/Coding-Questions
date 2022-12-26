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


//
