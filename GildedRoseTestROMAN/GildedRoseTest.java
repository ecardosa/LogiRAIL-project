package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertAll;

class GildedRoseTest {

    @Test @DisplayName(value= "Positive Quality and SellIn")
    void CP1() {
        Item[] items = new Item[] { new Item("Articulo normal", 2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("properties",
            () -> assertEquals("Nombre 1", items[0].name, "Nombre del producto"),
            () -> assertEquals(1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(0, items[0].quality, "Calidad final")
        );
    }
    
    @Test  @DisplayName(value= "CP2 name")
    void CP2() {
        Item[] items = new Item[] { new Item("Articulo normal", 3, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("properties",
            () -> assertEquals("Nombre 2", items[0].name, "Nombre del producto"),
            () -> assertEquals(2, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(2, items[0].quality, "Calidad final")
        );
    }
    
    @Test  @DisplayName(value= "CP3 name")
    void CP3() {
        Item[] items = new Item[] { new Item("Articulo normal", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("properties",
            () -> assertEquals("Nombre 3", items[0].name, "Nombre del producto"),
            () -> assertEquals(-1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(2, items[0].quality, "Calidad final")
        );
    }
    
    @Test  @DisplayName(value= "CP5 name")
    void CP5() {
        Item[] items = new Item[] { new Item("Articulo normal", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll("properties",
            () -> assertEquals("Nombre 3", items[0].name, "Nombre del producto"),
            () -> assertEquals(-1, items[0].sellIn, "Fecha recomendada"),
            () -> assertEquals(2, items[0].quality, "Calidad final")
        );
    }
    
    @Test
    @DisplayName("Prueba de Sulfuras")
    void sulfurasQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Mano de Ragnaros", 11, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Mano de Ragnaros", items[0].name, "El nombre debería permanecer igual");
        assertEquals(11, items[0].sellIn, "SellIn debería permanecer igual");
        assertEquals(80, items[0].quality, "La calidad debería permanecer igual");
    }

    @Test
    @DisplayName("Prueba de Queso Brie envejecido")
    void agedBrieQuality() {
        Item[] items = new Item[] { new Item("Queso Brie envejecido", 10, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Queso Brie envejecido", items[0].name, "El nombre debería permanecer igual");
        assertEquals(3, items[0].sellIn, "SellIn debería disminuir en 1");
        assertEquals(50, items[0].quality, "La calidad debería aumentar en 1");
    }


    
    
    
}
