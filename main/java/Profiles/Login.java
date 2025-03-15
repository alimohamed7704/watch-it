package main.java.Profiles;

import java.util.*;

public class Login {
    public static HashMap<String , String > UsersData = new HashMap<>();

    Login(){
        InitializeData();
    }

    public static void InitializeData()
    {
        UsersData.put("youssab235@gmail.com" , "1234");
        UsersData.put("youssa@gmal.com" , "5678");
        UsersData.put("youb235@gmail.com" , "9101112");
        UsersData.put("you@gmial.com" , "13141516");
    }
}
