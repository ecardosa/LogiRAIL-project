package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";

    @Test
    @DisplayName("Nombre del producto permanece inalterado después de actualizar la calidad")
    void testProductNameUnchangedAfterUpdate() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    
    @Test
    @DisplayName("Sulfuras siempre tiene calidad 80, independientemente de los días restantes")
    void testSulfurasQualityAlwaysEighty() {
        Item[] items = new Item[] { new Item(SULFURAS, 22, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("Propiedades Sulfuras",
            () -> assertEquals(SULFURAS, app.items[0].name),
            () -> assertEquals(22, app.items[0].sellIn),
            () -> assertEquals(80, app.items[0].quality)
        );
    }

    @Test
    @DisplayName("Calidad de Sulfuras no cambia incluso si se inicia con 79")
    void testSulfurasQualityUnchangedIfNotEighty() {
        Item[] items = new Item[] { new Item(SULFURAS, 22, 79) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("Propiedades Sulfuras con calidad incorrecta",
            () -> assertEquals(SULFURAS, app.items[0].name),
            () -> assertEquals(22, app.items[0].sellIn),
            () -> assertEquals(80, app.items[0].quality)
        );
    }

    @Test
    @DisplayName("Aged Brie incrementa en calidad a medida que pasa el tiempo")
    void testAgedBrieIncreasesInQuality() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 7, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("Propiedades Aged Brie",
            () -> assertEquals(AGED_BRIE, app.items[0].name),
            () -> assertEquals(6, app.items[0].sellIn),
            () -> assertEquals(50, app.items[0].quality)
        );
    }

    @Test
    @DisplayName("Verificar decremento correcto en la calidad y días de venta")
    void testStandardProductQualityDecrease() {
        Item[] items = new Item[] { new Item("Standard Product", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("Decremento en Standard Product",
            () -> assertEquals("Standard Product", app.items[0].name),
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(19, app.items[0].quality)
        );
    }
}