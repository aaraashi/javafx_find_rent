package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Member;
import application.model.Name;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController implements Initializable {

	
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextArea txtAComm;
	
	@FXML
	private Slider sldPrice;
	@FXML
	private Label lblPrice = new Label();
	
	@FXML
	private CheckBox chBx1;
	@FXML
	private CheckBox chBx2;
	@FXML
	private Label chOption;
	
	@FXML
	private Label lblSelectedLocation;
	@FXML
	private ListView<MyLocation> locationList;
	private final ObservableList<MyLocation> locations = FXCollections.observableArrayList();
	@FXML
	private Button bttnLocation;
	
	@FXML
	private RadioButton rdBttn1;
	@FXML
	private RadioButton rdBttn2;
	@FXML
	private RadioButton rdBttn3;
	@FXML
	private Label rdOption;
	private Type type; 
	@FXML
	private ToggleGroup typeToggleGroup;
	@FXML
	private ImageView imageView;

	
	
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private Label bedroomNum;
	@FXML
	private Label bedroomLabel;
	
	@FXML
	private Button bttnSubmit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		
		lblPrice.setText("Monthly Price: " + (int) sldPrice.getValue());

		sldPrice.valueProperty().addListener(
	         new ChangeListener<Number>() {
	        	 @Override
	        	 public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
	        		 lblPrice.setText("Monthly Price: " + newValue.intValue());
	        	 }
	         });
		
		
		rdBttn1.setUserData(Type.APARTMENT);
		rdBttn2.setUserData(Type.HOUSE);
		rdBttn3.setUserData(Type.CONDO);
		
		locations.add(new MyLocation(1, "Toronto GTA"));
		locations.add(new MyLocation(2, "Mississauga"));
		locations.add(new MyLocation(1, "North York"));
		locations.add(new MyLocation(1, "Scarborough"));
		locations.add(new MyLocation(1, "Etobicoke"));
		locationList.setItems(locations);
		
		
		locationList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyLocation>() {
		@Override
		public void changed(ObservableValue<? extends MyLocation> ov, MyLocation oldValue, MyLocation newValue) {
				lblSelectedLocation.setText("" + newValue.getLocationName());
		}
		});
	
		
		comboBox.getItems().removeAll(comboBox.getItems());
	    comboBox.getItems().addAll("1", "2", "3");
	    comboBox.getSelectionModel().select("2");
	    bedroomLabel.setText(String.valueOf(comboBox.getSelectionModel().getSelectedItem()));
		
	}
	
	// Start radio button section
	@FXML
	private void itemRadioButtonSelected(ActionEvent e) throws Exception {
		
		type = (Type) typeToggleGroup.getSelectedToggle().getUserData();
		displayImage();
	}

	@FXML
	private void displayImage() throws Exception {
		
		Image img1 = new Image("/application/image/apartment.jpg");
		Image img2 = new Image("/application/image/house.jpg");
		Image img3 = new Image("/application/image/condo.jpg");
		Image img4 = new Image("/application/image/default.png");
		
		if(type.equals(Type.APARTMENT)) {
			imageView.setImage(img1);
		}else if(type.equals(Type.HOUSE)) {
			imageView.setImage(img2);  
		}else if(type.equals(Type.CONDO)) {
			imageView.setImage(img3);  
		}
		else {
			imageView.setImage(img4);  
			
		}
	}
	// End radio button section
	
	

	// Start list view section
	/*@FXML
	private void displayLocation(ActionEvent event) {
		ObservableList<MyLocation> selectedItemList = locationList.getSelectionModel().getSelectedItems(); 
		lblSelectedLocation.setText("" + selectedItemList.get(0));
	}*/
	// End list view section
	
	// Start check box section
	@FXML
	private void handleCheckboxAction(ActionEvent event) {
		chOption.setText("");
		if(chBx1.isSelected()) {
			chOption.setText(chBx1.getText());
		}
		if(chBx2.isSelected()) {
			chOption.setText(chOption.getText() + " " + chBx2.getText());
		}

	}
	// End check box section
	
	// Start combo box section
	@FXML
	private void handleComboBoxAction(ActionEvent event) {
		bedroomLabel.setText(String.valueOf(comboBox.getSelectionModel().getSelectedItem()));

	}
	
	// End combo box section
	
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		
		// composition
		Member member = new Member();
		Name name = new Name();
		member.setName(name);
		name.setFirstName(txtFirstName.getText());
		name.setLastName(txtLastName.getText());
		member.setType(type);
		String notice = chOption.getText();
		member.setNotice(notice);
		String location = lblSelectedLocation.getText();
		member.setLocation(location);
		String room = bedroomLabel.getText();
		member.setRoom(room);
		String price = lblPrice.getText();
		member.setPrice(price);
		String comment = txtAComm.getText();
		member.setComment(comment);
		
		// open second window	
		try {
			Stage stage = new Stage();
			String fxmlFileName = "SecondWindow.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			SecondWindowController controller = loader.<SecondWindowController>getController();
			controller.populateData(member);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}	

