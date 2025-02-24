package cn.gsq.n9e.config;

import cn.gsq.n9e.N9eAlarmManager;
import cn.gsq.n9e.N9eAlarmManagerImpl;
import cn.gsq.n9e.core.AlarmLevel;
import cn.gsq.n9e.core.Metric;
import cn.gsq.n9e.core.MetricGroup;
import cn.gsq.n9e.core.MetricGroupType;
import cn.hutool.core.date.Week;
import cn.hutool.core.lang.Pair;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.config.N9eAlarmAutoConfigure
 *
 * @author : gsq
 * @since  : 2024-08-13 16:47
 **/
@Configuration
@EnableConfigurationProperties(N9eAlarmProperties.class)
public class N9eAlarmAutoConfigure {

   //使用者API入口实例
    @Bean(name = "n9eAlarmManager")
    public N9eAlarmManager getN9eAlarmManager() {
        return new N9eAlarmManagerImpl();
    }

    //加载默认分组和指标
    @Bean(name = "n9eAlarmEnvLoad")
    public Pair<String, String> load(N9eAlarmManager manager){
        // 主机
        manager.updateMetricGroup(new MetricGroup().setName("磁盘").setType(MetricGroupType.HOST));
        // 服务
        manager.updateMetricGroup(new MetricGroup().setName("HDFS").setType(MetricGroupType.SERVE));
        //告警指标
        Set<Week> weekSet=new HashSet<>();
        weekSet.add(Week.SUNDAY);
        weekSet.add(Week.MONDAY);
        weekSet.add(Week.TUESDAY);
        weekSet.add(Week.WEDNESDAY);
        weekSet.add(Week.THURSDAY);
        weekSet.add(Week.FRIDAY);
        weekSet.add(Week.SATURDAY);
        for (MetricGroup metricGroup : manager.getMetricGroups()) {
            if(metricGroup.getName().equals("磁盘")&&metricGroup.getType().equals(MetricGroupType.HOST)){
                Metric memMetric = new Metric("内存使用率超过75%", metricGroup.getId());
                memMetric.setSuggestion("请清理内存");
                memMetric.setStart("00:00");
                memMetric.setEnd("23:59");
                memMetric.setLevel(AlarmLevel.WARN);
                memMetric.setDays(weekSet);
                memMetric.setGroupName(metricGroup.getName());
                memMetric.setExpression("mem_used_percent>75");
                manager.updateMetric(memMetric);

                Metric diskMetric = new Metric("硬盘使用率超过75%", metricGroup.getId());
                diskMetric.setSuggestion("请清理硬盘");
                diskMetric.setStart("00:00");
                diskMetric.setEnd("23:59");
                diskMetric.setLevel(AlarmLevel.WARN);
                diskMetric.setDays(weekSet);
                diskMetric.setGroupName(metricGroup.getName());
                diskMetric.setExpression("disk_inodes_used / disk_inodes_total * 100>0.75");
                manager.updateMetric(diskMetric);
            }
            if(metricGroup.getName().equals("HDFS")&&metricGroup.getType().equals(MetricGroupType.SERVE)){
                Metric capMetric = new Metric("存储使用率超过75%", metricGroup.getId());
                capMetric.setSuggestion("请清理存储");
                capMetric.setStart("00:00");
                capMetric.setEnd("23:59");
                capMetric.setLevel(AlarmLevel.WARN);
                capMetric.setDays(weekSet);
                capMetric.setGroupName(metricGroup.getName());
                capMetric.setExpression("topk(1,Hadoop_NameNode_CapacityUsed)>0.75");
                manager.updateMetric(capMetric);
            }
        }

        return null;
    }

}
