package com.gildedrose.strategy;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class FunctionalStrategy implements ItemStrategy {

    private IntBinaryOperator quality;
    private IntUnaryOperator sellIn;

    public FunctionalStrategy(IntBinaryOperator quality, IntUnaryOperator sellIn) {
        this.quality = quality;
        this.sellIn = sellIn;
    }

    @Override
    public int calculateQuality(int currentQuality, int currentSellIn) {
        return quality.applyAsInt(currentQuality, currentSellIn);
    }

    @Override
    public int calculateSellIn(int currentSellIn) {
        return sellIn.applyAsInt(currentSellIn);
    }

}
