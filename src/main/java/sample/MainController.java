package sample;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by OskarPraca on 2017-04-29.
 */
public class MainController implements Initializable {


    @FXML
    ImageView logo;

    @FXML
    JFXListView list;

    @FXML
    Label pagesLabel;

    @FXML
    Label authorLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        RotateTransition transition = new RotateTransition();
//        transition.setDuration(Duration.seconds(5));
//        transition.setByAngle(360);
//        transition.setCycleCount(10);
//        transition.setAutoReverse(false);
//        transition.setToAngle(360);
//        //transition.setFromAngle(-60);
//        transition.setNode(logo);
//        transition.setAxis(new Point3D(5,25,55));
//        transition.play();
//
//        ScaleTransition scale = new ScaleTransition();
//        scale.setNode(logo);
//        scale.setByX(.5);
//        scale.setByY(.5);
//        scale.setDuration(Duration.seconds(5));
//        scale.setCycleCount(10);
//        scale.play();


//        TranslateTransition translateTransition = new TranslateTransition();
//        translateTransition.setNode(logo);
//        translateTransition.setDuration(Duration.seconds(5));
//        translateTransition.setByX(800);
//        translateTransition.setFromX(-300);
//        translateTransition.setCycleCount(10);
//        translateTransition.setAutoReverse(false);
//        translateTransition.play();


//        FadeTransition fadeTransition = new FadeTransition();
//        fadeTransition.setNode(logo);
//        fadeTransition.setDuration(Duration.seconds(5));
//        fadeTransition.setFromValue(1.0);
//        fadeTransition.setToValue(0.0);
//        fadeTransition.setAutoReverse(true);
//        fadeTransition.setCycleCount(2);
//        fadeTransition.play();
        //list = new JFXListView<String>();




        list.setItems(loadBooks());
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    String[] bookData = loadBookData((String)list.getSelectionModel().getSelectedItem());
                    authorLabel.setText(bookData[0]);
                    pagesLabel.setText(bookData[1]);
            }
        });


    }

    // 0 - autor, 1 - ilość stron
    private String[] loadBookData(String bookName) {
        Statement statement = MySqlConnector.getInstance().getNewStatement();
        String[] dataArray = new String[2];
        System.out.println(bookName);
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE name = '" + bookName + "'");
            while (resultSet.next()){//asd
                dataArray[0] = resultSet.getString("author");
                dataArray[1] = String.valueOf(resultSet.getInt("pages"));
                return dataArray;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ObservableList<String> loadBooks() {
        ObservableList<String> items = FXCollections.observableArrayList ();
        Statement statement = MySqlConnector.getInstance().getNewStatement();

        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM book");
            while(resultSet.next()) {
                items.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

      return items;
    }
}
