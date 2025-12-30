package com.solution.dsa.leetcode;

/**
 * https://leetcode.com/problems/word-search/description/?envType=problem-list-v2&envId=depth-first-search
 */
public class WordSearch {
    record Index(int row, int col) {
    }

    public boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (logic(board, word, r, c, 0)) return true;
            }
        }
        return false;
    }

    public boolean logic(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(index))
            return false;

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = logic(board, word, row - 1, col, index + 1)
                || logic(board, word, row + 1, col, index + 1)
                || logic(board, word, row, col - 1, index + 1)
                || logic(board, word, row, col + 1, index + 1);

        board[row][col] = temp;
        return found;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        System.out.println(new WordSearch().exist(board, word));
    }
}
