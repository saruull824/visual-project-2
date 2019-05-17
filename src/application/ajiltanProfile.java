package application;

public class ajiltanProfile {
	private String h_id;
	private String v_id;
	private String tun;
	private String ognoo;
	private String e_id;
	
	public ajiltanProfile(String h_id, String v_id, String tun, String ognoo, String e_id) {
		super();
		this.h_id = h_id;
		this.v_id = v_id;
		this.tun = tun;
		this.ognoo = ognoo;
		this.e_id = e_id;
	}

	public String getH_id() {
		return h_id;
	}

	public void setH_id(String h_id) {
		this.h_id = h_id;
	}

	public String getV_id() {
		return v_id;
	}

	public void setV_id(String v_id) {
		this.v_id = v_id;
	}

	public String getTun() {
		return tun;
	}

	public void setTun(String tun) {
		this.tun = tun;
	}
	
	public String getOgnoo() {
		return ognoo;
	}

	public void setOgnoo(String ognoo) {
		this.ognoo = ognoo;
	}
	
	public String getE_id() {
		return e_id;
	}

	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
}
