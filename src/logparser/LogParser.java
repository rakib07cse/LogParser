/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rakib
 */
public class LogParser {

    /**
     * @param args the command line arguments
     */
    private static final String logtxt = "20170201210506187 INFO - R 1b5eb5b0-e8c2-11e6-aeba-816202914bb1 getMyFriends - {\"userId\":28,\"userUpdateTime\":1485862991848,\"contactUpdateTime\":1485932434083,\"countryCode\":\"+880\"}";
    private static Pattern logPattern = Pattern.compile(
            "^(\\d{17})\\s+(\\w+)\\s*-?\\s*(R|\\d+)\\s+([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})(\\s+(\\w+)\\s*-?\\s*\\{{1}(.*?)\\}{1})?$");

    private static Pattern logPattern1 = Pattern.compile("^(\\d{17})\\s+(ERROR|WARN|FATAL|INFO)\\s+-?\\s*(R|((LiveStreamHistory)->(.*})|.*\\))(\\s+.*)?)(\\s+([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})\\s(\\w+)\\s*-?(.*))?$");

    private static Pattern keyValuePattern = Pattern.compile("\"?(\\w+)\"?\\s*:\\s*\"?(\\w+)\"?");

    private static String datePattern = "yyyyMMddHHmmssSSS"; // e.g. 20170201210453812
    private static final Logger logger = Logger.getLogger("LogParser");

    public List<EventLog> sendFileForProcessing(File file) {

        String currentLine;
        FileReader fr;
        BufferedReader br;
        List<EventLog> listEventLog = new ArrayList<>();
        EventLog eventLog;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((currentLine = br.readLine()) != null) {
                eventLog = logParse(currentLine);
                if (eventLog != null) {
                    listEventLog.add(eventLog);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEventLog;
    }

    public static EventLog logParse(String logtxt) {

        EventLog eventLog = new EventLog();

        Matcher logMatcher = logPattern1.matcher(logtxt);

        if (!logMatcher.matches()) {
            logger.warning("Log txt can't be matched with any matchers.");
            return null;
        }

        int groupCount = logMatcher.groupCount();
        if (groupCount >= 1) {
            String timestamp = logMatcher.group(1);
            eventLog.setTimeStamp(timestamp);

        }
        if (groupCount >= 2) {
            String logLevel = logMatcher.group(2);
            eventLog.setLogLevel(logLevel);
        }
        if (groupCount >= 4) {
            String eventType = logMatcher.group(4);
            eventLog.setEventType(eventType);

        }
        if (groupCount >= 5) {
            String liveStreamHistory = logMatcher.group(5);
            eventLog.setLiveStreamHistroy(liveStreamHistory);
        }
        if (groupCount >= 6) {
            String liveStreamParams = logMatcher.group(6);
            eventLog.setLiveStreamParams(liveStreamParams);
        }
        if (groupCount >= 9) {
            String requestId = logMatcher.group(9);
            eventLog.setRquestId(requestId);
        }
        if (groupCount >= 10) {
            String methodName = logMatcher.group(10);
            eventLog.setMethodName(methodName);
        }

        if (groupCount >= 11) {
            String funcArgs = logMatcher.group(11);
            eventLog.setParams(funcArgs);
        }

        //  System.out.println("TimeStamp: " + timestamp + " LogLevel: " + logLevel + " Eventtype: " + eventType + " RequetsId: " + requestId + " MethodName: " + methodName + " FunctionArgs: " + funcArgs);
        return eventLog;
    }

//    public static void main(String[] args) {
//        // TODO code application logic here
//        logParse(logtxt);
//    }
}
