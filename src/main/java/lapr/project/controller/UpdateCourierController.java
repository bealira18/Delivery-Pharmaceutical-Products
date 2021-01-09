package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.model.Courier;

public class UpdateCourierController {

    private final CourierDB cDB;

    public UpdateCourierController(){
        cDB=new CourierDB();
    }

    public UpdateCourierController(CourierDB cDB){
        this.cDB=cDB;
    }

    public boolean updateCourier(String email, Courier c){
        if(c==null){
            return false;
        }
        return cDB.updateCourier(email,c);
    }

}
