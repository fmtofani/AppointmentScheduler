/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package Util;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

/**
 *
 * @author micha
 */
public class LoggerUtil {
    //private static final String LOGFILE = "C195_log.txt";
    
    public LoggerUtil() {
    }
    
//verify if file exists     
   public static void addEntry (String user, boolean loggedIn) throws IOException {
       //See if file exists
        String logFile = "C195_log.txt";
        File tmpFile = new File(logFile);
        Boolean exists = tmpFile.exists();
        if(exists == true){
            //Append logFile if a file already exists
            try (FileWriter fileWriter = new FileWriter(logFile, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
                 printWriter.println(TimeUtil.getUTCTime() + " " + user + " " + (loggedIn ? "Succeed" : "Failed"));
            } catch (IOException ex) {
                    System.out.println("Error creating log file \n Error Message: " + ex.getMessage());
            }
          } else {
            try (FileWriter fileWriter = new FileWriter(logFile);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
                 printWriter.println("Acme Appointment Scheduler Access Log. Written by Frank Michael Tofani"
                                     +TimeUtil.getUTCTime() + " " + user + " " + (loggedIn ? "Succeed" : "Failed"));
            } catch (IOException ex) {
                    System.out.println("Error creating log file \n Error Message: " + ex.getMessage());
            }
 
           }

    }
    
    
    
}
