package charten.shield.util;

import charten.shield.Main;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> TEST = createTag("test");
        TagKey<Item> ALCOHOL_BOTTLE = createTag("alcohol_bottle");
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Main.MOD_ID, name));
        }
    }
}