package cn.gsq.n9e.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.pojo.AlarmGroupType
 *
 * @author : gsq
 * @since : 2024-08-12 10:36
 **/
@Slf4j
@Getter
@AllArgsConstructor
public enum MetricGroupType {

    HOST("集群主机"),

    SERVE("组件服务"),

    CUSTOM("自定义");

    private final String name;

    private static final Map<String , MetricGroupType> map = new HashMap<>();

    static {
        for (MetricGroupType metricGroupType : MetricGroupType.values()) {
            map.put(metricGroupType.name, metricGroupType);
        }
    }

   // 枚举对象

    public static MetricGroupType parse(String name) {
        if(map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }

}
