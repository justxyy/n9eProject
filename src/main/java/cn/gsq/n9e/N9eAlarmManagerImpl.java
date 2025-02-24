package cn.gsq.n9e;

import cn.gsq.n9e.core.*;
import cn.gsq.n9e.core.pojo.AlarmInfoDTO;
import cn.gsq.n9e.core.pojo.MetricDTO;
import cn.gsq.n9e.utils.HttpUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.N9eAlarmManagerImpl
 *
 * @author : xyy
 * @date : 2024-08-19 09:08
 * @note : It's not technology, it's art !
 **/
public class N9eAlarmManagerImpl implements N9eAlarmManager{

    @Value("${galaxy.n9e.url}")
    private String n9eUrl;

    @Override
    public void updateMetricGroup(MetricGroup group) {
        if(ObjectUtil.isNotEmpty(group.getId())){
            modifyMetricGroup(group);
        }else {
            addMetricGroup(group);
        }
    }

    private void addMetricGroup(MetricGroup group) {
        String url=n9eUrl+"/v1/n9e/busi-groups";
        JSONObject team = new JSONObject();
        team.set("user_group_id",1);
        team.set("perm_flag","rw");
        ArrayList<JSONObject> list = new ArrayList<>();
        list.add(team);

        JSONObject bgroup = new JSONObject();
        bgroup.set("name",group.getType().getName()+"-"+group.getName());
        bgroup.set("label_enable",0);
        bgroup.set("members",list);

        HttpUtil.doPost(url,JSONUtil.toJsonStr(bgroup));
    }

    private void modifyMetricGroup(MetricGroup group) {
        String url=n9eUrl+"/v1/n9e/busi-group/"+group.getId();
        JSONObject bgroup = new JSONObject();
        bgroup.set("name",group.getType().getName()+"-"+group.getName());
        bgroup.set("label_enable",0);
        HttpUtil.doPut(url,JSONUtil.toJsonStr(bgroup));
    }

    @Override
    public void removeMetricGroup(String id) {
        //如果指标组存在指标 则不允许删除
        for (MetricDTO metric : getMetrics()) {
            if(metric.getGroupId().equals(id))
                throw new RuntimeException("该指标组存在指标 不允许删除！");
        }

        String url=n9eUrl+"/v1/n9e/busi-group/"+id;
        JSONObject object = new JSONObject();
        HttpUtil.doDelete(url,JSONUtil.toJsonStr(object));
    }

    @Override
    public List<MetricGroup> getMetricGroups() {
        String url=n9eUrl+"/v1/n9e/busi-groups";
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        List<MetricGroup> mg=new ArrayList<>();
        for (Object o : JSONUtil.parseArray(dat)) {
            JSONObject object = JSONUtil.parseObj(o);
            if(ObjectUtil.isNotNull(object)&&object.getInt("id")!=1){
                Integer id = object.getInt("id");
                String name = (String) object.get("name");
                String[] split = name.split("-");
                if(split.length>1){
                    MetricGroupType groupType = MetricGroupType.parse(split[0]);
                    MetricGroup dto = new MetricGroup().setId(String.valueOf(id)).setName(split[1]).setType(groupType);
                    mg.add(dto);
                }
            }
        }
        return mg;
    }

    @Override
    public void updateMetric(Metric metric) {
        if(ObjectUtil.isNotEmpty(metric.getId())){
            modifyMetric(metric);
        }else {
            addMetric(metric);
        }

    }

    private void addMetric(Metric metric) {
        String url=n9eUrl+"/v1/n9e/busi-group/"+metric.getGroupId()+"/alert-rules";
        List<Object> list=new ArrayList<>();

        Map<String,Object> json = new HashMap<>();
        json.put("disabled",1);
        json.put("name",metric.getName());
        json.put("group_id",Integer.parseInt(metric.getGroupId()));
        json.put("prod","metric");

        Map<String,Object> ruleConfig = new HashMap<>();
        List<Object> queryList=new ArrayList<>();
        Map<String,Object> rule = new HashMap<>();
        rule.put("prom_ql",metric.getExpression());
        rule.put("severity",metric.getLevel().getId());
        queryList.add(rule);
        ruleConfig.put("queries",queryList);
        json.put("rule_config",ruleConfig);

        json.put("enable_status",false);
        json.put("notify_recovered",1);
        json.put("recover_duration",0);
        json.put("notify_repeat_step",60);
        json.put("notify_max_number",0);
        json.put("callbacks",null);
        json.put("annotations",null);
        json.put("cate","prometheus");
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        json.put("datasource_ids",integers);
        json.put("prom_eval_interval",20);
        json.put("prom_for_duration",60);
        json.put("enable_in_bg",0);
        List<String> weekList=new ArrayList<>();
        for (Week day : metric.getDays()) {
            weekList.add(String.valueOf(day.getValue()-1));
        }
        ArrayList<List> lists = new ArrayList<>();
        lists.add(weekList);
        json.put("enable_days_of_weeks",lists);
        String[] start = {metric.getStart()};
        json.put("enable_stimes",start);
        String[] end = {metric.getEnd()};
        json.put("enable_etimes",end);
        json.put("note",metric.getSuggestion());

        list.add(json);

        JSONConfig config = new JSONConfig();
        config.setIgnoreNullValue(false); // 设置不忽略 null 值
        HttpUtil.doPost(url,JSONUtil.toJsonStr(list,config));
    }

