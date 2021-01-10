package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;

public class GraphController {

    private final Graph<Address, Path> g;
    private final AddressDB aDB;
    private final PathDB pDB;

    public GraphController() {

        g = new Graph<>(true);
        aDB = new AddressDB();
        pDB = new PathDB();
    }

    public GraphController(AddressDB aDB, PathDB pDB) {

        g = new Graph<>(true);
        this.aDB = aDB;
        this.pDB = pDB;
    }

    public Graph<Address, Path> getGraph() {

        return g.clone();
    }

    public void fillGraph() throws SQLException {

        List<Address> la = aDB.getAddresses();
        List<Path> lp = pDB.getPaths(la);

        GraphAlgorithms.fillGraph(g, la, lp);
    }
}
