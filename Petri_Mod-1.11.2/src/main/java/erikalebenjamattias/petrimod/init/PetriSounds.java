package erikalebenjamattias.petrimod.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import erikalebenjamattias.petrimod.main.Reference;

public class PetriSounds {

	public static SoundEvent ENTIY_GRAND_PIANO_A0;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C1;
	public static SoundEvent ENTIY_GRAND_PIANO_Dsharp1;
	public static SoundEvent ENTIY_GRAND_PIANO_Fsharp1;
	public static SoundEvent ENTIY_GRAND_PIANO_A1;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C2;
	public static SoundEvent ENTIY_GRAND_PIANO_Dsharp2;
	public static SoundEvent ENTIY_GRAND_PIANO_Fsharp2;
	public static SoundEvent ENTIY_GRAND_PIANO_A2;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C3;
	public static SoundEvent ENTIY_GRAND_PIANO_Dsharp3;
	public static SoundEvent ENTIY_GRAND_PIANO_Fsharp3;
	public static SoundEvent ENTIY_GRAND_PIANO_A3;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C4;
	public static SoundEvent ENTIY_GRAND_PIANO_Dsharp4;
	public static SoundEvent ENTIY_GRAND_PIANO_Fsharp4;
	public static SoundEvent ENTIY_GRAND_PIANO_A4;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C5;
	public static SoundEvent ENTIY_GRAND_PIANO_Dsharp5;
	public static SoundEvent ENTIY_GRAND_PIANO_Fsharp5;
	public static SoundEvent ENTIY_GRAND_PIANO_A5;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C6;
	public static SoundEvent ENTIY_GRAND_PIANO_Dsharp6;
	public static SoundEvent ENTIY_GRAND_PIANO_Fsharp6;
	public static SoundEvent ENTIY_GRAND_PIANO_A6;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C7;
	public static SoundEvent ENTIY_GRAND_PIANO_Dsharp7;
	public static SoundEvent ENTIY_GRAND_PIANO_Fsharp7;
	public static SoundEvent ENTIY_GRAND_PIANO_A7;
	
	public static SoundEvent ENTIY_GRAND_PIANO_C8;
	
