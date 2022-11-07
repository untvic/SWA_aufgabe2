package aufgabe2;

public class Producer {
    
    public void sendMessage(String nameQ, String message, SMQ<String> smq){

            smq.addMessage(nameQ,message, false, false);

    }
    public void sendMessage(String nameQ, String message, SMQ<String> smq, boolean fixedCapacity, boolean discarding){

        smq.addMessage(nameQ, message, fixedCapacity, discarding);

}
}
