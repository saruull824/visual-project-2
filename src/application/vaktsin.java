package application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class vaktsin {
	private final StringProperty dugaar;
	private final StringProperty ner;
	private final IntegerProperty tun;
	private final IntegerProperty nas;
	private final IntegerProperty duration;
	
	public vaktsin(String dugaar, String ner, Integer tun, Integer nas, Integer duration) {
		this.dugaar = new SimpleStringProperty(dugaar);
		this.ner = new SimpleStringProperty(ner);
		this.tun= new SimpleIntegerProperty(tun);
		this.nas= new SimpleIntegerProperty(nas);
		this.duration= new SimpleIntegerProperty(duration);
	}
	
	public String getDugaar() {
		return dugaar.get();
	}
	
	
	public String getNer() {
		return ner.get();
	}
	
	public Integer getTun() {
		return tun.get();
	}

	public Integer getNas() {
		return nas.get();
	}
	
	public Integer getDuration() {
		return duration.get();
	}
	
	public void setDugaar(String value) {
		dugaar.set(value);
	}

	
	public void setNer(String value) {
		ner.set(value);
	}

	public void setTun(Integer value) {
		tun.set(value);
	}
	
	public void setNas(Integer value) {
		nas.set(value);
	}
	
	public void setDuration(Integer value) {
		duration.set(value);
	}
	
	
	public StringProperty dugaarProperty() {
		return dugaar;
	}

	
	public StringProperty nerProperty() {
		return ner;
	}
	
	public IntegerProperty tunProperty() {
		return tun;
	}
	
	public IntegerProperty nasProperty() {
		return nas;
	}
	
	public IntegerProperty durationProperty() {
		return duration;
	}
	
}
