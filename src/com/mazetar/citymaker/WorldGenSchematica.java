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
			
			
			
		}
		
	}
	
	
}
