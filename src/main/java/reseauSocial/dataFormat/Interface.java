package reseauSocial.dataFormat;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Interface extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;

    public Interface(ArrayList<SocialNode> reseau) {
        super("Affichage du réseau");

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
                    graph.insertEdge(parent, null, l.getLinkName() + "\n" + l.getProperties()[0], noeuds.get(sn),
                            noeuds.get(l.getTarget()));
                }
            }
        }
        graph.getModel().endUpdate();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);
        getContentPane().add(graphComponent);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LinkProperty amis = new LinkProperty("since", "1985");
        LinkProperty[] tprop = { amis };
        SocialNode catherine = new SocialNode("catherine", null);
        Link jeancath = new Link("friend", tprop, catherine);
        Link[] tjeancath = { jeancath };
        SocialNode jean = new SocialNode("jean", tjeancath);

        ArrayList<SocialNode> reseau = new ArrayList<SocialNode>();
        reseau.add(jean);
        reseau.add(catherine);
        Interface frame = new Interface(reseau);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}