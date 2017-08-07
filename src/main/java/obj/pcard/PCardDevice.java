package obj.pcard;

import obj.EmployInfo;
import org.apache.log4j.Logger;
import util.ExcelReader;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

// 打卡机
public class PCardDevice {

    private static String PREFIX = "考勤机_";
    private static long DUPLICATED_MIN_INTERVAL_HOUR = 1;
    public static Logger logger = Logger.getLogger(PCardDevice.class);

    ExcelReader excelReader = new ExcelReader();
    LinkedList<PCardRec> records;
    HashMap<String, EmployInfo> pcards;

    public PCardDevice() {
    }

    public void init() {
        read();
        clean();
        regulate();
    }

    private void read() {
        File dir = new File("");
        boolean found = false;
        File file = null;
        File[] files = dir.listFiles();
        for(int i=0; i<files.length; i++) {
            file = files[i];
            if(file.getName().startsWith(PREFIX)) {
                found = true;
                break;
            }
        }

        if(found) {
            records = excelReader.readPCard(file);
        }else {
            logger.debug("未找到考勤机的Excel表.");
        }

    }

    // remove duplicate punch card information in case someone punch card mult-times
    private void clean() {
        PCardRec prior = null;
        Iterator<PCardRec> iter = records.iterator();
        while(iter.hasNext()) {
            PCardRec cur = iter.next();
            if(prior != null) {
                long diff = prior.getPunchTime().getTime() - cur.getPunchTime().getTime();
                if(cur.getName().equalsIgnoreCase(prior.getDepart()) &&
                        Math.abs(diff/60000)< DUPLICATED_MIN_INTERVAL_HOUR) {
                    iter.remove();
                }
            }
            prior = cur;
        }

    }

    private void regulate() {
        for(PCardRec rec : records) {
            String name = rec.getName();
            Date time = rec.getPunchTime();
        }
    }
}

