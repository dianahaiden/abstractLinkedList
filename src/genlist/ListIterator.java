package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.Iterator;

/**
 * This class implements {@code Iterator<T>}.
 * It returns a iterator over the elements of the list.
 */
public class ListIterator<T> implements Iterator<T> {

    /** This is the beginning of the list. */
    private Node<T> head;

    /**
     * Constructor that makes a list iterator with a list.
     * @param list must be of {@code Node<T>} type.
     */
    public ListIterator(Node<T> list) {
        head = list;
    } // ListIterator

    /**
     * Method that checks if there is a next element.
     * @return true if there is an item.
     */
    public boolean hasNext() {
        if (head.getNext() == null) {
            return false;
        } else {
            return true;
        } // if else
    } // hasNext

    /**
     * Method that returns contents of next element.
     * @return next element.
     */
    public T next() {
        return head.getNext().getContents();
    } // next

} // Iterator
