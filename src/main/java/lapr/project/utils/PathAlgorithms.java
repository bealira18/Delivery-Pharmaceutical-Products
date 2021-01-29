package lapr.project.utils;

import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
import static lapr.project.utils.Constants.*;

/**
 * This class contains a myriad of algorithms pertaining to the calculation of
 * distance, energy and time of traversal of given paths.
 *
 * @author lapr3-2020-g052
 */
public class PathAlgorithms {

    /**
     * Private constructor to hide the implicit public one.
     */
    private PathAlgorithms() {

    }

    /**
     * Method responsible for calculating the distance (in meters) between two
     * addresses received as parameter.
     *
     * @param a1 address #1.
     * @param a2 address #2.
     * @return the distance between two addresses (in meters).
     */
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

    /**
     * Method responsible for calculating the amount of energy (in W.h) that it
     * takes for a Scooter (with default characteristics) to traverse a given
     * path received as parameter. The attributes of the courier driving the
     * scooter are also by default.
     *
     * @param p path to traverse.
     * @return the energy (in W.h) to traverse the path.
     */
    public static double calcScooterEnergy(Path p) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), AVG_SCOOTER_SPEED);
        double roadSlope = Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance);

        double slopeDrag = (AVG_COURIER_WEIGHT + AVG_SCOOTER_WEIGHT) * GRAVITATIONAL_ACCELERATION * Math.sin(roadSlope);
        double aeroDrag = 0.5 * AIR_DENSITY * AVG_SCOOTER_DRAG * AVG_SCOOTER_FRONTAL * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * (AVG_COURIER_WEIGHT + AVG_SCOOTER_WEIGHT) * p.getKineticCoeficient() * Math.cos(roadSlope);
        double totalForce = slopeDrag + aeroDrag + groundDrag;

        double totalEnergy = totalForce * AVG_SCOOTER_SPEED * calcTime(distance, AVG_SCOOTER_SPEED) * JOULE_TO_WATTHOUR_CONVERTER;

        return totalEnergy < 0 ? 0 : totalEnergy;
    }

    /**
     * Method responsible for calculating the amount of energy (in W.h) that it
     * takes for a Scooter (with default characteristics) to traverse a given
     * path received as parameter. It also receives as parameter the courier
     * that will drive the scooter and the list of products that it will carry.
     *
     * @param p path to traverse.
     * @param c courier.
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the path.
     */
    public static double calcScooterEnergy(Path p, Courier c, List<Product> lp) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double totalWeight = c.getWeight() + AVG_SCOOTER_WEIGHT + lp.stream().mapToDouble(Product::getWeight).sum();
        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), AVG_SCOOTER_SPEED);
        double roadSlope = Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance);

        double slopeDrag = totalWeight * GRAVITATIONAL_ACCELERATION * Math.sin(roadSlope);
        double aeroDrag = 0.5 * AIR_DENSITY * AVG_SCOOTER_DRAG * AVG_SCOOTER_FRONTAL * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * totalWeight * p.getKineticCoeficient() * Math.cos(roadSlope);
        double totalForce = slopeDrag + aeroDrag + groundDrag;

        double totalEnergy = totalForce * AVG_SCOOTER_SPEED * calcTime(distance, AVG_SCOOTER_SPEED) * JOULE_TO_WATTHOUR_CONVERTER;

        return totalEnergy < 0 ? 0 : totalEnergy;
    }

    /**
     * Method responsible for calculating the amount of energy (in W.h) that it
     * takes for a Scooter, received as parameter, to traverse a given path
     * received as parameter. It also receives as parameter the courier that
     * will drive the scooter and the list of products that it will carry.
     *
     * @param p path to traverse.
     * @param c courier.
     * @param s scooter.
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the path.
     */
    public static double calcScooterEnergy(Path p, Courier c, Scooter s, List<Product> lp) {

        double distance = calcDistance(p.getAddress1(), p.getAddress2());
        if (distance == 0) {
            return 0;
        }
        double totalWeight = c.getWeight() + s.getWeight() + lp.stream().mapToDouble(Product::getWeight).sum();
        double relativeSpeed = getRelativeSpeed(p.getWindSpeed(), p.getWindAngle(), s.getAverageSpeed());
        double roadSlope = Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance);

        double slopeDrag = totalWeight * GRAVITATIONAL_ACCELERATION * Math.sin(roadSlope);
        double aeroDrag = 0.5 * AIR_DENSITY * s.getAerodynamicCoeficient() * s.getFrontalArea() * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * totalWeight * p.getKineticCoeficient() * Math.cos(roadSlope);
        double totalForce = slopeDrag + aeroDrag + groundDrag;

        double totalEnergy = totalForce * s.getAverageSpeed() * calcTime(distance, s.getAverageSpeed()) * JOULE_TO_WATTHOUR_CONVERTER;

        return totalEnergy < 0 ? 0 : totalEnergy;
    }

    /**
     * Method responsible for calculating the amount of energy (in W.h) that it
     * takes for a Drone (with default characteristics) to traverse a given path
     * received as parameter.
     *
     * @param p path to traverse.
     * @return the energy (in W.h) to traverse the path.
     */
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

    /**
     * Method responsible for calculating the amount of energy (in W.h) that it
     * takes for a Drone (with default characteristics) to traverse a given path
     * received as parameter. It also receives as parameter the list of products
     * to carry.
     *
     * @param p path to traverse.
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the path.
     */
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

    /**
     * Method responsible for calculating the amount of energy (in W.h) that it
     * takes for a Drone, received as parameter, to traverse a given path
     * received as parameter. It also receives as parameter the list of products
     * to carry.
     *
     * @param p path to traverse.
     * @param d drone.
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the path.
     */
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

    /**
     * Method responsible for calculating the total distance (in meters) of a
     * complete voyage.
     *
     * @param la list of addresses (by order of traversal).
     * @return the total distance (in meters) to traverse the voyage.
     */
    public static double calcTotalDistance(LinkedList<Address> la) {

        double totalDistance = 0.0d;

        for (int i = 0; i < la.size() - 1; i++) {

            totalDistance += calcDistance(la.get(i), la.get(i + 1));
        }
        return totalDistance;
    }

    /**
     * Method responsible for calculating the total energy required (in W.h) for
     * a Scooter (with default characteristics) to traverse a given voyage,
     * received as parameter.
     *
     * @param g adjancent matrix graph containing the paths.
     * @param la list of addresses (by order of traversal).
     * @param c courier.
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the voyage.
     */
    public static double calcScooterTotalEnergy(Graph<Address, Path> g, LinkedList<Address> la, Courier c, List<Product> lp) {

        double totalEnergy = 0.0d;

        for (int i = 0; i < la.size() - 1; i++) {

            Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
            totalEnergy += calcScooterEnergy(p, c, lp);
        }
        return totalEnergy;
    }

    /**
     * Method responsible for calculating the total energy required (in W.h) for
     * a Scooter, received as parameter, to traverse a given voyage also
     * received as parameter.
     *
     * @param g adjancent matrix graph containing the paths.
     * @param la list of addresses (by order of traversal).
     * @param c courier.
     * @param s scooter.
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the voyage.
     */
    public static double calcScooterTotalEnergy(Graph<Address, Path> g, LinkedList<Address> la, Courier c, Scooter s, List<Product> lp) {

        double totalEnergy = 0.0d;

        for (int i = 0; i < la.size() - 1; i++) {

            Path p = g.getEdge(la.get(i), la.get(i + 1)).getElement();
            totalEnergy += calcScooterEnergy(p, c, s, lp);
        }
        return totalEnergy;
    }

    /**
     * Method responsible for calculating the total energy required (in W.h) for
     * a Drone (with default characteristics) to traverse a given voyage,
     * received as parameter. It also handles the calculation of the energy
     * required for the vertical stretches of the voyage.
     *
     * @param g adjancent matrix graph containing the paths.
     * @param la list of addresses (by order of traversal).
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the voyage.
     */
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

    /**
     * Method responsible for calculating the total energy required (in W.h) for
     * a Drone, received as parameter, to traverse a given voyage also received
     * as parameter. It also handles the calculation of the energy required for
     * the vertical stretches of the voyage.
     *
     * @param g adjancent matrix graph containing the paths.
     * @param la list of addresses (by order of traversal).
     * @param d drone.
     * @param lp list of products.
     * @return the energy (in W.h) to traverse the voyage.
     */
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

    /**
     * Method responsible for calculating the time (in seconds) to traverse a
     * given distance, received as parameter by dividing it by the speed (in
     * m/s) it'll be traversed in.
     *
     * @param distance distance (in meters).
     * @param speed speed (in m/s).
     * @return the time (in seconds) to traverse the given distance.
     */
    public static int calcTime(double distance, double speed) {

        return (int) (distance / speed);
    }

    /**
     * Method responsible for calculating relative velocity (in m/s).
     *
     * @param windSpeed the wind speed (in m/s).
     * @param windDegrees the wind direction (in Degrees).
     * @param speed the speed (in m/s).
     * @return the relative velocity (in m/s).
     */
    public static double getRelativeSpeed(double windSpeed, double windDegrees, double speed) {

        double relativeWind = windSpeed * Math.cos(Math.toRadians(windDegrees));

        return speed - relativeWind;
    }
}
