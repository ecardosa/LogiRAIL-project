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
    
    
}
