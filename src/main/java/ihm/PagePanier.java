package ihm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modèle.Articles;
import modèle.Panier;
import modèle.Tomate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagePanier extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField fieldSous, fieldLivraison, fieldTotal;
    private DefaultTableModel modeleTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Appel du JDialog avec null comme parent pour test indépendant
                PagePanier dialog = new PagePanier(null,null,null);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Nouveau constructeur avec Frame parent
    public PagePanier(Frame parent, Panier panierClient,Tomato fenetre) {
        super(parent, "Votre Panier", true);  // true = modal
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 691, 451);

        this.contentPane = new JPanel(new BorderLayout());
        this.contentPane.setBackground(new Color(73, 130, 91));
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);

        // Barre supérieure avec icône, titre et bouton complètement à droite
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(73, 130, 91));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setBackground(new Color(73, 130, 91));

        ImageIcon originalIcon = new ImageIcon("/Users/joandiaz/Downloads/image-removebg-preview.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(resizedImage));
        leftPanel.add(lblIcon);

        JLabel lblNewLabel_2 = new JLabel("Votre Panier");
        lblNewLabel_2.setBackground(new Color(255, 255, 255));
        lblNewLabel_2.setForeground(new Color(47, 47, 48));
        leftPanel.add(lblNewLabel_2);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightPanel.setBackground(new Color(73, 130, 91));

        JButton btnRecalculate = new JButton("Recalculer le panier");
        btnRecalculate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		for (int i=0;i<modeleTable.getRowCount();i++) {
        		int quantite = 1;
        		Object value = modeleTable.getValueAt(i, 2);
        		if (value instanceof Integer) {
        		    quantite = (Integer) value;
        		    if (quantite<0) {
            			quantite=1;
            		}else if (quantite>panierClient.getArticles().get(i).getTomate().getStock()) {
            			quantite=panierClient.getArticles().get(i).getTomate().getStock();
            		}else if(quantite==0) {
            			modeleTable.removeRow(i);
            		}
        		} else if (value instanceof Number) {
        		    quantite = ((Number) value).intValue();
        		    if (quantite<0) {
            			quantite=1;
            		}else if (quantite>panierClient.getArticles().get(i).getTomate().getStock()) {
            			quantite=panierClient.getArticles().get(i).getTomate().getStock();
            		}else if(quantite==0) {
            			modeleTable.removeRow(i);
            		}
        		} else if (value != null) {
        		    try {
        		        quantite = Integer.parseInt(value.toString());
        		        if (quantite<0) {
                			quantite=1;
                		}else if (quantite>panierClient.getArticles().get(i).getTomate().getStock()) {
                			quantite=panierClient.getArticles().get(i).getTomate().getStock();
                		}else if(quantite==0) {
                			modeleTable.removeRow(i);
                		}
        		    } catch (NumberFormatException a) {

        		    }
        		}
        		if(quantite==0) {
        			panierClient.retirerArticle(panierClient.getArticles().get(i));
        		}else {
        			panierClient.getArticles().get(i).setQuantité(quantite);
        		}
        		
        		}
        		
        		maj(panierClient);
        		fenetre.majPanier();
        		
        	}
        });
        btnRecalculate.setBackground(Color.WHITE);
        btnRecalculate.setOpaque(true);
        btnRecalculate.setBorderPainted(false); // Supprime la bordure

        rightPanel.add(btnRecalculate);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        this.contentPane.add(topPanel, BorderLayout.NORTH);

        // Tableau des produits
        modeleTable = new DefaultTableModel(new Object[]{"produit","prix","quantité","total"}, 0);
        this.table = new JTable(modeleTable);
        JScrollPane scrollPane = new JScrollPane(this.table);
        this.contentPane.add(scrollPane, BorderLayout.CENTER);
        
        //ajouter la liste du panier dans le tableau
        for (Articles p : panierClient.getArticles()) {
        	modeleTable.addRow(new Object[] { p.getDésignation(),p.getPrixUnitaire(),p.getQuantité(),p.getPrixTotal() });
        }

        // Section sous-total, frais de livraison et total
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel subtotalPanel = new JPanel(new GridLayout(3, 2, 15, 10)); // 3 lignes, 2 colonnes
        subtotalPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Sous total
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(238, 237, 237));
        subtotalPanel.add(panel_1_1);

        JLabel lblNewLabel_1_1 = new JLabel("  Sous total :  ");
        lblNewLabel_1_1.setOpaque(true);
        lblNewLabel_1_1.setBackground(new Color(255, 252, 169));
        panel_1_1.add(lblNewLabel_1_1);

        this.fieldSous = new JTextField(panierClient.getSousTotal()+"€");
        this.fieldSous.setEditable(false);
        this.fieldSous.setColumns(6);
        subtotalPanel.add(this.fieldSous);

        // Frais de livraison
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(237, 238, 237));
        subtotalPanel.add(panel_1);

        JLabel lblNewLabel_1 = new JLabel("  Frais de livraison :  ");
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setBackground(new Color(255, 252, 169));
        panel_1.add(lblNewLabel_1);

        this.fieldLivraison = new JTextField(panierClient.getFraisExpedition()+"€");
        this.fieldLivraison.setEditable(false);
        this.fieldLivraison.setColumns(6);
        subtotalPanel.add(this.fieldLivraison);

        // Total
        JPanel panel = new JPanel();
        panel.setBackground(new Color(237, 238, 237));
        subtotalPanel.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel("  Total :  ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBackground(new Color(255, 252, 169));
        panel.add(lblNewLabel);

        this.fieldTotal = new JTextField(panierClient.getTotal()+"€");
        this.fieldTotal.setEditable(false);
        this.fieldTotal.setColumns(6);
        subtotalPanel.add(this.fieldTotal);

        bottomPanel.add(subtotalPanel, BorderLayout.NORTH);

        // Boutons d'action
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnValidate = new JButton("Valider le panier");
        btnValidate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		PagePaiements paiement = new PagePaiements(fenetre,panierClient);
        		paiement.setVisible(true);
        	}
        });
        JButton btnClear = new JButton("Vider le panier");
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panierClient.viderPanier();	
        		modeleTable.setRowCount(0);
        		fenetre.majPanier();
        	}
        });
        JButton btnContinue = new JButton("Continuer les achats");
        btnContinue.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        buttonPanel.add(btnValidate);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnContinue);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }
    
    public void maj(Panier panierClient) {
		this.fieldSous.setText(panierClient.getSousTotal()+"€");
		this.fieldLivraison.setText(panierClient.getFraisExpedition()+"€");
		this.fieldTotal.setText(panierClient.getTotal()+"€");
		revalidate(); 
	    repaint();
    }
    

    
}
