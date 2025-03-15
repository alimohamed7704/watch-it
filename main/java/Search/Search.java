package Search;
import Content.*;
import Crew.*;
import org.jetbrains.annotations.NotNull;
import java.util.*;
public class Search {
    public static List<Crew> SearchCrew(String toSearch){
        List<Crew> result = new ArrayList<>();
        for(Crew crew : CrewManager.crewList){
            if(SubstringFinder.isFound(toSearch,crew.getName())) result.add(crew);
        }
        return result;
    }
    public static List<Content> SearchContent(String toSearch){
        List<Content> result = new ArrayList<>();
        for(Content cont : ContentManager.getContnetList()){
            if(SubstringFinder.isFound(toSearch,cont.getTitle())) result.add(cont);
        }
        return result;
    }
    public static List<Content> filterGenre(List<Content> currentContent, List<String> genres){
        List<Content> result = new ArrayList<>();
        for(Content cont : currentContent){
            List<String> cont_genres = cont.getGenres();
            boolean isAdded = false;
            for(String genre : cont_genres){
                for(String searchedGenre : genres){
                    if(genre.equalsIgnoreCase(searchedGenre)){
                        result.add(cont);
                        isAdded = true;
                        break;
                    }
                }
                if(isAdded) break;
            }
        }
        return result;
    }
    public static List<Content> filterRating(List<Content> currentContent, double lowerBound, double upperBound){
        List<Content> result = new ArrayList<>();
        for(Content cont : currentContent){
            if(cont.getRating() >= lowerBound && cont.getRating() <= upperBound) result.add(cont);
        }
        return result;
    }
    public static List<Content> filterDuration(List<Content> currentContent, double lowerBound, double upperBound){
        List<Content> result = new ArrayList<>();
        for(Content cont : currentContent){
            if(cont.getDuration() >= lowerBound && cont.getDuration() <= upperBound) result.add(cont);
        }
        return result;
    }
    public static List<Content> filterLanguage(List<Content> currentContent, List<String> languages) {
        List<Content> result = new ArrayList<>();
        for (Content cont : currentContent) {
            String cont_lang = cont.getLanguage();
            for (String searchedLanguage : languages) {
                if (searchedLanguage.equalsIgnoreCase(cont_lang)) {
                    result.add(cont);
                    break;
                }
            }
        }
        return result;
    }
}