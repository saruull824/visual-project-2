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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


public class huuhedController implements Initializable {

	@FXML
	private TableView<huuhed> tableview;
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
	    private TextField ner;
	    
	   @FXML
	    private TableColumn<huuhed, String> colHDugaar;

	    @FXML
	    private TableColumn<huuhed, String> colHOvog;

	    @FXML
	    private TableColumn<huuhed, String> colHNer;

	    @FXML
	    private TableColumn<huuhed, String> colHHuis;

	    @FXML
	    private TableColumn<huuhed, String> colHReg;
	   
	    @FXML
	    private TableColumn<huuhed, Button> colHButton;

    @Override
 	public void initialize(URL location, ResourceBundle resources) {
 		colHDugaar.setCellValueFactory(new PropertyValueFactory<>("idx"));
 		colHOvog.setCellValueFactory(new PropertyValueFactory<>("ovog"));
 		colHNer.setCellValueFactory(new PropertyValueFactory<>("ner"));
 		colHHuis.setCellValueFactory(new PropertyValueFactory<>("huis"));
 		colHReg.setCellValueFactory(new PropertyValueFactory<>("regdugaar"));
 		tableview.setItems(observableList);
 		refresh();
 	}
    

    void refresh() {
		tableview.getItems().clear();
		try {
			// create a mysql database connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
			Statement myStmt = (Statement) conn.createStatement();
			ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery("select id, ovog, ner, huis, reg_dugaar from d_huuhed");
			int i = 1;
			while (myRs.next()) {
				huuhed huuhed = new huuhed(myRs.getString("id"), myRs.getString("ovog"),
						myRs.getString("ner"), myRs.getString("huis"), myRs.getString("reg_dugaar"), String.valueOf(i));
				tableview.getItems().add(huuhed);
				i++;
			}
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	ObservableList<huuhed> observableList = FXCollections.observableArrayList();

	 @FXML
	    void btnHaihAction(ActionEvent event) {
		 try {
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
		            ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner, huis, reg_dugaar from d_huuhed where ner='"+ner.getText()+"'");
		            observableList.clear();
		            int i =1;
		            while(rs.next()){
		                observableList.add(new huuhed(rs.getString("id"),rs.getString("ovog"),rs.getString("ner"),rs.getString("huis"),rs.getString("reg_dugaar"),String.valueOf(i)));
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
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
	    		 huuhed m = tableview.getSelectionModel().getSelectedItem();
	    		 observableList.remove(m);
		        
		         try {
		        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
		 			Statement myStmt = (Statement) conn.createStatement();
		             Statement stmt = conn.createStatement();
		             String sql = "Delete From d_huuhed where ner='"+m.getNer()+"'";
		             int delc=stmt.executeUpdate(sql);
		             sql = "Delete From d_huuhed where ner =?";
		             PreparedStatement pstmt = conn.prepareStatement(sql);
		             pstmt.setString(1,m.getNer());
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

	    @FXML
	    void bugdiigHarah(MouseEvent event) {
	    	refresh();
	    }
    @FXML
    void test(MouseEvent event) {
    	refresh();
    }
    
}