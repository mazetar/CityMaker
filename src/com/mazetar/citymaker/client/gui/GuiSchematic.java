package com.mazetar.citymaker.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiSchematic extends GuiScreen {
	ItemStack selectorItem;
	
	public GuiSchematic() {
		selectorItem = this.mc.thePlayer.getHeldItem();
	}
	
	@Override
	public void drawBackground(int par1) {
		super.drawBackground(par1);
		this.drawDefaultBackground();
		
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		// render, txt, x, y, color
		this.drawCenteredString(fontRenderer, "Building Selector", this.width / 2, 30, 0xFFFFFF);
		
		
		
		
	}
}
