
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();

        int i = 0, j = 0;

        while(j < nums.length){

            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[j]){
                dq.pollLast();
            }

            dq.offerLast(j);

            if(j - i + 1 == k){

                ans.add(nums[dq.peekFirst()]);

                if(!dq.isEmpty() && dq.peekFirst() == i){
                    dq.pollFirst();
                }

                i++;
            }

            j++;
        }

        int[] res = new int[ans.size()];
        for(int x = 0; x < ans.size(); x++){
            res[x] = ans.get(x);
        }

        return res;
    }
}