package reseauSocial.dataFormat;

import java.util.ArrayList;
import java.util.List;

public class Link {
	
	private String linkName;
	private List<LinkProperty> properties;
	private SocialNode noeudDepart;
	private SocialNode noeudArrive;
	
	public Link() {}
	
	public Link(String linkName, List<LinkProperty> properties, SocialNode noeudDepart, SocialNode noeudArrive) {
		this.properties = new ArrayList<>();
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
	public List<LinkProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<LinkProperty> properties) {
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
		StringBuilder bld = new StringBuilder();
		bld.append("Link [linkName=" + linkName + ", properties=");
		
		for(LinkProperty link : properties)
			bld.append( link.toString() + ", ");
		
		bld.append( ", noeudDepart=" + noeudDepart.getName()
				+ ", noeudArrive=" + noeudArrive.getName() + "]");
		return bld.toString();
	}
}
