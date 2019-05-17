package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class mainController implements Initializable{
	@FXML
    private BorderPane borderpane;
		
	@FXML
	private Button btnEhlel;

	@FXML
	private Button btnHuuhed;

	@FXML	
	private Button btnVaktsin;

    @FXML
    private Button btnAjiltan;

    @FXML
    private Button btnGarah;
    
    @FXML
    private Button btnTailan; 
	
    @FXML
    void ehlel(MouseEvent event) {
    	loadALL("ehlel");
    }

    @FXML
    void garah(MouseEvent event) {
    	Stage stage = (Stage) btnGarah.getScene().getWindow();
		stage.close();
    }

    @FXML
    void huuhed(MouseEvent event) {
    	loadALL("huuhed2");
    }

    @FXML
    void ajiltan(MouseEvent event) {
    	loadALL("ajiltan");
    }

    @FXML
    void vaktsin(MouseEvent event) {
    	loadALL("vaktsin1");
    }

    @FXML
    void tailan(MouseEvent event) {
    	loadALL("tailan");
    }
    
    
    private void loadALL(String all) {
    	Parent root = null;
    	try{
    		root= FXMLLoader.load(getClass().getResource(all+".fxml"));
    	}
    	 catch(Exception e) {
 	    	e.printStackTrace();
 	    	}
    	borderpane.setCenter(root);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadALL("ehlel");
	}
}
