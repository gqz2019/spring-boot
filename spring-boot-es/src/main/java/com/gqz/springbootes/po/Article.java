package com.gqz.springbootes.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-10-16 15:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    private Long id;
    private String content;
    private String title;
}
