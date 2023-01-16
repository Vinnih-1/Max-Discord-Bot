package kazumy.project.maxbot.discord.listener.interactions.menu;

import kazumy.project.maxbot.configuration.basic.CommandValue;
import kazumy.project.maxbot.configuration.embed.NicknameEmbedValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import kazumy.project.maxbot.payment.Payment;
import kazumy.project.maxbot.payment.PaymentData;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;
import net.dv8tion.jda.api.utils.FileUpload;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;

import java.awt.*;
import java.math.BigDecimal;

public class UnbanMenuInteraction extends InteractionService<SelectMenuInteraction> {

    public UnbanMenuInteraction() {
        super("menu-unban");
    }

    @Override
    public void execute(SelectMenuInteraction event) {
        val payment = new Payment();
        val value = event.getSelectedOptions().get(0).getValue().split("\\.");
        val data = PaymentData.builder()
                .id(RandomStringUtils.randomAlphanumeric(15))
                .title(event.getSelectedOptions().get(0).getLabel())
                .description(event.getSelectedOptions().get(0).getDescription())
                .price(new BigDecimal(value[1]))
                .build();

        payment.request(data).wait(success -> {
            event.getChannel().sendMessageEmbeds(NicknameEmbedValue.instance().toEmbed())
                    .addActionRow(Button.success("button-unban", "Nickname"))
                    .queue();
        });
        event.deferReply(false).setContent("Cobrança Automática de Serviços Prestados")
                .addEmbeds(new EmbedBuilder()
                                .setColor(Color.GRAY)
                                .setTitle(":white_check_mark: | PIX! Basta copiar e colar!")
                                .setDescription(payment.getQrData())
                                .build())
                .addFiles(FileUpload.fromData(payment.getAsImage(event.getMember().getId()))).queue();
    }
}
