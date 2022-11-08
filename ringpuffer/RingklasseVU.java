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
    public int size() {return size;}

    @Override
    public boolean isEmpty() {return this.pr_isEmpty() ;}
    private boolean pr_isEmpty() {return (size == 0) ;}

    @Override
    public boolean contains(Object o) {return false;}

    @Override
    public Iterator iterator() {return null;}

    @Override
    public Object[] toArray() {return null;}

    @Override
    public Object[] toArray(Object[] a) {return null;}

    @Override
    public boolean remove(Object o) {return false;}

    @Override
    public boolean containsAll(Collection c) {return false;}

    @Override
    public boolean addAll(Collection c) {return false;}

    @Override
    public boolean removeAll(Collection c) {return false;}
    
    @Override
    public boolean retainAll(Collection c) {return false;}
    @Override
    public void clear() {}

    @Override
    public boolean add(T message) {
    
        return pr_add(message);
    }

    private boolean pr_add(T e) {
        if(e == null){ throw new NullPointerException();}
        if(size >= capacity ){
            if(fixedCapacity == false){
                if(discarding == false){
                    throw new IllegalStateException();
                }else {
                    this.discarding_add(e);
                    return true;
                }
            } else{
                expand_capacity(2);
                this.normal_add(e);
                return true;
            }
        }else{
            this.normal_add(e);
           return true; 
        }
        
    }

    

    @Override
    public boolean offer(T e) {
       
        return pr_offer(e);
    }

    private boolean pr_offer(T e) {
        
        if(e == null){ throw new NullPointerException();}
        if(size >= capacity ){
            if(fixedCapacity == false){
                if(discarding == false){
                    return false; 
                }else {
                    this.discarding_add(e);
                    return true;
                }
            } else{
                expand_capacity(2);
                this.normal_add(e);
                return true;
            }
        }else{
            this.normal_add(e);
            return true; 
        }
    }

    private void normal_add(T e_in){
        elements.remove(writePOS);
            elements.add(writePOS, e_in);
            this.advance_writing();
            size = size +1;
    }

    private void discarding_add(T e_in){
        elements.remove(writePOS);
        elements.add(writePOS, e_in);
        this.advance_writing();
        this.advance_reading();
    }

    private void expand_capacity(int factor_in){
        int new_cap = (capacity* factor_in) -capacity;
        for(int i = 0; i<new_cap; i++){
            elements.add(writePOS,null);
        }
        readPOS = readPOS + capacity;
        capacity = capacity + new_cap;
    }   


    @Override
    public T remove() {
       return this.pr_remove(); 
    }

    public T pr_remove() {
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }else{
           return normal_remove();
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
            return normal_remove(); 
        }
    }

    private T normal_remove(){
            //naechsten 2 Lines auskommentieren, wenn array nicht genau sein soll!
            T el = elements.remove(readPOS);
            elements.add(readPOS, null);
            this.advance_reading();
            size = size -1;
            return el;
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
    
    public void updateConfig(boolean fixedCapacity_in, boolean discarding_in){
        this.fixedCapacity = fixedCapacity_in;
        this.discarding = discarding_in;
    }
}
