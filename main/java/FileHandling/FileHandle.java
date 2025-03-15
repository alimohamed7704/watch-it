package FileHandling;

import Content.*;
import Profiles.*;
import Crew.*;
import Subscription.*;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandle {

    private static final String initialPath = "src/main/resources/FileHandling/";
    private static List<Profile> profileList = new ArrayList<>();

    public static void saveListToJsonFile(String filePath , List<?> thelist ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath),thelist);
    }

    public static <T> List<T> loadListFromJson(String filePath, Class<T> clazz) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            System.out.println("creating The file at " + filePath);
            objectMapper.writeValue(new File(filePath), new ArrayList<>());
            System.out.println("Empty Json list file Created");
            return new ArrayList<>();
        } catch (JsonMappingException e) {
            System.err.println("Error parsing JSON in file: " + filePath);
            e.printStackTrace();
            return new ArrayList<>();  // Return an empty list to avoid crashing
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON in file: " + filePath);
            e.printStackTrace();
            return new ArrayList<>();  // Return an empty list to avoid crashing
        }
    }

    public static void loadAllFiles()throws IOException{
        //Crew
        CrewManager.crewList = FileHandle.loadListFromJson(initialPath + "Crew.json" ,Crew.class);

        //Content

        ContentManager.setContentList( FileHandle.loadListFromJson(initialPath + "Content.json" ,Content.class));


        //WatchRecord
        List<WatchRecord> watchRecordList = FileHandle.loadListFromJson(initialPath+"WatchRecord.json", WatchRecord.class);
        List<Subscription> SubscriptionList = FileHandle.loadListFromJson( initialPath+"Subscription.json",Subscription.class);
        //User & Admin properties
        profileList = FileHandle.loadListFromJson(initialPath + "Users.json",Profile.class);

        mapWatchRecordsToUsers(watchRecordList,SubscriptionList);


        Admin.setUnlistedContent(FileHandle.loadListFromJson(initialPath+"UnlistedContent.json",Content.class));



    }

    public static void saveALlFiles()throws IOException{

        //Crew
        FileHandle.saveListToJsonFile(initialPath + "Crew.json",CrewManager.crewList);

        //Content
        FileHandle.saveListToJsonFile(initialPath + "Content.json", ContentManager.getContnetList());

        //Save Watch Records
        List<WatchRecord> WR = new ArrayList<>();
        List<Subscription> SubscriptionList = new ArrayList<>();
        for (Profile p : profileList){
            if(p instanceof User){
                User u = (User) p;
                for(WatchRecord wr : u.getWatchRecords()){
                    WR.add(wr);
                }
                Subscription s = u.getSubscription();
                if(s!=null){
                    SubscriptionList.add(s);
                }
            }
        }
        saveListToJsonFile(initialPath+"WatchRecord.json",WR);

        FileHandle.saveListToJsonFile(initialPath + "Subscription.json",SubscriptionList);

        // User & Admin properties
        FileHandle.saveListToJsonFile(initialPath + "Users.json",profileList);
        FileHandle.saveListToJsonFile(initialPath + "UnlistedContent.json" , Admin.getUnlistedContent());


    }


    public static List<Profile> getProfileList() {
        return profileList;
    }
    public static void addProfile(Profile profile){
        FileHandle.profileList.add(profile);
    }

    private static void mapWatchRecordsToUsers(List<WatchRecord> watchRecords , List<Subscription> subscriptionList){
        for (Profile p: profileList){
            if(p instanceof User){
                User u = (User) p;
                for (WatchRecord wd : watchRecords){
                    if(wd.getUserId() == u.getID()){
                        u.addWatchRecord(wd);
                    }
                }
                for (Subscription s : subscriptionList){
                    if(u.getID() == s.getUserId()) {
                        if (LocalDate.now().isBefore(s.getEnd_date()) || LocalDate.now().equals(s.getEnd_date()) )
                            u.setSubscription(s);
                    }
                }
            }
        }
    }
}
