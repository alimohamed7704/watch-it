package Content;

import com.fasterxml.jackson.annotation.*;
import javafx.scene.image.Image;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Crew.*;
import Crew.CrewManager;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = Movie.class, name = "Movie"),
        @JsonSubTypes.Type(value = Show.class, name = "Show")
})

@JsonIgnoreProperties({"crewList", "viewHistory","director","releaseDate"})

public abstract class Content {


    private String title;
    private List<Crew> crewList = new ArrayList<>();
    private Director director;
    private String language;
    private String country;
    private LocalDate releaseDate; // Store release date as a string

    private List<String> genres = new ArrayList<>();
    private int id;
    private long budget;
    private long revenue;
    private double rating;
    private long totalPeopleRated;
    private double duration;
    private String posterPath;
    private String description;
    private String logoPath;

    private Map<String, Integer> viewHistory = new HashMap<>();

    public Content(String title, String language, String country, LocalDate releaseDate, List<String> genres, int id, long budget, long revenue, double rating, long totalPeopleRated, double duration, String posterPath, String description, String logoPath) {
        this.title = title;
        this.language = language;
        this.country = country;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.id = id;
        this.budget = budget;
        this.revenue = revenue;
        this.rating = rating;
        this.totalPeopleRated = totalPeopleRated;
        this.duration = duration;
        this.posterPath = posterPath;
        this.description = description;
        this.logoPath = logoPath;
    }

    @JsonIgnore
    public int getViewsLast30Days() {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAgo = today.minusDays(30);

        int totalViews = 0;
        for (Map.Entry<String, Integer> entry : viewHistory.entrySet()) {
            LocalDate entryDate = LocalDate.parse(entry.getKey());
            if (!entryDate.isBefore(thirtyDaysAgo)) {
                totalViews += entry.getValue();
            }
        }
        return totalViews;
    }

    public Map<String, Integer> getViewHistory() {
        return viewHistory;
    }

//    public List<Integer> getCastList() {
//        return castIDs;
//    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getReleaseDateString() {
        return releaseDate.toString();
    }

    // Getter and setter for genres
    public List<String> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public double getDuration() {
        return duration;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getRating() {
        return rating;
    }

    public void addGenres(String[] genre) {
        Collections.addAll(genres, genre);
    }

    public void addCast(String name) {
        Crew toAdd = CrewManager.Search(name);
        if (toAdd != null) {
            crewList.add(toAdd);
            toAdd.addToWorks(this);
        }
    }

    public void rateContent(int rate) {
        rating *= totalPeopleRated;
        rating += rate;
        totalPeopleRated++;
        rating /= totalPeopleRated;
    }

    public void rateContent(int rate , int oldRate){
        rating*=totalPeopleRated;
        rating-=oldRate;
        rating+=rate;
        rating/=totalPeopleRated;
    }
    public void addView(LocalDate date) {
        String dateString = date.toString();
        viewHistory.put(dateString, viewHistory.getOrDefault(dateString, 0) + 1);
    }

    protected void initializeCrewList(List<Integer> crewIDs) {
        Crew crew;
        for(Integer x : crewIDs){
            crew = CrewManager.getCrewByID(x);
            if(crew!=null){
                if(crew instanceof Director){
                    this.director = (Director) crew;
                }
                crewList.add(crew);
                crew.addToWorks(this);
            }
        }
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public List<Integer> getCrewIDs(){
        List<Integer> crewIDs =new ArrayList<>();
        for (Crew c : crewList){
            crewIDs.add(c.getCrewID());
        }
        return crewIDs;
    }

    public Director getDirector() {
        return director;
    }
    public String getDescription(){
        return description;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public long getTotalPeopleRated() {
        return totalPeopleRated;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public abstract String getType();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }



    public void setDescription(String description) {
        this.description = description;
    }
}
