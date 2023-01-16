package kazumy.project.maxbot.discord;

import kazumy.project.maxbot.configuration.basic.DiscordValue;
import kazumy.project.maxbot.discord.listener.EventListener;
import kazumy.project.maxbot.discord.listener.InteractionManager;
import kazumy.project.maxbot.spigot.SpigotMain;
import lombok.Data;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public class DiscordMain {

    private InteractionManager interactionManager = new InteractionManager();
    private final SpigotMain plugin;

    private JDA jda;

    public DiscordMain startup() {
        try {
            jda = JDABuilder.createDefault(DiscordValue.get(DiscordValue::token))
                    .addEventListeners(new EventListener(this))
                    .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                    .build().awaitReady();

            interactionManager.initInteraction();
            Bukkit.getConsoleSender().sendMessage("§a[MaxBot] §f O bot foi carregado com êxito!");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§c[ERRO] §f Não foi possível inicializar o bot!");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        return this;
    }
}
