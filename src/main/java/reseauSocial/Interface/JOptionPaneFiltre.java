package reseauSocial.Interface;




import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.parcours.Condition;

class JOptionPaneFiltre {
	private Map<String,List<LinkProperty>> condition;
	private JOptionPaneFiltre prochainFiltre;
    public JOptionPaneFiltre() {
    	this.condition = new HashMap<>();
        String[] items = {"Filtre 1"};
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(combo);
        panel.add(new JLabel("(*)Nom du lien :"));
        panel.add(field1);
        panel.add(new JLabel("Nom propriété :"));
        panel.add(field2);
        panel.add(new JLabel("Valeur propriété:"));
        panel.add(field3);
        panel.add(new JLabel("(*) : valeur obligatoire"));
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	String nom = field1.getText();
            String prop = field2.getText();
            String propValue = field3.getText();
            if(prop.equals("")) {
            	prop = null;
            }
            if(propValue .equals("")) {
            	propValue = null;
            }
            ArrayList<LinkProperty> listprop = new ArrayList<>();
            LinkProperty lp = new LinkProperty(prop,propValue);
            listprop.add(lp);
            condition.put(nom, listprop);
            this.prochainFiltre = new JOptionPaneFiltre(this.condition);
        } else {
            System.out.println("Cancelled");
        }
    }
    
    public JOptionPaneFiltre(Map<String,List<LinkProperty>> condition) {
    	this.condition = condition;
        String[] items = {"Filtre "+(condition.size()+1)};
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(combo);
        panel.add(new JLabel("(*)Nom du lien :"));
        panel.add(field1);
        panel.add(new JLabel("Nom propriété:"));
        panel.add(field2);
        panel.add(new JLabel("Valeur propriété:"));
        panel.add(field3);
        panel.add(new JLabel("(*) : valeur obligatoire"));
        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	String nom = field1.getText();
            String prop = field2.getText();
            String propValue = field3.getText();
            if(prop.equals("")) {
            	prop = null;
            }
            if(propValue .equals("")) {
            	propValue = null;
            }
            LinkProperty lp = new LinkProperty(prop,propValue);
            if(this.condition.containsKey(nom)) {
            	this.condition.get(nom).add(lp);
            }
            else {
            	ArrayList<LinkProperty> listprop = new ArrayList<>();
            	listprop.add(lp);
            	condition.put(nom, listprop);
            }
            this.prochainFiltre = new JOptionPaneFiltre(this.condition);
            
        } else {
            System.out.println("Cancelled");
        }
    }

	public Map<String, List<LinkProperty>> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, List<LinkProperty>> condition) {
		this.condition = condition;
	}


    public List<Condition> mapToConditon(){
    	List<Condition> cdt = new ArrayList<>();
		this.condition.forEach((k,v) -> cdt.add(new Condition(k,v)));
    	
    	
    	return cdt;
    	
    }

    
}