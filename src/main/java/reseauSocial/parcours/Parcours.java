package reseauSocial.parcours;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.SocialNode;

public class Parcours {
	
	//parcours largeur d'abord
	public List<SocialNode> parcoursLargeur(SocialNode noeud) {
		List<SocialNode> noeudsVisites = new ArrayList<>();
		Deque<SocialNode> noeudsSuivants = new LinkedList<>();
		
		for (Link lien : noeud.getLinkList()) {
			if (lien.getNoeudDepart().equals(noeud)) {
				noeudsSuivants.addLast(lien.getNoeudArrive());
			}
			else {
				noeudsSuivants.add(lien.getNoeudDepart());
			}
		}
		
		noeudsVisites.add(noeud);
		
		while (noeudsSuivants.peekFirst() != null) {
			noeud = noeudsSuivants.pollFirst();
			for (Link lien : noeud.getLinkList()) {
				if (lien.getNoeudDepart().equals(noeud)) {
					noeudsSuivants.addLast(lien.getNoeudArrive());
				}
				else {
					noeudsSuivants.add(lien.getNoeudDepart());
				}
			}
			if(!noeudsVisites.contains(noeud)) {
				noeudsVisites.add(noeud);
			}
				
		}
		
		return noeudsVisites;
	}
	
	//parcours profonndeur d'abord
	public List<SocialNode> parcoursProfondeur(SocialNode noeud) {
		
		List<SocialNode> noeudsVisites = new ArrayList<>();
		return parcoursProfondeurRec(noeud,noeudsVisites);
		
	}
	
	private List<SocialNode> parcoursProfondeurRec(SocialNode noeud, List<SocialNode> noeudsVisites) {
		//List<SocialNode> noeudsVisites = new ArrayList<>();
		Deque<SocialNode> noeudsSuivants = new LinkedList<>();
		
		if(!noeudsVisites.contains(noeud)) {
			noeudsVisites.add(noeud);
			
			for (Link lien : noeud.getLinkList()) {
				if (lien.getNoeudDepart().equals(noeud)) {
					noeudsSuivants.addLast(lien.getNoeudArrive());
				}
				else {
					noeudsSuivants.add(lien.getNoeudDepart());
				}
			}

			while(noeudsSuivants.peekFirst() != null) {
				
				noeudsVisites.addAll(parcoursProfondeurRec(noeudsSuivants.pollFirst(), noeudsVisites));
			}
			return noeudsVisites;
		}
		else {
			return noeudsVisites;
		}
	}
	
	
	
	
	
	
	
	
}