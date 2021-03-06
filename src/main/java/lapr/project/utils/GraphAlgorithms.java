package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import static lapr.project.utils.Constants.*;

/**
 * Class responsible for the handling and obtaining of graph information. As
 * such, it contains a myriad of algorithms to manipulate, calculate, create and
 * write information pertaining to graphs. More specifically, adjacent matrix
 * graphs.
 *
 * @author lapr3-2020-g052
 */
public class GraphAlgorithms {

    /**
     * Private constructor to hide the implicit public one.
     */
    private GraphAlgorithms() {

    }

    /**
     * Method responsible for filling the graph, received as parameter with the
     * addresses (vertexes) contained in the list of addresses received as
     * parameter and their respective paths (edges) contained in the list of
     * paths received as parameter. The weights of the edges contained in this
     * particular graph are denoted by distance.
     *
     * @param g the graph to be filled.
     * @param la list of addresses (vertexes).
     * @param lp list of paths (edges).
     */
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

    /**
     * Method responsible for filling the graph, received as parameter with the
     * addresses (vertexes) contained in the list of addresses received as
     * parameter and their respective paths (edges) contained in the list of
     * paths received as parameter. The weights of the edges contained in this
     * particular graph are denoted by energy.
     *
     * @param scooterOrDrone if it's for a scooter (true), or drone (false).
     * @param g the graph to be filled.
     * @param la list of addresses (vertexes).
     * @param lp list of paths (edges).
     */
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

