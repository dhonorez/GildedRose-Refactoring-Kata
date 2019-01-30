package com.gildedrose;

import com.gildedrose.strategy.DefaultStrategy;
import com.gildedrose.strategy.ItemStrategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultStrategyTest {

    private ItemStrategy underTest = new DefaultStrategy();

    @Test
    public void testSellInDecreasesByOneDay() {
        assertEquals(0, underTest.calculateSellIn(1));
    }

    @Test
    public void testQualityDecreasesByOneWhenEndDateNotReached() {
        assertEquals(0, underTest.calculateQuality(1, 1));
    }

    @Test
    public void testQualityDecreasesByTwoWhenEndDateReached() {
        assertEquals(0, underTest.calculateQuality(2, -1));
    }

    @Test
    public void testQualityDoesNotDropBelowZero() {
        assertEquals(0, underTest.calculateQuality(0, 0));
    }

}
