package ringpuffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class RingklasseVU<T> implements Serializable,Queue<T>{

    ArrayList<T> elements;
    int writePOS;
    int readPOS;
    int capacity;
    int size;
    boolean fixedCapacity;
    boolean discarding;

    //Konstruktor
    RingklasseVU(int cap_in, boolean fixedCapacity_in , boolean discarding_in){
        size = 0;
        writePOS = 0;
        readPOS = 0;
        capacity = cap_in;
        fixedCapacity = fixedCapacity_in;
        discarding = discarding_in;
        elements = new ArrayList<T>(capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (writePOS == readPOS) ;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray(Object[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean add(T e) {
    
        return pr_add(e);
    }

    private boolean pr_add(T e) {
        if(next(writePOS) == readPOS){
            return false;
        }else{
            this.advance_writing();
            elements.add(writePOS, e);
            size++;
            return true;
        } 
    }

    

    @Override
    public boolean offer(Object e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T remove() {
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }else{
            T el = elements.get(readPOS);
            this.advance_reading();
            size--;
            return el; 
        }
        
    }

    @Override
    public T poll() {
        if(this.isEmpty()){
            return null;
        }else{
            T el = elements.get(readPOS);
            this.advance_reading();
            size--;
            return el; 
        }
        
    }

    @Override
    public T element() {
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }else{
            return elements.get(readPOS);
        }
    }

    @Override
    public T peek() {
        if(this.isEmpty()){
            return null;
        }else{
            return elements.get(readPOS);
        }
    }

    private int next(int nr) {
        if((nr+1) == capacity){
            return 0;
        }
        else {
            return (nr+1);
        }
    }

    private boolean advance_writing() {
        return true;
    }

    private boolean advance_reading() {
        return false;
    }
    
}
