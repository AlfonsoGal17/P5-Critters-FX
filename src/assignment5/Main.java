package assignment5;








import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane grid = new GridPane();
	static Integer worldTime =0;
	static Text timeWorld = new Text();
	static boolean play = false;
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Critters");
		//add descriptive text
		Text actionTitle = new Text("Welcome, choose an action.");
		actionTitle.setLayoutX(5);
		actionTitle.setLayoutY(20);
		actionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		///////////////////////////////////////////////////////////////
		//make button and set its location
		Button btn = new Button();	
		btn.setLayoutX(5);
		btn.setLayoutY(50);
		btn.setText("Make");
		//make choicebox, set location, and add values
		ChoiceBox<String> cb = new ChoiceBox<>();
		cb.setLayoutX(95);
		cb.setLayoutY(50);
		cb.getItems().addAll("Craig","Algae", "Critter1", "Critter2");
		cb.setValue("Craig");
		//add a way to add multiple critters
		Label lbl1 = new Label("How many? ");
		lbl1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		TextField txt1 = new TextField();
		txt1.setText("1");
		txt1.setMaxWidth(50);
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(lbl1,txt1);
		hb1.setLayoutX(200);
		hb1.setLayoutY(50);
		//determine what happens when button  btn is pressed
		btn.setOnAction(e -> getChoice(cb, txt1));
		///////////////////////////////////////////////////////////////
		//make timestep button
		Button stepbtn = new Button();
		stepbtn.setLayoutX(5);
		stepbtn.setLayoutY(100);
		stepbtn.setText("Step");
		//add way to select amount of steps
		Label lbl = new Label("Step ammount: ");
		lbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		TextField txt = new TextField();
		txt.setMaxWidth(50);
		txt.setText("1");
		HBox hb = new HBox();
		hb.getChildren().addAll(lbl,txt);
		hb.setLayoutX(100);
		hb.setLayoutY(100);
		stepbtn.setOnAction(e -> doStep(txt));
		////////////////////////////////////////////////////
		//keep count of time
		Label timeLbl = new Label("World time: ");
		timeWorld.setFont(Font.font("Tahoma",FontWeight.NORMAL, 16));
		HBox t = new HBox();
		t.getChildren().addAll(timeLbl,timeWorld);
		t.setSpacing(10);
		t.setLayoutX(5);
		t.setLayoutY(150);
		/////////////////////////////////////////////////////////////
		//add Play/Stop button
		Button play = new Button();
		play.setLayoutX(5);
		play.setLayoutY(175);
		play.setText("Play");
		play.setOnAction(e -> timeLoop());
		Button stop = new Button();
		stop.setLayoutX(100);
		stop.setLayoutY(175);
		stop.setText("Stop");
		stop.setOnAction(e -> stopLoop());
		
		//make window
		Pane root = new Pane();
		//add objects to window
		root.getChildren().addAll(stop, play,t,hb1,hb,stepbtn,cb,btn,actionTitle);
		//set dimensions of window
		primaryStage.setScene(new Scene(root, 470, 300));
		//show build window
		primaryStage.show();
	}
	//To do something with the value of the selescted item
	private void getChoice(ChoiceBox<String> c, TextField num){
		String critter = c.getValue();
		int stepnum = Integer.parseInt(num.getText());
		try{
			for(int i=0;i<stepnum;i++){
				Critter.makeCritter("assignment5."+critter);
			}
		}
		catch(InvalidCritterException e){
			System.out.println("Error, not a critter: "+ critter);
		}
		Critter.displayWorld();
		
		}
	//makes the time step and shows world
	private void doStep(TextField txt){

		int stepnum = Integer.parseInt(txt.getText());
		for(int i =0; i<stepnum ; i++){
			Critter.worldTimeStep();
			worldTime++;
			timeWorld.setText(worldTime.toString());
		}
		Critter.displayWorld();
		}
	//loops time to create animation
	private void timeLoop(){
		play = true;
		while(play){
			Critter.worldTimeStep();
			Critter.displayWorld();
			worldTime++;
			timeWorld.setText(worldTime.toString());
		}
	}
	private void stopLoop(){
		play = false;
	}
		
	}

