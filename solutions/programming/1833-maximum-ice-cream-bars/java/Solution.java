package dev.vkh.solutions;

public class Solution {

  public static int maxIceCream(int[] costs, int coins) {
    int maxCost = 0;

    for (int cost : costs) {
      maxCost = Math.max(maxCost, cost);
    }

    int[] costFrequency = new int[maxCost + 1];

    for (int cost : costs) {
      costFrequency[cost]++;
    }

    int boughtBars = 0;

    for (int cost = 1; cost <= maxCost; cost++) {
      if (costFrequency[cost] == 0) {
        continue;
      }

      int affordableCount = coins / cost;
      int barsToBuy = Math.min(costFrequency[cost], affordableCount);

      boughtBars += barsToBuy;
      coins -= barsToBuy * cost;

      if (coins < cost) {
        break;
      }
    }

    return boughtBars;
  }

  static void main() {
    int[] costs = {1, 3, 2, 4, 1};
    int coins1 = 7;
    System.out.println(maxIceCream(costs, coins1)); // Output: 4
  }
}
