package kazumy.project.maxbot.configuration.basic;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Getter @Accessors(fluent = true)
@ConfigSection("maxbot")
@ConfigFile("config.yml")
public class DiscordValue implements ConfigurationInjectable {

    @Getter private static final DiscordValue instance = new DiscordValue();

    @ConfigField("token") private String token;

    @ConfigField("guild") private String guild;

    @ConfigField("ticket") private String ticket;

    public static <T> T get(Function<DiscordValue, T> function) {
        return function.apply(instance);
    }
}
