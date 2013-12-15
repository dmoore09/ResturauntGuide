package cs2114.restaurant;

import java.util.NoSuchElementException;
import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 * A circular linked list that will be used to hold restaurant reviews from yelp
 *
 * @param <E>
 * @author dmoore09
 * @version Apr 2, 2013
 */
public class CircularLinkedList<E>
    implements CircularList<E>
{

    // node that represents the current position in the list
    private Node<E> current;

    // integer to represent the size of the list
    private int     size;


    /**
     * iterator for CircularLinkedList
     *
     * @author dmoore09
     * @version Apr 4, 2013
     */
    public class CircularIterator
        implements Iterator<E>
    {
        private int position;
        private E   nextItem;
        private Node<E> nextIt;


        /**
         * Initialize fields
         */
        public CircularIterator()
        {
            position = size;
            nextIt = current;
        }


        /**
         * Checks to see if there is a next node
         *
         * @return true is next item is not the current position, false if it is
         */
        @Override
        public boolean hasNext()
        {
            if (position > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }


        /**
         * set position to the next node in the list
         *
         * @return E data in the next node
         */
        @Override
        public E next()
        {
            if (hasNext())
            {
                nextItem = nextIt.data();
                nextIt = nextIt.next();
                position--;
                return nextItem;

            }

            throw new NoSuchElementException("There is not next Item");

        }


        /**
         * not to be implemented throws an exception
         */
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Remove is not Implemented"
                + " yet");
        }

    }


    /**
     * Creates a new iterator so the list can be iterated over
     *
     * @return new iterator
     */
    @Override
    public Iterator<E> iterator()
    {
        return new CircularIterator();
    }


    /**
     * constructor for the linked list
     */
    public CircularLinkedList()
    {
        current = new Node<E>(null);
        current.join(current);
        size = 0;
    }


    /**
     * add an element to the list
     *
     * @param data
     *            the element to be added
     */
    @Override
    public void add(E data)
    {
        // if size is 0 create new node and make it current
        if (size == 0)
        {
            current.split();
            current.setData(data);
            current.join(current);
            size++;
        }
        // if size is 1 create new node and join it to first node
        else if (size == 1)
        {
            Node<E> nextNode = new Node<E>(data);
            current.split();
            current.join(nextNode);
            nextNode.join(current);
            current = nextNode;
            size++;
        }
        // add node before current A - B, A C - B, A - C - B
        else
        {
            Node<E> nextNode = new Node<E>(data);

            Node<E> previous = current.previous();

            // split nodes in middle
            previous.split();

            // join new node to current node
            nextNode.join(current);

            // join previous node to new node
            previous.join(nextNode);

            // make next node current
            current = nextNode;
            size++;
        }

    }


    /**
     * empty all of the items in the CircularLinkedList
     */
    @Override
    public void clear()
    {
        current = new Node<E>(null);
        size = 0;

    }


    /**
     * Returns current node's data. If the list is empty throw's a NoSuchElement
     * Exception
     *
     * @return data in the current node
     */
    @Override
    public E getCurrent()
    {
        if (size == 0)
        {
            throw new NoSuchElementException("The List is empty, so there "
                + "is no current item");

        }
        else
        {
            return current.data();
        }
    }


    /**
     * updates the current node to the next node in the CircularLinkedList
     */
    @Override
    public void next()
    {
        if (size > 0)
        {
            current = current.next();
        }

    }


    /**
     * updates current node to be the previous node
     */
    @Override
    public void previous()
    {
        if (size > 0)
        {
            current = current.previous();
        }

    }


    /**
     * remove the current item in the list.
     *
     * @return current item
     */
    @Override
    public E removeCurrent()
    {
        // throw exception if the list is empty
        if (size == 0)
        {
            throw new NoSuchElementException("The list is empty!");
        }
        // split the current node's ties to other nodes and connect new ones
        // A - B - C, A B C, A - C
        else if (size > 1)
        {
            Node<E> oldCurrent = current;
            Node<E> previous = current.previous();
            current = current.next();
            // split nodes
            Node<E> next = previous.split().split();
            // join nodes
            previous.join(next);
            size--;

            return oldCurrent.data();
        }
        else
        {
            E data = current.data();
            current = new Node<E>(null);
            size--;
            return data;
        }

    }


    /**
     * Tells size of list
     *
     * @return the size of the CircularLinkedList
     */
    public int size()
    {
        return size;
    }


    /**
     * Represents the Circular list in a format recognizable to users
     *
     * @return string representation of the list
     */
    public String toString()
    {
        if (size == 0)
        {
            return "[]";
        }
        else if (size == 1)
        {
            return "[" + current.data() + "]";
        }
        else
        {
            Node<E> position = current;

            String result = "[" + current.data() + ", ";

            for (int i = 0; i < size - 1; i++)
            {

                if (i == size - 2)
                {
                    result = result + position.next().data() + "]";
                }
                else
                {
                    result = result + position.next().data() + ", ";
                    position = position.next();

                }

            }

            return result;
        }

    }


    /**
     * returns data in next node for testing purposes
     *
     * @return data inside next node
     */
    public E getNext()
    {
        return current.next().data();
    }


    /**
     * returns data in the previous node for testing purposes
     *
     * @return data inside previous node
     */
    public E getPrevious()
    {
        return current.previous().data();
    }

}
