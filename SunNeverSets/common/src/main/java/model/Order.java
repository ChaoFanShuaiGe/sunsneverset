package model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data

public class Order implements Serializable {

    private Long id;
    private Long order_id;
    private int product_num;
    private int product_id;
    private BigDecimal product_price;
    //    private Date order_time;
    //   private Date pay_time;
    private String order_time;
    private String pay_time;
    private int status;   //0,失效;1,生效
    private String product_name;
    private String product_picture;
    private int pay_status;

    private int user_id;
    private String userName;
}