    private void modifyMetric(Metric metric) {
        String url=n9eUrl+"/v1/n9e/busi-group/"+metric.getGroupId()+"/alert-rule/"+metric.getId();
        Map<String,Object> json = new HashMap<>();
        json.put("disabled",1);
        json.put("name",metric.getName());
        json.put("group_id",Integer.parseInt(metric.getGroupId()));
        json.put("append_tags",null);
        json.put("note",metric.getSuggestion());
        json.put("prod","metric");

        Map<String,Object> ruleConfig = new HashMap<>();
        List<Object> queryList=new ArrayList<>();
        Map<String,Object> rule = new HashMap<>();
        rule.put("prom_ql",metric.getExpression());
        rule.put("severity",metric.getLevel().getId());
        queryList.add(rule);
        ruleConfig.put("queries",queryList);
        json.put("rule_config",ruleConfig);

        json.put("enable_status",false);
        json.put("notify_channels",null);
        json.put("notify_groups",null);
        json.put("notify_recovered",1);
        json.put("recover_duration",0);
        json.put("notify_repeat_step",1);
        json.put("notify_max_number",0);
        json.put("callbacks",null);
        json.put("annotations",null);
        json.put("cate","prometheus");
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        json.put("datasource_ids",integers);
        json.put("prom_eval_interval",20);
        json.put("prom_for_duration",60);
        json.put("enable_in_bg",0);
        List<String> list=new ArrayList<>();
        for (Week day : metric.getDays()) {
            list.add(String.valueOf(day.getValue()-1));
        }
        ArrayList<List> lists = new ArrayList<>();
        lists.add(list);
        json.put("enable_days_of_weeks",lists);
        String[] start = {metric.getStart()};
        json.put("enable_stimes",start);
        String[] end = {metric.getEnd()};
        json.put("enable_etimes",end);

        JSONConfig config = new JSONConfig();
        config.setIgnoreNullValue(false); // 设置不忽略 null 值
        HttpUtil.doPut(url,JSONUtil.toJsonStr(json,config));

    }

    @Override
    public void removeMetric(String id) {
        String url=n9eUrl+"/v1/n9e/alert-rules";
        JSONObject object = new JSONObject();
        object.append("ids",Integer.parseInt(id));
        HttpUtil.doDelete(url,JSONUtil.toJsonStr(object));
    }

    @Override
    public List<MetricDTO> getMetrics() {
        String url=n9eUrl+"/v1/n9e/alert-rules";
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        List<MetricDTO> list=new ArrayList<>();
        for (Object o : JSONUtil.parseArray(dat)) {
            list.add(getMetricDtoByObject(o));
        }
        List<MetricDTO> collect = list.stream().filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        return collect;
    }

