package com.solution.dsa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleV2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) adj[i] = new ArrayList<>();
        for (int[] dependency : prerequisites) {
            adj[dependency[0]].add(dependency[1]);
        }

        /*
        0 -> not visited
        1 -> visiting
        2 -> visited
         */
        int[] state = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (checkForCycle(i, adj, state)) return false;
        }
        return true;
    }

    private boolean checkForCycle(int course, List<Integer>[] adj, int[] state) {
        if (state[course] == 1) return true;    // this course is in visiting loop, that means it is part of dependency
        if (state[course] == 2) return false;   // this course is already successfully visited, no cycle found

        state[course] = 1;
        for (Integer nbr : adj[course]) {
            if (checkForCycle(nbr, adj, state)) return true;
        }
        state[course] = 2;
        return false;
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
