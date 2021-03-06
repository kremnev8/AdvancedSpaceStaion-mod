
package net.glider.src.renderer;

import ic2.api.item.IC2Items;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.glider.src.entity.AbstractSteve;
import net.glider.src.renderer.models.ModelArmorStand;
import net.glider.src.renderer.models.ModelDummy;
import net.glider.src.tiles.TileEntityArmorStand;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import buildcraft.BuildCraftTransport;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.PipeTransportFluids;
import cofh.thermaldynamics.duct.BlockDuct;
import cofh.thermaldynamics.duct.energy.TileEnergyDuct;
import cofh.thermaldynamics.duct.energy.TileEnergyDuctSuper;
import cofh.thermaldynamics.duct.item.TileItemDuct;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import crazypants.enderio.conduit.BlockConduitBundle;
import crazypants.enderio.conduit.TileConduitBundle;
import crazypants.enderio.conduit.item.ItemConduit;
import crazypants.enderio.conduit.power.PowerConduit;

@SideOnly(Side.CLIENT)
public class TileEntityArmorStandRenderer extends TileEntitySpecialRenderer {
	
	private static final ResourceLocation rl = new ResourceLocation(GliderModInfo.ModTestures, "textures/blocks/ArmorStand.png");
	private ModelArmorStand model = new ModelArmorStand();
	public int rot = 0;
	
