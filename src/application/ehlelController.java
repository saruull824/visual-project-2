package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ehlelController implements Initializable{
	@FXML
    private TextField huuhedRD;
	
	@FXML
    private TextField huuhedNer;
	
	@FXML
    private ComboBox<String> emch;
	
	@FXML
    private ComboBox<String> suvilagch;

    @FXML
    private ComboBox<String> vaktsin;

    @FXML
    private ComboBox<Integer> tun;
    
    @FXML
    private TextArea tailbar;
    
    @FXML
    private TableView<relatedHuuhed> tableview;

    @FXML
    private TableColumn<relatedHuuhed, String> tableNer;

    @FXML
    private TableColumn<relatedHuuhed, String> tableVaktsin;
    
    @FXML
    private Button btnNemeh;

    @FXML
    private Button btnTuuh;
	
	ObservableList<String> emchOptions = FXCollections.observableArrayList();
	ObservableList<String> suvilagchOptions = FXCollections.observableArrayList();
	ObservableList<String> vaktsinOptions = FXCollections.observableArrayList();
	ObservableList<Integer> tunOptions = FXCollections.observableArrayList();
	int vakId[] = new int[100];
	int vakTun[] = new int[100];
	int huuhedId;
	int emchId[] = new int[100];
	int suvilagchId[] = new int[100];
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableNer.setCellValueFactory(new PropertyValueFactory<>("ner"));
		tableVaktsin.setCellValueFactory(new PropertyValueFactory<>("vaktsin"));
		//check();
		emch.setItems(emchOptions);
		suvilagch.setItems(suvilagchOptions);
		vaktsin.setItems(vaktsinOptions);
		tun.setItems(tunOptions);
		try {
		 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
		    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner from d_ajiltan where tushaal='эмч'");
		    int i = 0;
		    while(rs.next()){
		    	emchOptions.add(rs.getString("ovog").charAt(0) + "." + rs.getString("ner"));
		    	emchId[i] = Integer.valueOf(rs.getString("id"));
		    	i++;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		try {
		 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
		    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner from d_ajiltan where tushaal='сувилагч'");
		    int i = 0;
		    while(rs.next()){
		    	suvilagchOptions.add(rs.getString("ovog").charAt(0) + "." + rs.getString("ner"));
		    	suvilagchId[i] = Integer.valueOf(rs.getString("id"));
		    	i++;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		try {
		 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
		    ResultSet rs = conn.createStatement().executeQuery("SELECT id, ner, tun from d_vaktsin");
		    int i = 0;
		    while(rs.next()){
		    	vakId[i] = Integer.valueOf(rs.getString("id"));
		    	vakTun[i] = Integer.valueOf(rs.getString("tun"));
		    	vaktsinOptions.add(rs.getString("ner"));
		    	i++;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	@FXML
    void vaktsinAction(ActionEvent event) {
		int max = vakTun[vaktsin.getSelectionModel().getSelectedIndex()];
		tunOptions.clear();
		for(int i = 1; i <= max; i++)
			tunOptions.add(i);
    }
	
	@FXML
    void nemeh(ActionEvent event) {
		int emchSel = emch.getSelectionModel().getSelectedIndex();
		int vakSel = vaktsin.getSelectionModel().getSelectedIndex();
		int suvilagchSel = suvilagch.getSelectionModel().getSelectedIndex();
		int tunVal = tun.getSelectionModel().getSelectedIndex();
		tunVal++;
		if(emchSel != -1 && vakSel != -1 && suvilagchSel != -1 && huuhedId != 0 && tunVal != 0) {
	    	String sql = "INSERT INTO d_darhlaa VALUES('"+huuhedId+
	    			"', '"+emchId[emchSel]+
	    			"', '"+vakId[vakSel]+
	    			"', '"+suvilagchId[suvilagchSel]+
	    			"', '"+new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date())+
	    			"', '"+tunVal+
	    			"', '"+tailbar.getText()+
	    			"')";
	    	System.out.println(sql);
	    	try {
	    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");
	            Statement stmt = conn.createStatement();
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Алдаа гарлаа");
			alert.setHeaderText(null);
			alert.setContentText("Хүүхэд, эмч, сувилагч, вакцин болон тунгаа шалгаад дахин оролдоно уу.");
			ButtonType buttonTypeOk = new ButtonType("Тийм", ButtonData.OK_DONE);
			ButtonType buttonTypeCancel = new ButtonType("Үгүй", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
			alert.showAndWait();
		}
    }

    @FXML
    void haih(ActionEvent event) {
    	if(huuhedRD.getText().isEmpty())
    		return;
    	try {
		 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
	        ResultSet rs = conn.createStatement().executeQuery("SELECT id, ovog, ner from d_huuhed where reg_dugaar='"+huuhedRD.getText()+"'");
	        if(rs.next()) {
	        	huuhedNer.setText(rs.getString("ovog").charAt(0) + "." + rs.getString("ner"));
	        	huuhedId = Integer.valueOf(rs.getString("id"));
	        } else {
	        	if(!huuhedNer.getText().isEmpty()) {
	        		huuhedNer.setText("");
	        		huuhedId = 0;
	        	}
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
    void check() {
//    	Vector<String> huuhduud = new Vector<String>();
//    	Vector<Float> huuhduud_nas = new Vector<Float>();
//    	Vector<String> vaktsin = new Vector<String>();
//    	Vector<Float> vaktsin_nas = new Vector<Float>();
//    	Vector<Float> vaktsin_duration = new Vector<Float>();
//    	Integer today = Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new java.util.Date()));
//
//    	try {
//		 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
//		    ResultSet rs = conn.createStatement().executeQuery("SELECT ovog, ner, reg_dugaar from d_huuhed where horoo = 6");
//		    while(rs.next()){
//		    	huuhduud.add(rs.getString("ovog").charAt(0) + "." + rs.getString("ner"));
//		    	Integer huuhedBirth = Integer.valueOf(rs.getString("reg_dugaar").substring(2, 8));
//		    	if (huuhedBirth > 500000)
//		    		huuhedBirth += 19000000;
//		    	else {
//		    		huuhedBirth += 20000000;
//		    		huuhedBirth -= 2000;
//		    	}
//		    	if(today%100 < huuhedBirth%100) {
//		    		 huuhedBirth += 100;
//		    	}
//		    	today /= 100;
//		    	huuhedBirth /= 100;
//		    	int diff = today-huuhedBirth;
//		    	if(diff%100 > 12)
//		    		diff -= 88;
//		    	huuhduud_nas.add((float)diff/100);
//		    }
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
//    	
//    	try {
//		 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital2", "root", "saruul0201");			
//		    ResultSet rs = conn.createStatement().executeQuery("SELECT ner, tungiin_nas, tungiin_duration from d_vaktsin");
//		    while(rs.next()){
//		    	vaktsin.add(rs.getString("ner"));
//		    	vaktsin_nas.add(Float.valueOf(rs.getString("tungiin_nas")));
//		    	vaktsin_duration.add(Float.valueOf(rs.getString("tungiin_duration")));
//		    }
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
//    	
//    	for(int i = 0; i < huuhduud.size(); i++)
//    		for(int j = 0; j < vaktsin.size(); j++) {
//    			if(huuhduud_nas.elementAt(i) >= vaktsin_nas.elementAt(j) && huuhduud_nas.elementAt(i) <= vaktsin_nas.elementAt(j)+vaktsin_duration.elementAt(j))
//    				tableview.getItems().add(new relatedHuuhed(huuhduud.elementAt(i), vaktsin.elementAt(j)));
//    		}
    }
    
    @FXML
    void tuuh(ActionEvent event) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("darhlaaHistory.fxml"));
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
}
