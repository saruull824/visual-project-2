package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class editController implements Initializable {
	private int id = 0;
	
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	
	@FXML
	private TextField ovog;

	@FXML
	private TextField ner;

	@FXML
	private TextField huis;

	@FXML
	private TextField reg_dugaar;

	@FXML
	private TextField horoo;

	@FXML
	private TextField hayag;

	@FXML
	private TextField ognoo;

	@FXML
	private TextField tsag;

	@FXML
	private ComboBox<String> kartnii_burtgel;

	@FXML
	private TextField etsgiin_ner;

	@FXML
	private TextField etsgiin_dugaar;

	@FXML
	private TextField ehiin_ner;

	@FXML
	private TextField ehiin_dugaar;

	@FXML
	private Button save;

	void fill() {
		try {
			if (id != 0) {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
				Statement myStmt = (Statement) myConn.createStatement();
				String sql = "select * from d_huuhed";
				ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				myRs.next();
				ovog.setText(myRs.getString("ovog"));
				ner.setText(myRs.getString("ner"));
				huis.setText(myRs.getString("huis"));
				reg_dugaar.setText(myRs.getString("reg_dugaar"));
				horoo.setText(myRs.getString("horoo"));
				hayag.setText(myRs.getString("hayag"));
				ognoo.setText(myRs.getString("ognoo"));
				tsag.setText(myRs.getString("tsag"));
				kartnii_burtgel.getSelectionModel().select(Integer.parseInt(myRs.getString("kartnii_burtgel")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void OnSaveAction(ActionEvent event) {
//		if (id == 0) {
//			try {
//				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "1234");
//				Statement myStmt = (Statement) conn.createStatement();
//				String sql = "INSERT INTO d_huuhed(ovog, ner, huis, reg_dugaar, horoo, hayag, ognoo, tsag, kartnii_burtgel) VALUES ('"
//						+ ovog.getText() + "', '" + ner.getText() + "','" + huis.getText() + "','"
//						+ reg_dugaar.getText() + "','" + horoo.getText() + "','" + hayag.getText() + "','"
//						+ ognoo.getText() + "','" + tsag.getText() + "','" + kartnii_burtgel.getSelectionModel().getSelectedIndex() + "')";
//				((java.sql.Statement) myStmt).execute(sql);
//				conn.close();
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
//		} else {
//			try {
//				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "1234");
//				Statement myStmt = (Statement) conn.createStatement();
//				String query = "UPDATE d_huuhed SET ovog = '1234' where ovog ='" +ovog.getText()+"'";
//				
//				
//				boolean myRs = ((java.sql.Statement) myStmt).execute(query);
//			
//				if(myRs){
//					
//			
//			}
//				conn.close();
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
//		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		if(id != -1){
//		ObservableList<String> observablelist = FXCollections.observableArrayList();
//		observablelist.addAll("Тийм", "Үгүй");
//		kartnii_burtgel.setItems(observablelist);
//		}
//		try {
//			System.out.println(id);
//			if (id != 0) {
//				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
//				Statement myStmt = (Statement) myConn.createStatement();
//				String sql = "select * from d_huuhed";
//				ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
//				myRs.next();
//				ovog.setText(myRs.getString("ovog"));
//				ner.setText(myRs.getString("ner"));
//				huis.setText(myRs.getString("huis"));
//				reg_dugaar.setText(myRs.getString("reg_dugaar"));
//				horoo.setText(myRs.getString("horoo"));
//				hayag.setText(myRs.getString("hayag"));
//				ognoo.setText(myRs.getString("ognoo"));
//				tsag.setText(myRs.getString("tsag"));
//				kartnii_burtgel.getSelectionModel().select(Integer.parseInt(myRs.getString("kartnii_burtgel")));
////				etsgiin_ner.setText(myRs.getString("etsgiin_ner"));
////				etsgiin_dugaar.setText(myRs.getString("etsgiin_dugaar"));
////				ehiin_ner.setText(myRs.getString("ehiin_ner"));
////				ehiin_dugaar.setText(myRs.getString("ehiin_dugaar"));
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
}
