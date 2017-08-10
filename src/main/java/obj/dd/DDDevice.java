package obj.dd;

import org.apache.log4j.Logger;
import util.ExcelReader;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

// 钉钉
public class DDDevice {

    private static final String PREFIX = "钉钉_";
    private static long DUPLICATED_MIN_INTERVAL_HOUR = 1;
    public static Logger logger = Logger.getLogger(DDDevice.class);
    private ExcelReader excelReader = new ExcelReader();
    private LinkedList<DDRecord> records;

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
            records = excelReader.readDD(file);
        }else {
            logger.debug("未找到考勤机的Excel表.");
        }
    }

    private void clean() {
        DDRecord prior = null;
        Iterator<DDRecord> iter = records.iterator();
        while(iter.hasNext()) {
            DDRecord cur = iter.next();
            if(prior != null) {
                long diff = prior.getPtime().getTime() - cur.getPtime().getTime();
                if(cur.getName().equalsIgnoreCase(prior.getName()) &&
                        Math.abs(diff/60000)< DUPLICATED_MIN_INTERVAL_HOUR) {
                    iter.remove();
                }
            }
            prior = cur;
        }
    }

    private void regulate() {

    }
}
