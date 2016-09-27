package com.gojek.parkinglot;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ParkingAssistant parkingAssistant = new ParkingAssistant();
        CommandParser commandParser = new CommandParser();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] inputValues = input.split(" ");

        // Interactive
        if(inputValues.length == 1 && inputValues[0].equals("my_program")) {
            input = br.readLine();
            String[] inputVals = input.split(" ");
            if(commandParser.parse(inputVals[0]) != CommandParser.CommandType.create_parking_lot) {
                System.out.println("Invalid Input");
                return;
            }
            else {
                CommandParser.CommandType command = commandParser.parse(inputVals[0]);
                String result = parkingAssistant.processCommand(command, inputVals);
                System.out.println(result);
            }

            while (true) {
                input = br.readLine();
                if(input.equals("exit")) {
                    break;
                }
                inputVals = input.split(" ");
                CommandParser.CommandType command = commandParser.parse(inputVals[0]);
                String result = parkingAssistant.processCommand(command, inputVals);
                System.out.println(result);
            }
        }
        // File input
        else if(inputValues.length == 4 && inputValues[0].equals("my_program") && inputValues[2].equals(">")) {
            final OutputStream os = new FileOutputStream(inputValues[3]);
            final PrintStream printStream = new PrintStream(os);
            String sCurrentLine;
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/file_inputs.txt"));
            input = bufferedReader.readLine();
            String[] inputVals = input.split(" ");
            if(commandParser.parse(inputVals[0]) != CommandParser.CommandType.create_parking_lot) {
                System.out.println("Invalid Input");
                return;
            }
            else {
                CommandParser.CommandType command = commandParser.parse(inputVals[0]);
                String result = parkingAssistant.processCommand(command, inputVals);
                printStream.print(result);
            }

            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                inputVals = sCurrentLine.split(" ");
                CommandParser.CommandType command = commandParser.parse(inputVals[0]);
                String result = parkingAssistant.processCommand(command, inputVals);
                printStream.print("\n" + result);
            }

            printStream.close();
        }
        else {
            System.out.println("Invalid Input");
        }
    }
}
