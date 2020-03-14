package com.ll.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="table_picture")
public class Picture {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id ;
    @Column
    private String url ;
    @Column
    private String name;
    @Column
    private Integer flag ;
    @Column
    private Date createTime ;
}
