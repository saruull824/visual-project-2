package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ajiltan {
	private final StringProperty dugaar;
	private final StringProperty ovog;
	private final StringProperty ner;
	private final StringProperty tushaal;
	private final StringProperty idx;
	
	public ajiltan(String dugaar, String ovog, String ner, String tushaal, String idx) {
		this.dugaar = new SimpleStringProperty(dugaar);
		this.ovog = new SimpleStringProperty(ovog);
		this.ner = new SimpleStringProperty(ner);
		this.tushaal= new SimpleStringProperty(tushaal);
		this.idx = new SimpleStringProperty(idx);
	}
	
	public String getDugaar() {
		return dugaar.get();
	}
	
	public String getOvog() {
		return ovog.get();
	}
	
	public String getNer() {
		return ner.get();
	}
	
	public String getTushaal() {
		return tushaal.get();
	}
	
	
	public void setDugaar(String value) {
		dugaar.set(value);
	}

	public void setOvog(String value) {
		ovog.set(value);
	}
	
	public void setNer(String value) {
		ner.set(value);
	}
	
	public void setTushaal(String value) {
		tushaal.set(value);
	}
	
	public StringProperty dugaarProperty() {
		return dugaar;
	}
	
	public StringProperty ovogProperty() {
		return ovog;
	}
	
	public StringProperty nerProperty() {
		return ner;
	}
	
	public StringProperty tushaalProperty() {
		return tushaal;
	}
	
	
	
	public String getIdx() {
		return idx.get();
	}
	
	public void setIdx(String value) {
		idx.set(value);
	}
	
	public StringProperty idxProperty() {
		return idx;
	}
}
