package ru.babagay.jdbc;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DBinsert {

    public static void main(String[] args) {

        String file = "users.txt";

        try (
                BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( file ) )
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
