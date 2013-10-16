package com.mazetar.citymaker;

import java.util.Random;

import com.mazetar.citymaker.builder.SchematicHandler;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenSchematica implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == 0)
		{
			
			if (chunkX == 1)
				if (chunkZ == 1){
					int x = chunkX * 16 + 10;
					int z = chunkZ * 16 + 10;
					int y = world.getTopSolidOrLiquidBlock(x, z);
					CityGenerator.generateCity(world, x, y, z);
					System.out.println("=======>>  CITY GEN AT (" + x + "," + y + "," + z + ") <<========");
				}
				
			
		}
		
	}
	
	
}
