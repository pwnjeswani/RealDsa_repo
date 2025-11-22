import AdjList.AbstractWeightedAdjListGraph;
import java.util.*;

class WeightedAdjListUndirectedGraph extends AbstractWeightedAdjListGraph {

    public WeightedAdjListUndirectedGraph() {
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
        return total / 2;
    }

    @Override
    public void removeVertex(int v) {
        List<Pair<Integer, Integer>> neighbors = map.get(v);
        if (neighbors != null) {
            for (Pair<Integer, Integer> p : neighbors) {
                int neighbor = p.neighbor;
                List<Pair<Integer, Integer>> nList = map.get(neighbor);
                if (nList != null) {
                    nList.removeIf(pair -> pair.neighbor.equals(v));
                }
            }
        }
        map.remove(v);
    }

    @Override
    public void addEdge(int u, int v, int weight) {
        List<Pair<Integer, Integer>> listU = map.computeIfAbsent(u, k -> new ArrayList<>());
        listU.add(new Pair<>(v, weight));

        List<Pair<Integer, Integer>> listV = map.computeIfAbsent(v, k -> new ArrayList<>());
        listV.add(new Pair<>(u, weight));
    }

    @Override
    public void removeEdge(int u, int v) {
        List<Pair<Integer, Integer>> listU = map.get(u);
        if (listU != null) {
            listU.removeIf(pair -> pair.neighbor.equals(v));
        }

        List<Pair<Integer, Integer>> listV = map.get(v);
        if (listV != null) {
            listV.removeIf(pair -> pair.neighbor.equals(u));
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
    WeightedAdjListUndirectedGraph g = new WeightedAdjListUndirectedGraph();
    g.addEdge(1, 2, 5);
    g.addEdge(2, 3, 3);
    g.addEdge(3, 1, 2);
    g.addEdge(3, 4, 4);
    g.printGraph();
    System.out.println("Vertices: " + g.getNumberOfVertices());
    System.out.println("Edges: " + g.getNumberOfEdges());
//    g.removeVertex(3);
    g.removeEdge(3,4);
    System.out.println("Vertices: " + g.getNumberOfVertices());
    System.out.println("Edges: " + g.getNumberOfEdges());
    g.printGraph();
}
