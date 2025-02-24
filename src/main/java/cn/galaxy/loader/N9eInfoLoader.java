package cn.galaxy.loader;

import cn.gsq.common.AbstractInformationLoader;
import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * Project : galaxy
 * Class : cn.galaxy.loader.N9eInfoLoader
 *
 * @author : xyy
 * @date : 2024-08-23 11:09
 * @note : It's not technology, it's art !
 **/
public class N9eInfoLoader extends AbstractInformationLoader {
    /**
     * @Description : agent进程不启动
     * @Param : []
     * @Return : boolean
     * @Author : gsq
     * @Date : 15:21
     * @note : An art cell !
     **/
    @Override
    public boolean isEnable() {
        return !System.getenv("ROLE").equals("agent");
    }

}
