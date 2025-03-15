package main.java.Watch_Record;//package Watch_Record;
//
//import content.Content;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.HashMap;
//
//public class WatchRecord {
//    private int userId;
//
//    private Content content;
//
//    private LocalDate dateOfWatching;
//
//    private int rate;
//
//    public static HashMap<Content, Integer> watchedContent = new HashMap<>(); //to track the top watched content
//
//    public WatchRecord(int userId, LocalDate dateOfWatching , Content content) {
//        this.userId = userId;
//
//        this.dateOfWatching = dateOfWatching;
//
//        this.content = content;
//
//        this.rate = 0;// rate is not assigned by user
//
//        WatchRecord.watchedContent.put(content, watchedContent.getOrDefault(content, 0) + 1);
//    }
//
//    public WatchRecord(int userId, LocalDate dateOfWatching, Content content, int rate) {
//        this(userId, dateOfWatching, content);
//
//        this.rate = rate;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public LocalDate getDateOfWatching() {
//        return dateOfWatching;
//    }
//
//    public int getRate() {
//        return rate;
//    }
//
//    public Content getContent() {
//        return content;
//    }
//
//    public void setRate(int rate) {
//        this.rate = rate;
//    }
//}
