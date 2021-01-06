package com.pb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author haohan
 * @date 2020/12/9 15:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private String age;

    private String address;

}
