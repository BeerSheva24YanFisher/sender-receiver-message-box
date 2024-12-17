package telran.producer.consumer;

public class Sender extends Thread{
    private int nMessages;
    private MessageBox messageBoxEven;
    private MessageBox messageBoxOdd;
    public Sender(int nMessages, MessageBox messageBoxEven, MessageBox messageBoxOdd) {
        this.nMessages = nMessages;
        this.messageBoxEven = messageBoxEven;
        this.messageBoxOdd = messageBoxOdd;
    }
    @Override
    public void run(){
        for(int i = 0; i < nMessages; i++) {
            try {
                int seqNum = i + 1;
                MessageBox messageBox = seqNum % 2 == 0 ? messageBoxEven : messageBoxOdd;
                messageBox.put("Message" + seqNum);
            } catch (InterruptedException e) {
                
            }
            
    }
}

}