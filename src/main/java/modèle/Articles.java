package modèle;

public class Articles {

    private Tomate tomate;
    private int quantité;

    public Articles(Tomate tomate, int quantité) {
        this.tomate = tomate;
        this.quantité = quantité;
    }

    public Tomate getTomate() {
        return this.tomate;
    }

    public int getQuantité() {
        return this.quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public String getDésignation() {
        return this.tomate.getDésignation();
    }

    public float getPrixUnitaire() {
        return this.tomate.getPrixTTC();
    }

    public float getPrixTotal() {
        return this.quantité * this.tomate.getPrixTTC();
    }
}