package hr.bm.error;

public class MyDataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private long id;

	public MyDataNotFoundException(long id) {
		this.id = id;
	}

	public long getMyDataId() {
		return id;
	}
}
