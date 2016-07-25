package ch.makery.address.util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Created by lukasz.homik on 2016-07-06.
 */
public class AlertMsg {

    //private Stage dialogStage;
    public static void ShowMsg(String title, String msg, String errMsg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        //dialogStage = stage;
        //alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(msg);
        alert.setContentText(errMsg);

        alert.showAndWait();
    }
}
