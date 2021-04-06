package me.tulio.lff;

import me.tulio.lff.util.CC;
import me.tulio.lff.util.Cooldown;
import me.tulio.lff.util.TimeUtil;
import me.tulio.lff.util.command.BaseCommand;
import me.tulio.lff.util.command.Command;
import me.tulio.lff.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LFFCommand extends BaseCommand {

    @Command(name = "lff", permission = "tulongas.command.lff")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        if (LFF.getInfo().containsKey(player.getUniqueId())) {
            LFF lff = LFF.getInfo().get(player.getUniqueId());
            if (lff.getCooldown().hasExpired()) {
                Main.get().getMainConfig().getStringList("SENT").forEach(s -> {
                    player.sendMessage(CC.translate(s.replace("{time}", TimeUtil.millisToRoundedTime(Main.get().getMainConfig().getLong("TIME")))));
                });
                for (String s : Main.getMessage()) {
                    Bukkit.broadcastMessage(CC.translate(s.replace("{bars}", CC.LINE).replace("{player}", player.getName())));
                }

                lff.setCooldown(new Cooldown(Main.get().getMainConfig().getLong("TIME")));
                LFF.getInfo().put(player.getUniqueId(), lff);
            } else {
                Main.get().getMainConfig().getStringList("WAITING").forEach(s -> {
                    player.sendMessage(CC.translate(s.replace("{time}", TimeUtil.millisToRoundedTime(lff.getCooldown().getRemaining()))));
                });
            }
        } else {
            Main.get().getMainConfig().getStringList("SENT").forEach(s -> {
                player.sendMessage(CC.translate(s.replace("{time}", TimeUtil.millisToRoundedTime(Main.get().getMainConfig().getLong("TIME")))));
            });
            for (String s : Main.getMessage()) {
                Bukkit.broadcastMessage(CC.translate(s.replace("{bars}", CC.LINE).replace("{player}", player.getName())));
            }

            LFF lff = new LFF(player, new Cooldown(Main.get().getMainConfig().getLong("TIME")));
            LFF.getInfo().put(player.getUniqueId(), lff);
        }
    }
}
