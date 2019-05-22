package reseauSocial.Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import JSON.JsonConverter;
import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;
import reseauSocial.parcours.Parcours;

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
		this.sn = new SocialNetwork();
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
		this.frame.getContentPane().add(BorderLayout.CENTER, panelRecherche);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private  JPanel createToolbar(JPanel panelGraph) {
		// TODO Auto-generated method stub
		JPanel panelToolbar = new JPanel();
		JLabel nomNoeud = new JLabel("Noeud : ");
		JTextField nom = new JTextField();
		nom.setColumns(8);
		panelToolbar.add(nomNoeud);
		panelToolbar.add(nom);
		panelToolbar.setSize(200,200);
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
		
		JLabel labelProfondeur = new JLabel("Niv. Profondeur :");
		JSpinner textProfondeur = new JSpinner();
		JComponent editor = textProfondeur.getEditor();
		JFormattedTextField formatTextField = ((JSpinner.DefaultEditor) editor).getTextField();
		formatTextField.setColumns(2);
		
		panelToolbar.add(labelProfondeur);
		panelToolbar.add(textProfondeur);
		
		
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
		else if("Recherche".equals(e.getActionCommand())) {
			JTextField texte = (JTextField) this.panelRecherche.getComponent(1);
			int profondeur = (int) ((JSpinner) this.panelRecherche.getComponent(3)).getValue();
			String nodeName = texte.getText();
			SocialNode node = this.sn.getNodeByName(nodeName);
			if(typeParcours =="largeur") {
				if (node == null) {
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Le noeud "+nodeName+" n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else {
					this.panelGraph.remove(0);
					ArrayList<SocialNode> listnode = (ArrayList<SocialNode>) Parcours.parcoursLargeur(node,profondeur);
					this.panelGraph.add((createGraph((ArrayList<SocialNode>) listnode)));
					this.frame.invalidate();
					this.frame.validate();
					this.frame.repaint();
				}
			}
			else {
				if (node == null) {
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Le noeud "+nodeName+" n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else {
					this.panelGraph.remove(0);
					ArrayList<SocialNode> listnode = (ArrayList<SocialNode>) Parcours.parcoursProfondeur(node,profondeur);
					this.panelGraph.add((createGraph((ArrayList<SocialNode>) listnode)));
					this.frame.invalidate();
					this.frame.validate();
					this.frame.repaint();
				}
				
			}
		}
	}

	private void readFile() throws IOException {
		// TODO Auto-generated method stub
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
				JOptionPane jop3 = new JOptionPane();
				jop3.showMessageDialog(null, "Le fichier n'est pas valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
