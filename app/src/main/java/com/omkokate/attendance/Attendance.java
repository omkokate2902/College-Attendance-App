package com.omkokate.attendance;

public class Attendance {
    private String subname;
    private String avg;
    private String avgth;
    private String avgpr;
    private String avg3d;
    private String avg7d;
    private String avgmonth;
    private int avgPB;
    private int avgthPB;
    private int avgprPB;
    private int avg3dPB;
    private int avg7dPB;
    private int avgmonthPB;

    public Attendance(){}

    public Attendance(String subname, String avg, String avgth, String avgpr, String avg3d, String avg7d, String avgmonth, int avgPB){
        this.subname = subname;
        this.avg = avg;
        this.avgth = avgth;
        this.avgpr = avgpr;
        this.avg3d = avg3d;
        this.avg7d = avg7d;
        this.avgmonth = avgmonth;
        this.avgPB = avgPB;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getAvgth() {
        return avgth;
    }

    public void setAvgth(String avgth) {
        this.avgth = avgth;
    }

    public String getAvgpr() {
        return avgpr;
    }

    public void setAvgpr(String avgpr) {
        this.avgpr = avgpr;
    }

    public String getAvg3d() {
        return avg3d;
    }

    public void setAvg3d(String avg3d) {
        this.avg3d = avg3d;
    }

    public String getAvg7d() {
        return avg7d;
    }

    public void setAvg7d(String avg7d) {
        this.avg7d = avg7d;
    }

    public String getAvgmonth() {
        return avgmonth;
    }

    public void setAvgmonth(String avgmonth) {
        this.avgmonth = avgmonth;
    }

    public int getAvgPB() {
        return avgPB;
    }

    public void setAvgPB(int avgPB) {
        this.avgPB = avgPB;
    }

    public int getAvgthPB() {
        return avgthPB;
    }

    public void setAvgthPB(int avgthPB) {
        this.avgthPB = avgthPB;
    }

    public int getAvgprPB() {
        return avgprPB;
    }

    public void setAvgprPB(int avgprPB) {
        this.avgprPB = avgprPB;
    }

    public int getAvg3dPB() {
        return avg3dPB;
    }

    public void setAvg3dPB(int avg3dPB) {
        this.avg3dPB = avg3dPB;
    }

    public int getAvg7dPB() {
        return avg7dPB;
    }

    public void setAvg7dPB(int avg7dPB) {
        this.avg7dPB = avg7dPB;
    }

    public int getAvgmonthPB() {
        return avgmonthPB;
    }

    public void setAvgmonthPB(int avgmonthPB) {
        this.avgmonthPB = avgmonthPB;
    }
}
