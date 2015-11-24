/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datacleaning.MaxEnt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

/**
 *
 * @author DELL
 */
public class CleaningPosTaggedCorpus {

    public static void clean() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.setProperty("file.encoding", "UTF-16");
        Field charset = Charset.class.getDeclaredField("defaultCharset");
        charset.setAccessible(true);
        charset.set(null, null);

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {

            File file = new File("output.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            String sCurrentLine;

            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            while ((sCurrentLine = br.readLine()) != null) {
                String[] input = sCurrentLine.split(" ");

                for (int i = 0; i < input.length; i++) {

                    String output = "";

                    if (input[i].length() >= 4 && input[i].substring(input[i].length() - 4).equals("NNPA")) {
                        String[] temp = input[i].split("_");
                        output = "<START:person>   " + temp[0] + "   <END> ";

                    } else {
                        String[] temp = input[i].split("_");
                        if (temp[0].equals(".")) {
                            output = temp[0] + "\n ";
                        } else {
                            output = temp[0] + " ";
                        }

                    }

                    bufferWritter.write(output);

                    System.out.println(output);

                }

            }

            bufferWritter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
