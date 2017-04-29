package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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

    public String getPassword() {
        return passwordText.getText().trim();
    }

    public String getLogin() {
        return loginText.getText().trim();
    }


    private boolean isLoginFormValid() {
        if(getLogin().length() < 4 || getPassword().length() < 4){
            Utils.openDialog("Logowanie", "Login i hasło muszą mieć minimum 4 znaki");
            return false;
        }

        return true;
    }


    public void openDialog(MouseEvent event) throws IOException {
        if(!isLoginFormValid()){
            return;
        }

        System.out.println("Login: " + loginText.getText() + " Hasło: " + Utils.hashPassword(passwordText.getText()));
        Statement statement = MySqlConnector.getInstance().getNewStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE name = '" + loginText.getText()+"' LIMIT 1");

            int counter = 0;

                while (resultSet.next()) {
                    String passwordFromDatabase = resultSet.getString("password");
                    if(passwordFromDatabase.equals(Utils.hashPassword(passwordText.getText()))){

                        Parent mainPage = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                        Scene scene = new Scene(mainPage);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


                        stage.hide();
                        stage.setScene(scene);
                        stage.show();

                        Utils.openDialog("Logowanie", "Zalogowałeś się poprawnie!");
                    }else{
                        Utils.openDialog("Logowanie", "Błędne hasło!");
                    }
                    counter++;
                }
           if(counter == 0) {
               Utils.openDialog("Logowanie", "Użytkownik nie istnieje.");
           }
           statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void register() {

        try {
            String sql = "INSERT INTO user(name, lastname, password, number) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = MySqlConnector.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, "oskarx");
            statement.setString(2, "Kowalski");
            statement.setString(3, "10135886");
            statement.setString(4, "722277722");

            statement.execute();
            statement.close();

            //////////

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void initialize(URL location, ResourceBundle resources) {

    }
}
