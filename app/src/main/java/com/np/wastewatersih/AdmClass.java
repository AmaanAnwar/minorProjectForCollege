package com.np.wastewatersih;

public class AdmClass {
   private String name,email,phone,gid,gender,state,city;

    public AdmClass(String name, String email, String phone, String gid, String gender, String state, String city) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gid = gid;
        this.gender = gender;
        this.state = state;
        this.city = city;
    }
    public AdmClass()
    {

    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
