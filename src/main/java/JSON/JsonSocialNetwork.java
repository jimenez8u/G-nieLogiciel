package JSON;

import java.util.ArrayList;

public class JsonSocialNetwork {
	ArrayList<JsonLink> linkList;

	public JsonSocialNetwork() {}
	
	public JsonSocialNetwork(ArrayList<JsonLink> linkList) {
		super();
		this.linkList = linkList;
	}

	public ArrayList<JsonLink> getLinkList() {
		return linkList;
	}
	public void setLinkList(ArrayList<JsonLink> linkList) {
		this.linkList = linkList;
	}
	public void addLink(JsonLink link)
	{
		this.linkList.add(link);
	}
	
}
