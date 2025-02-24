package cn.gsq.n9e.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.AlarmQueryConditions
 *
 * @author : gsq
 * @since : 2024-08-14 17:15

 **/
@Getter
@Setter
public class AlarmQueryConditions {

    private String content;     // 告警内容

    private String id;          // 指标id

    private AlarmLevel level;    // 告警级别

    private AlarmStatus status;  // 告警状态

}
