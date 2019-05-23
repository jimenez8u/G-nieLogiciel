package reseauSocial.parcours;

import java.util.List;

import reseauSocial.dataFormat.LinkProperty;

public class Condition {
	@Override
	public String toString() {
		return "Condition [name=" + name + ", properties=" + properties + "]";
	}

	private String name;
	private List<LinkProperty> properties;
	
	public Condition(String name, List<LinkProperty> properties) {
		this.name = name;
		this.properties = properties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LinkProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<LinkProperty> properties) {
		this.properties = properties;
	}
	
	
}
