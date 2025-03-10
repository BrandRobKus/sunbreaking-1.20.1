package com.brandrobkus.sunbreaking.sound;
import com.brandrobkus.sunbreaking.Sunbreaking;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent HAMMER_THROW = registerSoundEvent("hammer_throw");
    public static final SoundEvent HAMMER_HIT = registerSoundEvent("hammer_hit");
    public static final SoundEvent HAMMER_RETURN = registerSoundEvent("hammer_return");
    public static final SoundEvent SHADOWSHOT_NODE = registerSoundEvent("shadowshot_node");
    public static final SoundEvent COOLDOWN_END = registerSoundEvent("cooldown_end");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Sunbreaking.MOD_ID, name);
        SoundEvent soundEvent = SoundEvent.of(id);
        Registry.register(Registries.SOUND_EVENT, id, soundEvent);
        Sunbreaking.LOGGER.info("Registered sound: " + id); // Log the sound registration
        return soundEvent;
    }



    public static void registerSounds() {
        Sunbreaking.LOGGER.info("Registering Sounds for " + Sunbreaking.MOD_ID);
    }
}

