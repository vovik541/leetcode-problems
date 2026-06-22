package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxNumberOfBalloons(String text) {
    Map<Character, Integer> balloonMap =
        new HashMap() {
          {
            put('b', 0);
            put('a', 0);
            put('l', 0);
            put('o', 0);
            put('n', 0);
          }
        };

    for (char c : text.toCharArray()) {
      if (balloonMap.containsKey(c)) {
        balloonMap.put(c, balloonMap.get(c) + 1);
      }
    }

    int maxBalloons = Integer.MAX_VALUE;

    for (Map.Entry<Character, Integer> balloonEntry : balloonMap.entrySet()) {
      if (balloonEntry.getKey() == 'l' || balloonEntry.getKey() == 'o')
        maxBalloons = Math.min(maxBalloons, balloonEntry.getValue() / 2);
      ;
      maxBalloons = Math.min(maxBalloons, balloonEntry.getValue());
    }

    return maxBalloons;
  }

  static void main() {
    System.out.println(new Solution().maxNumberOfBalloons("loonbalxballpoon")); // 2
  }
}
