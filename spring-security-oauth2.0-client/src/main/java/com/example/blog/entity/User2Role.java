package com.example.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bty
 * @date 2022/10/2
 * @since 17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User2Role {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
