package Profiles;

import Content.*;
import Profiles.Profile;
import Profiles.WatchRecord;
import Subscription.*;
import com.fasterxml.jackson.annotation.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

@JsonIgnoreProperties({"watchRecords","watchLater","subscription","crewFreq","genreFreq","contentPoints"})
public class User extends Profile {

    List<WatchRecord> watchRecords = new ArrayList<>();
    List<Content> watchLater = new ArrayList<>();

    Subscription subscription;

    private static int IdCounter = 0;

    private Map<Integer,Integer> crewFreq = new HashMap<>();

    private Map<String,Integer> genreFreq = new HashMap<>();

    private double balance;

    private int numberOfWatchedMovies;

    private Map<Content,Integer> contentPoints;

    private String selectedPlan;

    public User(String email, String username, String password, String firstName, String lastName
            , double balance , List<Integer> watchLaterContentIds , int numberOfWatchedMovies , int id)
    {
        super( id ,email,  username,  password,  firstName, lastName);
        IdCounter++;
        this.balance = balance;
        this.numberOfWatchedMovies = numberOfWatchedMovies;
        initalizeWatchLater(watchLaterContentIds);
    }


    @JsonCreator
    public User(@JsonProperty("email") String email, @JsonProperty("username") String username , @JsonProperty("balance") double balance , @JsonProperty("id") int id,
                @JsonProperty("password") String password, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName ,
                @JsonProperty("watchLaterContentIds") List<Integer> watchLaterContentIds ,@JsonProperty("numberOfWatchedMovies") int numberOfWatchedMovies ) {
        this(email,username,password,firstName,lastName,balance,watchLaterContentIds ,numberOfWatchedMovies,id);
    }
    public User(String email, String username, String password, String firstName, String lastName /*, String subscribedPlan*/
            , double balance , List<Integer> watchLaterContentIds , int numberOfWatchedMovies)
    {
        this(email,username,password,firstName,lastName,balance,watchLaterContentIds ,numberOfWatchedMovies,IdCounter+1);
    }

    public User(){
        super(++IdCounter);
    }

    private void incrementCrewFreq(Content curContent){
        List<Integer> cast = curContent.getCrewIDs();
        for(int crewID : cast){
            crewFreq.put(crewID,crewFreq.getOrDefault(crewID,0) + 1);
        }
    }

    private void incrementGenreFreq(Content curContent) {
        List<String> curContentGenre = curContent.getGenres();
        for (String genre : curContentGenre) {
            genreFreq.put(genre, genreFreq.getOrDefault(genre, 0) + 1);
        }
    }

    public boolean watchAContentIfYouCan(Content content) {
        if (subscription == null) {return false;}

        WatchRecord wd = isContentFoundInWatchRecords(content);
        if(wd != null && !wd.getDateOfWatching().isBefore(LocalDate.parse("1900-01-01"))){
            return true;
        }
        if (numberOfWatchedMovies + 1 <= subscription.getMaximumNumberOfMovies()) {
            if(wd == null){
                wd = new WatchRecord(this.getID(),LocalDate.now(),content);
            }else{
                wd.setDateOfWatching(LocalDate.now());
            }
            watchRecords.add(wd);
            numberOfWatchedMovies++;
            incrementCrewFreq(content);
            incrementGenreFreq(content);
            return true;
        }
        return false;

    }
    public void Rate(Content content ,int rating)
    {
        WatchRecord wd = isContentFoundInWatchRecords(content);
        if(wd == null){
            wd = new WatchRecord(this.getID(), LocalDate.parse("1800-01-01"), content);
            watchRecords.add(wd);
        }
        if(wd.getRate()==0)
            content.rateContent(rating);
        else{
            content.rateContent(rating , wd.getRate());
        }
        wd.setRate(rating);

    }

    public List<WatchRecord> getWatchRecords() {
        return watchRecords;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance=balance;
    }

    public int getNumberOfWatchedMovies() {
        return numberOfWatchedMovies;
    }

    public void setNumberOfWatchedMovies(int numberOfWatchedMovies ) {
        this.numberOfWatchedMovies=numberOfWatchedMovies;
    }

    public String getType(){
        return "User";
    }

    private void initalizeWatchLater(List<Integer> watchLaterContentIds)
    {
        for ( int contentId: watchLaterContentIds) {
            watchLater.add(ContentManager.getContnetList().stream().filter
                            (content1 -> content1.getId()==contentId).findAny()
                    .orElseThrow(()-> new IllegalArgumentException("No Content found with ID: " + contentId + " when trying to initialize watch later")));
        }
    }

    public List<Integer> getWatchLaterContentIds() {
        List<Integer> watchLaterIDs = new ArrayList<>();
        for (Content content : watchLater){
            watchLaterIDs.add(content.getId());
        }
        return watchLaterIDs;
    }

    public void addWatchRecord(WatchRecord wd){
        this.watchRecords.add(wd);
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public Map<String,Integer> getGenreFreq() {
        return genreFreq;
    }

    public Map<Integer,Integer> getCrewFreq() {
        return crewFreq;
    }

    public Map<Content,Integer> getContentPoints(){
        return contentPoints;
    }

    public List<Content> getWatchLater() {
        return watchLater;
    }

    public WatchRecord isContentFoundInWatchRecords(Content givenContent){
        for(WatchRecord curRecord : watchRecords){
            Content curContent = curRecord.getContent();
            if(givenContent.getId() == curContent.getId()) return curRecord;
        }
        return null;
    }


    private Map<Content,Integer> calculateContentPoints(List<Content> recommendationList){
        Map<Content,Integer> contentPoints = new HashMap<>();
        List<Content> allContent = ContentManager.getContnetList();
        for(Content curContent : allContent){
            if (isContentFoundInWatchRecords(curContent) != null){
                incrementCrewFreq(curContent);
                incrementGenreFreq(curContent);
            }
        }
        for(Content curContent : allContent) {
            if (isContentFoundInWatchRecords(curContent) == null || isContentFoundInWatchRecords(curContent).getDateOfWatching().isBefore(LocalDate.parse("1900-01-01"))) {
                int crewPoints = 0;
                List<Integer> curContentCrew = curContent.getCrewIDs();
                for (Integer curCrewID : curContentCrew) {
                    crewPoints += crewFreq.getOrDefault(curCrewID, 0);
                }

                int genrePoints = 0;
                List<String> curContentGenre = curContent.getGenres();
                for (String curGenre : curContentGenre) {
                    genrePoints += genreFreq.getOrDefault(curGenre, 0);
                }
                int totalPoints = (genrePoints * 2) + crewPoints;
                if (totalPoints > 0) {
                    recommendationList.add(curContent);
                    contentPoints.put(curContent, totalPoints);
                }
            }
        }

        return contentPoints;
    }

    @JsonIgnore
    public List<Content> getRecommendation(){
        List<Content> returnRecommendation = new ArrayList<>();
        contentPoints = calculateContentPoints(returnRecommendation);
        ContentPointComparator comparator = new ContentPointComparator(this.contentPoints);
        returnRecommendation.sort(comparator);
        return returnRecommendation;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public void addToWatchLater(Content c){
        if(!watchLater.contains(c))
            watchLater.add(c);
    }


    @JsonIgnore
    public String getSelectedPlan() {
        return selectedPlan;
    }

    public void setSelectedPlan(String selectedPlan) {
        this.selectedPlan = selectedPlan;
    }


}