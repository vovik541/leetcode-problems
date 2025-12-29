package medium.pyramidTransitionMatrix;

import java.util.*;

//simple backtracking + memoization
class Solution {
    private Map<String, List<Character>> rules = new HashMap<>();
    private Set<String> failed = new HashSet<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.pyramidTransition("BCD", List.of("BCC", "CDE", "CEA", "FFF")));
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String s : allowed) {
            String key = s.substring(0, 2);
            char top = s.charAt(2);
            rules.computeIfAbsent(key, k -> new ArrayList<>()).add(top);
        }

        return dfs(bottom);
    }

    private boolean dfs(String row) {
        if (row.length() == 1) {
            return true;
        }

        if (failed.contains(row)) {
            return false;
        }

        boolean result = buildNextLevel(row, 0, new StringBuilder());
        if (!result) {
            failed.add(row);
        }
        return result;
    }

    private boolean buildNextLevel(String row, int index, StringBuilder next) {
        if (index == row.length() - 1) {
            return dfs(next.toString());
        }

        String pair = row.substring(index, index + 2);
        if (!rules.containsKey(pair)) {
            return false;
        }

        for (char c : rules.get(pair)) {
            next.append(c);
            if (buildNextLevel(row, index + 1, next)) {
                return true;
            }
            next.deleteCharAt(next.length() - 1);
        }

        return false;
    }
}