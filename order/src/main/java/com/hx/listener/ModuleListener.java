package com.hx.listener;

/**
 * 模块监听器
 *
 * @author hexian
 * @date 2021/5/19 20:50
 */
public interface ModuleListener {

    /**
     * 删除模块监听器
     *
     * @param moduleEventObject 模块事件对象
     */
    void deleteModuleListener(ModuleEventObject moduleEventObject);
}
