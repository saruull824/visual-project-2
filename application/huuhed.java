package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class huuhed {
	private final StringProperty dugaar;
	private final StringProperty ovog;
	private final StringProperty ner;
	private final StringProperty huis;
	private final StringProperty regdugaar;
	private final StringProperty idx;
	
	public huuhed(String dugaar, String ovog, String ner, String huis, String regdugaar, String idx) {
		this.dugaar = new SimpleStringProperty(dugaar);
		this.ovog = new SimpleStringProperty(ovog);
		this.ner = new SimpleStringProperty(ner);
		this.huis= new SimpleStringProperty(huis);
		this.regdugaar= new SimpleStringProperty(regdugaar);
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
	
	public String getHuis() {
		return huis.get();
	}
	
	public String getRegdugaar() {
		return regdugaar.get();
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
	
	public void setHuis(String value) {
		huis.set(value);
	}
	
	public void setRegdugaar(String value) {
		regdugaar.set(value);
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
	
	public StringProperty huisProperty() {
		return huis;
	}
	
	public StringProperty regdugaarProperty() {
		return regdugaar;
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
