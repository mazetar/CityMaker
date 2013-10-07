package com.mazetar.citymaker.item;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item;

public class ModItems {
	
	public static Item areaSelector;
	
	public static void preInitItems(FMLPreInitializationEvent event) {
		
		
	}
	
	
	public static void initItems() {
		areaSelector = new ItemBuildingSelector(5432);
	}
	
}
