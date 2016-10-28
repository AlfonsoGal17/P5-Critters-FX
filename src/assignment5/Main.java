package assignment5;




import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane grid = new GridPane();
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Critters");
		//add descriptive text
		Text scenetitle = new Text("Use this to make a critter:");
		scenetitle.setLayoutX(5);
		scenetitle.setLayoutY(45);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		//make button and set its location
		Button btn = new Button();	
		btn.setLayoutX(5);
		btn.setLayoutY(50);
		btn.setText("make Critter!");
		//make choicebox, set location, and add values
		ChoiceBox<String> cb = new ChoiceBox<>();
		cb.setLayoutX(150);
		cb.setLayoutY(50);
		cb.getItems().addAll("Craig","Algae", "Critter1", "Critter2");
		cb.setValue("Craig");
		//determine what happens when button  btn is pressed
		btn.setOnAction(e -> getChoice(cb));
		//make timestep/show button

		//make window
		Pane root = new Pane();
		//add objects to window
		root.getChildren().addAll(cb,btn,scenetitle);
		//set dimensions of window
		primaryStage.setScene(new Scene(root, 300, 300));
		//show build window
		primaryStage.show();
	}
	//To do something with the value of the selescted item
	private void getChoice(ChoiceBox<String> c){
		String critter = c.getValue();
		try{
		Critter.makeCritter("assignment5."+critter);
		}
		catch(InvalidCritterException e){
			System.out.println("Error, not a critter: "+ critter);
		}
		System.out.println("made "+ critter);
		
		}
	}

