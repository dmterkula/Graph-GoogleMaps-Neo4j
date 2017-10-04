package dmterkula.selfstudy.graphs.graphImpl.domain;

import dmterkula.selfstudy.graphs.binaryheap.domain.BinaryHeap;

import java.util.*;

/**
 * Created by David Terkula on 5/11/2017.
 */
public class Graph<T> {

    private HashSet<Edge> Edges;
    private HashSet<Node> Nodes;
    private ArrayList<ArrayList<Node>> adjacencyList;
    private BinaryHeap<Node<T>> heap;

    public Graph(){
        Edges = new HashSet<>();
        Nodes = new HashSet<>();
        adjacencyList = new ArrayList<>();
    }

    public Graph(HashSet<Node> nodes, HashSet<Edge> edges) {
        Edges = edges;
        Nodes = nodes;
        adjacencyList = new ArrayList<>();
        populateAdjacencyList();
    }

    public void populateAdjacencyList() {
        for(Node n : Nodes) {
            ArrayList<Node> row = new ArrayList<Node>();
            row.add(n);
            for (Object e : getEdges()) {
                Edge ed = (Edge) e;
                row.add(ed.getEnd());
            }
            adjacencyList.add(row);

        }
    }

    public HashSet<Node> getNodes() {
        return Nodes;
    }

    public void setNodes(HashSet<Node> nodes) {
        Nodes = nodes;
    }

    public HashSet<Edge> getEdges() {
        return Edges;
    }

    public void setEdges(HashSet<Edge> edges) {
        Edges = edges;
    }

    public Edge getEdge(Node start, Node end){
        Edge returnMe = null;
        for(Edge e : Edges){
            if(e.getStart().equals(start) && e.getEnd().equals(end)){
                returnMe = e;
            }
        }
        return returnMe;
    }

    public ArrayList<Node> getNeighbors(Node node) {

        ArrayList<Node> returnMe = new ArrayList<Node>();
        for (Edge edge: getEdges()) {
            if(edge.getStart().equals(node))
                returnMe.add(edge.getEnd());
        }
        return returnMe;
    }

    public ArrayList<ArrayList<Node>> getAdjacencyList() {
        return adjacencyList;
    }

    public HashMap<Node, Node> getMSTMap(Node root) {

        HashMap<Node, Double> distance = new HashMap<Node, Double>();
        HashMap<Node, Node> parents = new HashMap<Node, Node>();
        HashMap<Node, Boolean> seen = new HashMap<Node, Boolean>();


        for (Node n : Nodes) {
            if (n.equals(root)) {
                root.setValue(0.0);
                distance.put(n, root.getValue());
                seen.put(n, true);
                parents.put(n, null);
            } else {
                distance.put(n, Double.POSITIVE_INFINITY);
                seen.put(n, false);
                parents.put(n, null);
            }

        }

        BinaryHeap<Node<T>> heap = new BinaryHeap<Node<T>>(true);
        heap.insert(root);


         for(int i = 0; i< Nodes.size()-1; i++){//while(!heap.isEmpty()) {
            Comparable c = heap.deleteMin();
            Node currentNode = (Node) c;
            System.out.println("i am min now: " + currentNode);
            for (Node neighbor : getNeighbors(currentNode)) {
                if(seen.get(neighbor) == true) {
                    // if (currentNode.getEdge(neighbor).getWeight() < distance.get(neighbor)) {
                       // distance.put(neighbor, currentNode.getEdge(neighbor).getWeight());
                    Edge e = getEdge(currentNode, neighbor);
                    if(e.getWeight() < distance.get(neighbor)){
                        distance.put(neighbor, e.getWeight());
                        heap.update(neighbor, distance);
                        parents.put(neighbor, currentNode);
                    }
                }
                else{ // not see
                    seen.put(neighbor, true);
                    // distance.put(neighbor, currentNode.getEdge(neighbor).getWeight());
                    distance.put(neighbor, getEdge(currentNode, neighbor).getWeight());
                    neighbor.setValue(distance.get(neighbor));
                    heap.insert(neighbor);
                    parents.put(neighbor, currentNode);
                }

            }

            System.out.println();
            System.out.println("Distance map");
            for (Map.Entry<Node, Double> entry : distance.entrySet()) {
                Node key = entry.getKey();
                Double value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }
            System.out.println();
            System.out.println("Parent map");
            for (Map.Entry<Node, Node> entry : parents.entrySet()) {
                Node key = entry.getKey();
                Node value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }

        }
        return parents;
    }

