package edu.upc.caminstech.equipstic.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NullSafeTests {

    @Test
    public void testSorted() {
        List<String> elems = Arrays.asList("ccc", "bbb", "aaa");
        assertThat(NullSafe.sorted(elems), contains("aaa", "bbb", "ccc"));
    }

    @Test
    public void testSortedNullList() {
        assertThat(NullSafe.sorted(null), empty());
    }

}
