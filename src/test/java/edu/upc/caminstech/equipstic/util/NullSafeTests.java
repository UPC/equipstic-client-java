package edu.upc.caminstech.equipstic.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class NullSafeTests {

    @Test
    void testSorted() {
        List<String> elems = Arrays.asList("ccc", "bbb", "aaa");
        assertThat(NullSafe.sorted(elems), contains("aaa", "bbb", "ccc"));
    }

    @Test
    void testSortedNullList() {
        assertThat(NullSafe.sorted(null), empty());
    }

}
