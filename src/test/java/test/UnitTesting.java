package test;

import org.junit.Test;
import test.objectLocator.OrRead;
import test.utility.ReadExcel;

import java.util.List;
import java.util.Map;

public class UnitTesting {

    @Test
    public void readExcelsheetTest(){
        ReadExcel readExcel = new ReadExcel("G:\\testing\\AMTDirect_NousAutomation\\Framework\\Modules\\Fasb2.xlsx");
        List<Map> records = readExcel.read("TC001_TC050");
        System.out.println(records);
    }
    @Test
    public void orReadTesting(){
        OrRead orRead  = new OrRead("OR_PI.RPRCharge.txtndLinkedForms");
        List<Map> records = orRead.getOrFromSheet();
        System.out.println(records);
    }

}
