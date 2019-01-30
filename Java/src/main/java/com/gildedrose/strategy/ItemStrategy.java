package com.gildedrose.strategy;

public interface ItemStrategy {

    int MIN_QUALITY = 0;
    int MAX_QUALITY = 50;

    default int calculateQuality(int currentQuality, int currentSellIn) {
        return decreaseQuality(currentQuality, currentSellIn < 0 ? 2 : 1);
    }

    default int calculateSellIn(int currentSellIn) {
        return currentSellIn - 1;
    }

    default int increaseQuality(int currentQuality, int increase) {
        int quality = currentQuality + increase;
        return quality > MAX_QUALITY ? MAX_QUALITY : quality;
    }

    default int decreaseQuality(int currentQuality, int decrease) {
        int quality = currentQuality - decrease;
        return quality < MIN_QUALITY ? MIN_QUALITY : quality;
    }

}
