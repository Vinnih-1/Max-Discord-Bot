package kazumy.project.maxbot.configuration.basic;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Getter @Accessors(fluent = true)
@ConfigSection("maxbot.commands")
@ConfigFile("config.yml")
public class CommandValue implements ConfigurationInjectable {

    @Getter private static final CommandValue instance = new CommandValue();

    @ConfigField("vip") private String vip;

    @ConfigField("cash") private String cash;

    @ConfigField("unban") private String unban;

    public static <T> T get(Function<CommandValue, T> function) {
        return function.apply(instance);
    }
}
