package dfs;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/employee-importance/
 */
public class _690_员工的重要性 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

    }

    Map<Integer, Employee> map = new HashMap<Integer, Employee>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs1(id);
    }

    private int dfs1(int id) {
        Employee employee = map.get(id);
        int ans = employee.importance;
        if (employee.subordinates == null) {
            return ans;
        }
        List<Integer> subordinates = employee.subordinates;
        for (Integer subordinate : subordinates) {
            ans += dfs1(subordinate);
        }
        return ans;
    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        int ans = employee.importance;
        List<Integer> subordinates = employee.subordinates;
        for (Integer subordinate : subordinates) {
            ans += dfs(subordinate);
        }
        return ans;
    }

    public int getImportance1(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        int ans = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            Employee employee = map.get(cur);
            ans += employee.importance;
            for (Integer subordinate : employee.subordinates) {
                queue.offer(subordinate);
            }
        }
        return ans;
    }

}
