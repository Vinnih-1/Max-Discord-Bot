package kazumy.project.maxbot.discord.listener.interactions.menu;

import kazumy.project.maxbot.configuration.embed.CashEmbedValue;
import kazumy.project.maxbot.configuration.embed.UnbanEmbedValue;
import kazumy.project.maxbot.configuration.embed.VipEmbedValue;
import kazumy.project.maxbot.configuration.menu.CashMenuValue;
import kazumy.project.maxbot.configuration.menu.UnbanMenuValue;
import kazumy.project.maxbot.configuration.menu.VipMenuValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import lombok.val;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;

public class ServicesMenuInteraction extends InteractionService<SelectMenuInteraction> {

    public ServicesMenuInteraction() {
        super("services");
    }

    @Override
    public void execute(SelectMenuInteraction event) {
        val key = event.getSelectedOptions().get(0).getValue();

        switch (key) {
            case "vip":
                event.deferReply(false).addEmbeds(VipEmbedValue.instance().toEmbed())
                        .addActionRow(VipMenuValue.instance().toMenu()).queue();
                break;
            case "cash":
                event.deferReply(false).addEmbeds(CashEmbedValue.instance().toEmbed())
                        .addActionRow(CashMenuValue.instance().toMenu()).queue();
                break;
            case "unban":
                event.deferReply(false).addEmbeds(UnbanEmbedValue.instance().toEmbed())
                        .addActionRow(UnbanMenuValue.instance().toMenu()).queue();
                break;
        }
    }
}
