package telran.producer.consumer;

public class Receiver extends Thread {
    private MessageBox messageBox;

    public Receiver(MessageBox messageBox) {
        this.messageBox = messageBox;
        setDaemon(false);
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageBox.take();
                if (message == null) {
                    break;
                }
                System.out.printf("Thread: %s, message: %s\n", getName(), message);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}