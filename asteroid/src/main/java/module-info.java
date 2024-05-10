module asteroid {
    requires com.almasb.fxgl.all;
    requires common;

    opens asteroidsystem to com.almasb.fxgl.core;

    provides services.IGamePluginService with asteroidsystem.AsteroidFactory;
}