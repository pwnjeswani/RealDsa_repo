import AdjList.AbstractAdjListGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class AdjListUndirectedGraph extends AbstractAdjListGraph {

    public AdjListUndirectedGraph() {
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
       return totalEdges/2;
    }

    @Override
    public void removeVertex(int v) {
        List<Integer> list = map.getOrDefault(v,new ArrayList<>());
        for (Integer i : list) {
            List<Integer> list2 = map.getOrDefault(i,new ArrayList<>());
            list2.remove(Integer.valueOf(v));
            map.put(i,list2);
        }
        map.remove(v);
    }

    @Override
    public void addEdge(int u, int v) {

        List<Integer> list = map.getOrDefault(u,new ArrayList<>());
        list.add(v);
        map.put(u,list);

        List<Integer> list2 = map.getOrDefault(v,new ArrayList<>());
        list2.add(u);
        map.put(v,list2);
    }

    @Override
    public void removeEdge(int u, int v) {
        List<Integer> list = map.getOrDefault(u,new ArrayList<>());
        list.remove(Integer.valueOf(v));
        map.put(u,list);

        List<Integer> list2 = map.getOrDefault(v,new ArrayList<>());
        list2.remove(Integer.valueOf(u));
        map.put(v,list2);
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
    AdjListUndirectedGraph unDirectedGraph = new AdjListUndirectedGraph();
    unDirectedGraph.addEdge(1, 2);
    unDirectedGraph.addEdge(2, 3);
    unDirectedGraph.addEdge(3, 1);
    unDirectedGraph.addEdge(3, 4);
    unDirectedGraph.addEdge(4, 1);
    unDirectedGraph.printGraph();
    System.out.println("Number of vertices: " + unDirectedGraph.getNumberOfVertices());
    System.out.println("Number of edges: " + unDirectedGraph.getNumberOfEdges());
    System.out.println("Neighbors of vertex 3: " + unDirectedGraph.getNeighbors(3));
    System.out.println("Neighbors of vertex 1: " + unDirectedGraph.getNeighbors(1));
    System.out.println("Neighbors of vertex 2: " + unDirectedGraph.getNeighbors(2));
    unDirectedGraph.removeVertex(3);
    unDirectedGraph.printGraph();
    System.out.println("Has Edge 1,2 = " + unDirectedGraph.hasEdge(1,2));
    unDirectedGraph.removeEdge(2,1);
    System.out.println("Has Edge 1,2 = " + unDirectedGraph.hasEdge(1,2));
    unDirectedGraph.printGraph();
}