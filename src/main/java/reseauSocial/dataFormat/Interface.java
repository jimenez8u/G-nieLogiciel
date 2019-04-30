package reseauSocial.dataFormat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Interface extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
         JPanel panelGraph = new JPanel();
    	 JFrame frame = new JFrame();
    	 frame.setVisible(true);
    	 frame.setSize(800, 600);
         JPanel panel = new JPanel();
         JButton bouton = new JButton("Insert file");
         bouton.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
           {
         	  JFileChooser choix = new JFileChooser();
         	  int retour=choix.showOpenDialog(panel);
         	  if(retour==JFileChooser.APPROVE_OPTION){
         	     // un fichier a été choisi (sortie par OK)
         	     // nom du fichier  choisi 
         	     choix.getSelectedFile().getName();
         	     // chemin absolu du fichier choisi
         	     choix.getSelectedFile().
         	     getAbsolutePath();
         	     ArrayList<LinkProperty> propertyList = new ArrayList<LinkProperty>();
         			ArrayList<Link> linkList = new ArrayList<Link>();
         			ArrayList<Link> linklist2 = new ArrayList<Link>();
         			LinkProperty lProperty = new LinkProperty("premier test", "oui");
         			propertyList.add(lProperty);
         			SocialNode sNJean = new SocialNode("Jean");
         			Link sL = new Link("premier lien", propertyList, sNJean);
         			linkList.add(sL);
         			SocialNode sNJacques = new SocialNode("Jacques", linkList);
         			Link sLL = new Link("deuxieme lien", propertyList, sNJean);
         			linklist2.add(sLL);
         			SocialNode sNPatrick = new SocialNode("Patrick",linklist2);
         			SocialNetwork sN = new SocialNetwork();
         			sN.addNode(sNJacques);
         			sN.addNode(sNJean);
         			sN.addNode(sNPatrick);
         			panelGraph.setSize(400, 400);
         			panelGraph.add(createGraph(sN.getSocialNetwork()));
         			frame.invalidate();
         			frame.validate();
         			frame.repaint();
         	  }else;// pas de fichier choisi
           }
         });
         panel.add(bouton);
         frame.getContentPane().add(BorderLayout.SOUTH, panel);
         frame.getContentPane().add(BorderLayout.NORTH, panelGraph);
        
    }
    
    public static JComponent createGraph(ArrayList<SocialNode> reseau) {
    	mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        HashMap<SocialNode, Object> noeuds = new HashMap<SocialNode, Object>();
        graph.getModel().beginUpdate();
        int i = 1;
        for (SocialNode sn : reseau) {
            try {
                noeuds.put(sn, graph.insertVertex(parent, null, sn.getName(), 10 + i, 10, 80, 30));
                i += 200;
            } finally {
                graph.getModel().endUpdate();
            }
        }
        graph.getModel().beginUpdate();
        for (SocialNode sn : reseau) {
            if (sn.getLinkList() != null) {
                for (Link l : sn.getLinkList()) {
                    graph.insertEdge(parent, null, l.getLinkName() + "\n" + l.getProperties(), noeuds.get(sn),
                            noeuds.get(l.getTarget()));
                }
            }
        }
        graph.getModel().endUpdate();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setSize(400, 400);
        graphComponent.setEnabled(true);
        graphComponent.setVisible(true);
        return graphComponent;
    }
}