package net.aerelldev.mbtlhihh.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.aerelldev.mbtlhihh.core.IMbtlhihhPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class MbtlhihhCommand {
    public static String prefix = "keepalive";
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal(prefix)
                        .requires(sourceStack -> sourceStack.hasPermission(4))
                        .then(Commands.literal("status")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(MbtlhihhCommand::status))
                        )
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("status", BoolArgumentType.bool())
                                        .then(Commands.argument("use_totem", BoolArgumentType.bool())
                                                .executes(MbtlhihhCommand::keepalive))
                                )
                        )
        );
    }

    private static int status(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack sourceStack = context.getSource();
        ServerPlayer serverPlayer = EntityArgument.getPlayer(context, "player");
        String playerName = serverPlayer.getName().getString();
        IMbtlhihhPlayer iMbtlhihhPlayer = (IMbtlhihhPlayer) serverPlayer;
        String message = "[Keep-Alive Mod] \n"
                .concat("Name : " + playerName + "\n")
                .concat("Status : " + (iMbtlhihhPlayer.getMbtlhihh() ? "§etrue" : "§cfalse") + "§f\n")
                .concat("Use Totem : " + (iMbtlhihhPlayer.getMbtlhihhUseTotem() ? "§etrue" : "§cfalse") + "§f\n");
        sourceStack.sendSuccess(() ->
                Component.literal(message), true);
        return 1;
    }

    private static int keepalive(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack sourceStack = context.getSource();
        ServerPlayer serverPlayer = EntityArgument.getPlayer(context, "player");
        String playerName = serverPlayer.getName().getString();
        IMbtlhihhPlayer iMbtlhihhPlayer = (IMbtlhihhPlayer) serverPlayer;
        boolean status = BoolArgumentType.getBool(context, "status");
        boolean totem = BoolArgumentType.getBool(context, "use_totem");
        iMbtlhihhPlayer.setIsMbtlhihh(status);
        iMbtlhihhPlayer.setMbtlhihhUseTotem(totem);
        String message = "[Keep-Alive Mod] \n"
                .concat("Name : " + playerName + "\n")
                .concat("Status -> " + (iMbtlhihhPlayer.getMbtlhihh() ? "§etrue" : "§cfalse") + "§f\n")
                .concat("Use Totem -> " + (iMbtlhihhPlayer.getMbtlhihhUseTotem() ? "§etrue" : "§cfalse") + "§f\n");
        sourceStack.sendSuccess(() ->
                Component.literal(message), true);
        return 1;
    }
}
