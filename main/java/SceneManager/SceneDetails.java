package SceneManager;
import Crew.Crew;
import Profiles.*;
import Content.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Stack;

public class SceneDetails {
    public static User user;
    public static Admin admin;
    public static List<Content> currentList;
    public static String title;
    public static Content currentContent;

    public static Crew crew;

    public static Stack<Pane> scenes=new Stack<>();
}
