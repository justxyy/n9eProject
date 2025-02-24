package cn.gsq.n9e.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.AlarmStatus
 *
 * @author : gsq
 * @date : 2024-08-12 17:15
 * @note : It's not technology, it's art !
 **/
@Slf4j
@Getter
@AllArgsConstructor
public enum AlarmStatus {

    UNSOLVED(0,"未处理"),

    SOLVED(1,"已处理");

    private final Integer id;

    private final String name;
    private static final Map<Integer , AlarmStatus> map = new HashMap<>();

    static {
        for (AlarmStatus alarmStatus : AlarmStatus.values()) {
            map.put(alarmStatus.id, alarmStatus);
        }
    }


    public static AlarmStatus parse(Integer id) {
        if(map.containsKey(id)) {
            return map.get(id);
        }
        return null;
    }
}
