package fr.boutique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceCommandeTest {

    private static final DepotStock STOCK_VIDE = reference -> 0;

    @Test
    void panierVideLanceIllegalStateException() {
        ServiceCommande service = new ServiceCommande(STOCK_VIDE);
        assertThrows(IllegalStateException.class, () -> service.passerCommande(new Panier(), "client1"));
    }

    @Test
    void identifiantClientInvalideLanceIllegalArgumentException() {
        ServiceCommande service = new ServiceCommande(STOCK_VIDE);
        Panier panier = new Panier();
        panier.ajouterArticle(new Article("A1", "Livre", 10.0), 1);

        assertThrows(IllegalArgumentException.class, () -> service.passerCommande(panier, ""));
        assertThrows(IllegalArgumentException.class, () -> service.passerCommande(panier, null));
    }

    @Test
    void stockInsuffisantLanceStockInsuffisantException() {
        DepotStock stock = reference -> 0;
        ServiceCommande service = new ServiceCommande(stock);

        Panier panier = new Panier();
        panier.ajouterArticle(new Article("A1", "Livre", 10.0), 2);

        assertThrows(StockInsuffisantException.class, () -> service.passerCommande(panier, "client1"));
    }

    @Test
    void passeCommandeRetourneCommandeAvecTotalCorrect() {
        DepotStock stock = reference -> 10;
        ServiceCommande service = new ServiceCommande(stock);

        Panier panier = new Panier();
        panier.ajouterArticle(new Article("A1", "Livre", 10.0), 2);
        panier.ajouterArticle(new Article("A2", "Stylo", 2.0), 1);

        Commande commande = service.passerCommande(panier, "client-123");

        assertEquals("client-123", commande.identifiantClient());
        assertEquals(22.0, commande.total(), 1e-9);
        assertNotNull(commande.dateCreation());
    }
}
