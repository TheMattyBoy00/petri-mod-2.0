package erikalebenjamattias.petrimod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCopperIngot extends Item {

	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return true;
	}
}
