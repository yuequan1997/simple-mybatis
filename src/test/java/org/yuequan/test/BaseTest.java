package org.yuequan.test;

import org.yuequan.config.Configuration;
import org.yuequan.entity.User;
import org.yuequan.executor.SimpleExecutor;
import org.yuequan.mapper.UserMapper;
import org.yuequan.session.SqlSession;

public class BaseTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test_db?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC","root","123456","org.yuequan.mapper");
        SqlSession sqlSession = new SqlSession(configuration, new SimpleExecutor(configuration));
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(1);
        System.out.println(user.getUsername());
    }
}
