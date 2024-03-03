package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class Controller implements Initializable,Runnable{

    @FXML
    private Button openBtn;

    @FXML
    private TextField timeZone;

    @FXML
    private Label timelabel;

//    public void updateTime() {
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
//            String currentTime = TimeDefault.sdf.format(new Date());
//            timelabel.setText(currentTime);
//        }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//    }
    
    public void newClock() {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
 
    public void run() {
    	
    	try {
            while (true) {
            	
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                int n = Integer.parseInt(timeZone.getText());
                TimeZone timeZone = TimeZone.getTimeZone("GMT" + (n >= 0 ? "+" : "") + n);

                sdf.setTimeZone(timeZone);
                Platform.runLater(() -> {
                    timelabel.setText(sdf.format(new Date()));
                    
                });
                
                Thread.sleep(1000); // Tạm dừng luồng trong 1 giây
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void openBtn() {
    	newClock();
    	Thread thread = new Thread(this);
        thread.start();
    	
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//updateTime();
    }
}