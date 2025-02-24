package cn.gsq.n9e.utils;

import okhttp3.*;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Project : galaxy
 * Class : cn.gsq.n9e.utils.HttpUtil
 *
 * @author : xyy
 * @date : 2024-08-19 13:19
 * @note : It's not technology, it's art !
 **/
public class HttpUtil {
    private static String AUTHORIZATION="Basic dXNlcjAwMTpjY2MyNmRhN2I5YWJhNTMzY2JiMjYzYTM2YzA3ZGNjNQ==";//配置文件配的user001:ccc26da7b9aba533cbb263a36c07dcc5的base64加密
    private static OkHttpClient client = new OkHttpClient();

    /**
     * @Description : get请求
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/8/19
     * @note : An art cell !
     **/
    public static String doGet(String url) {
        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", AUTHORIZATION)
                    .build();


            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RuntimeException("doGet请求失败，状态码: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("httpUtil 请求异常：" + e.getMessage());
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
    }


    /**
     * @Description : post请求
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/8/19
     * @note : An art cell !
     **/
    public static void doPost(String url,String json) {
        Response response = null;
        try {
            // 创建 RequestBody
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Authorization", AUTHORIZATION)
                    .build();


            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("doPost请求失败，状态码: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("httpUtil 请求异常：" + e.getMessage());
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
    }

    /**
     * @Description : put请求
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/8/19
     * @note : An art cell !
     **/
    public static void doPut(String url,String json) {
        Response response = null;
        try {
            // 创建 RequestBody
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader("Authorization", AUTHORIZATION)
                    .build();


            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("doPut请求失败，状态码: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("httpUtil 请求异常：" + e.getMessage());
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
    }

    /**
     * @Description : delete请求
     * @Param :
     * @Return :
     * @Author : xyy
     * @Date : 2024/8/19
     * @note : An art cell !
     **/
    public static void doDelete(String url,String json) {
        Response response = null;
        try {
            // 创建 RequestBody
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .delete(body)
                    .addHeader("Authorization", AUTHORIZATION)
                    .build();


            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("doDelete请求失败，状态码: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("httpUtil 请求异常：" + e.getMessage());
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
    }


    public static void main(String[] args) {
//        String url = "http://10.0.16.229:17000/v1/n9e/users?p=1&limit=10&query=";
//        System.out.println(doGet(url));

//        String url="http://10.0.16.229:17000/v1/n9e/users?p=1&limit=10&query=";
//        String json="{\n    \"id\": 1,\n    \"username\": \"xyy\",\n    \"nickname\": \"超管\",\n    \"phone\": \"\",\n    \"email\": \"\",\n    \"portrait\": \"\",\n    \"roles\": [\n        \"Admin\"\n    ],\n    \"contacts\": {},\n    \"maintainer\": 0,\n    \"create_at\": 1697527031,\n    \"create_by\": \"system\",\n    \"update_at\": 1697527031,\n    \"update_by\": \"system\",\n    \"admin\": true\n}";
//        doPost(url,json);

//        String url="http://10.0.16.229:17000/v1/n9e/user/";
//        String json="{\n    \"id\": 3,\n    \"username\": \"xyy\",\n    \"nickname\": \"超管11\",\n    \"phone\": \"\",\n    \"email\": \"\",\n    \"portrait\": \"\",\n    \"roles\": [\n        \"Standard\"\n    ],\n    \"contacts\": {},\n    \"maintainer\": 0,\n    \"create_at\": 1697527031,\n    \"create_by\": \"system\",\n    \"update_at\": 1697527031,\n    \"update_by\": \"system\",\n    \"admin\": false\n}";
//        doPut(url,json);

//        String url="http://10.0.16.229:17000/v1/n9e/user/";
//        doDelete(url);

        //创建告警规则
//        String url="http://10.0.16.229:17000/v1/n9e/alert-rules";
//        String json="[\n    {\n        \"disabled\": 1,\n        \"name\": \"小于200G的盘，空间不足了\",\n        \"append_tags\": [],\n        \"note\": \"\",\n        \"prod\": \"metric\",\n        \"rule_config\": {\n            \"inhibit\": true,\n            \"queries\": [\n                {\n                    \"prom_ql\": \"disk_used_percent > 90 and disk_total/1024/1024/1024 < 200\",\n                    \"severity\": 3\n                },\n                {\n                    \"prom_ql\": \"disk_used_percent > 95 and disk_total/1024/1024/1024 < 200\",\n                    \"severity\": 2\n                },\n                {\n                    \"prom_ql\": \"disk_used_percent > 99 and disk_total/1024/1024/1024 < 200\",\n                    \"severity\": 1\n                }\n            ]\n        },\n        \"enable_status\": false,\n        \"notify_channels\": [],\n        \"notify_groups\": [],\n        \"notify_recovered\": 1,\n        \"recover_duration\": 0,\n        \"notify_repeat_step\": 60,\n        \"notify_max_number\": 0,\n        \"callbacks\": [],\n        \"annotations\": {},\n        \"extra_config\": {\n            \"escalation\": {},\n            \"notify_aggregation\": {}\n        },\n        \"cate\": \"prometheus\",\n        \"datasource_ids\": [\n            52\n        ],\n        \"prom_eval_interval\": 30,\n        \"prom_for_duration\": 60,\n        \"enable_in_bg\": 0,\n        \"enable_days_of_weeks\": [\n            [\n                \"0\",\n                \"1\",\n                \"2\",\n                \"3\",\n                \"4\",\n                \"5\",\n                \"6\"\n            ]\n        ],\n        \"enable_stimes\": [\n            \"00:00\"\n        ],\n        \"enable_etimes\": [\n            \"23:59\"\n        ]\n    }\n]";
//        doPost(url,json);



//        //根据产品查询告警规则列表
//        String url="http://10.0.16.229:17000/v1/n9e/alert-rules";
//        System.out.println(doGet(url));

//        根据id查询告警规则
//        String url="http://10.0.16.229:17000/v1/n9e/alert-rule/3";
//        System.out.println(doGet(url));

        //根据id更新告警规则
//        String url="http://10.0.16.229:17000/v1/n9e/alert-rule/3";
//        String json="{\n" +
//                "        \"id\": 3,\n" +
//                "        \"group_id\": 0,\n" +
//                "        \"cate\": \"prometheus\",\n" +
//                "        \"datasource_ids\": [\n" +
//                "            79\n" +
//                "        ],\n" +
//                "        \"cluster\": \"\",\n" +
//                "        \"name\": \"test111111\",\n" +
//                "        \"note\": \"请清理磁盘9999999999999\",\n" +
//                "        \"prod\": \"anomaly\",\n" +
//                "        \"algorithm\": \"\",\n" +
//                "        \"algo_params\": null,\n" +
//                "        \"delay\": 0,\n" +
//                "        \"severity\": 0,\n" +
//                "        \"severities\": null,\n" +
//                "        \"disabled\": 0,\n" +
//                "        \"prom_for_duration\": 60,\n" +
//                "        \"prom_ql\": \"\",\n" +
//                "        \"rule_config\": {\n" +
//                "            \"algo_params\": {\n" +
//                "                \"lower_bound\": 1,\n" +
//                "                \"lower_times_num\": 1,\n" +
//                "                \"seasonal_duration\": 604800,\n" +
//                "                \"upper_bound\": 1,\n" +
//                "                \"upper_times_num\": 1\n" +
//                "            },\n" +
//                "            \"prom_ql\": \"aa\",\n" +
//                "            \"severity\": 1\n" +
//                "        },\n" +
//                "        \"event_relabel_config\": null,\n" +
//                "        \"prom_eval_interval\": 20,\n" +
//                "        \"enable_stime\": \"00:00\",\n" +
//                "        \"enable_stimes\": [\n" +
//                "            \"00:00\"\n" +
//                "        ],\n" +
//                "        \"enable_etime\": \"00:00\",\n" +
//                "        \"enable_etimes\": [\n" +
//                "            \"00:00\"\n" +
//                "        ],\n" +
//                "        \"enable_days_of_week\": [\n" +
//                "            \"0\",\n" +
//                "            \"1\",\n" +
//                "            \"2\",\n" +
//                "            \"3\",\n" +
//                "            \"4\",\n" +
//                "            \"5\",\n" +
//                "            \"6\"\n" +
//                "        ],\n" +
//                "        \"enable_days_of_weeks\": [\n" +
//                "            [\n" +
//                "                \"0\",\n" +
//                "                \"1\",\n" +
//                "                \"2\",\n" +
//                "                \"3\",\n" +
//                "                \"4\",\n" +
//                "                \"5\",\n" +
//                "                \"6\"\n" +
//                "            ]\n" +
//                "        ],\n" +
//                "        \"enable_in_bg\": 0,\n" +
//                "        \"notify_recovered\": 1,\n" +
//                "        \"notify_channels\": [\n" +
//                "            \"wecom\",\n" +
//                "            \"dingtalk\"\n" +
//                "        ],\n" +
//                "        \"notify_groups_obj\": [\n" +
//                "\n" +
//                "        ],\n" +
//                "        \"notify_groups\": [\n" +
//                "\n" +
//                "        ],\n" +
//                "        \"notify_repeat_step\": 1,\n" +
//                "        \"notify_max_number\": 0,\n" +
//                "        \"recover_duration\": 0,\n" +
//                "        \"callbacks\": [\n" +
//                "\n" +
//                "        ],\n" +
//                "        \"runbook_url\": \"\",\n" +
//                "        \"append_tags\": [\n" +
//                "            \"alarmGroup=host-cpu\"\n" +
//                "        ],\n" +
//                "        \"annotations\": {\n" +
//                "\n" +
//                "        },\n" +
//                "        \"extra_config\": {\n" +
//                "            \"custom_notify_tpl\": {\n" +
//                "                \"dingtalk\": \"tx-sms\",\n" +
//                "                \"wecom\": \"wecom\"\n" +
//                "            },\n" +
//                "            \"escalation\": {\n" +
//                "                \"enable\": true,\n" +
//                "                \"for_duration\": 60,\n" +
//                "                \"new_channels\": [\n" +
//                "                    \"wecom\"\n" +
//                "                ],\n" +
//                "                \"new_severity\": 2,\n" +
//                "                \"notify_groups\": [\n" +
//                "\n" +
//                "                ],\n" +
//                "                \"notify_max_number\": 0,\n" +
//                "                \"notify_repeat_step\": 60,\n" +
//                "                \"redefine_channels\": true,\n" +
//                "                \"redefine_severity\": true\n" +
//                "            },\n" +
//                "            \"notify_aggregation\": {\n" +
//                "                \"enable\": false,\n" +
//                "                \"wait\": 1\n" +
//                "            }\n" +
//                "        },\n" +
//                "        \"create_at\": 1724049596,\n" +
//                "        \"create_by\": \"\",\n" +
//                "        \"update_at\": 1724049596,\n" +
//                "        \"update_by\": \"\",\n" +
//                "        \"uuid\": 0\n" +
//                "    }";
//        doPut(url,json);

        //删除告警规则
//        String url="http://10.0.16.229:17000/v1/n9e/alert-rules";
//        String json="{\n    \"ids\": [\n        2\n    ]\n}";
//        doDelete(url,json);


//        //获取业务组
//        String url="http://10.0.16.229:17000/v1/n9e/busi-groups";
//        System.out.println(doGet(url));

        //添加业务组
//        String url="http://172.22.1.221:17000/v1/n9e/busi-groups";
//        String json="{\n" +
//                "    \"name\": \"集群主机-磁盘\",\n" +
//                "    \"label_enable\": 0,\n" +
//                "    \"members\": [\n" +
//                "        {\n" +
//                "            \"user_group_id\": 1,\n" +
//                "            \"perm_flag\": \"rw\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        doPost(url,json);

        //修改业务组
//        String url="http://172.22.1.221:17000/v1/n9e/busi-group/3";
//        String json="{\n" +
//                "    \"name\": \"主机222-磁盘11\",\n" +
//                "    \"label_enable\": 0\n" +
//                "}";
//        doPut(url,json);

//        //删除业务组
//        String url="http://172.22.1.221:17000/v1/n9e/busi-group/2";
//        doDelete(url,"{}");


//        //获取历史告警信息
//        long etime = System.currentTimeMillis()/1000;
//        System.out.println(etime);
//        long stime=etime-5*24*60*60;
//        System.out.println(stime);
//        String url="http://172.22.1.221:17000/v1/n9e/alert-his-events/list?limit=999&is_recovered=1&stime="+stime+"&etime="+etime;
//        System.out.println(doGet(url));

        //获取当前告警信息
//        String url="http://172.22.1.221:17000/v1/n9e/alert-cur-events/list?p=1&limit=999";
//        System.out.println(doGet(url));


//        获取指标名 用于提示
//        Instant now = Instant.now();
//        Instant old = now.minus(Duration.ofHours(12));
//        String nowTime = DateTimeFormatter.ISO_INSTANT.format(now);
//        String oldTime = DateTimeFormatter.ISO_INSTANT.format(old);
//        System.out.println(nowTime);
//        System.out.println(oldTime);
//        String url="http://172.22.1.217:17000/api/n9e/proxy/1/api/v1/label/__name__/values?start="+oldTime+"&end="+nowTime;
//        String s = HttpUtil.doGet(url);
//        System.out.println(s);


//        //添加告警规则
//        String url="http://172.22.1.221:17000/v1/n9e/busi-group/6/alert-rules";
//        String json="[\n" +
//                "  {\n" +
//                "    \"disabled\": 0,\n" +
//                "    \"name\": \"testrule1111\",\n" +
//                "    \"group_id\": 6,\n" +
//                "    \"prod\": \"metric\",\n" +
//                "    \"rule_config\": {\n" +
//                "      \"queries\": [\n" +
//                "        {\n" +
//                "          \"prom_ql\": \"cpu_usage_active>1\",\n" +
//                "          \"severity\": 2\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    \"enable_status\": true,\n" +
//                "    \"notify_recovered\": 1,\n" +
//                "    \"recover_duration\": 0,\n" +
//                "    \"notify_repeat_step\": 60,\n" +
//                "    \"notify_max_number\": 0,\n" +
//                "    \"callbacks\": [],\n" +
//                "    \"annotations\": {},\n" +
//                "    \"cate\": \"prometheus\",\n" +
//                "    \"datasource_ids\": [\n" +
//                "      0\n" +
//                "    ],\n" +
//                "    \"prom_eval_interval\": 30,\n" +
//                "    \"prom_for_duration\": 60,\n" +
//                "    \"enable_in_bg\": 0,\n" +
//                "    \"enable_days_of_weeks\": [\n" +
//                "      [\n" +
//                "        \"0\",\n" +
//                "        \"1\",\n" +
//                "        \"2\",\n" +
//                "        \"3\",\n" +
//                "        \"4\",\n" +
//                "        \"5\",\n" +
//                "        \"6\"\n" +
//                "      ]\n" +
//                "    ],\n" +
//                "    \"enable_stimes\": [\n" +
//                "      \"00:00\"\n" +
//                "    ],\n" +
//                "    \"enable_etimes\": [\n" +
//                "      \"00:00\"\n" +
//                "    ]\n" +
//                "  }\n" +
//                "]";
//        doPost(url,json);


//        //修改告警规则
//        String url="http://172.22.1.221:17000/v1/n9e/busi-group/6/alert-rule/26";
//        String json="{\n" +
//                "  \"disabled\": 1,\n" +
//                "  \"name\": \"dddd22\",\n" +
//                "  \"append_tags\": [],\n" +
//                "  \"group_id\": 6,\n" +
//                "  \"note\": \"wwwwccc\",\n" +
//                "  \"prod\": \"metric\",\n" +
//                "  \"rule_config\": {\n" +
//                "    \"queries\": [\n" +
//                "      {\n" +
//                "        \"prom_ql\": \"cpu_usage_active>30\",\n" +
//                "        \"severity\": 1,\n" +
//                "        \"keys\": {\n" +
//                "          \"labelKey\": \"\",\n" +
//                "          \"valueKey\": \"\"\n" +
//                "        }\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  \"enable_status\": false,\n" +
//                "  \"notify_channels\": [],\n" +
//                "  \"notify_groups\": [],\n" +
//                "  \"notify_recovered\": 1,\n" +
//                "  \"recover_duration\": 0,\n" +
//                "  \"notify_repeat_step\": 1,\n" +
//                "  \"notify_max_number\": 0,\n" +
//                "  \"callbacks\": [],\n" +
//                "  \"annotations\": {},\n" +
//                "  \"cate\": \"prometheus\",\n" +
//                "  \"datasource_ids\": [\n" +
//                "    0\n" +
//                "  ],\n" +
//                "  \"prom_eval_interval\": 20,\n" +
//                "  \"prom_for_duration\": 60,\n" +
//                "  \"enable_in_bg\": 0,\n" +
//                "  \"enable_days_of_weeks\": [\n" +
//                "    [\n" +
//                "      \"0\",\n" +
//                "      \"1\",\n" +
//                "      \"6\",\n" +
//                "      \"3\",\n" +
//                "      \"4\",\n" +
//                "      \"5\",\n" +
//                "      \"2\"\n" +
//                "    ]\n" +
//                "  ],\n" +
//                "  \"enable_stimes\": [\n" +
//                "    \"00:00\"\n" +
//                "  ],\n" +
//                "  \"enable_etimes\": [\n" +
//                "    \"00:00\"\n" +
//                "  ]\n" +
//                "}";
//        doPut(url,json);


    }



}
