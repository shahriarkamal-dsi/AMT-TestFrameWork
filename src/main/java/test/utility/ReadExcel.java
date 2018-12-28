package test.utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcel {

    private String filePath ;


    public ReadExcel(String path){
        this.filePath = path ;
    }
    public List<Map> read(String sheetName) {
        try {
            File excelFile = new File(filePath);
            FileInputStream fis = new FileInputStream(excelFile);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workBook.getSheet(sheetName);
            Iterator<Row> rowIt = sheet.iterator();
            boolean firstRow = true ;
            String[] columnNames = new String[10] ;
            List<Map> records = new ArrayList<Map>() ;

            while(rowIt.hasNext()) {
                Row row = rowIt.next();

                // iterate on cells for the current row
                Iterator<Cell> cellIterator = row.cellIterator();
                int index = 0;
                Map record = new HashMap();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if(firstRow){
                        columnNames[index++]=cell.toString() ;
                    } else {
                        record.put(columnNames[index++],cell.toString());
                    }
                }
                if(firstRow)
                    firstRow = false ;
                else {
                    records.add(record);
                }
            }
            workBook.close();
            fis.close();
            return records ;

        } catch (Exception ex){
            ex.printStackTrace();
            return null ;

        }

    }

}
