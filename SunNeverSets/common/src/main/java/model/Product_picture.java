package model;

import lombok.Data;

import java.io.Serializable;


@Data
public class Product_picture implements Serializable {

    private int id;
    private int product_id;
    private String product_picture;
    private String introduction;

}
