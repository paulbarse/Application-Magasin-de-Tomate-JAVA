package ihm;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class FenetreAlerte extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreAlerte dialog = new FenetreAlerte(null);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public FenetreAlerte(Frame parent) {
		super();
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setSize(341, 165);
	    setLocationRelativeTo(null); // centre la fenêtre

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(new BorderLayout(0, 0));
	    
	    JPanel panelPhoto = new JPanel(new GridBagLayout());
	    contentPane.add(panelPhoto, BorderLayout.WEST);

	    JLabel Photo = new JLabel("");
	    Photo.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
	    Photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    Photo.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\pigea\\Desktop\\WB\\exclamation.png").getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
	    GridBagConstraints gbc_Photo = new GridBagConstraints();
	    gbc_Photo.gridy = 0;
	    panelPhoto.add(Photo, gbc_Photo);
	    
	    JPanel panelPanierVide = new JPanel(new BorderLayout());
	    contentPane.add(panelPanierVide, BorderLayout.CENTER);

	    JLabel JlabelPanierVide = new JLabel("Votre panier est vide ");
	    JlabelPanierVide.setFont(new Font("Verdana", Font.BOLD, 15));
	    JlabelPanierVide.setHorizontalAlignment(SwingConstants.CENTER);
	    JlabelPanierVide.setVerticalAlignment(SwingConstants.CENTER);

	    panelPanierVide.add(JlabelPanierVide, BorderLayout.CENTER);
	    
	    JPanel panelBoutonOk = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
	    contentPane.add(panelBoutonOk, BorderLayout.SOUTH);

	    JButton boutonOk = new JButton("OK");
	    boutonOk.addActionListener(e -> dispose());
	    panelBoutonOk.add(boutonOk, BorderLayout.CENTER);



	    
	}


}