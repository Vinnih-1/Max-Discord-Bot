package kazumy.project.maxbot.configuration.basic;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Getter @Accessors(fluent = true)
@ConfigSection("maxbot.mercado-pago")
@ConfigFile("config.yml")
public class PaymentValue implements ConfigurationInjectable {

    @Getter private static final PaymentValue instance = new PaymentValue();

    @ConfigField("access-token") private String token;

    @ConfigField("external-pos-id") private String pos;

    @ConfigField("user-id") private String user;

    public static <T> T get(Function<PaymentValue, T> function) {
        return function.apply(instance);
    }
}
