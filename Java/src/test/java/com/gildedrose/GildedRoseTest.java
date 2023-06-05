package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void golden_master_test(){
        String output = TexttestFixture.executeFixture(new String[]{});
        Approvals.verify(output);
    }

    @Test
    void backstage_quality_should_increase_by_two_when_sellIn_lower_than_eleven_and_greater_than_six() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }
    @Test
    void backstage_quality_should_increase_by_three_when_sellIn_lower_than_eleven_and_lower_than_six() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }
    @Test
    void backstage_quality_should_increase_by_two_when_sellIn_lower_than_eleven_and_greater_than_six_and_quality_lower_than_50() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
    void backstage_quality_should_increase_by_three_when_sellIn_lower_than_eleven_and_lower_than_six_and_quality_lower_than_50() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
    void Aged_brie_quality_should_increase_by_one_when_sellIn_higher_than_zero() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
    void elixir_of_the_Mongoose_quality_should_decrease_by_1() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
    }
    @Test
    void Hand_of_Sulfuras_never_reduces_sellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].sellIn);
    }
    @Test
    void Hand_of_Sulfuras_never_reduces_quality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(7, app.items[0].quality);
    }
    @Test
    void aged_brie_reduces_sellIn_when_a_day_passes() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
    }
    @Test
    void backstage_reduces_sellIn__when_a_day_passes() {
        Item[] items = new Item[] { new Item("backstage", 5, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
    }
    @Test
    void elixir_of_the_Mongoose_sellIn_should_decrease_by_1() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
    }
    @Test
    void aged_brie_quality_less_than_max_quality_should_increase_by_1() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
    void elixir_of_the_Mongoose_quality_should_not_be_less_than_zero() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
    @Test
    void aged_brie_quality_should_not_be_more_than_50() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }



}