	public static void init() {
		final ResourceLocation entity_grand_piano_a0 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a0");
		
		final ResourceLocation entity_grand_piano_c1 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c1");
		final ResourceLocation entity_grand_piano_dsharp1 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.dsharp1");
		final ResourceLocation entity_grand_piano_fsharp1 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.fsharp1");
		final ResourceLocation entity_grand_piano_a1 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a1");
		
		final ResourceLocation entity_grand_piano_c2 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c2");
		final ResourceLocation entity_grand_piano_dsharp2 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.dsharp2");
		final ResourceLocation entity_grand_piano_fsharp2 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.fsharp2");
		final ResourceLocation entity_grand_piano_a2 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a2");
		
		final ResourceLocation entity_grand_piano_c3 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c3");
		final ResourceLocation entity_grand_piano_dsharp3 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.dsharp3");
		final ResourceLocation entity_grand_piano_fsharp3 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.fsharp3");
		final ResourceLocation entity_grand_piano_a3 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a3");
		
		final ResourceLocation entity_grand_piano_c4 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c4");
		final ResourceLocation entity_grand_piano_dsharp4 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.dsharp4");
		final ResourceLocation entity_grand_piano_fsharp4 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.fsharp4");
		final ResourceLocation entity_grand_piano_a4 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a4");
		
		final ResourceLocation entity_grand_piano_c5 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c5");
		final ResourceLocation entity_grand_piano_dsharp5 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.dsharp5");
		final ResourceLocation entity_grand_piano_fsharp5 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.fsharp5");
		final ResourceLocation entity_grand_piano_a5 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a5");
		
		final ResourceLocation entity_grand_piano_c6 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c6");
		final ResourceLocation entity_grand_piano_dsharp6 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.dsharp6");
		final ResourceLocation entity_grand_piano_fsharp6 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.fsharp6");
		final ResourceLocation entity_grand_piano_a6 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a6");
		
		final ResourceLocation entity_grand_piano_c7 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c7");
		final ResourceLocation entity_grand_piano_dsharp7 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.dsharp7");
		final ResourceLocation entity_grand_piano_fsharp7 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.fsharp7");
		final ResourceLocation entity_grand_piano_a7 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.a7");
		
		final ResourceLocation entity_grand_piano_c8 = new ResourceLocation(Reference.MOD_ID, "entity.grand_piano.c8");
		
		ENTIY_GRAND_PIANO_A0 = GameRegistry.register(new SoundEvent(entity_grand_piano_a0).setRegistryName(entity_grand_piano_a0));
		
		ENTIY_GRAND_PIANO_C1 = GameRegistry.register(new SoundEvent(entity_grand_piano_c1).setRegistryName(entity_grand_piano_c1));
		ENTIY_GRAND_PIANO_Dsharp1 = GameRegistry.register(new SoundEvent(entity_grand_piano_dsharp1).setRegistryName(entity_grand_piano_dsharp1));
		ENTIY_GRAND_PIANO_Fsharp1 = GameRegistry.register(new SoundEvent(entity_grand_piano_fsharp1).setRegistryName(entity_grand_piano_fsharp1));
		ENTIY_GRAND_PIANO_A1 = GameRegistry.register(new SoundEvent(entity_grand_piano_a1).setRegistryName(entity_grand_piano_a1));
		
		ENTIY_GRAND_PIANO_C2 = GameRegistry.register(new SoundEvent(entity_grand_piano_c2).setRegistryName(entity_grand_piano_c2));
		ENTIY_GRAND_PIANO_Dsharp2 = GameRegistry.register(new SoundEvent(entity_grand_piano_dsharp2).setRegistryName(entity_grand_piano_dsharp2));
		ENTIY_GRAND_PIANO_Fsharp2 = GameRegistry.register(new SoundEvent(entity_grand_piano_fsharp2).setRegistryName(entity_grand_piano_fsharp2));
		ENTIY_GRAND_PIANO_A2 = GameRegistry.register(new SoundEvent(entity_grand_piano_a2).setRegistryName(entity_grand_piano_a2));
		
		ENTIY_GRAND_PIANO_C3 = GameRegistry.register(new SoundEvent(entity_grand_piano_c3).setRegistryName(entity_grand_piano_c3));
		ENTIY_GRAND_PIANO_Dsharp3 = GameRegistry.register(new SoundEvent(entity_grand_piano_dsharp3).setRegistryName(entity_grand_piano_dsharp3));
		ENTIY_GRAND_PIANO_Fsharp3 = GameRegistry.register(new SoundEvent(entity_grand_piano_fsharp3).setRegistryName(entity_grand_piano_fsharp3));
		ENTIY_GRAND_PIANO_A3 = GameRegistry.register(new SoundEvent(entity_grand_piano_a3).setRegistryName(entity_grand_piano_a3));
		
		ENTIY_GRAND_PIANO_C4 = GameRegistry.register(new SoundEvent(entity_grand_piano_c4).setRegistryName(entity_grand_piano_c4));
		ENTIY_GRAND_PIANO_Dsharp4 = GameRegistry.register(new SoundEvent(entity_grand_piano_dsharp4).setRegistryName(entity_grand_piano_dsharp4));
		ENTIY_GRAND_PIANO_Fsharp4 = GameRegistry.register(new SoundEvent(entity_grand_piano_fsharp4).setRegistryName(entity_grand_piano_fsharp4));
		ENTIY_GRAND_PIANO_A4 = GameRegistry.register(new SoundEvent(entity_grand_piano_a4).setRegistryName(entity_grand_piano_a4));
		
		ENTIY_GRAND_PIANO_C5 = GameRegistry.register(new SoundEvent(entity_grand_piano_c5).setRegistryName(entity_grand_piano_c5));
		ENTIY_GRAND_PIANO_Dsharp5 = GameRegistry.register(new SoundEvent(entity_grand_piano_dsharp5).setRegistryName(entity_grand_piano_dsharp5));
		ENTIY_GRAND_PIANO_Fsharp5 = GameRegistry.register(new SoundEvent(entity_grand_piano_fsharp5).setRegistryName(entity_grand_piano_fsharp5));
		ENTIY_GRAND_PIANO_A5 = GameRegistry.register(new SoundEvent(entity_grand_piano_a5).setRegistryName(entity_grand_piano_a5));
		
		ENTIY_GRAND_PIANO_C6 = GameRegistry.register(new SoundEvent(entity_grand_piano_c6).setRegistryName(entity_grand_piano_c6));
		ENTIY_GRAND_PIANO_Dsharp6 = GameRegistry.register(new SoundEvent(entity_grand_piano_dsharp6).setRegistryName(entity_grand_piano_dsharp6));
		ENTIY_GRAND_PIANO_Fsharp6 = GameRegistry.register(new SoundEvent(entity_grand_piano_fsharp6).setRegistryName(entity_grand_piano_fsharp6));
		ENTIY_GRAND_PIANO_A6 = GameRegistry.register(new SoundEvent(entity_grand_piano_a6).setRegistryName(entity_grand_piano_a6));
		
		ENTIY_GRAND_PIANO_C7 = GameRegistry.register(new SoundEvent(entity_grand_piano_c7).setRegistryName(entity_grand_piano_c7));
		ENTIY_GRAND_PIANO_Dsharp7 = GameRegistry.register(new SoundEvent(entity_grand_piano_dsharp7).setRegistryName(entity_grand_piano_dsharp7));
		ENTIY_GRAND_PIANO_Fsharp7 = GameRegistry.register(new SoundEvent(entity_grand_piano_fsharp7).setRegistryName(entity_grand_piano_fsharp7));
		ENTIY_GRAND_PIANO_A7 = GameRegistry.register(new SoundEvent(entity_grand_piano_a7).setRegistryName(entity_grand_piano_a7));
		
		ENTIY_GRAND_PIANO_C8 = GameRegistry.register(new SoundEvent(entity_grand_piano_c8).setRegistryName(entity_grand_piano_c8));
	}
}
