package Content;

import java.util.Comparator;
import java.util.Map;

public class ContentPointComparator implements Comparator {
    Map<Content,Integer> contentPoints;
    public ContentPointComparator(Map<Content, Integer> contentPoints) {this.contentPoints = contentPoints;}
    @Override
    public int compare(Object a,Object b){
        Content content1 = (Content)a;
        Content content2 = (Content)b;
        int content1Score = contentPoints.getOrDefault(content1,0);
        int content2Score = contentPoints.getOrDefault(content2,0);
        // Descending
        return Integer.compare(content2Score, content1Score);
    }
}
