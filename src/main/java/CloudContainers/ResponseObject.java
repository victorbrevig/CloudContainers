package CloudContainers;

public class ResponseObject {
	private String errorMessage;
	private statusTrackingObject status;

	public ResponseObject(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	public ResponseObject() {
		
	}
	
	public statusTrackingObject getStatus() {
		return status;
	}
	public void setStatus(statusTrackingObject status) {
		this.status = status;
	}
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	

}
