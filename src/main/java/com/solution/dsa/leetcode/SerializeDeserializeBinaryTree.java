package com.solution.dsa.leetcode;

import com.solution.dsa.leetcode.ds.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A - Leaf
 * B - Left Skewed
 * C - Right Skewed
 */
public class SerializeDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder serializedStr = new StringBuilder();
        serializeInternal(root, serializedStr);
        return serializedStr.toString();
    }

    private void serializeInternal(TreeNode root, StringBuilder serializedStr) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            serializedStr.append("A");
        } else if (root.right == null) {
            serializedStr.append("B");
        } else if (root.left == null) {
            serializedStr.append("C");
        }
        serializedStr.append(root.val).append(",");

        serializeInternal(root.left, serializedStr);
        serializeInternal(root.right, serializedStr);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeInternal(queue);
    }

    private TreeNode deserializeInternal(Queue<String> data) {
        if (data.isEmpty()) return null;

        TreeNode root = new TreeNode();
        String top = data.poll();
        if (top.charAt(0) == 'A') {
            root.val = Integer.parseInt(top.substring(1));
        } else if ((top.charAt(0) == 'B')) {
            root.val = Integer.parseInt(top.substring(1));
            root.left = deserializeInternal(data);
        } else if (top.charAt(0) == 'C') {
            root.val = Integer.parseInt(top.substring(1));
            root.right = deserializeInternal(data);
        } else {
            root.val = Integer.parseInt(top);
            root.left = deserializeInternal(data);
            root.right = deserializeInternal(data);
        }
        return root;
    }

    public static void main(String[] args) {
        {
            TreeNode root = new TreeNode(1,
                    new TreeNode(2),
                    new TreeNode(3,
                            new TreeNode(4,
                                    new TreeNode(6),
                                    new TreeNode(7)),
                            new TreeNode(5)));
            SerializeDeserializeBinaryTree serializer = new SerializeDeserializeBinaryTree();
            String serializedStr = serializer.serialize(root);
            System.out.println(serializedStr);
            System.out.println(serializer.deserialize(serializedStr));
        }
        {
            SerializeDeserializeBinaryTree serializer = new SerializeDeserializeBinaryTree();
            String serializedStr = serializer.serialize(null);
            System.out.println(serializedStr);
            System.out.println(serializer.deserialize(serializedStr));
        }
        {
            TreeNode root = new TreeNode(1,
                    new TreeNode(2),
                    new TreeNode(3,
                            new TreeNode(4),
                            new TreeNode(5)));

            SerializeDeserializeBinaryTree serializer = new SerializeDeserializeBinaryTree();
            String serializedStr = serializer.serialize(root);
            System.out.println(serializedStr);
            System.out.println(serializer.deserialize(serializedStr));
        }
        {
            TreeNode root = new TreeNode(1,
                    new TreeNode(3,
                            null,
                            new TreeNode(2)),
                    null);
            SerializeDeserializeBinaryTree serializer = new SerializeDeserializeBinaryTree();
            String serializedStr = serializer.serialize(root);
            System.out.println(serializedStr);
            System.out.println(serializer.deserialize(serializedStr));
        }
        {
            TreeNode root = new TreeNode(3,
                    new TreeNode(1),
                    new TreeNode(4,
                            new TreeNode(2),
                            null));
            SerializeDeserializeBinaryTree serializer = new SerializeDeserializeBinaryTree();
            String serializedStr = serializer.serialize(root);
            System.out.println(serializedStr);
            System.out.println(serializer.deserialize(serializedStr));
        }
    }
}
