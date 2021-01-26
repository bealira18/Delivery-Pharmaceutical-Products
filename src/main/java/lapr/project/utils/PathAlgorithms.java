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

public class PathAlgorithms {

    private PathAlgorithms() {

    }

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
        double roadSlope = Math.atan((p.getAddress2().getAltitude() - p.getAddress1().getAltitude()) / distance);

        double slopeDrag = (AVG_COURIER_WEIGHT + AVG_SCOOTER_WEIGHT) * GRAVITATIONAL_ACCELERATION * Math.sin(roadSlope);
        double aeroDrag = 0.5 * AIR_DENSITY * AVG_SCOOTER_DRAG * AVG_SCOOTER_FRONTAL * Math.pow(relativeSpeed, 2);
        double groundDrag = GRAVITATIONAL_ACCELERATION * (AVG_COURIER_WEIGHT + AVG_SCOOTER_WEIGHT) * p.getKineticCoeficient() * Math.cos(roadSlope);
        double totalForce = slopeDrag + aeroDrag + groundDrag;

        double totalEnergy = totalForce * AVG_SCOOTER_SPEED * calcTime(distance, AVG_SCOOTER_SPEED) * JOULE_TO_WATTHOUR_CONVERTER;

        return totalEnergy < 0 ? 0 : totalEnergy;
    }

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
