package wang.l1n.platform.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
