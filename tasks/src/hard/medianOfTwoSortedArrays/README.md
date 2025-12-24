# Median of Two Sorted Arrays

**Difficulty:** Hard  
**Topics:** Array, Binary Search, Divide and Conquer

---

## Description

Given two sorted arrays `nums1` and `nums2` of sizes `m` and `n` respectively, return the **median** of the two sorted arrays.

The overall run time complexity should be **O(log(m + n))**.

---

## Input

- `nums1`: sorted integer array of length `m`
- `nums2`: sorted integer array of length `n`

---

## Output

- A `double` representing the median of the combined sorted array

---

## Constraints

- `0 ≤ m, n ≤ 1000`
- `1 ≤ m + n ≤ 2000`
- `-10^6 ≤ nums1[i], nums2[i] ≤ 10^6`
- Both arrays are sorted in non-decreasing order

---

## Approach

The arrays are **not merged**.

The solution performs a **binary search on the smaller array** to find a valid partition such that:

- The left partitions contain exactly half of the total elements
- All elements in the left partitions are less than or equal to all elements in the right partitions

Once the correct partition is found:
- If the total number of elements is **odd**, the median is the maximum element of the left partitions
- If **even**, the median is the average of the maximum element of the left partitions and the minimum element of the right partitions

---

## Complexity

- **Time Complexity:** `O(log(min(m, n)))`
- **Space Complexity:** `O(1)`

---

## Examples

### Example 1
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000

### Example 2
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000

---

## Notes

- Binary search is always performed on the smaller array
- No extra memory or array merging is used
- This is the optimal solution expected in technical interviews