    public Graph<Node> getMSTGraph(Node root) {

        HashMap<Node, Double> distance = new HashMap<Node, Double>();
        HashMap<Node, Node> parents = new HashMap<Node, Node>();
        HashMap<Node, Boolean> seen = new HashMap<Node, Boolean>();


        for (Node n : Nodes) {
            if (n.equals(root)) {
                root.setValue(0.0);
                distance.put(n, root.getValue());
                seen.put(n, true);
                parents.put(n, null);
            } else {
                distance.put(n, Double.POSITIVE_INFINITY);
                seen.put(n, false);
                parents.put(n, null);
            }

        }

        BinaryHeap<Node<T>> heap = new BinaryHeap<Node<T>>(true);
        heap.insert(root);


        for(int i = 0; i< Nodes.size()-1; i++){//while(!heap.isEmpty()) {
            Comparable c = heap.deleteMin();
            Node currentNode = (Node) c;
            System.out.println("i am min now: " + currentNode);
            for (Node neighbor : getNeighbors(currentNode)) {
                if(seen.get(neighbor) == true) {
                    // if (currentNode.getEdge(neighbor).getWeight() < distance.get(neighbor)) {
                    // distance.put(neighbor, currentNode.getEdge(neighbor).getWeight());
                    Edge e = getEdge(currentNode, neighbor);
                    if(e.getWeight() < distance.get(neighbor)){
                        distance.put(neighbor, e.getWeight());
                        heap.update(neighbor, distance);
                        parents.put(neighbor, currentNode);
                    }
                }
                else{ // not see
                    seen.put(neighbor, true);
                    // distance.put(neighbor, currentNode.getEdge(neighbor).getWeight());
                    distance.put(neighbor, getEdge(currentNode, neighbor).getWeight());
                    neighbor.setValue(distance.get(neighbor));
                    heap.insert(neighbor);
                    parents.put(neighbor, currentNode);
                }

            }

            System.out.println();
            System.out.println("Distance map");
            for (Map.Entry<Node, Double> entry : distance.entrySet()) {
                Node key = entry.getKey();
                Double value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }
            System.out.println();
            System.out.println("Parent map");
            for (Map.Entry<Node, Node> entry : parents.entrySet()) {
                Node key = entry.getKey();
                Node value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }

        }
        Graph<Node> mst = new Graph();
        for(Map.Entry<Node, Node> entry: parents.entrySet()){
            mst.getEdges().add((getEdge(entry.getKey(), entry.getValue())));
            mst.getNodes().add(entry.getKey());
        }
        return mst;
    }

    public HashMap<Node, Node> getShortestPathMap(Node root){
        HashMap<Node, Double> distance = new HashMap<Node, Double>();
        HashMap<Node, Node> parents = new HashMap<Node, Node>();
        HashMap<Node, Boolean> seen = new HashMap<Node, Boolean>();


        for (Node n : Nodes) {
            if (n.equals(root)) {
                root.setValue(0.0);
                distance.put(n, root.getValue());
                seen.put(n, true);
                parents.put(n, null);
            } else {
                distance.put(n, Double.POSITIVE_INFINITY);
                seen.put(n, false);
                parents.put(n, null);
            }

        }

        BinaryHeap<Node<T>> heap = new BinaryHeap<Node<T>>(true);
        heap.insert(root);


        for(int i = 0; i< Nodes.size()-1; i++){//while (!heap.isEmpty()) {
            Comparable c = heap.deleteMin();
            Node currentNode = (Node) c;
            System.out.println("i am min now: " + currentNode);
            //Node current = heap.deleteMin();
            for (Node neighbor : getNeighbors(currentNode)) {
                if(seen.get(neighbor) == true) {
//                    if (currentNode.getEdge(neighbor).getWeight() + distance.get(currentNode) < distance.get(neighbor)) {
//                        distance.put(neighbor, currentNode.getEdge(neighbor).getWeight() + distance.get(currentNode));
                    Edge e = getEdge(currentNode, neighbor);
                    if(e.getWeight() + distance.get(currentNode) < distance.get(neighbor)){
                        distance.put(neighbor, e.getWeight()+distance.get(neighbor));
                        heap.update(neighbor, distance);
                        parents.put(neighbor, currentNode);
                    }
                }
                else{ // not see
                    seen.put(neighbor, true);
                    //distance.put(neighbor, currentNode.getEdge(neighbor).getWeight() + distance.get(currentNode));
                   distance.put(neighbor, getEdge(currentNode, neighbor).getWeight() + distance.get(currentNode));
                    neighbor.setValue(distance.get(neighbor));
                    heap.insert(neighbor); // was temp
                    parents.put(neighbor, currentNode);
                }

            }

            System.out.println();
            System.out.println("Distance map");
            for (Map.Entry<Node, Double> entry : distance.entrySet()) {
                Node key = entry.getKey();
                Double value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }
            System.out.println();
            System.out.println("Parent map");
            for (Map.Entry<Node, Node> entry : parents.entrySet()) {
                Node key = entry.getKey();
                Node value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }

        }
        return parents;
    }

