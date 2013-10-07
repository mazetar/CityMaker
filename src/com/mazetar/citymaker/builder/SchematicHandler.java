package com.mazetar.citymaker.builder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.world.World;

import com.tehbeard.forge.schematic.SchVector;
import com.tehbeard.forge.schematic.SchematicFactory;
import com.tehbeard.forge.schematic.SchematicFile;
import com.tehbeard.forge.schematic.product.PasteToWorld;
import com.tehbeard.forge.schematic.worker.IdTranslateWorker;
import com.tehbeard.forge.schematic.worker.WEOffsetWorker;

public class SchematicHandler {
	
	public static Map<String, int[]> SizeMap = new HashMap<String, int[]>();
	private byte sizeOffset = 0;
	public int getSchWidth(String schName) {
		return SizeMap.get(schName)[0] + sizeOffset;
	}
	public int getSchHeight(String schName) {
		return SizeMap.get(schName)[1] + sizeOffset;
	}
	public int getSchLength(String schName) {
		return SizeMap.get(schName)[2] + sizeOffset;
	}
	
	public static void PasteAtNW (String name, World world, int x, int y, int z) {
		LoadSchematic(world, x, y, z, name);
	}
	
	public static void PasteAtNE (String name, World world, int x, int y, int z) {
		LoadSchematic(world, x - SizeMap.get(name)[0] + 1, y, z, name);
	}
	
	public static void LoadSchematic(World world, int x, int y, int z, String name ) {
		LoadSchematicNormal(world, x, y, z, name, 0);
    }
	
    public static void LoadSchematicNormal(World world, int x, int y, int z, String name, int rotation ) {
        boolean rotate = rotation != 0;
        try {
            SchematicFile file = new SchematicFile(new File("./mods/citymaker/" + name + ".schematic"));
            PasteToWorld paste = new PasteToWorld(world);
            paste.setWorldVec(new SchVector((int)Math.floor(x),(int) Math.floor(y),(int) Math.floor(z)));
            SchematicFactory factory = new SchematicFactory().loadWorkers(new WEOffsetWorker(),new IdTranslateWorker());
            
            if(rotate){
                paste.setRotations(rotation);
            }
            
            
            factory.loadSchematic(file).produce(paste);
            
        } catch (IOException e) {
            System.out.println("Could not read file");
            e.printStackTrace(); 
        }
    }
    
    public static void ScanSchematicsForSize() {
    	File folder = new File("./mods/citymaker");
    	File[] files = folder.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".schematic"); // Only check schematic files.
            }});
    	if (files == null) {
    		System.out.println("NO SCHEMATICS FOUND @ " + folder.getAbsolutePath());
    		return;
    	}
    		
    	
    	List<File> fileList = Arrays.asList(files);
    	
    	for (File schmFile : fileList) {
    		try {
	            SchematicFile file = new SchematicFile(schmFile);
	            String name = schmFile.getName().substring(0, schmFile.getName().indexOf("."));
	            int[] sizes = new int[3];
	            sizes[0] = file.getWidth();
	            sizes[1] = file.getHeight();
	            sizes[2] = file.getLength();
	            SizeMap.put(name, sizes);
	            System.out.println(name + " is entered into map with sizes (" + sizes[0] + "," + sizes[1] + "," + sizes[2] + ")" );
	    	} catch (IOException e) {
	            System.out.println("Could not read file");
	            e.printStackTrace(); 
	        }
    	}
    }
    
    public static void LoadSchematicNormal(World world, int x, int y, int z, String name, int rotation, byte flag ) {
        boolean rotate = rotation != 0;
        try {
            SchematicFile file = new SchematicFile(new File("./mods/citymaker/" + name + ".schematic"));
            // grab the size from here:
            int width = file.getWidth();
            int height = file.getHeight();
            int length = file.getLength();
            
            PasteToWorld paste = new PasteToWorld(world);
            paste.setWorldVec(new SchVector((int)Math.floor(x),(int) Math.floor(y),(int) Math.floor(z)));
            SchematicFactory factory = new SchematicFactory().loadWorkers(new WEOffsetWorker(),new IdTranslateWorker());
            
            if(rotate){
                paste.setRotations(rotation);
            }
            
            
            factory.loadSchematic(file).produce(paste);
            
        } catch (IOException e) {
            System.out.println("Could not read file");
            e.printStackTrace(); 
        }
    }
	
}
