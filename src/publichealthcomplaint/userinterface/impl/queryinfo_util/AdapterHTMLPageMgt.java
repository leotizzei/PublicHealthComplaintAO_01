package publichealthcomplaint.userinterface.impl.queryinfo_util;

import publichealthcomplaint.userinterface.impl.infrastr_util.ComponentFactory;
import publichealthcomplaint.userinterface.impl.infrastr_util.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;

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


	public String closeQueries() {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.closeQueries();
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


	public String htmlPage(String title, String text) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.htmlPage(title, text);
	}


	public String htmlPage(String title, String text, int pageType) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.htmlPage(title, text, pageType);
	}


	public String htmlPageAdministrator(String title, String text) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.htmlPageAdministrator(title, text);
	}


	public String htmlPageQueries(String title, String text) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.htmlPageQueries(title, text);
	}


	public String link(String url, String description) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.link(url, description);
	}


	public String open() {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.open();
	}


	public String open(String title) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return htmlPage.open(title);
	}


	public String open(String title, String bgColor) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt htmlPage = (publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		return null;
	}

}
