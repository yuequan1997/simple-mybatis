package org.yuequan.mapper;

import org.yuequan.annotation.Select;
import org.yuequan.entity.User;

public interface UserMapper {
    @Select(sql = "select * from user where id = ?", result = User.class)
    User findById(int id);
}
