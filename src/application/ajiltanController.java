package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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

public class ajiltanController implements Initializable{

	@FXML
	private ComboBox<String> haihCombo;
    
	@FXML
    private BorderPane borderpane;

    @FXML
    private TextField ner;

    @FXML
    private Button btnAjiltanHaih;

    @FXML
    private Button btnAjiltanZasah;

    @FXML
    private Button btnAjiltanNemeh;

    @FXML
    private Button btnAjiltanUstgah;
    
    @FXML
	private Label seeAll;

    @FXML
    private TableView<ajiltan> tableview;

    @FXML
    private TableColumn<ajiltan, String> colHDugaar;

    @FXML
    private TableColumn<ajiltan, String> colAOvog;

    @FXML
    private TableColumn<ajiltan, String> colANer;

    @FXML
    private TableColumn<ajiltan, String> colATushaal;
    
    ObservableList<ajiltan> observableListA = FXCollections.observableArrayList();
	
    // @Override
 	public void initialize(URL location, ResourceBundle resources) {
 		seeAll.setVisible(false);
 		haihCombo.getItems().removeAll(haihCombo.getItems());
 		haihCombo.getItems().addAll("Овог", "Нэр", "Албан тушаал");
 		haihCombo.getSelectionModel().select("Овог");
 		ner.setPromptText("Овог");
		haihCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldText, newText) -> {
		if (oldText !=null) {
		switch(oldText) {
			case "Овог": ner.setPromptText("Овог"); 
			break;
			case "Нэр": ner.setPromptText("Нэр"); 
			break;
			case "Албан тушаал": ner.setPromptText("Албан тушаал");
			break;
			}
		}
		if (newText != null) {
		switch(newText) {
		case "Овог": ner.setPromptText("Овог");  break;
		case "Нэр": ner.setPromptText("Нэр");
		break;
		case "Албан тушаал": ner.setPromptText("Албан тушаал");
		break;
			}
		}  ;
        });
 		colHDugaar.setCellValueFactory(new PropertyValueFactory<>("idx"));
 		colAOvog.setCellValueFactory(new PropertyValueFactory<>("ovog"));
 		colANer.setCellValueFactory(new PropertyValueFactory<>("ner"));
 		colATushaal.setCellValueFactory(new PropertyValueFactory<>("tushaal"));
 		tableview.setItems(observableListA);
 		tableview.setRowFactory( tv -> {
		    TableRow<ajiltan> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            ajiltan rowData = row.getItem();
		            try {
			            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajiltanProfile.fxml"));
			            Parent root1 = (Parent) fxmlLoader.load();
			            ajiltanProfileController ajiltanProfileController = fxmlLoader.getController();
			            ajiltanProfileController.setId(Integer.valueOf(rowData.getDugaar()));
			            ajiltanProfileController.fill();
			            
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
 			ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery("select id, ovog, ner, tushaal from d_ajiltan");
 			int i = 1;
 			while (myRs.next()) {
 				
 				ajiltan ajiltan = new ajiltan(myRs.getString("id"), myRs.getString("ovog"),
 						myRs.getString("ner"), myRs.getString("tushaal"), String.valueOf(i));
 				tableview.getItems().add(ajiltan);
 				i++;
 			}
 			conn.close();
 		} catch (Exception e) {
 			System.err.println(e.getMessage());
 		}
 	}

    @FXML
    void btnAjiltanHaihAction(ActionEvent event) {
    	 if(!seeAll.isVisible())
			 seeAll.setVisible(true);
    	 {
    	int selected = haihCombo.getSelectionModel().getSelectedIndex();
 		switch(selected) {
 		case 0:
 			try {
 			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
 		    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner, tushaal from d_ajiltan where ovog LIKE'%"+ner.getText()+"%'");
 		    observableListA.clear();
 		    int i =1;
 		    while(rs.next()){
 		        observableListA.add(new ajiltan(rs.getString("id"),rs.getString("ovog"),rs.getString("ner"),rs.getString("tushaal"),String.valueOf(i)));
 		}
 			}
 			catch (SQLException e) {
 			    e.printStackTrace();
 			}
 		    break;
 		case 1:
 			try {
 			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
 		    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner, tushaal from d_ajiltan where ner LIKE '%"+ner.getText()+"%'");
 		    observableListA.clear();
 		    int i =1;
 		    while(rs.next()){
 		        observableListA.add(new ajiltan(rs.getString("id"),rs.getString("ovog"),rs.getString("ner"),rs.getString("tushaal"),String.valueOf(i)));
 		    }
 			}
 			catch (SQLException e) {
 			    e.printStackTrace();
 			}
 		    break;
 		case 2:
 			try {
		 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
		    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner, tushaal from d_ajiltan where tushaal='"+ner.getText()+"'");
		    observableListA.clear();
		    int i =1;
		    while(rs.next()){
		        observableListA.add(new ajiltan(rs.getString("id"),rs.getString("ovog"),rs.getString("ner"),rs.getString("tushaal"),String.valueOf(i)));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
 			break;
 		}
    	 }
    }

    @FXML
    void btnAjiltanNemehAction(ActionEvent event) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajiltanEdit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
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

    @FXML
    void btnAjiltanUstgahAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Баталгаажуулалт");
		alert.setHeaderText(null);
		alert.setContentText("Устгах уу?");
		ButtonType buttonTypeOk = new ButtonType("Тийм");
		ButtonType buttonTypeCancel = new ButtonType("Үгүй");
		alert.getButtonTypes().setAll(buttonTypeOk,buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		 if (result.get() == buttonTypeOk){
			 ajiltan a = tableview.getSelectionModel().getSelectedItem();
			 observableListA.remove(a);
	         try {
	        	 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
	             Statement stmt = conn.createStatement();
	             String sql = "Delete From d_ajiltan where ner='"+a.getNer()+"'";
	             stmt.execute(sql);
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
		 } else {
			
		     // ... user chose CANCEL or closed the dialog
		 }
    }

    @FXML
    void btnAjiltanZasahAction(ActionEvent event) {
    	if(!tableview.getSelectionModel().isEmpty()) {
			try {
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajiltanEdit.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            ajiltanEditController ajiltanEditController = fxmlLoader.getController();
	            ajiltanEditController.setId(Integer.valueOf(tableview.getSelectionModel().getSelectedItem().getDugaar()));
	            ajiltanEditController.fill();
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
    void bugdiigHarahA(MouseEvent event) {
    	ner.clear();
		seeAll.setVisible(false);
		refresh();
    }
}



