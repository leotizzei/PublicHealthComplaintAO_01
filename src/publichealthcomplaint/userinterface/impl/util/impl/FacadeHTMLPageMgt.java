package publichealthcomplaint.userinterface.impl.util.impl;

import publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt;

class FacadeHTMLPageMgt implements IHTMLPageMgt {

		
		
	public String close() {
		return HTMLCode.close();
	}

	
	public String closeAdministrator() {
		
		return HTMLCode.closeAdministrator();
	}

	
	public String closeQueries() {
	
		return HTMLCode.closeQueries();
	}

	
	public String errorPage(String message) {
	
		return HTMLCode.errorPage(message);
	}

	
	public String errorPageAdministrator(String message) {
	
		return HTMLCode.errorPageAdministrator(message);
	}

	
	public String errorPageQueries(String message) {
	
		return HTMLCode.errorPageQueries(message);
	}

	
	public String htmlPage(String title, String text, int pageType) {
	
		return HTMLCode.htmlPage(title, text);
	}

	
	public String htmlPageAdministrator(String title, String text) {
	
		return HTMLCode.htmlPageAdministrator(title, text);
	}

	
	public String htmlPageQueries(String title, String text) {
		
		return HTMLCode.htmlPageQueries(title, text);
	}


	public String link(String url, String description) {

		return HTMLCode.link(url, description);
	}

	
	public String open() {
	
		return HTMLCode.open();
	}

	
	public String open(String title) {
	
		return HTMLCode.open();
	}

	
	public String open(String title, String bgColor) {
	
		return HTMLCode.open(title, bgColor);
	}


	
	public String htmlPage(String title, String text) {
		
		return HTMLCode.htmlPage(title, text);
	}

}
