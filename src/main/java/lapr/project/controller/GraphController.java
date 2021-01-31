package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

/**
 * Controls the processes pertinent to graph manipulation and creation.
 *
 * @author lapr3-2020-g052
 */
public class GraphController {

    /**
     * The scooter graph whose weights are defined by distance.
     */
    private final Graph<Address, Path> gScooter;
    /**
     * The drone graph whose weights are defined by distance.
     */
    private final Graph<Address, Path> gDrone;
    /**
     * The scooter graph whose weights are defined by energy.
     */
    private final Graph<Address, Path> gScooterEnergy;
    /**
     * The drone graph whose weights are defined by energy.
     */
    private final Graph<Address, Path> gDroneEnergy;

    /**
     * Creates an instance of a GraphController.
     */
    public GraphController() {

        gScooter = new Graph<>(true);
        gDrone = new Graph<>(true);
        gScooterEnergy = new Graph<>(true);
        gDroneEnergy = new Graph<>(true);
    }

    /**
     * Returns the scooter graph whose weights are defined by distance.
     *
     * @return the scooter graph whose weights are defined by distance.
     */
    public Graph<Address, Path> getGraphScooter() {

        return gScooter.clone();
    }

    /**
     * Returns the drone graph whose weights are defined by distance.
     *
     * @return the drone graph whose weights are defined by distance.
     */
    public Graph<Address, Path> getGraphDrone() {

        return gDrone.clone();
    }

    /**
     * Returns the scooter graph whose weights are defined by energy.
     *
     * @return the scooter graph whose weights are defined by energy.
     */
    public Graph<Address, Path> getGraphScooterEnergy() {

        return gScooterEnergy.clone();
    }

    /**
     * Returns the drone graph whose weights are defined by energy.
     *
     * @return the drone graph whose weights are defined by energy.
     */
    public Graph<Address, Path> getGraphDroneEnergy() {

        return gDroneEnergy.clone();
    }

    /**
     * Initializes the process of filling the scooter graph whose weights are
     * defined by distance.
     *
     * @param la the list of addresses (vertexes).
     * @param lp the list of paths (edges).
     */
    public void fillGraphScooter(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraph(gScooter, la, lp);
    }

    /**
     * Initializes the process of filling the drone graph whose weights are
     * defined by distance.
     *
     * @param la the list of addresses (vertexes).
     * @param lp the list of paths (edges).
     */
    public void fillGraphDrone(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraph(gDrone, la, lp);
    }

    /**
     * Initializes the process of filling the scooter graph whose weights are
     * defined by energy.
     *
     * @param la the list of addresses (vertexes).
     * @param lp the list of paths (edges).
     */
    public void fillGraphScooterEnergy(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraphEnergy(true, gScooterEnergy, la, lp);
    }

    /**
     * Initializes the process of filling the drone graph whose weights are
     * defined by energy.
     *
     * @param la the list of addresses (vertexes).
     * @param lp the list of paths (edges).
     */
    public void fillGraphDroneEnergy(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraphEnergy(false, gDroneEnergy, la, lp);
    }

    /**
     * Initializes the process of obtaining the nearest pharmacy to a given
     * address, received as parameter.
     *
     * @param inverted true, if it's to calculate from the pharmacy to the
     * client, false if otherwise.
     * @param a the target address.
     * @param laPharma the list of pharmacy addresses.
     * @return the nearest pharmacy to the given address.
     */
    public Address getNearestPharmacy(boolean inverted, Address a, List<Address> laPharma) {

        return GraphAlgorithms.getNearestPharmacy(inverted, gDrone, a, laPharma);
    }

