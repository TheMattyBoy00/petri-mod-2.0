package erikalebenjamattias.petrimod.main;

import erikalebenjamattias.petrimod.init.PetriBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PetriTab extends CreativeTabs {

	public PetriTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(PetriBlocks.copper_ore);
	}
}