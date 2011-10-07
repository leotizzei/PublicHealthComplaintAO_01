package publichealthcomplaint.infrastructuremgt.impl;

import publichealthcomplaint.datatypes.IEmployeeDt;


class Employee implements IEmployeeDt{

	private String name;

	private String login;

	private String password;

	public Employee(String login, String password, String name) {
		this.name = name;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}
}