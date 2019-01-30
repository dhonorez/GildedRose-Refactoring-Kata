package com.gildedrose;

import com.gildedrose.strategy.*;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

class StrategySelector {

    private static final ItemStrategy DEFAULT_STRATEGY = new DefaultStrategy();
    private static final ItemStrategy BRIE_STRATEGY = new BrieStrategy();
    private static final ItemStrategy BACKSTAGE_STRATEGY = new BackstageStrategy();
    private static final ItemStrategy CONJURED_STRATEGY = new ConjuredStrategy();

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String BRIE = "Aged Brie";
    private static final String CONJURED = "Conjured";

    static ItemStrategy getStrategy(String itemname) {
        if (itemname.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            return new FunctionalStrategy(fixedQuality, dontSell);
        } else if (itemname.equals(BRIE)) {
            return BRIE_STRATEGY;
        } else if (itemname.equals(BACKSTAGE_PASSES)) {
            return BACKSTAGE_STRATEGY;
        } else if (itemname.startsWith(CONJURED)) {
            return CONJURED_STRATEGY;
        } else {
            return DEFAULT_STRATEGY;
        }
    }

    /**
     * Depending on the volatility of the strategies and the need to start combining them, I would choose the functional stategy.
     */
    private static IntBinaryOperator fixedQuality = (quality, sellIn) -> 80;
    private static IntUnaryOperator dontSell = sellIn -> sellIn;

}
