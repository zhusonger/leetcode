package cn.com.lasong.leetcode.card.queue_stack;

import java.util.*;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-04-15
 * Description:
 */
public class BFS_cloneGraph {

    private Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (null == node) {
            return null;
        }


        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node item = queue.poll();
            List<Node> neighbors = item.neighbors;
            Node cloneNode = visited.get(item);
            for (Node neighbor : neighbors) {
                if (!visited.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                }
                cloneNode.neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }
}
