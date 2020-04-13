package com.example.employer;

public class ShortListing {
    String name;
    String location;
    String language;
    String Uid;
    String status;
    String employee_id;

    public ShortListing(String name, String location, String language, String uid,String employee_id,String status) {
        this.name = name;
        this.location = location;
        this.language = language;
      this.Uid = uid;
      this.employee_id=employee_id;
      this.status=status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ShortListing() {
    }
}
