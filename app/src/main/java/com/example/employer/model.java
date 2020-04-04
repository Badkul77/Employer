package com.example.employer;

public class model {

    String jobtitle,companynamem,description,timeofreporting,duratin,date,rupee,job;

    public model(String jobtitle, String companynamem, String description, String timeofreporting, String duratin, String date, String rupee,String job) {
        this.jobtitle = jobtitle;
        this.companynamem = companynamem;
        this.description = description;
        this.timeofreporting = timeofreporting;
        this.duratin = duratin;
        this.date = date;
        this.rupee = rupee;
        this.job=job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getCompanynamem() {
        return companynamem;
    }

    public void setCompanynamem(String companynamem) {
        this.companynamem = companynamem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeofreporting() {
        return timeofreporting;
    }

    public void setTimeofreporting(String timeofreporting) {
        this.timeofreporting = timeofreporting;
    }

    public String getDuratin() {
        return duratin;
    }

    public void setDuratin(String duratin) {
        this.duratin = duratin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRupee() {
        return rupee;
    }

    public void setRupee(String rupee) {
        this.rupee = rupee;
    }
}
