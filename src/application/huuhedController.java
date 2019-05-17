package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
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
	private Label seeAll;
	
	 @FXML
	 private ComboBox<String> haihCombo;
	
	ObservableList<huuhed> observableList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		seeAll.setVisible(false);
		haihCombo.getItems().removeAll(haihCombo.getItems());
		haihCombo.getItems().addAll("Нэр", "Регистрийн дугаар");
		haihCombo.getSelectionModel().select("Нэр");
		ner.setPromptText("Нэр");
		haihCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldText, newText) -> {
		if (oldText !=null) {
		switch(oldText) {
			case "Нэр": ner.setPromptText("Нэр"); 
			break;
			case "Регистрийн дугаар": ner.setPromptText("Регистрийн дугаар"); 
			break;
			}
		}
		if (newText != null) {
		switch(newText) {
		case "Нэр": ner.setPromptText("Нэр");  break;
		case "Регистрийн дугаар": ner.setPromptText("Регистрийн дугаар");
		break;
			}
		}  ;
        });
		colHDugaar.setCellValueFactory(new PropertyValueFactory<>("idx"));
		colHOvog.setCellValueFactory(new PropertyValueFactory<>("ovog"));
		colHNer.setCellValueFactory(new PropertyValueFactory<>("ner"));
		colHHuis.setCellValueFactory(new PropertyValueFactory<>("huis"));
		colHReg.setCellValueFactory(new PropertyValueFactory<>("regdugaar"));
		tableview.setItems(observableList);
		tableview.setRowFactory( tv -> {
		    TableRow<huuhed> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            huuhed rowData = row.getItem();
		            try {
			            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("huuhedProfile.fxml"));
			            Parent root1 = (Parent) fxmlLoader.load();
			
			            huuhedProfileController huuhedProfileController = fxmlLoader.getController();
			            huuhedProfileController.setId(Integer.valueOf(rowData.getDugaar()));
			            huuhedProfileController.fill();
			            
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
	
	 @FXML
	 void btnHaihAction(ActionEvent event) {
		 if(!seeAll.isVisible())
			 seeAll.setVisible(true);
		 {
			 int selected = haihCombo.getSelectionModel().getSelectedIndex();
				switch(selected) {
				case 0:
					try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
				    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner, huis, reg_dugaar from d_huuhed where ner LIKE '%"+ner.getText()+"%'");
				    observableList.clear();
				    int i =1;
				    while(rs.next()){
				        observableList.add(new huuhed(rs.getString("id"),rs.getString("ovog"),rs.getString("ner"),rs.getString("huis"),rs.getString("reg_dugaar"),String.valueOf(i)));
				}
					}
					catch (SQLException e) {
					    e.printStackTrace();
					}
				    break;
				case 1:
					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
				    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner, huis, reg_dugaar from d_huuhed where reg_dugaar='"+ner.getText()+"'");
				    observableList.clear();
				    int i =1;
				    while(rs.next()){
				        observableList.add(new huuhed(rs.getString("id"),rs.getString("ovog"),rs.getString("ner"),rs.getString("huis"),rs.getString("reg_dugaar"),String.valueOf(i)));
				    }
					}
					catch (SQLException e) {
					    e.printStackTrace();
					}
				    break; }
		 }
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
			 huuhed m = tableview.getSelectionModel().getSelectedItem();
			 observableList.remove(m);
	         try {
	        	 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
	             Statement stmt = conn.createStatement();
	             String sql = "Delete From d_huuhed where ner='"+m.getNer()+"'";
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
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("huuhedEdit.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            huuhedEditController huuhedEditController = fxmlLoader.getController();
	            huuhedEditController.setId(Integer.valueOf(tableview.getSelectionModel().getSelectedItem().getDugaar()));
	            huuhedEditController.fill();
	            
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
	
	@FXML
	void bugdiigHarah(MouseEvent event) {
		ner.clear();
		seeAll.setVisible(false);
		refresh();
	}
}