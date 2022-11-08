package aufgabe2;

public class Producer<T> {
    
    public void sendMessage(String nameQ, T message, SMQ<T> smq){

        smq.addMessage(nameQ, message, false, false);

    }
    public void sendMessage(String nameQ, T message, SMQ<T> smq, boolean fixedCapacity, boolean discarding){

        smq.addMessage(nameQ, message, fixedCapacity, discarding);

}
}
