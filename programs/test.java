package programs;
import ringpuffer.RingklasseVU;
import aufgabe2.*;

public class test {
    public static void main(String[] args) {
        Producer<String> pro1 = new Producer<String>();
        Producer<String> pro2 = new Producer<String>();
        Consumer<String> con1 = new Consumer<String>();
        Consumer<String> con2 = new Consumer<String>();
        SMQ<String> smq = null;
        smq.getInstance();
        
        pro1.sendMessage("chat1", "1", smq);
        con1.getMessage("chat1", smq);
        con1.getMessage("chat1", smq);
        pro1.sendMessage("chat1", "2", smq);
        pro2.sendMessage("chat1", "3", smq);
        con2.getMessage("chat1", smq);
        con2.getMessage("chat2", smq);
        con1.startListen("chat2", smq);
        pro1.sendMessage("chat2", "4", smq);
        con2.startListen("chat2", smq);
        pro1.sendMessage("chat2", "5", smq);
        pro1.sendMessage("chat2", "6", smq);
    }
}
