package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.function.*;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Constructs a {@code GenList} Linked List.
 * Contains methods that can be perfomed on the list.
 */
public class LinkedGenList<T> implements GenList<T> {

    /** The first node. */
    public Node<T> head;

    /** Size of the list. */
    public int size;

    /**
     * This constructor makes a new list.
     */
    public LinkedGenList() {
        this.head = new Node<T>(null);
        this.size = 0;
    } // constructor


    public void addToEnd(Node<T> n, T elem) {
        if (n == null) {
            return;
        } // if

        if (n.getNext() == null) {
            n.setNext(elem);
            return;
        } // if

        addToEnd(n.getNext(), elem);
    } // addToEnd

    /**
     * This constructor takes a {@code GenList<U>} and changes it into a {@code GenList<T>} object.
     * @param other must be a {@code GenList<U>} object.
     * @param <U> must extend T.
     */
    public <U extends T> LinkedGenList(GenList<U> other) {
        if (other == null) {
            throw new NullPointerException("list is null");
        } // if

        GenList<T> newList = new LinkedGenList<T>();

        for (int i = 0; i < other.size(); i++) {
            T item = other.get(i);
            newList.add(item);
        } // for

    } // constructor

    /**
     * Returns the size of the list.
     * @return size of list
     */
    @Override
    public int size() {
        if (this.size > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } // if

        return this.size;
    } // getSize

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T obj) {
        if (obj == null) {
            throw new NullPointerException("obj is null");
        } // if

        // Node<T> tempNode = new Node<T>(obj);
        Node<T> list = head;
        addToEnd(list, obj);
        /*
        while (list.getNext() != null) { //get to the end of the list
            list = list.getNext();
        } // while

        list.setNext(tempNode);
        */
        size++;

        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    @Override
    public <U extends T> boolean add(GenList<U> list) {
        if (list == null) {
            throw new NullPointerException("list is null");
        } // if

        if (list.size() == 0) {
            return false;
        } // if

        Node<T> tempNode = head;

        while (tempNode.getNext() != null) {
            tempNode = tempNode.getNext();
        } // while

        for (int i = 0; i < list.size(); i++) {
            T contents = list.get(i);
            tempNode.setNext(new Node<T>(contents, null));
            tempNode = tempNode.getNext();
        } // for

        size += list.size();

        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, T obj) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index is out of rangle");
        } // if

        if (obj == null) {
            throw new NullPointerException("obj is null");
        } // if

        Node<T> tempNode = new Node<T>(obj);
        Node<T> list = head;

        for (int i = 0; i < index; i++) {
            list = list.getNext();
        } // for

        tempNode.setNext(list.getNext());
        list.setNext(tempNode);
        size++;

        return true;
    } // add at index

    /**
     * {@inheritDoc}
     */
    @Override
    public <U extends T> boolean add(int index, GenList<U> list) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index is out of rangle");
        } // if

        if (list == null) {
            throw new NullPointerException("list is null");
        } // if

        if (list.size() == 0) {
            return false;
        } // if

        int location = index;
        for (int i = 0; i < list.size(); i++) {
            T contents = list.get(i);
            add(location, contents);
            location++;
        } // for

        return true;
    } // add list at index

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } // if

        Node<T> list = head;

        for (int i = 0; i <= index; i++) {
            list = list.getNext();
        } // for
        return list.getContents();
    } // get

    /**
     * {@inheritDoc}
     */
    @Override
    public T set(int index, T obj) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } // if

        if (obj == null) {
            throw new NullPointerException("obj is null");
        } // if

        Node<T> list = head;

        for (int i = 0; i <= index; i++) {
            list = list.getNext();
        } // for

        T previousObj = list.getContents();
        list.setContents(obj);

        return previousObj;
    } // set

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean allMatch(Predicate<T> p) {
        if (p == null) {
            throw new NullPointerException("the specified predicate is null");
        } // if

        boolean passes = true;

        for (int i = 0; i < size(); i++) {
            if (p.test(get(i)) == false) {
                passes = false;
            }
        } // for

        return passes;
    } // allMatch

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new ListIterator<T>(head);
        return iterator;
    } // iterator

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean anyMatch(Predicate<T> p) {
        if (p  == null) {
            throw new NullPointerException("the specified predicate is null");
        } // if

        boolean exists = false;

        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                exists = true;
            } // if
        } // for

        return exists;
    } // anyMatch

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        size = 0;
    } // clear

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T o) {
        if (o == null) {
            throw new NullPointerException("specified object is null");
        } // if

        for (int i = 0; i < size(); i++) {
            if (get(i).equals(o)) {
                return true;
            } // if
        } // for

        return false;
    } // contains

    /**
     * {@inheritDoc}
     */
    @Override
    public GenList<T> distinct() {
        GenList<T> dist = new LinkedGenList<T>();
        boolean inList = false;
        dist.add(get(0));

        for (int i = 1; i < size(); i++) {
            for (int j = 0; j < dist.size(); j++) {
                if (get(i).equals(dist.get(j))) {
                    inList = true;
                } // if
            } // for

            if (inList == false) {
                dist.add(get(i));
            } // if
            inList = false; // reset boolean
        } // for

        return dist;
    } // distinct

    /**
     * {@inheritDoc}
     */
    @Override
    public GenList<T> filter(Predicate<T> p) {
        if (p == null) {
            throw new NullPointerException("predicate is null");
        } // if

        GenList<T> filtered = new LinkedGenList<T>();
        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                filtered.add(get(i));
            } // if
        } // for
        return filtered;
    } // filter

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(T obj) {
        if (obj == null) {
            throw new NullPointerException("obj is null");
        } // if

        for (int i = 0; i < size; i++) {
            if (get(i).equals(obj)) {
                return i;
            } // if
        } // for
        return -1;
    } // indexOf

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } // if
        return false;
    } // isEmpty

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeString() {
        String print = "";

        for (int i = 0; i < size(); i++) {
            print += get(i);
        } // for

        return print;
    } // makeString

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeString(String sep) {
        String print = "";
        for (int i = 0; i < this.size - 1; i++) {
            print += this.get(i) + sep;
        } // for

        print += this.get(this.size - 1);

        return print;
    } // makeString

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeString(String start, String sep, String end) {
        String print = start;
        for (int i = 0; i < this.size - 1; i++) {
            print += this.get(i) + sep;
        } // for

        print += this.get(this.size - 1);

        print += end;
        return print;
    } // makeString

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> GenList<R> map(Function<T,R> f) {
        if (f == null) {
            throw new NullPointerException("function is nulll");
        } // if

        GenList<R> mappedList = new LinkedGenList<R>();

        for (int i = 0 ; i < size; i++) {
            if ((f.apply(get(i))) == null) {
                throw new NullPointerException("result of calling apply is null");
            } // if

            mappedList.add(f.apply(get(i)));
        } // for

        return mappedList;
    } // map

    /**
     * {@inheritDoc}
     */
    @Override
    public T max(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("comparator is null");
        } // if

        T contents = get(0);
        for (int i = 1; i < size; i++) {
            if (c.compare(contents, get(i)) < 0) {
                contents = get(i);
            }
        } // for
        return contents;

    } // max

    /**
     * {@inheritDoc}
     */
    @Override
    public T min(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("comparator is null");
        } // if

        T contents = get(0);
        for (int i = 1; i < size; i++) {
            if (c.compare(contents, get(i)) > 0) {
                contents = get(i);
            }
        } // for
        return contents;
    } // max

    /**
     * {@inheritDoc}
     */
    @Override
    public T reduce(T start, BinaryOperator<T> f) {
        if (f == null) {
            throw new NullPointerException("function is null");
        } // if

        T result = start;

        for (int i = 0; i < size(); i++) {
            if (f.apply(result, get(i)) == null) {
                throw new NullPointerException("result of function is null");
            } // if

            result = f.apply(result, get(i));
        } // for

        return result;
    } // reduce

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } // if

        Node<T> list = head;

        for (int i = 0; i < index; i++) {
            list = list.getNext();
        } // for

        list.setNext(list.getNext().getNext());
        T contents = list.getNext().getContents();

        size--;

        return contents;
    } // remove

    /**
     * {@inheritDoc}
     */
    @Override
    public GenList<T> reverse() {
        GenList<T> rev = new LinkedGenList<T>();

        for (int i = size() - 1; i >= 0; i--) {
            rev.add(get(i));
        } // for

        return rev;
    } // reverse

    /**
     * {@inheritDoc}
     */
    @Override
    public GenList<T> splice(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("illegal endpoint index value");
        } // if

        GenList<T> sp = new LinkedGenList<T>();

        for (int i = fromIndex; i < toIndex; i++) {
            sp.add(get(i));
        } // for

        return sp;
    } // splice

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] toArray(IntFunction<T[]> gen) {
        T[] array = gen.apply(size);

        for (int i = 0; i < array.length; i++) {
            array[i] = get(i);
        } // for
        return array;
    } // toArray


} // LinkedGenList
