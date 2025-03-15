package Crew;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Content.*;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = Actor.class, name = "Actor"),
        @JsonSubTypes.Type(value = Director.class, name = "Director")

       // ,@JsonSubTypes.Type(value = Producer.class, name = "Producer")
})


@JsonIgnoreProperties({"dateOfBirth" , "previousWorks"})
public abstract class Crew {


    //personal details
    private String firstName;
    private String lastName;
    private String gender;
    private String nationality;
    private LocalDate dateOfBirth;
    protected List<String> socialMedia = new ArrayList<>();

    protected String imagePath;


    //professional details
    private int crewID;
    protected static int maxCrewID = -1;
    //private List<Integer> previousProjectsIDs = new ArrayList<>();
    private List<Content> previousWorks = new ArrayList<>();


    //Constructor
    public Crew(String firstName, String lastName , String gender, LocalDate dateOfBirth, String nationality, int crewID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.crewID = crewID;
        if (crewID>maxCrewID){
            maxCrewID = crewID;
        }
    }

    public Crew(String firstName, String lastName , String gender, LocalDate dateOfBirth, String nationality, int crewID , List<String> socialMedia){
        this(firstName ,lastName ,gender , dateOfBirth , nationality ,crewID);
        this.socialMedia = socialMedia;
    }

    //setters

    //Getters

    @JsonIgnore
    public String getName() {

        return (firstName+" "+lastName);
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getStringDateOfBirth() {
        return dateOfBirth.toString();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getCrewID() {
        return crewID;
    }
//    public List<Integer> getPreviousProjects() {
//        return previousProjectsIDs;
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Content> getPreviousWorks() {
        return previousWorks;
    }
    public void addToWorks(Content content){
        previousWorks.add(content);
    }
    public abstract String getType();
    public String getImagePath() {
        return imagePath;
    }

    public List<String> getSocialMedia(){
        return socialMedia;
    }


}
//show content
//filter movies
//show series
//show shows
/*
public class Crewc {
    public String firstName;
    public String lastName;
    public String imagePath;
    public int CrewID;
    public Crewc(String firstName, String lastName, String gender, String nationality, int crewID,String imagePath){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.imagePath = imagePath;
        CrewID = crewID;
    }

 */
