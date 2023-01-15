package kazumy.project.maxbot.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import kazumy.project.maxbot.configuration.basic.CommandValue;
import kazumy.project.maxbot.configuration.basic.DiscordValue;
import kazumy.project.maxbot.configuration.basic.PaymentValue;
import kazumy.project.maxbot.configuration.embed.*;
import kazumy.project.maxbot.configuration.menu.*;
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
                        CommandValue.instance(),

                        TicketEmbedValue.instance(),
                        ServiceEmbedValue.instance(),
                        TermsEmbedValue.instance(),
                        CloseTicketEmbedValue.instance(),
                        CashEmbedValue.instance(),
                        VipEmbedValue.instance(),
                        UnbanEmbedValue.instance(),
                        NicknameEmbedValue.instance(),

                        CashMenuValue.instance(),
                        UnbanMenuValue.instance(),
                        VipMenuValue.instance(),
                        TicketMenuValue.instance(),
                        ServiceMenuValue.instance()
                );
    }
}
