module core {
    requires com.almasb.fxgl.all;
    requires common;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    exports launcher to com.almasb.fxgl.core, spring.core, spring.beans, spring.context;

    uses services.IGamePluginService;
    uses services.IInputService;

    opens launcher to spring.core;
}