package publichealthcomplaint.userinterface.impl.util.impl;

import publichealthcomplaint.datatypes.Constants;

/**
 *
 * Classe HTMLCode - possui metodos para facilitar a 
 * impressao dos codigos html de saida dos servlets
 *
 */
class HTMLCode {

	private static final int REGULAR_PAGE = 1;

	private static final int QUERIES_PAGE = 2;

	private static final int ADMINISTRATOR_PAGE = 3;

	static String close() {
		return "<p><center>" + HTMLCode.foot();
	}

	static String closeAdministrator() {
		return "<p><center>" + HTMLCode.linkAdministrator() + " - " + HTMLCode.foot();
	}

	static String closeQueries() {
		return "<p><center>" + HTMLCode.linkQueries() + " - " + HTMLCode.foot();
	}

	static String errorPage(String message) {
		return HTMLCode.htmlPage("Error message", message);
	}

	static String errorPageAdministrator(String message) {
		return HTMLCode.htmlPage("Administrator - Error message", message, ADMINISTRATOR_PAGE);
	}

	static String errorPageQueries(String message) {
		return HTMLCode.htmlPage("Queries - Error message", message, QUERIES_PAGE);
	}

	private static String foot() {
		return "<a href=\"" + Constants.SYSTEM_INDEX + "\">" + "Main menu</a>"
		+ "<p><small>Health-Watcher - 2006</small></center></body></html>";
	}

	static String htmlPage(String title, String text) {
		return htmlPage(title, text, REGULAR_PAGE);
	}

	static String htmlPage(String title, String text, int pageType) {

		StringBuffer pagina = new StringBuffer();

		pagina.append(HTMLCode.open(title, "white"));
		pagina.append("<center>");
		pagina.append("<font face=\"Arial\" color=\"black\" size=+1>" + title + "</font></td>");
		pagina.append("</center>");

		pagina.append("<font face=\"Arial\" color=\"black\"><small>");
		pagina.append("<p align=\"center\">");
		pagina.append(text);
		pagina.append("</small></font>");
		switch (pageType) {
		case QUERIES_PAGE:
			pagina.append(HTMLCode.closeQueries());
			break;
		case ADMINISTRATOR_PAGE:
			pagina.append(HTMLCode.closeAdministrator());
			break;
		default:
			pagina.append(HTMLCode.close());
		}
		return pagina.toString();
	}

	static String htmlPageAdministrator(String title, String text) {
		return htmlPage(title, text, ADMINISTRATOR_PAGE);
	}

	static String htmlPageQueries(String title, String text) {
		return htmlPage(title, text, QUERIES_PAGE);
	}

	static String link(String url, String description) {
		return "<a href=\"" + url + "\">" + description + "</a>";
	}

	private static String linkAdministrator() {
		return link(Constants.SYSTEM_INDEX_ADMINISTRATOR, "Employee's menu");
	}

	private static String linkQueries() {
		return link(Constants.SYSTEM_QUERIES, "Queries' menu");
	}

	static String open() {
		return HTMLCode.open("");
	}

	static String open(String title) {
		return HTMLCode.open("Health-Watcher - 2006 - " + title, "white");
	}

	static String open(String title, String bgColor) {
		return HTMLCode.open1() + title + HTMLCode.open2() + "<body bgcolor=\"" + bgColor + "\">";
	}

	private static String open1() {
		return "<HTML><HEAD><TITLE>";
	}

	private static String open2() {
		return "</TITLE></HEAD>";
	}

}