package util;

import obj.pcard.PCardRec;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ExcelReader {

    public static Logger logger = Logger.getLogger(ExcelReader.class);


    public LinkedList<PCardRec> readPCard(File file) {
        LinkedList<PCardRec> records = null;

        try {
            Workbook wb = WorkbookFactory.create(file);
            Sheet sheet = wb.getSheetAt(0);
            int nrow = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(0);
            int ncol = row.getPhysicalNumberOfCells();
            records = new LinkedList<PCardRec>();
            for(int i=1; i<nrow; i++) {
                row = sheet.getRow(i);

                // 部门
                int j = 0;
                String dep = getStringCellValue(row.getCell(j));

                // 员工编号
                ++j;
                String id = getStringCellValue(row.getCell(j));

                // 姓名
                ++j;
                String name = getStringCellValue(row.getCell(j));

                // 考勤时间
                ++j;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date time = sdf.parse(getStringCellValue(row.getCell(j)));
                
                // 状态
                ++j;
                boolean state = getStringCellValue(row.getCell(j)).contains("上班");

                // currently ignore other fields

                PCardRec e = new PCardRec();
                e.setDepart(dep);
                e.setId(id);
                e.setName(name);
                e.setPunchTime(time);
                e.setState(state);

                records.add(e);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return records;
    }


    private String getStringCellValue(Cell cell) {
        String res = "";

        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                res = String.valueOf((int)cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                res = cell.getStringCellValue();
                break;
            default:
                // log it
                logger.error("Strange cell type: " + cell.getCellType());
                break;
        }

        return res;
    }


    public LinkedList<PCardRec> readDD(File file) {
        LinkedList<PCardRec> records = null;

        try {
            Workbook wb = WorkbookFactory.create(file);
            Sheet sheet = wb.getSheetAt(0);
            int nrow = sheet.getPhysicalNumberOfRows();
            System.out.println("dd row: " + nrow);
            Row row = sheet.getRow(3);
            int ncol = row.getPhysicalNumberOfCells();
            records = new LinkedList<PCardRec>();
            for(int i=4; i<nrow; i++) {
                row = sheet.getRow(i);

                // 部门
                int j = 0;
                String name = getStringCellValue(row.getCell(j));

                // 员工编号
                ++j;
                String id = getStringCellValue(row.getCell(j));

                // 姓名
                ++j;
                String dep = getStringCellValue(row.getCell(j));

                // currently ignore other fields
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return records;
    }

    public static void main(String[] args) {
        File file = new File("松立软件7月份钉钉信息.xls");
        ExcelReader er = new ExcelReader();
        LinkedList<PCardRec> records = er.readDD(file);
        er.cleanData(records);
        for(PCardRec rec : records) {
            System.out.println(rec);
        }
    }

    // test function
    private void cleanData(LinkedList<PCardRec> records) {
        PCardRec prior = null;
        Iterator<PCardRec> iter = records.iterator();
        while(iter.hasNext()) {
            PCardRec cur = iter.next();
            if(prior != null) {
                long diff = prior.getPunchTime().getTime() - cur.getPunchTime().getTime();
                if(cur.getName().equalsIgnoreCase(prior.getName()) &&
                        Math.abs(diff/60000)<1) {
                    iter.remove();
                }
            }
            prior = cur;
        }

    }
}
