package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import static lapr.project.utils.Constants.*;

public class GraphAlgorithms {

    private GraphAlgorithms() {

    }

    public static void fillGraph(Graph<Address, Path> g, List<Address> la, List<Path> lp) {

        if (la != null) {
            for (Address a : la) {
                g.insertVertex(a);
            }
        }
        if (lp != null) {
            List<Address> lad = new ArrayList<>();
            g.vertices().forEach(lad::add);
            addPathsToGraph(g, lad, lp);
        }
    }

    public static void fillGraphEnergy(boolean scooterOrDrone, Graph<Address, Path> g, List<Address> la, List<Path> lp) {

        if (la != null) {
            for (Address a : la) {
                g.insertVertex(a);
            }
        }
        if (lp != null) {
            List<Address> lad = new ArrayList<>();
            g.vertices().forEach(lad::add);
            addPathsToGraphEnergy(scooterOrDrone, g, lad, lp);
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

    public static boolean writePathToFile(String fileName, Graph<Address, Path> g, LinkedList<Address> la,
            double distance, double energy, String vehicle) {

        if (la.isEmpty()) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            double totalEnergy = 0;

            bw.write("Vehicle Type -> " + vehicle + "\n");
            bw.write("Vehicle Specifications :\n");
            if ("Drone".equalsIgnoreCase(vehicle)) {
                bw.write("Drone Horizontal Speed = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_H_SPEED) + M_S_NEWLINE);
                bw.write("Drone Vertical Speed = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_V_SPEED) + M_S_NEWLINE);
                bw.write("Drone Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_WEIGHT) + KG_NEWLINE);
                bw.write("Drone Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_DRAG) + UNITLESS_NEWLINE);
                bw.write("Drone Frontal Area = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_FRONTAL) + "m2.\n");
                bw.write("Drone Width = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_WIDTH) + "m.\n");
            }
            if ("Scooter".equalsIgnoreCase(vehicle)) {
                bw.write("Scooter Speed = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_SPEED) + M_S_NEWLINE);
                bw.write("Scooter Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_WEIGHT) + KG_NEWLINE);
                bw.write("Courier Weight = " + String.format(Locale.ROOT, "%.2f", AVG_COURIER_WEIGHT) + KG_NEWLINE);
                bw.write("Scooter Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_DRAG) + UNITLESS_NEWLINE);
                bw.write("Scooter Frontal Area (considering upright human) = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_FRONTAL) + "m2.\n");
            }
            bw.newLine();
            bw.write("Projected Path :\n");
            bw.write("Total Distance = " + String.format(Locale.ROOT, "%.2f", distance) + "km.\n");
            bw.write("Total Energy Consumption = " + String.format(Locale.ROOT, "%.2f", energy) + WH_NEWLINE);
            bw.newLine();
            bw.write("Path Structure :\n");

            for (int i = 0; i < la.size() - 1; i++) {
                Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
                double distance2 = PathAlgorithms.calcDistance(la.get(i), la.get(i + 1));
                double roadSlope = Math.toDegrees(Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance2));

                bw.newLine();
                bw.write(p.getAddress1().getDescription() + " -> " + p.getAddress2().getDescription() + "\n");
                bw.write("Path Kinetic Coefficient = " + String.format(Locale.ROOT, "%.2f", p.getKineticCoeficient()) + UNITLESS_NEWLINE);
                bw.write("Path Wind Angle = " + String.format(Locale.ROOT, "%.2f", p.getWindAngle()) + " Degrees.\n");
                bw.write("Path Wind Speed = " + String.format(Locale.ROOT, "%.2f", p.getWindSpeed()) + M_S_NEWLINE);
                bw.write("Road Slope Angle = " + String.format(Locale.ROOT, "%.2f", roadSlope) + " Degrees.\n");
                bw.write("Distance = " + String.format(Locale.ROOT, "%.2f", distance2 / 1000) + "km.\n");
                if ("Drone".equalsIgnoreCase(vehicle)) {
                    totalEnergy += PathAlgorithms.calcDroneEnergy(p);
                    bw.write("Energy = " + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcDroneEnergy(p)) + WH_NEWLINE);
                }
                if ("Scooter".equalsIgnoreCase(vehicle)) {
                    bw.write("Energy = " + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcScooterEnergy(p)) + WH_NEWLINE);
                }
            }
            if ("Drone".equalsIgnoreCase(vehicle)) {
                bw.newLine();
                bw.write("Vertical Energy Consumption = " + String.format(Locale.ROOT, "%.2f", energy - totalEnergy) + WH_NEWLINE);
            }
        } catch (IOException ex) {
            Logger.getLogger(GraphAlgorithms.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean writePathToFile(String fileName, Graph<Address, Path> g, LinkedList<Address> la,
            double distance, double energy, Courier c, Scooter s, List<Product> lPro) {

        if (la.isEmpty()) {
            return false;
        }
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(fileName))) {

            bw2.write("Vehicle Type -> Scooter\n");
            bw2.write("Vehicle Specifications :\n");
            bw2.write("Scooter Speed = " + String.format(Locale.ROOT, "%.2f", s.getAverageSpeed()) + M_S_NEWLINE);
            bw2.write("Scooter Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", s.getWeight()) + KG_NEWLINE);
            bw2.write("Courier Weight = " + String.format(Locale.ROOT, "%.2f", c.getWeight()) + KG_NEWLINE);
            bw2.write("Load Weight = " + String.format(Locale.ROOT, "%.2f", lPro.stream().mapToDouble(Product::getWeight).sum()) + KG_NEWLINE);
            bw2.write("Scooter Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", s.getAerodynamicCoeficient()) + UNITLESS_NEWLINE);
            bw2.write("Scooter Frontal Area (considering upright human) = " + String.format(Locale.ROOT, "%.2f", s.getFrontalArea()) + "m2.\n");

            bw2.newLine();
            bw2.write("Projected Path :\n");
            bw2.write("Total Distance = " + String.format(Locale.ROOT, "%.2f", distance) + "km.\n");
            bw2.write("Total Energy Consumption = " + String.format(Locale.ROOT, "%.2f", energy) + WH_NEWLINE);
            bw2.newLine();
            bw2.write("Path Structure :\n");

            for (int i = 0; i < la.size() - 1; i++) {
                Path p2 = g.getEdge(la.get(i), la.get(i + 1)).getElement();
                double distance3 = PathAlgorithms.calcDistance(la.get(i), la.get(i + 1));
                double roadSlope = Math.toDegrees(Math.atan((p2.getAddress2().getAltitude() - p2.getAddress1().getAltitude()) / distance3));

                bw2.newLine();
                bw2.write(p2.getAddress1().getDescription() + " -> " + p2.getAddress2().getDescription() + "\n");
                bw2.write("Path Kinetic Coefficient = " + String.format(Locale.ROOT, "%.2f", p2.getKineticCoeficient()) + UNITLESS_NEWLINE);
                bw2.write("Path Wind Angle = " + String.format(Locale.ROOT, "%.2f", p2.getWindAngle()) + " Degrees.\n");
                bw2.write("Path Wind Speed = " + String.format(Locale.ROOT, "%.2f", p2.getWindSpeed()) + M_S_NEWLINE);
                bw2.write("Road Slope Angle = " + String.format(Locale.ROOT, "%.2f", roadSlope) + " Degrees.\n");
                bw2.write("Distance = " + String.format(Locale.ROOT, "%.2f", distance3 / 1000) + "km.\n");
                bw2.write("Energy = " + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcScooterEnergy(p2, c, s, lPro)) + WH_NEWLINE);
            }
        } catch (IOException ex) {
            Logger.getLogger(GraphAlgorithms.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean writePathToFile(String fileName, Graph<Address, Path> g, LinkedList<Address> la,
            double distance, double energy, Drone d, List<Product> lPro) {

        if (la.isEmpty()) {
            return false;
        }
        try (BufferedWriter bw3 = new BufferedWriter(new FileWriter(fileName))) {

            double totalEnergy = 0;

            bw3.write("Vehicle Type -> Drone\n");
            bw3.write("Vehicle Specifications :\n");
            bw3.write("Drone Horizontal Speed = " + String.format(Locale.ROOT, "%.2f", d.getAverageSpeed()) + M_S_NEWLINE);
            bw3.write("Drone Vertical Speed = " + String.format(Locale.ROOT, "%.2f", d.getAverageVerticalSpeed()) + M_S_NEWLINE);
            bw3.write("Drone Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", d.getWeight()) + KG_NEWLINE);
            bw3.write("Load Weight = " + String.format(Locale.ROOT, "%.2f", lPro.stream().mapToDouble(Product::getWeight).sum()) + KG_NEWLINE);
            bw3.write("Drone Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", d.getAerodynamicCoeficient()) + UNITLESS_NEWLINE);
            bw3.write("Drone Frontal Area = " + String.format(Locale.ROOT, "%.2f", d.getFrontalArea()) + "m2.\n");
            bw3.write("Drone Width = " + String.format(Locale.ROOT, "%.2f", d.getWidth()) + "m.\n");

            bw3.newLine();
            bw3.write("Projected Path :\n");
            bw3.write("Total Distance = " + String.format(Locale.ROOT, "%.2f", distance) + "km.\n");
            bw3.write("Total Energy Consumption = " + String.format(Locale.ROOT, "%.2f", energy) + WH_NEWLINE);
            bw3.newLine();
            bw3.write("Path Structure :\n");

            for (int i = 0; i < la.size() - 1; i++) {
                Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
                totalEnergy += PathAlgorithms.calcDroneEnergy(p);
                double distance4 = PathAlgorithms.calcDistance(la.get(i), la.get(i + 1));
                double roadSlope = Math.toDegrees(Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance4));

                bw3.newLine();
                bw3.write(p.getAddress1().getDescription() + " -> " + p.getAddress2().getDescription() + "\n");
                bw3.write("Path Kinetic Coefficient = " + String.format(Locale.ROOT, "%.2f", p.getKineticCoeficient()) + UNITLESS_NEWLINE);
                bw3.write("Path Wind Angle = " + String.format(Locale.ROOT, "%.2f", p.getWindAngle()) + " Degrees.\n");
                bw3.write("Path Wind Speed = " + String.format(Locale.ROOT, "%.2f", p.getWindSpeed()) + M_S_NEWLINE);
                bw3.write("Road Slope Angle = " + String.format(Locale.ROOT, "%.2f", roadSlope) + " Degrees.\n");
                bw3.write("Distance = " + String.format(Locale.ROOT, "%.2f", distance4 / 1000) + "km.\n");
                bw3.write("Energy = " + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcDroneEnergy(p)) + WH_NEWLINE);
            }
            bw3.newLine();
            bw3.write("Vertical Energy Consumption = " + String.format(Locale.ROOT, "%.2f", energy - totalEnergy) + WH_NEWLINE);

        } catch (IOException ex) {
            Logger.getLogger(GraphAlgorithms.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
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

    public static void generateCombinations(int n, List<Address> lNodes,
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

    public static void mergeLinkedLists(LinkedList<Address> lOrig, LinkedList<Address> lAddon) {

        if (lOrig.isEmpty()) {
            lOrig.addAll(lAddon);
            return;
        }
        if (lOrig.getLast() != lAddon.getFirst()) {
            throw new ArrayStoreException("Error merging Linked Lists : Head doesn't match Tail");
        }
        lAddon.removeFirst();
        lOrig.addAll(lAddon);
    }

    public static Address getNearestPharmacy(Graph<Address, Path> g, Address aOrig, List<Address> la) {

        if (la.isEmpty() || g == null || !g.validVertex(aOrig)) {
            return null;
        }
        LinkedList<Address> dummyList = new LinkedList<>();
        Address nearestPharmacy = la.get(0);
        double lowestDistance = shortestPath(g, aOrig, la.get(0), dummyList);
        double currentDistance;

        for (int i = 1; i < la.size(); i++) {

            dummyList = new LinkedList<>();
            currentDistance = shortestPath(g, aOrig, la.get(i), dummyList);

            if (currentDistance < lowestDistance) {
                lowestDistance = currentDistance;
                nearestPharmacy = la.get(i);
            }
        }
        return nearestPharmacy;
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

    private static void addPathsToGraphEnergy(boolean scooterOrDrone, Graph<Address, Path> g, List<Address> la, List<Path> lp) {

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

                if (scooterOrDrone) {
                    g.insertEdge(a1, a2, p, PathAlgorithms.calcScooterEnergy(p));

                } else {
                    g.insertEdge(a1, a2, p, PathAlgorithms.calcDroneEnergy(p));
                }
            }
        }
    }
}
