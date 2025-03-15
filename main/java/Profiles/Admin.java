package Profiles;

import Content.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;



public class Admin extends Profile{
    private static List<Content> unlistedContent = new ArrayList<>();
    private static int ID = 0;
    public Admin(String email, String username, String password, String firstName, String lastName ,int id)
    {
        super( id ,email,  username,  password,  firstName, lastName);
        ID++;
    }
    @JsonCreator
    public Admin(@JsonProperty("email") String email, @JsonProperty("username") String username , @JsonProperty("id") int id,
                @JsonProperty("password") String password, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
        this(email,username,password,firstName,lastName,id);
    }

    public Admin(String email, String username, String password, String firstName, String lastName) {
        this(email,username,password,firstName,lastName,ID+1);
    }

//    public Content selectContent()
//    {
//
//    }
    /*
     * private void addContent(Content content)
     * {
     *   contentList.add(content);
     * }
     *
     * private void deleteContent(Content content)
     * {
     *   for(Content c : contentList)
     *   {
     *       if(c.equals(content)
     *           {contentList.erase(c); break;}
     *   }
     * }
     *
     * private void updateContentName, updateContentActor, updateContentReleaseDate, etc..
     *
     * */

    public void addContent(Content Content)
    {
        //List<Content> currentList = ContentManager.getContentList();
    }
    @Override
    public String getType(){
        return "Admin";
    }

    public static void setUnlistedContent(List<Content> unlistedContent) {
        Admin.unlistedContent = unlistedContent;
    }
    public static List<Content> getUnlistedContent()
    {
        return unlistedContent;
    }
}
