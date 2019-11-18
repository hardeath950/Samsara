package org.l2j.gameserver.model.items.type;

/**
 * EtcItem Type enumerated.
 */
public enum EtcItemType implements ItemType {
    NONE,
    SCROLL,
    ARROW,
    POTION,
    SPELLBOOK,
    RECIPE,
    MATERIAL,
    PET_COLLAR,
    CASTLE_GUARD,
    DYE,
    SEED,
    SEED2,
    HARVEST,
    LOTTO,
    RACE_TICKET,
    TICKET_OF_LORD,
    LURE,
    CROP,
    MATURECROP,
    ENCHANT_WEAPON,
    ENCHANT_ARMOR,
    BLESSED_ENCHANT_WEAPON,
    BLESSED_ENCHANT_ARMOR,
    BLESS_ENCHT_WP,
    BLESS_ENCHT_AM,
    COUPON,
    ELIXIR,
    ENCHT_ATTR,
    ENCHT_ATTR_CURSED,
    BOLT,
    ENCHT_ATTR_INC_PROP_ENCHT_WP,
    ENCHT_ATTR_INC_PROP_ENCHT_AM,
    BLESSED_ENCHT_ATTR_INC_PROP_ENCHT_WP,
    BLESSED_ENCHT_ATTR_INC_PROP_ENCHT_AM,
    SOLID_ENCHANT_ARMOR,
    SOLID_ENCHANT_WEAPON,
    RUNE,
    GIANT_ENCHT_ATTR_INC_PROP_ENCHT_WP,
    GIANT_ENCHT_ATTR_INC_PROP_ENCHT_AM,
    ENCHT_ATTR_CRYSTAL_ENCHANT_AM,
    ENCHT_ATTR_CRYSTAL_ENCHANT_WP,
    ENCHT_ATTR_ANCIENT_CRYSTAL_ENCHANT_AM,
    ENCHT_ATTR_ANCIENT_CRYSTAL_ENCHANT_WP,
    ENCHT_ATTR_RUNE,
    ENCHT_ATTRT_RUNE_SELECT,
    TELEPORT_BOOKMARK,
    CHANGE_ATTR,
    SOULSHOT,
    SHAPE_SHIFTING_WP,
    BLESS_SHAPE_SHIFTING_WP,
    // EIT_RESTORE_SHAPE_SHIFTING_WP,
    SHAPE_SHIFTING_WP_FIXED,
    SHAPE_SHIFTING_AM,
    BLESS_SHAPE_SHIFTING_AM,
    SHAPE_SHIFTING_AM_FIXED,
    SHAPE_SHIFTING_HAIRACC,
    BLESS_SHAPE_SHIFTING_HAIRACC,
    SHAPE_SHIFTING_HAIRACC_FIXED,
    RESTORE_SHAPE_SHIFTING_WP,
    RESTORE_SHAPE_SHIFTING_AM,
    RESTORE_SHAPE_SHIFTING_HAIRACC,
    RESTORE_SHAPE_SHIFTING_ALLITEM,
    BLESS_INC_PROP_ENCHT_WP,
    BLESS_INC_PROP_ENCHT_AM,
    CARD_EVENT,
    SHAPE_SHIFTING_ALLITEM_FIXED,
    MULTI_ENCHT_WP,
    MULTI_ENCHT_AM,
    MULTI_INC_PROB_ENCHT_WP,
    MULTI_INC_PROB_ENCHT_AM,
    SOUL_CRYSTAL,
    CARD,
    SEVEN_SINS_AGATHION_ENCHANT,
    AGATHION_ENCHANT,
    IMPROVED_ENCHANT_WEAPON,
    IMPROVED_ENCHANT_ARMOR,
    SIGEL_RUNE,
    COLOR_CHANGE,
    COLOR_TITLE_CHANGE,
    ENSOUL_STONE,
    SEAL_SCROLL,
    UNSEAL_SCROLL,
    TRANSFORMATION_BOOK,
    TRANSFORMATION_BOOK_BOX_RANDOM,
    TRANSFORMATION_BOOK_BOX_RANDOM_RARE,
    TRANSFORMATION_BOOK_BOX_HIGH_GRADE,
    TRANSFORMATION_BOOK_BOX_STANDARD,
    TRANSFORMATION_BOOK_BOX_RARE,
    TRANSFORMATION_BOOK_BOX_LEGENDARY,
    LUCKY_ENCHANT,
    GIANT_ENCHANT,
    BLESSED_LUCKY_ENCHANT;

    /**
     * @return the ID of the item after applying the mask.
     */
    @Override
    public int mask() {
        return 0;
    }
}
