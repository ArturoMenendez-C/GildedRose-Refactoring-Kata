package com.gildedrose;

class GildedRose {
    private static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;
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
                    item.quality = increaseQuality(item);
                } else {
                    if (isBackstagePass(item)) {
                        item.quality = MIN_QUALITY;
                    } else {
                        item.quality = reduceQuality(item);
                    }
                }
            }

            if (isBackstagePass(item)) {
                item.quality = increaseQuality(item);
                if (isBackstagePass(item)) updateBackstageQualityBasedOnDueDate(item);

            } else {
                if (isAgedBrie(item)) {
                    item.quality = increaseQuality(item);
                    if (isBackstagePass(item)) updateBackstageQualityBasedOnDueDate(item);

                } else {
                    item.quality = reduceQuality(item);

                }
            }
        }
    }

    private static boolean isSellInLessThanZero(Item item) {
        return item.sellIn < MIN_QUALITY;
    }

    private static int reduceSellIn(Item item) {
        return item.sellIn - 1;
    }

    private static int increaseQuality(Item item) {
        return Math.min(item.quality + 1, MAX_QUALITY);
    }

    private static int reduceQuality(Item item) {
        return Math.max(item.quality - 1, MIN_QUALITY);
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
