package fr.boutique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PanierReductionTest {

    @Test
    void codeReductionInvalideLanceIllegalArgumentException() {
        Panier panier = new Panier();
        panier.ajouterArticle(new Article("A1", "Livre", 10.0), 1);

        assertThrows(IllegalArgumentException.class, () -> panier.appliquerCodeReduction(null));
        assertThrows(IllegalArgumentException.class, () -> panier.appliquerCodeReduction(""));
        assertThrows(IllegalArgumentException.class, () -> panier.appliquerCodeReduction("   "));
    }

    @Test
    void reduction20Applique20pourcent() {
        Panier panier = new Panier();
        panier.ajouterArticle(new Article("A1", "Livre", 10.0), 3);
        panier.appliquerCodeReduction("REDUC20");

        assertEquals(24.0, panier.calculerTotal(), 1e-9);
    }
}
