package application;

import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DTRMain extends Application{
	TextField console = new TextField("");
	Label empName = new Label();
	Label incorrectInput = new Label("");
	VBox inAndEnter;
   @Override
   public void start(Stage primaryStage){
	   Button enter = new Button("Enter");   
	   Label userID = new Label("Enter employee ID");
	   inAndEnter = new VBox(10, incorrectInput, userID, console,  enter);
	   inAndEnter.setPadding(new Insets(10));
	   console.setMaxWidth(100);
	   
	   enter.setOnAction(e -> something());
	   
	   VBox mainVBox = new VBox(10, inAndEnter);
	   Scene scene = new Scene(mainVBox, 400, 200);
	   scene.getStylesheets().add(getClass().getResource("/styles/application.css").toString());
	   primaryStage.setScene(scene);
	   primaryStage.setTitle("Register");
	   primaryStage.show();
   }

   private void something() {
	String data = console.getText();
	
	if(data.length() < 8) {
		incorrectInput.setText("Wrong stuff");
		Timeline timeline = new Timeline(
				new KeyFrame (Duration.seconds(0.1), new KeyValue(inAndEnter.styleProperty(), "-fx-background-color: red;")),
				new KeyFrame(Duration.seconds(0.5), new KeyValue(inAndEnter.styleProperty(), "-fx-background-color: white;"))
			);
		timeline.setCycleCount(3);
		timeline.play();
	}else {
		incorrectInput.setText("empNAme");
	}
	return;
}


public static void main(String[] args){
      launch(args);
   }
}