    public Graph<Node> getShortestPathGraph(Node root){
        HashMap<Node, Double> distance = new HashMap<Node, Double>();
        HashMap<Node, Node> parents = new HashMap<Node, Node>();
        HashMap<Node, Boolean> seen = new HashMap<Node, Boolean>();


        for (Node n : Nodes) {
            if (n.equals(root)) {
                root.setValue(0.0);
                distance.put(n, root.getValue());
                seen.put(n, true);
                parents.put(n, null);
            } else {
                distance.put(n, Double.POSITIVE_INFINITY);
                seen.put(n, false);
                parents.put(n, null);
            }

        }

        BinaryHeap<Node<T>> heap = new BinaryHeap<Node<T>>(true);
        heap.insert(root);


        for(int i = 0; i< Nodes.size()-1; i++){//while (!heap.isEmpty()) {
            Comparable c = heap.deleteMin();
            Node currentNode = (Node) c;
            System.out.println("i am min now: " + currentNode);
            //Node current = heap.deleteMin();
            for (Node neighbor : getNeighbors(currentNode)) {
                if(seen.get(neighbor) == true) {
//                    if (currentNode.getEdge(neighbor).getWeight() + distance.get(currentNode) < distance.get(neighbor)) {
//                        distance.put(neighbor, currentNode.getEdge(neighbor).getWeight() + distance.get(currentNode));
                    Edge e = getEdge(currentNode, neighbor);
                    if(e.getWeight() + distance.get(currentNode) < distance.get(neighbor)){
                        distance.put(neighbor, e.getWeight()+distance.get(neighbor));
                        heap.update(neighbor, distance);
                        parents.put(neighbor, currentNode);
                    }
                }
                else{ // not see
                    seen.put(neighbor, true);
                    //distance.put(neighbor, currentNode.getEdge(neighbor).getWeight() + distance.get(currentNode));
                    distance.put(neighbor, getEdge(currentNode, neighbor).getWeight() + distance.get(currentNode));
                    neighbor.setValue(distance.get(neighbor));
                    heap.insert(neighbor); // was temp
                    parents.put(neighbor, currentNode);
                }

            }

            System.out.println();
            System.out.println("Distance map");
            for (Map.Entry<Node, Double> entry : distance.entrySet()) {
                Node key = entry.getKey();
                Double value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }
            System.out.println();
            System.out.println("Parent map");
            for (Map.Entry<Node, Node> entry : parents.entrySet()) {
                Node key = entry.getKey();
                Node value = entry.getValue();
                System.out.println("{" + key + ", " + value + "}");

            }

        }
        Graph<Node> sp = new Graph();
        for(Map.Entry<Node, Node> entry: parents.entrySet()){
            sp.getEdges().add((getEdge(entry.getKey(), entry.getValue())));
            sp.getNodes().add(entry.getKey());
        }
        return sp;
    }

    public Node[] DFS(Node root){
        Node [] order = new Node[Nodes.size()];
        order[0] = root;
        if(root == null){
            return order;
        }
        HashMap<Node, Boolean> visited = new HashMap<>();

        for(Node n: Nodes){
            visited.put(n, false);
        }
        visited.put(root, true);
        int orderCounter = 1;

        Stack<Node> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node current = stack.pop();
            if (visited.get(current) == false) {
                visited.put(current, true);
                order[orderCounter] = current;
                orderCounter++;
            }
                for (Node n : getNeighbors(current)) {
                    if (visited.get(n) == false) {
                        stack.push(n);
                    }
                }
            }

        return order;
    }

    public Node[] BFS(Node root){
        Node [] order = new Node[Nodes.size()];
        order[0] = root;
        if(root == null){
            return order;
        }
        HashMap<Node, Boolean> visited = new HashMap<>();

        for(Node n: Nodes){
            visited.put(n, false);
        }
        visited.put(root, true);
        int orderCounter = 1;

        Queue<Node> queue = new PriorityQueue();

        queue.add(root);
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited.get(current) == false) {
                visited.put(current, true);
                order[orderCounter] = current;
                orderCounter++;
            }
            for (Node n : getNeighbors(current)) {
                if (visited.get(n) == false) {
                    queue.add(n);
                }
            }
        }

        return order;
    }


}
