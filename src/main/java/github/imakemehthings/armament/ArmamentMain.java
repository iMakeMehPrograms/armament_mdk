package github.imakemehthings.armament;

import com.tterrag.registrate.Registrate;
import github.imakemehthings.armament.init.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(ArmamentMain.MOD_ID)
public class ArmamentMain {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "armament";

    public static final Registrate REGISTRATE = Registrate.create(MOD_ID);

    public ArmamentMain() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}