package com.gildedrose.strategy;

public class FixedQualityStrategy implements ItemStrategy {

    private final int fixedQuality;

    public FixedQualityStrategy(int fixedQuality) {
        this.fixedQuality = fixedQuality;
    }

    @Override
    public int calculateQuality(int currentQuality, int currentSellIn) {
        return fixedQuality;
    }

    @Override
    public int calculateSellIn(int currentSellIn) {
        return currentSellIn;
    }

}
