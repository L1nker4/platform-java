package wang.l1n.platform.common.function;


import wang.l1n.platform.common.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
