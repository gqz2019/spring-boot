package com.gqz.springbootprovider.pojo;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "linkman")
@Entity
public class Linkman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String sex;
    private long age;
    private String address;
    private String qq;
    private String email;

}
