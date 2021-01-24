package lapr.project.model;

public class Scooter extends Vehicle {

    private int scooterStatusId;

    public Scooter(int idScooter, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor,
            double currentBattery, double maxBattery, double averageSpeed, int scooterStatusId) {

        super(idScooter, idPharmacy, weight, aerodynamicCoeficient, frontalArea, motor, currentBattery, maxBattery, averageSpeed);
        this.scooterStatusId = scooterStatusId;
    }

    public Scooter(int idVehicle, int scooterStatusId) {

        super(idVehicle);
        this.scooterStatusId = scooterStatusId;
    }

    public int getScooterStatusId() {

        return scooterStatusId;
    }
    
    public static double getScooterMaxPayload() {
        
        double scooterMaxPayload = Double.parseDouble(System.getProperty("scooter.max.payload", "0.0"));
        if (scooterMaxPayload <= 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative or 0 Scooter Max Payload). Please check your configuration file.");
        }
        return scooterMaxPayload;
    }

    public void setScooterStatusId(int scooterStatusId) {

        this.scooterStatusId = scooterStatusId;
    }
    
    public static void setScooterMaxPayload(double scooterMaxPayload) {

        if (scooterMaxPayload <= 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative or 0 Scooter Max Payload)");
        }
        System.setProperty("scooter.max.payload", String.valueOf(scooterMaxPayload));
    }

    @Override
    public String toString() {

        return super.toString() + " Scooter{" + "scooterStatusId=" + scooterStatusId + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 19 * hash + this.scooterStatusId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Scooter) {
            return super.equals(obj) && scooterStatusId == ((Scooter) obj).scooterStatusId;
        }
        return false;
    }
}
