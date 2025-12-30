package com.solution.dsa.leetcode;

import com.solution.dsa.leetcode.ds.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/?envType=problem-list-v2&envId=depth-first-search
 */
public class RecoverBST {
    public void recoverTree(TreeNode root) {
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);
        List<TreeNode> faultyNodes = new ArrayList<>();
        logic(root, prev, faultyNodes);
        swap(faultyNodes);
    }

    private void swap(List<TreeNode> faultyNodes) {
        int temp = faultyNodes.get(0).val;
        faultyNodes.get(0).val = faultyNodes.get(1).val;
        faultyNodes.get(1).val = temp;
    }

    private TreeNode logic(TreeNode root, TreeNode prev, List<TreeNode> faultyNodes) {
        if (root == null) return prev;

        prev = logic(root.left, prev, faultyNodes);
        // current
        if (faultyNodes.isEmpty() && prev.val > root.val) {
            faultyNodes.add(prev);
            faultyNodes.add(root);
        } else if (!faultyNodes.isEmpty() && prev.val > root.val) {
            faultyNodes.set(1, root);
        }
        prev = root;
        prev = logic(root.right, prev, faultyNodes);
        return prev;
    }

    public static void main(String[] args) {
        {
            TreeNode root = new TreeNode(1,
                    new TreeNode(3,
                            null,
                            new TreeNode(2)),
                    null);
            new RecoverBST().recoverTree(root);
            System.out.println(root);
        }
        {
            TreeNode root = new TreeNode(3,
                    new TreeNode(1),
                    new TreeNode(4,
                            new TreeNode(2),
                            null));
            new RecoverBST().recoverTree(root);
            System.out.println(root);
        }
    }
}
