package kazumy.project.maxbot.discord.listener.interactions.button;

import kazumy.project.maxbot.discord.listener.InteractionService;
import lombok.val;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class NicknameButtonInteraction extends InteractionService<ButtonInteractionEvent> {

    public NicknameButtonInteraction() {
        super("nickname");
    }

    @Override
    public void execute(ButtonInteractionEvent event) {
        val modal = Modal.create("modalname", "Insira o seu Nickname");
        // TODO: Discernir as intenções do pagamento para executar o comando específico do produto
        modal.addActionRow(TextInput.create("name", "Name", TextInputStyle.SHORT)
                .setPlaceholder("Nickname")
                .build());
        event.replyModal(modal.build()).queue();
    }
}
