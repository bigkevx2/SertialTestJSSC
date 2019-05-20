import java.util.Scanner;

public class ArduinoOut implements Runnable { // implements Runnable to work with threads.
    private ComPort comPort;
    // In this class we want access to the threads for stopping them. Not sure if this is necessary.
    private MultiThread multiThread;
    // constructor that gives this class the same instance of comport as all other serialport communicating classes.
    // it will also get an instance of MultiThread.
    public ArduinoOut(ComPort comPort, MultiThread multiThread) {
        this.comPort = comPort;
        this.multiThread = multiThread;
    }
    // Show a console menu with options to work with your Arduino.
    public void consoleText() {
        System.out.println("Please enter the number of your choice");
        System.out.println("1. Blink all leds 10x");
        System.out.println("2. Blink led 1 1x");
        System.out.println("3. Blink led 2 2x");
        System.out.println("4. Blink led 3 3x");
        System.out.println("5. Blink led 4 4x");
        System.out.println("6. Carnival");
        System.out.println("7. Hello World!");
        System.out.println("8. Beep");
        System.out.println("9. Potmeter");
        System.out.println("0. Quit program\n");
    }
    // the run method is used for multithreading, if this thread starts it kicks off run.
    @Override
    public void run() {
        // Create a scanner to catch console input
        Scanner consoleIn = new Scanner(System.in);
        consoleText(); // show the console menu
            // keep listening to console input as long as the thread is alive.
            while (consoleIn.hasNext() && !Thread.interrupted()) {
                int val = -1;
                String command = "";
                // TODO: we also want to be able to process strings.
                try {
                    val = consoleIn.nextInt(); // write output with ints
                } catch (Exception e) {
                    command = consoleIn.nextLine();
                }
//                String command = consoleIn.nextLine(); // write output with strings
                // close the application on input 0.
                if (val == 0) {
                    consoleIn.close();
                    comPort.close();
                    multiThread.stopThreads();
                    System.exit(1);
                } else {
                    try {
                        // test to see if multiThread.stopThreads does indeed stop the threads.
//                        if (val == 11) {
//                            multiThread.stopThreads();
//                        }
                        // write to the Arduino with the method in class Comport, write with ints or Strings
                        if (val != -1) {
                            comPort.writeOutput(val);
                            System.out.println(val);
                        } else {
                            comPort.writeOutput(command);
                            System.out.println(command);
                        }
                        // show the menu after each entry in the console.
//                        consoleText();
                    } catch (Exception e) {
                        System.out.println("kan niet schrijven");
                    }
                }
            }
        }
}
