package org.alcha.algalonj.models.wow.characters;

/**
 * <p>Created by Alcha on 8/4/2017.</p>
 */

public class WoWCharacterFeedCriteria extends WoWCharacterFeedEntry {
    private WoWCharacterFeedCriteria() {
        setEntryType(Type.CRITERIA);
    }

    public WoWCharacterFeedCriteria newInstance() { return new WoWCharacterFeedCriteria();}

    public WoWCharacterFeedCriteria newInstanceFromJSON(JSONObject jsonObject) {
        WoWCharacterFeedCriteria feedCriteria = new WoWCharacterFeedCriteria();

        return feedCriteria;
    }
}
