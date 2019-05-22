package json;

import java.util.ArrayList;
import java.util.List;

public class JsonSocialNetwork {
	List<JsonLink> linkList;

	public JsonSocialNetwork() 
	{
		this.linkList = new ArrayList<>();
	}
	
	public JsonSocialNetwork(List<JsonLink> linkList) {
		super();
		this.linkList = linkList;
	}

	public List<JsonLink> getLinkList() {
		return linkList;
	}
	public void setLinkList(List<JsonLink> linkList) {
		this.linkList = linkList;
	}
	public void addLink(JsonLink link)
	{
		this.linkList.add(link);
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("JsonSocialNetwork [linkList=");
		
		for(JsonLink link : linkList)
			bld.append(link.toString() + ", ");
	
		bld.append( "]");
		
		return bld.toString();
	}
	
}
