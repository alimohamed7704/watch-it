package Subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Standard extends Subscription{

    @JsonIgnore
    private static int numberOfUsers = 0;

    @JsonIgnore
    private static final int maximumNumberOfMovies = 10;
    @JsonIgnore
    private static final double planPrice = 75.0;
    public Standard(int userId, LocalDate start_date){
        super(userId ,start_date,maximumNumberOfMovies);
        numberOfUsers++;
        eachMonthRevenue.put(start_date.getMonth(),eachMonthRevenue.getOrDefault(start_date.getMonth(),0.0)+planPrice);
    }
    @JsonCreator
    public Standard(@JsonProperty("userId") int userId, @JsonProperty("startDateString") String StartDate) {
        this(userId , LocalDate.parse(StartDate));
    }


    public static void setNumberOfUsers(int numberOfUsers) {
        Standard.numberOfUsers = numberOfUsers;
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

    public String getType(){
        return "Standard";
    }
}
