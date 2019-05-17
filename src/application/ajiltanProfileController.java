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

public class ajiltanProfileController implements Initializable {
	private int id = 0;
	
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	
	@FXML
    private TableView<ajiltanProfile> tableview;

    @FXML
    private TableColumn<ajiltanProfile, String> h_id;

    @FXML
    private TableColumn<ajiltanProfile, String> v_id;

    @FXML
    private TableColumn<ajiltanProfile, String> tun;

    @FXML
    private TableColumn<ajiltanProfile, String> ognoo;

    @FXML
    private TableColumn<ajiltanProfile, String> e_id;
    

    @FXML
    private Label ovog;

    @FXML
    private Label ner;

    @FXML
    private Label tushaal;

  
    ObservableList<ajiltanProfile> observableList = FXCollections.observableArrayList();
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	h_id.setCellValueFactory(new PropertyValueFactory<>("h_id"));
    	v_id.setCellValueFactory(new PropertyValueFactory<>("v_id"));
    	tun.setCellValueFactory(new PropertyValueFactory<>("tun"));
    	ognoo.setCellValueFactory(new PropertyValueFactory<>("ognoo"));
    	e_id.setCellValueFactory(new PropertyValueFactory<>("e_id"));
    	tableview.setItems(observableList);
	}
	
	void fill() {
		try {
			if (id != 0) {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
				Statement myStmt = (Statement) myConn.createStatement();
				String sql = "select * from d_ajiltan h left join d_asran_hamgaalagch a on h.id=a.h_id where id='"+id+"'";
				ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				myRs.next();
				ovog.setText(myRs.getString("ovog"));
				ner.setText(myRs.getString("ner"));
				tushaal.setText(myRs.getString("tushaal"));
				
				sql = "select d.*, e.ovog as eovog, e.ner as ener, s.ovog as sovog, s.ner as sner, v.ner as vner from d_darhlaa d left join d_ajiltan e on d.e_id=e.id left join d_ajiltan s on d.s_id=s.id left join d_vaktsin v on d.v_id=v.id where h_id='"+id+"'";
				myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				while(myRs.next()) {
					observableList.add(new ajiltanProfile(myRs.getString("h_id"), myRs.getString("v_id"), myRs.getString("tun"), myRs.getString("ognoo"),myRs.getString("e_id")));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
