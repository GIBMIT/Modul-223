package ch.gibm.bean;

import org.primefaces.PrimeFaces;

import ch.gibm.util.JSFMessageUtil;

public class AbstractBean {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractBean() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected void closeDialog(){
		PrimeFaces.current().ajax().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen(){
		PrimeFaces.current().ajax().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}
	
}