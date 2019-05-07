package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class SecondWindowController implements Initializable {

	
	@FXML
	private TextArea txtAInfo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}

	public void populateData(Member member) {
		
		// test printing
		System.out.println("I'm in the second controller.");
		System.out.println(member.getName().getFirstName());
		
		
		String formattedText = String.format("The information you provided is:"
				+ "%nFirst Name: %s Last Name: %s%nType: %s%nNotification Option: %s%nLocation: %s"
				+ "%nNumber of Bedroom: %s%nMonthly Price: %s%nYour Comment: %s", 
				member.getName().getFirstName(), member.getName().getLastName(),member.getType(), member.getNotice(), 
				member.getLocation(), member.getRoom(), member.getPrice(), member.getComment()
				);
		txtAInfo.setText(formattedText);
		
	}

}
