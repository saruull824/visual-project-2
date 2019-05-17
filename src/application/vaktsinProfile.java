package application;

public class vaktsinProfile {
	private String h_id;
	private String e_id;
	private String v_id;
	private String s_id;
	
	public vaktsinProfile(String h_id, String e_id, String v_id, String s_id) {
		super();
		this.h_id = h_id;
		this.e_id = e_id;
		this.v_id = v_id;
		this.s_id = s_id;
	}

	public String getH_id() {
		return h_id;
	}

	public void setH_id(String h_id) {
		this.h_id = h_id;
	}

	public String getE_id() {
		return e_id;
	}

	public void setE_id(String e_id) {
		this.e_id = e_id;
	}

	public String getV_id() {
		return v_id;
	}

	public void setV_id(String v_id) {
		this.v_id = v_id;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	
}
