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

public class huuhedEditController implements Initializable {
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
	private ComboBox<String> huis;

	@FXML
	private TextField reg_dugaar;

	@FXML
	private TextField duureg;
	
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
	private TextField etsgiin_utas;

	@FXML
	private TextField ehiin_ner;

	@FXML
	private TextField ehiin_utas;

	@FXML
	private Button save;
	
	void fill() {
		try {
			if (id != 0) {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "");
				Statement myStmt = (Statement) myConn.createStatement();
				String sql = "select * from d_huuhed h left join d_asran_hamgaalagch a on h.id=a.h_id where id='"+id+"'";
				ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
				myRs.next();
				ovog.setText(myRs.getString("ovog"));
				ner.setText(myRs.getString("ner"));
				if(myRs.getString("huis").equals("Эр"))
					huis.getSelectionModel().select(0);
				else
					huis.getSelectionModel().select(1);
				reg_dugaar.setText(myRs.getString("reg_dugaar"));
				duureg.setText(myRs.getString("duureg"));
				horoo.setText(myRs.getString("horoo"));
				hayag.setText(myRs.getString("hayag"));
				etsgiin_ner.setText(myRs.getString("etsgiin_ner"));
				etsgiin_utas.setText(myRs.getString("etsgiin_utasnii_dugaar"));
				ehiin_ner.setText(myRs.getString("ehiin_ner"));
				ehiin_utas.setText(myRs.getString("ehiin_utasnii_dugaar"));
				kartnii_burtgel.getSelectionModel().select(Integer.parseInt(myRs.getString("kartnii_burtgel")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void OnSaveAction(ActionEvent event) {
		int error = 0;
		if(ovog.getText().isEmpty() || 
			ner.getText().isEmpty() ||
			huis.getSelectionModel().isEmpty() ||
			reg_dugaar.getText().isEmpty() ||
			duureg.getText().isEmpty() ||
			horoo.getText().isEmpty() ||
			hayag.getText().isEmpty() ||
			kartnii_burtgel.getSelectionModel().isEmpty())
			error = 1;
		else {
			if (id == 0) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "");
					Statement myStmt = (Statement) conn.createStatement();
					String sql = "INSERT INTO d_huuhed(ovog, ner, huis, reg_dugaar, duureg, horoo, hayag, ognoo, tsag, kartnii_burtgel) VALUES ('"
							+ ovog.getText() + "', '" 
							+ ner.getText() + "','" 
							+ huis.getSelectionModel().getSelectedItem() + "','"
							+ reg_dugaar.getText() + "','" 
							+ duureg.getText() + "','" 
							+ horoo.getText() + "','" 
							+ hayag.getText() + "','"
							+ new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) + "','" 
							+ new SimpleDateFormat("HH:ss").format(new java.util.Date()) + "','" 
							+ kartnii_burtgel.getSelectionModel().getSelectedIndex() + "')";
					((java.sql.Statement) myStmt).execute(sql);
					
					sql = "SELECT id FROM d_huuhed WHERE reg_dugaar='"+ reg_dugaar.getText() + "'";
					ResultSet myRs = ((java.sql.Statement) myStmt).executeQuery(sql);
					myRs.next();
					String h_id = myRs.getString("id");
					sql = "INSERT INTO d_asran_hamgaalagch(h_id, etsgiin_ner, etsgiin_utas, ehiin_ner, ehiin_utas) VALUES('"+h_id+"','"+etsgiin_ner.getText()+"','"+etsgiin_utas.getText()+"', '"+ehiin_ner.getText()+"', '"+ehiin_utas.getText()+"')";
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "");
					Statement myStmt = (Statement) conn.createStatement();
					String query = "UPDATE d_huuhed SET ovog = '"
					+ovog.getText()+ "', ner = '"
					+ner.getText()+"', huis = '"
					+huis.getSelectionModel().getSelectedItem()+"', reg_dugaar='"
					+reg_dugaar.getText()+"', duureg='"
					+duureg.getText()+"', horoo='"
					+horoo.getText()+"', hayag='"
					+hayag.getText()+"', kartnii_burtgel='"
					+kartnii_burtgel.getSelectionModel().getSelectedIndex()+"' where id ='" +id+"'";
					((java.sql.Statement) myStmt).execute(query);
				
					query = "UPDATE d_asran_hamgaalagch SET etsgiin_ner = '"
							+etsgiin_ner.getText()+ "', etsgiin_utasnii_dugaar = '"
							+etsgiin_utas.getText()+"', ehiin_ner = '"
							+ehiin_ner.getText()+"', ehiin_utasnii_dugaar = '"
							+ehiin_utas.getText()+"' where h_id ='" +id+"'";
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
			Stage stage = (Stage) save.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> huisOptions = FXCollections.observableArrayList();
		huis.setItems(huisOptions);
		huisOptions.addAll("Эр", "Эм");
		huis.getSelectionModel().select(0);
		ObservableList<String> observablelist = FXCollections.observableArrayList();
		observablelist.addAll("Үгүй", "Тийм");
		kartnii_burtgel.setItems(observablelist);
		kartnii_burtgel.getSelectionModel().select(0);
	}
}
