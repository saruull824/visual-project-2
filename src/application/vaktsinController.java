package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



public class vaktsinController implements Initializable {

	 	@FXML
	    private BorderPane borderpane;

	    @FXML
	    private Button btnHaih;

	    @FXML
	    private Button btnZasah;

	    @FXML
	    private Button btnNemeh;

	    @FXML
	    private Button btnUstgah;	      

	    @FXML
	    private TableView<vaktsin> tableview;

	    @FXML
	    private TableColumn<vaktsin, String> colVDugaar;

	    @FXML
	    private TableColumn<vaktsin, String> colVNer;

	    @FXML
	    private TableColumn<vaktsin, String> colVTun;

	    @FXML
	    private TableColumn<vaktsin, String> colVNas;

	    @FXML
	    private TableColumn<vaktsin, String> colVDuration;

		
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

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
    	colVDugaar.setCellValueFactory(new PropertyValueFactory<>("dugaar"));
 		colVNer.setCellValueFactory(new PropertyValueFactory<>("ner"));
 		colVTun.setCellValueFactory(new PropertyValueFactory<>("tun"));
 		colVNas.setCellValueFactory(new PropertyValueFactory<>("nas"));
 		colVDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
 		tableview.setItems(observableList1);
 		refresh();
 	}
    
    void refresh() {
    	tableview.getItems().clear();
		try {
			// create a mysql database connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
			Statement myStmt = (Statement) conn.createStatement();
			ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery("select id, ner, tun, tungiin_nas, tungiin_duration from d_vaktsin");
			while (myRs.next()) {
				vaktsin vaktsin = new vaktsin(myRs.getString("id"), myRs.getString("ner"),myRs.getInt("tun"),
						myRs.getInt("tungiin_nas"), myRs.getInt("tungiin_duration"));
				tableview.getItems().add(vaktsin);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	ObservableList<vaktsin> observableList1 = FXCollections.observableArrayList();


    @FXML
    void garah(MouseEvent event) {
    	Stage stage = (Stage) btnGarah.getScene().getWindow();
		// do what you have to do
		stage.close();
    }
    

    @FXML
    void btnHaihAction(ActionEvent event) {

    }

    @FXML
    void btnNemehAction(ActionEvent event) {

    }

    @FXML
    void btnUstgahAction(ActionEvent event) {
   	 Alert alert = new Alert(AlertType.CONFIRMATION);
	 alert.setTitle("Confirmation");
	 alert.setHeaderText(" ");
	 alert.setContentText("Устгах уу?");

	 Optional<ButtonType> result = alert.showAndWait();
	 if (result.get() == ButtonType.OK){
		 vaktsin v = tableview.getSelectionModel().getSelectedItem();
		 observableList1.remove(v);
         try {
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
 			Statement myStmt = (Statement) conn.createStatement();
             Statement stmt = conn.createStatement();
             String sql = "Delete From d_vaktsin where ner='"+v.getNer()+"'";
             int delc=stmt.executeUpdate(sql);
             sql = "Delete From d_vaktsin where ner =?";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setString(1,v.getNer());
             delc = pstmt.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }

		 
		 
		 
	 } else {
	     // ... user chose CANCEL or closed the dialog
	 }
    }

    @FXML
    void btnZasahAction(ActionEvent event) {

    }



//    @FXML
//    void huuhed(MouseEvent event) {
//    	loadALL("huuhed");
//   
//    }
//
//    @FXML
//    void program(MouseEvent event) {
//    	loadALL("program");
//    }

//    @FXML
//    void vaktsin(MouseEvent event) {
//    	loadALL("vaktsin1");
//    }
//    
    
//    private void loadALL(String all) {
//    	Parent root = null;
//    	try{
//    		root= FXMLLoader.load(getClass().getResource(all+".fxml"));
//    	}
//    	 catch(Exception e) {
// 	    	e.printStackTrace();
// 	    	}
//    	borderpane.setCenter(root);
//    }
//    
//    


	    
}
