package com.solution.dsa.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> dependencyMap = new HashMap<>();
        for (int[] dependency : prerequisites) {
            dependencyMap.putIfAbsent(dependency[0], new HashSet<>());
            dependencyMap.get(dependency[0]).add(dependency[1]);
        }
        Map<Integer, Boolean> computationMemory = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            if (!checkCanFinish(i, dependencyMap, new HashSet<>(), computationMemory)) return false;
        }
        return true;
    }

    private boolean checkCanFinish(int course, Map<Integer, Set<Integer>> dependencyMap,
                                   Set<Integer> visited, Map<Integer, Boolean> computationMemory) {

        if (computationMemory.get(course) != null) return computationMemory.get(course);
        if (visited.contains(course)) {
            computationMemory.put(course, false);
            return false;
        }

        visited.add(course);
        Set<Integer> dependencies = dependencyMap.get(course);
        if (dependencies == null || dependencies.isEmpty()) return true;

        for (int dependency : dependencies) {
            boolean result = checkCanFinish(dependency, dependencyMap, visited, computationMemory);
            computationMemory.put(dependency, result);
            if (!result) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        {
            System.out.println(new CourseSchedule().canFinish(
                    2,
                    new int[][]{
                            {1, 0}
                    }));
        }
        {
            System.out.println(new CourseSchedule().canFinish(
                    2, new int[][]{
                            {1, 0},
                            {0, 1}
                    }));
        }
        {
            System.out.println(new CourseSchedule().canFinish(
                    4, new int[][]{
                            {0, 1},
                            {0, 2},
                            {2, 1},
                            {1, 3},
                            {3, 0}
                    }));
        }
    }
}
