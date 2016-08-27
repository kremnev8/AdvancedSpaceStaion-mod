
package net.glider.src.items;

import java.util.List;
import java.util.Random;

import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBrokenPlate extends ItemMod {
	
	private IIcon[] iconsTin;
	private IIcon[] iconsSteel;
	private IIcon[] iconsAluminum;
	private int type;
	private Random rand = new Random();
	
	public ItemBrokenPlate(String uln, int type)
	{
		super(uln);
		this.type = type;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setNoRepair();
		this.setShowDescr(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		if (tab == CreativeTabs.tabMaterials)
		{
			list.add(new ItemStack(item, 1, rand.nextInt(4)));
		}
	}
	
	@Override
	public CreativeTabs[] getCreativeTabs()
	{
		return new CreativeTabs[] { CreativeTabs.tabMaterials };
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister IIreg)
	{
		this.iconsTin = new IIcon[4];
		this.iconsSteel = new IIcon[4];
		this.iconsAluminum = new IIcon[4];
		
		for (int i = 0; i < 4; ++i)
		{
			this.iconsTin[i] = IIreg.registerIcon(GliderModInfo.ModTestures + ":" + "brokenTin" + i);
		}
		for (int i = 0; i < 4; ++i)
		{
			this.iconsSteel[i] = IIreg.registerIcon(GliderModInfo.ModTestures + ":" + "brokenSteel" + i);
		}
		for (int i = 0; i < 4; ++i)
		{
			this.iconsAluminum[i] = IIreg.registerIcon(GliderModInfo.ModTestures + ":" + "brokenAluminum" + i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		int j = MathHelper.clamp_int(meta, 0, 3);
		if (type == 0)
		{
			return iconsTin[j];
		} else if (type == 1)
		{
			return iconsSteel[j];
		} else
		{
			return iconsAluminum[j];
		}
	}
	
}