    /**
     * @Description : 类型转换
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/8/20
     * @note : An art cell !
     **/
    private MetricDTO getMetricDtoByObject(Object o) {
        JSONObject obj = JSONUtil.parseObj(o);
        if(obj.getStr("prod").equals("metric")){
            MetricDTO dto = new MetricDTO(obj.getStr("name"), obj.getStr("group_id"));
            dto.setId(obj.getStr("id"));
            dto.setRunning(!obj.getBool("disabled"));
            dto.setSuggestion(obj.getStr("note"));
            dto.setStart(obj.getStr("enable_stime"));
            dto.setEnd(obj.getStr("enable_etime"));
            Object days_of_week = obj.get("enable_days_of_week");
            Set<Week> weekSet=new HashSet<>();
            for (Object o1 : JSONUtil.parseArray(days_of_week)) {
                int dayOfWeek = Integer.parseInt((String) o1);
                Week week = Week.of(dayOfWeek + 1);
                weekSet.add(week);
            }
            dto.setDays(weekSet);
            JSONObject ruleConfig = JSONUtil.parseObj(obj.get("rule_config"));
            if(ObjectUtil.isEmpty(JSONUtil.parseArray(ruleConfig.getStr("queries")))){
                Integer severity = (Integer) ruleConfig.get("severity");
                switch (severity){
                    case 1:
                        dto.setLevel(AlarmLevel.SERIOUS);
                        break;
                    case 2:
                        dto.setLevel(AlarmLevel.WARN);
                        break;
                    case 3:
                        dto.setLevel(AlarmLevel.NORMAL);
                        break;
                }
                dto.setExpression(ruleConfig.getStr("prom_ql"));
            }else {
                for (Object queries : JSONUtil.parseArray(ruleConfig.getStr("queries"))) {
                    JSONObject object = JSONUtil.parseObj(queries);
                    Integer severity = (Integer) object.get("severity");
                    switch (severity){
                        case 1:
                            dto.setLevel(AlarmLevel.SERIOUS);
                            break;
                        case 2:
                            dto.setLevel(AlarmLevel.WARN);
                            break;
                        case 3:
                            dto.setLevel(AlarmLevel.NORMAL);
                            break;
                    }
                    dto.setExpression(object.getStr("prom_ql"));
                }
            }
            List<MetricGroup> metricGroups = getMetricGroups();
            List<MetricGroup> collect = metricGroups.stream().filter(group -> group.getId().equals(dto.getGroupId())).collect(Collectors.toList());
            if(ObjectUtil.isNotEmpty(collect)){
                MetricGroup metricGroup = collect.get(0);
                dto.setGroupName(metricGroup.getName());
                if(
                        (metricGroup.getName().equals("磁盘")&&metricGroup.getType().equals(MetricGroupType.HOST)&&obj.getStr("name").equals("内存使用率超过75%"))
                                ||
                                (metricGroup.getName().equals("磁盘")&&metricGroup.getType().equals(MetricGroupType.HOST)&&obj.getStr("name").equals("硬盘使用率超过75%"))
                                ||
                                (metricGroup.getName().equals("HDFS")&&metricGroup.getType().equals(MetricGroupType.SERVE)&&obj.getStr("name").equals("存储使用率超过75%"))
                ){
                    dto.setCanOps(false);
                }
            }

            return dto;
        }
        return null;
    }

