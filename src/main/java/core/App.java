package core;

import obj.EmployInfo;
import obj.dd.DingDing;
import obj.pcard.PCardDevice;
import obj.pcard.PCardInfo;

import java.util.HashMap;

public class App {
    HashMap<String, EmployInfo> pcards;
    HashMap<String, EmployInfo> ddcards;

    PCardDevice pcardDevice;
    DingDing ddDevice;

    public App() {
        pcardDevice = new PCardDevice();
        ddDevice = new DingDing();
    }

    public void run() {
        init();
        merge();
        generate();
    }

    private void init() {
        pcardDevice.init();
        ddDevice.init();

//        pcards = pcardDevice.getRecords();
//        ddcards = ddDevice.getRecords();
    }

    private void merge() {
    }

    private void generate() {

    }
}
