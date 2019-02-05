package test.keywordScripts;

public class UtilKeywordScript {
    protected boolean validateTestData(String testData,int numberOfParams) {
        try {
            String data[] = testData.split(",");
            if(data.length >=numberOfParams) return  true;
            return  false;
        } catch (Exception ex) {
            return  false;
        }

    }
}
