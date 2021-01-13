package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Vehicle;

public class PathAlgorithms {

    private PathAlgorithms() {

    }

    /**
     * Earth radius in Km.
     */
    private static final int EARTH_RADIUS = 6371;
    /**
     * Wind degrees (non-radian).
     */
    private static final int WIND_DEGREES = 45;
    /**
     * Wind velocity in m/s.
     */
    private static final int WIND_VELOCITY = 5;
    /**
     * Air density in Kg/m3.
     */
    private static final double AIR_DENSITY = 1.324;
    /**
     * Gravitational acceleration in 9.8 m/s2.
     */
    private static final double GRAVITATIONAL_ACCELERATION = 9.8;
    /**
     * Average vehicle speed in m/s.
     */
    private static final double AVG_VEHICLE_SPEED = 20;

    public static double calcDistance(Address a1, Address a2) {

        double lat1 = a1.getLatitude();
        double long1 = a1.getLongitude();
        double lat2 = a2.getLatitude();
        double long2 = a2.getLongitude();
        double alt1 = a1.getAltitude();
        double alt2 = a2.getAltitude();

        if (Double.compare(lat1, lat2) == 0 && Double.compare(long1, long2) == 0 && Double.compare(alt1, alt2) == 0) {
            return 0;
        }

        double latDistance = Math.toRadians(lat2 - lat1);
        double longDistance = Math.toRadians(long2 - long1);
        double firstVal = Math.pow(Math.sin(latDistance / 2), 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(longDistance / 2), 2);
        double secVal = 2 * Math.atan2(Math.sqrt(firstVal), Math.sqrt(1 - firstVal));

        double distance = EARTH_RADIUS * secVal * 1000;
        double height = alt1 - alt2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance) / 1000;
    }

    public static double calcEnergy(Path p, Courier c, Vehicle v, List<Product> lp) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double totalWeight = c.getWeight() + v.getWeight() + lp.stream().mapToDouble(Product::getWeight).sum();

        double relativeWind = WIND_VELOCITY * Math.cos(WIND_DEGREES);
        double relativeSpeed = relativeWind - AVG_VEHICLE_SPEED;

        double aeroDrag = 0.5 * AIR_DENSITY * v.getAerodynamicCoeficient() * v.getFrontalArea() * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * totalWeight * p.getKineticCoeficient();

        return (aeroDrag + groundDrag) * distance / 3600;
    }

    public static double calcTotalDistance(LinkedList<Address> la) {

        double totalDistance = 0;

        for (int i = 0; i < la.size() - 1; i++) {

            totalDistance += calcDistance(la.get(i), la.get(i + 1));
        }
        return totalDistance;
    }

    public static double calcTotalEnergy(Graph<Address, Path> g, LinkedList<Address> la, Courier c, Vehicle v, List<Product> lp) {

        double totalEnergy = 0;

        for (int i = 0; i < la.size() - 1; i++) {

            Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
            totalEnergy += calcEnergy(p, c, v, lp);
        }
        return totalEnergy;
    }
}
