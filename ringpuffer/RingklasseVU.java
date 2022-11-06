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
    public RingklasseVU(int cap_in, boolean fixedCapacity_in , boolean discarding_in){
        size = 0;
        writePOS = 0;
        readPOS = 0;
        capacity = cap_in;
        fixedCapacity = fixedCapacity_in;
        discarding = discarding_in;
        elements = new ArrayList<T>(capacity);
        for(int i = 0; i<capacity; i++){
            elements.add(null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.pr_isEmpty() ;
    }

    private boolean pr_isEmpty() {
        
        return (size == 0) ;
    }

    @Override
    public boolean contains(Object o) {
        
        return false;
    }

    @Override
    public Iterator iterator() {
        
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
        if(size >= capacity ){
            if(fixedCapacity == false){
                if(discarding == false){
                    throw new IllegalStateException();
                }else {
                    elements.remove(writePOS);
                    elements.add(writePOS, e);
                    this.advance_writing();
                    this.advance_reading();
                    return true;
                }
            } else{
                for(int i = 0; i<capacity; i++){
                    elements.add(writePOS,null);
                }
                readPOS = readPOS + capacity;
                capacity = capacity * 2;
                return true;
            }
        }else{
            elements.remove(writePOS);
            elements.add(writePOS, e);
            this.advance_writing();
            size = size +1;
           return true; 
        }
        
    }

    

    @Override
    public boolean offer(T e) {
       
        return pr_offer(e);
    }

    private boolean pr_offer(T e) {
        if(size >= capacity ){
            if(fixedCapacity == false){
                if(discarding == false){
                    return false; 
                }else {
                    elements.remove(writePOS);
                    elements.add(writePOS, e);
                    this.advance_writing();
                    this.advance_reading();
                    return true;
                }
            } else{
                for(int i = 0; i<capacity; i++){
                    elements.add(writePOS,null);
                }
                readPOS = readPOS + capacity;
                capacity = capacity * 2;
                return true;
            }
        }else{
            elements.remove(writePOS);
            elements.add(writePOS, e);
            this.advance_writing();
            size = size +1;
           return true; 
        }
    }

    @Override
    public T remove() {
       return this.pr_remove(); 
    }

    public T pr_remove() {
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }else{
            T el = elements.remove(readPOS);
            elements.add(readPOS, null);
            this.advance_reading();
            size = size -1;
            return el; 
        } 
    }

    @Override
    public T poll() {
        return this.pr_poll();
    }

    private T pr_poll() {
        
        if(this.isEmpty()){
            return null;
        }else{
            T el = elements.remove(readPOS);
            elements.add(readPOS, null);
            this.advance_reading();
            size = size -1;
            return el; 
        }
        
    }

    @Override
    public T element() {
        return this.pr_element();
    }

    
    private T pr_element() {
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }else{
            return elements.get(readPOS);
        }
    }

    @Override
    public T peek() {
        return this.pr_peek();
    }

    private T pr_peek() {
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

    private void advance_writing() {
        writePOS = next(writePOS);
    }

    private void advance_reading() {
        readPOS = next(readPOS);
    }

    public void show(){
        for(T i : elements ){
            System.out.println(i);
        }
        System.out.println("readPOS:"+readPOS);
        System.out.println("writePOS:"+writePOS);
    }
    
}
