package main.java.Crew;

import java.util.Date;
import java.util.List;

public class Producer extends Crew
{
   private String ProductionCompany;

    public Producer(){
        super();
    }
    //constructor
    public Producer(String type,String firstName, String gender, String dateOfBirth, String nationality, int crewID,String productionCompany) {
        super(type,firstName, gender, dateOfBirth, nationality, crewID);
        ProductionCompany=productionCompany;
    }
}
