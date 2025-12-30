package com.solution.dsa.graph.example.unweighted;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyListGraph {

    public List<List<Integer>> createUndirectedGraph(int v, int[][] edges) {
        List<List<Integer>> mat = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            mat.add(new LinkedList<>());
        }

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];

            mat.get(v1).add(v2);
            mat.get(v2).add(v1);
        }
        return mat;
    }

    public List<List<Integer>> createDirectedGraph(int v, int[][] edges) {
        List<List<Integer>> mat = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            mat.add(new LinkedList<>());
        }

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];

            mat.get(v1).add(v2);
        }
        return mat;

    }

    public static void main(String[] args) {
        {
            int v = 3;
            int[][] edges = new int[][]{
                    {1, 0},
                    {2, 0},
                    {1, 2}
            };

            List<List<Integer>> mat = new AdjacencyListGraph().createUndirectedGraph(v, edges);
            System.out.println("Undirected graph: ");
            for (int i = 0; i < mat.size(); i++) {
                System.out.print(i + ": ");
                for (Integer integer : mat.get(i)) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        {
            int v = 3;
            int[][] edges = { {1, 0}, {1, 2}, {2, 0} };

            List<List<Integer>> mat = new AdjacencyListGraph().createDirectedGraph(v, edges);
            System.out.println("Directed graph: ");
            for (int i = 0; i < mat.size(); i++) {
                System.out.print(i + ": ");
                for (Integer integer : mat.get(i)) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
