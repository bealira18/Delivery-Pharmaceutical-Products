package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;

public class UpdateNrChargingStationsController {

    private final ParkDB spDB;

    public UpdateNrChargingStationsController() {
        spDB = new ParkDB();
    }

    public UpdateNrChargingStationsController(ParkDB spDB) {
        this.spDB = spDB;
    }

    @SuppressWarnings("null")
    public boolean updateNrChargingStations(int parkId, int nr) {

        //SQL Para ir buscar parque em questão.
        //Verificar se nr + atual numChargingStations não excede limite.
        //Se sim, petáculo.
        //Após isso, percorrer os parking spaces e torná-los em charging stations na BDDAD.
        Park sp = null; //Função para ir buscar o sp em questão
        sp.setNumChargingStations(nr);
        return true;
    }
}
