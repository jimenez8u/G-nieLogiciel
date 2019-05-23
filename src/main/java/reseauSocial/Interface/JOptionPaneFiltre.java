package reseauSocial.Interface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.parcours.Condition;

class JOptionPaneFiltre implements ActionListener {
	private Map<String,List<LinkProperty>> condition;
	private List<Condition> cdtList;
	private JPanel suprFiltre;
	private JFrame fenetreSupr;
    public JOptionPaneFiltre() {
    	this.condition = new HashMap<>();
    	this.condition = this.addFiltre(this.condition,1);
    	this.cdtList = this.mapToConditon();
    }

    public Map<String,List<LinkProperty>> addFiltre(Map<String,List<LinkProperty>> condition, int i) {
        String[] items = {"Filtre "+i};
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
        if (result == JOptionPane.OK_OPTION && !(field1.getText().equals(""))) {
        	String nom = field1.getText();
            String prop = field2.getText();
            String propValue = field3.getText();
            LinkProperty lp = new LinkProperty(prop,propValue);
            if(this.condition.containsKey(nom)) {
            	if(!(prop.equals("") && propValue .equals(""))) {
            		this.condition.get(nom).add(lp);
            	}
            }
            else {
            	ArrayList<LinkProperty> listprop = new ArrayList<>();
            	if(!(prop.equals("") && propValue .equals(""))) {
            		listprop.add(lp);
            	}
            	condition.put(nom, listprop);
            }
            return addFiltre(condition,i+1);
        }
        else {
            return condition;
        }
    }
    public void retirerFiltre() {
    	this.fenetreSupr = new JFrame();
    	this.fenetreSupr.setSize(550,125);
    	this.fenetreSupr.setLocation(500, 500);
    	this.fenetreSupr.setVisible(true);
    	this.suprFiltre = new JPanel(new GridLayout(0, 2));
    	this.suprFiltre.setSize(520,100);
    	Integer i =0;
		for (Condition filtre : this.cdtList) {
			JButton btn = new JButton("Enlever la condition");
			btn.setActionCommand(i.toString());
			btn.addActionListener(this);
			this.suprFiltre.add(btn);
	        this.suprFiltre.add(new JLabel(filtre.toString()));
	        i++;
		}
		this.fenetreSupr.add(this.suprFiltre);
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

	public List<Condition> getCdtList() {
		return cdtList;
	}

	public void setCdtList(List<Condition> cdtList) {
		this.cdtList = cdtList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.cdtList.remove(Integer.parseInt(e.getActionCommand()));
		this.suprFiltre.setVisible(false);
		this.fenetreSupr.setVisible(false);
		this.retirerFiltre();
	}

    
}