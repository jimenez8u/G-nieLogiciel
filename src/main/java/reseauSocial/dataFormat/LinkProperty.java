package reseauSocial.dataFormat;

public class LinkProperty {
	private String propertyName;
	private String propertyValue;

	public LinkProperty() 
	{}

	public LinkProperty(String propertyName, String propertyValue) {

		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String toString() {
		return this.propertyName + ":" + this.propertyValue;
	}
}
