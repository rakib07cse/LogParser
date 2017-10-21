/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author rakib
 */
public class EventLog {

    private String timeStamp;
    private String logLevel;
    private String eventType;
    private String requestId;
    private String methodName;
    private String liveStreamParams;
    private String liveStreamHistory;
    private String params;

    public EventLog() {
    }

    public EventLog(String timeStamp, String logLevel, String eventType, String liveStreamHistory, String liveStreamParams, String requetId, String methodName, String params) {

        this.timeStamp = timeStamp;
        this.logLevel = logLevel;
        this.eventType = eventType;
        this.liveStreamHistory = liveStreamHistory;
        this.liveStreamParams = liveStreamParams;
        this.requestId = requetId;
        this.methodName = methodName;
        this.params = params;
    }

    public EventLog(String timeStamp, String logLevel, String eventType) {
        this.timeStamp = timeStamp;
        this.logLevel = logLevel;
        this.eventType = eventType;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;

    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setLiveStreamHistroy(String liveStreamHistory) {
        this.liveStreamHistory = liveStreamHistory;
    }

    public String getLiveStreamHistory() {
        return liveStreamHistory;
    }

    public void setLiveStreamParams(String liveStreamParams) {
        this.liveStreamParams = liveStreamParams;
    }

    public String getLiveStreamParams() {
        return liveStreamParams;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRquestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getParams() {
        return params;
    }

}
