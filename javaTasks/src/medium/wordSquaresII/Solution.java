package medium.wordSquaresII;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().wordSquares(new String[]{"able","area","echo","also"}));
    }

    public List<List<String>> wordSquares(String[] words) {
        Map<Integer, List<String>> wordsByFirstAndLastChar = buildWordsByFirstAndLastChar(words);

        List<List<String>> resultTuples = new ArrayList<>();

        for (String topWord : words) {
            for (String bottomWord : words) {
                if (topWord.equals(bottomWord)) {
                    continue;
                }

                char requiredLeftFirstChar = topWord.charAt(0);
                char requiredLeftLastChar = bottomWord.charAt(0);

                char requiredRightFirstChar = topWord.charAt(3);
                char requiredRightLastChar = bottomWord.charAt(3);

                List<String> leftCandidates =
                        wordsByFirstAndLastChar.getOrDefault(makeKey(requiredLeftFirstChar, requiredLeftLastChar), List.of());
                List<String> rightCandidates =
                        wordsByFirstAndLastChar.getOrDefault(makeKey(requiredRightFirstChar, requiredRightLastChar), List.of());

                for (String leftWord : leftCandidates) {
                    if (leftWord.equals(topWord) || leftWord.equals(bottomWord)) {
                        continue;
                    }

                    for (String rightWord : rightCandidates) {
                        if (rightWord.equals(topWord) || rightWord.equals(bottomWord) || rightWord.equals(leftWord)) {
                            continue;
                        }
                        resultTuples.add(List.of(topWord, leftWord, rightWord, bottomWord));
                    }
                }
            }
        }

        resultTuples.sort(lexicographicTupleComparator());
        return resultTuples;
    }

    private Map<Integer, List<String>> buildWordsByFirstAndLastChar(String[] words) {
        Map<Integer, List<String>> wordsByFirstAndLastChar = new HashMap<>();
        for (String word : words) {
            int key = makeKey(word.charAt(0), word.charAt(3));
            wordsByFirstAndLastChar.computeIfAbsent(key, ignored -> new ArrayList<>()).add(word);
        }
        return wordsByFirstAndLastChar;
    }

    private int makeKey(char firstChar, char lastChar) {
        return (firstChar << 16) | lastChar;
    }

    private Comparator<List<String>> lexicographicTupleComparator() {
        return (tupleA, tupleB) -> {
            for (int index = 0; index < 4; index++) {
                int compare = tupleA.get(index).compareTo(tupleB.get(index));
                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        };
    }
}