    /**
     * Initializes the process of calculating and obtaining the shortest path,
     * distance wise, between two addresses, received as parameters.
     *
     * @param scooterOrDrone true, if the vehicle in question to traverse in is
     * a scooter, false if it's a drone.
     * @param aOrig the origin address.
     * @param aDest the destination address.
     * @param shortPath the linked list which'll house the shortest path.
     * @return the accumulated weight (in this case, distance (meters)) of the
     * shortest path.
     */
    public double getShortestPath(boolean scooterOrDrone, Address aOrig, Address aDest, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.shortestPath(gScooter, aOrig, aDest, shortPath);

        } else {
            return GraphAlgorithms.shortestPath(gDrone, aOrig, aDest, shortPath);
        }
    }

    /**
     * Initializes the process of calculating and obtaining the shortest path,
     * energy wise, between two addresses, received as parameters.
     *
     * @param scooterOrDrone true, if the vehicle in question to traverse in is
     * a scooter, false if it's a drone.
     * @param aOrig the origin address.
     * @param aDest the destination address.
     * @param shortPath the linked list which'll house the shortest path.
     * @return the accumulated weight (in this case, energy (Wattshour)) of the
     * shortest path.
     */
    public double getShortestPathEnergy(boolean scooterOrDrone, Address aOrig, Address aDest, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.shortestPath(gScooterEnergy, aOrig, aDest, shortPath);

        } else {
            return GraphAlgorithms.shortestPath(gDroneEnergy, aOrig, aDest, shortPath);
        }
    }

    /**
     * Initializes the process of calculating and obtaining the shortest path,
     * distance wise, between two addresses, received as parameters, while
     * obligatorily passing through a predetermined set of nodes, also received
     * as parameter.
     *
     * @param scooterOrDrone true, if the vehicle in question to traverse in is
     * a scooter, false if it's a drone.
     * @param aOrig the origin address.
     * @param aDest the destination address.
     * @param nodes the nodes to be passed through compulsorily
     * @param shortPath the linked list which'll house the shortest path.
     * @return the accumulated weight (in this case, distance (meters)) of the
     * shortest path.
     */
    public double getShortestPathThroughNodes(boolean scooterOrDrone, Address aOrig, Address aDest,
            List<Address> nodes, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.getShortestPathThroughNodes(gScooter, nodes, shortPath, aOrig, aDest);

        } else {
            return GraphAlgorithms.getShortestPathThroughNodes(gDrone, nodes, shortPath, aOrig, aDest);
        }
    }

    /**
     * Initializes the process of calculating and obtaining the shortest path,
     * energy wise, between two addresses, received as parameters, while
     * obligatorily passing through a predetermined set of nodes, also received
     * as parameter.
     *
     * @param scooterOrDrone true, if the vehicle in question to traverse in is
     * a scooter, false if it's a drone.
     * @param aOrig the origin address.
     * @param aDest the destination address.
     * @param nodes the nodes to be passed through compulsorily
     * @param shortPath the linked list which'll house the shortest path.
     * @return the accumulated weight (in this case, energy (Wattshour)) of the
     * shortest path.
     */
    public double getShortestPathThroughNodesEnergy(boolean scooterOrDrone, Address aOrig, Address aDest,
            List<Address> nodes, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.getShortestPathThroughNodes(gScooterEnergy, nodes, shortPath, aOrig, aDest);

        } else {
            return GraphAlgorithms.getShortestPathThroughNodes(gDroneEnergy, nodes, shortPath, aOrig, aDest);
        }
    }

    /**
     * Initializes the process of exporting a voyage, received as parameter, to
     * a file whose name is also received as parameter. This particular
     * iteration of the writePathToFile algorithm, which is overloaded, handles
     * exporting voyages where the vehicle and other extraneous factors have
     * default values.
     *
     * @param scooterOrDrone true, if the vehicle in question to traverse in is
     * a scooter, false if it's a drone.
     * @param fileName the file's name.
     * @param la the voyage.
     * @param distance the total distance (in meters).
     * @param energy the total energy (in Wattshour).
     * @param v the vehicle type.
     * @return true, if it has been exported successfully. Otherwise, returns
     * false.
     */
    public boolean writePathToFile(boolean scooterOrDrone, String fileName, LinkedList<Address> la,
            double distance, double energy, String v) {

        if (scooterOrDrone) {
            return GraphAlgorithms.writePathToFile(fileName, gScooterEnergy, la, distance, energy, v);

        } else {
            return GraphAlgorithms.writePathToFile(fileName, gDroneEnergy, la, distance, energy, v);
        }
    }

    /**
     * Initializes the process of exporting a voyage, received as parameter, to
     * a file whose name is also received as parameter. This particular
     * iteration of the writePathToFile algorithm, which is overloaded, handles
     * exporting voyages where the vehicle type is a scooter and the extraneous
     * factors such as the list of products it carried and who drived the
     * scooter are known.
     *
     * @param fileName the file's name.
     * @param la the voyage.
     * @param distance the total distance (in meters).
     * @param energy the total energy (in Wattshour).
     * @param c the courier.
     * @param s the scooter.
     * @param lpro the list of products.
     * @return true, if it has been exported successfully. Otherwise, returns
     * false.
     */
    public boolean writePathToFile(String fileName, LinkedList<Address> la, double distance, double energy,
            Courier c, Scooter s, List<Product> lpro) {

        return GraphAlgorithms.writePathToFile(fileName, gScooterEnergy, la, distance, energy, c, s, lpro);
    }

    /**
     * Initializes the process of exporting a voyage, received as parameter, to
     * a file whose name is also received as parameter. This particular
     * iteration of the writePathToFile algorithm, which is overloaded, handles
     * exporting voyages where the vehicle type is a drone and the extraneous
     * factors such as the list of products it carried are known.
     *
     * @param fileName the file's name.
     * @param la the voyage.
     * @param distance the total distance (in meters).
     * @param energy the total energy (in Wattshour).
     * @param d the drone.
     * @param lpro the list of products.
     * @return true, if it has been exported successfully. Otherwise, returns
     * false.
     */
    public boolean writePathToFile(String fileName, LinkedList<Address> la, double distance, double energy,
            Drone d, List<Product> lpro) {

        return GraphAlgorithms.writePathToFile(fileName, gDroneEnergy, la, distance, energy, d, lpro);
    }
}
