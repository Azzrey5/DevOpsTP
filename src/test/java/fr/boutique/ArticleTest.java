package fr.boutique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest {

    @Test
    void constructeurInitialiseLesChamps() {
        Article article = new Article("A1", "Livre", 12.5);

        assertEquals("A1", article.getReference());
        assertEquals("Livre", article.getNom());
        assertEquals(12.5, article.getPrix(), 1e-9);
    }

    @Test
    void setPrixNegatifLanceUneException() {
        Article article = new Article("A1", "Livre", 12.5);

        assertThrows(IllegalArgumentException.class, () -> article.setPrix(-1.0));
    }

    @Test
    void constructeurAvecArgumentsInvalidesLance() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Article(null, "Livre", 10.0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Article("A1", "", 10.0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Article("A1", "Livre", -1.0))
        );
    }
}