	private float modelScale = 0.0666667F;
	private boolean helmLeatherLayer = false;
	private boolean thuamcraftRobeChest = false;
	private boolean legLeatherLayer = false;
	private boolean thaumcraftRobeLegs = false;
	private boolean thaumcraftRobeFoot = false;
	private static String[] armorArray;
	private AbstractClientPlayer steve;
	private boolean renderSteve = false;
	private ItemStack pumpkinTester = new ItemStack(Blocks.pumpkin);
	private Item pumkingetting = Item.getItemFromBlock(Blocks.pumpkin);
	private ModelDummy modelDummy = new ModelDummy();
	public static String[] defaultHelmNames = { "item.helmetCloth", "item.helmetIron", "item.helmetChain", "item.helmetGold", "item.helmetDiamond" };
	public static String[] defaultHelmArmorPaths = { "textures/models/armor/leather_layer_1.png", "textures/models/armor/iron_layer_1.png",
			"textures/models/armor/chainmail_layer_1.png", "textures/models/armor/gold_layer_1.png", "textures/models/armor/diamond_layer_1.png" };
	public static String[] defaultChestNames = { "item.chestplateCloth", "item.chestplateIron", "item.chestplateChain", "item.chestplateGold", "item.chestplateDiamond" };
	public static String[] defaultChestArmorPaths = { "textures/models/armor/leather_layer_1.png", "textures/models/armor/iron_layer_1.png",
			"textures/models/armor/chainmail_layer_1.png", "textures/models/armor/gold_layer_1.png", "textures/models/armor/diamond_layer_1.png" };
	public static String[] defaultLegNames = { "item.leggingsCloth", "item.leggingsIron", "item.leggingsChain", "item.leggingsGold", "item.leggingsDiamond" };
	public static String[] defaultLegArmorPaths = { "textures/models/armor/leather_layer_2.png", "textures/models/armor/iron_layer_2.png",
			"textures/models/armor/chainmail_layer_2.png", "textures/models/armor/gold_layer_2.png", "textures/models/armor/diamond_layer_2.png" };
	public static String[] defaultBootNames = { "item.bootsCloth", "item.bootsIron", "item.bootsChain", "item.bootsGold", "item.bootsDiamond" };
	public static String[] defaultBootArmorPaths = { "textures/models/armor/leather_layer_1.png", "textures/models/armor/iron_layer_1.png",
			"textures/models/armor/chainmail_layer_1.png", "textures/models/armor/gold_layer_1.png", "textures/models/armor/diamond_layer_1.png" };
	private int degreeAngle;
	public static final ResourceLocation GLINT_PNG = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float f1)
	{
		renderTE((TileEntityArmorStand) te, dx, dy, dz, f1);
	}
	
	public void renderTE(TileEntity te, double dx, double dy, double dz, float tick)
	{
		GL11.glTranslatef(0, 0.01F, 0);
		GL11.glDisable(GL11.GL_CULL_FACE);
		rot = te.getBlockMetadata();
		if (rot == 0)
		{
			degreeAngle = 180;
		} else if (rot == 3)
		{
			degreeAngle = 270;
		} else if (rot == 2)
		{
			degreeAngle = 0;
		} else if (rot == 1)
		{
			degreeAngle = 90;
		}
		
		//	float scale = 0.0625F;
		
		if (this.steve == null)
		{
			this.steve = new AbstractSteve(te.getWorldObj());
		}
		RenderManager.renderPosX = this.steve.posX = dx;
		RenderManager.renderPosY = this.steve.posY = dy;
		RenderManager.renderPosZ = this.steve.posZ = dz;
		
		TileEntityArmorStand standTile = (TileEntityArmorStand) te;
		ItemStack helmStack = standTile.getArmor(0);
		ItemStack chestStack = standTile.getArmor(1);
		ItemStack legginsStack = standTile.getArmor(2);
		ItemStack bootsStack = standTile.getArmor(3);
		this.renderSteve = false;
		this.steve.inventory.armorInventory[3] = null;
		this.steve.inventory.armorInventory[2] = null;
		this.steve.inventory.armorInventory[1] = null;
		this.steve.inventory.armorInventory[0] = null;
		if (helmStack != null)
		{
			Item helmItem = helmStack.getItem();
			if (((helmItem instanceof ItemSkull)) || (helmItem == this.pumkingetting))
			{
				this.steve.inventory.armorInventory[0] = helmStack;
				this.renderSteve = true;
			}
			if ((helmItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorHelm = (ItemArmor) helmItem;
				int aType = armorHelm.armorType;
				ModelBiped helmModel = helmItem.getArmorModel(this.steve, helmStack, 0);
				boolean isEnchanted = armorHelm.hasEffect(helmStack, 0);
				String helmTexture = ForgeHooksClient.getArmorTexture(this.steve, helmStack, getArmor(helmStack.getUnlocalizedName(), aType), aType, null);
				GL11.glPushMatrix();
				GL11.glTranslated(dx + 0.5D, dy + 1.549999952316284D, dz + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.helmLeatherLayer = false;
				if (helmStack.getUnlocalizedName().contains("item.helmetCloth"))
				{
					int var9 = armorHelm.getColor(helmStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					this.helmLeatherLayer = true;
				}
				if (helmModel != null)
				{
					this.steve.inventory.armorInventory[3] = helmStack;
					this.renderSteve = true;
				} else
				{
					GL11.glScalef(1.02F, 1.02F, 1.02F);
					bindTexture(new ResourceLocation(helmTexture));
					this.modelDummy.renderHead();
					if (this.helmLeatherLayer)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("textures/models/armor/leather_layer_1_overlay.png"));
						this.modelDummy.renderHead();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(0);
				}
				GL11.glPopMatrix();
			}
		}
		if (chestStack != null)
		{
			Item cuirassItem = chestStack.getItem();
			if ((cuirassItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorCuirass = (ItemArmor) cuirassItem;
				int aType = armorCuirass.armorType;
				
				ModelBiped cuirassModel = cuirassItem.getArmorModel(this.steve, chestStack, 1);
				
				String cuirassTexture = ForgeHooksClient.getArmorTexture(this.steve, chestStack, getArmor(chestStack.getUnlocalizedName(), aType), aType, null);
				boolean isEnchanted = armorCuirass.hasEffect(chestStack, 0);
				
				GL11.glPushMatrix();
				GL11.glTranslated(dx + 0.5D, dy + 1.600000023841858D, dz + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.thuamcraftRobeChest = false;
				if ((chestStack.getUnlocalizedName().contains("item.chestplateCloth")) || (chestStack.getUnlocalizedName().contains("item.ItemChestplateRobe")))
				{
					int var9 = armorCuirass.getColor(chestStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					if (chestStack.getUnlocalizedName().contains("item.ItemChestplateRobe"))
					{
						this.thuamcraftRobeChest = true;
					}
				}
				//		String tchoverTest = cuirassItem.toString();
				if (cuirassModel != null)
				{
					this.steve.inventory.armorInventory[2] = chestStack;
					this.renderSteve = true;
				} else
				{
					GL11.glScalef(1.12F, 1.12F, 1.12F);
					bindTexture(new ResourceLocation(cuirassTexture));
					this.modelDummy.renderChest();
					if (this.thuamcraftRobeChest)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("thaumcraft:textures/models/robes_1_overlay.png"));
						this.modelDummy.renderChest();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(1);
				}
				GL11.glPopMatrix();
			}
		}
		if (legginsStack != null)
		{
			Item greavesItem = legginsStack.getItem();
			if ((greavesItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorGreaves = (ItemArmor) greavesItem;
				int aType = armorGreaves.armorType;
				
				ModelBiped greavesModel = greavesItem.getArmorModel(this.steve, legginsStack, 2);
				
				String greavesTexture = ForgeHooksClient.getArmorTexture(this.steve, legginsStack, getArmor(legginsStack.getUnlocalizedName(), aType), aType, null);
				boolean isEnchanted = armorGreaves.hasEffect(legginsStack, 0);
				
				GL11.glPushMatrix();
				GL11.glTranslated(dx + 0.5D, dy + 1.799999952316284D, dz + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.legLeatherLayer = false;
				this.thaumcraftRobeLegs = false;
				if ((legginsStack.getUnlocalizedName().contains("item.leggingsCloth")) || (legginsStack.getUnlocalizedName().contains("item.ItemLeggingsRobe")))
				{
					int var9 = armorGreaves.getColor(legginsStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					if (legginsStack.getUnlocalizedName().contains("item.ItemLeggingsRobe"))
					{
						this.thaumcraftRobeLegs = true;
						GL11.glTranslated(0.0D, 0.21D, 0.0D);
					} else
					{
						this.legLeatherLayer = true;
					}
				}
				if (greavesModel != null)
				{
					this.steve.inventory.armorInventory[1] = legginsStack;
					this.renderSteve = true;
				} else
				{
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					bindTexture(new ResourceLocation(greavesTexture));
					this.modelDummy.renderLegs();
					if (this.legLeatherLayer)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("textures/models/armor/leather_layer_2_overlay.png"));
						this.modelDummy.renderLegs();
					}
					if (this.thaumcraftRobeLegs)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("thaumcraft:textures/models/robes_2_overlay.png"));
						this.modelDummy.renderLegs();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(2);
				}
				GL11.glPopMatrix();
			}
		}
		if (bootsStack != null)
		{
			Item bootsItem = bootsStack.getItem();
			if ((bootsItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorBoots = (ItemArmor) bootsItem;
				int aType = armorBoots.armorType;
				
				ModelBiped bootsModel = bootsItem.getArmorModel(this.steve, bootsStack, 3);
				String bootsTexture = ForgeHooksClient.getArmorTexture(this.steve, bootsStack, getArmor(bootsStack.getUnlocalizedName(), aType), aType, null);
				boolean isEnchanted = armorBoots.hasEffect(bootsStack, 0);
				
				GL11.glPushMatrix();
				GL11.glTranslated(dx + 0.5D, dy + 1.7D, dz + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.thaumcraftRobeFoot = false;
				if ((bootsStack.getUnlocalizedName().contains("item.bootsCloth")) || (bootsStack.getUnlocalizedName().contains("item.ItemBootsRobe")))
				{
					int var9 = armorBoots.getColor(bootsStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					if (bootsStack.getUnlocalizedName().contains("item.ItemBootsRobe"))
					{
						this.thaumcraftRobeFoot = true;
					}
				}
				if (bootsModel != null)
				{
					this.steve.inventory.armorInventory[0] = bootsStack;
					this.renderSteve = true;
				} else
				{
					bindTexture(new ResourceLocation(bootsTexture));
					this.modelDummy.renderLegs();
					if (this.thaumcraftRobeFoot)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("thaumcraft:textures/models/robes_1_overlay.png"));
						this.modelDummy.renderLegs();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(3);
				}
				GL11.glPopMatrix();
			}
		}
		GL11.glPushMatrix();
		GL11.glTranslated(dx, dy, dz);
		//	GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
		//	GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0, -0.01F, 0);
		rot = te.getBlockMetadata();
		GL11.glPushMatrix();
		//GL11.glTranslated(dx, dy, dz);
		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
		if (rot == 0)
		{
			GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
		} else if (rot == 3)
		{
			GL11.glRotatef(180, -1.0F, 0.0F, 1.0F);
		} else if (rot == 2)
		{
			GL11.glRotatef(180, 0.0F, 0.0F, -1.0F);
		} else if (rot == 1)
		{
			GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
		}
		// GL11.glTranslatef(0F, 0F, 0.1F);
		
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindTexture(rl);
		model.render(0.0625F);
		
		World world = te.getWorldObj();
		for (int l = 0; l < 4; l++)
		{
			int meta = world.getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
			
			int o = l + meta;
			if (o > 3)
			{
				o -= 4;
			}
			
			Block bl = world.getBlock(te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord, te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0));
			if (bl == GCBlocks.fuelLoader)
			{
				model.render("fuel", l + 3 > 3 ? (l - 4) + 3 : l + 3);
			}
			if (bl == GCBlocks.aluminumWire)
			{
				model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
			}
			if (EnergyConfigHandler.isIndustrialCraft2Loaded() && bl == Block.getBlockFromItem(IC2Items.getItem("copperCableBlock").getItem()))
			{
				model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
			}
			if (EnergyConfigHandler.isBuildcraftReallyLoaded() && bl == BuildCraftTransport.genericPipeBlock)
			{
				if (BlockGenericPipe.getPipe(world, te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord, te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0)) == null
						|| BlockGenericPipe.getPipe(world, te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord,
								te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0)).transport instanceof PipeTransportFluids)
				{
					
				} else
				{
					model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
				}
			}
			if (Loader.isModLoaded("ThermalDynamics") && bl instanceof BlockDuct)
			{
				TileEntity te2 = world.getTileEntity(te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord, te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0));
				if (te2 instanceof TileEnergyDuct || te2 instanceof TileItemDuct && !(te2 instanceof TileEnergyDuctSuper))
				{
					model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
				} else if (te2 instanceof TileEnergyDuctSuper)
				{
					model.render("fuel", l + 3 > 3 ? (l - 4) + 3 : l + 3);
				}
			}
			if (Loader.isModLoaded("EnderIO") && bl instanceof BlockConduitBundle)
			{
				TileConduitBundle te2 = (TileConduitBundle) world.getTileEntity(te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord,
						te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0));
				if (te2 != null && (te2.hasType(PowerConduit.class) || te2.hasType(ItemConduit.class)))
				{
					model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
				}
			}
		}
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		
		/*
		 * int armortextnum = standTile.getArmorText(); if ((armortextnum > 0)
		 * && (armortextnum < 5)) { ItemStack armorpiece =
		 * standTile.getArmor(armortextnum - 1); if (armorpiece != null) {
		 * String armorname = armorpiece.func_82833_r(); int numlines = 1;
		 * String ench1 = ""; String ench2 = ""; String ench3 = ""; String ench4
		 * = "";
		 * 
		 * NBTTagList taglist = func_92056_g(armorpiece);
		 * 
		 * int tcount = taglist.func_74745_c(); if ((taglist != null) && (tcount
		 * > 0)) { if (tcount >= 1) { short var7 =
		 * taglist.func_150305_b(0).func_74765_d("id"); short var8 =
		 * taglist.func_150305_b(0).func_74765_d("lvl"); if
		 * (Enchantment.field_77331_b[var7] != null) { ench1 =
		 * Enchantment.field_77331_b[var7].func_77316_c(var8); numlines = 2; } }
		 * if (tcount >= 2) { short var7 =
		 * taglist.func_150305_b(1).func_74765_d("id"); short var8 =
		 * taglist.func_150305_b(1).func_74765_d("lvl"); if
		 * (Enchantment.field_77331_b[var7] != null) { ench2 =
		 * Enchantment.field_77331_b[var7].func_77316_c(var8); numlines = 3; } }
		 * if (tcount >= 3) { short var7 =
		 * taglist.func_150305_b(2).func_74765_d("id"); short var8 =
		 * taglist.func_150305_b(2).func_74765_d("lvl"); if
		 * (Enchantment.field_77331_b[var7] != null) { ench3 =
		 * Enchantment.field_77331_b[var7].func_77316_c(var8); numlines = 4; } }
		 * if (tcount >= 4) { short var7 =
		 * taglist.func_150305_b(3).func_74765_d("id"); short var8 =
		 * taglist.func_150305_b(3).func_74765_d("lvl"); if
		 * (Enchantment.field_77331_b[var7] != null) { ench4 =
		 * Enchantment.field_77331_b[var7].func_77316_c(var8); numlines = 5; } }
		 * } switch (armortextnum) { case 1: renderText(armorname, ench1, ench2,
		 * ench3, ench4, numlines, -208.0D, -15.0D); break; case 2:
		 * renderText(armorname, ench1, ench2, ench3, ench4, numlines, -85.0D,
		 * 10.0D); break; case 3: renderText(armorname, ench1, ench2, ench3,
		 * ench4, numlines, 20.0D, 0.0D); break; case 4: renderText(armorname,
		 * ench1, ench2, ench3, ench4, numlines, 100.0D, 0.0D); break; } }
		 * standTile.setArmorText(0); }
		 */
		GL11.glTranslatef(0, 0.01F, 0);
		GL11.glPopMatrix();
		if (this.renderSteve)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.75F, (float) dz + 0.5F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(this.degreeAngle - 180, 0.0F, 1.0F, 0.0F);
			
			RenderManager.instance.renderEntitySimple(this.steve, -0.3F);
			
			GL11.glPopMatrix();
		}
		GL11.glTranslatef(0, -0.01F, 0);
	}
	
	/*
	 * public void renderText(String text, String text2, String text3, String
	 * text4, String text5, int numlines, double yadjust, double zadjust) {
	 * FontRenderer fontRender = func_147498_b(); GL11.glDepthMask(false);
	 * 
	 * GL11.glScalef(0.005F, 0.005F, 0.005F); GL11.glTranslated(0.0D, 100.0D +
	 * yadjust, -45.0D + zadjust);
	 * 
	 * int adjust = fontRender.func_78256_a(text) / 2;
	 * fontRender.func_85187_a(text, -adjust, 0, Config.color,
	 * Config.textshadow); if (numlines > 1) { int adjust2 =
	 * fontRender.func_78256_a(text2) / 2; fontRender.func_85187_a(text2,
	 * -adjust2, 10, Config.color2, Config.textshadow); } if (numlines > 2) {
	 * int adjust3 = fontRender.func_78256_a(text3) / 2;
	 * fontRender.func_85187_a(text3, -adjust3, 20, Config.color2,
	 * Config.textshadow); } if (numlines > 3) { int adjust4 =
	 * fontRender.func_78256_a(text4) / 2; fontRender.func_85187_a(text4,
	 * -adjust4, 30, Config.color2, Config.textshadow); } if (numlines > 4) {
	 * int adjust5 = fontRender.func_78256_a(text5) / 2;
	 * fontRender.func_85187_a(text5, -adjust5, 40, Config.color2,
	 * Config.textshadow); } GL11.glDepthMask(true); }
	 */
	
	public String getArmor(String itemStackName, int armorType)
	{
		String armorDefault = "textures/models/armor/iron_layer_1.png";
		String armorLegDefault = "textures/models/armor/iron_layer_1.png";
		//	String armorName = itemStackName.toLowerCase();
		switch (armorType) {
		case 0:
			String armorReturn;
			for (int x = 0; x < defaultHelmNames.length; x++)
			{
				if (defaultHelmNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultHelmArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorDefault;
			}
			break;
		case 1:
			for (int x = 0; x < defaultChestNames.length; x++)
			{
				if (defaultChestNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultChestArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorDefault;
			}
			break;
		case 2:
			for (int x = 0; x < defaultLegNames.length; x++)
			{
				if (defaultLegNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultLegArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorLegDefault;
			}
			break;
		case 3:
			for (int x = 0; x < defaultBootNames.length; x++)
			{
				if (defaultBootNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultBootArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorDefault;
			}
			break;
		}
		return armorDefault;
	}
	
	public void enchant(int armorType)
	{
		float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 48.0F;
		bindTexture(GLINT_PNG);
		GL11.glEnable(3042);
		float var20 = 0.5F;
		GL11.glColor4f(var20, var20, var20, 1.0F);
		GL11.glDepthFunc(514);
		GL11.glDepthMask(false);
		for (int var21 = 0; var21 < 2; var21++)
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			float var22 = 0.76F;
			GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
			GL11.glBlendFunc(768, 1);
			GL11.glMatrixMode(5890);
			GL11.glLoadIdentity();
			float var23 = tickModifier * (0.001F + var21 * 0.003F) * 20.0F;
			float var24 = 0.3333333F;
			GL11.glScalef(var24, var24, var24);
			GL11.glRotatef(30.0F - var21 * 60.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.0F, var23, 0.0F);
			GL11.glMatrixMode(5888);
			switch (armorType) {
			case 0:
				this.modelDummy.renderHead();
				break;
			case 1:
				this.modelDummy.renderChest();
				break;
			case 2:
				this.modelDummy.renderLegs();
				break;
			case 3:
				this.modelDummy.renderLegs();
			}
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glMatrixMode(GL11.GL_TEXTURE);
		GL11.glDepthMask(true);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
	
}