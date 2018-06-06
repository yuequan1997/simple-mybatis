package org.yuequan.executor;

public interface Executor {
    <T> T query(String statement, String parameter);
}
