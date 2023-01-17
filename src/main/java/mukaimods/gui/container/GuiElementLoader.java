package mukaimods.gui.container;

import mukaimods.Mukaimods;
import mukaimods.czm.gui.GuiCZM1;
import mukaimods.gui.GuiContainerDemo;
import mukaimods.gui.GuiCopyMusic;
import mukaimods.gui.GuiMakeMusic;
import mukaimods.gui.GuiNumberGame;
import mukaimods.gui.GuiPlayMusic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler
{
    public static final int GUI_DEMO = 1;
    public static final int Copy_Music = 2;
    public static final int czm1 = 114514;
    public static final int Play_Music = 3;
    public static final int Make_Music = 4;
    public static final int Number_Game = 5;
    public GuiElementLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Mukaimods.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	switch (ID)
        {
        case GUI_DEMO:
            return new ContainerDemo(player);
        case Play_Music:
            return new ContainerPlayMusic(world, player, world.getTileEntity(new BlockPos(x,y,z)));  
        case Copy_Music:
            return new CopyMusic(world, player,  new BlockPos(x,y,z));
        case Make_Music:
            return new ConitainerMakeMusic(world, player, world.getTileEntity(new BlockPos(x,y,z)));  
        case Number_Game:
            return new ContainerNumberGame(world, player, world.getTileEntity(new BlockPos(x,y,z)));  
        case czm1:
            return new mukaimods.czm.gui.ContainerCZM1(player);
        default:
            return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	switch (ID)
        {
        case GUI_DEMO:
            return new GuiContainerDemo(new ContainerDemo(player));
        case Play_Music:
            return new GuiPlayMusic(new ContainerPlayMusic(world, player,world.getTileEntity(new BlockPos(x,y,z))),world.getTileEntity(new BlockPos(x,y,z)));
        case Copy_Music:
            return new GuiCopyMusic(new CopyMusic(world, player, new BlockPos(x,y,z)),new BlockPos(x,y,z));
        case Make_Music:
            return new GuiMakeMusic(new ConitainerMakeMusic(world, player,world.getTileEntity(new BlockPos(x,y,z))),world.getTileEntity(new BlockPos(x,y,z)));
        case Number_Game:
            return new GuiNumberGame(new ContainerNumberGame(world, player,world.getTileEntity(new BlockPos(x,y,z))),world.getTileEntity(new BlockPos(x,y,z)));
        case czm1:
            return new GuiCZM1(new mukaimods.czm.gui.ContainerCZM1(player));
        default:
            return null;
        }
    }
    
    
    }
