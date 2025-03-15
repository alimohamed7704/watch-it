package Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;



public class Premium extends Subscription{
    private static int numberOfUsers = 0;
    private static final int maximumNumberOfMovies = 30;
    private static final double planPrice = 150.0;
    public Premium(int userId, LocalDate start_date){
        super(userId ,start_date,maximumNumberOfMovies);
        numberOfUsers++;
        eachMonthRevenue.put(start_date.getMonth(),eachMonthRevenue.getOrDefault(start_date.getMonth(),0.0)+planPrice);
    }
    @JsonCreator
    public Premium(@JsonProperty("userId") int userId, @JsonProperty("startDateString") String StartDate) {
        this(userId , LocalDate.parse(StartDate));
    }

    public static int getNumberOfUsers(){
        return numberOfUsers;
    }
    public static double getPlanPrice()
    {
        return planPrice;
    }
    public static double getTotalRevenue()
    {
        return numberOfUsers*planPrice;
    }

    //    @JsonProperty
    public String getType(){
        return "Premium";
    }
}
