package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


public class mainController implements Initializable {
		
		@FXML
		private Button btnEhlel;

		@FXML
		private Button btnHuuhed;

		@FXML	
		private Button btnVaktsin;

	    @FXML
	    private Button btnProgram;
	
	    @FXML
	    private Button btnGarah;
	    
	    @FXML
	    private Button btnHaih;

		@FXML
	    private BorderPane borderpane;
 
	    @FXML
	    void btnNemehAction(ActionEvent event) {

	    }

	    @FXML
	    void btnUstgahAction(ActionEvent event) {

	    }

	    @FXML
	    void btnZasahAction(ActionEvent event) {

	    }

	    @FXML
	    void test(MouseEvent event) {

	    }
    
    @Override
 	public void initialize(URL location, ResourceBundle resources) {

 	}
    
	ObservableList<huuhed> observableList = FXCollections.observableArrayList();
	ObservableList<vaktsin> observableList1 = FXCollections.observableArrayList();
    @FXML
    void ehlel(MouseEvent event) {
    	loadALL("ehlel");
    }

    @FXML
    void garah(MouseEvent event) {
    	Stage stage = (Stage) btnGarah.getScene().getWindow();
		// do what you have to do
		stage.close();
    }

    @FXML
    void huuhed(MouseEvent event) {
    	loadALL("huuhed");
    }

    @FXML
    void program(MouseEvent event) {
    	loadALL("program");
    }

    @FXML
    void vaktsin(MouseEvent event) {
    	loadALL("vaktsin1");
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
    
    @FXML
    void btnHaihAction(ActionEvent event) {

    }
}
