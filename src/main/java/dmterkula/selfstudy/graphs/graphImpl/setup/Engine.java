package dmterkula.selfstudy.graphs.graphImpl.setup;

import dmterkula.selfstudy.graphs.binaryheap.domain.TypeSafeArrayList;
import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;
import dmterkula.selfstudy.graphs.graphImpl.domain.Graph;
import dmterkula.selfstudy.graphs.graphImpl.domain.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by David Terkula on 5/27/2017.
 */
public class Engine {

        private HashMap<Integer, Node> idToNode;
        private HashSet<Node> inputNodes;
        private HashMap<String, Edge> urlToEdge;
        private HashSet<Edge> inputEdges;
        private GoogleMapsHandler googleMapsHandler;

        public Engine() throws IOException{
            idToNode = new HashMap<Integer, Node>();
            inputNodes =  new HashSet<Node>();
            urlToEdge = new HashMap<String, Edge>();
            inputEdges = new HashSet<Edge>();
            googleMapsHandler = new GoogleMapsHandler();
            test();
        }

        public void test() throws IOException {
       /* BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        heap.insert(5);
        heap.insert(7);
        heap.insert(8);
        heap.insert(4);
        System.out.println(heap);
        heap.insert(23);
        System.out.println(heap);
        heap.insert(2);
        System.out.println(heap);
        heap.insert(100);
        heap.insert(-3);
        heap.insert(-4);
        System.out.println(heap);

        heap.deleteMin();
        System.out.println(heap);
        heap.deleteMin();
        System.out.println(heap);*//*

        TypeSafeArrayList<Integer> list = new TypeSafeArrayList<Integer>();
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(4);
        list.add(23);
        list.add(2);
        list.add(100);
        list.add(-3);
        BinaryHeap<Integer> heap2 = new BinaryHeap<Integer>(list, true);
        heap2.deleteMin();
        System.out.println(heap2);*/

       /* BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>(true);
        testHeap.insert(5);
        testHeap.insert(7);
        testHeap.insert(8);
        testHeap.insert(4);
        testHeap.insert(23);
        testHeap.insert(2);
        testHeap.insert(100);
        testHeap.insert(3);
        System.out.println(testHeap);
        testHeap.setMin(testHeap.getList(), false);
        System.out.println(testHeap);
        testHeap.deleteMax();
        System.out.println(testHeap);*/


          /*  Node i = new Node(3.0);
            Node j = new Node(6.0);
            Node k = new Node(3.0);
            Node l = new Node(5.0);
            Node m = new Node(1.0);

            Edge iToJ = new Edge(i, j, 2.0);
            Edge jToK = new Edge(j, k, 3.0);
            Edge iToL = new Edge(i, l, 7.0);
            Edge lToK = new Edge(l, k, 4.0);

            Edge jToI = new Edge(j, i, 2.0);
            Edge kToJ = new Edge(k, j, 3.0);
            Edge ltoI = new Edge(l, i, 7.0);
            Edge kToL = new Edge(k, l, 3.0);

            Edge iToM = new Edge(i, m, 1);
            Edge mToI = new Edge(m, i, 1);
            Edge jToM = new Edge(j, m, 5);
            Edge mToJ = new Edge(m, j, 5);
            Edge lToM = new Edge(l, m, 7);
            Edge mToL = new Edge(m, l, 7);
            Edge kToM = new Edge(k, m, 2);
            Edge mToK = new Edge(m, k, 2);



            HashSet<Node> nodes = new HashSet<Node>();

            nodes.add(i);
            nodes.add(j);
            nodes.add(k);
            nodes.add(l);
            nodes.add(m);

            HashSet<Edge> edges = new HashSet<Edge>();
            edges.add(iToJ);
            edges.add(iToL);
            edges.add(jToK);
            edges.add(lToK);

            edges.add(jToI);
            edges.add(ltoI);
            edges.add(kToJ);
            edges.add(kToL);

            edges.add(iToM);
            edges.add(jToM);
            edges.add(lToM);
            edges.add(kToM);
            edges.add(mToI);
            edges.add(mToJ);
            edges.add(mToK);
            edges.add(mToL);




            Graph<Double> G = new Graph(nodes, edges);
            ArrayList<Node> jNeighbors = G.getNeighbors(j);
            for (Node n : jNeighbors) {
                System.out.println(n.getId());
                Edge e = G.getEdge(j, n);
                System.out.println(e);
            }

            System.out.println();
            System.out.println("Checking hash map nodes");
            for (Node n : nodes) {
                System.out.println(n);
            }

            ArrayList<ArrayList<Node>> adjList = G.getAdjacencyList();
            System.out.println("ADJ LIST: ");
            System.out.println("Size:" + adjList.size());
            System.out.println("Node i id: " + i);
            System.out.println();

            System.out.println("getAdjList(): " + adjList);
            System.out.println();*/


            //BinaryHeap<Node<Double>> heap = new BinaryHeap<Node<Double>>();
            //heap.insert(j);
            //Node n = heap.deleteMin();
            //System.out.println(heap.size());
            //System.out.println(heap.isEmpty());
            //System.out.println(heap.deleteMin());
            //System.out.println(n);

            //HashMap<Node, Node> mst = G.getMST(i);
//            HashMap<Node, Node> mst = G.getShortestPath(i);
//            System.out.println();
//
//            for (Map.Entry<Node, Node> entry : mst.entrySet()) {
//                Node key = entry.getKey();
//                Node value = entry.getValue();
//                System.out.println("{" + key + ", " + value + "}");
//            }


            TextFileReader nodeTFR = new TextFileReader("src/main/resources/Locations.txt");
            nodeTFR.readFile();
            ArrayList<String []> input = nodeTFR.getInputLines();
            processNodeInput(input);
//            for(Node n: inputNodes){
//                System.out.println(n + ", " + n.getField());
//            }

            TextFileReader edgeTFR = new TextFileReader("src/main/resources/Edges.txt");
            edgeTFR.readFile();
            ArrayList<String []> eInput = edgeTFR.getInputLines();
            processEdgeInput(eInput);
            ArrayList<String> urls = createUrls();
            updateEdgeWeight(urls);

            Graph<Double> mapsGraph = new Graph<Double>(inputNodes, inputEdges);
            HashMap<Node, Node> minTree = mapsGraph.getMSTMap(idToNode.get(0));
           // System.out.println();

//            for (Map.Entry<Node, Node> entry : minTree.entrySet()) {
//                Node key = entry.getKey();
//                Node value = entry.getValue();
//                System.out.println("{" + key + ", " + value + "}");
//            }
//
//            System.out.println("BFS TEST");
//            Node root = mapsGraph.getAdjacencyList().get(0).get(0);
//            System.out.println(root);
//            Node [] order = mapsGraph.BFS(root);
//            System.out.print("{");
//            for(Node n: order){
//                System.out.print(n +", ");
//            }
//            System.out.print("}");
        }

