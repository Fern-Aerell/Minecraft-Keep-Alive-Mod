package net.aerelldev.mbtlhihh.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.aerelldev.mbtlhihh.core.IMbtlhihhPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Iterator;

public class MbtlhihhCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("mbtlhihh")
                        .requires(sourceStack -> sourceStack.hasPermission(4))
                        .then(Commands.literal("status")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(MbtlhihhCommand::status))
                        )
                        .then(Commands.literal("activate")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(MbtlhihhCommand::activate))
                        )
                        .then(Commands.literal("deactivate")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(MbtlhihhCommand::deactivate))
                        )
        );
    }

    private static int status(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack sourceStack = context.getSource();
        ServerPlayer serverPlayer = EntityArgument.getPlayer(context, "player");
        String playerName = serverPlayer.getName().getString();
        IMbtlhihhPlayer iMbtlhihhPlayer = (IMbtlhihhPlayer) serverPlayer;
        sourceStack.sendSuccess(() -> Component.literal("[MBTLHIHH] " + playerName + " : " + (iMbtlhihhPlayer.getMbtlhihh() ? "§etrue" : "§cfalse")), true);
        return 1;
    }

    private static int activate(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack sourceStack = context.getSource();
        ServerPlayer serverPlayer = EntityArgument.getPlayer(context, "player");
        String playerName = serverPlayer.getName().getString();
        IMbtlhihhPlayer iMbtlhihhPlayer = (IMbtlhihhPlayer) serverPlayer;
        iMbtlhihhPlayer.setIsMbtlhihh(true);
        sourceStack.sendSuccess(() -> Component.literal("[MBTLHIHH] " + playerName + " -> " + (iMbtlhihhPlayer.getMbtlhihh() ? "§etrue" : "§cfalse")), true);
        return 1;
    }

    private static int deactivate(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack sourceStack = context.getSource();
        ServerPlayer serverPlayer = EntityArgument.getPlayer(context, "player");
        String playerName = serverPlayer.getName().getString();
        IMbtlhihhPlayer iMbtlhihhPlayer = (IMbtlhihhPlayer) serverPlayer;
        iMbtlhihhPlayer.setIsMbtlhihh(false);
        sourceStack.sendSuccess(() -> Component.literal("[MBTLHIHH] " + playerName + " -> " + (iMbtlhihhPlayer.getMbtlhihh() ? "§etrue" : "§cfalse")), true);
        return 1;
    }
}
