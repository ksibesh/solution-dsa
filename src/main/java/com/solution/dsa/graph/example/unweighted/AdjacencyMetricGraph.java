package com.solution.dsa.graph.example.unweighted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacencyMetricGraph {

    public List<List<Integer>> createUndirectedGraph(int v, int[][] edges) {
        List<List<Integer>> mat = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(v, 0));
            mat.add(row);
        }

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];

            mat.get(v1).set(v2, 1);
            mat.get(v2).set(v1, 1);
        }
        return mat;
    }

    public List<List<Integer>> createDirectedGraph(int v, int[][] edges) {
        List<List<Integer>> mat = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(v, 0));
            mat.add(row);
        }

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];

            mat.get(v1).set(v2, 1);
        }
        return mat;
    }

    public static void main(String[] args) {
        {
            int v = 3;
            int[][] edges = {
                    {0, 1},
                    {0, 2},
                    {1, 2}
            };

            List<List<Integer>> mat = new AdjacencyMetricGraph().createUndirectedGraph(v, edges);
            System.out.println("Undirected graph: ");
            for (List<Integer> integers : mat) {
                for (Integer integer : integers) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        {
            int v = 3;
            int[][] edges = {{1, 0}, {2, 0}, {1, 2}};

            List<List<Integer>> mat = new AdjacencyMetricGraph().createDirectedGraph(v, edges);
            System.out.println("Directed graph: ");
            for (List<Integer> integers : mat) {
                for (Integer integer : integers) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
