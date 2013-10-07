package com.mazetar.citymaker.core.proxy;

import com.mazetar.citymaker.item.ModItems;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {


	public void preInit(FMLPreInitializationEvent event) {
		
	}

	public void init(FMLInitializationEvent event) {
		ModItems.initItems();
	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
