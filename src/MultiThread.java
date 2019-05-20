public class MultiThread {
    private Thread t1;
    private Thread t2;

    public MultiThread() {
        // Instantiate and initialize a comport via class ComPort.
        ComPort comPort = new ComPort();
        comPort.initialize();
        // Create instances of arduinoOut and arduinoIn. Both get the same instance of comPort so that they communicate via the same line.
        ArduinoOut arduinoOut = new ArduinoOut(comPort, this);
        ArduinoIn arduinoIn = new ArduinoIn(comPort);
        // Start two threads for each class that needs one.
        t1 = new Thread(arduinoOut);
        t2 = new Thread(arduinoIn);
        t1.start();
        t2.start();
    }
    // method to stop the threads nice and neat.
     public void stopThreads() {
        t1.interrupt();
        t2.interrupt();
     }
}
