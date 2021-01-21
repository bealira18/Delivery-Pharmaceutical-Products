package lapr.project.controller;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.model.Vehicle;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

public class GraphController {

    private final Graph<Address, Path> gScooter;
    private final Graph<Address, Path> gDrone;

    public GraphController() {

        gScooter = new Graph<>(true);
        gDrone = new Graph<>(false);
    }

    public Graph<Address, Path> getGraphScooter() {

        return gScooter.clone();
    }

    public Graph<Address, Path> getGraphDrone() {

        return gDrone.clone();
    }

    public void fillGraphScooter(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraph(gScooter, la, lp);
    }

    public void fillGraphDrone(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraph(gDrone, la, lp);
    }

    public Address getNearestPharmacy(Address a, List<Address> laPharma) {

        return GraphAlgorithms.getNearestPharmacy(gDrone, a, laPharma);
    }

    public double getShortestPath(boolean scooterOrDrone, Address aOrig, Address aDest, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.shortestPath(gScooter, aOrig, aDest, shortPath);

        } else {
            return GraphAlgorithms.shortestPath(gDrone, aOrig, aDest, shortPath);
        }
    }

    public double getShortestPathThroughNodes(boolean scooterOrDrone, Address aOrig, Address aDest,
            List<Address> nodes, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.getShortestPathThroughNodes(gScooter, nodes, shortPath, aOrig, aDest);

        } else {
            return GraphAlgorithms.getShortestPathThroughNodes(gDrone, nodes, shortPath, aOrig, aDest);
        }
    }

    public boolean writePathToFile(String fileName, LinkedList<Address> la,
            double distance, double energy, Vehicle v) {

        return GraphAlgorithms.writePathToFile(fileName, la, distance, energy, v);
    }
}
