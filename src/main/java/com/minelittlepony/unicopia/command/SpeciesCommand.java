package com.minelittlepony.unicopia.command;

import com.minelittlepony.unicopia.Race;
import com.minelittlepony.unicopia.entity.player.Pony;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;

class SpeciesCommand {
    static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("race");

        EnumArgumentType<Race> raceArgument = EnumArgumentType.of(Race.class, Race::isUsable, Race.EARTH);

        builder.then(CommandManager.literal("get")
                      .executes(context -> get(context.getSource(), context.getSource().getPlayer(), true))
               .then(CommandManager.argument("target", EntityArgumentType.player())
                      .executes(context -> get(context.getSource(), EntityArgumentType.getPlayer(context, "target"), false))
               ));

        builder.then(CommandManager.literal("set")
               .then(CommandManager.argument("race", raceArgument)
                       .executes(context -> set(context.getSource(), context.getSource().getPlayer(), context.getArgument("race", Race.class), true))
               .then(CommandManager.argument("target", EntityArgumentType.player())
                       .executes(context -> set(context.getSource(), EntityArgumentType.getPlayer(context, "target"), context.getArgument("race", Race.class), false))
               )));

        builder.then(CommandManager.literal("describe")
               .then(CommandManager.argument("race", raceArgument)
                       .executes(context -> describe(context.getSource().getPlayer(), context.getArgument("race", Race.class))
               )));

        builder.then(CommandManager.literal("list")
                       .executes(context -> list(context.getSource().getPlayer())
                ));

        dispatcher.register(builder);
    }

    static int set(ServerCommandSource source, PlayerEntity player, Race race, boolean isSelf) {

        if (race.isPermitted(player)) {
            Pony pony = Pony.of(player);
            pony.setSpecies(race);
            pony.setDirty();

            Text formattedName = new TranslatableText(race.name().toLowerCase());

            if (!isSelf) {
                source.sendFeedback(new TranslatableText("commands.race.success.other", player.getName(), formattedName), true);
            } else {
                if (player.getEntityWorld().getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK)) {
                    player.sendMessage(new TranslatableText("commands.race.success.self"), false);
                }
                source.sendFeedback(new TranslatableText("commands.race.success.otherself", player.getName(), formattedName), true);
            }
        } else if (player.getEntityWorld().getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK)) {
            player.sendMessage(new TranslatableText("commands.race.permission"), false);
        }

        return 0;
    }

    static int get(ServerCommandSource source, PlayerEntity player, boolean isSelf) {
        Race spec = Pony.of(player).getSpecies();

        String name = "commands.race.tell.";
        name += isSelf ? "self" : "other";

        player.sendMessage(new TranslatableText(name)
                .append(new TranslatableText(spec.getTranslationKey())
                        .styled(s -> s.withColor(Formatting.GOLD))), false);

        return 0;
    }

    static int list(PlayerEntity player) {
        player.sendMessage(new TranslatableText("commands.race.list"), false);

        MutableText message = new LiteralText("");

        boolean first = true;
        for (Race i : Race.values()) {
            if (!i.isDefault() && i.isPermitted(player)) {
                message.append(new TranslatableText((!first ? "\n" : "") + " - " + i.name().toLowerCase()));
                first = false;
            }
        }

        player.sendMessage(message.styled(s -> s.withColor(Formatting.GOLD)), false);

        return 0;
    }

    static int describe(PlayerEntity player, Race species) {
        String name = species.name().toLowerCase();

        player.sendMessage(new TranslatableText(String.format("commands.race.describe.%s.1", name)).styled(s -> s.withColor(Formatting.YELLOW)), false);
        player.sendMessage(new TranslatableText(String.format("commands.race.describe.%s.2", name)), false);
        player.sendMessage(new TranslatableText(String.format("commands.race.describe.%s.3", name)).styled(s -> s.withColor(Formatting.RED)), false);

        return 0;
    }
}
