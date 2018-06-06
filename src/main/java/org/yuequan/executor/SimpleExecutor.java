package org.yuequan.executor;

public class SimpleExecutor implements Executor {
    @Override
    public <T> T query(String statement, String parameter) {
        //TODO
        System.out.println(statement);
        return null;
    }
}
