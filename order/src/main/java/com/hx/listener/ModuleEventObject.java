package com.hx.listener;

/**
 * 模块事件对象
 *
 * @author hexian
 * @date 2021/5/19 20:42
 */
public class ModuleEventObject {

    private ModuleEventSource moduleEventSource;

    public ModuleEventObject() {
    }

    public ModuleEventObject(ModuleEventSource moduleEventSource) {
        this.moduleEventSource = moduleEventSource;
    }

    public ModuleEventSource getModuleEventSource() {
        return moduleEventSource;
    }
}
