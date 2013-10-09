package com.tehbeard.forge.schematic.shell.commands;

import java.io.File;
import java.io.IOException;

import com.tehbeard.forge.schematic.SchVector;
import com.tehbeard.forge.schematic.SchematicFactory;
import com.tehbeard.forge.schematic.SchematicFile;
import com.tehbeard.forge.schematic.product.PasteToWorld;
import com.tehbeard.forge.schematic.shell.commands.BCommand.PermLevel;
import com.tehbeard.forge.schematic.worker.IdTranslateWorker;
import com.tehbeard.forge.schematic.worker.WEOffsetWorker;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

@BCommand(command="loadsch",level=PermLevel.none,usage="/loadsch filename")
public class LoadCommand extends PlayerCommand {

    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring) {
        
EntityPlayerMP player = getCommandSenderAsPlayer(icommandsender);
        
        try {
            SchematicFile file = new SchematicFile(new File("./mods/citymaker/" + astring[0] + ".schematic"));
            PasteToWorld paste = new PasteToWorld(player.worldObj);
            paste.setWorldVec(new SchVector((int)Math.floor(player.posX),(int) Math.floor(player.posY),(int) Math.floor(player.posZ)));
            SchematicFactory factory = new SchematicFactory().loadWorkers(new WEOffsetWorker(),new IdTranslateWorker());
            
            ArgumentPack arguments = new ArgumentPack(new String[]{}, new String[]{"rotate"}, astring);
            int i;
            boolean rotate = false;
            for (i = 0; i < astring.length; i++) {
               String string = astring[i];
               if (string.contains("rotate"))
               {
                   rotate = true;
                   break;
               }
           }
            
            if(rotate){
                paste.setRotations(Integer.parseInt(astring[i+1]));
            }
            
            
            factory.loadSchematic(file).produce(paste);
            
        } catch (IOException e) {
            player.addChatMessage("Could not read file");
            e.printStackTrace();
        }
    }

}
