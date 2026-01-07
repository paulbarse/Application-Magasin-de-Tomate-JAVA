package modèle;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TestImage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Image");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);

            JLabel logo = new JLabel();
            logo.setHorizontalAlignment(SwingConstants.CENTER);

            URL imageURL = TestImage.class.getResource("/images/logo.jpg");
            if (imageURL != null) {
                ImageIcon icon = new ImageIcon(imageURL);
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                logo.setIcon(new ImageIcon(image));
            } else {
                logo.setText("Image non trouvée");
                System.err.println("Image non trouvée !");
            }

            frame.getContentPane().add(logo, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
