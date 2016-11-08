package assignment5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	static GridPane grid = new GridPane();
	static Integer worldTime = 0;
	static Text timeWorld = new Text();
	static Integer alg = 0;
	static Text algtxt = new Text();
	static Integer craig = 0;
	static Text craigtxt = new Text();
	static Integer c1 = 0;
	static Text c1txt = new Text();
	static Integer c2 = 0;
	static Text c2txt = new Text();
	static Integer c3 = 0;
	static Text c3txt = new Text();
	static Integer c4 = 0;
	static Text c4txt = new Text();
	static Integer alph = 0;
	static Text alphtxt = new Text();
	static int statpos = 275;
	static Text[] stattxt = new Text[20];

	static Integer[] p = new Integer[20];
	static HBox[] stat = new HBox[20];

	static List<String> crits = new ArrayList<String>();
	static List<String> c = new ArrayList<String>();
	static boolean play = false;

	public static void main(String[] args) {
		// Store params as some critters modify when initialized
		int width = Params.world_width;
		int height = Params.world_height;
		int walk = Params.walk_energy_cost;
		int run = Params.run_energy_cost;
		int rest = Params.rest_energy_cost;
		int min = Params.min_reproduce_energy;
		int refresh = Params.refresh_algae_count;
		int photo = Params.photosynthesis_energy_amount;
		int start = Params.start_energy;
		int look = Params.look_energy_cost;
		File currentDir = new File("");
		File folder = new File(currentDir.getAbsolutePath() + "/src/assignment5");
		// System.out.println(currentDir.getAbsolutePath());
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> listFiles = new ArrayList<String>();
		;
		for (File LOF : listOfFiles) {
			listFiles.add(LOF.getName());
		}
		for (String ls : listFiles) {
			// ls.substring(0, ls.length()-5);
			c.add(ls.substring(0, ls.length() - 5));
		}
		for (int i = 0; i < c.size(); i++) {
			if (listOfFiles[i].isFile()) {
				// System.out.println("File " + listOfFiles[i].getName());
				Class<?> myClass;
				try {
					// myClass = Class.forName("assignment5." +
					// listOfFiles[i].getName().substring(0,listOfFiles[i].getName().length()-5));

					myClass = Class.forName("assignment5." + c.get(i));
					Critter nC = (Critter) myClass.newInstance();

					if (nC instanceof Critter) {
						// crits.add(listOfFiles[i].getName().substring(0,listOfFiles[i].getName().length()
						// -5));
						// c.add(.get(i));
						crits.add(c.get(i));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (listOfFiles[i].isDirectory()) {
				// System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		Params.world_width = width;
		Params.world_height = height;
		Params.walk_energy_cost = walk;
		Params.run_energy_cost = run;
		Params.rest_energy_cost = rest;
		Params.min_reproduce_energy = min;
		Params.refresh_algae_count = refresh;
		Params.photosynthesis_energy_amount = photo;
		Params.start_energy = start;
		Params.look_energy_cost = look;

		launch(args);
	}
 static GridPane populationgrid = new GridPane();
 //static TilePane populationgrid = new TilePane();
	@Override
	public void start(Stage primaryStage) {
		//starter code will REMOVE
		try{
			Stage secondaryStage = new Stage();
			secondaryStage.setX(1200);
			secondaryStage.setY(200);
			populationgrid.setGridLinesVisible(true);
			secondaryStage.setTitle("Critter World");
			Scene scene = new Scene(populationgrid, 500,500);
			secondaryStage.setScene(scene);
			secondaryStage.show();
			//Painter.paint();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//end of starter code
		primaryStage.setTitle("Critters");
		// add descriptive text
		Text actionTitle = new Text("Welcome, choose an action.");
		actionTitle.setLayoutX(5);
		actionTitle.setLayoutY(20);
		actionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		///////////////////////////////////////////////////////////////
		// make button and set its location
		Button btn = new Button();
		btn.setLayoutX(5);
		btn.setLayoutY(50);
		btn.setText("Make");
		// make choicebox, set location, and add values
		ChoiceBox<String> cb = new ChoiceBox<>();
		cb.setLayoutX(95);
		cb.setLayoutY(50);
		
		// cb.getItems().addAll("Craig", "Algae", "AlgaephobicCritter",
		// "Critter1", "Critter2", "Critter3", "Critter4");

		cb.getItems().addAll(crits);

		cb.setValue("Craig");
		// add a way to add multiple critters
		Label lbl1 = new Label("How many? ");
		lbl1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		TextField txt1 = new TextField();
		txt1.setText("1");
		txt1.setMaxWidth(50);
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(lbl1, txt1);
		hb1.setLayoutX(280);
		hb1.setLayoutY(50);
		// determine what happens when button btn is pressed
		btn.setOnAction(e -> getChoice(cb, txt1));
		///////////////////////////////////////////////////////////////
		// make timestep button
		Button stepbtn = new Button();
		stepbtn.setLayoutX(5);
		stepbtn.setLayoutY(100);
		stepbtn.setText("Step");
		// add way to select amount of steps
		Label lbl = new Label("Step amount: ");
		lbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		TextField txt = new TextField();
		txt.setMaxWidth(50);
		txt.setText("1");
		HBox hb = new HBox();
		hb.getChildren().addAll(lbl, txt);
		hb.setLayoutX(90);
		hb.setLayoutY(100);
		stepbtn.setOnAction(e -> doStep(txt));
		////////////////////////////////////////////////////
		//make Seed button
		
				Button seedBtn = new Button();
				seedBtn.setLayoutX(275);
				seedBtn.setLayoutY(100);
				seedBtn.setText("Seed");
				// add way to select amount of steps
				Label seedlbl = new Label("Seed #: ");
				seedlbl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
				TextField seedtxt = new TextField();
				seedtxt.setMaxWidth(50);
				seedtxt.setText("1");
				HBox seedhb = new HBox();
				seedhb.getChildren().addAll(seedlbl, seedtxt);
				seedhb.setLayoutX(350);
				seedhb.setLayoutY(100);
				seedBtn.setOnAction(e -> seedSet(seedtxt));
		// keep count of time
		Label timeLbl = new Label("World time: ");
		
		timeWorld.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		HBox t = new HBox();
		t.getChildren().addAll(timeLbl, timeWorld);
	//	t.setSpacing(10);
		t.setLayoutX(5);
		t.setLayoutY(150);
		/////////////////////////////////////////////////////////////
		// add Play/Stop button
		ToggleButton tb = new ToggleButton();
		tb.setText("Play/Stop");
		tb.setLayoutX(5);
		tb.setLayoutY(200);
		// add slider for animation speed
		Slider slider = new Slider();
		slider.setLayoutX(150);
		slider.setLayoutY(200);
		slider.setMin(0);
		slider.setMax(5);
		slider.setValue(0);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(1);
		slider.setBlockIncrement(1);
		tb.setOnAction(e -> timeLoop(tb, slider));
		/////////////////////////////////////////////////////////////
		// add quit button
		Button quitbtn = new Button();
		quitbtn.setText("Quit");
		quitbtn.setLayoutX(400);
		quitbtn.setLayoutY(200);
		quitbtn.setOnAction(e -> quitApp());
		// add stats button/info box

		Label sts = new Label("World Stats:"); // title
		sts.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
		sts.setLayoutX(5);
		sts.setLayoutY(250);
		// make stats button

		for (int i = 0; i < stattxt.length; i++) {
			stattxt[i] = new Text();
		}
		for (int i = 0; i < p.length; i++) {
			p[i] = new Integer(0);
		}
		for(int i=0; i< stat.length;i++){
			stat[i] = new HBox();
		}
		for (int i = 0; i < crits.size(); i++) {
			Label lblstat = new Label(crits.get(i) + ": ");
			stat[i].getChildren().addAll(lblstat,stattxt[i]);
			stat[i].setLayoutX(10);
			stat[i].setLayoutY(statpos);
			statpos += 25;
			

		}

//		Label algLbl = new Label("Algae: ");
//		algtxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
//		HBox a = new HBox();
//		a.getChildren().addAll(algLbl, algtxt);
//		a.setSpacing(10);
//		a.setLayoutX(5);
//		a.setLayoutY(320);
//		Label crlbl = new Label("Craig: ");
//		craigtxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
//		HBox craigbox = new HBox();
//		craigbox.getChildren().addAll(crlbl, craigtxt);
//		craigbox.setSpacing(10);
//		craigbox.setLayoutX(5);
//		craigbox.setLayoutY(350);
//		Label c1lbl = new Label("Critter1: ");
//		c1txt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
//		HBox c1box = new HBox();
//		c1box.getChildren().addAll(c1lbl, c1txt);
//		c1box.setSpacing(10);
//		c1box.setLayoutX(5);
//		c1box.setLayoutY(375);
//		Label c2lbl = new Label("Critter2: ");
//		c2txt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
//		HBox c2box = new HBox();
//		c2box.getChildren().addAll(c2lbl, c2txt);
//		c2box.setSpacing(10);
//		c2box.setLayoutX(5);
//		c2box.setLayoutY(400);
//		Label c3lbl = new Label("Critter3: ");
//		c3txt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
//		HBox c3box = new HBox();
//		c3box.getChildren().addAll(c3lbl, c3txt);
//		c3box.setSpacing(10);
//		c3box.setLayoutX(5);
//		c3box.setLayoutY(425);
//		Label c4lbl = new Label("Critter4: ");
//		c4txt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
//		HBox c4box = new HBox();
//		c4box.getChildren().addAll(c4lbl, c4txt);
//		c4box.setSpacing(10);
//		c4box.setLayoutX(5);
//		c4box.setLayoutY(450);
//		Label aphlbl = new Label("AlgaephobicCritter: ");
//		alphtxt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
//		HBox aphbox = new HBox();
//		aphbox.getChildren().addAll(aphlbl, alphtxt);
//		aphbox.setSpacing(10);
//		aphbox.setLayoutX(5);
//		aphbox.setLayoutY(475);
		// make window
		Pane root = new Pane();
		// add objects to window/
		//aphbox, c4box, c3box, c2box, c1box, craigbox, a,
		root.getChildren().addAll( sts, quitbtn, slider, tb, t, hb1, hb,
				stepbtn, cb, btn, actionTitle, seedBtn, seedhb);
		for(HBox h : stat){
			root.getChildren().add(h);
		}
		// set dimensions of window
		primaryStage.setScene(new Scene(root, 470, 550));
		// show build window
		primaryStage.show();
	}

	// To do something with the value of the selescted item
	private void getChoice(ChoiceBox<String> c, TextField num) {
		String critter = c.getValue();
		int stepnum = Integer.parseInt(num.getText());
		try {
			for (int i = 0; i < stepnum; i++) {
				Critter.makeCritter("assignment5." + critter);
			}
		} catch (InvalidCritterException e) {
			System.out.println("Error, not a critter: " + critter);
		}
		Critter.displayWorld();
		try{
		for(int j=0;j< crits.size();j++){
			p[j] = Critter.getInstances("assignment5."+crits.get(j)).size();
			stattxt[j].setText(p[j].toString());
		}}
		catch(InvalidCritterException e){
			e.printStackTrace();
		}

	}



	// makes the time step and shows world
	private void doStep(TextField txt) {

		int stepnum = Integer.parseInt(txt.getText());
		for (int i = 0; i < stepnum; i++) {
			Critter.worldTimeStep();
			worldTime++;
			timeWorld.setText(worldTime.toString());
			try {
				for(int j=0;j< crits.size();j++){
					p[j] = Critter.getInstances("assignment5."+crits.get(j)).size();
					stattxt[j].setText(p[j].toString());
				}
//				alg = Critter.getInstances("assignment5.Algae").size();
//				algtxt.setText(alg.toString());
//				craig = Critter.getInstances("assignment5.Craig").size();
//				craigtxt.setText(craig.toString());
//				c1 = Critter.getInstances("assignment5.Critter1").size();
//				c1txt.setText(c1.toString());
//				c2 = Critter.getInstances("assignment5.Critter2").size();
//				c2txt.setText(c2.toString());
//				c3 = Critter.getInstances("assignment5.Critter3").size();
//				c3txt.setText(c3.toString());
//				c4 = Critter.getInstances("assignment5.Critter4").size();
//				c4txt.setText(c4.toString());
//				alph = Critter.getInstances("assignment5.AlgaephobicCritter").size();
//				alphtxt.setText(alph.toString());
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
		}

		Critter.displayWorld();
	}

	//set seed values
	private void seedSet(TextField f){
		int seednum = Integer.parseInt(f.getText().toString());
		Critter.setSeed(seednum);
	}
	// loops time to create animation
	private void timeLoop(ToggleButton tb, Slider s) {
		try {
			if (tb.isSelected()) {
				new Thread(new Runnable() {
					public void run() {
						while (tb.isSelected()) {
							try {

								long sleeptime = (long) s.getValue();
								sleeptime *= 100;
								Thread.sleep(550 - sleeptime);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							Critter.worldTimeStep();
							Critter.displayWorld();
							worldTime++;
							timeWorld.setText(worldTime.toString());
							try {
								for(int j=0;j< crits.size();j++){
									p[j] = Critter.getInstances("assignment5."+crits.get(j)).size();
									stattxt[j].setText(p[j].toString());
								}
//								alg = Critter.getInstances("assignment5.Algae").size();
//								algtxt.setText(alg.toString());
//								craig = Critter.getInstances("assignment5.Craig").size();
//								craigtxt.setText(craig.toString());
//								c1 = Critter.getInstances("assignment5.Critter1").size();
//								c1txt.setText(c1.toString());
//								c2 = Critter.getInstances("assignment5.Critter2").size();
//								c2txt.setText(c2.toString());
//								c3 = Critter.getInstances("assignment5.Critter3").size();
//								c3txt.setText(c3.toString());
//								c4 = Critter.getInstances("assignment5.Critter4").size();
//								c4txt.setText(c4.toString());
//								alph = Critter.getInstances("assignment5.AlgaephobicCritter").size();
//								alphtxt.setText(alph.toString());
							} catch (InvalidCritterException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			} else {
				tb.setSelected(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// quit app
	private void quitApp() {
		System.exit(0);
	}
	// get classes

}
