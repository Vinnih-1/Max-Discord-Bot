package kazumy.project.maxbot.discord.listener.interactions.menu;

import kazumy.project.maxbot.configuration.basic.CommandValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import kazumy.project.maxbot.payment.Payment;
import kazumy.project.maxbot.payment.PaymentData;
import lombok.val;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;

import java.math.BigDecimal;

public class CashMenuInteraction extends InteractionService<SelectMenuInteraction> {

    public CashMenuInteraction() {
        super("cash");
    }

    @Override
    public void execute(SelectMenuInteraction event) {
        val value = event.getSelectedOptions().get(0).getValue().split("\\.");
        val data = PaymentData.builder()
                .id(RandomStringUtils.randomAlphanumeric(15))
                .title(event.getSelectedOptions().get(0).getLabel())
                .description(event.getSelectedOptions().get(0).getDescription())
                .price(new BigDecimal(value[2]))
                .build();

        new Payment().request(data).wait(success -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format(CommandValue.get(CommandValue::vip),
                value[0],
                value[1])));
    }
}
