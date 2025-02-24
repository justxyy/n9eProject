package cn.gsq.n9e.core.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.core.pojo.N9eMetric
 *
 * @author : xyy
 * @date : 2024-09-11 10:08
 * @note : It's not technology, it's art !
 **/
@Setter
@Getter
@Accessors(chain = true)
public class N9eMetric {
    private int id;
    private String uuid;
    private String collector;
    private String typ;
    private String name;
    private String unit;
    private String note;
    private String lang;
    private String expression;
    private String created_at;
    private String created_by;
    private String updated_at;
    private String updated_by;

}
