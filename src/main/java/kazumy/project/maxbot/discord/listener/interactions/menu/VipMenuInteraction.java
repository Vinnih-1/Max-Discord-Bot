package kazumy.project.maxbot.discord.listener.interactions.menu;

import kazumy.project.maxbot.configuration.embed.NicknameEmbedValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import kazumy.project.maxbot.payment.Payment;
import kazumy.project.maxbot.payment.PaymentData;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.utils.FileUpload;
import org.apache.commons.lang.RandomStringUtils;

import java.awt.*;
import java.math.BigDecimal;

public class VipMenuInteraction extends InteractionService<SelectMenuInteraction> {

    public VipMenuInteraction() {
        super("vip");
    }

    @Override
    public void execute(SelectMenuInteraction event) {
        val payment = new Payment();
        val value = event.getSelectedOptions().get(0).getValue().split("\\.");
        val data = PaymentData.builder()
                .id(RandomStringUtils.randomAlphanumeric(15))
                .title(event.getSelectedOptions().get(0).getLabel())
                .description(event.getSelectedOptions().get(0).getDescription())
                .price(new BigDecimal(value[2]))
                .build();
        payment.request(data).wait(success -> {
            event.getChannel().sendMessageEmbeds(NicknameEmbedValue.instance().toEmbed())
                    .addActionRow(Button.success("nickname", "Nickname"))
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
