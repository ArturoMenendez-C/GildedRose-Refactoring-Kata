package com.gildedrose;

class GildedRose {
    private static final int MAX_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isAgedBrie(item)
                && !isBackstagePass(item)) {
                if (item.quality > 0) {
                    if (!isSulfuras(item)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality = item.quality + 1;

                    if (isBackstagePass(item)) updateBackstageQualityBasedOnDueDate(item);
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackstagePass(item)) {
                        if (item.quality > 0) {
                            if (!isSulfuras(item)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static void updateBackstageQualityBasedOnDueDate(Item item) {
        if (item.sellIn < 11 && item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }

        if (item.sellIn < 6 && item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
