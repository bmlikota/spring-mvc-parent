package hr.bm.dto;

public class BmRestData {

	private String message;

	private double id;

	public BmRestData() {
	}

	public BmRestData(String message, double id) {
		this.message = message;
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String toString() {
		return "id = " + id + ", message = " + message + ";;";
	}
}
