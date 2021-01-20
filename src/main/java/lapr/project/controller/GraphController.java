package lapr.project.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

public class GraphController {

    private final Graph<Address, Path> gScooter;
    private final Graph<Address, Path> gDrone;
    private final AddressDB aDB;
    private final PathDB pDB;

    public GraphController() {

        gScooter = new Graph<>(true);
        gDrone = new Graph<>(false);
        aDB = new AddressDB();
        pDB = new PathDB();
    }

    public GraphController(AddressDB aDB, PathDB pDB) {

        gScooter = new Graph<>(true);
        gDrone = new Graph<>(false);
        this.aDB = aDB;
        this.pDB = pDB;
    }

    public Graph<Address, Path> getGraphScooter() {

        return gScooter.clone();
    }

    public Graph<Address, Path> getGraphDrone() {

        return gDrone.clone();
    }

    public void fillGraphScooter() throws SQLException {

        List<Address> laScooter = aDB.getAddresses();
        List<Path> lpScooter = pDB.getPaths(laScooter);

        GraphAlgorithms.fillGraph(gScooter, laScooter, lpScooter);
    }

    public void fillGraphDrone() throws SQLException {

        List<Address> laDrone = aDB.getAddresses();
        List<Path> lpDrone = pDB.getPaths(laDrone);

        GraphAlgorithms.fillGraph(gDrone, laDrone, lpDrone);
    }

    public Address getNearestPharmacy(Address a) throws SQLException {

        List<Address> la = aDB.getPharmacyAddresses();

        return GraphAlgorithms.getNearestPharmacy(gDrone, a, la);
    }

    public double getShortestPath(boolean scooterOrDrone, Address aOrig, Address aDest, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.shortestPath(gScooter, aOrig, aDest, shortPath);

        } else {
            return GraphAlgorithms.shortestPath(gDrone, aOrig, aDest, shortPath);
        }
    }

    public double getShortestPathThroughNodes(boolean scooterOrDrone, Address aOrig, Address aDest, List<Address> nodes, LinkedList<Address> shortPath) {

        if (scooterOrDrone) {
            return GraphAlgorithms.getShortestPathThroughNodes(gScooter, nodes, shortPath, aOrig, aDest);

        } else {
            return GraphAlgorithms.getShortestPathThroughNodes(gDrone, nodes, shortPath, aOrig, aDest);
        }
    }
}
