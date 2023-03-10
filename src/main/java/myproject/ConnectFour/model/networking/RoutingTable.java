package myproject.ConnectFour.model.networking;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class RoutingTable {

    // alpha determines the maximum number of nodes that can be contacted in parallel
    // when performing iterative routing or lookups in the distributed hash table.
    // B / 2^K - used to calculate prefix length for a given bucket
    private static int B;       // ID size in number of bits
    private static int K;       // size of k-bucket (number of nodes it can store)
    private static int alpha;
    private static List<List<Node>> buckets;
    private static Node localNode;
    private static Node bootstrapNode;


    public static void init(Node local, Node boot, int B, int K) {
        RoutingTable.B = B;
        RoutingTable.K = K;
        localNode = local;
        bootstrapNode = boot;
        buckets = new ArrayList<>((int)Math.pow(2, K));
        for (int i = 0; i < (int)Math.pow(2, K); i++) {
            buckets.add(new ArrayList<>(K));
        }
    }

    public static boolean add(Node node) {
        if (node.getId() == localNode.getId()) return false;
        int index = getIndex(localNode.getId(), node.getId());
        ArrayList<Node> bucket = (ArrayList<Node>) buckets.get(index);
        if (bucket.size() < K) {
            remove(node);
            bucket.add(node);
        } else {
            if (remove(node)) bucket.add(node);
            else {
                removeOldestNode(bucket);
                bucket.add(node);
            }
        }
        return true;
    }

    public static boolean remove(Node node) {
        int index = getIndex(localNode.getId(), node.getId());
        ArrayList<Node> bucket = (ArrayList<Node>) buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getId() == node.getId()) {
                bucket.remove(i);
                return true;
            }
        }
        return false;
    }

    public static boolean remove(String nodeIdHex) {
        int index = getIndex(localNode.getId(), IDGenerator.hexStringToInt(nodeIdHex));
        ArrayList<Node> bucket = (ArrayList<Node>) buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getId() == IDGenerator.hexStringToInt(nodeIdHex)) {
                bucket.remove(i);
                return true;
            }
        }
        return false;
    }

    // k - number of the closest nodes retrieved
    public static ArrayList<Node> getClosestNodes(int id, int k) {
        ArrayList<Node> closestNodes = new ArrayList<Node>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>(
                Comparator.comparingInt(n -> n.getDistance(IDGenerator.intToHexString(id))));
        for (List<Node> bucket : buckets) {
            for (Node node : bucket) {
                if (node.getId() != localNode.getId()) {
                    pq.offer(node);
                }
            }
        }
        while (!pq.isEmpty() && closestNodes.size() < k) {
            closestNodes.add(pq.poll());
        }
        return closestNodes;
    }

    // I will see later what am I going to do about this one
    // when I will actually need to use it
    public static void updateLastSeen(String nodeIdHex) {
        int index = getIndex(localNode.getId(), IDGenerator.hexStringToInt(nodeIdHex));
        List<Node> bucket = buckets.get(index);
        for (Node n : bucket) {
            if (n.getId() == IDGenerator.hexStringToInt(nodeIdHex)) {
                n.setLastSeenTimestamp(Instant.now().getEpochSecond());
                return;
            }
        }
    }




    private static int getIndex(int localNodeId, int nodeId) {
        int distance = localNodeId ^ nodeId;
        int prefixLength = (int)(B / Math.pow(2, K));
        int offset = B - prefixLength;
        return distance >> offset;
    }

    public static void removeOldestNode(ArrayList<Node> nodes) {
        long oldestTimestamp = Long.MAX_VALUE;
        Node oldestNode = null;
        for (Node node : nodes) {
            if (node.getLastSeenTimestamp() < oldestTimestamp) {
                oldestTimestamp = node.getLastSeenTimestamp();
                oldestNode = node;
            }
        }
        if (oldestNode != null) {
            nodes.remove(oldestNode);
        }
    }


    public static Node getLocalNode() {
        return localNode;
    }

    public static Node getBootstrapNode() {
        return bootstrapNode;
    }

    public static ArrayList<Node> getAllNodes() {
        ArrayList<Node> allNodes = new ArrayList<>();
        for (List<Node> bucket : buckets) {
            allNodes.addAll(bucket);
        }
        return allNodes;
    }







    // for testing purposes only
    public static void print() {
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println("BUCKET " + i);
            for (int j = 0; j < buckets.get(i).size(); j++) {
                int id = buckets.get(i).get(j).getId();
                System.out.print(IDGenerator.intToHexString(id) + "  ");
            }
            System.out.println();
        }
    }
}
