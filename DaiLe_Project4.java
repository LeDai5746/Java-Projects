import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DaiLe_Project4 extends Application {
	//We create the text fields for the user and the button
	private TextField tfName = new TextField();
	private TextField tfProb = new TextField();
	private TextField tfLRange = new TextField();
	private TextField tfHRange = new TextField();
	private Button btGo = new Button("Go");
	//The string for the operation that will be printed when the button is pressed
	String operation;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		//We create a gridpane and place all the textfields in it
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		//A label prompts the user to put information
		gridPane.add(new Label("Enter your Name"), 0, 0);
		gridPane.add(tfName, 1, 0);
		//A new label is created to display errors if they exist
		Label Name = new Label();
		gridPane.add(Name, 2, 0);
		gridPane.add(new Label("How many Problems?"), 0, 1);
		gridPane.add(tfProb, 1, 1);
		Label Prob = new Label();
		gridPane.add(Prob, 2, 1);
		gridPane.add(new Label("Lowest value for range of factors"), 0, 2);
		gridPane.add(tfLRange, 1, 2);
		Label lRange = new Label();
		gridPane.add(lRange, 2, 2);
		gridPane.add(new Label("Highest value for range of factors"), 0, 3);
		gridPane.add(tfHRange, 1, 3);
		Label hRange = new Label();
		gridPane.add(hRange, 2, 3);
		gridPane.add(btGo, 1, 5);
		gridPane.setAlignment(Pos.CENTER);
		
		//A new toggle group is created for the operation selection
		ToggleGroup tg = new ToggleGroup();
		//We add new radio buttons for each operation and add it to the toggle group tg
		RadioButton add = new RadioButton("Addition +");
		add.setToggleGroup(tg);
		//Addition is selected by default so the user cannot send a null value
		add.setSelected(true);
		RadioButton sub = new RadioButton("Subtraction -");
		sub.setToggleGroup(tg);
		RadioButton mult = new RadioButton("Multiplication *");
		mult.setToggleGroup(tg);
		RadioButton div = new RadioButton("Division /");
		div.setToggleGroup(tg);
		//A vbox is created to display the radio buttons vertically
		VBox box = new VBox(10, add, sub, mult, div);
		gridPane.add(new Label("Problem Type"), 0, 4);
		gridPane.add(box, 1, 4);
		
		//The scene is created with the title
		Scene scene = new Scene(gridPane, 400, 300);
		primaryStage.setTitle("Welcome to Flashcards");
		primaryStage.setScene(scene);
		primaryStage.show();

		//The first exception handling is created for the name
		//The event kicks in when a key is pressed
		tfName.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				//We check for the enter, tab, and up/down keys
				if (event.getCode() == KeyCode.ENTER ^ event.getCode() == KeyCode.TAB ^ event.getCode() == KeyCode.UP ^ event.getCode() == KeyCode.DOWN) {
					//If the textfield is null or empty we throw an error and set the empty label to display an error next to the text field
					if (tfName.getText() == null || tfName.getText().isEmpty()) {
						System.out.println("Please reenter your name.");
						Name.setText("Invalid");
					}
					//Here we create an array from the string entered into the textfield
					char[] nameChar = tfName.getText().toCharArray();
					//A for loop checks each character to see if it is a space, letter, or period
					for (char c : nameChar) {
						if (!Character.isLetter(c) && c != ' ' && c != '.') {
							System.out.println("Please reenter your name.");
							Name.setText("Invalid");
							break;
						//If all the characters are accepted, the error message is set to null once the user reenters the above keys
						} else
							Name.setText(null);
					}
					//If the user presses up we go back to the loop, otherwise we go to the next text field
					if (event.getCode() == KeyCode.UP) {
						tfHRange.requestFocus();
					} else
						tfProb.requestFocus();
				}
			}
		});
		//We follow the same procedure
		tfProb.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER ^ event.getCode() == KeyCode.TAB ^ event.getCode() == KeyCode.UP ^ event.getCode() == KeyCode.DOWN) {
					//A try catch system is used to detect if the input is an integer
					try {
						Integer.parseInt(tfProb.getText());
						//If the textfield is less than 1 we throw an error
						if (Integer.parseInt(tfProb.getText()) < 1) {
							Prob.setText("Invalid");
							System.out.println("Please enter a number greater than 0.");
						} else
							Prob.setText(null);
					//This catch checks if the text field is an integer
					} catch (NumberFormatException ex) {
						Prob.setText("Invalid");
						System.out.println("Please enter an integer.");
					}
					if (event.getCode() == KeyCode.UP) {
						tfName.requestFocus();
					} else
						tfLRange.requestFocus();
				}
			}
		});
		//We check the lower range for errors
		tfLRange.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER ^ event.getCode() == KeyCode.TAB ^ event.getCode() == KeyCode.UP ^ event.getCode() == KeyCode.DOWN) {
					try {
						Integer.parseInt(tfLRange.getText());
						//The lower range cannot be less than zero
						if (Integer.parseInt(tfLRange.getText()) < 0) {
							lRange.setText("Invalid");
							System.out.println("Please enter a non negative number.");
						} else
							lRange.setText(null);
					} catch (NumberFormatException ex) {
						lRange.setText("Invalid");
						System.out.println("Please enter an integer.");
					}
					if (event.getCode() == KeyCode.UP) {
						tfProb.requestFocus();
					} else
						tfHRange.requestFocus();
				}
			}
		});
		//We check the upper range for errors
		tfHRange.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER ^ event.getCode() == KeyCode.TAB ^ event.getCode() == KeyCode.UP ^ event.getCode() == KeyCode.DOWN) {
					try {
						Integer.parseInt(tfHRange.getText());
						if (Integer.parseInt(tfHRange.getText()) < 0) {
							hRange.setText("Invalid");
							System.out.println("Please enter a non negative number.");
						//If the upper range is lower than the lower range, the user is prompted for a new value
						} else if (Integer.parseInt(tfHRange.getText()) <= Integer.parseInt(tfLRange.getText())) {
							hRange.setText("Invalid");
							System.out.println("Please enter a number greater than the lower range.");
						} else
							hRange.setText(null);
					} catch (NumberFormatException ex) {
						hRange.setText("Invalid");
						System.out.println("Please enter an integer.");
					}
					if (event.getCode() == KeyCode.UP) {
						tfLRange.requestFocus();
					} else
						tfName.requestFocus();
				}
			}
		});
		//Here we assign a value to the string operation depending on which radio button is selected
		add.setOnAction(e -> {
			if (add.isSelected()) {
				operation = "A";
			}
		});
		sub.setOnAction(e -> {
			if (sub.isSelected()) {
				operation = "S";
			}
		});
		mult.setOnAction(e -> {
			if (mult.isSelected()) {
				operation = "M";
			}
		});
		div.setOnAction(e -> {
			if (div.isSelected()) {
				operation = "D";
			}
		});

		//We create a boolean binding to check the text fields are empty or the error messages are not empty
		BooleanBinding goEnable = tfName.textProperty().isEmpty().or(tfProb.textProperty().isEmpty()).or(tfLRange.textProperty().isEmpty()).or(tfHRange.textProperty().isEmpty())
				.or(Name.textProperty().isNotEmpty()).or(Prob.textProperty().isNotEmpty()).or(lRange.textProperty().isNotEmpty()).or(hRange.textProperty().isNotEmpty());
		//If the boolean binding finds an empty text field or non empty error message the button is disabled
		btGo.disableProperty().bind(goEnable);
		//The button prints the user input values when pressed
		btGo.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event1) {
				System.out.println(tfName.getText() + " " + tfProb.getText() + " " + tfLRange.getText() + " "
						+ tfHRange.getText() + " " + operation);
			}
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
}