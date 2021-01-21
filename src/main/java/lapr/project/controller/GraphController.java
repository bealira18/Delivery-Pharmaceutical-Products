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
    private final Graph<Address, Path> gScooterEnergy;
    private final Graph<Address, Path> gDroneEnergy;

    public GraphController() {

        gScooter = new Graph<>(true);
        gDrone = new Graph<>(true);
        gScooterEnergy = new Graph<>(true);
        gDroneEnergy = new Graph<>(true);
    }

    public Graph<Address, Path> getGraphScooter() {

        return gScooter.clone();
    }

    public Graph<Address, Path> getGraphDrone() {

        return gDrone.clone();
    }

    public Graph<Address, Path> getGraphScooterEnergy() {

        return gScooterEnergy.clone();
    }

    public Graph<Address, Path> getGraphDroneEnergy() {

        return gDroneEnergy.clone();
    }

    public void fillGraphScooter(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraph(gScooter, la, lp);
    }

    public void fillGraphDrone(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraph(gDrone, la, lp);
    }

    public void fillGraphScooterEnergy(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraphEnergy(true, gScooterEnergy, la, lp);
    }

    public void fillGraphDroneEnergy(List<Address> la, List<Path> lp) {

        GraphAlgorithms.fillGraphEnergy(false, gDroneEnergy, la, lp);
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

    public double getShortestPathEnergy(boolean scooterOrDrone, Address aOrig, Address aDest, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.shortestPath(gScooterEnergy, aOrig, aDest, shortPath);

        } else {
            return GraphAlgorithms.shortestPath(gDroneEnergy, aOrig, aDest, shortPath);
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

    public double getShortestPathThroughNodesEnergy(boolean scooterOrDrone, Address aOrig, Address aDest,
            List<Address> nodes, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.getShortestPathThroughNodes(gScooterEnergy, nodes, shortPath, aOrig, aDest);

        } else {
            return GraphAlgorithms.getShortestPathThroughNodes(gDroneEnergy, nodes, shortPath, aOrig, aDest);
        }
    }

    public boolean writePathToFile(String fileName, LinkedList<Address> la,
            double distance, double energy, Vehicle v) {

        return GraphAlgorithms.writePathToFile(fileName, la, distance, energy, v);
    }
}