    @Override
    public List<MetricDTO> getMetrics(String gid) {
        String url=n9eUrl+"/v1/n9e/alert-rules";
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        List<MetricDTO> list=new ArrayList<>();
        for (Object o : JSONUtil.parseArray(dat)) {
            list.add(getMetricDtoByObject(o));
        }
        return list.stream()
                .filter(ObjectUtil::isNotNull)
                .filter(metricDTO -> metricDTO.getGroupId().equals(gid))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String,Object> getBuiltinMetadata() {
        Map<String,Object> res=new HashMap<>();
        String typeUrl=n9eUrl+"/v1/n9e/builtin-metrics/types";
        String getType = HttpUtil.doGet(typeUrl);
        Object type = JSONUtil.parseObj(getType).get("dat");
        res.put("typ",type);

        String collectorUrl=n9eUrl+"/v1/n9e/builtin-metrics/collectors";
        String getCollector = HttpUtil.doGet(collectorUrl);
        Object collector = JSONUtil.parseObj(getCollector).get("dat");
        res.put("collector",collector);

        String defaultUrl=n9eUrl+"/v1/n9e/builtin-metrics/types/default";
        String getDefault = HttpUtil.doGet(defaultUrl);
        Object defaultType = JSONUtil.parseObj(getDefault).get("dat");
        res.put("default",defaultType);

        return res;
    }

    @Override
    public Map<String,Object> getBuiltinMetric(String collector, String typ, String query, String pageNum, String pageSize) {
        String url=n9eUrl+"/v1/n9e/builtin-metrics?collector="+collector+"&p="+pageNum+"&limit="+pageSize+"&unit=";
        if(ObjectUtil.isNotEmpty(typ))
            url=url+"&typ="+typ;
        if(ObjectUtil.isNotEmpty(query))
            url=url+"&query="+query;
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        Object list = JSONUtil.parseObj(dat).get("list");
        Object total = JSONUtil.parseObj(dat).get("total");
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return map;
    }

    @Override
    public List<String> getBuiltinMetricList() {
        List<String> list=new ArrayList<>();
        Instant now = Instant.now();
        Instant old = now.minus(Duration.ofHours(12));
        String nowTime = DateTimeFormatter.ISO_INSTANT.format(now);
        String oldTime = DateTimeFormatter.ISO_INSTANT.format(old);
        String url=n9eUrl+"/api/n9e/proxy/1/api/v1/label/__name__/values?start="+oldTime+"&end="+nowTime;
        String s = HttpUtil.doGet(url);
        Object data = JSONUtil.parseObj(s).get("data");
        for (Object o : JSONUtil.parseArray(data)) {
            list.add((String) o);
        }
        return list;
    }

    //根据指标id获取指标 修改时用
    @Override
    public MetricDTO getMetric(String id) {
        String url=n9eUrl+"/v1/n9e/alert-rule/"+id;
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        return getMetricDtoByObject(dat);
    }

    @Override
    public void run(String id) {
        //获取json
        String url=n9eUrl+"/v1/n9e/alert-rule/"+id;
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        JSONObject obj = JSONUtil.parseObj(dat);
        obj.set("disabled",0);
        HttpUtil.doPut(url,JSONUtil.toJsonStr(obj));
    }

    @Override
    public void stop(String id) {
        //获取json
        String url=n9eUrl+"/v1/n9e/alert-rule/"+id;
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        JSONObject obj = JSONUtil.parseObj(dat);
        obj.set("disabled",1);
        HttpUtil.doPut(url,JSONUtil.toJsonStr(obj));
    }

    @Override
    public List<AlarmInfoDTO> getAlarmInfos() {
        List<AlarmInfoDTO> curAlarmInfos = getCurAlarmInfos();
        List<AlarmInfoDTO> hisAlarmInfos = getHisAlarmInfos();
        List<AlarmInfoDTO> alist=new ArrayList<>();
        alist.addAll(curAlarmInfos);
        alist.addAll(hisAlarmInfos);
        alist.sort(Comparator.comparing(AlarmInfoDTO::getId).reversed());
        return alist;
    }

    @Override
    public List<AlarmInfoDTO> getCurrentAlarmInfos() {
        return getCurAlarmInfos();
    }

    public List<AlarmInfoDTO> getCurAlarmInfos() {
        String url=n9eUrl+"/v1/n9e/alert-cur-events/list?p=1&limit=999";
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        Object list = JSONUtil.parseObj(dat).get("list");

        List<AlarmInfoDTO> alist=new ArrayList<>();
        for (Object o : JSONUtil.parseArray(list)) {
            JSONObject obj = JSONUtil.parseObj(o);
            AlarmInfoDTO dto = new AlarmInfoDTO();
            dto.setId(obj.getStr("id"));
            dto.setMetric(obj.getStr("rule_name"));
            dto.setLevel(AlarmLevel.parse(obj.getInt("severity")));
            dto.setStartTime(obj.getStr("first_trigger_time"));
            dto.setEndTime(obj.getStr("trigger_time"));
            dto.setStatus(AlarmStatus.UNSOLVED);
            dto.setSolveInfo(obj.getStr("rule_note"));
            dto.setGroupId(obj.getStr("group_id"));
            dto.setGroupName(obj.getStr("group_name"));
            dto.setHostName(obj.getStr("target_ident"));
            alist.add(dto);
        }

        return alist;
    }

    public List<AlarmInfoDTO> getHisAlarmInfos() {
        long etime = System.currentTimeMillis()/1000;
        long stime=etime-5*24*60*60;
        String url=n9eUrl+"/v1/n9e/alert-his-events/list?limit=9999&is_recovered=1&stime="+stime+"&etime="+etime;
        String s = HttpUtil.doGet(url);
        Object dat = JSONUtil.parseObj(s).get("dat");
        Object list = JSONUtil.parseObj(dat).get("list");

        List<AlarmInfoDTO> alist=new ArrayList<>();
        for (Object o : JSONUtil.parseArray(list)) {
            JSONObject obj = JSONUtil.parseObj(o);
            AlarmInfoDTO dto = new AlarmInfoDTO();
            dto.setId(obj.getStr("id"));
            dto.setMetric(obj.getStr("rule_name"));
            dto.setLevel(AlarmLevel.parse(obj.getInt("severity")));
            dto.setStartTime(obj.getStr("first_trigger_time"));
            dto.setEndTime(obj.getStr("last_eval_time"));
            dto.setStatus(AlarmStatus.parse(obj.getInt("is_recovered")));
            dto.setSolveTime(obj.getStr("recover_time"));
            dto.setSolveInfo(obj.getStr("rule_note"));
            dto.setGroupId(obj.getStr("group_id"));
            dto.setGroupName(obj.getStr("group_name"));
            dto.setHostName(obj.getStr("target_ident"));
            alist.add(dto);
        }
        return alist;
    }

    @Override
    public AlarmInfoDTO getAlarmInfo(String id) {
        return null;
    }

    @Override
    public void solve(String id, AlarmStatus status, String solveInfo) {

    }

    @Override
    public void removeAlarmInfo(String id) {

    }

    @Override
    public List<AlarmInfoDTO> getAlarmInfosByConditions(AlarmQueryConditions conditions) {
        return null;
    }
}
