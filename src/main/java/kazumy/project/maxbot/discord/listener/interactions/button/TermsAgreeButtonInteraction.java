package kazumy.project.maxbot.discord.listener.interactions.button;

import kazumy.project.maxbot.configuration.embed.CloseTicketEmbedValue;
import kazumy.project.maxbot.configuration.embed.ServiceEmbedValue;
import kazumy.project.maxbot.configuration.menu.ServiceMenuValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class TermsAgreeButtonInteraction extends InteractionService<ButtonInteractionEvent> {

    public TermsAgreeButtonInteraction() {
        super("terms-agree");
    }

    @Override
    public void execute(ButtonInteractionEvent event) {
        event.getMessage().delete().queue();
        event.getChannel().sendMessageEmbeds(CloseTicketEmbedValue.instance().toEmbed())
                .addActionRow(Button.danger("close-ticket-button", "Fechar ticket"))
                .queue();

        event.getChannel().sendMessageEmbeds(ServiceEmbedValue.instance().toEmbed())
                .addActionRow(ServiceMenuValue.instance().toMenu("services"))
                .queue();
    }
}
