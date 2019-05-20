//import com.fazecast.jSerialComm.*;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.util.Scanner;
//
///**
// * Voorbeeldcode van RXTX omgebouwd naar JSerial
// */
//public class SerialTestJSSC implements SerialPortDataListener {
//    private SerialPort comPort;
//    private BufferedReader input;
//    private OutputStream output;
//
//    public static void main(String[] args) throws Exception {
//        SerialTestJSSC serialTestJSSC = new SerialTestJSSC();
//        serialTestJSSC.close();
//        serialTestJSSC.initialize();
//        Thread t = new Thread() {
//            public void run() {
//                //the following line will keep this app alive for 1000 seconds,
//                //waiting for events to occur and responding to them (printing incoming messages to console).
//                try {
//                    Thread.sleep(1000000000);
//                }
//                catch (InterruptedException ie) {
//                }
//            }
//        };
//        t.start();
//
////        System.out.println("nullpointer: " + serialTestJSSC.input.readLine());
//        if (serialTestJSSC.input.readLine().equals("<Arduino is ready>")) {
////        if (!serialTestJSSC.input.readLine().isEmpty()) { // beter dan bovenstaande omdat zo ook andere input wordt geveangen
//            Scanner consoleIn = new Scanner(System.in); // console scanner maken
//            serialTestJSSC.consoleText(); // menu in console tonen
//            // hier opletten, deze blocked totdat hij input ontvangt, dan kan er niets van de arduino binnenkomen
//            // aan de andere kant, zou niet uit mogen maken, opdracht om output te geven altijd via huiscentrale laten lopen
//            while (consoleIn.hasNext()) {
////                String incoming = serialTestJSSC.input.readLine();
////                if (!incoming.isEmpty()) {
////                    System.out.println(incoming);
////                } else {
//                    int val = consoleIn.nextInt();
//                    if (val == 9) {
//                        consoleIn.close();
//                        serialTestJSSC.comPort.closePort();
//                        System.exit(1);
//                    } else {
//                        serialTestJSSC.output.write(val);
//                        serialTestJSSC.consoleText();
//                    }
////                }
//            }
//        } else {
//            System.out.println("Comport niet beschikbaar, bezet door Arduino IDE?");
//            System.out.println(serialTestJSSC.input.readLine());
//        }
//    }
//
//    public void initialize() {
////        SerialPort[] comPort2 = SerialPort.getCommPorts(); // get all comports for this pc
////        for (SerialPort ports : comPort2) {
////            System.output.println(ports.getDescriptivePortName());
////        }
//        comPort = SerialPort.getCommPort("COM5"); // hardcoded comport, is this wise?
////        comPort = SerialPort.getCommPorts()[2]; // hardcode the comport from the array, is this wise?
//        comPort.getDescriptivePortName();
//        if (comPort == null) {
//            System.out.println("Could not find COM port.");
//        }
//
//        try {
//            comPort.openPort();
//
//            comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);
//            input = new BufferedReader(new InputStreamReader(comPort.getInputStream()));
//            output = comPort.getOutputStream();
//
//            comPort.addDataListener(this);
//
//        } catch (Exception e) {
////            System.output.println("Could not open port because of: " + e);
//            System.out.println("Comport niet beschikbaar, bezet door Arduino IDE?");
//            comPort.closePort();
//            System.exit(1);
//
//        }
//    }
//
//    public synchronized void close() {
//        if (comPort != null) {
//            comPort.removeDataListener();
//            comPort.closePort();
//        }
//    }
//
//    public void consoleText() {
//        System.out.println("Please enter the number of your choice");
//        System.out.println("1. Blink all leds 10x");
//        System.out.println("2. Blink led 1 1x");
//        System.out.println("3. Blink led 2 2x");
//        System.out.println("4. Blink led 3 3x");
//        System.out.println("5. Blink led 4 4x");
//        System.out.println("6. Carnival");
//        System.out.println("9. Quit program");
//    }
//
//    @Override
//    public int getListeningEvents() {
//        return 0;
//    }
//
//    @Override
//    public void serialEvent(SerialPortEvent serialPortEvent) {
//        return;
//    }
//}
