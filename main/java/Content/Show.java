package Content;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDate;
import java.util.List;


public class Show extends Content {
    private int numberOfEpisodes;

    public Show(String title, String language, String country, LocalDate releaseDate, List<String> genres, int id, long budget, long revenue, double rating, long totalPeopleRated, double duration, String posterPath, String description, String logoPath, int numberOfEpisodes) {
        super(title, language, country, releaseDate, genres, id, budget, revenue, rating, totalPeopleRated, duration, posterPath, description, logoPath);
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @JsonCreator
    public Show(
            @JsonProperty("crewIDs") List<Integer> crewIDs,
            @JsonProperty("title") String title,
            @JsonProperty("language") String language,
            @JsonProperty("country") String country,
            @JsonProperty("releaseDateString") String releaseDateString,
            @JsonProperty("genres") List<String> genres,
            @JsonProperty("id") int id,
            @JsonProperty("budget") long budget,
            @JsonProperty("revenue") long revenue,
            @JsonProperty("rating") double rating,
            @JsonProperty("totalPeopleRated") long totalPeopleRated,
            @JsonProperty("duration") double duration,
            @JsonProperty("posterPath") String posterPath,
            @JsonProperty("description") String description,
            @JsonProperty("logoPath") String logoPath,
            @JsonProperty("numberOfEpisodes") int numberOfEpisodes) {
            this(title,language,country,LocalDate.parse(releaseDateString),genres,id,budget,revenue,rating,totalPeopleRated,duration,posterPath,description,logoPath,numberOfEpisodes);
            initializeCrewList(crewIDs);
    }



    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    @Override
    public String getType(){
        return "Show";
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
