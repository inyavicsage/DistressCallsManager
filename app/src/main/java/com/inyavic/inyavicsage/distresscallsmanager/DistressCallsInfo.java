package com.inyavic.inyavicsage.distresscallsmanager;

public class DistressCallsInfo {

    private int _id;
    private String type;
    private String priority;
    private String callerName;
    private String callerPhoneNum;
    private String callerLoc;
    private String description;
    private String recCallPath;
    private String datetimeReceived;

    protected DistressCallsInfo() {
    }

    protected DistressCallsInfo(String type, String priority, String callerName, String callerPhoneNum,
                                String callerLoc, String description, String recCallPath, String datetimeReceived) {
        this.type = type;
        this.priority = priority;
        this.callerName = callerName;
        this.callerPhoneNum = callerPhoneNum;
        this.callerLoc = callerLoc;
        this.description = description;
        this.recCallPath = recCallPath;
        this.datetimeReceived = datetimeReceived;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getCallerPhoneNum() {
        return callerPhoneNum;
    }

    public void setCallerPhoneNum(String callerPhoneNum) {
        this.callerPhoneNum = callerPhoneNum;
    }

    public String getCallerLoc() {
        return callerLoc;
    }

    public void setCallerLoc(String callerLoc) {
        this.callerLoc = callerLoc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecCallPath() {
        return recCallPath;
    }

    public void setRecCallPath(String recCallPath) {
        this.recCallPath = recCallPath;
    }

    public String getDatetimeReceived() {
        return datetimeReceived;
    }

    public void setDatetimeReceived(String datetimeReceived) {
        this.datetimeReceived = datetimeReceived;
    }
}
