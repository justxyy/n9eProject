package cn.gsq.n9e.core;

import cn.hutool.core.date.Week;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.AbstractMetrics
 *
 * @author : gsq
 * @since : 2024-08-12 10:54
 **/
@Getter
public class Metric {

    private final String name;    // 指标名称

    private final String groupId;    // 指标分组

    @Setter
    private String groupName;          // 指标归属的组名

    @Setter
    private String id;          // 指标id

    @Setter
    private AlarmLevel level;   // 告警级别

    @Setter
    private String expression;  // 指标表达式

    @Setter
    private Set<Week> days;    // 监控周期

    @Setter
    private String start;       // 开始时间

    @Setter
    private String end;         // 结束时间

    @Setter
    private String suggestion;  // 告警建议

    public Metric(String name, String groupId) {
        this.name = name;
        this.groupId = groupId;
    }

}
