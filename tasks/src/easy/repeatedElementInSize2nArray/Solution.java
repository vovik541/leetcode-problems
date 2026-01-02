package easy.repeatedElementInSize2nArray;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().repeatedNTimes(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
    }

    public int repeatedNTimes(int[] nums) {
        int arrayLength = nums.length;

        for (int index = 0; index < arrayLength - 1; index++) {
            if (nums[index] == nums[index + 1]) {
                return nums[index];
            }
            if (index + 2 < arrayLength && nums[index] == nums[index + 2]) {
                return nums[index];
            }
            if (index + 3 < arrayLength && nums[index] == nums[index + 3]) {
                return nums[index];
            }
        }

        throw new IllegalStateException("No repeated element found");
    }
}
