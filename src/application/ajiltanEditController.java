package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ajiltanEditController implements Initializable {
	private int id = 0;
	
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	@FXML
    private TextField aNer;

    @FXML
    private TextField aOvog;

    @FXML
    private ComboBox<String> tushaal;

    @FXML
    private Button exit;

    @FXML
    private Button aHadgalah1;
	
	void fill() {
		try {
			if (id != 0) {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
				Statement myStmt = (Statement) myConn.createStatement();
				String sql = "select * from d_ajiltan h left join d_asran_hamgaalagch a on h.id=a.h_id where id='"+id+"'";
				ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				myRs.next();
				aOvog.setText(myRs.getString("ovog"));
				aNer.setText(myRs.getString("ner"));
				if(myRs.getString("tushaal").equals("эмч"))
					tushaal.getSelectionModel().select(0);
				else
					tushaal.getSelectionModel().select(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void aHadgalahAction(ActionEvent event) {
		int error = 0;
		if(aOvog.getText().isEmpty() || 
			aNer.getText().isEmpty() ||
			tushaal.getSelectionModel().isEmpty())
			error = 1;
		else {
			if (id == 0) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
					Statement myStmt = (Statement) conn.createStatement();
					String sql = "INSERT INTO d_ajiltan(ovog, ner, tushaal) VALUES ('"
							+ aOvog.getText() + "', '" 
							+ aNer.getText() + "','" 
							+ tushaal.getSelectionModel().getSelectedItem() +"')";
					((java.sql.Statement) myStmt).execute(sql);
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
					Statement myStmt = (Statement) conn.createStatement();
					String query = "UPDATE d_ajiltan SET ovog = '"
					+aOvog.getText()+ "', ner = '"
					+aNer.getText()+"', tushaal = '"
					+tushaal.getSelectionModel().getSelectedItem()+"' where id ='" +id+"'";
					((java.sql.Statement) myStmt).execute(query);
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		if(error == 1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Мэдээлэл");
			alert.setHeaderText(null);
			alert.setContentText("Хоосон талбаруудыг бөглөнө үү.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Мэдээлэл");
			alert.setHeaderText(null);
			alert.setContentText("Амжилттай хадгаллаа.");
			alert.showAndWait();
			Stage stage = (Stage) aHadgalah1.getScene().getWindow();
			stage.close();
		}
	}
	
	 @FXML
	    void OnExitAction(ActionEvent event) {
		  Stage stage = (Stage) exit.getScene().getWindow();
		    stage.close();
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> tushaalOptions = FXCollections.observableArrayList();
		tushaal.setItems(tushaalOptions);
		tushaalOptions.addAll("эмч", "сувилагч");
		tushaal.getSelectionModel().select(0);
	}
}
