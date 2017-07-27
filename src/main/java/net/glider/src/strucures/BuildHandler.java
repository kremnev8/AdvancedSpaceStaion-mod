package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.glider.src.blocks.BlockContainerMod;
import net.glider.src.blocks.BlockMod;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.ForgeDirectionUtils;
import net.glider.src.utils.ItemUtil;
import net.glider.src.utils.MatrixHelper;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BuildHandler {
	
	public static StructureStub str1 = new StructureStub(true);
	public static StructureHall str2 = new StructureHall(false);
	public static StructureCornerHall str3 = new StructureCornerHall(false);
	public static StructureCrossroad str4 = new StructureCrossroad(false);
	public static StructureHallWAirlock str5 = new StructureHallWAirlock(false);
	public static StructureWindow str6 = new StructureWindow(false);
	public static StructureCupola str7 = new StructureCupola(false);
	public static StructureDockingPort str8 = new StructureDockingPort(false);
	public static StructureSolarPanel str9 = new StructureSolarPanel(false);
	public static StructureThall str10 = new StructureThall(false);
	public static StructureBigHall str11 = new StructureBigHall(false);
	public static StructureGreenHouse str12 = new StructureGreenHouse();
	public static StructurePierce str13 = new StructurePierce();
	
	private static boolean haveContainerItem(List<ItemStack> found, OreDictItemStack is)
	{
		for (int i = 0; i < found.size(); i++)
		{
			OreDictItemStack curr = new OreDictItemStack(found.get(i));
			if (is != null && curr != null)
			{
				if (is.isStackEqual(curr, false))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean CheckItems(World world, String FuncName, NBTTagList items, EntityPlayer player)
	{
		if (player.capabilities.isCreativeMode)
		{
			return true;
		}
		
		Structure CurStr = Structure.FindStructure(FuncName);
		List<OreDictItemStack> required = CurStr.getRequiredItems();
		
		for (int i = 0; i < required.size(); i++)
		{
			OreDictItemStack curr = required.get(i);
			for (int j = 0; j < required.size(); j += 0)
			{
				boolean removed = false;
				if (j != i)
				{
					OreDictItemStack last = required.get(j);
					if (last != null && curr != null)
					{
						if (curr.isStackEqual(last, true))
						{
							curr.example.stackSize += last.example.stackSize;
							required.remove(j);
							removed = true;
						}
					}
				}
				if (!removed)
				{
					j++;
				}
			}
		}
		
		List<ItemStack> found = new ArrayList();
		
		if (items.tagCount() > 0)
		{
			for (int i = 0; i < items.tagCount(); i++)
			{
				int[] pos = items.func_150306_c(i);
				if (world != null)
				{
					TileEntity te = world.getTileEntity(pos[0], pos[1], pos[2]);
					if (te instanceof IInventory)
					{
						IInventory inv = (IInventory) te;
						if (inv.getSizeInventory() > 0)
						{
							for (int j = 0; j < inv.getSizeInventory(); j++)
							{
								if (inv.getStackInSlot(j) != null)
								{
									found.add(inv.getStackInSlot(j).copy());
								}
							}
						}
					}
				}
			}
			
		}
		if (player.inventory.getSizeInventory() > 0)
		{
			for (int j = 0; j < player.inventory.getSizeInventory(); j++)
			{
				if (player.inventory.getStackInSlot(j) != null)
				{
					found.add(player.inventory.getStackInSlot(j).copy());
				}
			}
		}
		for (int i = 0; i < found.size(); i++)
		{
			ItemStack curr = found.get(i);
			for (int j = 0; j < found.size(); j += 0)
			{
				boolean removed = false;
				if (j != i)
				{
					ItemStack last = found.get(j);
					if (last != null && curr != null)
					{
						if (ItemUtil.AreItemStackEqual(curr, last, true))
						{
							curr.stackSize += last.stackSize;
							found.remove(j);
							removed = true;
						}
					}
				}
				if (!removed)
				{
					j++;
				}
			}
		}
		
		boolean skipped = false;
		for (int i = 0; i < required.size(); i++)
		{
			if (!haveContainerItem(found, required.get(i)))
			{
				return false;
			}
		}
		
		for (int k = 0; k < required.size(); k++)
		{
			OreDictItemStack wantedItem = required.get(k);
			boolean Found = false;
			for (int i = 0; i < items.tagCount(); i++)
			{
				int[] pos = items.func_150306_c(i);
				if (world != null)
				{
					TileEntity te = world.getTileEntity(pos[0], pos[1], pos[2]);
					if (te instanceof IInventory)
					{
						IInventory inv = (IInventory) te;
						if (inv.getSizeInventory() > 0)
						{
							for (int j = 0; j < inv.getSizeInventory(); j++)
							{
								OreDictItemStack curr = new OreDictItemStack(inv.getStackInSlot(j));
								if (wantedItem != null && curr != null)
								{
									if (wantedItem.isStackEqual(curr, true))
									{
										if (curr.example.stackSize >= wantedItem.example.stackSize)
										{
											Found = true;
											inv.decrStackSize(j, wantedItem.example.stackSize);
											if (inv.getStackInSlot(j) != null && inv.getStackInSlot(j).stackSize == 0)
											{
												inv.setInventorySlotContents(j, null);
											}
											break;
										} else if (curr.example.stackSize > 0)
										{
											wantedItem.example.stackSize -= curr.example.stackSize;
											inv.setInventorySlotContents(j, null);
										}
									}
								}
							}
							if (Found)
							{
								break;
							}
						}
					}
				}
			}
			if (player.inventory.getSizeInventory() > 0)
			{
				for (int j = 0; j < player.inventory.getSizeInventory(); j++)
				{
					OreDictItemStack curr = new OreDictItemStack(player.inventory.getStackInSlot(j));
					if (wantedItem != null && curr != null)
					{
						if (wantedItem.isStackEqual(curr, true))
						{
							if (curr.example.stackSize >= wantedItem.example.stackSize)
							{
								Found = true;
								player.inventory.decrStackSize(j, wantedItem.example.stackSize);
								if (player.inventory.getStackInSlot(j) != null && player.inventory.getStackInSlot(j).stackSize == 0)
								{
									player.inventory.setInventorySlotContents(j, null);
								}
								break;
							} else if (curr.example.stackSize > 0)
							{
								wantedItem.example.stackSize -= curr.example.stackSize;
								player.inventory.setInventorySlotContents(j, null);
							}
						}
					}
				}
			}
			
			if (!Found)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean HandleBuild(World world, ForgeDirection dir, String FuncName, int x, int y, int z, int rot, EntityPlayerMP player)
	{
		
		switch (FuncName) {
		case "stub":
			
			if (str1.Check(world, dir, x, y, z, -1))
			{
				str1.Build(world, dir, x, y, z);
				return true;
			}
		case "hall":
			if (str2.Check(world, dir, x, y, z, -1))
			{
				int[] IPoint = MatrixHelper.findMatrixPoint(world, dir, x, y, z);
				Map Matrix;
				if (IPoint != null)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					// if (Matrix == null) return false;
					if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint[0], IPoint[1], IPoint[2])) return false;
				} else Matrix = null;
				
				// no if's for each dir!
				int[] Spos = new int[] { x, y, z };
				Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 9);
				int[] Ppos;
				if (IPoint != null)
				{
					Ppos = IPoint.clone();
					Ppos = ForgeDirectionUtils.IncreaseByDir(dir, Ppos, 18);
				} else
				{
					Ppos = new int[] { 0, 0, 0 };
				}
				
				TileEntityInfo te = null;
				if (IPoint != null)
				{
					te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
					
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str2.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.ChildObjects.add(Nstr);
					}
				}
				str2.Build(world, dir, x, y, z);
				boolean Conect = false;
				if (Matrix != null)
				{
					if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
					{
						te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
						if (te != null)
						{
							if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
									&& te.Object.placementDir.getOpposite() == dir)
							{
								Conect = true;
							} else if (te.Object.getUnlocalizedName().equals("corner") && str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == dir)
							{
								Conect = true;
							} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
							{
								ForgeDirection[] dirs = str4.getDirs(te.Object.placementDir);
								for (int i = 0; i < 3; i++)
								{
									ForgeDirection STdir = dirs[i];
									if (STdir.getOpposite() == dir)
									{
										Conect = true;
										break;
									}
								}
							} else if (te.Object.getUnlocalizedName().equals("thall"))
							{
								str10.setRotation(te.Object.placementRotation);
								ForgeDirection[] dirs = str10.getDirs(te.Object.placementDir);
								for (int i = 0; i < 2; i++)
								{
									ForgeDirection STdir = dirs[i];
									if (STdir.getOpposite() == dir) Conect = true;
								}
							}
							if (Conect)
							{
								int[] Ipos = ForgeDirectionUtils.IncreaseByDir(dir, IPoint.clone(), 9);
								TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
								if (Ite != null)
								{
									
									Ite.Object.connections.add(te.Object);
								}
							}
						}
					}
				}
				if (!Conect)
				{
					str1.Build(world, dir, Spos[0], Spos[1], Spos[2]);
					str2.ClearWay(world, dir, x, y, z);
				} else
				{
					// no if's for each dir!
					Spos = ForgeDirectionUtils.IncreaseByDir(dir.getOpposite(), Spos, 2);
					str3.ClearWay(world, dir, Spos[0], Spos[1], Spos[2]);
					str2.ClearWay(world, dir, x, y, z);
				}
				return true;
			}
		case "corner":
			str3.setRotation(rot);
			if (str3.Check(world, dir, x, y, z, -1))
			{
				int[] IPoint = MatrixHelper.findMatrixPoint(world, dir, x, y, z);
				Map Matrix;
				if (IPoint != null)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					// if (Matrix == null) return false;
					if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint[0], IPoint[1], IPoint[2])) return false;
				} else Matrix = null;
				TileEntityInfo te = null;
				if (IPoint != null)
				{
					te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
					
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str3.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.ChildObjects.add(Nstr);
					}
				}
				str3.Build(world, dir, x, y, z);
				
				ForgeDirection Ndir = str3.onTurn(dir, rot);
				
				// no if's for each dir!
				int[] Spos = new int[] { x, y, z };
				Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 4);
				Spos = ForgeDirectionUtils.IncreaseByDir(Ndir, Spos, 5);
				int[] Ppos;
				if (IPoint != null)
				{
					Ppos = IPoint.clone();
					Ppos = ForgeDirectionUtils.IncreaseByDir(dir, Ppos, 9);
					Ppos = ForgeDirectionUtils.IncreaseByDir(Ndir, Ppos, 9);
				} else
				{
					Ppos = new int[] { 0, 0, 0 };
				}
				boolean Conect = false;
				if (Matrix != null)
				{
					if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
					{
						te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
						if (te != null)
						{
							if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
									&& te.Object.placementDir.getOpposite() == Ndir)
							{
								Conect = true;
							} else if (te.Object.getUnlocalizedName().equals("corner") && str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndir)
							{
								Conect = true;
							} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
							{
								ForgeDirection[] dirs = str4.getDirs(te.Object.placementDir);
								for (int i = 0; i < 3; i++)
								{
									ForgeDirection STdir = dirs[i];
									if (STdir.getOpposite() == Ndir) Conect = true;
								}
							} else if (te.Object.getUnlocalizedName().equals("thall"))
							{
								str10.setRotation(te.Object.placementRotation);
								ForgeDirection[] dirs = str10.getDirs(te.Object.placementDir);
								for (int i = 0; i < 2; i++)
								{
									ForgeDirection STdir = dirs[i];
									if (STdir.getOpposite() == Ndir) Conect = true;
								}
							}
							if (Conect)
							{
								int[] Ipos = ForgeDirectionUtils.IncreaseByDir(dir, IPoint.clone(), 9);
								TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
								if (Ite != null)
								{
									
									Ite.Object.connections.add(te.Object);
								}
							}
						}
					}
					if (!Conect)
					{
						str1.Build(world, Ndir, Spos[0], Spos[1], Spos[2]);
						str2.ClearWay(world, dir, x, y, z);
					} else
					{
						// no if's for each dir!
						Spos = ForgeDirectionUtils.IncreaseByDir(Ndir.getOpposite(), Spos, 2);
						str3.ClearWay(world, Ndir, Spos[0], Spos[1], Spos[2]);
						str2.ClearWay(world, dir, x, y, z);
						// str3.ClearWay(world, Ndir,
						// Spos[0], Spos[1],
						// Spos[2]);
					}
				} else
				{
					str1.Build(world, Ndir, Spos[0], Spos[1], Spos[2]);
					str2.ClearWay(world, dir, x, y, z);
				}
				return true;
			}
		case "crossroad":
			if (str4.Check(world, dir, x, y, z, -1))
			{
				
				int[] IPoint = MatrixHelper.findMatrixPoint(world, dir, x, y, z);
				Map Matrix;
				if (IPoint != null)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					if (dir != ForgeDirection.UNKNOWN)
					{
						if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint[0], IPoint[1], IPoint[2])) return false;
					}
				} else Matrix = null;
				TileEntityInfo te = null;
				if (IPoint != null && dir != ForgeDirection.UNKNOWN)
				{
					te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
					
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str4.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.ChildObjects.add(Nstr);
					}
				}
				str4.Build(world, dir, x, y, z);
				if (dir != ForgeDirection.UNKNOWN)
				{
					ForgeDirection[] dirs = str4.getDirs(dir);
					for (int i = 0; i < 3; i++)
					{
						ForgeDirection Ndir = dirs[i];
						int[] pos = str4.ChangePosForDir(dir, Ndir, x, y, z);
						
						// no if's for each dir!
						int[] Spos;
						if (IPoint != null)
						{
							Spos = IPoint.clone();
							Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 9);
							Spos = ForgeDirectionUtils.IncreaseByDir(Ndir, Spos, 9);
						} else
						{
							Spos = new int[] { 0, 0, 0 };
						}
						
						boolean Conect = false;
						if (Matrix != null)
						{
							if (MatrixHelper.FindPointInMatrix(Matrix, Spos) != null)
							{
								te = (TileEntityInfo) world.getTileEntity(Spos[0], Spos[1], Spos[2]);
								if (te != null)
								{
									if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
											&& te.Object.placementDir.getOpposite() == Ndir)
									{
										Conect = true;
									} else if (te.Object.getUnlocalizedName().equals("corner")
											&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndir)
									{
										Conect = true;
									} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
									{
										ForgeDirection[] dirs1 = str4.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 3; i2++)
										{
											ForgeDirection STdir = dirs1[i2];
											if (STdir.getOpposite() == Ndir) Conect = true;
										}
									} else if (te.Object.getUnlocalizedName().equals("thall"))
									{
										str10.setRotation(te.Object.placementRotation);
										ForgeDirection[] dirs1 = str10.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 2; i2++)
										{
											ForgeDirection STdir = dirs1[i2];
											if (STdir.getOpposite() == Ndir) Conect = true;
										}
									}
									if (Conect)
									{
										int[] Ipos = ForgeDirectionUtils.IncreaseByDir(dir, IPoint.clone(), 9);
										TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
										if (Ite != null)
										{
											
											Ite.Object.connections.add(te.Object);
										}
									}
								}
							}
						}
						if (!Conect)
						{
							str1.Build(world, Ndir, pos[0], pos[1], pos[2]);
						} else
						{
							// no if's for each dir!
							pos = ForgeDirectionUtils.IncreaseByDir(Ndir.getOpposite(), pos, 2);
							str3.ClearWay(world, Ndir, pos[0], pos[1], pos[2]);
						}
						
					}
					str2.ClearWay(world, dir, x, y, z);
				}
				return true;
			}
		case "hallairlock":
			if (str5.Check(world, dir, x, y, z, -1))
			{
				int[] IPoint = MatrixHelper.findMatrixPoint(world, dir, x, y, z);
				Map Matrix;
				if (IPoint != null)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					// if (Matrix == null) return false;
					if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint[0], IPoint[1], IPoint[2])) return false;
				} else Matrix = null;
				TileEntityInfo te = null;
				if (IPoint != null)
				{
					te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str5.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.ChildObjects.add(Nstr);
					}
				}
				str5.setOwner(player.getGameProfile().getName());
				str5.Build(world, dir, x, y, z);
				// no if's for each dir!
				int[] Spos = new int[] { x, y, z };
				Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 9);
				int[] Ppos;
				if (IPoint != null)
				{
					Ppos = IPoint.clone();
					Ppos = ForgeDirectionUtils.IncreaseByDir(dir, Ppos, 18);
				} else
				{
					Ppos = new int[] { 0, 0, 0 };
				}
				
				boolean Conect = false;
				if (Matrix != null)
				{
					if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
					{
						te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
						if (te != null)
						{
							if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
									&& te.Object.placementDir.getOpposite() == dir)
							{
								Conect = true;
							} else if (te.Object.getUnlocalizedName().equals("corner") && str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == dir)
							{
								Conect = true;
							} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
							{
								ForgeDirection[] dirs = str4.getDirs(te.Object.placementDir);
								for (int i = 0; i < 3; i++)
								{
									ForgeDirection STdir = dirs[i];
									if (STdir.getOpposite() == dir)
									{
										Conect = true;
										break;
									}
								}
							} else if (te.Object.getUnlocalizedName().equals("thall"))
							{
								str10.setRotation(te.Object.placementRotation);
								ForgeDirection[] dirs = str10.getDirs(te.Object.placementDir);
								for (int i = 0; i < 2; i++)
								{
									ForgeDirection STdir = dirs[i];
									if (STdir.getOpposite() == dir) Conect = true;
								}
							}
							if (Conect)
							{
								int[] Ipos = ForgeDirectionUtils.IncreaseByDir(dir, IPoint.clone(), 9);
								TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
								if (Ite != null)
								{
									Ite.Object.connections.add(te.Object);
								}
							}
						}
					}
				}
				if (!Conect)
				{
					str1.Build(world, dir, Spos[0], Spos[1], Spos[2], 0);
					str2.ClearWay(world, dir, x, y, z);
				} else
				{
					// no if's for each dir!
					Spos = ForgeDirectionUtils.IncreaseByDir(dir.getOpposite(), Spos, 2);
					str3.ClearWay(world, dir, Spos[0], Spos[1], Spos[2]);
					str2.ClearWay(world, dir, x, y, z);
				}
				return true;
			}
		case "window":
			if (str6.Check(world, dir, x, y, z, -1))
			{
				int[] MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, x, y, z);
				if (MatrixPoint != null && MatrixPoint.length > 0)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str6.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str6.setRotation(rot);
				if (rot == 1) str2.ClearWay(world, dir, x, y, z);
				str6.Build(world, dir, x, y, z);
				return true;
			}
		case "cupola":
			if (str7.Check(world, dir, x, y, z, -1))
			{
				int[] MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, x, y, z);
				if (MatrixPoint != null && MatrixPoint.length > 0)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str7.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str7.Build(world, dir, x, y, z);
				str7.ClearWay(world, dir, x, y, z);
				return true;
			}
		case "dockport":
			if (str8.Check(world, dir, x, y, z, -1))
			{
				int[] MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, x, y, z);
				if (MatrixPoint != null && MatrixPoint.length > 0)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str8.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str8.Build(world, dir, x, y, z);
				str8.ClearWay(world, dir, x, y, z);
				return true;
			}
		case "solarpanel":
			if (str9.Check(world, dir, x, y, z, -1))
			{
				int[] MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, x, y, z);
				if (MatrixPoint != null && MatrixPoint.length > 0)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str9.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str9.setRotation(rot);
				str9.Build(world, dir, x, y, z);
				return true;
			}
		case "thall":
			if (str10.Check(world, dir, x, y, z, -1))
			{
				int[] IPoint = MatrixHelper.findMatrixPoint(world, dir, x, y, z);
				Map Matrix;
				if (IPoint != null)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					// if (Matrix == null) return false;
					if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint[0], IPoint[1], IPoint[2])) return false;
				} else Matrix = null;
				TileEntityInfo te = null;
				if (IPoint != null)
				{
					te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str10.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.ChildObjects.add(Nstr);
					}
				}
				str10.setRotation(rot);
				str10.Build(world, dir, x, y, z);
				
				ForgeDirection[] dirs = str10.getDirs(dir);
				for (int i = 0; i < 2; i++)
				{
					ForgeDirection Ndir = dirs[i];
					int[] pos = str4.ChangePosForDir(dir, Ndir, x, y, z);
					// str1.Build(world, Ndir,
					// pos[0],pos[1],pos[2]);
					
					int[] Spos;
					if (IPoint != null)
					{
						Spos = IPoint.clone();
						Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 9);
						Spos = ForgeDirectionUtils.IncreaseByDir(Ndir, Spos, 9);
					} else
					{
						Spos = new int[] { 0, 0, 0 };
					}
					
					boolean Conect = false;
					if (Matrix != null)
					{
						if (MatrixHelper.FindPointInMatrix(Matrix, Spos) != null)
						{
							te = (TileEntityInfo) world.getTileEntity(Spos[0], Spos[1], Spos[2]);
							if (te != null)
							{
								if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
										&& te.Object.placementDir.getOpposite() == Ndir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("corner")
										&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
								{
									ForgeDirection[] dirs1 = str4.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < 3; i2++)
									{
										ForgeDirection STdir = dirs1[i2];
										if (STdir.getOpposite() == Ndir) Conect = true;
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									ForgeDirection[] dirs1 = str10.getDirs(te.Object.placementDir);
									for (int j = 0; j < 2; j++)
									{
										ForgeDirection STdir = dirs1[j];
										if (STdir.getOpposite() == Ndir) Conect = true;
									}
								}
								if (Conect)
								{
									int[] Ipos = ForgeDirectionUtils.IncreaseByDir(dir, IPoint.clone(), 9);
									TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
									if (Ite != null)
									{
										Ite.Object.connections.add(te.Object);
									}
								}
							}
						}
					}
					// System.out.println("W "+pos[0]+" "+pos[1]+" "+pos[2]+" "+Ndir+" "+i);
					if (!Conect)
					{
						str1.Build(world, Ndir, pos[0], pos[1], pos[2]);
					} else
					{
						pos = ForgeDirectionUtils.IncreaseByDir(Ndir.getOpposite(), pos, 2);
						str3.ClearWay(world, Ndir, pos[0], pos[1], pos[2]);
					}
				}
				
				str2.ClearWay(world, dir, x, y, z);
				
				return true;
			}
		case "bighall":
			if (str11.Check(world, dir, x, y, z, -1))
			{
				int[] IPoint = MatrixHelper.findMatrixPoint(world, dir, x, y, z);
				Map Matrix;
				if (IPoint != null)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					if (Matrix != null)
					{
						if (!isAvailable(dir, Matrix, IPoint[0], IPoint[1], IPoint[2])) return false;
						int[] FTPos = ForgeDirectionUtils.IncreaseByDir(dir, IPoint.clone(), 9);
						if (!isAvailable(dir, Matrix, FTPos[0], FTPos[1], FTPos[2])) return false;
						ForgeDirection Tdir;
						if (rot == 0)
						{
							Tdir = ForgeDirectionUtils.turnAgainstClockwise(dir);
						} else
						{
							Tdir = ForgeDirectionUtils.turnClockwise(dir);
						}
						int[] Tpos = ForgeDirectionUtils.IncreaseByDir(Tdir, IPoint.clone(), 9);
						if (!isAvailable(dir, Matrix, Tpos[0], Tpos[1], Tpos[2])) return false;
						ForgeDirectionUtils.IncreaseByDir(dir, Tpos, 9);
						if (!isAvailable(dir, Matrix, FTPos[0], FTPos[1], FTPos[2])) return false;
					}
					
				} else Matrix = null;
				TileEntityInfo te = null;
				if (IPoint != null)
				{
					te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str11.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.ChildObjects.add(Nstr);
					}
				}
				str11.setRotation(rot);
				str11.Build(world, dir, x, y, z);
				
				ForgeDirection[] dirs = str11.getDirs(dir);
				List<int[]> posT = str11.getPos(dir, dirs, x, y, z);
				Iterator<int[]> posI = posT.iterator();
				int i = 0;
				while (posI.hasNext())
				{
					int[] pos = posI.next();
					boolean Conect = false;
					if (Matrix != null)
					{
						
						int Px;
						int Pz;
						if (dirs[i] == ForgeDirection.NORTH)
						{
							Pz = pos[2] - 4;
							Px = pos[0];
						} else if (dirs[i] == ForgeDirection.SOUTH)
						{
							Pz = pos[2] + 4;
							Px = pos[0];
						} else if (dirs[i] == ForgeDirection.WEST)
						{
							Pz = pos[2];
							Px = pos[0] - 4;
						} else if (dirs[i] == ForgeDirection.EAST)
						{
							Pz = pos[2];
							Px = pos[0] + 4;
						} else
						{
							Px = pos[0];
							Pz = pos[2];
						}
						
						if (MatrixHelper.FindPointInMatrix(Matrix, new int[] { Px, pos[1] - 3, Pz }) != null)
						{
							te = (TileEntityInfo) world.getTileEntity(Px, pos[1] - 3, Pz);
							if (te != null)
							{
								if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
										&& te.Object.placementDir.getOpposite() == dirs[i])
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("corner")
										&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == dirs[i])
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
								{
									ForgeDirection[] Cdirs = str4.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < Cdirs.length; i2++)
									{
										ForgeDirection STdir = Cdirs[i2];
										if (STdir.getOpposite() == dirs[i])
										{
											Conect = true;
											break;
										}
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									ForgeDirection[] dirs1 = str10.getDirs(te.Object.placementDir);
									for (int j = 0; j < dirs1.length; j++)
									{
										ForgeDirection STdir = dirs1[j];
										if (STdir.getOpposite() == dirs[i]) Conect = true;
									}
								}
								if (Conect)
								{
									int[] Ipos = ForgeDirectionUtils.IncreaseByDir(dir, IPoint.clone(), 9);
									TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]);
									if (Ite != null)
									{
										Ite.Object.connections.add(te.Object);
									}
								}
							}
						}
					}
					if (!Conect)
					{
						str1.Build(world, dirs[i], pos[0], pos[1], pos[2]);
						i++;
					} else
					{
						int Sx = pos[0];
						int Sz = pos[2];
						if (dirs[i] == ForgeDirection.WEST)
						{
							Sx = pos[0] + 2;
						} else if (dirs[i] == ForgeDirection.EAST)
						{
							Sx = pos[0] - 2;
						} else if (dirs[i] == ForgeDirection.SOUTH)
						{
							Sz = pos[2] - 2;
						} else if (dirs[i] == ForgeDirection.NORTH)
						{
							Sz = pos[2] + 2;
						}
						str2.ClearWay(world, dirs[i], pos[0], y, pos[2]);
						str3.ClearWay(world, dirs[i], Sx, y, Sz);
						i++;
					}
				}
				str2.ClearWay(world, dir, x, y, z);
				return true;
				
			}
		case "greenhouse":
			if (str12.Check(world, dir, x, y, z, -1))
			{
				int[] MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, x, y, z);
				if (MatrixPoint != null && MatrixPoint.length > 0)
				{
					
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
					if (te != null)
					{
						Map Matrix = new HashMap<Integer, int[]>();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, MatrixPoint);
						Structure curr = te.Object;
						boolean[] wrong = new boolean[] { false, false };
						if (Matrix != null)
						{
							int[] Nmatr = MatrixHelper.FindPointInMatrix(Matrix, new int[] { MatrixPoint[0] - 9, MatrixPoint[1], MatrixPoint[2] });
							if (Nmatr != null && Nmatr.length > 0)
							{
								TileEntityInfo te2 = (TileEntityInfo) world.getTileEntity(Nmatr[0], Nmatr[1], Nmatr[2]);
								if (te2 != null)
								{
									if (te2.AddObjects != null && te2.AddObjects.size() > 0)
									{
										for (int j = 0; j < te2.AddObjects.size(); j++)
										{
											if (te2.AddObjects.get(j).getUnlocalizedName() == Structure.SOLARPANELID
													|| te2.AddObjects.get(j).getUnlocalizedName() == Structure.GREENHOUSE)
											{
												return false;
											}
										}
									}
									if (!(te2.Object.placementPos[0] == curr.placementPos[0] && te2.Object.placementPos[1] == curr.placementPos[1] && te2.Object.placementPos[2] == curr.placementPos[2]))
									{
										wrong[0] = true;
									}
								}
							} else
							{
								wrong[0] = true;
							}
							Nmatr = MatrixHelper.FindPointInMatrix(Matrix, new int[] { MatrixPoint[0], MatrixPoint[1], MatrixPoint[2] - 9 });
							if (Nmatr != null && Nmatr.length > 0)
							{
								TileEntityInfo te2 = (TileEntityInfo) world.getTileEntity(Nmatr[0], Nmatr[1], Nmatr[2]);
								if (te2 != null)
								{
									if (te2.AddObjects != null && te2.AddObjects.size() > 0)
									{
										for (int j = 0; j < te2.AddObjects.size(); j++)
										{
											if (te2.AddObjects.get(j).getUnlocalizedName() == Structure.SOLARPANELID
													|| te2.AddObjects.get(j).getUnlocalizedName() == Structure.GREENHOUSE)
											{
												return false;
											}
										}
									}
									if (!(te2.Object.placementPos[0] == curr.placementPos[0] && te2.Object.placementPos[1] == curr.placementPos[1] && te2.Object.placementPos[2] == curr.placementPos[2]))
									{
										wrong[1] = true;
									}
								}
							} else
							{
								wrong[1] = true;
							}
							if (wrong[0] && wrong[1])
							{
								x = x + 9;
								z = z + 9;
								MatrixPoint[0] = MatrixPoint[0] + 9;
								MatrixPoint[2] = MatrixPoint[2] + 9;
								te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
								
							} else if (wrong[0])
							{
								x = x + 9;
								MatrixPoint[0] = MatrixPoint[0] + 9;
								te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
							} else if (wrong[1])
							{
								z = z + 9;
								MatrixPoint[2] = MatrixPoint[2] + 9;
								te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
							}
							
							Nmatr = MatrixHelper.FindPointInMatrix(Matrix, new int[] { MatrixPoint[0] - 9, MatrixPoint[1], MatrixPoint[2] - 9 });
							if (Nmatr != null && Nmatr.length > 0)
							{
								TileEntityInfo te2 = (TileEntityInfo) world.getTileEntity(Nmatr[0], Nmatr[1], Nmatr[2]);
								if (te2 != null)
								{
									if (te2.AddObjects != null && te2.AddObjects.size() > 0)
									{
										for (int j = 0; j < te2.AddObjects.size(); j++)
										{
											if (te2.AddObjects.get(j).getUnlocalizedName() == Structure.SOLARPANELID
													|| te2.AddObjects.get(j).getUnlocalizedName() == Structure.GREENHOUSE)
											{
												return false;
											}
										}
									}
								}
							}
						}
					}
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str12.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str12.Build(world, dir, x, y, z);
				return true;
			}
		case "pierce":
			if (str13.Check(world, dir, x, y, z, -1))
			{
				int[] MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, x, y, z);
				if (MatrixPoint != null && MatrixPoint.length > 0)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint[0], MatrixPoint[1], MatrixPoint[2]);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str13.getUnlocalizedName());
						Nstr.Configure(new int[] { x, y, z }, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				if (rot == 1) str2.ClearWay(world, dir, x, y, z);
				str13.Build(world, dir, x, y, z);
				str2.ClearWay(world, dir, x, y, z);
				return true;
			}
		}
		
		return false;
	}
	
	public static void buildInfoPoint(World world, ForgeDirection dir, String FuncName, int x, int y, int z, int rot, int PLx, int PLy, int PLz)
	{
		Block info = BlockContainerMod.BlockInfo;
		TileEntityInfo te;
		switch (FuncName) {
		case "hall":
			world.setBlock(x, y, z, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity(dir, rot, str2.copy(), new int[] { PLx, PLy, PLz });
			
			break;
		case "corner":
			world.setBlock(x, y, z, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity(dir, rot, str3.copy(), new int[] { PLx, PLy, PLz });
			
			break;
		case "crossroad":
			world.setBlock(x, y, z, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity(dir, rot, str4.copy(), new int[] { PLx, PLy, PLz });
			
			break;
		case "hallairlock":
			world.setBlock(x, y, z, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity(dir, rot, str5.copy(), new int[] { PLx, PLy, PLz });
			
			break;
		case "thall":
			world.setBlock(x, y, z, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity(dir, rot, str10.copy(), new int[] { PLx, PLy, PLz });
			
			break;
		case "bighall":
			world.setBlock(x, y, z, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity(dir, rot, str11.copy(), new int[] { PLx, PLy, PLz });
			
			break;
		}
	}
	
	public static void buildRemoveInfoPoint(World world, ForgeDirection dir, String FuncName, int x, int y, int z, int rot, int Ix, int Iy, int Iz)
	{
		Block info = BlockContainerMod.BlockRemoveInfo;
		TileEntityRemoveInfo te;
		switch (FuncName) {
		case "hall":
			world.setBlock(x, y, z, info, str2.getMetaFromDir(dir), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(Ix, Iy, Iz));
			break;
		case "corner":
			world.setBlock(x, y, z, info, str3.getMetaFromDirARot(dir, rot), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(Ix, Iy, Iz));
			break;
		case "crossroad":
			world.setBlock(x, y, z, info, str2.getMetaFromDir(dir), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(Ix, Iy, Iz));
			break;
		case "hallairlock":
			world.setBlock(x, y, z, info, str2.getMetaFromDir(dir), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(Ix, Iy, Iz));
			break;
		case "thall":
			world.setBlock(x, y, z, info, str10.getMetaFromDirARot(dir, rot), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(x, y, z);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(Ix, Iy, Iz));
			break;
		}
	}
	
	public static void buildBuildPoint(World world, int x, int y, int z, int type)
	{
		Block info = BlockMod.BuildpPoint;
		world.setBlock(x, y, z, info, type, 2);
	}
	
	public static boolean isAvailable(ForgeDirection dir, Map<Integer, int[]> M, int x, int y, int z)
	{
		int Px;
		int Pz;
		
		if (dir == ForgeDirection.WEST)
		{
			Px = x - 9;
			Pz = z;
		} else if (dir == ForgeDirection.EAST)
		{
			Px = x + 9;
			Pz = z;
		} else if (dir == ForgeDirection.SOUTH)
		{
			Px = x;
			Pz = z + 9;
		} else if (dir == ForgeDirection.NORTH)
		{
			Px = x;
			Pz = z - 9;
		} else
		{
			Px = x;
			Pz = z;
		}
		
		if (MatrixHelper.FindPointInMatrix(M, new int[] { Px, y, Pz }) == null)
		{
			return true;
		} else return false;
	}
	
	public static String getLocolizedName(String uln, int rot, boolean isShort)
	{
		
		switch (uln) {
		case "stub":
			return str1.getName();
		case "hall":
			return str2.getName();
		case "corner":
			return str3.getName();
		case "crossroad":
			return str4.getName();
		case "hallairlock":
			return str5.getName();
		case "window":
			return str6.getName();
		case "cupola":
			return str7.getName();
		case "dockport":
			return str8.getName();
		case "solarpanel":
			return str9.getName();
		case "thall":
			return str10.getName();
		case "bighall":
			return str11.getName();
		default:
			return "";
		}
	}
	
}
