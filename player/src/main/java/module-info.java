module player {
    requires com.almasb.fxgl.all;
    requires common;

    opens playersystem to com.almasb.fxgl.core;

    provides services.IGamePluginService with playersystem.PlayerFactory;
    provides services.IInputService with playersystem.PlayerControl;
}