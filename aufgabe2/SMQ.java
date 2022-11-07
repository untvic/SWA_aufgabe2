package aufgabe2;
import ringpuffer.RingklasseVU;

import java.util.HashMap;
import java.util.Map;

public class SMQ<T> {

    private static SMQ<?> smq;
    
    private Map<String, RingklasseVU<T>> messageQs = new HashMap<String, RingklasseVU<T>>();
    private Map<Consumer<T>, RingklasseVU<String>> Listeners = new HashMap<Consumer<T>, RingklasseVU<String>>();

    private SMQ(){
    }
    
    public static <T> SMQ<?> getInstance(){
        if(smq == null){
            smq = new SMQ<>();
        }
      return smq;
    }


    public void addMessage(String nameQ, T Message, boolean fixedCapacity, boolean discarding ){

        if(messageQs.containsKey(nameQ)){
            messageQs.get(nameQ).updateConfig(fixedCapacity, discarding);
            messageQs.get(nameQ).add(Message);
        }
        else{
            messageQs.put(nameQ, new RingklasseVU<T>(10, fixedCapacity, discarding));
        }
    }

    public boolean addListener(String nameQ, Consumer<T> con){
        if(!Listeners.containsKey(con)){
            Listeners.put(con,new RingklasseVU<String>(10, false, false));
        }
        if(messageQs.containsKey(nameQ) && !Listeners.get(con).contains(nameQ)){
            Listeners.get(con).add(nameQ);
            return true;
        }
            return false;
        
    }

    public void retrieveMessage(String nameQ, Consumer<T> con){
        while(!messageQs.isEmpty()){
            con.receiveMessage(messageQs.get(nameQ).poll()); 
        }
    }

}

