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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class darhlaaHistoryController implements Initializable{
	@FXML
    private TableView<darhlaaHistory> tableview;
	
	@FXML
    private TableColumn<darhlaaHistory, String> h_ner;

    @FXML
    private TableColumn<darhlaaHistory, String> v_ner;

    @FXML
    private TableColumn<darhlaaHistory, String> v_tun;

    @FXML
    private TableColumn<darhlaaHistory, String> ognoo;

    @FXML
    private TableColumn<darhlaaHistory, String> emch;

    @FXML
    private TableColumn<darhlaaHistory, String> suvilagch;

    @FXML
    private TableColumn<darhlaaHistory, String> tailbar;

    ObservableList<darhlaaHistory> observableList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		h_ner.setCellValueFactory(new PropertyValueFactory<>("h_ner"));
		v_ner.setCellValueFactory(new PropertyValueFactory<>("v_ner"));
    	v_tun.setCellValueFactory(new PropertyValueFactory<>("v_tun"));
    	ognoo.setCellValueFactory(new PropertyValueFactory<>("ognoo"));
    	emch.setCellValueFactory(new PropertyValueFactory<>("emch"));
    	suvilagch.setCellValueFactory(new PropertyValueFactory<>("suvilagch"));
    	tailbar.setCellValueFactory(new PropertyValueFactory<>("tailbar"));
    	tableview.setItems(observableList);
    	
    	try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "");
			Statement myStmt = (Statement) myConn.createStatement();
			String sql = "select d.*, e.ovog as eovog, e.ner as ener, s.ovog as sovog, s.ner as sner, v.ner as vner, h.ovog as hovog, h.ner as hner from d_darhlaa d left join d_ajiltan e on d.e_id=e.id left join d_ajiltan s on d.s_id=s.id left join d_vaktsin v on d.v_id=v.id left join d_huuhed h on d.h_id=h.id";
			ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
			while(myRs.next()) {
				observableList.add(new darhlaaHistory(myRs.getString("hovog").charAt(0)+"."+myRs.getString("hner"), myRs.getString("vner"), myRs.getString("tun"), myRs.getString("ognoo"), myRs.getString("eovog").charAt(0)+"."+myRs.getString("ener"), myRs.getString("sovog").charAt(0)+"."+myRs.getString("sner"), myRs.getString("tailbar")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
