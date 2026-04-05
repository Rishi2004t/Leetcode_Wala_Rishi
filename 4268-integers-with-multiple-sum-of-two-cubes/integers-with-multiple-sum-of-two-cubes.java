class Solution {
    public List<Integer> findGoodIntegers(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = (int)Math.cbrt(n);
        for(int a=  1; a <= max; a++){
            for(int b = a; b <= max; b++){
                int sum = a*a*a + b*b*b;
                if(sum > n) break;
                map.put(sum ,  map.getOrDefault(sum, 0) + 1);
            }
        }
        List<Integer>  ans = new ArrayList<>();
        for(int key :  map.keySet()){
            if(map.get(key) >= 2){
                ans.add(key);
            }
        }
        Collections.sort(ans);
        return ans;

    }
}