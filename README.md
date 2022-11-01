# Quicksort-w-Pair
```sh
This assignments task was to follow the loop invariant that is given as:
     *  Return a Pair of indices, such that 
     *  Pair.left <= Pair.right, and 
     *  for p <= i < Pair.left, array[i] < array[Pair.left], and
     *  for Pair.left <= i <= Pair.right, array[i] == array[Pair.left], and 
     *  for Pair.right < i < q, array[Pair.right] < array[i]     
     *   
     *   @param array the array of values
     *   @param p the first index in the array fragment of interest
     *   @param q one past the last index in the array fragment of interest
     
     
        Loop invariants:
        1) All values in array[p .. pair.left-1] are less than array[Pair.left]
        2) All values in array[pair.left .. pair.right] are equal
        3) All values in array[pair.right+1 .. i] are greater than array[Pair.right]
```

The goal is to use bring the pivot values between the 2 pairs and sort them using an algorithm that runs in O(n). This is a faster variation than a traditional quicksort algorithm. The test cases that are written in main indicates that are algorithm works.
