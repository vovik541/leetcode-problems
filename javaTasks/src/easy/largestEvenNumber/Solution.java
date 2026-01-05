package easy.largestEvenNumber;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().largestEven("12512"));
    }

    public String largestEven(String s) {
        for (int i = 1; i <= s.length(); i++) {
            if (Character.getNumericValue(s.charAt(s.length() - i)) % 2 == 0) {
                return s.substring(0, s.length() + 1 - i);
            }
        }

        return "";
    }
}
