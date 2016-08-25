
package net.glider.src.gui;

import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.tiles.TileEntityDockingPort;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int BUILDERGUI = 0;
	public static final int REMOVERGUI = 1;
	public static final int DOCKINGPORTGUI = 2;
	public static final int FUELLOADERGUI = 3;
	public static final int ARMORSTANDGUI = 4;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		
		switch (ID)
		{
		case BUILDERGUI:
			return new ContainerBuilder(player.inventory);
		case REMOVERGUI:
			return new ContainerRemover(player.inventory, (TileEntityRemoveInfo) te);
		case DOCKINGPORTGUI:
			if (te != null)
			{
				return new ContainerDockingPort(player.inventory, (TileEntityDockingPort) te);
			}
		case ARMORSTANDGUI:
			if (te != null)
			{
				return new ContainerArmorStand(player, (TileEntityArmorStand) te);
			}
		}
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		
		switch (ID)
		{
		case BUILDERGUI:
			return new GuiBuilder(player);
		case REMOVERGUI:
			return new GuiRemover(player, (TileEntityRemoveInfo) te);
		case DOCKINGPORTGUI:
			if (te != null)
			{
				return new GuiDockingPort(player.inventory, (TileEntityDockingPort) te);
			}
		case ARMORSTANDGUI:
			if (te != null)
			{
				return new GuiArmorStand(player, (TileEntityArmorStand) te);
			}
		}
		
		return null;
	}
}