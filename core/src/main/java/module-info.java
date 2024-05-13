module core {
    requires com.almasb.fxgl.all;
    requires common;
    requires java.net.http;

    exports launcher to com.almasb.fxgl.core;

    uses services.IGamePluginService;
    uses services.IInputService;
}