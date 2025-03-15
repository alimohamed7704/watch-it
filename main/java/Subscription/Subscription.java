package Subscription;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

import Subscription.*;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = Basic.class, name = "Basic"),
        @JsonSubTypes.Type(value = Standard.class, name = "Standard"),
        @JsonSubTypes.Type(value = Premium.class, name = "Premium")
})

@JsonIgnoreProperties({"start_date" ,"end_date","maximumNumberOfMovies"})

public abstract class Subscription {
    private int userId;
    private LocalDate start_date;

    private LocalDate end_date;
    private int maximumNumberOfMovies;

    public static HashMap<Month, Double> eachMonthRevenue = new HashMap<Month, Double>();


    public Subscription(int userId, LocalDate start_date, int maximumNumberOfMovies) {
        this.userId = userId;

        this.start_date = start_date;

        this.end_date = start_date.plusDays(30);

        this.maximumNumberOfMovies = maximumNumberOfMovies;

    }

    public int getUserId() {
        return userId;
    }


    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }


    public int getMaximumNumberOfMovies() {
        return maximumNumberOfMovies;
    }

    public String getStartDateString() {
        return start_date.toString();
    }

    public static String getMostSubscribedPlan()
    {
        if(Basic.getNumberOfUsers() >= Standard.getNumberOfUsers()
            && Basic.getNumberOfUsers() >= Premium.getNumberOfUsers()
        )
            return "Basic Plan";
        else if(Standard.getNumberOfUsers() >= Premium.getNumberOfUsers())
            return "Standard Plan";
        else
            return "Premium Plan";
    }

    public static String getTheMonthWithMaximumRevenue() {
        String topMonth = "";
        double maxRevenue = -1;
        for(Map.Entry<Month,Double> entry: eachMonthRevenue.entrySet())
        {
            if(entry.getValue() > maxRevenue)
            {
                maxRevenue = entry.getValue();
                topMonth = entry.getKey().name().toLowerCase();
            }
        }
        char firstChar = topMonth.charAt(0);
        topMonth = Character.toUpperCase(firstChar) + topMonth.substring(1);
        return topMonth;
    }

    public static double getTotalRevenue() {
        return Basic.getTotalRevenue() + Standard.getTotalRevenue() + Premium.getTotalRevenue();
    }

    public abstract String getType();


@JsonIgnore
    public  double getPrice() {
        if(this instanceof Basic){
            return Basic.getPlanPrice();
        }else if (this instanceof Standard){
            return Standard.getPlanPrice();
        }else{
            return Premium.getPlanPrice();
        }
    }

}
