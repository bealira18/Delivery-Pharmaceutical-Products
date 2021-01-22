package lapr.project.utils;

public final class Constants {

    /**
     * Earth radius in Km. Irrelevant for lower distances.
     */
    public static final int EARTH_RADIUS = 6371;
    /**
     * Wind degrees (non-radian).
     */
    public static final double AIR_DENSITY = 1.2041;
    /**
     * Gravitational acceleration in 9.8 m/s2.
     */
    public static final double GRAVITATIONAL_ACCELERATION = 9.8;
    /**
     * Average scooter speed in m/s.
     */
    public static final double AVG_SCOOTER_SPEED = 8.9;
    /**
     * Average scooter weight in kg.
     */
    public static final double AVG_SCOOTER_WEIGHT = 15;
    /**
     * Average scooter drag.
     */
    public static final double AVG_SCOOTER_DRAG = 1.1;
    /**
     * Average scooter frontal area in m2.
     */
    public static final double AVG_SCOOTER_FRONTAL = 1.1;
    /**
     * Average commercial drone horizontal speed in m/s.
     */
    public static final double AVG_DRONE_H_SPEED = 22.36;
    /**
     * Average commercial drone vertical speed in m/s.
     */
    public static final double AVG_DRONE_V_SPEED = 9;
    /**
     * Average drone weight in kg.
     */
    public static final double AVG_DRONE_WEIGHT = 5;
    /**
     * Average drone drag.
     */
    public static final double AVG_DRONE_DRAG = 0.6;
    /**
     * Average drone frontal area in m2.
     */
    public static final double AVG_DRONE_FRONTAL = 0.4;
    /**
     * Average courier weight in kg.
     */
    public static final double AVG_COURIER_WEIGHT = 70;
    /**
     * Drone altitude in meters, taking into account it parks at 10m above
     * ground.
     */
    public static final double DRONE_ALTITUDE = 140;
    /**
     * Average drone width in m.
     */
    public static final double AVG_DRONE_WIDTH = 0.5;
    /**
     * How many Wh a J is worth.
     */
    public static final double JOULE_TO_WATTHOUR_CONVERTER = 0.00027777777777778;
    /**
     * m/s string to avoid duplication.
     */
    public static final String M_S_NEWLINE = "m/s.\n";
    /**
     * kg string to avoid duplication.
     */
    public static final String KG_NEWLINE = "kg.\n";
    /**
     * W.h string to avoid duplication.
     */
    public static final String WH_NEWLINE = "W.h.\n";
    /**
     * Unitless string to avoid duplication.
     */
    public static final String UNITLESS_NEWLINE = " (unitless).\n";
}
