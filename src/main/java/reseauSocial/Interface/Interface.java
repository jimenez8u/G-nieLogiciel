package reseauSocial.Interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import JSON.JsonConverter;
import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;

public class Interface extends JFrame implements ActionListener {

	/** Pour éviter un warning venant du JFrame */
	private static final long serialVersionUID = -8123406571694511514L;
	public JPanel panelGraph;
	public JPanel panelRecherche;
	public JPanel panel;
	public JFrame frame;
	public String typeParcours = "largeur";
	private SocialNetwork sn;


	/**
	 * @param args
	 */
	public Interface() {
		this.panelGraph = new JPanel();
		this.panelRecherche = createToolbar(panelGraph);
		this.panelRecherche.setVisible(false);
		this.frame = new JFrame();
		this.frame.setVisible(true);
		this.frame.setSize(1200, 600);
		this.panel = new JPanel();
		JButton bouton = new JButton("Insert file");
		bouton.setActionCommand("readFile");
		bouton.addActionListener(this);
			
		this.panel.add(bouton);
		
		this.frame.getContentPane().add(BorderLayout.SOUTH, panel);
		this.frame.getContentPane().add(BorderLayout.NORTH, panelGraph);
		this.frame.getContentPane().add(BorderLayout.EAST, panelRecherche);
	}

		


	private  JPanel createToolbar(JPanel panelGraph) {
		// TODO Auto-generated method stub
		JPanel panelToolbar = new JPanel();
		JLabel nomNoeud = new JLabel("Noeud : ");
		JTextField nom = new JTextField();
		nom.setColumns(8);
		panelToolbar.add(nomNoeud);
		panelToolbar.add(nom);
		panelToolbar.setSize(100,100);
		
		JRadioButton profondeur = new JRadioButton("Profondeur");
		profondeur.setActionCommand("profondeur");
		JRadioButton largeur = new JRadioButton("Largeur");
		largeur.setActionCommand("largeur");
		largeur.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(profondeur);
		group.add(largeur);
		
		
		JButton btn = new JButton("Rechercher");
		btn.setActionCommand("Recherche");
		
		
		largeur.addActionListener(this);
		profondeur.addActionListener(this);
		btn.addActionListener(this);
		
		panelToolbar.add(profondeur);
		panelToolbar.add(largeur);
		panelToolbar.add(btn);
		return panelToolbar;
	}

	public static HashMap<String, Object> addNodeToGraph(ArrayList<SocialNode> reseau, mxGraph graph) {
		Object parent = graph.getDefaultParent();
		HashMap<String, Object> nodeMap = new HashMap<String, Object>();
		for (SocialNode sn : reseau) {
			nodeMap.put(sn.getName(), graph.insertVertex(parent, null, sn.getName(), (int) (Math.random() * 800),
					(int) (Math.random() * 400), 80, 30));
		}

		return nodeMap;

	}

	public static void addLinkToGraph(ArrayList<SocialNode> reseau, HashMap<String, Object> nodeMap, mxGraph graph) {
		Object parent = graph.getDefaultParent();
		for (SocialNode sn : reseau) {
			if (sn.getLinkList() != null) {
				for (Link l : sn.getLinkList()) {
					if (l.getNoeudDepart().equals(sn)) {
						Object lien = graph.insertEdge(parent, null, l.getLinkName() + "\n" + l.getProperties(),
								nodeMap.get(l.getNoeudDepart().getName()), nodeMap.get(l.getNoeudArrive().getName()));
					}
				}
			}
		}
	}

	public static JComponent createGraph(ArrayList<SocialNode> reseau) {
		mxGraph graph = new mxGraph();
		graph.getModel().beginUpdate();
		HashMap<String, Object> nodeMap = addNodeToGraph(reseau, graph);
		addLinkToGraph(reseau, nodeMap, graph);
		graph.getModel().endUpdate();
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setSize(800, 800);
		graphComponent.setBounds(0, 0, 800, 800);
		graphComponent.setEnabled(true);
		graphComponent.setVisible(true);

		return graphComponent;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if ("readFile".equals(e.getActionCommand())) {
			readFile();
		}
		else if("largeur".equals(e.getActionCommand())){
			this.typeParcours = "largeur";
		}
		else if("profondeur".equals(e.getActionCommand())){
			this.typeParcours = "pronfondeur";
		}
		else if("recherche".equals(e.getActionCommand())) {
			this.panelGraph.remove(0);
			JTextField texte = (JTextField) this.panelRecherche.getComponent(1);
			String nodeName = texte.getText();
			this.panelGraph.remove(0);
			panelGraph.add(createGraph(Parcours.parcoursLargeur(sn.getNodeByName(nodeName));
		}
	}

	private void readFile() {
		// TODO Auto-generated method stub
		JFileChooser choix = new JFileChooser();
		int retour = choix.showOpenDialog(panel);
		if (retour == JFileChooser.APPROVE_OPTION) {
			// un fichier a été choisi (sortie par OK)
			// nom du fichier choisi
			choix.getSelectedFile().getName();
			// chemin absolu du fichier choisi
			String path = choix.getSelectedFile().getAbsolutePath();
			SocialNetwork sN = null;
			try {
				InputStream flux = new FileInputStream(path);
				InputStreamReader lecture = new InputStreamReader(flux);
				BufferedReader buff = new BufferedReader(lecture);
				String ligne = buff.readLine();
				sn = sN;
				sN = JsonConverter.getSocialNetwork(ligne);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			;
			panelGraph.setSize(800, 800);
			panelGraph.add(createGraph(sN.getSocialNetwork()));
			panelRecherche.setVisible(true);
			frame.invalidate();
			frame.validate();
			frame.repaint();
		} else
			;// pas de fichier choisi
	}
}
