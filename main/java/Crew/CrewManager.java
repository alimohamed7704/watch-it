package Crew;

import java.time.LocalDate;
import java.util.*;

import Content.Content;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class CrewManager {
    public static List<Crew> crewList = new ArrayList<>();
    public static Crew Search(String name){
        Crew returnCrew = null;
        for(Crew crew : crewList){
            if(crew.getName().equals(name)) {
                returnCrew = crew;
                break;
            }
        }
        return returnCrew;
    }

    public static void addActor(String firstName,String lastName, String gender, LocalDate dateOfBirth, String nationality, int crewID){
        Crew crew = new Actor(firstName,lastName, gender, dateOfBirth, nationality, crewID);
        crewList.add(crew);
    }
    static void addDirector(String firstName,String lastName, String gender, LocalDate dateOfBirth, String nationality, int crewID){
        Crew crew = new Director(firstName ,lastName,  gender, dateOfBirth, nationality, crewID);
        crewList.add(crew);
    }
//    static void addProducer(String firstName, String lastName,String gender, String dateOfBirth, String nationality,String productionCompany){
//        Crew crew = new Producer(firstName,lastName,gender, LocalDate.parse(dateOfBirth),nationality,productionCompany);
//        crewList.add(crew);
//    }

//    public static void displayCrewList(){
//        for (Crew crew : crewList){
//            System.out.println(crew.getName() + crew.getPreviousProjects());
//        }
//    }

    public static Crew getCrewByID(int id){
        Crew returnCrew = null;
        for(Crew crew : crewList){
            if(crew.getCrewID() == id) {
                returnCrew = crew;
                break;
            }
        }
        return returnCrew;
    }

//    public static void saveCrewListToJson(String filePath) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(new File(filePath), crewList);
//    }
//
//    // Load the Crew list from a JSON file
//    public static void loadCrewListFromJson(String filePath) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Crew> loadedcrewList = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Crew.class));
//        crewList.addAll(loadedcrewList);
//    }

}
// CREW MANAGER
