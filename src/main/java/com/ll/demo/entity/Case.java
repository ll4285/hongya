package com.ll.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="table_case")
public class Case {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id ;
    @Column
    private String name ;
    @Column
    private String url ;
    @Column
    private Date createTime ;
    @OneToOne
    @JoinColumn(name = "prcture_id")
    private Picture prcture ;
    @OneToOne
    @JoinColumn(name = "type_id")
    private Dictionary type ;
}
