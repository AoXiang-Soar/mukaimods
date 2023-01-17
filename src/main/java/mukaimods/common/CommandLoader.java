package mukaimods.common;

import mukaimods.player.CapabilityLoader;
import mukaimods.player.IPTTCapability;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandLoader extends CommandBase {

	public CommandLoader(FMLServerStartingEvent event) {
		event.registerServerCommand(this);
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}

	@Override
	public String getName() {
		return "ArcaeaPttSet";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/ArcaeaPttSet - Set your Potential";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (sender.getCommandSenderEntity() != null && args.length == 1) {
			EntityPlayerMP mp = CommandBase.getCommandSenderAsPlayer(sender);
			try {
				Capability<IPTTCapability> capability = CapabilityLoader.PTT;
				if (mp.hasCapability(capability, null)) {
					IPTTCapability ptts = mp.getCapability(CapabilityLoader.PTT, null);
					ptts.updatePtt(new Double(args[0]));
				}
			} catch (Exception e) {
				throw new WrongUsageException("commands.pttset.usage");
			}
		}
	}
}