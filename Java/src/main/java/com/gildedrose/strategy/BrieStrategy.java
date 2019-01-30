package com.gildedrose.strategy;

public class BrieStrategy implements ItemStrategy {

    @Override
    public int calculateQuality(int currentQuality, int currentSellIn) {
        return increaseQuality(currentQuality, currentSellIn < 0 ? 2 : 1);
    }

}
