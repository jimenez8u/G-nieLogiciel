package reseauSocial.parcours;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.SocialNode;

public class Parcours {
	
	//parcours largeur d'abord
	public static List<SocialNode> parcoursLargeur(SocialNode noeud, int profondeurRecherche) {
		List<SocialNode> noeudsVisites = new ArrayList<>();
		Deque<SocialNode> noeudsSuivants = new LinkedList<>();
		int nbNoeudsEtage;
		
		noeudsVisites.add(noeud);
		for (Link lien : noeud.getLinkList()) {
			if (lien.getNoeudDepart().equals(noeud)) {
				noeudsSuivants.addLast(lien.getNoeudArrive());
			}
			else {
				noeudsSuivants.add(lien.getNoeudDepart());
			}
		}
		
		nbNoeudsEtage = noeudsSuivants.size();
		SocialNode noeudSuivant;
		while (noeudsSuivants.peekFirst() != null && profondeurRecherche > 0) {
			noeudSuivant = noeudsSuivants.pollFirst();
			
			nbNoeudsEtage --;
			if (nbNoeudsEtage == 0) {
				profondeurRecherche --;
				nbNoeudsEtage = noeudsSuivants.size();
			}
			
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
	public static List<SocialNode> parcoursProfondeur(SocialNode noeud, int profondeurRecherche) {
		
		List<SocialNode> noeudsVisites = new ArrayList<>();
		return parcoursProfondeurRec(noeud,noeudsVisites,profondeurRecherche);
		
	}
	
	private static List<SocialNode> parcoursProfondeurRec(SocialNode noeud, List<SocialNode> noeudsVisites, int profondeurRecherche) {
		List<SocialNode> noeudsResult = new ArrayList<>();
		Deque<SocialNode> noeudsSuivants = new LinkedList<>();
		
		if(!noeudsVisites.contains(noeud) && profondeurRecherche >=0) {
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
				
				noeudsResult.addAll(parcoursProfondeurRec(noeudsSuivants.pollFirst(), noeudsVisites,profondeurRecherche -1));
			}
			return noeudsResult;
		}
		else {
			return noeudsResult;
		}
	}
	
	
	
	
	
	
	
	
}