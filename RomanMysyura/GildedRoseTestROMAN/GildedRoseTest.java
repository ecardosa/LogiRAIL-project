package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertAll;

class GildedRoseTest {

    @Test
    @DisplayName(value= "La calidad y SellIn disminuyen en 1 para un artículo normal antes de la fecha de venta")
    void normalItemBeforeSellDate() {
        Item[] items = new Item[] { new Item("Articulo normal", 2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Articulo normal", items[0].name, "Nombre del producto"),
            () -> assertEquals(1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(0, items[0].quality, "Calidad final")
        );
    }
    
    @Test
    @DisplayName(value= "La calidad y SellIn disminuyen en 2 para un artículo normal después de la fecha de venta")
    void normalItemAfterSellDate() {
        Item[] items = new Item[] { new Item("Articulo normal", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Articulo normal", items[0].name, "Nombre del producto"),
            () -> assertEquals(-1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(2, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad nunca es negativa")
    void qualityNeverNegative() {
        Item[] items = new Item[] { new Item("Articulo normal", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality, "Calidad final");
    }

    @Test
    @DisplayName(value= "La calidad aumenta en 1 para Queso Brie antes de la fecha de venta")
    void agedBrieBeforeSellDate() {
        Item[] items = new Item[] { new Item("Queso Brie envejecido", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Queso Brie envejecido", items[0].name, "Nombre del producto"),
            () -> assertEquals(1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(1, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad aumenta en 2 para Queso Brie después de la fecha de venta")
    void agedBrieAfterSellDate() {
        Item[] items = new Item[] { new Item("Queso Brie envejecido", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Queso Brie envejecido", items[0].name, "Nombre del producto"),
            () -> assertEquals(-1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(2, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad nunca supera 50")
    void qualityNeverExceeds50() {
        Item[] items = new Item[] { new Item("Queso Brie envejecido", 2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality, "Calidad final");
    }

    @Test
    @DisplayName(value= "Las propiedades de Sulfuras permanecen sin cambios")
    void sulfurasPropertiesUnchanged() {
        Item[] items = new Item[] { new Item("Sulfuras", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Sulfuras", items[0].name, "Nombre del producto"),
            () -> assertEquals(0, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(80, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad disminuye en 2 para Entradas al Backstage cuando SellIn > 10")
    void backstagePassesBeforeSellIn10() {
        Item[] items = new Item[] { new Item("Entrada al Backstage", 15, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Entrada al Backstage", items[0].name, "Nombre del producto"),
            () -> assertEquals(14, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(21, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad aumenta en 2 para Entradas al Backstage cuando SellIn <= 10 y > 5")
    void backstagePassesWhenSellIn5to10() {
        Item[] items = new Item[] { new Item("Entrada al Backstage", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Entrada al Backstage", items[0].name, "Nombre del producto"),
            () -> assertEquals(9, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(22, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad aumenta en 3 para Entradas al Backstage cuando SellIn <= 5 y > 0")
    void backstagePassesWhenSellIn0to5() {
        Item[] items = new Item[] { new Item("Entrada al Backstage", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Entrada al Backstage", items[0].name, "Nombre del producto"),
            () -> assertEquals(4, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(23, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad cae a 0 para Entradas al Backstage después del concierto")
    void backstagePassesAfterConcert() {
        Item[] items = new Item[] { new Item("Entrada al Backstage", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Entrada al Backstage", items[0].name, "Nombre del producto"),
            () -> assertEquals(-1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(0, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad disminuye en 2 para los artículos Conjured antes de la fecha de venta")
    void conjuredItemsBeforeSellDate() {
        Item[] items = new Item[] { new Item("Pastel de maná Conjured", 3, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Pastel de maná Conjured", items[0].name, "Nombre del producto"),
            () -> assertEquals(2, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(4, items[0].quality, "Calidad final")
        );
    }

    @Test
    @DisplayName(value= "La calidad disminuye en 4 para los artículos Conjured después de la fecha de venta")
    void conjuredItemsAfterSellDate() {
        Item[] items = new Item[] { new Item("Pastel de maná Conjured", 0, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("propiedades",
            () -> assertEquals("Pastel de maná Conjured", items[0].name, "Nombre del producto"),
            () -> assertEquals(-1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(2, items[0].quality, "Calidad final")
        );
    }
}
