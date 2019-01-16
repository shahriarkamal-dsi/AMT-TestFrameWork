package test.utility;

public class LogMessage {
    private Boolean passed = false ;  ;
    private String logMessage = "" ;

    public LogMessage(){

    }

    public LogMessage(Boolean passed,String logMeassge){
        this.passed = passed ;
        this.logMessage = logMeassge ;
    }

    public Boolean isPassed(){
        return passed ;
    }

    public void setPassed(Boolean passedValue) {
        this.passed = passedValue ;
    }


    public String getLogMessage(){
        return logMessage ;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage ;
    }
}
