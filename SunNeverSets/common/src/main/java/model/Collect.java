package model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class Collect implements Serializable {

    private int id;
    private int user_id;
    private int product_id;
    private String collect_time;
    private String product_picture;
    private String product_name;
    private String product_title;
    private BigDecimal product_selling_price;
    private BigDecimal product_price;

}
