package reseauSocial.dataFormat;

import java.util.ArrayList;

public class Link {
	
	private String linkName;
	private ArrayList<LinkProperty> properties;
	private SocialNode noeudDepart;
	private SocialNode noeudArrive;
	
	public Link() {}
	
	public Link(String linkName, ArrayList<LinkProperty> properties, SocialNode noeudDepart, SocialNode noeudArrive) {
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
	public SocialNode getNoeudDepart() {
		return noeudDepart;
	}
	public void setNoeudDepart(SocialNode target) {
		this.noeudDepart = target;
	}
	public SocialNode getNoeudArrive() {
		return noeudArrive;
	}
	public void setNoeudArrive(SocialNode noeudArrive) {
		this.noeudArrive = noeudArrive;
	}
	
	@Override
	public String toString() {
		String str =  "Link [linkName=" + linkName + ", properties=";
		
		for(LinkProperty link : properties)
			str += link.toString() + ", ";
		
		str += ", noeudDepart=" + noeudDepart.getName()
				+ ", noeudArrive=" + noeudArrive.getName() + "]";
		return str;
	}
}
