package reseausocial.interf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import json.JsonConverter;
import reseausocial.dataformat.Link;
import reseausocial.dataformat.SocialNetwork;
import reseausocial.dataformat.SocialNode;
import reseausocial.parcours.Condition;
import reseausocial.parcours.Parcours;

public class Interface implements ActionListener {

	private static Random r = new Random();
	private JPanel panelGraph;
	private JPanel panelRecherche;
	private JPanel panel;
	private JFrame frame;
	private SocialNetwork sn;
	private static Logger logger = Logger.getLogger(Interface.class.getName());
	private List<Condition> cdt = null;
	private JOptionPaneFiltre filtrage;



	/**
	 * @param args
	 */
	public Interface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			logger.severe("Style d'interface non reconnue");
		}
		this.sn = new SocialNetwork();
		this.panelGraph = new JPanel();
		this.panelRecherche = createToolbar();
		this.panelRecherche.setVisible(false);
		this.panelRecherche.setSize(400,400);
		this.frame = new JFrame();
		this.frame.setVisible(true);
		this.frame.setSize(1200, 600);
		this.frame.setLocationRelativeTo(null);
		this.panel = new JPanel();
		JButton bouton = new JButton("Insert file");
		bouton.setActionCommand("readFile");
		bouton.addActionListener(this);
			
		this.panel.add(bouton);
		
		this.frame.getContentPane().add(BorderLayout.SOUTH, panel);
		this.frame.getContentPane().add(BorderLayout.NORTH, panelGraph);
		this.frame.add(panelRecherche);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//############### Fonctions de création d'interface ###############


	public static Map<String, Object> addNodeToGraph(List<SocialNode> reseau, mxGraph graph) {
		Object parent = graph.getDefaultParent();
		Map<String, Object> nodeMap = new HashMap<>();
		for (SocialNode sn : reseau) {
			nodeMap.put(sn.getName(), graph.insertVertex(parent, null, sn.getName(), r.nextInt(800),
					r.nextInt(400), 80, 30));
		}
		return nodeMap;
	}

	public static void addLinkToGraph(List<SocialNode> reseau, Map<String, Object> nodeMap, mxGraph graph) {
		Object parent = graph.getDefaultParent();
		for (SocialNode sn : reseau) {
			if (sn.getLinkList() != null) {
				for (Link l : sn.getLinkList()) {
					if (l.getNoeudDepart().equals(sn)) {
						graph.insertEdge(parent, null, l.getLinkName() + "\n" + l.getProperties(),
								nodeMap.get(l.getNoeudDepart().getName()), nodeMap.get(l.getNoeudArrive().getName()));
					}
				}
			}
		}
	}

	public static JComponent createGraph(List<SocialNode> reseau) {
		mxGraph graph = new mxGraph();
		graph.getModel().beginUpdate();
		Map<String, Object> nodeMap = addNodeToGraph(reseau, graph);
		addLinkToGraph(reseau, nodeMap, graph);
		graph.getModel().endUpdate();
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setEnabled(true);
		graphComponent.setVisible(true);

		return graphComponent;
	}
	
	private  JPanel createToolbar() {
		JPanel panelToolbar = new JPanel();
		
		JLabel nomNoeud = new JLabel("Noeud : ");
		JTextField nom = new JTextField();
		nom.setColumns(8);
		
		JRadioButton profondeur = new JRadioButton("Profondeur");
		profondeur.setActionCommand("parcoursProfondeur");
		
		JRadioButton largeur = new JRadioButton("Largeur");
		largeur.setActionCommand("parcoursLargeur");
		largeur.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(profondeur);
		group.add(largeur);
		
		JButton btn = new JButton("Rechercher");
		btn.setActionCommand("Recherche");
		JLabel labelProfondeur = new JLabel("Niv. Profondeur :");
		
		JSpinner textProfondeur = new JSpinner();
		JComponent editor = textProfondeur.getEditor();
		JFormattedTextField formatTextField = ((JSpinner.DefaultEditor) editor).getTextField();
		formatTextField.setColumns(2);
		
		JButton filtre = new JButton("Ajouter filtre");
		filtre.setActionCommand("ajouterFiltre");
		
		JButton retirerFiltre = new JButton("Retirer filtre");
		retirerFiltre.setActionCommand("retirerFiltre");
		
		largeur.addActionListener(this);
		profondeur.addActionListener(this);
		btn.addActionListener(this);
		filtre.addActionListener(this);
		retirerFiltre.addActionListener(this);
		
		panelToolbar.add(nomNoeud);
		panelToolbar.add(nom);
		panelToolbar.add(labelProfondeur);
		panelToolbar.add(textProfondeur);
		panelToolbar.add(profondeur);
		panelToolbar.add(largeur);
		panelToolbar.add(filtre);
		panelToolbar.add(retirerFiltre);
		panelToolbar.add(btn);
		
		return panelToolbar; 
	}

	//############### Fonctions d'interaction avec l'interface ###############
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("readFile".equals(e.getActionCommand())) {
			try {
				readFile();
			} catch (IOException e1) {
				logger.severe("Erreur lecture fichier");
			}
		}
		else if("Recherche".equals(e.getActionCommand())) {
			faireRecherche();
		}
		else if("ajouterFiltre".equals(e.getActionCommand())) {
			ajouterFiltre();
		}
		else if("retirerFiltre".equals(e.getActionCommand()) && this.filtrage != null) {
			this.filtrage.retirerFiltre();
		}
	}

	private void ajouterFiltre() {
		if(filtrage == null) {
			filtrage = new JOptionPaneFiltre();
		}
		else {
			filtrage.setCondition(filtrage.addFiltre(filtrage.getCondition(), 1));
		}
		cdt = filtrage.mapToConditon();
		StringBuilder bld = new StringBuilder();
		for (Condition filtre : cdt) {
			bld.append(filtre + "\n");
		}
		if (cdt.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Aucun filtre crée", "Vos filtres", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
		JOptionPane.showMessageDialog(null, bld.toString(), "Vos filtres", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	private void faireRecherche() {
		boolean isProfondeur = ((JRadioButton) panelRecherche.getComponent(4)).isSelected();
		JTextField texte = (JTextField) this.panelRecherche.getComponent(1);
		int profondeur = (int) ((JSpinner) this.panelRecherche.getComponent(3)).getValue();
		String nodeName = texte.getText();
		SocialNode node = this.sn.getNodeByName(nodeName);
		if (node == null) {
			JOptionPane.showMessageDialog(null, "Le noeud "+nodeName+" n'existe pas.", "Erreur de noeud", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JFrame popup = new JFrame();
			JPanel graph = new JPanel();
			cdt = this.filtrage.getCdtList();
			if(cdt!=null && cdt.isEmpty()) {
				cdt=null;
			}
			List<SocialNode> listnode;
			if(isProfondeur) {
				listnode = Parcours.parcoursProfondeur(node,profondeur,cdt);
			}
			else {
				listnode = Parcours.parcoursLargeur(node,profondeur,cdt);
			}
			graph.add((createGraph(listnode)));
			popup.getContentPane().add(BorderLayout.CENTER, graph);
			popup.invalidate();
			popup.validate();
			popup.repaint();
			popup.setSize(1200, 600);
			popup.setVisible(true);
		}
	}


	private void readFile() throws IOException {
		SocialNetwork sN = new SocialNetwork();
		JFileChooser choix = new JFileChooser();
		int retour = choix.showOpenDialog(panel);
		if (retour == JFileChooser.APPROVE_OPTION) {
			String path = choix.getSelectedFile().getAbsolutePath();
			BufferedReader buff = new BufferedReader(new FileReader(path));
			try {
				String ligne = buff.readLine();
				sN = JsonConverter.getSocialNetwork(ligne);
				this.sn = sN;
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Le fichier n'est pas valide.", "Erreur fichier", JOptionPane.ERROR_MESSAGE);
				return;
			}
			finally {
				buff.close();
			}
			this.panelGraph.setSize(800, 800);
			this.panelGraph.add(createGraph(sN.getSocialNetwork()));
			this.panelRecherche.setVisible(true);
			this.frame.invalidate();
			this.frame.validate();
			this.frame.repaint();
		}
	}
}
