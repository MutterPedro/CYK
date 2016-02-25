package br.com.cyk;

import java.util.List;

public class Production {
	private String variable;
	private List<String> settings;
	
	public Production(String variable, List<String> settings) {
		super();
		this.variable = variable;
		this.settings = settings;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public List<String> getSettings() {
		return settings;
	}

	public void setSettings(List<String> settings) {
		this.settings = settings;
	}
}
