package ihm;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import modèle.Client;
import modèle.Panier;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagePaiements extends JDialog {
    public PagePaiements(Frame parent, Panier panierClient) {
        super(parent, "Vos coordonnées", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Panel principal avec fond vert et titre
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(70, 130, 90)); // Vert doux

        // Titre en haut
        JLabel titre = new JLabel("Vos coordonnées");
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        mainPanel.add(titre, BorderLayout.NORTH);

        // Panel central (tout le contenu sauf les boutons)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // --- Bloc Informations personnelles
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Informations personnelles", TitledBorder.LEFT, TitledBorder.TOP));
        infoPanel.setBackground(Color.WHITE);

        infoPanel.add(new JLabel("Nom :"));
        JTextField nom = new JTextField(15);
        infoPanel.add(nom);
        infoPanel.add(new JLabel("Prénom :"));
        JTextField prenom = new JTextField(15);
        infoPanel.add(prenom);
        infoPanel.add(new JLabel("Téléphone :"));
        JTextField telephone = new JTextField(15);
        infoPanel.add(telephone);
        infoPanel.add(new JLabel("Adresse e-mail :"));
        JTextField mail = new JTextField(15);
        infoPanel.add(mail);

        // --- Bloc Adresse postale
        JPanel adressePanel = new JPanel();
        adressePanel.setLayout(new GridLayout(4, 2, 10, 10));
        adressePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Adresse postale", TitledBorder.LEFT, TitledBorder.TOP));
        adressePanel.setBackground(Color.WHITE);

        adressePanel.add(new JLabel("Adresse (ligne 1) :"));
        JTextField adresse1 = new JTextField(15);
        adressePanel.add(adresse1);
        adressePanel.add(new JLabel("Adresse (complément) :"));
        JTextField adresse2 = new JTextField(15);
        adressePanel.add(adresse2);
        adressePanel.add(new JLabel("Code postal :"));
        JTextField codePostal = new JTextField(15);
        adressePanel.add(codePostal);
        adressePanel.add(new JLabel("Ville :"));
        JTextField ville = new JTextField(15);
        adressePanel.add(ville);

        // --- Bloc Mode de paiement
        JPanel paiementPanel = new JPanel();
        paiementPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        paiementPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Mode de paiement", TitledBorder.LEFT, TitledBorder.TOP));
        paiementPanel.setBackground(Color.WHITE);

        ButtonGroup paiementGroup = new ButtonGroup();
        JRadioButton carte = new JRadioButton("Carte bancaire");
        JRadioButton paypal = new JRadioButton("PayPal");
        JRadioButton cheque = new JRadioButton("Chèque");
        paiementGroup.add(carte); paiementGroup.add(paypal); paiementGroup.add(cheque);
        paiementPanel.add(carte);
        paiementPanel.add(paypal);
        paiementPanel.add(cheque);

        // --- Bloc Abonnement
        JPanel aboPanel = new JPanel();
        aboPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        aboPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Souhaitez-vous vous abonner ?", TitledBorder.LEFT, TitledBorder.TOP));
        aboPanel.setBackground(Color.WHITE);

        ButtonGroup aboGroup = new ButtonGroup();
        JRadioButton oui = new JRadioButton("Oui, je souhaite m'abonner");
        JRadioButton non = new JRadioButton("Non, je ne souhaite pas m'abonner");
        aboGroup.add(oui); aboGroup.add(non);
        aboPanel.add(oui);
        aboPanel.add(non);

        // Ajout des panels au panel central
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(infoPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(adressePanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(paiementPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(aboPanel);

        // Boutons en bas
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(70, 130, 90));
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomText = nom.getText();
                String prenomText = prenom.getText();
                String telephoneText = telephone.getText();
                String mailText = mail.getText();
                String adresse1Text = adresse1.getText();
                String adresse2Text = adresse2.getText();
                String codePostalText = codePostal.getText();
                String villeText = ville.getText();
                String choixPaiement = paiementGroup.getSelection().getActionCommand();
                Client client = new Client(nomText, prenomText, telephoneText, mailText, adresse1Text, adresse2Text, codePostalText, villeText, choixPaiement);
                Facture facture = new Facture(parent,panierClient, client);
                facture.setVisible(true);
            }
        });
        JButton annuler = new JButton("Annuler");
        buttonPanel.add(ok);
        buttonPanel.add(annuler);

        // Action Annuler ferme la fenêtre
        annuler.addActionListener(e -> dispose());


        // Agencement général
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Marges sur le panel central
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBackground(new Color(70, 130, 90));
        borderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        borderPanel.add(mainPanel, BorderLayout.CENTER);

        setContentPane(borderPanel);
        pack();
        setMinimumSize(new Dimension(600, 600));
        setLocationRelativeTo(parent);
    }



	// Pour tester la JDialog
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
            PagePaiements dialog = new PagePaiements(null,null);
            dialog.setVisible(true);
        });
    }
}
