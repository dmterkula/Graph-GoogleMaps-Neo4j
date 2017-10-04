package dmterkula.selfstudy.graphs.binaryheap.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David Terkula on 5/9/2017.
 */
public class BinaryHeapTest {

    private TypeSafeArrayList<Integer> list;
    private BinaryHeap<Integer> heap;

    @Before
    public void setUp() throws Exception {
        list = new TypeSafeArrayList<Integer>();
        list.add(5); list.add(7); list.add(8);list.add(4);
        list.add(23); list.add(2); list.add(100); list.add(-3);
        heap = new BinaryHeap<Integer>(list);

    }

    @After
    public void tearDown() throws Exception {
        for(int i = 0; i < list.size(); i ++){
            list.remove(i);
        }
    }


    @Test
    public void getList() throws Exception {
        TypeSafeArrayList<Integer> testList = new TypeSafeArrayList<Integer>();
        testList.add(-3); testList.add(2); testList.add(4); testList.add(5); testList.add(23);
        testList.add(8); testList.add(100); testList.add(7);
        assertNotNull(heap);
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>(list);
        assertEquals(testHeap.getList(), testList);
    }

    @Test
    public void insert() throws Exception {
        list.add(null);
        list.set(0, null); list.set(1, -4); list.set(2, -3); list.set(3, 4); list.set(4, 2); list.set(5, 23);
        list.set(6, 8); list.set(7, 100); list.set(8, 7); list.set(9, 5);

        heap.insert(-4);
        assertNotNull(heap);
        assertEquals(heap.getList(), list);
    }

    @Test
    public void deleteMin() throws Exception {
        heap.deleteMin();
        list.remove(1);
        list.set(0, null); list.set(1, 2); list.set(2, 5); list.set(3, 4); list.set(4, 7);
        list.set(5, 23); list.set(6, 8); list.set(7, 100);
        assertNotNull(heap);
        assertEquals(heap.getList(), list);

    }

    @Test
    public void deleteMax() throws Exception{
        TypeSafeArrayList<Integer> testList = new TypeSafeArrayList<Integer>();
        testList.add(8); testList.add(23); testList.add(7); testList.add(4); testList.add(5);
        testList.add(3); testList.add(2); testList.add(100);
        BinaryHeap<Integer> maxHeap = new BinaryHeap<Integer>(testList, true);
        maxHeap.setMin(maxHeap.getList(), false);
        assertEquals(maxHeap.isMin(), false);
        maxHeap.deleteMax();
        list.set(0, null); list.set(1, 23); list.set(2, 8); list.set(3,7); list.set(4,4);
        list.set(5,5); list.set(6, 3); list.set(7,2); list.remove(8);
        assertNotNull(maxHeap);
        assertNotNull(maxHeap.getList());
        assertEquals(maxHeap.getList(), list);
    }

}