    /**
     * Method responsible for obtaining the shortest path between two vertexes,
     * received as parameters, within a given graph, also received as parameter.
     * The resulting shortest path will be contained within the LinkedList
     * received as parameter.
     *
     * @param <V> vertex.
     * @param <E> edge.
     * @param g graph.
     * @param vOrig origin vertex.
     * @param vDest destination vertex.
     * @param shortPath shortest path LinkedList.
     * @return the accumulated weight of the edges belonging to the shortest
     * path.
     */
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
     * Method responsible for exporting all relevant information pertaining to a
     * given voyage to a file, whose name is received as parameter. All
     * information in this particular iteration of the method, which is
     * overloaded, is default information regarding the vehicle used and
     * extraneous information such as the courier information (which is also by
     * default).
     *
     * @param fileName the file's name.
     * @param g the graph.
     * @param la the path to be exported.
     * @param distance the distance of the voyage.
     * @param energy the energy required for the voyage.
     * @param vehicle the type of vehicle (in this project's current
     * implementation, only 'Scooter' and 'Drone' are implemented).
     * @return true, if the information has been exported successfully,
     * otherwise returns false.
     */
    public static boolean writePathToFile(String fileName, Graph<Address, Path> g, LinkedList<Address> la,
            double distance, double energy, String vehicle) {

        if (la.isEmpty()) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            double totalEnergy = 0;

            bw.write("Vehicle Type -> " + vehicle + "\n");
            bw.write(V_SPECS);
            if (D_STRING.equalsIgnoreCase(vehicle)) {
                bw.write("Drone Horizontal Speed = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_H_SPEED) + M_S_NEWLINE);
                bw.write("Drone Vertical Speed = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_V_SPEED) + M_S_NEWLINE);
                bw.write("Drone Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_WEIGHT) + KG_NEWLINE);
                bw.write("Drone Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_DRAG) + UNITLESS_NEWLINE);
                bw.write("Drone Frontal Area = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_FRONTAL) + M2_NEWLINE);
                bw.write("Drone Width = " + String.format(Locale.ROOT, "%.2f", AVG_DRONE_WIDTH) + "m.\n");
            }
            if (S_STRING.equalsIgnoreCase(vehicle)) {
                bw.write("Scooter Speed = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_SPEED) + M_S_NEWLINE);
                bw.write("Scooter Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_WEIGHT) + KG_NEWLINE);
                bw.write("Courier Weight = " + String.format(Locale.ROOT, "%.2f", AVG_COURIER_WEIGHT) + KG_NEWLINE);
                bw.write("Scooter Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_DRAG) + UNITLESS_NEWLINE);
                bw.write("Scooter Frontal Area (considering upright human) = " + String.format(Locale.ROOT, "%.2f", AVG_SCOOTER_FRONTAL) + M2_NEWLINE);
            }
            bw.newLine();
            bw.write(P_PATH);
            bw.write(T_DIST + String.format(Locale.ROOT, "%.2f", distance) + KM_NEWLINE);
            bw.write(T_E_CONS + String.format(Locale.ROOT, "%.2f", energy) + WH_NEWLINE);
            if (D_STRING.equalsIgnoreCase(vehicle)) {
                bw.write(T_OF_TR + String.format(Locale.ROOT, "%.0f", distance * 1000 / AVG_DRONE_H_SPEED) + SEC_NEWLINE);
            }
            if (S_STRING.equalsIgnoreCase(vehicle)) {
                bw.write(T_OF_TR + String.format(Locale.ROOT, "%.0f", distance * 1000 / AVG_SCOOTER_SPEED) + SEC_NEWLINE);
            }
            bw.newLine();
            bw.write(P_STR);

            for (int i = 0; i < la.size() - 1; i++) {
                Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
                double distance2 = PathAlgorithms.calcDistance(la.get(i), la.get(i + 1));
                double roadSlope = Math.toDegrees(Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance2));

                bw.newLine();
                bw.write(p.getAddress1().getDescription() + " -> " + p.getAddress2().getDescription() + "\n");
                bw.write(P_KIN_CO + String.format(Locale.ROOT, "%.3f", p.getKineticCoeficient()) + UNITLESS_NEWLINE);
                bw.write(P_WIN_A + String.format(Locale.ROOT, "%.2f", p.getWindAngle()) + D_NEWLINE);
                bw.write(P_WIN_S + String.format(Locale.ROOT, "%.2f", p.getWindSpeed()) + M_S_NEWLINE);
                bw.write(R_SLO_A + String.format(Locale.ROOT, "%.2f", roadSlope) + D_NEWLINE);
                bw.write(DIST + String.format(Locale.ROOT, "%.2f", distance2 / 1000) + KM_NEWLINE);
                if (D_STRING.equalsIgnoreCase(vehicle)) {
                    totalEnergy += PathAlgorithms.calcDroneEnergy(p);
                    bw.write(ENERGY + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcDroneEnergy(p)) + WH_NEWLINE);
                }
                if (S_STRING.equalsIgnoreCase(vehicle)) {
                    bw.write(ENERGY + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcScooterEnergy(p)) + WH_NEWLINE);
                }
            }
            if (D_STRING.equalsIgnoreCase(vehicle)) {
                bw.newLine();
                bw.write("Vertical Energy Consumption = " + String.format(Locale.ROOT, "%.2f", energy - totalEnergy) + WH_NEWLINE);
            }
        } catch (IOException ex) {
            Logger.getLogger(GraphAlgorithms.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Method responsible for exporting all relevant information pertaining to a
     * given voyage, traversed by a scooter driven by a courier carrying a list
     * of products, all received as parameters, to a file, whose name is
     * received as parameter.
     *
     * @param fileName the file's name.
     * @param g the graph.
     * @param la the path to be exported.
     * @param distance the distance of the voyage.
     * @param energy the energy required for the voyage.
     * @param c the courier.
     * @param s the scooter.
     * @param lPro the list of products.
     * @return true, if the information has been exported successfully,
     * otherwise returns false.
     */
    public static boolean writePathToFile(String fileName, Graph<Address, Path> g, LinkedList<Address> la,
            double distance, double energy, Courier c, Scooter s, List<Product> lPro) {

        if (la.isEmpty()) {
            return false;
        }
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(fileName))) {

            bw2.write("Vehicle Type -> Scooter\n");
            bw2.write(V_SPECS);
            bw2.write("Scooter Speed = " + String.format(Locale.ROOT, "%.2f", s.getAverageSpeed()) + M_S_NEWLINE);
            bw2.write("Scooter Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", s.getWeight()) + KG_NEWLINE);
            bw2.write("Courier Weight = " + String.format(Locale.ROOT, "%.2f", c.getWeight()) + KG_NEWLINE);
            bw2.write("Load Weight = " + String.format(Locale.ROOT, "%.2f", lPro.stream().mapToDouble(Product::getWeight).sum()) + KG_NEWLINE);
            bw2.write("Scooter Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", s.getAerodynamicCoeficient()) + UNITLESS_NEWLINE);
            bw2.write("Scooter Frontal Area (considering upright human) = " + String.format(Locale.ROOT, "%.2f", s.getFrontalArea()) + M2_NEWLINE);

            bw2.newLine();
            bw2.write(P_PATH);
            bw2.write(T_DIST + String.format(Locale.ROOT, "%.2f", distance) + KM_NEWLINE);
            bw2.write(T_E_CONS + String.format(Locale.ROOT, "%.2f", energy) + WH_NEWLINE);
            bw2.write(T_OF_TR + String.format(Locale.ROOT, "%.0f", distance * 1000 / s.getAverageSpeed()) + SEC_NEWLINE);
            bw2.newLine();
            bw2.write(P_STR);

            for (int i = 0; i < la.size() - 1; i++) {
                Path p2 = g.getEdge(la.get(i), la.get(i + 1)).getElement();
                double distance3 = PathAlgorithms.calcDistance(la.get(i), la.get(i + 1));
                double roadSlope = Math.toDegrees(Math.atan((p2.getAddress2().getAltitude() - p2.getAddress1().getAltitude()) / distance3));

                bw2.newLine();
                bw2.write(p2.getAddress1().getDescription() + " -> " + p2.getAddress2().getDescription() + "\n");
                bw2.write(P_KIN_CO + String.format(Locale.ROOT, "%.3f", p2.getKineticCoeficient()) + UNITLESS_NEWLINE);
                bw2.write(P_WIN_A + String.format(Locale.ROOT, "%.2f", p2.getWindAngle()) + D_NEWLINE);
                bw2.write(P_WIN_S + String.format(Locale.ROOT, "%.2f", p2.getWindSpeed()) + M_S_NEWLINE);
                bw2.write(R_SLO_A + String.format(Locale.ROOT, "%.2f", roadSlope) + D_NEWLINE);
                bw2.write(DIST + String.format(Locale.ROOT, "%.2f", distance3 / 1000) + KM_NEWLINE);
                bw2.write(ENERGY + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcScooterEnergy(p2, c, s, lPro)) + WH_NEWLINE);
            }
        } catch (IOException ex) {
            Logger.getLogger(GraphAlgorithms.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Method responsible for exporting all relevant information pertaining to a
     * given voyage, traversed by a drone carrying a list of products, all
     * received as parameters, to a file, whose name is received as parameter.
     *
     * @param fileName the file's name.
     * @param g the graph.
     * @param la the path to be exported.
     * @param distance the distance of the voyage.
     * @param energy the energy required for the voyage.
     * @param d the drone.
     * @param lPro the list of products.
     * @return true, if the information has been exported successfully,
     * otherwise returns false.
     */
    public static boolean writePathToFile(String fileName, Graph<Address, Path> g, LinkedList<Address> la,
            double distance, double energy, Drone d, List<Product> lPro) {

        if (la.isEmpty()) {
            return false;
        }
        try (BufferedWriter bw3 = new BufferedWriter(new FileWriter(fileName))) {

            double totalEnergy = 0;

            bw3.write("Vehicle Type -> Drone\n");
            bw3.write(V_SPECS);
            bw3.write("Drone Horizontal Speed = " + String.format(Locale.ROOT, "%.2f", d.getAverageSpeed()) + M_S_NEWLINE);
            bw3.write("Drone Vertical Speed = " + String.format(Locale.ROOT, "%.2f", d.getAverageVerticalSpeed()) + M_S_NEWLINE);
            bw3.write("Drone Weight (excluding load) = " + String.format(Locale.ROOT, "%.2f", d.getWeight()) + KG_NEWLINE);
            bw3.write("Load Weight = " + String.format(Locale.ROOT, "%.2f", lPro.stream().mapToDouble(Product::getWeight).sum()) + KG_NEWLINE);
            bw3.write("Drone Aerodynamic Coefficient = " + String.format(Locale.ROOT, "%.2f", d.getAerodynamicCoeficient()) + UNITLESS_NEWLINE);
            bw3.write("Drone Frontal Area = " + String.format(Locale.ROOT, "%.2f", d.getFrontalArea()) + M2_NEWLINE);
            bw3.write("Drone Width = " + String.format(Locale.ROOT, "%.2f", d.getWidth()) + "m.\n");

            bw3.newLine();
            bw3.write(P_PATH);
            bw3.write(T_DIST + String.format(Locale.ROOT, "%.2f", distance) + KM_NEWLINE);
            bw3.write(T_E_CONS + String.format(Locale.ROOT, "%.2f", energy) + WH_NEWLINE);
            bw3.write(T_OF_TR + String.format(Locale.ROOT, "%.0f", distance * 1000 / d.getAverageSpeed()) + SEC_NEWLINE);
            bw3.newLine();
            bw3.write(P_STR);

            for (int i = 0; i < la.size() - 1; i++) {
                Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
                totalEnergy += PathAlgorithms.calcDroneEnergy(p);
                double distance4 = PathAlgorithms.calcDistance(la.get(i), la.get(i + 1));
                double roadSlope = Math.toDegrees(Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance4));

                bw3.newLine();
                bw3.write(p.getAddress1().getDescription() + " -> " + p.getAddress2().getDescription() + "\n");
                bw3.write(P_KIN_CO + String.format(Locale.ROOT, "%.3f", p.getKineticCoeficient()) + UNITLESS_NEWLINE);
                bw3.write(P_WIN_A + String.format(Locale.ROOT, "%.2f", p.getWindAngle()) + D_NEWLINE);
                bw3.write(P_WIN_S + String.format(Locale.ROOT, "%.2f", p.getWindSpeed()) + M_S_NEWLINE);
                bw3.write(R_SLO_A + String.format(Locale.ROOT, "%.2f", roadSlope) + D_NEWLINE);
                bw3.write(DIST + String.format(Locale.ROOT, "%.2f", distance4 / 1000) + KM_NEWLINE);
                bw3.write(ENERGY + String.format(Locale.ROOT, "%.2f", PathAlgorithms.calcDroneEnergy(p)) + WH_NEWLINE);
            }
            bw3.newLine();
            bw3.write("Vertical Energy Consumption = " + String.format(Locale.ROOT, "%.2f", energy - totalEnergy) + WH_NEWLINE);

        } catch (IOException ex) {
            Logger.getLogger(GraphAlgorithms.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Method responsible for obtaining the shortest path between two vertexes,
     * received as parameters, within a given graph, also received as parameter,
     * while obligatorily passing through a predetermined set of nodes, also
     * received as parameter. The resulting shortest path will be contained
     * within the LinkedList received as parameter.
     *
     * @param g the graph.
     * @param lNodes the list of nodes to be passed through.
     * @param path shortest path LinkedList.
     * @param aOrig the origin vertex.
     * @param aDest the destination vertex.
     * @return the accumulated weight of the edges belonging to the shortest
     * path.
     */
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
     * Method responsible for generating all possible combinations (i.e.
     * permutations) between a set of nodes.
     *
     * @param n the number of nodes.
     * @param lNodes the list of nodes.
     * @param combos the list to be filled with all permutations.
     * @param aOrig the origin vertex.
     * @param aDest the destination vertex.
     */
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

    /**
     * Method responsible for merging two linked lists.
     *
     * @param lOrig the original linked list.
     * @param lAddon the linked list to be added to the original linked list.
     */
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

    /**
     * Method responsible for obtaining, in this case, the nearest pharmacy
     * address to a given address (in this particular case, a client's address,
     * but could be any address). Assuming the graph, received as parameter, is
     * directed, it can calculate the nearest pharmacy in two ways. From the
     * pharmacy to the client, and from the client to the pharmacy, depending on
     * the needs of the user.
     *
     * @param inverted true if the goal is to locate the nearest pharmacy by
     * checking the distance from the pharmacy to the client, false if
     * otherwise.
     * @param g the graph.
     * @param aDest the destination vertex.
     * @param la the list of pharmacy addresses.
     * @return the address of the nearest pharmacy.
     */
    public static Address getNearestPharmacy(boolean inverted, Graph<Address, Path> g, Address aDest, List<Address> la) {

        if (la.isEmpty() || g == null || !g.validVertex(aDest)) {
            return null;
        }
        Address nearestPharmacy = null;
        double lowestDistance = Double.MAX_VALUE;
        double currentDistance;

        for (int i = 0; i < la.size(); i++) {

            if (g.validVertex(la.get(i))) {

                LinkedList<Address> dummyList = new LinkedList<>();

                if (inverted) {
                    currentDistance = shortestPath(g, la.get(i), aDest, dummyList);

                } else {
                    currentDistance = shortestPath(g, aDest, la.get(i), dummyList);
                }
                if (currentDistance < lowestDistance) {
                    lowestDistance = currentDistance;
                    nearestPharmacy = la.get(i);
                }
            }
        }
        return nearestPharmacy;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param <V> vertex.
     * @param <E> edge.
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
     * @param <V> vertex.
     * @param <E> edge.
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

    /**
     * Method responsible for adding all paths contained in a list of paths
     * received as parameter to the graph, also received as parameter. This
     * particular iteration of the algorithm weighs the paths (i.e. edges) in
     * distance.
     *
     * @param g the graph for the paths to be added onto.
     * @param la the list of addresses whose paths contains.
     * @param lp the list of paths.
     */
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
     * Method responsible for adding all paths contained in a list of paths
     * received as parameter to the graph, also received as parameter. This
     * particular iteration of the algorithm weighs the paths (i.e. edges) in
     * energy.
     *
     * @param scooterOrDrone true, if it's for a scooter graph, false if it's
     * for a drone graph.
     * @param g the graph for the paths to be added onto.
     * @param la the list of addresses whose paths contains.
     * @param lp the list of paths.
     */
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
