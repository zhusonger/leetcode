package cn.com.lasong.leetcode.card.queue_stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: zhusong
 * Email: song.zhu@lasong.com.cn
 * Date: 2020-04-15
 * Description:
 */
public class DFS_cloneGraph {

    private Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (null == node) {
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        List<Node> neighbors = node.neighbors;
        if (null == neighbors) {
            return cloneNode;
        }

        for (Node item : neighbors) {
            cloneNode.neighbors.add(cloneGraph(item));
        }

        return cloneNode;
    }

}
