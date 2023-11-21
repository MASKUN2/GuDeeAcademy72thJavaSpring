package validation;

public class ValidationReport {
	
	private Boolean isPass;
	private String validationLog;
	
	public ValidationReport(Boolean isPass, String validationLog) {
		super();
		this.isPass = isPass;
		this.validationLog = validationLog;
	}

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}

	public String getValidationLog() {
		return validationLog;
	}

	public void setValidationLog(String validationLog) {
		this.validationLog = validationLog;
	}
	
	
	
	
}
