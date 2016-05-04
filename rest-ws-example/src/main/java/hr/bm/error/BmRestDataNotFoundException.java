package hr.bm.error;

public class BmRestDataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private long id;

	public BmRestDataNotFoundException(long id) {
		this.id = id;
	}

	public long getMyDataId() {
		return id;
	}
}
