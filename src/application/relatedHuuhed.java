package application;

public class relatedHuuhed {
	public relatedHuuhed(String ner, String vaktsin) {
		this.ner = ner;
		this.vaktsin = vaktsin;
	}
	
	private final String ner;
	private final String vaktsin;
	
	public String getNer() {
		return ner;
	}
	public String getVaktsin() {
		return vaktsin;
	}

}
