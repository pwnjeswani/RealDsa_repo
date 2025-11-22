package AdjList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractAdjListGraph {
    protected Map<Integer, List<Integer>> map;

    public AbstractAdjListGraph() {
        this.map = new HashMap<>();
    }
    public abstract int getNumberOfVertices();

    public abstract int getNumberOfEdges();

    public abstract void removeVertex(int v);

    public abstract void addEdge(int u, int v);


    public abstract void removeEdge(int u, int v);

    public abstract boolean hasEdge(int u, int v);

    public abstract ArrayList<Integer> getNeighbors(int v);


    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Integer node = entry.getKey();
            List<Integer> neighbours = entry.getValue();
            System.out.print("Node: " + node + " -> ");
            for (int n : neighbours) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
