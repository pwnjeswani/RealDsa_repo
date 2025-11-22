package AdjList;

import java.util.*;

public abstract class AbstractWeightedAdjListGraph {

    protected Map<Integer, List<Pair<Integer, Integer>>> map;

    public AbstractWeightedAdjListGraph() {
        this.map = new HashMap<>();
    }

    public abstract int getNumberOfVertices();

    public abstract int getNumberOfEdges();

    public abstract void removeVertex(int v);

    public abstract void addEdge(int u, int v, int weight);

    public abstract void removeEdge(int u, int v);

    public abstract boolean hasEdge(int u, int v);

    public abstract List<Pair<Integer, Integer>> getNeighbors(int v);

    public void printGraph() {
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : map.entrySet()) {
            Integer node = entry.getKey();
            List<Pair<Integer, Integer>> neighbours = entry.getValue();
            System.out.print("Node: " + node + " -> ");
            for (Pair<Integer, Integer> p : neighbours) {
                System.out.print("(" + p.neighbor + "," + p.weight + ") ");
            }
            System.out.println();
        }
    }

    public static class Pair<N, W> {
        public N neighbor;
        public W weight;

        public Pair(N neighbor, W weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }
}
