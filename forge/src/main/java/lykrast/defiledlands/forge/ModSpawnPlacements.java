package lykrast.defiledlands.forge;

import lykrast.defiledlands.common.registry.ModEntityTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 注册所有污秽之地实体的生成放置规则。
 * 若不注册，NaturalSpawner 会跳过光照检查，导致怪物全天候生成。
 */
@Mod.EventBusSubscriber(modid = "defiledlands", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSpawnPlacements {

    @SubscribeEvent
    public static void onRegisterSpawnPlacements(SpawnPlacementRegisterEvent event) {
        // Shambler / Twisted Shambler / Host — 亡灵类怪物，仅在暗处生成
        event.register(ModEntityTypes.SHAMBLER.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);

        event.register(ModEntityTypes.SHAMBLER_TWISTED.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);

        event.register(ModEntityTypes.HOST.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);

        // Scuttler — 类蜘蛛怪物，使用与原版蜘蛛相同的生成规则
        event.register(ModEntityTypes.SCUTTLER.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);

        // Defiled Slime — 继承 Slime 非 Monster，其自身 checkSpawnRules 已包含光照检查
        event.register(ModEntityTypes.SLIME_DEFILED.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                net.minecraft.world.entity.Mob::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
