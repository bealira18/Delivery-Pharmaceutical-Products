package lapr.project.controller;

import lapr.project.data.ScooterParkDB;
import lapr.project.model.ScooterPark;

public class UpdateNrChargingStationsController {

    private final ScooterParkDB spDB;

    public UpdateNrChargingStationsController() {
        spDB = new ScooterParkDB();
    }

    public UpdateNrChargingStationsController(ScooterParkDB spDB) {
        this.spDB = spDB;
    }

    @SuppressWarnings("null")
    public boolean updateNrChargingStations(int parkId, int nr) {

        //SQL Para ir buscar parque em questão.
        //Verificar se nr + atual numChargingStations não excede limite.
        //Se sim, petáculo.
        //Após isso, percorrer os parking spaces e torná-los em charging stations na BDDAD.
        ScooterPark sp = null; //Função para ir buscar o sp em questão
        sp.setNumChargingStations(nr);
        return true;
    }
}
