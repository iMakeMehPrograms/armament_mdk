package github.imakemehthings.armament.init;

import github.imakemehthings.armament.ArmamentMain;
import github.imakemehthings.armament.items.Handgun;
import github.imakemehthings.armament.items.cartridges.PistolCartridge;
import github.imakemehthings.armament.util.FirearmExternalItemProperties;
import github.imakemehthings.armament.util.ModCreativeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArmamentMain.MOD_ID);

    public static final RegistryObject<PistolCartridge> PISTOL_C = ITEMS.register("pistol_cartridge",
            () -> new PistolCartridge(new Item.Properties().tab(ModCreativeTab.mainTab)));

    public static final RegistryObject<Handgun> HANDGUN = ITEMS.register("handgun",
            () -> new Handgun(new Item.Properties()
                    .tab(ModCreativeTab.mainTab)
                    .durability(Handgun.externalProperties.getDurab()) // fucking damnit
                    .defaultDurability(Handgun.externalProperties.getDurab())));
}
