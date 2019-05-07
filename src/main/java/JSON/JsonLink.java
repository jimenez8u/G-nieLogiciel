package JSON;

import java.util.ArrayList;

import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.dataFormat.SocialNode;

public class JsonLink {
	private String linkName;
	private ArrayList<LinkProperty> properties;
	private String noeudDepart;
	private String noeudArrive;
	
	public JsonLink() {}
	
	public JsonLink(String linkName, ArrayList<LinkProperty> properties, String noeudDepart, String noeudArrive) {
		this.properties = new ArrayList<LinkProperty>();
		this.linkName = linkName;
		this.properties = properties;
		this.noeudDepart = noeudDepart;
		this.noeudArrive = noeudArrive;
	}
	
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public ArrayList<LinkProperty> getProperties() {
		return properties;
	}
	public void setProperties(ArrayList<LinkProperty> properties) {
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
		String str = "JsonLink [linkName=" + linkName + ", properties=[";
		
		for(LinkProperty link : properties)
			str += link.toString() + ", ";
		
		str += "], noeudDepart=" + noeudDepart
				+ ", noeudArrive=" + noeudArrive + "]";
		return str;
	}
}
