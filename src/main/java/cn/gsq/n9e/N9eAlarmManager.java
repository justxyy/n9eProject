package cn.gsq.n9e;

import cn.gsq.n9e.core.*;
import cn.gsq.n9e.core.pojo.AlarmInfoDTO;
import cn.gsq.n9e.core.pojo.MetricDTO;
import cn.hutool.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.N9eAlarmManager
 *
 * @author : gsq
 * @date : 2024-08-12 10:01
 * @note : It's not technology, it's art !
 **/
public interface N9eAlarmManager {

    /**
     * @Description : 新增/修改指标分组
     * @param cn.gsq.n9e.core.pojo.MetricGroup group : 主机分组实例
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/12 16:07
     * @Note : ⚠️ 创建失败抛出异常 !
     **/
    void updateMetricGroup(MetricGroup group);

    /**
     * @Description : 删除指标分组
     * @param java.lang.String id : 分组id
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/12 16:58
     * @Note : ⚠️ 删除失败抛出异常 !
     **/
    void removeMetricGroup(String id);

    /**
     * @Description : 获取全部指标分组
     * @param   :
     * @Return : java.util.List<cn.gsq.n9e.core.MetricGroup>
     * @Author : gsq
     * @Date : 2024/8/12 16:43
     * @Note : An art cell !
     **/
    List<MetricGroup> getMetricGroups();

    /**
     * @Description : 新增或修改指标元数据
     * @param cn.gsq.n9e.core.Metric metric : 指标实例
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/14 16:28
     * @Note : ⚠️ 更新失败抛出异常 !
     **/
    void updateMetric(Metric metric);

    /**
     * @Description : 根据id删除指标
     * @param java.lang.String id : 指标id
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/12 17:04
     * @Note : ⚠️ 删除失败抛出异常 !
     **/
    void removeMetric(String id);

    /**
     * @Description : 获取全部指标
     * @Return : java.util.List<cn.gsq.n9e.core.pojo.MetricDTO>
     * @Author : gsq
     * @Date : 2024/8/14 16:23
     * @Note : An art cell !
     **/
    List<MetricDTO> getMetrics();

    /**
     * @Description : 根据分组id获取下面所有的指标实例
     * @param java.lang.String id : 分组id
     * @Return : java.util.List<cn.gsq.n9e.core.Metric>
     * @Author : gsq
     * @Date : 2024/8/14 16:32
     * @Note : An art cell !
     **/
    List<MetricDTO> getMetrics(String id);

    /**
     * @Description : 获取内置指标的元数据信息
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/9/9
     * @note : An art cell !
     **/
    Map<String,Object> getBuiltinMetadata();

    /**
     * @Description : 查询内置指标的信息
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/9/9
     * @note : An art cell !
     **/
    Map<String,Object> getBuiltinMetric(String collector,String typ,String query,String pageNum,String pageSize);

    /**
     * @Description : 获取内置指标列表
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/9/9
     * @note : An art cell !
     **/
    List<String> getBuiltinMetricList();

    /**
     * @Description : 根据指标id获取指标
     * @param java.lang.String id : 指标id
     * @Return : cn.gsq.n9e.core.pojo.MetricDTO
     * @Author : gsq
     * @Date : 2024/8/14 16:33
     * @Note : An art cell !
     **/
    MetricDTO getMetric(String id);

    /**
     * @Description : 运行指标监控
     * @param java.lang.String id : 指标id
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/14 16:34
     * @Note : ⚠️ 有异常要抛出 !
     **/
    void run(String id);

    /**
     * @Description : 停止指标监控
     * @param java.lang.String id :
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/20 10:37
     * @Note : ⚠️ 有异常要抛出 !
     **/
    void stop(String id);

    /**
     * @Description : 获取全部告警信息
     * @Return : java.util.List<cn.gsq.n9e.core.pojo.AlarmInfoDTO>
     * @Author : gsq
     * @Date : 2024/8/14 17:05
     * @Note : An art cell !
     **/
    List<AlarmInfoDTO> getAlarmInfos();

    /**
     * @Description : 获取当前是告警的信息
     * @Return : java.util.List<cn.gsq.n9e.core.pojo.AlarmInfoDTO>
     * @Author : gsq
     * @Date : 2024/8/14 17:05
     * @Note : An art cell !
     **/
    List<AlarmInfoDTO> getCurrentAlarmInfos();

    /**
     * @Description : 根据id获取告警信息
     * @param java.lang.String id : 告警信息id
     * @Return : cn.gsq.n9e.core.pojo.AlarmInfoDTO
     * @Author : gsq
     * @Date : 2024/8/14 17:07
     * @Note : An art cell !
     **/
    AlarmInfoDTO getAlarmInfo(String id);

    /**
     * @Description : 处理告警信息
     * @param java.lang.String id : 告警信息id
     * @param cn.gsq.n9e.core.AlarmStatus status : 告警状态
     * @param java.lang.String solveInfo : 处理说明
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/14 17:10
     * @Note : ⚠️ 处理失败要抛出异常 !
     **/
    void solve(String id, AlarmStatus status, String solveInfo);

    /**
     * @Description : 根据id删除告警信息
     * @param java.lang.String id : 告警信息id
     * @Return : void
     * @Author : gsq
     * @Date : 2024/8/14 17:11
     * @Note : ⚠️ 删除失败要抛出异常 !
     **/
    void removeAlarmInfo(String id);

    /**
     * @Description : 根据条件查询告警信息
     * @param cn.gsq.n9e.core.AlarmQueryConditions conditions : 查询条件
     * @Return : java.util.List<cn.gsq.n9e.core.pojo.AlarmInfoDTO>
     * @Author : gsq
     * @Date : 2024/8/14 17:19
     * @Note : An art cell !
     **/
    List<AlarmInfoDTO> getAlarmInfosByConditions(AlarmQueryConditions conditions);

}
