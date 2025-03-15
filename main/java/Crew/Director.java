package Crew;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Director extends Crew{
    //constructor
    public Director(String firstName,String lastname, String gender, LocalDate dateOfBirth, String nationality, int crewID, List<String> socialMedia) {
        this(firstName, lastname,  gender, dateOfBirth, nationality, crewID);
        super.socialMedia = socialMedia;
    }

    public Director(String firstName,String lastName, String gender, LocalDate dateOfBirth, String nationality, int crewID) {
        super(firstName,lastName, gender, dateOfBirth, nationality, crewID);
    }

    public Director(String firstName,String lastname, String gender, LocalDate dateOfBirth, String nationality, List<String> socialMedia) {
        this(firstName, lastname,  gender, dateOfBirth, nationality,++maxCrewID,socialMedia);
    }


    @JsonCreator
    public Director(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("gender") String gender,
            @JsonProperty("stringDateOfBirth") String stringDateOfBirth,
            @JsonProperty("nationality") String nationality,
            @JsonProperty("crewID") int crewID,
            @JsonProperty("socialMedia") List<String> socialMedia,
            @JsonProperty("imagePath") String imagePath) {

        this(firstName,lastName,gender, LocalDate.parse(stringDateOfBirth),nationality,crewID,socialMedia);
        super.imagePath = imagePath;
    }

    @Override
    public String getType(){
        return "Director";
    }

}
