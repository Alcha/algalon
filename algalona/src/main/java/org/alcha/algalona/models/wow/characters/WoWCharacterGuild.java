package org.alcha.algalona.models.wow.characters;

import org.alcha.algalona.models.wow.WoWBattlegroup;
import org.alcha.algalona.models.wow.guilds.WoWGuild;
import org.alcha.algalona.models.wow.realms.WoWRealms;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <p>Created by Alcha on 8/4/2017.</p>
 */

public class WoWCharacterGuild extends WoWCharacterField {
    private WoWGuild mGuild;

    private WoWCharacterGuild() {

    }

    private WoWCharacterGuild(WoWGuild guild) {
        mGuild = guild;
    }

    public static WoWCharacterGuild newInstance() {
        return new WoWCharacterGuild();
    }

    public static WoWCharacterGuild newInstance(WoWGuild guild) {
        return new WoWCharacterGuild(guild);
    }

    public static WoWCharacterGuild newInstanceFromJSON(JSONObject jsonObject) {
        WoWCharacterGuild characterGuild = new WoWCharacterGuild();

        try {
            WoWGuild guild = WoWGuild.newInstance();
            guild.setName(jsonObject.getString("name"));
            guild.setRealm(WoWRealms.fromString(jsonObject.getString("realm")));
            guild.setBattleGroup(WoWBattlegroup.valueOf(jsonObject.getString("battlegroup")));
            guild.setLevel(0);
            guild.setSide(0);
            guild.setAchievementPoints(0);
            guild.setEmblem(new WoWGuild.Emblem());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return characterGuild;
    }
}