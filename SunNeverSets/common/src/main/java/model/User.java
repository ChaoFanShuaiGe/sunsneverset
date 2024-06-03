package model;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {

    private int user_id;
    private String userName;
    private String password;
    private String userPhoneNumber;
    private String email;
    private String gender;
    private String login_count;
    private String register_time;

}
