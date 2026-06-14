class Solution {
    private static class MultiSet {
        private final TreeMap<Integer, Integer> values =
                new TreeMap<>();

        private long sum;

        public void add(int value) {
            values.merge(value, 1, Integer::sum);
            sum += value;
        }

        public void remove(int value) {
            var count = values.get(value);

            if (count == 1) values.remove(value);
            else values.put(value, count - 1);

            sum -= value;
        }

        public boolean contains(int value) {
            return values.containsKey(value);
        }

        public boolean isEmpty() {
            return values.isEmpty();
        }

        public int first() {
            return values.firstKey();
        }

        public int last() {
            return values.lastKey();
        }

        public long sum() {
            return sum;
        }
    }

    public long maxSum(int[] nums, int k) {
        var n = nums.length;
        var answer = Long.MIN_VALUE;

        for (var left = 0; left < n; left++) {
            var smallInside = new MultiSet();
            var restInside = new MultiSet();
            var largeOutside = new MultiSet();
            var restOutside = new MultiSet();

            for (var value : nums) restOutside.add(value);

            var swaps = 0;
            var subarraySum = 0L;

            for (var right = left; right < n; right++) {
                var value = nums[right];

                if (largeOutside.contains(value)) {
                    largeOutside.remove(value);

                    if (!restOutside.isEmpty()) {
                        var replacement = restOutside.last();

                        restOutside.remove(replacement);
                        largeOutside.add(replacement);
                    } else {
                        var removed = smallInside.last();

                        smallInside.remove(removed);
                        restInside.add(removed);
                        swaps--;
                    }
                } else restOutside.remove(value);

                if (swaps == 0) restInside.add(value);
                else if (value < smallInside.last()) {
                    var removed = smallInside.last();

                    smallInside.remove(removed);
                    restInside.add(removed);
                    smallInside.add(value);
                } else restInside.add(value);

                while (swaps > 0 && largeOutside.first() <= smallInside.last()) {
                    var outsideValue = largeOutside.first();
                    var insideValue = smallInside.last();

                    largeOutside.remove(outsideValue);
                    restOutside.add(outsideValue);

                    smallInside.remove(insideValue);
                    restInside.add(insideValue);

                    swaps--;
                }

                while (swaps < k && !restOutside.isEmpty() && !restInside.isEmpty() && restOutside.last() > restInside.first()) {
                    var outsideValue = restOutside.last();
                    var insideValue = restInside.first();

                    restOutside.remove(outsideValue);
                    largeOutside.add(outsideValue);

                    restInside.remove(insideValue);
                    smallInside.add(insideValue);

                    swaps++;
                }

                subarraySum += value;

                var currentSum = subarraySum + largeOutside.sum() - smallInside.sum();

                answer = Math.max(answer, currentSum);
            }
        }

        return answer;
    }
}