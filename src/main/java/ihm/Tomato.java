package ihm;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.table.TableModel;

import modèle.OutilsBaseDonneesTomates;
import modèle.Panier;
import modèle.Tomates;
import modèle.Tomate; 
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import modèle.Articles;
public class Tomato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel modeleTable;
	private int NbTomate = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tomato frame = new Tomato(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Panier panierClient = new Panier();
	public Tomato(Articles a) {
		
		
		String cheminFichier = "src/main/resources/data/tomates.json";//
		Tomates baseTomates = OutilsBaseDonneesTomates.générationBaseDeTomates(cheminFichier); //liste c'est notre base de donnée avec toute les tomates
		List<Tomate> liste = baseTomates.getTomates();//
		
		
		
		
		
		if (a!=null){
			panierClient.ajouterArticle(a);
		}

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_haut = new JPanel(new BorderLayout());
		contentPane.add(panel_haut, BorderLayout.NORTH);	
		JPanel panel_haut_droite = new JPanel(new BorderLayout());
		panel_haut_droite.setBackground(new Color(72, 130, 91));
		panel_haut.add(panel_haut_droite, BorderLayout.CENTER);
		
		JLabel Tomate = new JLabel("Tomates");
		Tomate.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
		Tomate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panel_haut_droite.add(Tomate, BorderLayout.CENTER);

		
		JLabel logo = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/Projet/logo.png"));
        Image image = icon.getImage();
        Image nvImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(nvImage));
        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panel_haut_droite.add(logo, BorderLayout.WEST);

		
		JPanel panelDroite = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelDroite.setBackground(new Color(72, 130, 91));
		panel_haut_droite.add(panelDroite, BorderLayout.EAST);
		
		
		
		JButton panier = new JButton("");
		// ACCEDDER AU PANIER //
		panier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panierClient.getSousTotal()==0) {
					FenetreAlerte alerte = new FenetreAlerte(Tomato.this);
					alerte.setVisible(true);
				}else {
					PagePanier panierPage = new PagePanier(Tomato.this,panierClient,Tomato.this);
					panierPage.setVisible(true);
				}
			}
		});
		// ACCEDDER AU PANIER //
		
		
		
		ImageIcon iconPanier = new ImageIcon(getClass().getResource("/images/img_panier_1.jpg"));
        Image imagePanier = iconPanier.getImage();
        Image nvImagePanier = imagePanier.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        panier.setIcon(new ImageIcon(nvImagePanier));
		panier.setBackground(new Color(230, 230, 230));
		panier.setForeground(Color.WHITE);
		panier.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panier.setFocusPainted(false);
		panier.setBorderPainted(true);
		panier.setContentAreaFilled(true);
		panier.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panier.setOpaque(true); 
		panier.setPreferredSize(new Dimension(50, 40));
		panelDroite.add(panier, BorderLayout.EAST);
			
		
		textField = new JTextField();
		textField.setText(String.valueOf(panierClient.getSousTotal())+"€");
		textField.setHorizontalAlignment(SwingConstants.CENTER); 
		textField.setFont(new Font("Segoe UI", Font.BOLD, 18));  
		textField.setForeground(Color.BLACK);                    
		textField.setBackground(new Color(230, 230, 230));        
		textField.setEditable(false);                            
		textField.setFocusable(false);                           
		textField.setPreferredSize(new Dimension(100, 40));      
		panelDroite.add(textField, BorderLayout.EAST);
		
		JPanel panel_bas = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_bas.setBackground(new Color(72, 130, 91));
		contentPane.add(panel_bas, BorderLayout.SOUTH);
		
		JPanel panel_filtre = new JPanel();
		panel_filtre.setBackground(Color.WHITE); 

		
		panel_filtre.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createLineBorder(new Color(0, 128, 0), 2), 
		    "Filtres :", 
		    TitledBorder.LEFT, 
		    TitledBorder.TOP, 
		    new Font("Segoe UI", Font.BOLD, 14),
		    new Color(0, 128, 0) 
		));

		panel_bas.add(panel_filtre);

		
		JLabel logo_tomate = new JLabel("");
		ImageIcon iconFiltrePanier = new ImageIcon(getClass().getResource("/images/img_filtre_tomate_1.jpg"));
		Image imageFiltreType = iconFiltrePanier.getImage();
        Image nvImageFiltreType = imageFiltreType.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_tomate.setIcon(new ImageIcon(nvImageFiltreType));
		panel_filtre.add(logo_tomate);
		
		

		Set<String> couleursSet = new TreeSet<>();
		Set<String> varietesSet = new TreeSet<>();
		
		
		
		
		// CHOIX TYPE TOMATE //
		JComboBox choix_variante = new JComboBox();
		choix_variante.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				modeleTable.setRowCount(0);
				if (choix_variante.getSelectedItem().equals("Toute les Tomates")) {
					for (Tomate t : liste) {
			            modeleTable.addRow(new Object[] { t.getDésignation() });
			        }
				}
				
				for (Tomate t : liste) {
					if (t.getType().getDénomination().equals(choix_variante.getSelectedItem())) {
						modeleTable.addRow(new Object[] { t.getDésignation() });
					}
				}
				
			}
		});
		// CHOIX TYPE TOMATE //
		
		
		
		
		
		// CHOIX COULEUR AVEC TYPE //
		JComboBox choix_couleur = new JComboBox();
		choix_couleur.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				modeleTable.setRowCount(0);
				for (Tomate t : liste) {
					if (t.getCouleur().getDénomination().equals(choix_couleur.getSelectedItem()) && t.getType().getDénomination().equals(choix_variante.getSelectedItem())) {
						modeleTable.addRow(new Object[] { t.getDésignation() });
						
					}
					
				}
				for (Tomate t : liste) {
					if (choix_variante.getSelectedItem().equals("Toute les Tomates") && t.getCouleur().getDénomination().equals(choix_couleur.getSelectedItem())) {
						modeleTable.addRow(new Object[] { t.getDésignation() });
				
					}
				}
			}
		});
		// CHOIX COULEUR AVEC TYPE //
		
		
		
		
		

		for (Tomate t : liste) {
			varietesSet.add("Toute les Tomates");
		    varietesSet.add(t.getType().getDénomination());
		    couleursSet.add(t.getCouleur().getDénomination()); // ⚠️ getDénomination() selon ta classe Couleur
		}

		
		choix_couleur.setModel(new DefaultComboBoxModel<>(
			    couleursSet.toArray(new String[0])
			));
		
		choix_variante.setModel(new DefaultComboBoxModel<>(
		    varietesSet.toArray(new String[0])
		));
		

		
		
		
		panel_filtre.add(choix_variante);
		
		JLabel logo_couleur = new JLabel("");
		ImageIcon iconFiltreCouleur = new ImageIcon(getClass().getResource("/images/img_filtre_couleur_1.jpg"));
		Image imageFiltreCouleur = iconFiltreCouleur.getImage();
        Image nvImageFiltreCouleur = imageFiltreCouleur.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_couleur.setIcon(new ImageIcon(nvImageFiltreCouleur));
		panel_filtre.add(logo_couleur);
		panel_filtre.add(choix_couleur);
		
		
		
		// BOUTON POUR PASSER A PAGE CONSEIL //
		
		JButton conseil = new JButton("");
		
		conseil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conseil conseilPage = new Conseil(Tomato.this);
				conseilPage.setVisible(true);
			}
		});
		
		
		// BOUTON POUR PASSER A PAGE CONSEIL //
		
		
		
		
		
		ImageIcon iconConseil = new ImageIcon(getClass().getResource("/images/conseilimg_1.jpg"));
		Image imageConseil = iconConseil.getImage();
        Image nvImageConseil = imageConseil.getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        conseil.setIcon(new ImageIcon(nvImageConseil));
		conseil.setBackground(new Color(230, 230, 230));
		conseil.setForeground(Color.WHITE);
		conseil.setFont(new Font("Segoe UI", Font.BOLD, 14));
		conseil.setFocusPainted(false);
		conseil.setBorderPainted(true);
		conseil.setContentAreaFilled(true);
		conseil.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		conseil.setCursor(new Cursor(Cursor.HAND_CURSOR));
		conseil.setOpaque(true); 
		conseil.setPreferredSize(new Dimension(80, 60));
		panel_bas.add(conseil);
		
		JPanel panel_bordure_gauche = new JPanel();
		panel_bordure_gauche.setBackground(new Color(72, 130, 91));
		contentPane.add(panel_bordure_gauche, BorderLayout.WEST);
		
		JPanel panel_bordure_droit = new JPanel();
		panel_bordure_droit.setBackground(new Color(72, 130, 91));
		contentPane.add(panel_bordure_droit, BorderLayout.EAST);
		
		JPanel panel_centre = new JPanel();
		contentPane.add(panel_centre, BorderLayout.CENTER);
	

		
		modeleTable = new DefaultTableModel(new Object[]{"Variété de Tomates"}, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};
		
		//Table des Tomates
        JTable table = new JTable(modeleTable);// modeleTable contient les tomate
        table.setRowSelectionAllowed(true); 
        table.setRowSorter(new TableRowSorter<>(modeleTable));
        JScrollPane sp = new JScrollPane(table);
        //Table des Tomates
        
        
        
        
        
        // ACCEDER A PAGE DE LA TOMATE //
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                    // Convertir l'index de la vue vers le modèle
                int modelIndex = table.convertRowIndexToModel(selectedRow);

                    // Récupérer le nom de la tomate sélectionnée depuis le modèle
                String nomTomate = (String) modeleTable.getValueAt(modelIndex, 0);      
        				for (Tomate t : liste) {
                            if (t.getDésignation().equals(nomTomate)) {
                            	if(t.getStock()!=0) {
                            		Description_Tomate TomatePage = new Description_Tomate(Tomato.this,t,t.getDésignation(), t.getDescription(),t.getNomImage(),t.getStock(),t.getPrixTTC(),t.getNbGrainesParSachet(),Tomato.this);
                                    TomatePage.setVisible(true);
                            	}else {
                            		Description_Tomate_rupture TomateRupturePage = new Description_Tomate_rupture(Tomato.this,t.getDésignation(), t.getDescription(),t.getNomImage(),t.getStock(),t.getPrixTTC(),t.getNbGrainesParSachet(),t.getTomatesApparentées(),Tomato.this);
                                    TomateRupturePage.setVisible(true);
                            	}
                                
                            }
                        }
        			
        		    
                
        				
                   
                }
            
        });
             
        panel_centre.setLayout(new BorderLayout()); 
        panel_centre.add(sp, BorderLayout.CENTER);
        
        for (Tomate t : liste) {
            modeleTable.addRow(new Object[] { t.getDésignation() });
        }
        
        
      
		
		
	}
	
	public void majRajouterPanier(Articles article) {
		panierClient.ajouterArticle(article);
		textField.setText(panierClient.getSousTotal()+"€");
	    
	    // ou rafraîchir une JTable ou autre
	    revalidate(); 
	    repaint();
	}
	
	public void majPanier() {
		textField.setText(panierClient.getSousTotal()+"€");
	    revalidate(); 
	    repaint();
	}

	


}
