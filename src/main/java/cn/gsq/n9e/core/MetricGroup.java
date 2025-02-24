package cn.gsq.n9e.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.AbstractMetricsGroup
 *
 * @author : gsq
 * @date : 2024-08-12 10:41
 * @note : It's not technology, it's art !
 **/
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetricGroup {

    private String id;    // 指标分组id

    private String name;    // 指标分组名称

    private MetricGroupType type;   // 指标分组类型
}
