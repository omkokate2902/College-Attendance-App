package com.omkokate.attendance;

public class PA {
    private String time;
    private String subject;
    private String presenty;
    private String priority;

    public PA(String time, String subject, String presenty,String priority) {
        this.time = time;
        this.subject = subject;
        this.presenty = presenty;
        this.priority = priority;
    }

    public PA() {}

    public void setTime(String time) {
        this.time = time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPresenty(String presenty) {
        this.presenty = presenty;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public String getPresenty() {
        return presenty;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
