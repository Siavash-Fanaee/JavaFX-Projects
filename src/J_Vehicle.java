
public abstract class J_Vehicle {
	
	private String make;
	private String model;
	private String coler;
	private String regNum;
		
	//Constructor:
	protected J_Vehicle (String regN) {
		this.regNum = regN;
	}

	
	public String getRegNum() {
		return regNum;
	}


	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}


	//Getters and Setters:
	protected String getMake() {
		return make;
	}

	protected void setMake(String make) {
		this.make = make;
	}

	protected String getModel() {
		return model;
	}

	protected void setModel(String model) {
		this.model = model;
	}

	protected String getColer() {
		return coler;
	}

	protected void setColer(String coler) {
		this.coler = coler;
	}
	
}
