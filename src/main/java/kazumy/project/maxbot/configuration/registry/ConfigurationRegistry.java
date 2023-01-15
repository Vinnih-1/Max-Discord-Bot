package kazumy.project.maxbot.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import kazumy.project.maxbot.configuration.basic.DiscordValue;
import kazumy.project.maxbot.configuration.basic.PaymentValue;
import kazumy.project.maxbot.configuration.embed.CloseTicketEmbedValue;
import kazumy.project.maxbot.configuration.embed.ServiceEmbedValue;
import kazumy.project.maxbot.configuration.embed.TermsEmbedValue;
import kazumy.project.maxbot.configuration.embed.TicketEmbedValue;
import kazumy.project.maxbot.configuration.menu.ServiceMenuValue;
import kazumy.project.maxbot.configuration.menu.TicketMenuValue;
import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

@Data(staticConstructor = "of")
public class ConfigurationRegistry {

    private final JavaPlugin plugin;

    public void registry() {
        new BukkitConfigurationInjector(plugin)
                .injectConfiguration(
                        PaymentValue.instance(),
                        DiscordValue.instance(),

                        TicketEmbedValue.instance(),
                        ServiceEmbedValue.instance(),
                        TermsEmbedValue.instance(),
                        CloseTicketEmbedValue.instance(),

                        TicketMenuValue.instance(),
                        ServiceMenuValue.instance()
                );
    }
}
