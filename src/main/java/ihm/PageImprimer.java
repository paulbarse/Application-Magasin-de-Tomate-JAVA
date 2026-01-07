package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class PageImprimer extends JDialog {

	public PageImprimer(Window parent) {
		super(parent, "Imprimer", ModalityType.APPLICATION_MODAL);

		Color mainGreen = new Color(73, 130, 91);

		// --- HEADER ---
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(mainGreen);
		JLabel logoLabel = new JLabel(
				new ImageIcon(new ImageIcon("/Users/joandiaz/Downloads/image-removebg-preview.png").getImage()
						.getScaledInstance(44, 44, Image.SCALE_SMOOTH)));
		JLabel titleLabel = new JLabel("Imprimer");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		topPanel.add(logoLabel);
		topPanel.add(titleLabel);

		// --- Onglets ---
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Général", this.buildGeneralTab());
		tabs.addTab("Mise en page", this.buildLayoutTab());
		tabs.addTab("Apparence", this.buildAppearanceTab());

		// --- Bas de page ---
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnImprimer = new JButton("Imprimer");
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(e -> this.dispose());
		buttonPanel.add(btnImprimer);
		buttonPanel.add(btnAnnuler);
		buttonPanel.setBackground(mainGreen);

		// -- Layout principal --
		JPanel content = new JPanel();
		content.setBackground(mainGreen);
		content.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		content.setLayout(new BorderLayout());
		content.add(topPanel, BorderLayout.NORTH);
		content.add(tabs, BorderLayout.CENTER);
		content.add(buttonPanel, BorderLayout.SOUTH);

		this.setContentPane(content);
		this.setMinimumSize(new Dimension(540, 420));
		this.setSize(700, 520);
		this.setLocationRelativeTo(parent);
	}

	private JPanel buildGeneralTab() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		// Service d'impression
		JPanel imprPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imprPanel.setBackground(Color.WHITE);
		imprPanel.setBorder(BorderFactory.createTitledBorder("Service d'impression"));
		imprPanel.add(new JLabel("Nom :"));
		imprPanel.add(new JComboBox<>(
				new String[] { "HPCDC0AC (HP Officejet 5740 series)", "PDF Writer", "Autre imprimante..." }));
		imprPanel.add(new JButton("Propriétés..."));
		imprPanel.add(Box.createHorizontalStrut(16));
		JCheckBox checkFile = new JCheckBox("Imprimer dans un fichier");
		checkFile.setBackground(Color.WHITE);
		imprPanel.add(checkFile);
		p.add(imprPanel);

		// Plage d'impression
		JPanel rangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rangePanel.setBackground(Color.WHITE);
		rangePanel.setBorder(BorderFactory.createTitledBorder("Plage d'impression"));
		JRadioButton rbTout = new JRadioButton("Tout", true);
		JRadioButton rbPages = new JRadioButton("Pages");
		rbTout.setBackground(Color.WHITE);
		rbPages.setBackground(Color.WHITE);
		ButtonGroup grp = new ButtonGroup();
		grp.add(rbTout);
		grp.add(rbPages);
		JTextField from = new JTextField("1", 3);
		JTextField to = new JTextField("1", 3);
		rangePanel.add(rbTout);
		rangePanel.add(rbPages);
		rangePanel.add(new JLabel("de"));
		rangePanel.add(from);
		rangePanel.add(new JLabel("à"));
		rangePanel.add(to);
		p.add(rangePanel);

		// Copies
		JPanel copiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		copiesPanel.setBackground(Color.WHITE);
		copiesPanel.setBorder(BorderFactory.createTitledBorder("Copies"));
		copiesPanel.add(new JLabel("Nombre de copies :"));
		copiesPanel.add(new JSpinner(new SpinnerNumberModel(1, 1, 999, 1)));
		JCheckBox checkColl = new JCheckBox("Collationner", true);
		checkColl.setBackground(Color.WHITE);
		copiesPanel.add(checkColl);
		p.add(copiesPanel);

		return p;
	}

	private JPanel buildLayoutTab() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		// Orientation
		JPanel orientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		orientPanel.setBackground(Color.WHITE);
		orientPanel.add(new JLabel("Orientation :"));
		JRadioButton rbPortrait = new JRadioButton("Portrait", true);
		JRadioButton rbPaysage = new JRadioButton("Paysage");
		rbPortrait.setBackground(Color.WHITE);
		rbPaysage.setBackground(Color.WHITE);
		ButtonGroup grp = new ButtonGroup();
		grp.add(rbPortrait);
		grp.add(rbPaysage);
		orientPanel.add(rbPortrait);
		orientPanel.add(rbPaysage);
		p.add(orientPanel);

		// Format papier
		JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		formatPanel.setBackground(Color.WHITE);
		formatPanel.add(new JLabel("Format du papier :"));
		formatPanel.add(new JComboBox<>(new String[] { "A4", "A3", "Lettre", "Légal" }));
		p.add(formatPanel);

		// Marges
		JPanel marginsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		marginsPanel.setBackground(Color.WHITE);
		marginsPanel.add(new JLabel("Marges (mm) :"));
		marginsPanel.add(new JLabel("Haut"));
		marginsPanel.add(new JTextField("10", 2));
		marginsPanel.add(new JLabel("Bas"));
		marginsPanel.add(new JTextField("10", 2));
		marginsPanel.add(new JLabel("Gauche"));
		marginsPanel.add(new JTextField("10", 2));
		marginsPanel.add(new JLabel("Droite"));
		marginsPanel.add(new JTextField("10", 2));
		p.add(marginsPanel);

		return p;
	}

	private JPanel buildAppearanceTab() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		// Qualité
		JPanel qualPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		qualPanel.setBackground(Color.WHITE);
		qualPanel.add(new JLabel("Qualité d'impression :"));
		qualPanel.add(new JComboBox<>(new String[] { "Brouillon", "Normale", "Haute" }));
		p.add(qualPanel);

		// Mode Couleur
		JPanel colPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		colPanel.setBackground(Color.WHITE);
		colPanel.add(new JLabel("Mode d'impression :"));
		JRadioButton rbCouleur = new JRadioButton("Couleur", true);
		JRadioButton rbNB = new JRadioButton("Niveaux de gris");
		rbCouleur.setBackground(Color.WHITE);
		rbNB.setBackground(Color.WHITE);
		ButtonGroup grp = new ButtonGroup();
		grp.add(rbCouleur);
		grp.add(rbNB);
		colPanel.add(rbCouleur);
		colPanel.add(rbNB);
		p.add(colPanel);

		// Recto-verso
		JPanel duplexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		duplexPanel.setBackground(Color.WHITE);
		duplexPanel.add(new JLabel("Recto-verso :"));
		JCheckBox checkDuplex = new JCheckBox("Activer");
		checkDuplex.setBackground(Color.WHITE);
		duplexPanel.add(checkDuplex);
		p.add(duplexPanel);

		return p;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new PageImprimer(null).setVisible(true));
	}
}