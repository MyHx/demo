package com.hx.listener;

/**
 * 模块事件源
 *
 * @author hexian
 * @date 2021/5/19 20:44
 */
public class ModuleEventSource {

    private ModuleListener moduleListener;

    /**
     * 删除模块逻辑
     *
     * @param id 模块ID
     */
    void deleteModule(Integer id) {
        System.out.println("删除了一个" + id + "模块");
        // 执行了删除模块方法，需要处理菜单关联模块逻辑
        moduleListener.deleteModuleListener(new ModuleEventObject(this));
    }

    /**
     * 注册监听器，该类没有监听器对象啊，那么就传递进来吧。
     *
     * @param moduleListener 模块监听器
     */
    public void registerLister(ModuleListener moduleListener) {
        this.moduleListener = moduleListener;
    }
}
