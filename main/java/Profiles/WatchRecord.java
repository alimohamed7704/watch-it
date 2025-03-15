package Profiles;

import Content.*;
import com.fasterxml.jackson.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;


@JsonIgnoreProperties({"content","dateOfWatching"})
public class WatchRecord {
    private int userId;

    private Content content;



    private LocalDate dateOfWatching;

    private int rate;

    public static HashMap<Content, Integer> watchedContent = new HashMap<>(); //to track the top watched content

    public WatchRecord(int userId, LocalDate dateOfWatching , Content content) {
        this.userId = userId;

        this.dateOfWatching = dateOfWatching;

        this.content = content;

        this.rate = 0;// rate is not assigned by user

        WatchRecord.watchedContent.put(content, watchedContent.getOrDefault(content, 0) + 1);
    }

    public WatchRecord(int userId, LocalDate dateOfWatching, Content content, int rate) {
        this(userId, dateOfWatching, content);

        this.rate = rate;
    }
    public void setDateOfWatching(LocalDate dateOfWatching) {
        this.dateOfWatching = dateOfWatching;
    }

    @JsonCreator
    public WatchRecord(
            @JsonProperty("userId") int userId,
            @JsonProperty("dateOfWatchingString") String dateOfWatchingString,
            @JsonProperty("rate") int rate ,
            @JsonProperty("contentID") int ContentID ) {
            this.userId = userId;
            this.rate = rate;
            this.dateOfWatching = LocalDate.parse(dateOfWatchingString);
            initializeContent(ContentID);
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getDateOfWatching() {
        return dateOfWatching;
    }

    public int getRate() {
        return rate;
    }

    public Content getContent() {
        return content;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getContentID(){
        return content.getId();
    }
    public String getDateOfWatchingString() {
        return dateOfWatching.toString();
    }

    private void initializeContent(Integer ContentID){
        Content c = ContentManager.getContnetList().stream().filter
                (content1 -> content1.getId()==ContentID).findFirst().orElseThrow
                (()-> new IllegalArgumentException("No Content found with ID: " + ContentID));
        c.addView(this.dateOfWatching);
        this.content = c;
    }
}
