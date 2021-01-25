package lapr.project.data;

import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

import java.net.ConnectException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScooterDB extends DataHandler {

    public boolean addScooter(Scooter scooter) {

            return addScooter(scooter.getIdVehicle(), scooter.getIdPharmacy(), scooter.getWeight(), scooter.getAerodynamicCoeficient(),
                    scooter.getFrontalArea(), scooter.getMotor(), scooter.getCurrentBattery(), scooter.getMaxBattery(),
                    scooter.getAverageSpeed(), scooter.getScooterStatusId());
    }


    public Scooter getIdScooter(int idScooter) {
        Scooter s = null;

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getScooterById(?) }")) {

                // Regista o tipo de dados SQL para interpretar o resultado obtido.
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                // Especifica o parâmetro de entrada da função "getSailor".
                callStmt.setInt(2, idScooter);
                // Executa a invocação da função "getSailor".
                callStmt.execute();
                // Guarda o cursor retornado num objeto "ResultSet".
                try (ResultSet rSet = (ResultSet) callStmt.getObject(1)) {

                    if (rSet.next()) {
                        int id = rSet.getInt(1);
                        int scooterStatus = rSet.getInt(2);

                        s = new Scooter(id, scooterStatus);
                    }
                }
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e);
            throw new IllegalArgumentException("No Scooter with id:" + idScooter);
        } finally {
            closeAll();
        }
        return s;
    }

    public List<Scooter> getAllAvailableScooters(int orderId) {
        ArrayList<Scooter> scooters = new ArrayList<>();

        try (Connection con = getConnection()) {

            try (CallableStatement callStm = con.prepareCall("{ ? = call getAllAvailableScooters(?) }")) {

                callStm.registerOutParameter(1, OracleTypes.CURSOR);

                callStm.setInt(2, orderId);

                callStm.execute();

                try (ResultSet rSet = (ResultSet) callStm.getObject(1)) {

                    while (rSet.next()) {
                        scooters.add(getIdScooter(rSet.getInt(1)));
                    }
                }
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        return scooters;
    }

    public boolean updateScooter(int ids, Scooter s) {
        Scooter a;

        try {
            a = getIdScooter(ids);
            if (a == null) {
                return false;
            }
        } catch (NullPointerException e) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call updateScooter(?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt.setInt(1, s.getIdVehicle());
                callStmt.setInt(2, s.getScooterStatusId());
                callStmt.setInt(3, s.getIdPharmacy());
                callStmt.setDouble(4, s.getWeight());
                callStmt.setDouble(5, s.getAerodynamicCoeficient());
                callStmt.setDouble(6, s.getFrontalArea());
                callStmt.setDouble(7, s.getMotor());
                callStmt.setDouble(8, s.getCurrentBattery());
                callStmt.setDouble(9, s.getMaxBattery());
                callStmt.setDouble(10, s.getAverageSpeed());

                callStmt.execute();

                closeAll();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    private boolean addScooter(int idScooter, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea,
                               double motor, double currentBattery, double maxBattery, double averageSpeed, int scooterStatusId) {

        try (Connection con = getConnection()) {
            try (CallableStatement callStmt1 = con.prepareCall("{ call addScooter(?,?,?,?,?,?,?,?,?,?) }")) {

                callStmt1.setInt(1, idScooter);
                callStmt1.setInt(2, idPharmacy);
                callStmt1.setDouble(3, weight);
                callStmt1.setDouble(4, aerodynamicCoeficient);
                callStmt1.setDouble(5, frontalArea);
                callStmt1.setDouble(6, motor);
                callStmt1.setDouble(7, currentBattery);
                callStmt1.setDouble(8, maxBattery);
                callStmt1.setDouble(9, averageSpeed);
                callStmt1.setInt(10, scooterStatusId);

                callStmt1.execute();
                return true;

            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

}
