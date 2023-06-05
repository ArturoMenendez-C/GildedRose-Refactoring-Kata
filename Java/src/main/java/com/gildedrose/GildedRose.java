package com.gildedrose;

class GildedRose {
    private static final int MAX_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isSulfuras(item)) continue;
            item.sellIn = reduceSellIn(item);

            if (isSellInLessThanZero(item)) {
                if (isAgedBrie(item)) {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = increaseQuality(item);
                    }
                } else {
                    if (isBackstagePass(item)) {
                        item.quality = 0;
                    } else if (hasQuality(item) && !isSulfuras(item)) {
                        item.quality = reduceQuality(item);
                    }
                }
            }

            if (isBackstagePass(item)) {
                if (item.quality < MAX_QUALITY) {
                    item.quality = increaseQuality(item);
                    if (isBackstagePass(item)) updateBackstageQualityBasedOnDueDate(item);
                }
            } else {
                if (isAgedBrie(item)) {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = increaseQuality(item);
                        if (isBackstagePass(item)) updateBackstageQualityBasedOnDueDate(item);
                    }
                } else {
                    if (hasQuality(item) && !isSulfuras(item)) {
                        item.quality = reduceQuality(item);
                    }
                }
            }
        }
    }

    private static boolean isSellInLessThanZero(Item item) {
        return item.sellIn < 0;
    }

    private static int reduceSellIn(Item item) {
        return item.sellIn - 1;
    }

    private static int increaseQuality(Item item) {
        return item.quality + 1;
    }

    private static int reduceQuality(Item item) {
        return item.quality - 1;
    }

    private static boolean hasQuality(Item item) {
        return item.quality > 0;
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static void updateBackstageQualityBasedOnDueDate(Item item) {
        if (item.sellIn < 11 && item.quality < MAX_QUALITY) {
            item.quality = increaseQuality(item);
        }

        if (item.sellIn < 6 && item.quality < MAX_QUALITY) {
            item.quality = increaseQuality(item);
        }
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
