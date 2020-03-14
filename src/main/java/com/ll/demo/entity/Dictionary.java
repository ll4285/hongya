package com.ll.demo.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="table_dictionary")
public class Dictionary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id ;
    @Column
    private String type ;
    @Column
    private String code ;
    @Column
    private String name ;
    @Column
    private Integer pid ;
}
