package telran.producer.consumer;

public class Main {
    private static final int N_MESSAGES = 20;
    static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBoxEven = new SimpleMessageBox();
        MessageBox messageBoxOdd = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, messageBoxEven, messageBoxOdd);

        for (int i = 0; i < N_RECEIVERS; i++) {
            Receiver receiver = new Receiver(null);
            MessageBox messageBox = isReceiverEven(receiver) ? messageBoxEven : messageBoxOdd;
            receiver.setMessageBox(messageBox);
            receiver.start();
        }
        sender.start();
        sender.join();

        Thread.sleep(100);

    }

    private static boolean isReceiverEven(Receiver receiver) {
        long id = receiver.getId();
        return id % 2 == 0;
    }
}