package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Controller {
    public void openDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AkademiaKodu");
        alert.setHeaderText(null);
        alert.setContentText("Hello jestem tekstem!");

        ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonOskarIsOk = new ButtonType("Oskar jest fajny!");
        ButtonType buttonOK = new ButtonType("Ok!");

        alert.getButtonTypes().setAll(buttonCancel,buttonOskarIsOk,buttonOK);


        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonOskarIsOk) {
          System.out.println("Ktoś myśli, że Oskar jest fajny!");
        }else if(result.get() == buttonCancel) {
            System.out.println("Ktoś wyłączył okno (anuluował)");
        }
    }
}
