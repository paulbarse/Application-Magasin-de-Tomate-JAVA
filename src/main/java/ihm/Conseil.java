package ihm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modèle.OutilsBaseDonneesTomates;
import modèle.Tomates;
import modèle.Tomate;

public class Conseil extends JDialog {   // ← On hérite de JDialog

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        // On crée un JDialog avec parent null
        Conseil fenetre = new Conseil(null);
        fenetre.setSize(350, 550); 
        fenetre.setLocationRelativeTo(null); 
        fenetre.setVisible(true);
    }

    // Nouveau constructeur avec Frame parent
    public Conseil(Frame parent) {
        super(parent, "Conseils de Culture", true);  // ← true = modal

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(350, 550); 
        setLocationRelativeTo(parent); 

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 

        JPanel Haut_Titre = new JPanel();
        Haut_Titre.setPreferredSize(new Dimension(Short.MAX_VALUE, 50));      
        Haut_Titre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        JLabel Titre = new JLabel("Conseils de Culture");
        Titre.setForeground(new Color(72, 130, 91));
        Titre.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        Haut_Titre.add(Titre);

        JPanel Mid_panel = new JPanel();
        Mid_panel.setPreferredSize(new Dimension(300, 150)); 
        Mid_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150)); 
        Mid_panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(72, 130, 91), 2), 
            "Conseils :", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(72, 130, 91)
        ));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 320)); 
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 320)); 

        JTextArea txtrA = new JTextArea(Tomates.CONSEILS_DE_CULTURE);
        txtrA.setLineWrap(true);
        txtrA.setWrapStyleWord(true);
        txtrA.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtrA.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtrA,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panel);

        mainPanel.add(Haut_Titre);
        mainPanel.add(Mid_panel);

        JEditorPane texteConseil = new JEditorPane();
        texteConseil.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
        texteConseil.setText(Tomates.CONSEILS_DE_CULTURE_TITRE);
        texteConseil.setEditable(false);
        texteConseil.setForeground(new Color(72, 130, 91));

        Mid_panel.setLayout(new BorderLayout());
        Mid_panel.add(texteConseil, BorderLayout.CENTER);

        mainPanel.add(panel);

        setContentPane(mainPanel);

        JPanel basPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10)); 
        basPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); 

        JButton btnFermer = new JButton("Fermer");
        btnFermer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnFermer.setBackground(new Color(230, 230, 230)); 
        btnFermer.addActionListener(e -> dispose());
        basPanel.add(btnFermer);
        mainPanel.add(basPanel);
    }
}