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

public class huuhedProfileController implements Initializable {
	private int id = 0;
	
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	
	@FXML
    private TableView<huuhedProfile> tableview;

    @FXML
    private TableColumn<huuhedProfile, String> v_ner;

    @FXML
    private TableColumn<huuhedProfile, String> v_tun;

    @FXML
    private TableColumn<huuhedProfile, String> ognoo;

    @FXML
    private TableColumn<huuhedProfile, String> emch;

    @FXML
    private TableColumn<huuhedProfile, String> suvilagch;
    
    @FXML
    private TableColumn<huuhedProfile, String> tailbar;

    @FXML
    private Label ovog;

    @FXML
    private Label ner;

    @FXML
    private Label huis;

    @FXML
    private Label reg_dugaar;

    @FXML
    private Label duureg;

    @FXML
    private Label horoo;

    @FXML
    private Label hayag;

    @FXML
    private Label kartnii_burtgel;

    @FXML
    private Label etsgiin_ner;

    @FXML
    private Label etsgiin_utas;

    @FXML
    private Label ehiin_ner;

    @FXML
    private Label ehiin_utas;
	
    ObservableList<huuhedProfile> observableList = FXCollections.observableArrayList();
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	v_ner.setCellValueFactory(new PropertyValueFactory<>("v_ner"));
    	v_tun.setCellValueFactory(new PropertyValueFactory<>("v_tun"));
    	ognoo.setCellValueFactory(new PropertyValueFactory<>("ognoo"));
    	emch.setCellValueFactory(new PropertyValueFactory<>("emch"));
    	suvilagch.setCellValueFactory(new PropertyValueFactory<>("suvilagch"));
    	tailbar.setCellValueFactory(new PropertyValueFactory<>("tailbar"));
    	tableview.setItems(observableList);
	}
	
	void fill() {
		try {
			if (id != 0) {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
				Statement myStmt = (Statement) myConn.createStatement();
				String sql = "select * from d_huuhed h left join d_asran_hamgaalagch a on h.id=a.h_id where id='"+id+"'";
				ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				myRs.next();
				ovog.setText(myRs.getString("ovog"));
				ner.setText(myRs.getString("ner"));
				huis.setText(myRs.getString("huis"));
				reg_dugaar.setText(myRs.getString("reg_dugaar"));
				duureg.setText(myRs.getString("duureg"));
				horoo.setText(myRs.getString("horoo"));
				hayag.setText(myRs.getString("hayag"));
				etsgiin_ner.setText(myRs.getString("etsgiin_ner"));
				etsgiin_utas.setText(myRs.getString("etsgiin_utasnii_dugaar"));
				ehiin_ner.setText(myRs.getString("ehiin_ner"));
				ehiin_utas.setText(myRs.getString("ehiin_utasnii_dugaar"));
				if(Integer.valueOf(myRs.getString("kartnii_burtgel")) == 0)
					kartnii_burtgel.setText("Үгүй");
				else
					kartnii_burtgel.setText("Тийм");
				
				sql = "select d.*, e.ovog as eovog, e.ner as ener, s.ovog as sovog, s.ner as sner, v.ner as vner from d_darhlaa d left join d_ajiltan e on d.e_id=e.id left join d_ajiltan s on d.s_id=s.id left join d_vaktsin v on d.v_id=v.id where h_id='"+id+"'";
				myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				while(myRs.next()) {
					observableList.add(new huuhedProfile(myRs.getString("vner"), myRs.getString("tun"), myRs.getString("ognoo"), myRs.getString("eovog").charAt(0)+"."+myRs.getString("ener"), myRs.getString("sovog").charAt(0)+"."+myRs.getString("sner"), myRs.getString("tailbar")));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
