package model;

import lombok.Data;

import java.io.Serializable;


@Data
public class Category implements Serializable {

    private int category_id;
    private String category_name;

}
