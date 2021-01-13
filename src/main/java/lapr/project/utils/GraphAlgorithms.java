package lapr.project.utils;

import java.lang.reflect.Array;
import java.util.*;
import lapr.project.model.Address;
import lapr.project.model.Path;

public class GraphAlgorithms {

    private GraphAlgorithms() {

    }

    public static void fillGraph(Graph<Address, Path> g, List<Address> la, List<Path> lp) {

        if (la != null) {
            addAddressesToGraph(g, la);
        }
        if (lp != null) {
            addPathsToGraph(g, la, lp);
        }
    }

    //shortest-path between vOrig and vDest
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }
        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts];

        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) Array.newInstance(vOrig.getClass(), nverts);
        double[] dist = new double[nverts];

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = null;
        }
        shortestPathLength(g, vOrig, visited, pathKeys, dist);
        double lengthPath = dist[g.getKey(vDest)];

        if (lengthPath != Double.MAX_VALUE) {
            getPath(g, vOrig, vDest, pathKeys, shortPath);
            return lengthPath;
        }
        return 0;
    }

    /**
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        LinkedList<V> path = new LinkedList<>();

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return new ArrayList<>();
        }
        ArrayList<LinkedList<V>> paths = new ArrayList<>();

        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            allPaths(g, vOrig, vDest, path, paths);
        }
        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, boolean[] visited, V[] pathKeys, double[] dist) {

        int vKey = g.getKey(vOrig);
        dist[vKey] = 0;
        pathKeys[vKey] = vOrig;

        while (vOrig != null) {

            vKey = g.getKey(vOrig);
            visited[vKey] = true;

            for (V vAdj : g.adjVertices(vOrig)) {

                int vKeyAdj = g.getKey(vAdj);
                Edge<V, E> edge = g.getEdge(vOrig, vAdj);

                if (!visited[vKeyAdj] && dist[vKeyAdj] > dist[vKey] + edge.getWeight()) {
                    dist[vKeyAdj] = dist[vKey] + edge.getWeight();
                    pathKeys[vKeyAdj] = vOrig;
                }
            }
            double minDist = Double.MAX_VALUE;
            vOrig = null;

            for (V vert : g.vertices()) {

                int i = g.getKey(vert);

                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param <V>
     * @param <E>
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] pathKeys, LinkedList<V> path) {

        if (vOrig.equals(vDest)) {
            path.push(vOrig);

        } else {
            path.push(vDest);
            int vKey = g.getKey(vDest);
            vDest = pathKeys[vKey];
            getPath(g, vOrig, vDest, pathKeys, path);
        }
    }

    private static void addAddressesToGraph(Graph<Address, Path> g, List<Address> la) {

        la.forEach(a -> {
            g.insertVertex(a);
        });
    }

    private static void addPathsToGraph(Graph<Address, Path> g, List<Address> la, List<Path> lp) {

        Address a1 = null;
        Address a2 = null;

        for (Path p : lp) {

            for (Address a : la) {

                if (p.getAddress1().getDescription().equals(a.getDescription())) {
                    a1 = a;
                }
                if (p.getAddress2().getDescription().equals(a.getDescription())) {
                    a2 = a;
                }
            }
            if (a1 != null && a2 != null) {
                g.insertEdge(a1, a2, p, PathAlgorithms.calcDistance(a1, a2));
            }
        }
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path //* @param visited
     * set of discovered vertices
     * @param path stack with vertices of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        path.push(vOrig);

        for (V vAdj : g.adjVertices(vOrig)) {

            if (vAdj.equals(vDest)) {
                path.push(vDest);
                paths.add(path);
                path.pop();

            } else {
                if (!path.contains(vAdj)) {
                    allPaths(g, vAdj, vDest, path, paths);
                }
            }
        }
        path.pop();
    }
}
