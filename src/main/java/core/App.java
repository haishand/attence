package core;

import obj.Employee;
import obj.dd.DDDevice;
import obj.pcard.PCardDevice;

import java.util.HashMap;

public class App {
    HashMap<String, Employee> pcards;
    HashMap<String, Employee> ddcards;

    PCardDevice pcardDevice;
    DDDevice ddDevice;

    public App() {
        pcardDevice = new PCardDevice();
        ddDevice = new DDDevice();
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
