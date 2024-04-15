module core {
    requires com.almasb.fxgl.all;
    requires common;

    exports launcher to com.almasb.fxgl.core;

    uses services.IGamePluginService;
}