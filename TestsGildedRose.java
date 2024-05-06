package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    private GildedRose app;
    private Item[] items;

    @Test
    @DisplayName("La calidad de Sulfuras no cambia")
    void sulfurasQualityConstant() {
        items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 5, 80)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, items[0].quality, "La calidad de Sulfuras debe permanecer en 80");
    }

    @Test
    @DisplayName("La calidad no cae debajo de cero")
    void qualityNeverNegative() {
        items = new Item[]{new Item("Normal Item", 0, 0)};
        app = new GildedRose(items);
        app.updateQuality();
        assertTrue(items[0].quality >= 0, "La calidad debe ser al menos 0");
    }

    @Test
    @DisplayName("Aged Brie incrementa la calidad a medida que envejece")
    void agedBrieIncreasesQuality() {
        items = new Item[]{new Item("Aged Brie", 2, 0)};
        app = new GildedRose(items);
        app.updateQuality();
        assertTrue(items[0].quality > 0, "Aged Brie incrementa la calidad con el tiempo");
    }

    @Test
    @DisplayName("La calidad de Aged Brie nunca supera 50")
    void agedBrieMaxQuality() {
        items = new Item[]{new Item("Aged Brie", 2, 49)};
        app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(50, items[0].quality, "La calidad de Aged Brie debe ser máximo 50");
    }

    @Test
    @DisplayName("Backstage passes aumenta la calidad a medida que se acerca la fecha del concierto")
    void backstagePassesIncreasesQualityAsConcertApproaches() {
        items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 12, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, items[0].quality);
        app.updateQuality(); // Día 10
        assertEquals(22, items[0].quality);
    }

    @Test
    @DisplayName("Backstage passes aumenta la calidad por 3 cuando quedan 5 días o menos")
    void backstagePassesIncreaseByThreeWhenFiveDaysOrLess() {
        items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, items[0].quality, "La calidad debería aumentar por 3 cuando quedan 5 días o menos");
    }

    @Test
    @DisplayName("Backstage passes se vuelve cero después del concierto")
    void backstagePassesDropsToZeroAfterConcert() {
        items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality, "La calidad debe ser 0 después del concierto");
    }

    @Test
    @DisplayName("Productos normales degradan la calidad el doble de rápido después de la fecha de venta")
    void normalItemsDegradeTwiceAsFastAfterSellBy() {
        items = new Item[]{new Item("Normal Item", 0, 10)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
    }

    @Test
    @DisplayName("Conjured items degradan calidad dos veces más rápido")
    void conjuredItemsDegradeTwiceAsFast() {
        items = new Item[]{new Item("Conjured Mana Cake", 3, 6)};
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, items[0].quality, "Conjured items deberían degradar su calidad más rápido");
    }
}
