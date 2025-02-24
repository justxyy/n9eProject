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
 * @date : 2024-08-12 10:36
 * @note : It's not technology, it's art !
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

    /**
     * @Description : 枚举对象
     * @param java.lang.String name : 对象名称
     * @Return : cn.gsq.n9e.core.MetricGroupType
     * @Author : gsq
     * @Date : 2024/8/12 15:59
     * @Note : An art cell !
     **/
    public static MetricGroupType parse(String name) {
        if(map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }

}
