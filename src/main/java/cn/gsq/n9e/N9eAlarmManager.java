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
 * @since : 2024-08-12 10:01
 **/
public interface N9eAlarmManager {


     //新增/修改指标分组
    void updateMetricGroup(MetricGroup group);

    //删除指标分组
    void removeMetricGroup(String id);

    // 获取全部指标分组
    List<MetricGroup> getMetricGroups();

    // 新增或修改指标元数据
    void updateMetric(Metric metric);

    //根据id删除指标
    void removeMetric(String id);

    //获取全部指标
    List<MetricDTO> getMetrics();

    //  根据分组id获取下面所有的指标实例
    List<MetricDTO> getMetrics(String id);

    //获取内置指标的元数据信息
    Map<String,Object> getBuiltinMetadata();

   //查询内置指标的信息
    Map<String,Object> getBuiltinMetric(String collector,String typ,String query,String pageNum,String pageSize);

   // 获取内置指标列表
    List<String> getBuiltinMetricList();

    //根据指标id获取指标
    MetricDTO getMetric(String id);

   //运行指标监控
    void run(String id);

    //停止指标监控
    void stop(String id);

    //获取全部告警信息
    List<AlarmInfoDTO> getAlarmInfos();

   //获取当前是告警的信息
    List<AlarmInfoDTO> getCurrentAlarmInfos();

    // 根据id获取告警信息
    AlarmInfoDTO getAlarmInfo(String id);

    // 处理告警信息
    void solve(String id, AlarmStatus status, String solveInfo);

    // 根据id删除告警信息
    void removeAlarmInfo(String id);

    // 根据条件查询告警信息
    List<AlarmInfoDTO> getAlarmInfosByConditions(AlarmQueryConditions conditions);

}
