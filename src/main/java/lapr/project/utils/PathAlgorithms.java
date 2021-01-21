package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Scooter;

public class PathAlgorithms {

    private PathAlgorithms() {

    }

    /**
     * Earth radius in Km. Irrelevant for lower distances.
     */
    private static final int EARTH_RADIUS = 6371;
    /**
     * Wind degrees (non-radian).
     */
    private static final double AIR_DENSITY = 1.2041;
    /**
     * Gravitational acceleration in 9.8 m/s2.
     */
    private static final double GRAVITATIONAL_ACCELERATION = 9.8;
    /**
     * Average scooter speed in m/s.
     */
    private static final double AVG_SCOOTER_SPEED = 8.9;
    /**
     * Average scooter weight in kg.
     */
    private static final double AVG_SCOOTER_WEIGHT = 15;
    /**
     * Average scooter drag.
     */
    private static final double AVG_SCOOTER_DRAG = 1.1;
    /**
     * Average scooter frontal area in m2.
     */
    private static final double AVG_SCOOTER_FRONTAL = 0.5;
    /**
     * Average commercial drone horizontal speed in m/s.
     */
    private static final double AVG_DRONE_H_SPEED = 22.36;
    /**
     * Average commercial drone vertical speed in m/s.
     */
    private static final double AVG_DRONE_V_SPEED = 9;
    /**
     * Average drone weight in kg.
     */
    private static final double AVG_DRONE_WEIGHT = 5;
    /**
     * Average drone drag.
     */
    private static final double AVG_DRONE_DRAG = 0.6;
    /**
     * Average drone frontal area in m2.
     */
    private static final double AVG_DRONE_FRONTAL = 0.4;
    /**
     * Average courier weight in kg.
     */
    private static final double AVG_COURIER_WEIGHT = 70;
    /**
     * Drone altitude in meters, taking into account it parks at 10m above
     * ground.
     */
    private static final double DRONE_ALTITUDE = 140;
    /**
     * Average drone width in m.
     */
    private static final double AVG_DRONE_WIDTH = 0.5;
    /**
     * How many Wh a J is worth.
     */
    private static final double JOULE_TO_WATTHOUR_CONVERTER = 0.00027777777777778;

