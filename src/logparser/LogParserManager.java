/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rakib
 */
public class LogParserManager {

    private String dirName;

    private List<File> fileToBeProcessed = new ArrayList<>();

    public void scanDirectory(String dirName) {
        File logDir = new File(dirName);
        addLogFileToList(logDir);
        EventLog eventLog = new EventLog();
        LogParser logParser = new LogParser();
        List<EventLog> listEventLog = new ArrayList<>();
        for(File file:fileToBeProcessed){
            listEventLog = logParser.sendFileForProcessing(file);
            sendForEventLogPrint(listEventLog);
        }
        

    }

    private void addLogFileToList(File logDir) {
        for(File file:logDir.listFiles()){
            if(file.isDirectory()){
                addLogFileToList(file);
            }
            if(file.canRead() && file.isFile()){
                fileToBeProcessed.add(file);
            }
        }
    }

    private void sendForEventLogPrint(List<EventLog> listEventLog) {
        for(EventLog eventLog: listEventLog){
            System.out.println("Time: "+ eventLog.getTimeStamp()+ " LogLevel: "+ eventLog.getLogLevel()+" Method:"+eventLog.getMethodName()+" LiveStream: "+ eventLog.getLiveStreamHistory());
        }
    }
}
