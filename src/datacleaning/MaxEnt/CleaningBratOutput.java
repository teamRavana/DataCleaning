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
import java.lang.reflect.Field;
import java.nio.charset.Charset;

/**
 *
 * @author DELL
 */
public class CleaningBratOutput {

    public void clean() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        System.setProperty("file.encoding", "UTF-8");
        Field charset = Charset.class.getDeclaredField("defaultCharset");
        charset.setAccessible(true);
        charset.set(null, null);

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            File file = new File("output.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            String sCurrentLine;

            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);

                String[] input = sCurrentLine.split("\t");
                String output = "";
                if (input.length == 2) {

                    if (input[1].equals("PER")) {

                        output = "<START:person>   " + input[0] + "   <END> ";

                    } else if (input[1].equals("O") || input[1].equals("NE")) {

                        if (input[0].equals(".")) {
                            output = input[0] + "\n ";
                        } else {
                            output = input[0] + " ";
                        }

                    } else if (input[1].equals("ORG")) {

                        output = "<START:organization>   " + input[0] + "   <END> ";

                    } else if (input[1].equals("LOC")) {

                        output = "<START:location>   " + input[0] + "   <END> ";

                    }

                } else {

                    System.out.println("********************" + input.length);

                }

                bufferWritter.write(output);

                System.out.println(output);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
