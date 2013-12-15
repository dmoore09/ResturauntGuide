package cs2114.restaurant;

import java.util.NoSuchElementException;
import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** tests CircularLinkedList's methods
 *
 * @author dmoore09
 * @version Apr 4, 2013
 */
public class CircularLinkedListTest
    extends TestCase
{

    // fields for testing
    private CircularLinkedList<String> testList;


    /**
     * runs before every test method to reset initial conditions
     */
    public void setUp()
    {
        testList = new CircularLinkedList<String>();
    }


    /**
     * test next on empty string
     */
    public void testNextEmpty()
    {
        testList.next();
        Exception occurred = null;
        try
        {
            testList.getCurrent();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof NoSuchElementException);
        assertEquals(
            "The List is empty, so there is no current item",
            occurred.getMessage());
    }


    /**
     * test previous method
     */
    public void testPrevious()
    {
        testList.add("A");
        testList.previous();
        assertEquals("A", testList.getCurrent());
    }


    /**
     * test previous on empty list
     */
    public void testPreviousEmpty()
    {
        testList.previous();
        Exception occurred = null;
        try
        {
            testList.getCurrent();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof NoSuchElementException);
        assertEquals(
            "The List is empty, so there is no current item",
            occurred.getMessage());
    }


    /**
     * test the add method when the list is empty
     */
    public void testAddEmpty()
    {
        testList.add("A");
        assertEquals(1, testList.size());
    }


    /**
     * test the add method when the list has only one item
     */
    public void testAddSecond()
    {
        testList.add("A");
        testList.add("B");
        assertEquals(2, testList.size());
        assertEquals("B", testList.getCurrent());
        assertEquals("A", testList.getNext());
        assertEquals("A", testList.getPrevious());
    }


    /**
     * test add method when 3 objects are added
     */
    public void testAddThird()
    {
        testList.add("A");
        testList.add("B");
        testList.add("C");
        assertEquals(3, testList.size());
        assertEquals("C", testList.getCurrent());
        assertEquals("B", testList.getNext());
        assertEquals("A", testList.getPrevious());
    }


    /**
     * test add method when 5 objects are added a non trivial case
     */
    public void testAddFifth()
    {
        testList.add("A");
        testList.add("B");
        testList.add("C");
        testList.add("D");
        testList.add("E");
        assertEquals(5, testList.size());
        assertEquals("E", testList.getCurrent());
        assertEquals("D", testList.getNext());
        assertEquals("A", testList.getPrevious());
    }


    /**
     * test the clear method
     */
    public void testClear()
    {
        testList.add("A");
        testList.add("B");
        testList.add("C");
        testList.clear();
        assertEquals(0, testList.size());
    }


    /**
     * test remove current method when the list is empty
     */
    public void testRemoveCurrentEmpty()
    {
        Exception occurred = null;
        try
        {
            testList.removeCurrent();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof NoSuchElementException);
        assertEquals("The list is empty!", occurred.getMessage());
    }


    /**
     * test remove current method when the list is empty
     */
    public void testgetCurrentEmpty()
    {
        Exception occurred = null;
        try
        {
            testList.getCurrent();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof NoSuchElementException);
        assertEquals(
            "The List is empty, so there is no current item",
            occurred.getMessage());
    }


    /**
     * test remove current when there is one item
     */
    public void testRemoveCurrentOne()
    {
        testList.add("A");
        assertEquals("A", testList.removeCurrent());
    }


    /**
     * test remove when there are two elements
     */
    public void testRemoveCurrentTwo()
    {
        testList.add("A");
        testList.add("B");
        testList.removeCurrent();
        assertEquals("A", testList.getCurrent());
        assertEquals(1, testList.size());
    }


    /**
     * test to remove when there are three elements in the list
     */
    public void testRemoveCurrentThree()
    {
        testList.add("A");
        testList.add("B");
        testList.add("C");
        testList.removeCurrent();
        assertEquals("B", testList.getCurrent());
        assertEquals(2, testList.size());
        assertEquals("A", testList.getNext());
        assertEquals("A", testList.getPrevious());
    }


    /**
     * test to string method
     */
    public void testToString()
    {
        testList.add("A");
        testList.add("B");
        testList.add("C");
        assertEquals("[C, B, A]", testList.toString());
    }


    /**
     * test to String with only 1 item
     */
    public void testToString1()
    {
        testList.add("A");
        assertEquals("[A]", testList.toString());
    }


    /**
     * test toString when there are no elements in the list
     */
    public void testToStingEmpty()
    {
        assertEquals("[]", testList.toString());
    }


    /**
     * test hasNext method for the iterator when there is 1 element
     */
    public void testItHasNext()
    {
        assertFalse(testList.iterator().hasNext());
    }


    /**
     * test hasNext method for the iterator when there are 3 elements
     */
    public void testItHasNextT()
    {
        CircularLinkedList<String> test = new CircularLinkedList<String>();
        test.add("A");
        test.add("B");
        test.add("C");
        assertEquals(true, test.iterator().hasNext());
    }


    /**
     * test hasNext method for the iterator when there are 5 elements
     */
    public void testItHasNextT5()
    {
        CircularLinkedList<String> test = new CircularLinkedList<String>();
        test.add("A");
        test.add("C");
        test.add("B");
        test.next();
        assertEquals(true, test.iterator().hasNext());
    }


    /**
     * test iterator next method
     */
    public void testItNext()
    {
        testList.add("A");
        testList.add("B");
        testList.add("C");
        assertEquals("C", testList.iterator().next());
    }


    /**
     * test iterator next method when there are no elements
     */
    public void testItNext0()
    {
        Exception occurred = null;
        try
        {
            testList.iterator().next();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof NoSuchElementException);
        assertEquals("There is not next Item", occurred.getMessage());
    }


    /**
     * test remove current method when the list is empty
     */
    public void testItRemove()
    {
        testList.add("A");
        Exception occurred = null;
        try
        {
            testList.iterator().remove();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof UnsupportedOperationException);
        assertEquals("Remove is not Implemented yet", occurred.getMessage());
    }


    /**
     * test behavior based on project write up example
     */
    public void testBehavior()
    {
        CircularLinkedList<String> test = new CircularLinkedList<String>();
        test.add("A");
        test.add("C");
        test.add("B");
        assertEquals("[B, C, A]", test.toString());

        test.add("X");
        assertEquals("[X, B, C, A]", test.toString());

        test.removeCurrent();
        assertEquals("[B, C, A]", test.toString());

    }
}
