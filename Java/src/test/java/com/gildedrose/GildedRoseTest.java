package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.stream.IntStream;

public class GildedRoseTest {

    private Item vest = new Item("+5 Dexterity Vest", 10, 20);
    private Item brie = new Item("Aged Brie", 2, 0);
    private Item cake = new Item("Conjured Mana Cake", 3, 6);
    private Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
    private Item backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);

    @Test
    public void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void testVest_increaseOneDay() {
        GildedRose app = new GildedRose(new Item[]{vest});

        app.updateQuality();

        assertItemEquals(new Item("+5 Dexterity Vest", 9, 19), vest);
    }

    @Test
    public void testVest_increaseTenDays() {
        GildedRose app = new GildedRose(new Item[]{vest});

        IntStream.rangeClosed(1, 10).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("+5 Dexterity Vest", 0, 10), vest);
    }

    @Test
    public void testVest_increaseElevenDays() {
        GildedRose app = new GildedRose(new Item[]{vest});

        IntStream.rangeClosed(1, 11).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("+5 Dexterity Vest", -1, 8), vest);
    }

    @Test // Once the sell by date has passed, Quality degrades twice as fast
    public void testOnceSellDateHasPassedItemDecreasesTwiceAsFast() {
        GildedRose app = new GildedRose(new Item[]{vest});

        IntStream.rangeClosed(1, 12).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("+5 Dexterity Vest", -2, 6), vest);
    }

    @Test // The Quality of an item is never negative
    public void testItemQualityIsNeverNegative() {
        GildedRose app = new GildedRose(new Item[]{vest});

        IntStream.rangeClosed(1, 20).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("+5 Dexterity Vest", -10, 0), vest);
    }

    @Test // The Quality of an item is never more than 50
    public void testItemQualityNeverExceedsMaxValue() {
        GildedRose app = new GildedRose(new Item[]{brie});

        IntStream.rangeClosed(1, 30).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("Aged Brie", -28, 50), brie);
    }

    @Test // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    public void testSulfurasDoesNotDecreaseInQuality() {
        GildedRose app = new GildedRose(new Item[]{sulfuras});

        IntStream.rangeClosed(1, 10).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("Sulfuras, Hand of Ragnaros", 0, 80), sulfuras);
    }

    @Test // "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    public void testBackStagePassesIncreaseInQuality() {
        GildedRose app = new GildedRose(new Item[]{backstage});

        IntStream.rangeClosed(1, 5).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 26), backstage);

        IntStream.rangeClosed(1, 5).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 37), backstage);

        app.updateQuality();

        assertItemEquals(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 40), backstage);
    }

    @Test // "Backstage passes", Quality drops to 0 after the concert
    public void testBackStagePassesQualityZeroWhenSellInPasses() {
        GildedRose app = new GildedRose(new Item[]{backstage});

        IntStream.rangeClosed(1, 16).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0), backstage);
    }

    @Test
    public void testBrieIncreasesInQuality() {
        GildedRose app = new GildedRose(new Item[]{brie});

        IntStream.rangeClosed(1, 5).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("Aged Brie", -3, 8), brie);
    }

    @Test // "Conjured" items degrade in Quality twice as fast as normal items
    public void testConjuredDegradesTwiceAsFast() {
        GildedRose app = new GildedRose(new Item[]{cake});

        IntStream.rangeClosed(1, 2).forEach(i -> app.updateQuality());

        assertItemEquals(new Item("Conjured Mana Cake", 1, 2), cake);
    }

    private void assertItemEquals(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

}
