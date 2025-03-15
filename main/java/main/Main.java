package main;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;


import SceneManager.*;
import FileHandling.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FileHandle.loadAllFiles();
        SceneManager.init(stage);
    }


    public static void main(String[] args) throws IOException {
        launch();
        FileHandle.saveALlFiles();
    }
}