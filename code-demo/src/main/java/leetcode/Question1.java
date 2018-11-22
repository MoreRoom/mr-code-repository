package leetcode;

public class Question1 {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2];
        final int length = nums.length;
        for (int i = 0; i < length; i++) {
            int source = nums[i];
            for (int j = (i + 1); j < length; j++) {
                if (source + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

}
