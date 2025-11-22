import AdjList.AbstractAdjListGraph;
import java.util.ArrayList;

public class AdjListDirectedGraph extends AbstractAdjListGraph {

    public AdjListDirectedGraph() {
        super();
    }

    @Override
    public int getNumberOfVertices() {
        return map.size();
    }

    @Override
    public int getNumberOfEdges() {
       int totalEdges = 0;
       for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
           totalEdges += entry.getValue().size();
       }
       return totalEdges;
    }

    @Override
    public void removeVertex(int v) {
        // Remove all incoming edges to v
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            entry.getValue().remove(Integer.valueOf(v));
        }
        // Remove v's outgoing edges
        map.remove(v);
    }

    @Override
    public void addEdge(int u, int v) {
        List<Integer> list = map.getOrDefault(u,new ArrayList<>());
        list.add(v);
        map.put(u,list);
    }

    @Override
    public void removeEdge(int u, int v) {
        List<Integer> list = map.getOrDefault(u,new ArrayList<>());
        list.remove(Integer.valueOf(v));
        map.put(u,list);
    }

    @Override
    public boolean hasEdge(int u, int v) {
        List<Integer> list = map.getOrDefault(u,new ArrayList<>());
        return list.contains(v);
    }

    @Override
    public ArrayList<Integer> getNeighbors(int v) {
        return (ArrayList<Integer>) map.getOrDefault(v,new ArrayList<>());
    }
}

void main() {
    AdjListDirectedGraph directedGraph = new AdjListDirectedGraph();
    directedGraph.addEdge(1, 2);
    directedGraph.addEdge(2, 3);
    directedGraph.addEdge(3, 1);
    directedGraph.addEdge(4, 2);
    directedGraph.printGraph();
    System.out.println("Number of vertices: " + directedGraph.getNumberOfVertices());
    System.out.println("Number of edges: " + directedGraph.getNumberOfEdges());
    System.out.println("Neighbors of vertex 3: " + directedGraph.getNeighbors(3));
    System.out.println("Neighbors of vertex 1: " + directedGraph.getNeighbors(1));
    System.out.println("Neighbors of vertex 2: " + directedGraph.getNeighbors(2));
    System.out.println("Has Edge 1,2 = " + directedGraph.hasEdge(1,2));
    directedGraph.removeVertex(1);
    System.out.println("Number of vertices: " + directedGraph.getNumberOfVertices());
    System.out.println("Number of edges: " + directedGraph.getNumberOfEdges());
    System.out.println("Has Edge 1,2 = " + directedGraph.hasEdge(1,2));
    directedGraph.printGraph();
}