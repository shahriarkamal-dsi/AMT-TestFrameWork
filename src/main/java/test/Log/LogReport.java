package test.Log;

import test.coreModule.TestCase;

import java.util.List;

public class LogReport {

    private static final LogReport LogReportSingleton = new LogReport();
    private static final CreateLog PassedLogReport = new CreateLog("PassedTCReport");;
    private static final CreateLog FailedLogReport =  new CreateLog("FailedTCReport");  ;

    private LogReport(){

    }

    public static LogReport getInstance(){
        return LogReportSingleton ;
    }

    public void addTestcaseLogreport(TestCase testCase, List<LogMessage> logmessages ) {
        CreateLog createLog = null;
        if(testCase.isPassed() || !testCase.isPassed())
        {
            createLog = PassedLogReport;
        }
        if(!testCase.isPassed())
            createLog = FailedLogReport;
        String logName = testCase.getTestCaseNumber() + "--" + testCase.getTestCaseName() ;
        createLog.createLogger(logName );
        for(LogMessage logMessage : logmessages) {
            createLog.writeLog(logName,logMessage.getLogMessage(),logMessage.isPassed(),logMessage.isSkipped());
        }
    }
}
