package net.aerelldev.mbtlhihh;

import net.aerelldev.mbtlhihh.commands.MbtlhihhCommand;
import net.aerelldev.mbtlhihh.core.IMbtlhihhPlayer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class MBTLHIHH implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> MbtlhihhCommand.register(dispatcher));
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            IMbtlhihhPlayer oldP = (IMbtlhihhPlayer) oldPlayer;
            IMbtlhihhPlayer newP = (IMbtlhihhPlayer) newPlayer;
            newP.setIsMbtlhihh(oldP.getMbtlhihh());
            newP.setMbtlhihhUseTotem(oldP.getMbtlhihhUseTotem());
        });
    }
}
