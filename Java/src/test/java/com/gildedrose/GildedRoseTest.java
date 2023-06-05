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

}