        public void processNodeInput(ArrayList<String []> input){
            for(String [] line : input){
                Node n = new Node(Integer.parseInt(line[0]), line[1]);
                idToNode.put(n.getId(), n);
                inputNodes.add(n);
            }
        }

        public void processEdgeInput(ArrayList<String []> input){
            for(String [] line : input){
                Node start = idToNode.get(Integer.parseInt(line[0]));
                Node end = idToNode.get(Integer.parseInt(line[1]));
                Edge e = new Edge(start, end, 0.0);
                inputEdges.add(e);
            }
        }

        public ArrayList<String> createUrls() {
            ArrayList<String> urls = new ArrayList<String>();
            for (Edge e : inputEdges) {
                String origin = (String)e.getStart().getField();
                String destination = (String)e.getEnd().getField();
                String url = (googleMapsHandler.getUrl(origin, destination));
                urlToEdge.put(url, e);
                urls.add(url);
            }
            return urls;
        }

        public void updateEdgeWeight(ArrayList<String> urls) throws IOException{
            HashSet<Edge> edgeWithWeights = new HashSet<Edge>();
            for(String url: urls){
                String jsonString = googleMapsHandler.getJsonResponse(url);
                double edgeWeight = googleMapsHandler.useJSONToFindDistance(jsonString);
                Edge e = urlToEdge.get(url);
                e.setWeight(edgeWeight);
                urlToEdge.remove(url);
                urlToEdge.put(url, e);
                edgeWithWeights.add(e);
            }
            inputEdges = edgeWithWeights;
        }

        public HashSet<Node> getInputNodes(){
            return inputNodes;
        }

        public HashSet<Edge> getInputEdges(){
            return inputEdges;
        }
}
