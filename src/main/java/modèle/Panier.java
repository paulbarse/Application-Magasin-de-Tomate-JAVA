package modèle;
import java.util.ArrayList;
import java.util.List;

public class Panier {
    private static final double FRAIS_EXPEDITION = 5.50;
    private List<Articles> articles;

    public Panier() {
        this.articles = new ArrayList<>();
    }

    public void ajouterArticle(Articles article) {
        Tomate tomate = article.getTomate();
        int quantite = article.getQuantité();
        for (Articles a : this.articles) {
            if (a.getTomate().equals(tomate)) {
                a.setQuantité(a.getQuantité() + quantite);
                tomate.setStock(tomate.getStock() - quantite);
                return;
            }
        }
        this.articles.add(article);
        tomate.setStock(tomate.getStock() - quantite);
    }

    public void retirerArticle(Articles article) {
        for (int i = 0; i < this.articles.size(); i++) {
            Articles a = this.articles.get(i);
            if (a.getTomate().equals(article.getTomate())) {
                this.articles.remove(i);
                return;
            }
        }
    }

    public List<Articles> getArticles() {
        return this.articles;
    }

    public float getSousTotal() {
        float sousTotal = 0;
        for (Articles a : this.articles) {
            sousTotal += a.getPrixTotal();
        }
        return sousTotal;
    }

    public double getTotal() {
        return this.getSousTotal() + Panier.FRAIS_EXPEDITION;
    }

    public int getNombreArticles() {
        int total = 0;
        for (Articles a : this.articles) {
            total += a.getQuantité();
        }
        return total;
    }

    public int getNombreReferences() {
        return this.articles.size();
    }

    public static double getFraisExpedition() {
        return Panier.FRAIS_EXPEDITION;
    }

    public void viderPanier() {
        for (Articles a : this.articles) {
            Tomate tomate = a.getTomate();
            int quantite = a.getQuantité();
            tomate.setStock(tomate.getStock() + quantite);
        }
        this.articles.clear();
    }
}