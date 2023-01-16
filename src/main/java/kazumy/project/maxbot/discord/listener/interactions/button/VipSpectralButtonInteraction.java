package kazumy.project.maxbot.discord.listener.interactions.button;

import kazumy.project.maxbot.discord.listener.InteractionService;
import lombok.val;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class VipSpectralButtonInteraction extends InteractionService<ButtonInteractionEvent> {

    public VipSpectralButtonInteraction() {
        super("button-vip-spectral");
    }

    @Override
    public void execute(ButtonInteractionEvent event) {
        val modal = Modal.create("nickname", "Insira o seu Nickname");
        modal.addActionRow(TextInput.create("spectral", "Name", TextInputStyle.SHORT)
                .setPlaceholder("Nickname")
                .build());
        event.replyModal(modal.build()).queue();
    }
}
