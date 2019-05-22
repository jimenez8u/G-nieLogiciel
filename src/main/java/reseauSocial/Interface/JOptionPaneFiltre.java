package reseauSocial.Interface;




import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

class JOptionPaneFiltre {
	private List<String> condition;
	private JOptionPaneFiltre prochainFiltre;
    public JOptionPaneFiltre() {
    	this.condition = new ArrayList<String>();
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
            this.condition.add(nom+" : "+ prop + " : " + propValue);
            this.prochainFiltre = new JOptionPaneFiltre(this.condition);
        } else {
            System.out.println("Cancelled");
        }
    }
    
    public JOptionPaneFiltre(List<String> condition) {
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
            this.condition.add(nom+" : "+ prop + " : " + propValue);
            this.prochainFiltre = new JOptionPaneFiltre(this.condition);
            
        } else {
            System.out.println("Cancelled");
        }
    }

	public List<String> getCondition() {
		return condition;
	}

	public void setCondition(List<String> condition) {
		this.condition = condition;
	}
    
    

    
}