package cn.gsq.n9e.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.AlarmLevel
 *
 * @author : gsq
 * @since : 2024-08-12 17:15
 **/
@Slf4j
@Getter
@AllArgsConstructor
public enum AlarmLevel {

    SERIOUS(1,"严重"),

    WARN(2,"警告"),

    NORMAL(3,"一般");

    private final Integer id;

    private final String name;

    private static final Map<Integer , AlarmLevel> map = new HashMap<>();

    static {
        for (AlarmLevel alarmLevel : AlarmLevel.values()) {
            map.put(alarmLevel.id, alarmLevel);
        }
    }


    public static AlarmLevel parse(Integer id) {
        if(map.containsKey(id)) {
            return map.get(id);
        }
        return null;
    }
}