    public static double calcDistance(Address a1, Address a2) {

        double lat1 = a1.getLatitude();
        double long1 = a1.getLongitude();
        double lat2 = a2.getLatitude();
        double long2 = a2.getLongitude();
        double alt1 = a1.getAltitude();
        double alt2 = a2.getAltitude();

        double latDistance = Math.toRadians(lat2 - lat1);
        double longDistance = Math.toRadians(long2 - long1);
        double firstVal = Math.pow(Math.sin(latDistance / 2), 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(longDistance / 2), 2);
        double secVal = 2 * Math.atan2(Math.sqrt(firstVal), Math.sqrt(1 - firstVal));

        double distance = EARTH_RADIUS * secVal * 1000;
        double height = alt1 - alt2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public static double calcScooterEnergy(Path p) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), AVG_SCOOTER_SPEED);
        double aeroDrag = 0.5 * AIR_DENSITY * AVG_SCOOTER_DRAG * AVG_SCOOTER_FRONTAL * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * (AVG_COURIER_WEIGHT + AVG_SCOOTER_WEIGHT) * p.getKineticCoeficient();
        double totalForce = aeroDrag + groundDrag;

        return totalForce * AVG_SCOOTER_SPEED * calcTime(distance, AVG_SCOOTER_SPEED) * JOULE_TO_WATTHOUR_CONVERTER;
    }

    public static double calcScooterEnergy(Path p, Courier c, List<Product> lp) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double totalWeight = c.getWeight() + AVG_SCOOTER_WEIGHT + lp.stream().mapToDouble(Product::getWeight).sum();

        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), AVG_SCOOTER_SPEED);
        double aeroDrag = 0.5 * AIR_DENSITY * AVG_SCOOTER_DRAG * AVG_SCOOTER_FRONTAL * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * totalWeight * p.getKineticCoeficient();
        double totalForce = aeroDrag + groundDrag;

        return totalForce * AVG_SCOOTER_SPEED * calcTime(distance, AVG_SCOOTER_SPEED) * JOULE_TO_WATTHOUR_CONVERTER;
    }

    public static double calcScooterEnergy(Path p, Courier c, Scooter s, List<Product> lp) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double totalWeight = c.getWeight() + s.getWeight() + lp.stream().mapToDouble(Product::getWeight).sum();

        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), s.getAverageSpeed());
        double aeroDrag = 0.5 * AIR_DENSITY * s.getAerodynamicCoeficient() * s.getFrontalArea() * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * totalWeight * p.getKineticCoeficient();
        double totalForce = aeroDrag + groundDrag;

        return totalForce * s.getAverageSpeed() * calcTime(distance, s.getAverageSpeed()) * JOULE_TO_WATTHOUR_CONVERTER;
    }

    public static double calcDroneEnergy(Path p) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), AVG_DRONE_H_SPEED);

        double horizontalPower = 0.5 * AIR_DENSITY * AVG_DRONE_DRAG * AVG_DRONE_FRONTAL * Math.pow(relativeSpeed, 3);
        double liftPower = Math.pow(AVG_DRONE_WEIGHT, 2) / (AIR_DENSITY * Math.pow(AVG_DRONE_WIDTH, 2) * relativeSpeed);
        double tPower = horizontalPower + liftPower;
        double time = calcTime(distance, AVG_DRONE_H_SPEED);

        return tPower * time * JOULE_TO_WATTHOUR_CONVERTER;
    }

    public static double calcDroneEnergy(Path p, List<Product> lp) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double totalWeight = AVG_DRONE_WEIGHT + lp.stream().mapToDouble(Product::getWeight).sum();
        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), AVG_DRONE_H_SPEED);

        double horizontalPower = 0.5 * AIR_DENSITY * AVG_DRONE_DRAG * AVG_DRONE_FRONTAL * Math.pow(relativeSpeed, 3);
        double liftPower = Math.pow(totalWeight, 2) / (AIR_DENSITY * Math.pow(AVG_DRONE_WIDTH, 2) * relativeSpeed);
        double tPower = horizontalPower + liftPower;
        double time = calcTime(distance, AVG_DRONE_H_SPEED);

        return tPower * time * JOULE_TO_WATTHOUR_CONVERTER;
    }

    public static double calcDroneEnergy(Path p, Drone d, List<Product> lp) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double totalWeight = d.getWeight() + lp.stream().mapToDouble(Product::getWeight).sum();
        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), d.getAverageSpeed());

        double horizontalPower = 0.5 * AIR_DENSITY * d.getAerodynamicCoeficient() * d.getFrontalArea() * Math.pow(relativeSpeed, 3);
        double liftPower = Math.pow(totalWeight, 2) / (AIR_DENSITY * Math.pow(AVG_DRONE_WIDTH, 2) * relativeSpeed);
        double tPower = horizontalPower + liftPower;
        double time = calcTime(distance, d.getAverageSpeed());

        return tPower * time * JOULE_TO_WATTHOUR_CONVERTER;
    }

    public static double calcTotalDistance(LinkedList<Address> la) {

        double totalDistance = 0.0d;

        for (int i = 0; i < la.size() - 1; i++) {

            totalDistance += calcDistance(la.get(i), la.get(i + 1));
        }
        return totalDistance;
    }

    public static double calcScooterTotalEnergy(Graph<Address, Path> g, LinkedList<Address> la, Courier c, List<Product> lp) {

        double totalEnergy = 0.0d;

        for (int i = 0; i < la.size() - 1; i++) {

            Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
            totalEnergy += calcScooterEnergy(p, c, lp);
        }
        return totalEnergy;
    }

    public static double calcScooterTotalEnergy(Graph<Address, Path> g, LinkedList<Address> la, Courier c, Scooter s, List<Product> lp) {

        double totalEnergy = 0.0d;

        for (int i = 0; i < la.size() - 1; i++) {

            Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
            totalEnergy += calcScooterEnergy(p, c, s, lp);
        }
        return totalEnergy;
    }

    public static double calcDroneTotalEnergy(Graph<Address, Path> g, LinkedList<Address> la, List<Product> lp) {

        if (la.isEmpty()) {
            return 0;
        }
        double totalEnergy = 0.0d;
        double totalWeight = AVG_DRONE_WEIGHT + lp.stream().mapToDouble(Product::getWeight).sum();
        double thrust = totalWeight * GRAVITATIONAL_ACCELERATION;
        double time = calcTime(DRONE_ALTITUDE * 2, AVG_DRONE_V_SPEED);

        totalEnergy += (thrust / (Math.sqrt(2 * AIR_DENSITY * AVG_DRONE_FRONTAL))) * time * JOULE_TO_WATTHOUR_CONVERTER;

        for (int i = 0; i < la.size() - 1; i++) {

            Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
            totalEnergy += calcDroneEnergy(p, lp);
        }
        return totalEnergy;
    }

    public static double calcDroneTotalEnergy(Graph<Address, Path> g, LinkedList<Address> la, Drone d, List<Product> lp) {

        if (la.isEmpty()) {
            return 0;
        }
        double totalEnergy = 0.0d;
        double totalWeight = d.getWeight() + lp.stream().mapToDouble(Product::getWeight).sum();
        double thrust = totalWeight * GRAVITATIONAL_ACCELERATION;
        double time = calcTime(DRONE_ALTITUDE * 2, AVG_DRONE_V_SPEED);

        totalEnergy += (thrust / (Math.sqrt(2 * AIR_DENSITY * d.getFrontalArea()))) * time * JOULE_TO_WATTHOUR_CONVERTER;

        for (int i = 0; i < la.size() - 1; i++) {

            Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
            totalEnergy += calcDroneEnergy(p, lp);
        }
        return totalEnergy;
    }

    public static int calcTime(double distance, double speed) {

        return (int) (distance / speed);
    }

    public static double getRelativeSpeed(double windSpeed, double windDegrees, double speed) {

        double relativeWind = windSpeed * Math.cos(Math.toRadians(windDegrees));

        return speed - relativeWind;
    }
}
