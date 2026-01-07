package ihm;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modèle.OutilsBaseDonneesTomates;
import modèle.Tomate;
import modèle.Tomates;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Description_Tomate_rupture extends JDialog {  // ← On hérite de JDialog

    private static final long serialVersionUID = 1L;
    private JPanel Page;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // On crée un JDialog avec null parent (dialogue indépendant)
                    Description_Tomate_rupture dialog = new Description_Tomate_rupture(null, "", "","",0,0,0,null,null);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Description_Tomate_rupture(Frame parent, String nomTo, String descTo, String nomImg, int stockTom, float prix, int graine, List<Tomate> tApparente,Tomato fenetre) {
        super(parent, "Description Tomate", true);  

        setSize(950, 600);
        setLocationRelativeTo(parent);  
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JLabel logo = new JLabel(new ImageIcon("logo.png")); 
        contentPane.add(logo, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        centerPanel.setOpaque(false);
        centerPanel.setBackground(Color.WHITE);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel nom = new JLabel(nomTo, SwingConstants.CENTER);
        nom.setFont(new Font("Serif", Font.BOLD, 26));
        nom.setOpaque(true);
        nom.setBackground(new Color(220, 220, 220));
        nom.setAlignmentX(Component.CENTER_ALIGNMENT);
        nom.setHorizontalAlignment(SwingConstants.CENTER);
        
        // inserer une image dans le fichier src
        JLabel imgTomate = new JLabel("");
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Tomates200x200/"+nomImg+".jpg"));
        Image image = icon.getImage();
        imgTomate.setIcon(new ImageIcon(image));
        imgTomate.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgTomate.setHorizontalAlignment(SwingConstants.CENTER);
        // inserer une image dans le fichier src
        
        JLabel stock = new JLabel("rupture");
        stock.setFont(new Font("SansSerif", Font.BOLD, 18));
        stock.setForeground(Color.BLACK);
        stock.setBackground(new Color(255, 0, 0));
        stock.setOpaque(true);
        stock.setBorder(new EmptyBorder(5, 20, 5, 20));
        stock.setHorizontalAlignment(SwingConstants.CENTER);
        stock.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(nom);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(imgTomate,BorderLayout.WEST);//puis ajouter dans le build
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(stock);

        centerPanel.add(leftPanel);
        
        Frame parentPage = (Frame) SwingUtilities.getWindowAncestor(this);
        JComboBox tomateApparente = new JComboBox();
        String cheminFichier = "src/main/resources/data/tomates.json";//
		Tomates baseTomates = OutilsBaseDonneesTomates.générationBaseDeTomates(cheminFichier); //liste c'est notre base de donnée avec toute les tomates
		List<Tomate> liste = baseTomates.getTomates();//
        tomateApparente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		for (Tomate t : liste) {
					if (tomateApparente.getSelectedItem().equals(t.getDésignation())) {					
						Description_Tomate TomatePage = new Description_Tomate(parentPage,t,t.getDésignation(), t.getDescription(),t.getNomImage(),t.getStock(),t.getPrixTTC(),t.getNbGrainesParSachet(),fenetre);
                        TomatePage.setVisible(true);
					}
				}
        	}
        });
        tomateApparente.setMaximumSize(new Dimension(400, 30));
        Set<String> tApparenteSet = new TreeSet<>();
        for (Tomate t : tApparente) {
        	tApparenteSet.add(t.getDésignation());
        }
        tomateApparente.setModel(new DefaultComboBoxModel<>(
			    tApparenteSet.toArray(new String[0])
			));
        leftPanel.add(tomateApparente);



        
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel descriptionLabel = new JLabel("Description", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Serif", Font.BOLD, 26));
        descriptionLabel.setOpaque(true);
        descriptionLabel.setBackground(new Color(220, 220, 220));
        descriptionLabel.setBorder(new EmptyBorder(10, 20, 10, 20));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea description = new JTextArea(5, 30);
        description.setText(descTo);
        description.setFont(new Font("SansSerif", Font.PLAIN, 16));
        description.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        infoPanel.setOpaque(false);
        infoPanel.setMaximumSize(new Dimension(400, 80));

        JLabel graines = new JLabel("Nombre de sachets");
        graines.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JLabel grainesNb = new JLabel("");
        grainesNb.setText(String.valueOf(stockTom));
        grainesNb.setHorizontalAlignment(JTextField.CENTER);
        grainesNb.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JPanel prixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        prixPanel.setOpaque(false);

        infoPanel.add(graines);
        infoPanel.add(grainesNb);
        infoPanel.add(prixPanel);

        JLabel montant = new JLabel(prix+"€");
        montant.setFont(new Font("SansSerif", Font.PLAIN, 18));
        prixPanel.add(montant);

        JSpinner spinner = new JSpinner();
        spinner.setEnabled(false);
        spinner.setPreferredSize(new Dimension(50, 30));
        prixPanel.add(spinner);

        rightPanel.add(descriptionLabel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(description);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(infoPanel);
        
        JLabel nbGraine = new JLabel("Graines par Sachet : " + graine);
        nbGraine.setFont(new Font("SansSerif", Font.PLAIN, 18));
        infoPanel.add(nbGraine);

        centerPanel.add(rightPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setOpaque(false);
        JButton ajouter = new JButton("Ajouter au panier");
        ajouter.setEnabled(false);
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });

        Font buttonFont = new Font("SansSerif", Font.PLAIN, 18);
        ajouter.setFont(buttonFont);
        annuler.setFont(buttonFont);
        ajouter.setPreferredSize(new Dimension(180, 40));
        annuler.setPreferredSize(new Dimension(140, 40));

        buttonPanel.add(ajouter);
        buttonPanel.add(annuler);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
}
