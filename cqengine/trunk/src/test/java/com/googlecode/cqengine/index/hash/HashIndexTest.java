package com.googlecode.cqengine.index.hash;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.index.common.MapBasedIndex;
import com.googlecode.cqengine.index.navigable.NavigableIndex;
import com.googlecode.cqengine.testutil.Car;
import com.googlecode.cqengine.testutil.CarFactory;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

import static com.googlecode.cqengine.testutil.TestUtil.setOf;

/**
 * Created by niall.gallagher on 09/01/2015.
 */
public class HashIndexTest {

    @Test
    public void testGetDistinctKeysAndCounts() {
        IndexedCollection<Car> collection = new ConcurrentIndexedCollection<Car>();
        MapBasedIndex<String, Car> MODEL_INDEX = HashIndex.onAttribute(Car.MODEL);
        collection.addIndex(MODEL_INDEX);

        collection.addAll(CarFactory.createCollectionOfCars(20));

        Set<String> distinctModels = MODEL_INDEX.getDistinctKeys();
        Assert.assertEquals(setOf("Accord", "Avensis", "Civic", "Focus", "Fusion", "Hilux", "Insight", "M6", "Prius", "Taurus"), distinctModels);
        for (String model : distinctModels) {
            Assert.assertEquals(Integer.valueOf(2), MODEL_INDEX.getCountForKey(model));
        }
    }
}