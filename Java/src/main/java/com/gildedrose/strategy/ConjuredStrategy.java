package com.gildedrose.strategy;

public class ConjuredStrategy implements ItemStrategy {

    @Override
    public int calculateQuality(int currentQuality, int currentSellIn) {
        return decreaseQuality(currentQuality, currentSellIn < 0 ? 4 : 2);
    }

}
