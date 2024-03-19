package icu.yfd.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager<T> {
    public Map<String, List<Events<T>>> eventMap = new HashMap<>();

    public EventManager() {}

    public void add(String name, Events<T> action) {
        eventMap.computeIfAbsent(name, k -> new ArrayList<>()).add(action);
    }

    @SafeVarargs
    public final void emit(String eventName, T... eventData) {
        List<Events<T>> actions = eventMap.get(eventName);
        if (actions != null) {
            for (Events<T> action : actions) {
                for (T data : eventData) {
                    action.perform(data);
                }
            }
        }
    }

    public final void clear() {
        eventMap.clear();
    }
}