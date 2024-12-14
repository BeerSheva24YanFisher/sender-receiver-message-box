package telran.producer.consumer;

public class Receiver extends Thread {
    private final MessageBox messageBox;
    private final int threadIndex;

    public Receiver(MessageBox messageBox, int threadIndex) {
        this.messageBox = messageBox;
        this.threadIndex = threadIndex;
        setDaemon(false);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageBox.take();
                if (message == null) {
                    break;
                }

                int messageNumber = extractMessageNumber(message);

                if ((threadIndex % 2 == 0 && messageNumber % 2 == 0) ||
                    (threadIndex % 2 != 0 && messageNumber % 2 != 0)) {
                    System.out.printf("Thread: %s (Index: %d), message: %s\n", getName(), threadIndex, message);
                } else {
                    messageBox.put(message);
                }

            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    private int extractMessageNumber(String message) {
        try {
            return Integer.parseInt(message.replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}