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

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Interface extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;

    public Interface() {
        JFrame frame = new JFrame();
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
        	  }else;// pas de fichier choisi
          }
        });
        panel.add(bouton);
        getContentPane().add(BorderLayout.SOUTH, panel);
		setVisible(true);
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Interface frame = new Interface();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    public JComponent createGraph(ArrayList<SocialNode> reseau) {
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
        graphComponent.setEnabled(false);
        return graphComponent;
    }
}