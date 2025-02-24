package cn.gsq.n9e.core.pojo;

import cn.gsq.n9e.core.Metric;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.pojo.Metric
 *
 * @author : gsq
 * @date : 2024-08-12 15:42
 * @note : It's not technology, it's art !
 **/
@Setter
@Getter
@Accessors(chain = true)
public class MetricDTO extends Metric {

    private Boolean running;    // 是否正在运行

    private Boolean canOps=true;    // 是否可以删除或者修改

    public MetricDTO(String name, String groupId) {
        super(name, groupId);
    }

}
