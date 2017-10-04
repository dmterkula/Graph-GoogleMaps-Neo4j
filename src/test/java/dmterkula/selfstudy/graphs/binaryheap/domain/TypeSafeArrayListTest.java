package dmterkula.selfstudy.graphs.binaryheap.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David Terkula on 5/6/2017.
 */
public class TypeSafeArrayListTest {

    private TypeSafeArrayList<String> strings = new TypeSafeArrayList<String>();

    @Before
    public void setUp() throws Exception {
        strings.add("hello");
    }

    @After
    public void tearDown() throws Exception {
        strings.remove(0);
    }

    @Test
    public void add() throws Exception {
        assertNotNull(strings);
        assertEquals(strings.size(), 2);
        strings.add("world");
        assertEquals(strings.size(), 3);
        assertEquals(strings.get(2), "world");
    }

    @Test
    public void get() throws Exception {
        assertNotNull(strings);
        assertEquals(strings.get(1), "hello");
    }

    @Test
    public void size() throws Exception {
        assertNotNull(strings);
        assertEquals(2, strings.size());
    }

    @Test
    public void remove() throws Exception {
        assertNotNull(strings);
        strings.add("world");
        assertEquals(strings.size(), 3);
        strings.remove(2);
        assertEquals(strings.size(), 2);
        assertEquals(strings.get(1), "hello");
    }

    @Test
    public void isEmpty() throws Exception {
        assertNotNull(strings);
        assertFalse(strings.isEmpty());
    }

}