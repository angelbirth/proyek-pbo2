package pbo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Entry implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 687562616398077984L;
    private String id, name, birthplace, address, phoneNumber, email, job,
            company;
    private java.util.Date birthday;
    private char sex;

    public java.util.Date getBirthday() {
        return birthday;
    }

    public String getStringBirthday() {
        java.util.Date date = getBirthday();
        int y, m, d;
        y = date.getYear() + 1900;
        m = date.getMonth() + 1;
        d = date.getDate();
        return String.format("%d-%d-%d", y, m, d);
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getJob() {
        return job;
    }

    public String getCompany() {
        return company;
    }

    public char getSex() {
        return sex;
    }

    public void setId(String id) {
        if (id.matches("\\d+"))
            this.id = id;
        else
            throw new IllegalArgumentException("ID harus berupa nomor");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSex(char sex) {
        if (sex == 'F' || sex == 'M')
            this.sex = sex;
        else
            throw new IllegalArgumentException("masukan salah");
    }

    public String[] getDetail() {
        String sx = sex == 'F' ? "Female" : "Male";
        return new String[] { id, name, sx, birthplace,
                new SimpleDateFormat().format(birthday), address, phoneNumber,
                email, job, company };

    }
}
