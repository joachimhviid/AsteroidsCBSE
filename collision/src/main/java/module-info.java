module collision {
    requires com.almasb.fxgl.all;
    requires common;

    opens collisionsystem to com.almasb.fxgl.core;

    provides services.IGamePluginService with collisionsystem.CollisionPlugin;
}