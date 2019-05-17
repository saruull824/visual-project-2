package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class vaktsinProfileController implements Initializable {
	private int id = 0;
	
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}

	
	@FXML
    private TableView<vaktsinProfile> tableview;

    @FXML
    private TableColumn<vaktsinProfile, String> h_id;

    @FXML
    private TableColumn<vaktsinProfile, String> e_id;

    @FXML
    private TableColumn<vaktsinProfile, String> v_id;

    @FXML
    private TableColumn<vaktsinProfile, String> s_id;

    @FXML
    private Label ner;

    @FXML
    private Label tun;
	
    ObservableList<vaktsinProfile> observableList = FXCollections.observableArrayList();
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	h_id.setCellValueFactory(new PropertyValueFactory<>("h_id"));
    	e_id.setCellValueFactory(new PropertyValueFactory<>("e_id"));
    	v_id.setCellValueFactory(new PropertyValueFactory<>("v_id"));
    	s_id.setCellValueFactory(new PropertyValueFactory<>("s_id"));
    	tableview.setItems(observableList);
	}
	
	 void fill() {
		try {
			if (id != 0) {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
				Statement myStmt = (Statement) myConn.createStatement();
				String sql = "select * from d_vaktsin v left join d_tun t on v.id=t.v_id where id='"+id+"'";
				ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				myRs.next();
				ner.setText(myRs.getString("ner"));
				tun.setText(myRs.getString("tun"));
				sql = "select d.*, e.ovog as eovog, e.ner as ener, s.ovog as sovog, s.ner as sner, v.ner as vner, h.ovog as hovog, h.ner as hner from d_darhlaa d left join d_ajiltan e on d.e_id=e.id left join d_ajiltan s on d.s_id=s.id left join d_vaktsin v on d.v_id=v.id left join d_huuhed h on d.h_id=h.id"; 
				//where h_id='"+id+"'";
				myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				while(myRs.next()) {
					observableList.add(new vaktsinProfile(myRs.getString("h_id"), myRs.getString("e_id"), myRs.getString("v_id"), myRs.getString("s_id")));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
