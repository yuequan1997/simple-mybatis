package org.yuequan.test;

import org.yuequan.config.Configuration;
import org.yuequan.executor.Executor;
import org.yuequan.executor.SimpleExecutor;
import org.yuequan.mapper.DemoMapper;
import org.yuequan.session.SqlSession;

public class BaseTest {
    public static void main(String[] args) {
        SqlSession sqlSession = new SqlSession(new Configuration(), new SimpleExecutor());
        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
        mapper.findById(1);
    }
}
