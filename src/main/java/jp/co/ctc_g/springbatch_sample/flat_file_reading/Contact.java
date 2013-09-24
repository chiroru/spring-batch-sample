package jp.co.ctc_g.springbatch_sample.flat_file_reading;

import java.util.Date;

public class Contact {

    private Long id;
    
    private String firstname;
    
    private String lastname;
    
    private Date birth;
    
    public Contact(String firstname, String lastname, Date birth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth = birth;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirth() {
        return birth;
    }
    
    @Override
    public String toString() {
        return "Contact [id=" + id + ", firstname=" + firstname + ", lastname="
                + lastname + ", birth=" + birth + "]";
    }
}
