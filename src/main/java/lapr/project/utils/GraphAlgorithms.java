package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Vehicle;

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

        allPaths(g, vOrig, vDest, path, paths);

        return paths;
    }

    public static int writePathsToFile(String fileName, int nrPaths, List<LinkedList<Address>> la,
            Graph<Address, Path> g, Courier c, Vehicle v, List<Product> lp) throws IOException {

        if (la.isEmpty()) {
            return 0;
        }
        if (nrPaths > la.size()) {
            nrPaths = la.size();
        }
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < nrPaths; i++) {

            bw.write("Path #" + (i + 1));
            bw.newLine();
            bw.write("Total Distance = " + PathAlgorithms.calcTotalDistance(la.get(i)) + "km.");
            bw.newLine();
            bw.write("Total Energy Consumption = " + PathAlgorithms.calcTotalEnergy(g, la.get(i), c, v, lp) + "W.");
            bw.newLine();

            for (int j = 0; j < la.get(i).size(); j++) {

                bw.write(la.get(i).get(j).getDescription() + ";");
            }
            bw.newLine();
        }
        bw.close();
        fw.close();

        return nrPaths;
    }

    public static double getShortestPathThroughNodes(Graph<Address, Path> g, List<Address> lNodes,
            LinkedList<Address> path, Address aOrig, Address aDest) {

        if (g == null || lNodes.isEmpty()) {
            return 0.0d;
        }
        List<LinkedList<Address>> combos = new ArrayList<>();
        generateCombinations(lNodes.size(), lNodes, combos, aOrig, aDest);

        double minLength = 0.0d;
        double currentLength;
        double shortestPathBufferLength;
        LinkedList<Address> workList = new LinkedList<>();
        LinkedList<Address> shortestPathBuffer = new LinkedList<>();

        for (LinkedList<Address> combo : combos) {

            workList.clear();
            currentLength = 0.0d;

            for (int i = 0; i < combo.size() - 1; i++) {

                shortestPathBuffer.clear();
                shortestPathBufferLength = shortestPath(g, combo.get(i), combo.get(i + 1), shortestPathBuffer);

                if (shortestPathBufferLength == 0) {
                    continue;
                }
                currentLength += shortestPathBufferLength;

                if (currentLength > minLength && minLength != 0) {
                    continue;
                }
                mergeLinkedLists(workList, shortestPathBuffer);
            }
            if (minLength == 0 || minLength > currentLength) {
                minLength = currentLength;
                path.clear();
                path.addAll(workList);
            }
        }
        return minLength;
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

        for (Address a : la) {
            g.insertVertex(a);
        }
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

    private static void generateCombinations(int n, List<Address> lNodes,
            List<LinkedList<Address>> combos, Address aOrig, Address aDest) {

        if (n == 1) {
            LinkedList<Address> newWorld = new LinkedList<>();
            newWorld.add(aOrig);
            newWorld.addAll(lNodes);
            newWorld.add(aDest);
            combos.add(newWorld);

        } else if (n > 1) {

            for (int i = 0; i < n - 1; i++) {

                generateCombinations(n - 1, lNodes, combos, aOrig, aDest);

                if (n % 2 == 0) {
                    Collections.swap(lNodes, i, n - 1);

                } else {
                    Collections.swap(lNodes, 0, n - 1);
                }
            }
            generateCombinations(n - 1, lNodes, combos, aOrig, aDest);
        }
    }

    private static void mergeLinkedLists(LinkedList<Address> lOrig, LinkedList<Address> lAddon) {

        if (lOrig.isEmpty()) {
            lOrig.addAll(lAddon);
            return;
        }
        if (!(lOrig.getLast() == lAddon.getFirst())) {
            throw new ArrayStoreException("Error merging Linked Lists : Head doesn't match Tail");
        }
        lAddon.removeFirst();
        lOrig.addAll(lAddon);
    }
}
