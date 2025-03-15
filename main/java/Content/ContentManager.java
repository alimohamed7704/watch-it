package Content;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContentManager {
    private static List<Content> contentList;

    public ContentManager(){
        contentList = new ArrayList<>();
    }


    public static void addContent(Content content)
    {
        contentList.add(content);
    }
    public static void addListOfMovies(List<Content> contentList){
        contentList.addAll(contentList);
    }

    public static Content getContentByTitle(String title)
    {
        for (Content content : contentList){
            if(content.getTitle().equalsIgnoreCase(title)){
                return content;
            }
        }
        return  null;
    }
    // function top-rated sorts the list based on the rating
    public static List<Content> getTopRatedContent(){
        List<Content> topRatedContent = new ArrayList<>(contentList);
        topRatedContent.sort(Comparator.comparingDouble(Content::getRating).reversed());
        return  topRatedContent;
    }
    // function new releases sorts the list based on the release date
    public static List<Content> getNewReleasesContent(){
        List<Content> newReleasedContent = new ArrayList<>(contentList);
        newReleasedContent.sort(Comparator.comparing(Content::getReleaseDate).reversed());
        return  newReleasedContent;
    }

    public static List<Content> getTrendingMovies(){
        List<Content> trendingContent = new ArrayList<>(contentList);
        trendingContent.sort(Comparator.comparingInt(Content::getViewsLast30Days).reversed());
        return  trendingContent;
    }
    public void displayTrendingMovies(){
        for (Content content:getTrendingMovies()){
            System.out.println(content.getTitle()+" "+ content.getViewsLast30Days());
        }
    }

    public static void setContentList(List<Content> contentList) {
        ContentManager.contentList = contentList;
    }

    public static List<Content> getContnetList(){
        return contentList;
    }
}

