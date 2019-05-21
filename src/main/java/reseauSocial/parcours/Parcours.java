package reseauSocial.parcours;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.SocialNode;

public class Parcours {
	
	//parcours largeur d'abord
	public static List<SocialNode> parcoursLargeur(SocialNode noeud) {
		List<SocialNode> noeudsVisites = new ArrayList<>();
		Deque<SocialNode> noeudsSuivants = new LinkedList<>();
		
		noeudsVisites.add(noeud);
		for (Link lien : noeud.getLinkList()) {
			if (lien.getNoeudDepart().equals(noeud)) {
				noeudsSuivants.addLast(lien.getNoeudArrive());
			}
			else {
				noeudsSuivants.add(lien.getNoeudDepart());
			}
		}
		
		
		SocialNode noeudSuivant;
		
		while (noeudsSuivants.peekFirst() != null) {
			noeudSuivant = noeudsSuivants.pollFirst();
			
			for (Link lien : noeudSuivant.getLinkList()) {
				SocialNode noeudAAjouter;
				if(lien.getNoeudDepart().equals(noeudSuivant)) {
					noeudAAjouter = lien.getNoeudArrive();
				}
				else {
					noeudAAjouter = lien.getNoeudDepart();
				}
				
				if(!noeudsVisites.contains(noeudAAjouter)) {
					noeudsSuivants.addLast(noeudAAjouter);
				}
			}
			
			if(!noeudsVisites.contains(noeudSuivant)) {
				noeudsVisites.add(noeudSuivant);
			}
				
		}
		
		return noeudsVisites;
	}
	
	//parcours profondeur d'abord
	public static List<SocialNode> parcoursProfondeur(SocialNode noeud) {
		
		List<SocialNode> noeudsVisites = new ArrayList<>();
		return parcoursProfondeurRec(noeud,noeudsVisites);
		
	}
	
	private static List<SocialNode> parcoursProfondeurRec(SocialNode noeud, List<SocialNode> noeudsVisites) {
		List<SocialNode> noeudsResult = new ArrayList<>();
		Deque<SocialNode> noeudsSuivants = new LinkedList<>();
		
		if(!noeudsVisites.contains(noeud)) {
			noeudsVisites.add(noeud);
			noeudsResult.add(noeud);
			
			for (Link lien : noeud.getLinkList()) {
				if (lien.getNoeudDepart().equals(noeud)) {
					if(!noeudsVisites.contains(lien.getNoeudArrive())) {
						noeudsSuivants.addLast(lien.getNoeudArrive());
					}
				}
				else {
					if(!noeudsVisites.contains(lien.getNoeudDepart())) {
						noeudsSuivants.addLast(lien.getNoeudDepart());
					}
				}
			}

			while(noeudsSuivants.peekFirst() != null) {
				
				noeudsResult.addAll(parcoursProfondeurRec(noeudsSuivants.pollFirst(), noeudsVisites));
			}
			return noeudsResult;
		}
		else {
			return noeudsResult;
		}
	}
	
	
	
	
	
	
	
	
}