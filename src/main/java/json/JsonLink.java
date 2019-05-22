package json;

import java.util.ArrayList;
import java.util.List;

import reseauSocial.dataFormat.LinkProperty;

public class JsonLink {
	private String linkName;
	private List<LinkProperty> properties;
	private String noeudDepart;
	private String noeudArrive;
	
	public JsonLink() {}
	
	public JsonLink(String linkName, List<LinkProperty> properties, String depart, String arrive) {
		this.properties = new ArrayList<>();
		this.linkName = linkName;
		this.properties = properties;
		this.noeudDepart = depart;
		this.noeudArrive = arrive;
	}
	
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public List<LinkProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<LinkProperty> properties) {
		this.properties = properties;
	}
	public String getNoeudDepart() {
		return noeudDepart;
	}
	public void setNoeudDepart(String noeudDepart) {
		this.noeudDepart = noeudDepart;
	}
	public String getNoeudArrive() {
		return noeudArrive;
	}
	public void setNoeudArrive(String noeudArrive) {
		this.noeudArrive = noeudArrive;
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("JsonLink [linkName=" + linkName + ", properties=[");
		
		for(LinkProperty link : properties)
			bld.append(link.toString() + ", ");
		
		bld.append("], noeudDepart=" + noeudDepart
				+ ", noeudArrive=" + noeudArrive + "]");
		return bld.toString();
	}
}
