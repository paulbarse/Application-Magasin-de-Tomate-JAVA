package modèle;
import java.time.LocalDateTime;

public class Commande {
    private Client client;
    private Panier panier;
    private LocalDateTime dateCommande;

    public Commande(Client client, Panier panier) {
        this.client = client;
        this.panier = panier;
        this.dateCommande = LocalDateTime.now();
    }

    public Client getClient() {
        return this.client;
    }

    public Panier getPanier() {
        return this.panier;
    }

    public LocalDateTime getDateCommande() {
        return this.dateCommande;
    }

    // Récupère les frais de port depuis le panier
    public double getFraisDePort() {
        return this.panier.getFraisExpedition();
    }

    // Récupère le moyen de paiement depuis le client
    public String getMoyenPaiement() {
        return this.client.getMoyenPaiement();
    }

    // Total TTC des articles (hors frais de port)
    public double getTotalTTCCommande() {
        return this.panier.getSousTotal();
    }

    // Total TTC avec frais de port
    public double getPrixTotalTTC() {
        return this.getTotalTTCCommande() + this.getFraisDePort();
    }
}