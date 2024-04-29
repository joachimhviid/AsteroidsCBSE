module enemy {
    requires com.almasb.fxgl.all;
    requires common;

    opens enemysystem to com.almasb.fxgl.core;

    provides services.IGamePluginService with enemysystem.EnemyFactory;
}