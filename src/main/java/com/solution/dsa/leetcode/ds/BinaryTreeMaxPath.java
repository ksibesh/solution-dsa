package com.solution.dsa.leetcode.ds;

public class BinaryTreeMaxPath {

    private int globalMax = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        globalMax = Integer.MIN_VALUE;
        logic(root);
        return globalMax;
    }

    private int logic(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(logic(root.left), 0);
        int right = Math.max(logic(root.right), 0);

        int current = root.val + left + right;
        globalMax = Math.max(current, globalMax);

        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        {
            TreeNode root = new TreeNode(1,
                    new TreeNode(2),
                    new TreeNode(3));
            System.out.println(new BinaryTreeMaxPath().maxPathSum(root));
        }
        {
            TreeNode root = new TreeNode(-10,
                    new TreeNode(9),
                    new TreeNode(20,
                            new TreeNode(15),
                            new TreeNode(7)));
            System.out.println(new BinaryTreeMaxPath().maxPathSum(root));
        }
        {
            TreeNode root = new TreeNode(-3);
            System.out.println(new BinaryTreeMaxPath().maxPathSum(root));
        }
        {
            TreeNode root = new TreeNode(2,
                    new TreeNode(-1),
                    null
            );
            System.out.println(new BinaryTreeMaxPath().maxPathSum(root));
        }
    }

}
