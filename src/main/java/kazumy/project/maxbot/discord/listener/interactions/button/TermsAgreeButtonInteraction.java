package kazumy.project.maxbot.discord.listener.interactions.button;

import kazumy.project.maxbot.configuration.embed.CloseTicketEmbedValue;
import kazumy.project.maxbot.configuration.embed.ServiceEmbedValue;
import kazumy.project.maxbot.configuration.menu.ServiceMenuValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class TermsAgreeButtonInteraction extends InteractionService<ButtonInteractionEvent> {

    public TermsAgreeButtonInteraction() {
        super("terms-agree");
    }

    @Override
    public void execute(ButtonInteractionEvent event) {
        event.getMessage().delete().queue();
        event.getChannel().sendMessageEmbeds(
                        CloseTicketEmbedValue.instance().toEmbed(),
                        ServiceEmbedValue.instance().toEmbed())
                .addActionRow(ServiceMenuValue.instance().toMenu("services"))
                .queue();
    }
}
