package CloudContainers;

public class ResponseObject {
	private String errorMessage;

	public ResponseObject(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	public ResponseObject() {
		
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	

}
