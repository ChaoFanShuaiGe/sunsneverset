package model;

import lombok.Data;

import java.io.Serializable;


@Data
public class ShoppingCart implements Serializable {

    private int id;
    private int user_id;
    private int product_id;
    private int num;

}
