package sample;

import javafx.scene.control.Alert;

/**
 * Created by OskarPraca on 2017-04-26.
 */
public class Utils {
    public static void openDialog(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
