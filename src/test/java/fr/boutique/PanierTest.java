package fr.boutique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PanierTest {

    @Test
    void panierEstVideAuDepart() {
        Panier panier = new Panier();
        assertTrue(panier.estVide());
        assertEquals(0, panier.nombreArticles());
    }

    @Test
    void ajouterArticleAugmenteLeTotal() {
        Panier panier = new Panier();
        Article article = new Article("A1", "Livre", 10.0);

        panier.ajouterArticle(article, 2);

        assertFalse(panier.estVide());
        assertEquals(1, panier.nombreArticles());
        assertEquals(20.0, panier.calculerTotal(), 1e-9);
    }

    @Test
    void reductionREDUC10Applique10pourcent() {
        Panier panier = new Panier();
        Article article = new Article("A1", "Livre", 10.0);

        panier.ajouterArticle(article, 2);
        panier.appliquerCodeReduction("REDUC10");

        assertEquals(18.0, panier.calculerTotal(), 1e-9);
    }
}
