package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Fancy {

  private static final long mod = 1_000_000_007L;

  private final List<Long> normalizedValues;
  private long globalMultiplier;
  private long globalIncrement;

  public Fancy() {
    this.normalizedValues = new ArrayList<>();
    this.globalMultiplier = 1L;
    this.globalIncrement = 0L;
  }

  public void append(int val) {
    long inverseGlobalMultiplier = modularPower(globalMultiplier, mod - 2);
    long normalizedValue = modulo(val - globalIncrement) * inverseGlobalMultiplier % mod;
    normalizedValues.add(normalizedValue);
  }

  public void addAll(int inc) {
    globalIncrement = (globalIncrement + inc) % mod;
  }

  public void multAll(int m) {
    globalMultiplier = (globalMultiplier * m) % mod;
    globalIncrement = (globalIncrement * m) % mod;
  }

  public int getIndex(int idx) {
    if (idx < 0 || idx >= normalizedValues.size()) {
      return -1;
    }

    long normalizedValue = normalizedValues.get(idx);
    long currentValue = (normalizedValue * globalMultiplier + globalIncrement) % mod;

    return (int) currentValue;
  }

  private long modularPower(long base, long exponent) {
    long result = 1L;
    long currentBase = base % mod;
    long currentExponent = exponent;

    while (currentExponent > 0) {
      if ((currentExponent & 1L) == 1L) {
        result = (result * currentBase) % mod;
      }

      currentBase = (currentBase * currentBase) % mod;
      currentExponent >>= 1;
    }

    return result;
  }

  private long modulo(long value) {
    long normalizedValue = value % mod;
    return normalizedValue < 0 ? normalizedValue + mod : normalizedValue;
  }

  static void main() {
    Fancy fancy = new Fancy();

    fancy.append(2);
    fancy.addAll(3);
    fancy.append(7);
    fancy.multAll(2);
    System.out.println(fancy.getIndex(0)); // 10

    fancy.addAll(3);
    fancy.append(10);
    fancy.multAll(2);
    System.out.println(fancy.getIndex(0)); // 26
    System.out.println(fancy.getIndex(1)); // 34
    System.out.println(fancy.getIndex(2)); // 20
  }
}

/**
 * Your Fancy object will be instantiated and called as such: Fancy obj = new Fancy();
 * obj.append(val); obj.addAll(inc); obj.multAll(m); int param_4 = obj.getIndex(idx);
 */
