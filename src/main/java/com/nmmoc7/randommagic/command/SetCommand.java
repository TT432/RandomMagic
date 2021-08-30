package com.nmmoc7.randommagic.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.nmmoc7.randommagic.RandomMagic;
import com.nmmoc7.randommagic.capability.CapabilityMana;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SetCommand implements Command<CommandSource> {
    public static final SetCommand INSTANCE = new SetCommand();

    public static final LiteralArgumentBuilder<CommandSource> COMMAND = Commands.literal(RandomMagic.MOD_ID).then(
            Commands.literal("set").requires((commandSource) -> commandSource.hasPermissionLevel(2))
                    .then(Commands.argument("target", EntityArgument.player())
                            .then(Commands.literal("max")
                                    .then(Commands.argument("value", IntegerArgumentType.integer()).executes(INSTANCE)))
                            .then(Commands.literal("mana")
                                    .then(Commands.argument("value", IntegerArgumentType.integer()).executes(INSTANCE)))
            )
    );

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = EntityArgument.getPlayer(context, "target");
        int value = IntegerArgumentType.getInteger(context, "value");

        if (context.getInput().contains("max")) {
            CapabilityMana.getCapability(player).setMaxMana(player, value);
        }
        else if (context.getInput().contains("mana")) {
            CapabilityMana.getCapability(player).setMana(player, value);
        }
        return 0;
    }
}
