package com.gildedrose.strategy;

public class BackstageStrategy implements ItemStrategy {

    @Override
    public int calculateQuality(int currentQuality, int currentSellIn) {
        if (currentSellIn < 0) {
            return 0;
        } else if (currentSellIn < 6) {
            return increaseQuality(currentQuality, 3);
        } else if (currentSellIn < 11) {
            return increaseQuality(currentQuality, 2);
        } else {
            return increaseQuality(currentQuality, 1);
        }
    }

}
