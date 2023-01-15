package kazumy.project.maxbot.discord.listener.interactions.menu;

import kazumy.project.maxbot.configuration.basic.DiscordValue;
import kazumy.project.maxbot.configuration.embed.TermsEmbedValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;

import java.util.Arrays;
import java.util.Collections;

public class TicketMenuInteraction extends InteractionService<SelectMenuInteraction> {

    public TicketMenuInteraction() {
        super("ticket");
    }

    @Override
    public void execute(SelectMenuInteraction event) {
        val category = event.getGuild().getCategoryById(DiscordValue.get(DiscordValue::ticket));
        val menu = SelectMenu.fromData(event.getSelectMenu().toData());
        event.editSelectMenu(menu.setDefaultValues(Collections.emptyList()).build()).queue();

        category.createTextChannel(String.format("%s-comprar", event.getUser().getName()))
                .addMemberPermissionOverride(event.getMember().getIdLong(),
                        Arrays.asList(
                                Permission.VIEW_CHANNEL,
                                Permission.MESSAGE_SEND,
                                Permission.MESSAGE_ATTACH_FILES
                        ),
                        Collections.singletonList(Permission.ADMINISTRATOR)).queue(success -> {
                            success.sendMessageEmbeds(TermsEmbedValue.instance().toEmbed())
                                    .addActionRow(Button.success("terms-agree", "Sim"),
                                            Button.danger("terms-disagree", "Não"))
                                    .queue();
                });
    }
}
