package ringpuffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Ringpuffer<T> implements Queue<T>, Serializable {

    private ArrayList<T> elements;
    private int writePos;
    private int readPos;
    private int size;
    private int capacity;
    private boolean fixedCapacity;
    private boolean discarding;

    Ringpuffer(int sizein, int capacityin, boolean fixedCapacityin, boolean discardingin){
    this.writePos = 0;
    this.readPos = 0;
    this.size = 0;
    this.capacity = capacityin;
    this.fixedCapacity = fixedCapacityin;
    this.discarding = discardingin;
    elements = new ArrayList<T>();
    }


    public boolean isFull(){
        if(writePos - readPos +1 == capacity){
            return true; 
        }
        return false;
    }

    public void write(T e){
        if(this.isFull() && !fixedCapacity){
            for(int i = 0; i<capacity; i++){
                elements.add(readPos,null);
            }
            readPos = readPos + capacity;
            capacity = capacity * 2;
        }
        if(this.isFull() && discarding){
            elements.set(writePos, e);
            writePos++;
            readPos++; 
        }
        else if(!this.isFull()){
            elements.set(writePos, e);
            writePos++; 
            size++;
        }

    }

    public T read(){
        T e = elements.get(readPos);
        size--;
        readPos++;
        return e;
    }

  
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }
    @Override
    public boolean contains(Object o) {
        for(Object ob : elements){
            if(ob == o){
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
    @Override
    public Object[] toArray() {
        return elements.toArray();
    }
    @Override
    public <T> T[] toArray(T[] a) {
   
        return elements.toArray(a);
    }
    @Override
    public boolean remove(Object o) {
         for(Object ob : elements){
            if(ob == o){
                elements.remove(o);
                return true;
            }
        } 
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }
    @Override
    public boolean addAll(Collection<? extends T> c) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void clear() {
        elements = new ArrayList<T>();
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean add(T e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean offer(T e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public T remove() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public T poll() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public T element() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public T peek() {
        // TODO Auto-generated method stub
        return null;
    }
    
}