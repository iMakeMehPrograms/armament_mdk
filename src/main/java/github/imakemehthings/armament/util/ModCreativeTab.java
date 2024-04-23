package github.imakemehthings.armament.util;

import github.imakemehthings.armament.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTab extends CreativeModeTab {
    public static final ModCreativeTab mainTab = new ModCreativeTab(CreativeModeTab.TABS.length, "armament");
    private ModCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.PISTOL_C.get());
    }
}