package me.tulio.lff;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.tulio.lff.util.Cooldown;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class LFF {

    @Getter public static Map<UUID, LFF> info = Maps.newHashMap();

    private Player player;
    private Cooldown cooldown;
}
