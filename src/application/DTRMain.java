package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DTRMain extends Application{
	static HashMap<String, Employee> employees = new HashMap<>();
	TextField console = new TextField("");
	Label empName = new Label("");
	Label promptMessage;
	//VBox to hold the console where employees are to input data and the enter button
	VBox inAndEnter;
	Employee currEmp;
	
   @Override
   public void start(Stage primaryStage){
	   Button enter = new Button("Enter");   
	   promptMessage = new Label("Enter employee ID");
	   
	   //initialize and set up all specifications for the inAndEnter VBox
	   inAndEnter = new VBox(10, promptMessage, console,  enter);
	   inAndEnter.setPadding(new Insets(10));
	   inAndEnter.getStyleClass().add("inAndEnter");
	   console.setMaxWidth(100);
	   
	   AnchorPane name = new AnchorPane();
	   name.getChildren().add(empName);
	   AnchorPane.setTopAnchor(empName, 10.0);
	   AnchorPane.setRightAnchor(empName, 10.0);
	   
	   enter.setOnAction(e -> checkInput());
	   
	   //setup for the program window
	   VBox mainVBox = new VBox(name, inAndEnter);
	   Scene scene = new Scene(mainVBox, 400, 200);
	   scene.getStylesheets().add(getClass().getResource("/styles/application.css").toString());
	   primaryStage.setScene(scene);
	   primaryStage.setTitle("Register");
	   primaryStage.show();
   }

   private void checkInput() {
	   Timeline timeline;
	   String data = console.getText();
	   
	   if(promptMessage.getText() == "Enter employee ID") {
		   try {
			   currEmp = employees.get(data);
			   empName.setText(currEmp.getName());
			   promptMessage.setText("Enter Password");
		   }catch(NullPointerException e) {
			   empName.setText("Invalid empID");
			   timeline = new Timeline(
					   new KeyFrame (Duration.seconds(0.1), new KeyValue(inAndEnter.styleProperty(), "-fx-background-color: red;")),
					   new KeyFrame(Duration.seconds(0.5), new KeyValue(inAndEnter.styleProperty(), "-fx-background-color: white;"))
					   );
					   timeline.setCycleCount(3);
					   timeline.play(); 
		   }	   
	   }else if(promptMessage.getText() == "Enter Password") {
		   //TODO Prompt the user to enter their password and check whether the password is correct.
		   		//1.) Might want to add a button at this stage that goes back to the ID prompt in case another person needs to use the register
	   }
	return;
}

   public void checkEmpID(String data) {
	   
   }

public static void main(String[] args) throws FileNotFoundException{
	//create an Employee object for using the information in the employeeData.txt file
	File emps = new File("src/employeeData.txt");
	Scanner scnr = new Scanner(emps);
	
	while(scnr.hasNext()) {
		employees.put(scnr.next(), new Employee(scnr.next(), scnr.next()));
	}
	scnr.close();
	
	launch(args);
   }
}