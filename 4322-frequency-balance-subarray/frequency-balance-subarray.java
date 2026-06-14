class Solution {
    public int getLength(int[] nums) {
        var n = nums.length;
        var longest = 1;

        for (var left = 0; left < n; left++) {
            var frequencies = new java.util.HashMap<Integer, Integer>();
            var valuesAtFrequency = new int[n + 1];
            var distinct = 0;
            var maxFrequency = 0;

            for (var right = left; right < n; right++) {
                var value = nums[right];
                var oldFrequency = frequencies.getOrDefault(value, 0);

                if (oldFrequency > 0) valuesAtFrequency[oldFrequency]--;
                else distinct++;

                var newFrequency = oldFrequency + 1;

                frequencies.put(value, newFrequency);
                valuesAtFrequency[newFrequency]++;

                maxFrequency = Math.max(maxFrequency, newFrequency);

                var length = right - left + 1;

                if (distinct == 1) {
                    longest = Math.max(longest, length);
                    continue;
                }

                if (maxFrequency % 2 != 0) continue;

                var halfFrequency = maxFrequency / 2;
                var validFrequencyCount =
                        valuesAtFrequency[maxFrequency]
                        + valuesAtFrequency[halfFrequency];

                if (valuesAtFrequency[halfFrequency] > 0
                        && validFrequencyCount == distinct) longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}