package kazumy.project.maxbot.discord.listener.interactions.modal;

import kazumy.project.maxbot.discord.listener.InteractionService;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class NicknameModalInteraction extends InteractionService<ModalInteractionEvent> {

    public NicknameModalInteraction() {
        super("modalname");
    }

    @Override
    public void execute(ModalInteractionEvent event) {
        System.out.println(event.getInteraction().getValues().get(0).getId());
    }
}
