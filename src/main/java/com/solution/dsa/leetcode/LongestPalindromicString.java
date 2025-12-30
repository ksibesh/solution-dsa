package com.solution.dsa.leetcode;

public class LongestPalindromicString {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return null;
        return checkForLongestPalindrome(s, new String[s.length()][s.length()], 0, s.length() - 1);
    }

    private String checkForLongestPalindrome(String str, String[][] memory, int start, int end) {
        if (memory[start][end] != null) return memory[start][end];

        if (checkPalindrome(str, start, end)) {
            memory[start][end] = str.substring(start, end + 1);
            return memory[start][end];
        }

        String left = checkForLongestPalindrome(str, memory, start, end - 1);
        String right = checkForLongestPalindrome(str, memory, start + 1, end);

        if (left.length() >= right.length()) return left;
        else return right;
    }

    private boolean checkPalindrome(String str, int start, int end) {
        for (; start < end; start++, end--) {
            if (str.charAt(start) != str.charAt(end)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        {
            System.out.println(new LongestPalindromicString().longestPalindrome("babad"));
        }
        {
            System.out.println(new LongestPalindromicString().longestPalindrome("cbbd"));
        }
    }
}
