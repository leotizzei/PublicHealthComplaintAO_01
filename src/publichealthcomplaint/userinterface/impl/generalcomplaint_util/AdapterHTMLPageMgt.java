package publichealthcomplaint.userinterface.impl.generalcomplaint_util;

import java.io.FileNotFoundException;

import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IHTMLPageMgt;

class AdapterHTMLPageMgt implements IHTMLPageMgt {

	
	public String close() {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.close();
	}


	public String closeAdministrator() {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.closeAdministrator();
	}



	public String errorPage(String message) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.errorPage(message);
	}


	public String errorPageAdministrator(String message) {

		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.errorPageAdministrator(message);
	}


	public String errorPageQueries(String message) {

		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.errorPageQueries(message);
	}


	public String getFileListReplace(String[] keywords, String[] newWords,
			String file) throws FileNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil util = (publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil) mgr.getRequiredInterface("IUtil");
		return util.getFileListReplace(keywords, newWords, file);
	}


	
	public String htmlPage(String title, String text) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.htmlPage(title, text);
	}


	public String htmlPageAdministrator(String title, String text) {

		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.htmlPageAdministrator(title, text);
	}


	public String open(String title) {

		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		return htmlPage.open(title);
	}

}
