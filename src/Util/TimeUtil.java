/*
 *
 *********** Appointment Scheduler **********
 ************ Frank Michael Tofani ***********
 ************* WGU Class C195 Final ***********
 *
*/

package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class TimeUtil {
   
    public TimeUtil() {   
    }

    
/*
 *
 *   
 *  Most of these methods are useless. I was just thinking outloud so to speak.
 *  I haven't studied java.time yet and most likely that is what will be implemented.
 *
 *   
*/    


    public static String getUTCTime() {
        //yyyy-MM-dd HH:ii:ss
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDate = now.format(formatter);
        return formatDate;
    }
        
    public static int[] dateToIntArray (String date) {
        int[] dateArray = new int[5];
        String str1 = date.substring(0,4);
        String str2 = date.substring(5,7);
        String str3 = date.substring(8,10);
        String str4 = date.substring(12,14);
        String str5 = date.substring(16,18);
        dateArray[0] = Integer.parseInt(str1);
        dateArray[1] = Integer.parseInt(str2);
        dateArray[2] = Integer.parseInt(str3);
        dateArray[3] = Integer.parseInt(str4);
        dateArray[4] = Integer.parseInt(str5); 
        return dateArray;
    }     
        
    public static Date intToDate(int year, int month, int day, int hour, int min) throws ParseException {
        String str1 = Integer.toString(year);
        String str2 =  Integer.toString(month);
        String str3 = Integer.toString(day);
        String str4 =  " ";
        String str5 = Integer.toString(hour);
        String str6 = Integer.toString(min);
        String str7 = "00";
        String col = ":";
        String dash = "-";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(str1+dash+str2+dash+str3+str4+str5+col+str6+col+str7);
        return date;                       
    }
    
    public static Date stringToDate(String year, String month, String day, String hour, String min) throws ParseException {
        String col = ":";
        String dash = "-";
        
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(year+dash+month+dash+day+" "+hour+col+min+col+"00");
        return date;
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = simpleDateFormat.format(date);
        return strDate;        
    }
    
    public static String stringToString(String dat, String option) {
        String year = dat.substring(0,4);
        String month = dat.substring(5,7);
        String day = dat.substring(8,10);
        String date = dat.substring(0,10);
        String hour = dat.substring(12,14);
        String min = dat.substring(16,18);
        String time = dat.substring(012,18);
        switch (option) {
            case "year":
                return year;
            case "month":
                return month;
            case "day":
                return day;
            case "date":
                return date;
            case "hour":
                return hour;
            case "min":
                return min;
            case "time":
                return time;
            default:
                return "-1";
        }
    }    
        

//End Class        
}
