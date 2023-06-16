package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DTRMain extends Application{

   @Override
   public void start(Stage primaryStage){
	   Label testLabel = new Label("Hello World");
	   VBox mainVBox = new VBox(testLabel);
	   Scene scene = new Scene(mainVBox, 200, 100);
	   primaryStage.setScene(scene);
	   primaryStage.setTitle("Register");
	   primaryStage.show();
   }

   public static void main(String[] args){
      launch(args);
   }
}