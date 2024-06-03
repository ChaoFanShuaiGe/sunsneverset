package model;

import lombok.Data;

@Data
public class Address {
    private int id;
    private String name;
    private String phone;
    private String address;
    private int user_id;
}
