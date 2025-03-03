package com.brandrobkus.sunbreaking.util;

import com.brandrobkus.sunbreaking.Sunbreaking;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Items {
        public static final TagKey<Item> HAMMER =
                createTag("hammer_of_sol");
        public static final TagKey<Item> ARROWS =
                createTag("arrows");
        public static final TagKey<Item> GLAIVES =
                createTag("glaives");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Sunbreaking.MOD_ID, name));
        }

    }
}



