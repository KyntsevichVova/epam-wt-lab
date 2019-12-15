package com.kyntsevichvova.wtlab.bean;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    private String login;
    private String password;

}
