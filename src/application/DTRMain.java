package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
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
	Button punchBttn;
	
   @Override
   public void start(Stage primaryStage){
	   Button enter = new Button("Enter");   
	   punchBttn = new Button("Punch In/Out");
	   promptMessage = new Label("Enter employee ID");
	   
	   //Initialize and set up the Hbox to hold operations employee can take
	   HBox operations = new HBox(10, punchBttn);
	   operations.setPadding(new Insets(10));
	   punchBttn.getStyleClass().add("punchBttn");
	   
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
	   VBox mainVBox = new VBox(name, inAndEnter, operations);
	   Scene scene = new Scene(mainVBox, 400, 200);
	   scene.getStylesheets().add(getClass().getResource("/styles/application.css").toString());
	   primaryStage.setScene(scene);
	   primaryStage.setTitle("Register");
	   primaryStage.show();
   }

   private void checkInput() {
	   String data = console.getText();
	   
	   switch(promptMessage.getText()) {
	   		case "Enter employee ID":
	   			try {
	 			   currEmp = employees.get(data);
	 			   if(!currEmp.clockedIn()) {
	 				   empName.setText("Employee not on the clock");
	 			   }else {
	 				  empName.setText(currEmp.getName());
		 			  promptMessage.setText("Enter Password");
		 			  console.setText("");
	 			   }
	 		   }catch(NullPointerException e) {
	 			   empName.setText("Invalid empID");
	 			   runErrorAnimation();
	 		   }
	   			break;
	   			
	   		case "Enter Password":
	   			if(!data.equals(currEmp.getPassword())) {
	   				runErrorAnimation();
	   			}else {
	   				//TODO give access to the rest of the register's features to the employee
	   			}
	   			break;
	   }
   }
   
   void runErrorAnimation() {
	   Timeline timeline = new Timeline(
			   new KeyFrame (Duration.seconds(0.1), new KeyValue(inAndEnter.styleProperty(), "-fx-background-color: red;")),
			   new KeyFrame(Duration.seconds(0.5), new KeyValue(inAndEnter.styleProperty(), "-fx-background-color: rgb(80, 175, 70);"))
			   );
			   timeline.setCycleCount(3);
			   timeline.play(); 
   }

   public static void main(String[] args) throws FileNotFoundException{
		//create an Employee object for each employee using the information in the employeeData.txt file
		File emps = new File("src/employeeData.txt");
		Scanner scnr = new Scanner(emps);
		
		while(scnr.hasNext()) {
			employees.put(scnr.next(), new Employee(scnr.next(), scnr.next()));
		}
		scnr.close();
		
		launch(args);
   }
}