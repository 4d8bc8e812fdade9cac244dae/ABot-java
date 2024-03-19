package icu.yfd.listener;

@FunctionalInterface
public interface Events<T> {
    void perform(T eventData);
}
