package Profiles;

import SceneManager.SceneDetails;
import javafx.fxml.FXML;

public class trialController {
    @FXML
    public void add()
    {
        SceneDetails.currentList.add(SceneDetails.currentContent);
    }
}
