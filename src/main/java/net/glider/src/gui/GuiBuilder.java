package net.glider.src.gui;

import java.util.HashMap;
import java.util.Map;

import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.BuildPacket;
import net.glider.src.strucures.Structure;
import net.glider.src.strucures.StructureBigHall;
import net.glider.src.strucures.StructureCornerHall;
import net.glider.src.strucures.StructureCrossroad;
import net.glider.src.strucures.StructureCupola;
import net.glider.src.strucures.StructureDockingPort;
import net.glider.src.strucures.StructureGreenHouse;
import net.glider.src.strucures.StructureHall;
import net.glider.src.strucures.StructureHallWAirlock;
import net.glider.src.strucures.StructurePierce;
import net.glider.src.strucures.StructureRotatable;
import net.glider.src.strucures.StructureSolarPanel;
import net.glider.src.strucures.StructureThall;
import net.glider.src.strucures.StructureWindow;
import net.glider.src.utils.GLoger;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class GuiBuilder extends GuiModular {

	private InventoryPlayer inventory;

	public static Map strucutures = new HashMap<Integer, Structure>();

	private ResourceLocation texture = new ResourceLocation(GliderModInfo.ModTestures, "textures/Builder.png");
	public int Xsize = 224;
	public int Ysize = 160;
	private int Wx;
	private int Wy;
	private int Wz;
	private ForgeDirection Ndir;
	private int Bid;
	public int STRlastid = -1;
	public int rot = 0;
	public static Map AstG = new HashMap<String, Integer>();
	public Map Ast = new HashMap<Integer, Structure>();
	public GuiBuilderSide sideinv;
	public int meta;

	private boolean nullMOP = false;

	protected MovingObjectPosition getMovingObjectPositionFromPlayer(World p_77621_1_, EntityPlayer p_77621_2_, boolean p_77621_3_)
	{
		float f = 1.0F;
		float f1 = p_77621_2_.prevRotationPitch + (p_77621_2_.rotationPitch - p_77621_2_.prevRotationPitch) * f;
		float f2 = p_77621_2_.prevRotationYaw + (p_77621_2_.rotationYaw - p_77621_2_.prevRotationYaw) * f;
		double d0 = p_77621_2_.prevPosX + (p_77621_2_.posX - p_77621_2_.prevPosX) * (double) f;
		double d1 = p_77621_2_.prevPosY + (p_77621_2_.posY - p_77621_2_.prevPosY) * (double) f + (double) (p_77621_1_.isRemote ? p_77621_2_.getEyeHeight() - p_77621_2_.getDefaultEyeHeight() : p_77621_2_.getEyeHeight()); // isRemote
		// //
		// differences
		double d2 = p_77621_2_.prevPosZ + (p_77621_2_.posZ - p_77621_2_.prevPosZ) * (double) f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		if (p_77621_2_ instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) p_77621_2_).theItemInWorldManager.getBlockReachDistance();
		}
		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		return p_77621_1_.func_147447_a(vec3, vec31, p_77621_3_, !p_77621_3_, false);
	}

	public GuiBuilder(EntityPlayer player)
	{
		super(new ContainerBuilder(player.inventory));
		try
		{
			MovingObjectPosition mop = getMovingObjectPositionFromPlayer((World) Minecraft.getMinecraft().theWorld, player, false);

			if (mop == null)
				nullMOP = true;
			else
			{
				Wx = mop.blockX;
				Wy = mop.blockY;
				Wz = mop.blockZ;

				meta = player.worldObj.getBlockMetadata(Wx, Wy, Wz);
				Ndir = ForgeDirection.getOrientation(mop.sideHit).getOpposite();
				if (Ndir == ForgeDirection.EAST)
				{
					Wx = Wx - 1;
				} else if (Ndir == ForgeDirection.WEST)
				{
					Wx = Wx + 1;
				} else if (Ndir == ForgeDirection.SOUTH)
				{
					Wz = Wz - 1;
				} else if (Ndir == ForgeDirection.NORTH)
				{
					Wz = Wz + 1;
				}
				inventory = player.inventory;
			}
		}
		catch (Throwable error)
		{
			GLoger.logWarn("An error ocured in GuiBuilder(constructor)");
			error.printStackTrace();
		}

		sideinv = new GuiBuilderSide(this, this.inventorySlots, true, false);
		this.modules.add(sideinv);

	}

	/*
	 * 0 - everything, 1 - everything excluding pierce, 2 - only add
	 * structures, 3 - only window(only rot == 0), 4 - solar panels, 5 -
	 * greenhouse, 6 - pierce
	 */

	public static void init()
	{
		RegisterStructure(0, new StructureHall(false));
		RegisterStructure(1, new StructureCornerHall(false), 0);
		RegisterStructure(2, new StructureCrossroad(false));
		RegisterStructure(3, new StructureHallWAirlock(false));
		RegisterStructure(4, new StructureWindow(false), 0);
		RegisterStructure(5, new StructureCupola(false));
		RegisterStructure(6, new StructureDockingPort(false));
		RegisterStructure(7, new StructureSolarPanel(false));
		RegisterStructure(8, new StructureThall(false), 0);
		RegisterStructure(9, new StructureBigHall(false), 0);
		RegisterStructure(10, new StructureGreenHouse());
		RegisterStructure(11, new StructurePierce());
	}

	public static void RegisterStructure(int id, Structure strc)
	{
		strucutures.put(id, strc);
	}

	public static void RegisterStructure(int id, StructureRotatable strc, int rot)
	{
		strc.setRotation(rot);
		strucutures.put(id, strc);
	}

	private int getLines(int size)
	{
		if (size < 3)
		{
			return 0;
		} else if (size > 2 && size < 6)
		{
			return 1;
		} else if (size > 5)
		{
			return 2;
		}
		return 0;
	}

	@Override
	public void initGui()
	{
		try
		{
			AstG.clear();
			Ast.clear();
			boolean disp;

			if (nullMOP)
				Minecraft.getMinecraft().displayGuiScreen(null);

			if (!nullMOP)
			{
				int x = (width - Xsize - 40) / 2;
				int y = (height - Ysize) / 2;
				this.cornerX = x;
				this.cornerY = y;

				for (int a = 0; a < strucutures.size(); a++)
				{
					World world = (World) Minecraft.getMinecraft().theWorld;
					if (((Structure) strucutures.get(a)).Check(world, Ndir, Wx, Wy, Wz, meta))
					{
						if (((Structure) strucutures.get(a)).Check(world, Ndir, Wx, Wy, Wy, meta))
						{
							Ast.put(Ast.size(), ((Structure) strucutures.get(a)));
						}

					}
				}
				int xm = 0;
				int ym = 0;

				for (int i = 0; i < Ast.size(); i++)
				{
					ym = getLines(i);
					xm = i - (ym * 3);
					// GLoger.logInfo(xm+" "+ym+" "+i);
					buttonList.add(new GuiButtonBuilder(i, x + 12 + ((xm) * 68), y + 22 + (ym * 38), ((Structure) Ast.get(i)).getName()));
					((GuiButtonBuilder) buttonList.get(i)).setDirection(Ndir);

					if (((Structure) Ast.get(i)) instanceof StructureRotatable)
					{
						((GuiButtonBuilder) buttonList.get(i)).setRotation(((StructureRotatable) Ast.get(i)).nextPossibleValue(-1, Ndir, meta));
					}
					AstG.put(((Structure) Ast.get(i)).getUnlocalizedName(), i);
					Bid++;
				}
				if (fontRendererObj.getStringWidth(StatCollector.translateToLocal("builder.build_button.name")) + 6 < 40)
				{
					buttonList.add(new GuiButton(Bid, x + 159, y + 135, 40, 20, StatCollector.translateToLocal("builder.build_button.name")));
				} else
				{
					buttonList.add(new GuiButton(Bid, x + 159, y + 135, fontRendererObj.getStringWidth(StatCollector.translateToLocal("builder.build_button.name")) + 6, 20, StatCollector.translateToLocal("builder.build_button.name")));
				}

				super.initGui();
			}
		}
		catch (Throwable error)
		{
			GLoger.logWarn("An error ocured in GuiBuilder(constructor)", error);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);

		if (mc.thePlayer.inventory.getItemStack() == null && sideinv.theSlot != null && sideinv.theSlot.slotNumber < sideinv.shownItems.length && sideinv.shownItems[sideinv.theSlot.slotNumber] != null)
		{
			this.renderToolTip(sideinv.shownItems[sideinv.theSlot.slotNumber], mouseX, mouseY);
			sideinv.theSlot = null;
		}

	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		super.actionPerformed(button);
		try
		{
			if (button.id == Bid)
			{
				if (STRlastid != -1)
				{
					if (sideinv.currentPossible)
					{
						rot = ((GuiButtonBuilder) buttonList.get(STRlastid)).rot;
						PacketHandler.sendToServer(new BuildPacket(Ndir, ((Structure) Ast.get(STRlastid)).getUnlocalizedName(), Wx, Wy, Wz, rot, this.inventory.player.getHeldItem()));
						// GLoger.logInfo("Sending packet");
						Minecraft.getMinecraft().thePlayer.closeScreen();
					}
				}
			} else
			{

				if (STRlastid == button.id)
				{
					rot = ((GuiButtonBuilder) button).rot;
					if (Ast.get(STRlastid) instanceof StructureRotatable)
					{
						((GuiButtonBuilder) button).setRotation(((StructureRotatable) Ast.get(STRlastid)).nextPossibleValue(rot, Ndir, meta));

					}
					rot = ((GuiButtonBuilder) button).rot;
				} else
				{
					if (STRlastid != -1)
					{
						((GuiButtonBuilder) buttonList.get(STRlastid)).setEnabled(false);

					}

					STRlastid = button.id;
					((GuiButtonBuilder) button).setEnabled(true);
					if (Ast.get(STRlastid) instanceof StructureRotatable && ((GuiButtonBuilder) button).rot == 0)
					{
						((GuiButtonBuilder) button).setRotation(((StructureRotatable) Ast.get(STRlastid)).nextPossibleValue(-1, Ndir, meta));
					}
				}
			}
		}
		catch (Throwable error)
		{
			GLoger.logWarn("An error ocured in GuiBuilder", error);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		this.cornerX = x;
		this.cornerY = y;

		drawTexturedModalRect(x, y, 0, 0, Xsize, Ysize);

		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

		if (STRlastid == -1)
		{
			((GuiButton) buttonList.get(Bid)).enabled = false;
		} else
		{
			if (sideinv.currentPossible)
			{
				((GuiButton) buttonList.get(Bid)).enabled = true;
			} else
			{
				((GuiButton) buttonList.get(Bid)).enabled = false;
			}
		}

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		super.drawGuiContainerForegroundLayer(par1, par2);

		fontRendererObj.drawString(StatCollector.translateToLocal("builder.name"), (int) (xSize / 4.5D) - (fontRendererObj.getStringWidth(I18n.format("builder.name")) / 2) + 70, 10, 4210752, false);
	}

}
