package com.mazetar.citymaker;

import java.lang.ref.Reference;

import com.mazetar.citymaker.core.proxy.CommonProxy;
import com.mazetar.citymaker.lib.ModRef;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModRef.ID, name = ModRef.NAME, version = ModRef.VERSION)
@NetworkMod(channels = { ModRef.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false) //, packetHandler = PacketHandler.class)
public class CityMaker {
	    
	    @Instance(ModRef.ID)
	    public static CityMaker instance;

	    @SidedProxy(clientSide = ModRef.CLIENT_PROXY_CLASS, serverSide = ModRef.SERVER_PROXY_CLASS)
	    public static CommonProxy proxy;

	    @EventHandler
	    public void preInit(FMLPreInitializationEvent event){
	    	proxy.preInit(event);
	    }
	    
	    @EventHandler
	    public void load(FMLInitializationEvent event){
	        proxy.init(event);
	        
	    }
	    
	    
	    
	    @EventHandler
	    public void postInit(FMLPostInitializationEvent event){
	    	proxy.postInit(event);
	    }

}
