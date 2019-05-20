import com.fazecast.jSerialComm.SerialPort;

import java.io.*;

public class ComPort {
    private SerialPort comPort;
    private OutputStream output; // Where to write to
    private Writer writer;
    private PrintStream writer1; // what to write (byte, char or string. PrintStream does strings)
    private BufferedReader input;

    public void initialize() {
        // Create a serialport for your pc, check to see what serialport your Arduino is connected to.
        comPort = SerialPort.getCommPort("COM5"); // hardcoded comport, is this wise?

        try {
            // open the serialport, serialport can only be opened if there is no other program using it like the Arduino IDE.
            comPort.openPort();
            comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);
            // Create an outputstream.
            output = comPort.getOutputStream();
//            writer = new OutputStreamWriter(output,"ISO-8859-1");
            writer1 = new PrintStream(output);

            // Create an inputstream.
            input = new BufferedReader(new InputStreamReader(comPort.getInputStream()));

        } catch (Exception e) {
            System.out.println("Comport niet beschikbaar, bezet door Arduino IDE?");
            comPort.closePort(); // not sure we need this one, if the comport won't open you can't close it.
            System.exit(1); // Bye bye.
        }
    }
    // At this moment we only write int values to the Arduino.
    // TODO: we also want to write byte[]
//    public void writeOutput(int value) {
//        try {
////            output.write(value);
//            writer1.print("test");
//        }
////        catch (IOException e) {
//            catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    public void writeOutput(String command) {
        try {
            writer1.print(command);
        }
//        catch (IOException e) {
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeOutput(int value) {
        try {
            output.write(value);
        }
//        catch (IOException e) {
        catch (Exception e) {
            System.out.println(e);
        }
    }
    // Method to tell us what the Arduino is telling us.
    public String readInput() {
        try {
            return input.readLine();
        }
        catch (IOException e){
            return e.toString();
        }
    }
    // Clean up, if we stop the program we need to close the serialport.
    public synchronized void close() {
        if (comPort != null) {
            comPort.closePort();
        }
    }
}
