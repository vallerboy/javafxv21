package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    @FXML
    TextField loginText;

    @FXML
    PasswordField passwordText;

//    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("AkademiaKodu");
//        alert.setHeaderText(null);
//        alert.setContentText("Hello jestem tekstem!");
//
//    ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
//    ButtonType buttonOskarIsOk = new ButtonType("Oskar jest fajny!");
//    ButtonType buttonOK = new ButtonType("Ok!");
//
//        alert.getButtonTypes().setAll(buttonCancel,buttonOskarIsOk,buttonOK);
//
//
//    Optional<ButtonType> result = alert.showAndWait();
//        if(result.get() == buttonOskarIsOk) {
//        System.out.println("Ktoś myśli, że Oskar jest fajny!");
//
//
//    }else if(result.get() == buttonCancel) {
//        System.out.println("Ktoś wyłączył okno (anuluował)");
//    }




    public void openDialog() {
        System.out.println("Login: " + loginText.getText() + " Hasło: " + passwordText.getText());
        Statement statement = MySqlConnector.getInstance().getNewStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE name = '" + loginText.getText()+"'");

            int counter = 0;

                while (resultSet.next()) {
                    String passwordFromDatabase = resultSet.getString("password");
                    if(passwordFromDatabase.equals(passwordText.getText())){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Logowanie");
                        alert.setContentText("Zalogowałeś się poprawnie");
                        alert.showAndWait();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Logowanie");
                        alert.setContentText("Błędne hasło");
                        alert.showAndWait();
                    }
                    counter++;
                }
           if(counter == 0) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Logowanie");
               alert.setContentText("Użytkownik nie istnieje");
               alert.showAndWait();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
