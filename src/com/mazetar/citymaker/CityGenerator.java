package com.mazetar.citymaker;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import com.mazetar.citymaker.util.Vector;


public class CityGenerator {

	public static List<int[]> CityBorders = new ArrayList<int[]>();
	
	
	public static void generateCity(World w, int x, int y, int z) {
		
		int width = 100;
		int lenght = 100;
		
		Vector Center = new Vector(x, y, z);
		Vector NW = new Vector(x - width/2, y, z - lenght/2);
		Vector NE = new Vector(x + width/2, y, z - lenght/2);
		Vector SW = new Vector(x - width/2, y, z + lenght/2);
		Vector SE = new Vector(x + width/2, y, z + lenght/2);
		

		AddToBordersList(w, NW, SE);
		prepCityFloor(w, y, width, lenght, NW, NE, SE);
				
		
		generatePillar(w, NW, 50, Block.blockIron.blockID);
		generatePillar(w, NE, 50, Block.blockIron.blockID);
		generatePillar(w, SE, 50, Block.blockIron.blockID);
		generatePillar(w, SW, 50, Block.blockIron.blockID);
		
		// Decide Gate numbers & MainGate location
		int numGates = w.rand.nextInt(4) + 1;
		Vector mainGateLoc = new Vector(Center.X, Center.Y, SE.Z);
		
//		// 50/50 for maingate being north or south?
//		if (w.rand.nextBoolean())
//			mainGateLoc.Z = NE.Z;
		
		// Street Network
		int sideStreet = 3;
		int mainStreet = 5;
		int roadBlockID = Block.stoneBrick.blockID;
		
		for(int i = mainGateLoc.Z; i > NW.Z; i--) {
			for(int j = mainGateLoc.X - ((mainStreet - 1) /2); j < mainGateLoc.X + ((mainStreet - 1) /2);j++) {
				w.setBlock(j, Center.Y, i, roadBlockID);
			}
		}
		int cityLenght = NW.Z - SW.Z;
		int cityWidth = NW.X - NE.X;
		// generate vertical street
		
		
		
		
		
		
		
	}

	private static void prepCityFloor(World w, int y, int width, int lenght,
			Vector NW, Vector NE, Vector SE) {
		// Fill inn area below city.
		int tempBlockID = Block.dirt.blockID;
		for (int i = NW.X; i < NE.X+1; i++)
			for (int j = NW.Z; j < SE.Z+1; j++) {
				int top = w.getTopSolidOrLiquidBlock(i, j);
				if (top < y) {
					createPillar(w, i, top, j, y - top, tempBlockID);
				}
			}
					
		// Create the grass floor
		// Then Remove everything above the ground floor.
		tempBlockID = Block.grass.blockID;
		for (int i = 0; i < width+1; i++)
			for (int j = 0; j < lenght+1; j++) {
				w.setBlock(NW.X + i, y, NW.Z + j, tempBlockID);
			}
		for (int k = y+1; k < w.getActualHeight(); k++)
			for (int i = 0; i < width+1; i++)
				for (int j = 0; j < lenght+1; j++) {
					w.setBlock(NW.X + i, k, NW.Z + j, 0);
				}
	}

	private static void AddToBordersList(World w, Vector NW, Vector SE) {
		Chunk c = w.getChunkFromBlockCoords(NW.X, NW.Z);
		Chunk c2 = w.getChunkFromBlockCoords(SE.X, SE.Z);
		int x1 = c.xPosition;
		int z1 = c.zPosition;
		int x2 = c2.xPosition;
		int z2 = c2.zPosition;
		int minX = Math.min(x1, x2);
		int maxX = Math.max(x1, x2);
		int minZ = Math.min(z1, z2);
		int maxZ = Math.max(z1, z2);
		CityBorders.add(new int[]{minX, maxX, minZ, maxZ});
	}
	
	private static void createPillar(World w, int x, int y, int z, int height,
			int blockID) {
		for (int i = 0; i < height+1; i++)
			w.setBlock(x, y+i, z, blockID);
		
	}

	private static void generatePillar(World w, Vector loc, int height, int blockID) {
		createPillar(w, loc.X, loc.Y, loc.Z, height, blockID);
	}

}

