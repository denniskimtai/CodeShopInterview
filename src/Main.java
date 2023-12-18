import java.util.*;

//Question one
public class Main {

    //Question one
    private static String reverseString(String s){
        String new_str = "";

        //Input String s check constraints
        if(s.isEmpty() || s.length() > 104){
            return "Invalid input string. String should be between 1 and 104 characters.";
        }
        //Validate input string s contains English letters (upper-case and lower-case), digits, and spaces ' '
        if(!s.matches("[a-zA-Z0-9 ]+")){
            return "Invalid input string. String should only contain upper-case, lower-case, digits and spaces.";
        }

        // Split the input string by spaces
        String[] words = s.trim().split("\\s+");

        // build the reversed string
        // Iterate through the words in reverse order and append to the StringBuilder
        for (int i = words.length - 1; i >= 0; i--) {
            new_str += words[i];
            if (i > 0) {
                new_str += " ";
            }
        }
        return new_str;
    }

    //Question Two
    public static int[] processMatrix(int[][] matrix) {
        int n = matrix.length;
        int diagonalSum = 0;
        int rowsWithRepeat = 0;
        int columnsWithRepeat = 0;

        // Check if the input matrix is a square matrix
        for (int[] ints : matrix) {
            if (ints.length != n) {
                throw new IllegalArgumentException("Input matrix is not a square matrix");
            }
        }

        // 1. Sum of the values on the main diagonal
        for (int i = 0; i < n; i++) {
            diagonalSum += matrix[i][i];
        }

        // 2. Number of rows with repeated elements
        for (int i = 0; i < n; i++) {
            Set<Integer> rowSet = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (!rowSet.add(matrix[i][j])) {
                    // If repeated element found in the row, increment rowsWithRepeat and break
                    rowsWithRepeat++;
                    break;
                }
            }
        }

        // 3. Number of columns with repeated elements
        for (int j = 0; j < n; j++) {
            Set<Integer> columnSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (!columnSet.add(matrix[i][j])) {
                    // If repeated element found in the column, increment columnsWithRepeat and break
                    columnsWithRepeat++;
                    break;
                }
            }
        }
        return new int[]{diagonalSum, rowsWithRepeat, columnsWithRepeat};
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        //Check constraints
        if(candidates.length < 1 || candidates.length > 30){
            throw new IllegalArgumentException("Candidates length should be between 1 and 30");
        }

        if(target < 1 || target > 500){
            throw new IllegalArgumentException("Target should be between 1 and 500");
        }

        for(int i = 0; i<candidates.length; i++){
            if(candidates[i]<1 || candidates[i]>200){
                throw new IllegalArgumentException("Candidates values should be between 1 and 200");
            }
        }

        // Sort the candidates to handle duplicates efficiently
        Arrays.sort(candidates);

        // Start generating combinations from index 0
        generateCombinations(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    // Recursive function to generate combinations
    private static void generateCombinations(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        // Base case: if target is 0, add the current combination to the result
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // Iterate through candidates, starting from the specified index
        for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
            // Add the current candidate to the combination
            currentCombination.add(candidates[i]);

            // Recursively generate combinations with the updated remaining target and the same index
            generateCombinations(candidates, target - candidates[i], i, currentCombination, result);

            // Backtrack: remove the last added candidate for the next iteration
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // Sort intervals based on end points
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 1; // Count of non-overlapping intervals
        int end = intervals[0][1]; // Current end point

        for (int i = 1; i < intervals.length; i++) {
            // If the start point is greater than or equal to the current end point, it's non-overlapping
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }

        // The minimum number of intervals to remove is the total number of intervals minus non-overlapping intervals
        return intervals.length - count;
    }

    public static void main(String[] args) {
        //Test cases for question one
        System.out.println("Question One test cases results");
        String s1 = "the sky is blue";
        System.out.println("Input: s = \"" + s1 + "\"" +
                "\nOutput: \"" + reverseString(s1) + "\""); // Output: "blue is sky the"

        String s2 = "  hello world  ";
        System.out.println("Input: s = \"" + s2 + "\"" +
                "\nOutput: \"" + reverseString(s2) + "\"");  // Output: "world hello"

        String s3 = "a good   example";
        System.out.println("Input: s = \"" + s3 + "\"" +
                "\nOutput: \"" + reverseString(s3) + "\"");  // Output: "example good a"

        String s4 = "  Bob    Loves  Alice   ";
        System.out.println("Input: s = \"" + s4 + "\"" +
                "\nOutput: \"" + reverseString(s4) + "\"");  // Output: "Alice Loves Bob"

        String s5 = "Alice does not even like bob";
        System.out.println("Input: s = \"" + s5 + "\"" +
                "\nOutput: \"" + reverseString(s5) + "\""); // Output: "bob like even not does Alice"

        //Test cases for Question Two
        System.out.println("Question Two test cases results");
        int[][] matrix1 = {
                {1, 2, 3, 4},
                {2, 1, 4, 3},
                {3, 4, 1, 2},
                {4, 3, 2, 1}
        };
        System.out.println(Arrays.toString(processMatrix(matrix1)));

        // Example 2
        int[][] matrix2 = {
                {2, 2, 2, 2},
                {2, 3, 2, 3},
                {2, 2, 2, 3},
                {2, 2, 2, 2}
        };
        System.out.println(Arrays.toString(processMatrix(matrix2)));

        //Test cases for question Three
        System.out.println("Question Three test cases results");
        int[] candidates1 = {2,3,5};
        int target1 = 8;
        System.out.println("Output: " + combinationSum(candidates1, target1));

        int[] candidates2 = {2,3,6,7};
        int target2 = 7;
        System.out.println("Output: " + combinationSum(candidates2, target2));

        //Test cases for question Four
        System.out.println("Question Four test cases results");

        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(intervals1)); // Output: 1

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals2)); // Output: 2

        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println(eraseOverlapIntervals(intervals3)); // Output: 0
    }
}
