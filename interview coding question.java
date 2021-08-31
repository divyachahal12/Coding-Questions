// 169. Majority Element
// Given an array nums of size n, return the majority element.

// The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

//DO DRY RUN ON COPY FOR EACH SOLN APPROACH

class Solution {
    public int majorityElement(int[] nums) {
        //brute force O(N2)
        // traverse full array and maintain count for each 
        //     if count > n/2 return element
        
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int count = 0;
            int a = nums[i];
            for(int j = i; j < n; j++){
                if(nums[j] == a){
                    count++;
                }
                if(count > n/2){
                    return a;
                }
            }
        }
        return -1;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        //optimization use hashmap with number and its frequency stored O(nLogn)
      //2-d array cant be used here cause we dont know range of numbers
        
        
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i]) + 1);
            }else{
                map.put(nums[i], 1);
            }
            if(map.get(nums[i]) > n/2){
                return nums[i];
            }
        }
        return -1;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        //optimization Moore's Voting Algo O(n)
        int n = nums.length;
        int count = 0;
        int candidate = 0;
        for(int i = 0; i < n; i++){
            if(count == 0){
                candidate = nums[i];
                
            }
             
            if(nums[i] == candidate)
                count++;
            else 
                count--;
        }
        
          return candidate;
    }
}

public int majorityElement(int[] nums) {
        //by sorting given array O(nLogn)
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n/2];
        
        
    }




// 229. Majority Element II

// Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

// Follow-up: Could you solve the problem in linear time and in O(1) space?
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        //Hashmap soln O(nlogn)
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i]) + 1);
            }else{
                map.put(nums[i], 1);
            }
            if(map.get(nums[i]) > n/3 && !ans.contains(nums[i])){
                ans.add(nums[i]);
            }
        }
        return ans;
    }
}



 



