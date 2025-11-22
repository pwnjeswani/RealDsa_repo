import AdjList.AbstractWeightedAdjListGraph;
import java.util.*;

public class WeightedAdjListDirectedGraph extends AbstractWeightedAdjListGraph {

    public WeightedAdjListDirectedGraph() {
        super();
    }

    @Override
    public int getNumberOfVertices() {
        return map.size();
    }

    @Override
    public int getNumberOfEdges() {
        int total = 0;
        for (List<Pair<Integer, Integer>> list : map.values()) {
            total += list.size();
        }
        return total;
    }

    @Override
    public void removeVertex(int v) {
        // Remove incoming edges
        for (List<Pair<Integer, Integer>> list : map.values()) {
            list.removeIf(pair -> pair.neighbor.equals(v));
        }
        // Remove outgoing
        map.remove(v);
    }

    @Override
    public void addEdge(int u, int v, int weight) {
        List<Pair<Integer, Integer>> list = map.computeIfAbsent(u, k -> new ArrayList<>());
        list.add(new Pair<>(v, weight));
    }

    @Override
    public void removeEdge(int u, int v) {
        List<Pair<Integer, Integer>> list = map.get(u);
        if (list != null) {
            list.removeIf(pair -> pair.neighbor.equals(v));
        }
    }

    @Override
    public boolean hasEdge(int u, int v) {
        List<Pair<Integer, Integer>> list = map.get(u);
        if (list != null) {
            for (Pair<Integer, Integer> p : list) {
                if (p.neighbor.equals(v)) return true;
            }
        }
        return false;
    }

    @Override
    public List<Pair<Integer, Integer>> getNeighbors(int v) {
        return map.getOrDefault(v, new ArrayList<>());
    }
}

void main() {
    WeightedAdjListDirectedGraph g = new WeightedAdjListDirectedGraph();
    g.addEdge(1, 2, 5);
    g.addEdge(2, 3, 3);
    g.addEdge(3, 1, 2);
    g.addEdge(4, 2, 1);
    g.printGraph();
    System.out.println("Vertices: " + g.getNumberOfVertices());
    System.out.println("Edges: " + g.getNumberOfEdges());
    g.removeVertex(1);
    g.printGraph();
}
