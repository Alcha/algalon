package org.alcha.algalona.models.wow.characters;

import com.google.gson.JsonObject;

import org.alcha.algalona.models.wow.Region;
import org.alcha.algalona.models.wow.Faction;
import org.alcha.algalona.models.wow.battlegroups.WoWBattlegroup;
import org.alcha.algalona.models.wow.battlegroups.WoWEUBattlegroups;
import org.alcha.algalona.models.wow.battlegroups.WoWUSBattlegroups;
import org.alcha.algalona.models.wow.guilds.WoWGuildEmblem;
import org.alcha.algalona.models.wow.realms.WoWEURealms;
import org.alcha.algalona.models.wow.realms.WoWRealm;
import org.alcha.algalona.models.wow.realms.WoWUSRealms;

import static org.alcha.algalona.models.wow.characters.WoWCharacterField.Name.Guild;
import static org.alcha.algalona.network.AlgalonClient.getClientRegion;

/**
 * <p>Created by Alcha on 8/4/2017.</p>
 * <p>Stores the information related to the guild that a {@link Character WoWCharacters} is
 * in.</p>
 */
public class WoWCharacterGuild extends WoWCharacterField {
    private static final String LOG_TAG = "WoWCharacterGuild";
    private long mLastModified;
    private String mName;
    private WoWRealm mRealm;
    private WoWBattlegroup mBattlegroup;
    private int mLevel;
    private Faction mFaction = Faction.UNKNOWN;
    private int mAchievementPoints;
    private WoWGuildEmblem mEmblem;

    private WoWCharacterGuild() {
        setFieldName(Guild);
    }

    @Override
    public String toString() {
        return "Name = " + mName +
                ";Realm = " + mRealm +
                ";Battlegroup = " + mBattlegroup +
                ";Level = " + mLevel +
                ";Faction = " + mFaction.toString() +
                ";AchievementPoints = " + mAchievementPoints + ";";
    }

    public static WoWCharacterGuild newInstanceFromJson(JsonObject jsonObject) {
        WoWCharacterGuild characterGuild = new WoWCharacterGuild();
        Region region = getClientRegion();

        if (jsonObject.has("lastModified"))
            characterGuild.setLastModified(jsonObject.get("lastModified").getAsLong());

        if (jsonObject.has("name"))
            characterGuild.setName(jsonObject.get("name").getAsString());

        if (jsonObject.has("realm")) {
            if (region == Region.US) {
                characterGuild.setRealm(WoWUSRealms.fromString(jsonObject.get("realm").getAsString()));
            } else if (region == Region.EU) {
                characterGuild.setRealm(WoWEURealms.fromString(jsonObject.get("realm").getAsString()));
            }
        }

        if (jsonObject.has("battlegroup")) {
            if (region == Region.US) {
                characterGuild.setBattlegroup(WoWUSBattlegroups.fromString(jsonObject.get("battlegroup").getAsString()));
            } else if (region == Region.EU) {
                characterGuild.setBattlegroup(WoWEUBattlegroups.fromString(jsonObject.get("battlegroup").getAsString()));
            }
        }

        Character character = Character.newInstanceFromJson(new JsonObject());
        character.setBattlegroup(WoWUSBattlegroups.Aunz_Battle_Group);

        if (jsonObject.has("level"))
            characterGuild.setLevel(jsonObject.get("level").getAsInt());

        if (jsonObject.has("side"))
            characterGuild.setFaction(Faction.fromId(jsonObject.get("side").getAsInt()));

        if (jsonObject.has("achievementPoints"))
            characterGuild.setAchievementPoints(jsonObject.get("achievementPoints").getAsInt());

        if (jsonObject.has("emblem"))
            characterGuild.setEmblem(WoWGuildEmblem.newInstanceFromJSON(jsonObject.getAsJsonObject("emblem")));

        return characterGuild;
    }

    void setEmblem(WoWGuildEmblem emblem) {
        mEmblem = emblem;
    }

    public WoWGuildEmblem getEmblem() {
        return mEmblem;
    }

    void setLastModified(long lastModified) {
        mLastModified = lastModified;
    }

    public long getLastModified() {
        return mLastModified;
    }

    void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    void setRealm(WoWRealm realm) {
        mRealm = realm;
    }

    public WoWRealm getRealm() {
        return mRealm;
    }

    void setBattlegroup(WoWBattlegroup battlegroup) {
        mBattlegroup = battlegroup;
    }

    public WoWBattlegroup getBattlegroup() {
        return mBattlegroup;
    }

    void setLevel(int level) {
        mLevel = level;
    }

    public int getLevel() {
        return mLevel;
    }

    void setFaction(Faction faction) {
        mFaction = faction;
    }

    public Faction getFaction() {
        return mFaction;
    }

    void setAchievementPoints(int achievementPoints) {
        mAchievementPoints = achievementPoints;
    }

    public int getAchievementPoints() {
        return mAchievementPoints;
    }
}