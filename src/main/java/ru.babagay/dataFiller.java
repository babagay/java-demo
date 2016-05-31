package ru.babagay;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class dataFiller {

    private static Stream<String> reader;
    private static Path path;
    private static String fileName07 = "./src/main/resources/names.txt";
    private static String url01 = "http://www.behindthename.com/names/usage/english";

    private ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        path = Paths.get(fileName07);
        initStreamReader();

    }

    private void scanUrl(String url) throws IOException {

        URL oracle = new URL(url01);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }

    /**
     * Write string in file
     * @param fileName
     */
    static void fill(String fileName, String Name, String Surname){

        Random random = new Random();

        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bufferedWriter = new BufferedWriter(fw)
        ) {

            //andom.nextInt()
             bufferedWriter.write( Name + " " + Surname + "\n" );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void initStreamReader(){

        try {
            reader = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBuffer(String str){



        try (
                BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( fileName07 ) )
        ) {
//            for (Integer e : arrayList) {
//                bufferedWriter.write( result.substring(0, e) );
//                bufferedWriter.newLine();
//                result = result.substring( e );
//            }



        } catch (                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
