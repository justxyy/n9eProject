package cn.gsq.n9e.core.pojo;

import cn.gsq.n9e.core.AlarmLevel;
import cn.gsq.n9e.core.AlarmStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.pojo.AlarmInfoDTO
 *
 * @author : gsq
 * @since : 2024-08-14 16:38
 **/
@Setter
@Getter
@Accessors(chain = true)
public class AlarmInfoDTO {

    private String id;  // 告警信息id

    private String metric;  // 告警指标名称

    private AlarmLevel level;   // 告警级别

    private String startTime;   // 首次告警时间

    private String endTime;     // 末次告警时间

    private String solveTime;    // 处理时间

    private AlarmStatus status;  // 告警状态

    private String content;     // 告警内容

    private String solveInfo;   // 处理说明

    private String groupId;//告警组id

    private String groupName;//告警组名称

    private String hostName;//告警所在的主机

}
