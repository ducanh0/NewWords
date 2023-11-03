package Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;

public class Factory implements EntityFactory {
    public static final int BACKGROUND_W = 1500;
    public static final int BACKGROUND_H = 800;
    @Spawns("Background")
    public Entity SpawnBackground(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.BACKGROUND)
                .view(data.<String>get("link"))
                .zIndex(-100)
                .buildAndAttach();
    }

    public static final int MAINCHA_W = 50;
    public static final int MAINCHA_H = 36;
    @Spawns("Maincha")
    public Entity SpawnMainCha(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.MAINCHA)
                .viewWithBBox(data.<String>get("link"))
                .with(new CollidableComponent(true))
                .with(new Behavior())
                .zIndex(-50)
                .buildAndAttach();
    }

    public static final int ENEMY_W = 20;
    public static final int ENEMY_H = 20;
    @Spawns("Enemy")
    public Entity SpawnEnemy(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(data.<String>get("link"))
                .with(new CollidableComponent(true))
                .with(new Behavior())
                .with(new Txt())
                .buildAndAttach();
    }

    public static final int BULLET_W = 10;
    public static final int BULLET_H = 10;
    @Spawns("Bullet")
    public Entity SpawnBullet(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.BULLET)
                .viewWithBBox(data.<String>get("link"))
                .with(new CollidableComponent(true))
                .with(new Behavior())
                .zIndex(-75)
                .buildAndAttach();
    }

    public static final int GAI_W = 16;
    public static final int GAI_H = 16;
    @Spawns("Gai")
    public Entity SpawnGai(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.GAI)
                .viewWithBBox(data.<String>get("link"))
                .with(new CollidableComponent(true))
                .with(new Behavior())
                .zIndex(-15)
                .buildAndAttach();
    }

    public static final int OBJ_W = 32;
    public static final int OBJ_H = 32;
    @Spawns("Obj")
    public Entity SpawnObj(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.OBJ)
                .viewWithBBox(data.<String>get("link"))
                .with(new CollidableComponent(true))
                .with(new Behavior())
                .zIndex(-25)
                .buildAndAttach();
    }
}
