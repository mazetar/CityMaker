package com.mazetar.citymaker.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.mazetar.citymaker.CityMaker;
import com.mazetar.citymaker.builder.SchematicHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBuildingSelector extends Item {

	private String defaultBuilding = "v";
	public ItemBuildingSelector(int id) {
		super(id);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	 @Override
	    public void onCreated(ItemStack itemStack, World world,
	            EntityPlayer player) {
	        
	        itemStack.stackTagCompound.setString("builder", player.username);
	        itemStack.stackTagCompound.setString("building", defaultBuilding);
	        itemStack.stackTagCompound.setString("PastePos", "NW");
	        
	    }
	    
	    @Override
	    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
	            World world, int x, int y, int z, int side, float hitX, float hitY,
	            float hitZ) {
	        
	        if (player.isSneaking()) {
	        	player.openGui(CityMaker.instance, 0, world, x, y, z);
	        	
	        	return false;
	        }

        	SchematicHandler.PasteAtNW(stack.stackTagCompound.getString("building"), world, x, y, z);
	        // Open confirm building placement dialog here ..
	        
	        
	        return true;
	    }
	    
	    @Override
	    @SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack itemStack,
	            EntityPlayer p, List list, boolean par4) {
	        if (itemStack.stackTagCompound != null) {
	            String builder = itemStack.stackTagCompound.getString("builder");
	            if (builder.equals(p.username)) {
	                list.add(EnumChatFormatting.GREEN + "Building: " + itemStack.stackTagCompound.getString("building"));
	            }
	            else
	            {
	                list.add(EnumChatFormatting.RED + "Belongs to Buildmaster " + builder);
	                
	            }
	            
	            
	        }
	    }
	
	
	
}
