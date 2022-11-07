package aufgabe2;

public class Consumer<T> {
    public void getMessage(String nameQ, SMQ<T> smq){
        smq.retrieveMessage(nameQ, this);
    }

    public void startListen(String nameQ, SMQ<T> smq){
        if(!smq.addListener(nameQ, this)){
            System.out.println("Hinzufügen fehlgeschlagen"); 
        }
    }

    public void receiveMessage(T message){
        System.out.println(message);
    }
}
