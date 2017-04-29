package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by OskarPraca on 2017-04-29.
 */
public class MainController implements Initializable {


    @FXML
    ImageView logo;


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


        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(logo);
        fadeTransition.setDuration(Duration.seconds(5));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(2);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
            }
        });
        fadeTransition.play();



    }
}
