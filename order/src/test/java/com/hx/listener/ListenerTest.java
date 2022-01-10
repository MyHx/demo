package com.hx.listener;

/**
 * @author hexian
 * @date 2021/5/19 21:03
 */
public class ListenerTest {
    public static void main(String[] args) {

        ModuleEventSource moduleEventSource = new ModuleEventSource();

        //注册监听器()
        moduleEventSource.registerLister(new ModuleListener() {
            @Override
            public void deleteModuleListener(ModuleEventObject moduleEventObject) {
                ModuleEventSource moduleEventSource1 = moduleEventObject.getModuleEventSource();
                System.out.println(moduleEventSource1 + "刚才删除了一个模块");
            }
        });


        //当调用删除模块方法时，触发事件，将事件对象传递给监听器，最后监听器获得事件源，对事件源进行操作
        moduleEventSource.deleteModule(1);
    }
}
