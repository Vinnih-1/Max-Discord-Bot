package kazumy.project.maxbot.spigot;

import kazumy.project.maxbot.configuration.basic.DiscordValue;
import kazumy.project.maxbot.configuration.embed.TicketEmbedValue;
import kazumy.project.maxbot.configuration.menu.TicketMenuValue;
import kazumy.project.maxbot.configuration.registry.ConfigurationRegistry;
import kazumy.project.maxbot.discord.DiscordMain;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SpigotMain extends JavaPlugin {

    private DiscordMain discordMain;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("embed.yml", false);
        saveResource("menu.yml", false);

        ConfigurationRegistry.of(this).registry();
        discordMain = DiscordMain.of(this).startup();
        discordMain.getJda().getGuildById(DiscordValue.get(DiscordValue::guild))
                .getTextChannelById("1043521638223851520")
                .sendMessageEmbeds(TicketEmbedValue.instance().toEmbed())
                .addActionRow(TicketMenuValue.instance().toMenu("ticket"))
                .queue();
    }
}
