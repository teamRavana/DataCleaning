/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datacleaning;

import datacleaning.MaxEnt.CleaningBratOutput;

/**
 *
 * @author DELL
 */
public class DataCleaning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        CleaningBratOutput cleaningBratOutput = new CleaningBratOutput();
        cleaningBratOutput.clean();
                
    }
}
