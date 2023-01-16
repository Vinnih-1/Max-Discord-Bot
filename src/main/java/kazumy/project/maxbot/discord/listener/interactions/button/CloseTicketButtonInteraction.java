package kazumy.project.maxbot.discord.listener.interactions.button;

import kazumy.project.maxbot.discord.listener.InteractionService;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class CloseTicketButtonInteraction extends InteractionService<ButtonInteractionEvent> {

    public CloseTicketButtonInteraction() {
        super("close-ticket");
    }

    @Override
    public void execute(ButtonInteractionEvent event) {
        event.getChannel().delete().queue();
    }
}
