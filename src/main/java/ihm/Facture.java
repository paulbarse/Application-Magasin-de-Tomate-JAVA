package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import modèle.Articles;
import modèle.Client;
import modèle.Panier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Facture extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
 // Fournis un panier vide ou de test
	            
	            Facture dialog = new Facture(null,null, null);
	            dialog.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	/**
	 * Create the frame.
	 */
	public Facture(Frame parent,Panier panierClient, Client client) {
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true); // IMPORTANT : rend la fenêtre interactive et prioritaire

		setBounds(100, 100, 611, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_haut = new JPanel();
		panel_haut.setBackground(new Color(72, 130, 91));
		contentPane.add(panel_haut, BorderLayout.NORTH);
		
		JLabel PhotoFacture = new JLabel("");
		PhotoFacture.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\pigea\\Desktop\\WB\\facture.png").getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH)));
		panel_haut.add(PhotoFacture);
		
		JLabel VotreFacture = new JLabel("Votre Facture");
		VotreFacture.setFont(new Font("Viner Hand ITC", Font.BOLD, 23));
		panel_haut.add(VotreFacture);
		
		JLabel PhotoTomate = new JLabel("\r\n");
		PhotoTomate.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\pigea\\Desktop\\WB\\tomato.png").getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH)));
		panel_haut.add(PhotoTomate);
		
		JPanel panel_Mid = new JPanel();
		contentPane.add(panel_Mid, BorderLayout.CENTER);
		panel_Mid.setLayout(new BoxLayout(panel_Mid, BoxLayout.Y_AXIS));
		
		JPanel panel_Merci = new JPanel();
		panel_Merci.setMaximumSize(new Dimension(32767, 5000));
		panel_Merci.setLayout(new BorderLayout());
		panel_Merci.setPreferredSize(new Dimension(100, 20));
		JLabel TexteMerci = new JLabel("Merci de votre visite !");
		TexteMerci.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_Merci.add(TexteMerci);
	
		
		
		panel_Merci.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(new Color(0, 128, 0))));
		
		panel_Mid.add(panel_Merci);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_Mid.add(scrollPane);
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(panelContent);
		
		JLabel lblTitre = new JLabel("Votre facture", JLabel.CENTER);
		lblTitre.setFont(new Font("SansSerif", Font.BOLD, 24));
		panelContent.add(lblTitre);
		
		JLabel lblClient = new JLabel("<html><b>"+client.getPrenom()+" "+client.getNom()+"</b><br>"+client.getAdresse1()+"<br>"+client.getCodePostal()+"<br>Tél : "+client.getTelephone()+"<br>Mél : "+client.getMail()+"</html>");
		panelContent.add(lblClient);
		
		
		
		DefaultTableModel modeleTable = new DefaultTableModel(new Object[]{"Produit","Prix unitaire","Quantité","Prix TTC"}, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; 
		    }
		};
		
		for (Articles a : panierClient.getArticles()) {
			modeleTable.addRow(new Object[] { a.getDésignation(),a.getPrixUnitaire(),a.getQuantité(),a.getPrixTotal() });
		}
		
		JTable table = new JTable(modeleTable);
		table.setEnabled(false); // Pour lecture seule
		panelContent.add(table);
		
		JLabel lblPaiement = new JLabel("<html><b>TOTAL TTC COMMANDE: "+panierClient.getSousTotal()+"</b><br>FORFAIT FRAIS DE PORT : "+panierClient.getFraisExpedition()+"<br>PRIX TOTAL TTC"+panierClient.getTotal()+"<br>PAYE PAR "+client.getMoyenPaiement()+" </html>");
		panelContent.add(lblPaiement);
	
		JPanel panel_total = new JPanel();
		panel_total.setLayout(new BoxLayout(panel_total, BoxLayout.Y_AXIS));
		panel_total.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		
		JPanel panelTotalRow = new JPanel(new BorderLayout());
		panelTotalRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

		panel_total.add(panelTotalRow);

		
		JPanel panelFraisRow = new JPanel(new BorderLayout());
		panelFraisRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		panel_total.add(panelFraisRow);

		
		JPanel panel_bas = new JPanel(new BorderLayout());
		panel_bas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		panel_bas.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		
		panel_bas.add(panel_total, BorderLayout.WEST);
		panel_Mid.add(panel_bas);
		
		
		JPanel panel_bouton = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));
		
		panel_bas.add(panel_bouton, BorderLayout.CENTER);

		
		JButton Imprimer = new JButton("Imprimer");
		Imprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageImprimer impr = new PageImprimer(parent);
				impr.setVisible(true);
			}
		});
		Imprimer.setPreferredSize(new Dimension(100, 30));  
		Imprimer.setFont(new Font("Verdana", Font.PLAIN, 13));
		panel_bouton.add(Imprimer);

		
		JButton Quitter = new JButton("Quitter");
		Quitter.setPreferredSize(new Dimension(100, 30));
		Quitter.setFont(new Font("Verdana", Font.PLAIN, 13));
		panel_bouton.add(Quitter);
		
		
		

		


		
        
	}

}
