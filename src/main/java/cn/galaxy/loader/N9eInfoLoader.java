package cn.galaxy.loader;

import cn.gsq.common.AbstractInformationLoader;
import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * Project : galaxy
 * Class : cn.galaxy.loader.N9eInfoLoader
 *
 * @author : xyy
 * @since  : 2024-08-23 11:09
 **/
public class N9eInfoLoader extends AbstractInformationLoader {
    /**
     *agent进程不启动
     * @author : gsq
     * @since  : 15:21
     **/
    @Override
    public boolean isEnable() {
        return !System.getenv("ROLE").equals("agent");
    }

}
