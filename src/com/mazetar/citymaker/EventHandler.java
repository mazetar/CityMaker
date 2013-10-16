package com.mazetar.citymaker;

import java.util.Iterator;

import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;

public class EventHandler {
	
	@ForgeSubscribe
	public void Populate (Populate event) {
		int x = event.chunkX;
		int z = event.chunkZ;
		if (!checkChunk(x, z))
				event.setResult(Result.DENY);
	}
	
	@ForgeSubscribe
	public void chunkPreDecorate(DecorateBiomeEvent event) {
		int x = event.chunkX;
		int z = event.chunkZ;
		if (!checkChunk(x, z))
				event.setResult(Result.DENY);
		
	}
	
	public void chunkPopulate (PopulateChunkEvent event) {
		int x = event.chunkX;
		int z = event.chunkZ;
		if (!checkChunk(x, z))
				event.setResult(Result.DENY);
	}
	
	static boolean checkChunk(int x, int z) {
		for (Iterator iterator = CityGenerator.CityBorders.iterator(); iterator.hasNext();) {
			int[] borders = (int[]) iterator.next();
			if (borders[0] <= x && borders[1] >= x)
				if (borders[2] <= z && borders[3] >= z)
					return false;
		}
		return true;
	}
	

}
