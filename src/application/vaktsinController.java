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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



public class vaktsinController implements Initializable {

	  @FXML
	    private BorderPane borderpane;

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
 		tableview.setRowFactory( tv -> {
		    TableRow<vaktsin> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            vaktsin rowData = row.getItem();
		            try {
			            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vaktsinProfile.fxml"));
			            Parent root1 = (Parent) fxmlLoader.load();
			            vaktsinProfileController vaktsinProfileController = fxmlLoader.getController();
			            vaktsinProfileController.setId(Integer.valueOf(rowData.getDugaar()));
			            vaktsinProfileController.fill();
			            Stage stage = new Stage();
			            stage.initModality(Modality.APPLICATION_MODAL);
			            stage.setTitle(null);
			            stage.setResizable(false);
			            stage.setScene(new Scene(root1));
			            stage.show();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
		        }
		    });
		    return row ;
		});
 		refresh();
 	}
    
    void refresh() {
    	tableview.getItems().clear();
		try {
			// create a mysql database connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
			Statement myStmt = (Statement) conn.createStatement();
			ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery("select v.id, v.ner, t.tun, t.tungiin_nas, t.tungiin_duration from d_vaktsin v, d_tun t where t.v_id=v.id");
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
    void btnNemehAction(ActionEvent event) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("huuhedEdit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(null);
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.setOnHidden(e -> {
                refresh();
            });
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUstgahAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Баталгаажуулалт");
		alert.setHeaderText(null);
		alert.setContentText("Устгах уу?");
		ButtonType buttonTypeOk = new ButtonType("Тийм");
		ButtonType buttonTypeCancel = new ButtonType("Үгүй");
		alert.getButtonTypes().setAll(buttonTypeOk,buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		 if (result.get() == buttonTypeOk){
			 vaktsin v = tableview.getSelectionModel().getSelectedItem();
			 observableList1.remove(v);
	         try {
	        	 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
	             Statement stmt = conn.createStatement();
	             String sql = "Delete From d_vaktsin where ner='"+v.getNer()+"'";
	             stmt.execute(sql);
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
		 } else {
			
		 }
    }

    @FXML
    void btnZasahAction(ActionEvent event) {
    	if(!tableview.getSelectionModel().isEmpty()) {
			try {
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vaktsinEdit.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            vaktsinEditController vaktsinEditController = fxmlLoader.getController();
	            vaktsinEditController.setId(Integer.valueOf(tableview.getSelectionModel().getSelectedItem().getDugaar()));
	            vaktsinEditController.fill();
	            
	            Stage stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.setTitle(null);
	            stage.setResizable(false);
	            stage.setScene(new Scene(root1));
	            stage.setOnHidden(e -> {
	                refresh();
	            });
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
    }


	    
}
