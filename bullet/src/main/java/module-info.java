module bullet {
    requires com.almasb.fxgl.all;
    requires common;

    opens bulletsystem to com.almasb.fxgl.core;

    provides services.IGamePluginService with bulletsystem.BulletFactory;
}