package net.aerelldev.mbtlhihh;

import net.aerelldev.mbtlhihh.commands.MbtlhihhCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class MBTLHIHH implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> MbtlhihhCommand.register(dispatcher));
    }
}
