package reseauSocial.dataFormat;

import java.awt.BorderLayout;
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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import JSON.JsonConverter;

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
         		  String path = choix.getSelectedFile().getAbsolutePath();
         	      SocialNetwork sN = null;
         	      try{
         	    	InputStream flux = new FileInputStream(path); 
         	    	InputStreamReader lecture = new InputStreamReader(flux);
         	    	BufferedReader buff = new BufferedReader(lecture);
         	    	String ligne = buff.readLine();
         	    	sN = JsonConverter.getSocialNetwork(ligne);
         	      }catch(Exception e1) {e1.printStackTrace();};
         	      panelGraph.setSize(800, 800);
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
        HashMap<String, Object> noeuds = new HashMap<String, Object>();
        graph.getModel().beginUpdate();
        int i = 1;
        for (SocialNode sn : reseau) {
            try {
                noeuds.put(sn.getName(), graph.insertVertex(parent, null, sn.getName(), (int)(Math.random()*800), (int)(Math.random()*400), 80, 30));
                i += 200;
            } finally {
                graph.getModel().endUpdate();
            }
        }
        graph.getModel().beginUpdate();
        for (SocialNode sn : reseau) {
            if (sn.getLinkList() != null) {
                for (Link l : sn.getLinkList()) {
                	if (l.getNoeudDepart().equals(sn)) {
                		System.out.println(l.getLinkName());
                		Object lien =  graph.insertEdge(parent, null, l.getLinkName() + "\n" + l.getProperties(), noeuds.get(l.getNoeudDepart().getName()),
                				noeuds.get(l.getNoeudArrive().getName()));
                	}
                }
            }
        }
        graph.getModel().endUpdate(); 
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setSize(800, 800);
        graphComponent.setBounds(0, 0, 800, 800);
        graphComponent.setEnabled(true);
        graphComponent.setVisible(true);
        
        return graphComponent;
    }
}