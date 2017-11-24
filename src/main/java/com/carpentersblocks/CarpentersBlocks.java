package com.carpentersblocks;

import com.carpentersblocks.proxy.CommonProxy;
import com.carpentersblocks.util.CarpentersBlocksTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(
        modid = CarpentersBlocks.MOD_ID,
        name = "Carpenter's Blocks",
        version = "@VERSION@",
        dependencies = "required-after:forge@[14.23.0.2491,)"
        )
public class CarpentersBlocks {
	
    public static final String MOD_ID = "carpentersblocks";
    public static final CreativeTabs CREATIVE_TAB = new CarpentersBlocksTab(MOD_ID);
    public static FMLEventChannel channel;
    
    @SidedProxy(clientSide = "com.carpentersblocks.proxy.ClientProxy", serverSide = "com.carpentersblocks.proxy.CommonProxy")
    public static CommonProxy proxy;
        
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(MOD_ID);
        proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init(event);
    }
    
}
