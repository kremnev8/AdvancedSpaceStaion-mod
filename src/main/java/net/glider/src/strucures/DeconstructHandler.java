package net.glider.src.strucures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.glider.src.items.ItemMod;
import net.glider.src.tiles.TileEntityInfo;
import net.glider.src.tiles.TileEntityRemoveInfo;
import net.glider.src.utils.ForgeDirectionUtils;
import net.glider.src.utils.MatrixHelper;
import net.glider.src.utils.OreDictItemStack;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import scala.util.Random;

public class DeconstructHandler {
	
	static StructureStub str1 = new StructureStub(true);
	static StructureHall str2 = new StructureHall(false);
	static StructureCornerHall str3 = new StructureCornerHall(false);
	static StructureCrossroad str4 = new StructureCrossroad(false);
	static StructureHallWAirlock str5 = new StructureHallWAirlock(false);
	static StructureWindow str6 = new StructureWindow(false);
	static StructureCupola str7 = new StructureCupola(false);
	static StructureDockingPort str8 = new StructureDockingPort(false);
	static StructureSolarPanel str9 = new StructureSolarPanel(false);
	static StructureThall str10 = new StructureThall(false);
	static StructureBigHall str11 = new StructureBigHall(false);
	static StructureGreenHouse str12 = new StructureGreenHouse();
	public static StructurePierce str13 = new StructurePierce();
	
	public static List<ItemStack> modificateRetItems(List<OreDictItemStack> input)
	{
		List<ItemStack> ret = new ArrayList();
		if (input != null && input.size() > 0)
		{
			Random rand = new Random();
			for (int i = 0; i < input.size(); i++)
			{
				OreDictItemStack curr = input.get(i);
				if (curr.oreID.contains(OreDictionary.doesOreNameExist("plateTin") ? OreDictionary.getOreID("plateTin") : 0))
				{
					int rVal = rand.nextInt(curr.example.stackSize / 4);
					curr.example.stackSize -= rVal;
					ret.add(new ItemStack(ItemMod.brokenTin, rVal, rand.nextInt(3)));
					ret.add(curr.example);
				} else if (curr.oreID.contains(OreDictionary.doesOreNameExist("plateSteel") ? OreDictionary.getOreID("plateSteel") : 0))
				{
					int rVal = rand.nextInt(curr.example.stackSize / 4);
					curr.example.stackSize -= rVal;
					ret.add(new ItemStack(ItemMod.brokenSteel, rVal, rand.nextInt(3)));
					ret.add(curr.example);
				} else if (curr.oreID.contains(OreDictionary.doesOreNameExist("compressedSteel") ? OreDictionary.getOreID("compressedSteel") : 0))
				{
					int rVal = rand.nextInt(curr.example.stackSize / 4);
					curr.example.stackSize -= rVal;
					ret.add(new ItemStack(ItemMod.brokenAluminum, rVal, rand.nextInt(3)));
					ret.add(curr.example);
				} else
				{
					ret.add(curr.example);
				}
				
			}
		}
		return ret;
	}
	
	/**
	 * 
	 * @return 0 - failed, 1 - partially failed, 2 - successful
	 */
	public static int HandleDeconstruct(World world, List<Structure> objs, EntityPlayerMP player, int[] Ipos)
	{
		List<ItemStack> afterI = new ArrayList();
		boolean partiallyfailed = false;
		for (int i = 0; i < objs.size(); i++)
		{
			
			Structure str = (Structure) objs.get(i);
			
			if (!player.capabilities.isCreativeMode)
			{
				List<OreDictItemStack> items = str.getRequiredItems();
				afterI.addAll(DeconstructHandler.modificateRetItems(items));
			}
			
			int[] IPoint;
			switch (str.getUnlocalizedName()) {
			case "hall":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				Map Matrix;
				boolean Conect = false;
				if (IPoint != null && IPoint.length > 0)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					int[] SIpos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, IPoint.clone(), 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos[0], SIpos[1], SIpos[2]);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = false;
						for (int j = 0; j < Ite.ChildObjects.size(); j++)
						{
							if (Ite.ChildObjects.get(j).connections != null && Ite.ChildObjects.get(j).connections.size() > 0)
							{
								Structure Cstr = Ite.ChildObjects.get(j).connections.get(0);
								Structure CHstr = Ite.ChildObjects.get(j);
								if (CHstr instanceof StructureRotatable) ((StructureRotatable) CHstr).setRotation(CHstr.placementRotation);
								ForgeDirection[] StrDirs = CHstr.getDirs(CHstr.placementDir);
								int[] IP = MatrixHelper.findMatrixPoint(world, CHstr.placementDir, CHstr.placementPos[0], CHstr.placementPos[1], CHstr.placementPos[2]);
								boolean found = false;
								for (int k = 0; k < StrDirs.length; k++)
								{
									int[] IPD = ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP.clone(), 9);
									ForgeDirectionUtils.IncreaseByDir(StrDirs[k], IPD, 9);
									TileEntityInfo Cte = (TileEntityInfo) world.getTileEntity(IPD[0], IPD[1], IPD[2]);
									if (Cte != null && Cstr.getName().equals(Cte.Object.getName()) && Cstr.placementDir == Cte.Object.placementDir
											&& Cstr.placementPos[0] == Cte.Object.placementPos[0] && Cstr.placementPos[1] == Cte.Object.placementPos[1]
											&& Cstr.placementPos[2] == Cte.Object.placementPos[2])
									{
										found = true;
										if (CHstr instanceof StructureBigHall)
										{
											noConn = true;
											break;
										}
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP, 9);
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, CHstr.placementPos, 4);
										ForgeDirectionUtils.IncreaseByDir(StrDirs[k], CHstr.placementPos, 4);
										CHstr.connections.remove(0);
										CHstr.placementDir = StrDirs[k].getOpposite();
										Cte.ChildObjects.add(CHstr);
										TileEntityInfo CHte = (TileEntityInfo) world.getTileEntity(IP[0], IP[1], IP[2]);
										if (CHte != null)
										{
											CHte.Object = CHstr;
										}
										break;
									}
								}
								if (!found)
								{
									noConn = true;
									break;
								}
							} else
							{
								noConn = true;
								break;
							}
						}
						if (noConn)
						{
							partiallyfailed = true;
							if (objs.size() > 1)
							{
								return 1;
							} else return 0;
						}
					}
					
					if (world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]) != null)
					{
						TileEntityInfo te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
						for (int i2 = 0; i2 < te.ChildObjects.size(); i2++)
						{
							Structure Astr = (Structure) te.ChildObjects.get(i2);
							if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
									&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
									&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
							{
								te.ChildObjects.remove(i2);
								break;
							}
						}
					}
					
					int[] Ppos;
					Ppos = IPoint.clone();
					Ppos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, Ppos, 18);
					
					if (Matrix != null)
					{
						if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
						{
							TileEntityInfo te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
							if (te != null)
							{
								if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
										&& te.Object.placementDir.getOpposite() == str.placementDir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("corner")
										&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == str.placementDir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
								{
									ForgeDirection[] dirs = str4.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < 3; i2++)
									{
										ForgeDirection STdir = dirs[i2];
										if (STdir.getOpposite() == str.placementDir)
										{
											Conect = true;
											break;
										}
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									ForgeDirection[] dirs = str10.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < 2; i2++)
									{
										ForgeDirection STdir = dirs[i2];
										if (STdir.getOpposite() == str.placementDir) Conect = true;
									}
								}
							}
						}
					}
					
					IPoint[1] += 3;
					player.setPositionAndUpdate(IPoint[0] + 0.5F, IPoint[1], IPoint[2] + 0.5F);
				}
				
				str2.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				ForgeDirection dir = str.placementDir;
				
				int[] Spos = str.placementPos.clone();
				Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 9);
				if (!Conect)
				{
					str1.deconstruct(world, dir, Spos[0], Spos[1], Spos[2]);
				} else
				{
					Spos = ForgeDirectionUtils.IncreaseByDir(dir.getOpposite(), Spos, 1);
					str1.Build(world, dir.getOpposite(), Spos[0], Spos[1], Spos[2]);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "corner":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				Conect = false;
				if (IPoint != null && IPoint.length > 0)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					int[] SIpos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, IPoint.clone(), 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos[0], SIpos[1], SIpos[2]);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = false;
						for (int j = 0; j < Ite.ChildObjects.size(); j++)
						{
							if (Ite.ChildObjects.get(j).connections != null && Ite.ChildObjects.get(j).connections.size() > 0)
							{
								Structure Cstr = Ite.ChildObjects.get(j).connections.get(0);
								Structure CHstr = Ite.ChildObjects.get(j);
								if (CHstr instanceof StructureRotatable) ((StructureRotatable) CHstr).setRotation(CHstr.placementRotation);
								ForgeDirection[] StrDirs = CHstr.getDirs(CHstr.placementDir);
								int[] IP = MatrixHelper.findMatrixPoint(world, CHstr.placementDir, CHstr.placementPos[0], CHstr.placementPos[1], CHstr.placementPos[2]);
								boolean found = false;
								for (int k = 0; k < StrDirs.length; k++)
								{
									int[] IPD = ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP.clone(), 9);
									ForgeDirectionUtils.IncreaseByDir(StrDirs[k], IPD, 9);
									TileEntityInfo Cte = (TileEntityInfo) world.getTileEntity(IPD[0], IPD[1], IPD[2]);
									if (Cte != null && Cstr.getName().equals(Cte.Object.getName()) && Cstr.placementDir == Cte.Object.placementDir
											&& Cstr.placementPos[0] == Cte.Object.placementPos[0] && Cstr.placementPos[1] == Cte.Object.placementPos[1]
											&& Cstr.placementPos[2] == Cte.Object.placementPos[2])
									{
										found = true;
										if (CHstr instanceof StructureBigHall)
										{
											noConn = true;
											break;
										}
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP, 9);
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, CHstr.placementPos, 4);
										ForgeDirectionUtils.IncreaseByDir(StrDirs[k], CHstr.placementPos, 4);
										CHstr.connections.remove(0);
										CHstr.placementDir = StrDirs[k].getOpposite();
										Cte.ChildObjects.add(CHstr);
										TileEntityInfo CHte = (TileEntityInfo) world.getTileEntity(IP[0], IP[1], IP[2]);
										if (CHte != null)
										{
											CHte.Object = CHstr;
										}
										break;
									}
								}
								if (!found)
								{
									noConn = true;
									break;
								}
							} else
							{
								noConn = true;
								break;
							}
						}
						if (noConn)
						{
							partiallyfailed = true;
							if (objs.size() > 1)
							{
								return 1;
							} else return 0;
						}
					}
					
					if (world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]) != null)
					{
						TileEntityInfo te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
						for (int i2 = 0; i2 < te.ChildObjects.size(); i2++)
						{
							Structure Astr = (Structure) te.ChildObjects.get(i2);
							if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
									&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
									&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
							{
								te.ChildObjects.remove(i2);
								break;
							}
						}
					}
					
					int[] Ppos;
					Ppos = IPoint.clone();
					ForgeDirection Ndir = str3.onTurn(str.placementDir, str.placementRotation);
					Ppos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, Ppos, 9);
					Ppos = ForgeDirectionUtils.IncreaseByDir(Ndir, Ppos, 9);
					
					if (Matrix != null)
					{
						if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
						{
							TileEntityInfo te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
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
									ForgeDirection[] dirs = str4.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < 3; i2++)
									{
										ForgeDirection STdir = dirs[i2];
										if (STdir.getOpposite() == Ndir)
										{
											Conect = true;
											break;
										}
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									ForgeDirection[] dirs = str10.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < 2; i2++)
									{
										ForgeDirection STdir = dirs[i2];
										if (STdir.getOpposite() == Ndir) Conect = true;
									}
								}
							}
						}
					}
					IPoint[1] += 3;
					player.setPositionAndUpdate(IPoint[0] + 0.5F, IPoint[1], IPoint[2] + 0.5F);
				}
				
				str3.setRotation(str.placementRotation);
				str3.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				dir = str.placementDir;
				ForgeDirection Ndir = str3.onTurn(dir, str.placementRotation);
				
				Spos = str.placementPos.clone();
				Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 4);
				Spos = ForgeDirectionUtils.IncreaseByDir(Ndir, Spos, 5);
				
				if (!Conect)
				{
					str1.deconstruct(world, Ndir, Spos[0], Spos[1], Spos[2]);
				} else
				{
					Spos = ForgeDirectionUtils.IncreaseByDir(Ndir.getOpposite(), Spos, 1);
					str1.Build(world, Ndir.getOpposite(), Spos[0], Spos[1], Spos[2]);
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "window":
				str6.setRotation(str.placementRotation);
				str6.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				if (str.placementRotation == 1)
				{
					str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				} else
				{
					int[] pos = str.placementPos.clone();
					pos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, pos, 1);
					str6.ClearWay(world, str.placementDir, pos[0], pos[1], pos[2]);
				}
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							TileEntityInfo te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
										&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "solarpanel":
				
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							TileEntityInfo te = prete.get(j);
							
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if (te.Object.getUnlocalizedName() == Structure.BIGHHALL)
								{
									str9.ret = 5;
								} else
								{
									str9.ret = 4;
								}
								if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
										&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				str9.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "crossroad":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				boolean[] ConectT = new boolean[] { false, false, false };
				if (IPoint != null && IPoint.length > 0)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					int[] SIpos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, IPoint.clone(), 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos[0], SIpos[1], SIpos[2]);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = false;
						for (int j = 0; j < Ite.ChildObjects.size(); j++)
						{
							if (Ite.ChildObjects.get(j).connections != null && Ite.ChildObjects.get(j).connections.size() > 0)
							{
								Structure Cstr = Ite.ChildObjects.get(j).connections.get(0);
								Structure CHstr = Ite.ChildObjects.get(j);
								if (CHstr instanceof StructureRotatable) ((StructureRotatable) CHstr).setRotation(CHstr.placementRotation);
								ForgeDirection[] StrDirs = CHstr.getDirs(CHstr.placementDir);
								int[] IP = MatrixHelper.findMatrixPoint(world, CHstr.placementDir, CHstr.placementPos[0], CHstr.placementPos[1], CHstr.placementPos[2]);
								boolean found = false;
								for (int k = 0; k < StrDirs.length; k++)
								{
									int[] IPD = ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP.clone(), 9);
									ForgeDirectionUtils.IncreaseByDir(StrDirs[k], IPD, 9);
									TileEntityInfo Cte = (TileEntityInfo) world.getTileEntity(IPD[0], IPD[1], IPD[2]);
									if (Cte != null && Cstr.getName().equals(Cte.Object.getName()) && Cstr.placementDir == Cte.Object.placementDir
											&& Cstr.placementPos[0] == Cte.Object.placementPos[0] && Cstr.placementPos[1] == Cte.Object.placementPos[1]
											&& Cstr.placementPos[2] == Cte.Object.placementPos[2])
									{
										found = true;
										if (CHstr instanceof StructureBigHall)
										{
											noConn = true;
											break;
										}
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP, 9);
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, CHstr.placementPos, 4);
										ForgeDirectionUtils.IncreaseByDir(StrDirs[k], CHstr.placementPos, 4);
										CHstr.connections.remove(0);
										CHstr.placementDir = StrDirs[k].getOpposite();
										Cte.ChildObjects.add(CHstr);
										TileEntityInfo CHte = (TileEntityInfo) world.getTileEntity(IP[0], IP[1], IP[2]);
										if (CHte != null)
										{
											CHte.Object = CHstr;
										}
										break;
									}
								}
								if (!found)
								{
									noConn = true;
									break;
								}
							} else
							{
								noConn = true;
								break;
							}
						}
						if (noConn)
						{
							partiallyfailed = true;
							if (objs.size() > 1)
							{
								return 1;
							} else return 0;
						}
					}
					
					if (world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]) != null)
					{
						TileEntityInfo te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
						for (int i2 = 0; i2 < te.ChildObjects.size(); i2++)
						{
							Structure Astr = (Structure) te.ChildObjects.get(i2);
							if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
									&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
									&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
							{
								te.ChildObjects.remove(i2);
								break;
							}
						}
					}
					int[] Ppos;
					Ppos = IPoint.clone();
					ForgeDirection[] Ndirs = str4.getDirs(str.placementDir);
					for (int i3 = 0; i3 < 3; i3++)
					{
						Ppos = IPoint.clone();
						Ppos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, Ppos, 9);
						Ppos = ForgeDirectionUtils.IncreaseByDir(Ndirs[i3], Ppos, 9);
						
						if (Matrix != null)
						{
							if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
							{
								TileEntityInfo te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
								if (te != null)
								{
									if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
											&& te.Object.placementDir.getOpposite() == Ndirs[i3])
									{
										ConectT[i3] = true;
									} else if (te.Object.getUnlocalizedName().equals("corner")
											&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndirs[i3])
									{
										ConectT[i3] = true;
									} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
									{
										ForgeDirection[] dirs = str4.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 3; i2++)
										{
											ForgeDirection STdir = dirs[i2];
											if (STdir.getOpposite() == Ndirs[i3])
											{
												ConectT[i3] = true;
												break;
											}
										}
									} else if (te.Object.getUnlocalizedName().equals("thall"))
									{
										str10.setRotation(te.Object.placementRotation);
										ForgeDirection[] dirs = str10.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 2; i2++)
										{
											ForgeDirection STdir = dirs[i2];
											if (STdir.getOpposite() == Ndirs[i3]) ConectT[i3] = true;
										}
									}
								}
							}
						}
					}
					IPoint[1] += 3;
					player.setPositionAndUpdate(IPoint[0] + 0.5F, IPoint[1], IPoint[2] + 0.5F);
				}
				
				str4.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				dir = str.placementDir;
				
				ForgeDirection[] dirs = str4.getDirs(dir);
				
				int x = str.placementPos[0];
				int y = str.placementPos[1];
				int z = str.placementPos[2];
				
				for (int o = 0; o < 3; o++)
				{
					Ndir = dirs[o];
					int[] pos = str4.ChangePosForDir(dir, Ndir, x, y, z);
					
					if (!ConectT[o])
					{
						str1.deconstruct(world, Ndir, pos[0], pos[1], pos[2]);
					} else
					{
						pos = ForgeDirectionUtils.IncreaseByDir(Ndir.getOpposite(), pos, 1);
						str1.Build(world, Ndir.getOpposite(), pos[0], pos[1], pos[2]);
					}
					// str1.ClearWay(world, Ndir, pos[0],pos[1],pos[2]);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "cupola":
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						TileEntityInfo te = prete.get(0);
						player.setPositionAndUpdate(te.xCoord + 0.5F, te.yCoord + 3, te.zCoord + 0.5F);
					}
				}
				str7.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				TileEntityInfo te;
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
										&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "dockport":
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						te = prete.get(0);
						player.setPositionAndUpdate(te.xCoord + 0.5F, te.yCoord + 3, te.zCoord + 0.5F);
					}
				}
				str7.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
										&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "hallairlock":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				Conect = false;
				if (IPoint != null && IPoint.length > 0)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					int[] SIpos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, IPoint.clone(), 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos[0], SIpos[1], SIpos[2]);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = false;
						for (int j = 0; j < Ite.ChildObjects.size(); j++)
						{
							if (Ite.ChildObjects.get(j).connections != null && Ite.ChildObjects.get(j).connections.size() > 0)
							{
								Structure Cstr = Ite.ChildObjects.get(j).connections.get(0);
								Structure CHstr = Ite.ChildObjects.get(j);
								if (CHstr instanceof StructureRotatable) ((StructureRotatable) CHstr).setRotation(CHstr.placementRotation);
								ForgeDirection[] StrDirs = CHstr.getDirs(CHstr.placementDir);
								int[] IP = MatrixHelper.findMatrixPoint(world, CHstr.placementDir, CHstr.placementPos[0], CHstr.placementPos[1], CHstr.placementPos[2]);
								boolean found = false;
								for (int k = 0; k < StrDirs.length; k++)
								{
									int[] IPD = ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP.clone(), 9);
									ForgeDirectionUtils.IncreaseByDir(StrDirs[k], IPD, 9);
									TileEntityInfo Cte = (TileEntityInfo) world.getTileEntity(IPD[0], IPD[1], IPD[2]);
									if (Cte != null && Cstr.getName().equals(Cte.Object.getName()) && Cstr.placementDir == Cte.Object.placementDir
											&& Cstr.placementPos[0] == Cte.Object.placementPos[0] && Cstr.placementPos[1] == Cte.Object.placementPos[1]
											&& Cstr.placementPos[2] == Cte.Object.placementPos[2])
									{
										found = true;
										if (CHstr instanceof StructureBigHall)
										{
											noConn = true;
											break;
										}
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP, 9);
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, CHstr.placementPos, 4);
										ForgeDirectionUtils.IncreaseByDir(StrDirs[k], CHstr.placementPos, 4);
										CHstr.connections.remove(0);
										CHstr.placementDir = StrDirs[k].getOpposite();
										Cte.ChildObjects.add(CHstr);
										TileEntityInfo CHte = (TileEntityInfo) world.getTileEntity(IP[0], IP[1], IP[2]);
										if (CHte != null)
										{
											CHte.Object = CHstr;
										}
										break;
									}
								}
								if (!found)
								{
									noConn = true;
									break;
								}
							} else
							{
								noConn = true;
								break;
							}
						}
						if (noConn)
						{
							partiallyfailed = true;
							if (objs.size() > 1)
							{
								return 1;
							} else return 0;
						}
					}
					
					if (world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]) != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
						for (int i2 = 0; i2 < te.ChildObjects.size(); i2++)
						{
							Structure Astr = (Structure) te.ChildObjects.get(i2);
							if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
									&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
									&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
							{
								te.ChildObjects.remove(i2);
								break;
							}
						}
					}
					int[] Ppos;
					Ppos = IPoint.clone();
					Ppos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, Ppos, 18);
					
					if (Matrix != null)
					{
						if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
						{
							te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
							if (te != null)
							{
								if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
										&& te.Object.placementDir.getOpposite() == str.placementDir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("corner")
										&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == str.placementDir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
								{
									dirs = str4.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < 3; i2++)
									{
										ForgeDirection STdir = dirs[i2];
										if (STdir.getOpposite() == str.placementDir)
										{
											Conect = true;
											break;
										}
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									dirs = str10.getDirs(te.Object.placementDir);
									for (int i2 = 0; i2 < 2; i2++)
									{
										ForgeDirection STdir = dirs[i2];
										if (STdir.getOpposite() == str.placementDir) Conect = true;
									}
								}
							}
						}
					}
					IPoint[1] += 3;
					player.setPositionAndUpdate(IPoint[0] + 0.5F, IPoint[1], IPoint[2] + 0.5F);
				}
				str2.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				dir = str.placementDir;
				
				Spos = str.placementPos.clone();
				Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 9);
				if (!Conect)
				{
					str1.deconstruct(world, dir, Spos[0], Spos[1], Spos[2]);
				} else
				{
					Spos = ForgeDirectionUtils.IncreaseByDir(dir.getOpposite(), Spos, 1);
					str1.Build(world, dir.getOpposite(), Spos[0], Spos[1], Spos[2]);
				}
				
				Spos = str.placementPos.clone();
				Spos = ForgeDirectionUtils.IncreaseByDir(dir, Spos, 4);
				Spos = ForgeDirectionUtils.IncreaseByDir(ForgeDirectionUtils.turnClockwise(dir), Spos, 1);
				
				world.setBlock(Spos[0], Spos[1], Spos[2], Blocks.air, 0, 2);
				Spos = ForgeDirectionUtils.IncreaseByDir(ForgeDirectionUtils.turnAgainstClockwise(dir), Spos, 2);
				world.setBlock(Spos[0], Spos[1], Spos[2], Blocks.air, 0, 2);
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "thall":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				ConectT = new boolean[] { false, false };
				if (IPoint != null && IPoint.length > 0)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					int[] SIpos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, IPoint.clone(), 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos[0], SIpos[1], SIpos[2]);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = false;
						for (int j = 0; j < Ite.ChildObjects.size(); j++)
						{
							if (Ite.ChildObjects.get(j).connections != null && Ite.ChildObjects.get(j).connections.size() > 0)
							{
								Structure Cstr = Ite.ChildObjects.get(j).connections.get(0);
								Structure CHstr = Ite.ChildObjects.get(j);
								if (CHstr instanceof StructureRotatable) ((StructureRotatable) CHstr).setRotation(CHstr.placementRotation);
								ForgeDirection[] StrDirs = CHstr.getDirs(CHstr.placementDir);
								int[] IP = MatrixHelper.findMatrixPoint(world, CHstr.placementDir, CHstr.placementPos[0], CHstr.placementPos[1], CHstr.placementPos[2]);
								boolean found = false;
								for (int k = 0; k < StrDirs.length; k++)
								{
									int[] IPD = ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP.clone(), 9);
									ForgeDirectionUtils.IncreaseByDir(StrDirs[k], IPD, 9);
									TileEntityInfo Cte = (TileEntityInfo) world.getTileEntity(IPD[0], IPD[1], IPD[2]);
									if (Cte != null && Cstr.getName().equals(Cte.Object.getName()) && Cstr.placementDir == Cte.Object.placementDir
											&& Cstr.placementPos[0] == Cte.Object.placementPos[0] && Cstr.placementPos[1] == Cte.Object.placementPos[1]
											&& Cstr.placementPos[2] == Cte.Object.placementPos[2])
									{
										found = true;
										if (CHstr instanceof StructureBigHall)
										{
											noConn = true;
											break;
										}
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, IP, 9);
										ForgeDirectionUtils.IncreaseByDir(CHstr.placementDir, CHstr.placementPos, 4);
										ForgeDirectionUtils.IncreaseByDir(StrDirs[k], CHstr.placementPos, 4);
										CHstr.connections.remove(0);
										CHstr.placementDir = StrDirs[k].getOpposite();
										Cte.ChildObjects.add(CHstr);
										TileEntityInfo CHte = (TileEntityInfo) world.getTileEntity(IP[0], IP[1], IP[2]);
										if (CHte != null)
										{
											CHte.Object = CHstr;
										}
										break;
									}
								}
								if (!found)
								{
									noConn = true;
									break;
								}
							} else
							{
								noConn = true;
								break;
							}
						}
						if (noConn)
						{
							partiallyfailed = true;
							if (objs.size() > 1)
							{
								return 1;
							} else return 0;
						}
					}
					
					if (world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]) != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
						for (int i2 = 0; i2 < te.ChildObjects.size(); i2++)
						{
							Structure Astr = (Structure) te.ChildObjects.get(i2);
							if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
									&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
									&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
							{
								te.ChildObjects.remove(i2);
								break;
							}
						}
					}
					int[] Ppos;
					Ppos = IPoint.clone();
					str10.setRotation(str.placementRotation);
					ForgeDirection[] Ndirs = str10.getDirs(str.placementDir);
					for (int i3 = 0; i3 < 2; i3++)
					{
						Ppos = IPoint.clone();
						Ppos = ForgeDirectionUtils.IncreaseByDir(str.placementDir, Ppos, 9);
						Ppos = ForgeDirectionUtils.IncreaseByDir(Ndirs[i3], Ppos, 9);
						
						if (Matrix != null)
						{
							if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
							{
								te = (TileEntityInfo) world.getTileEntity(Ppos[0], Ppos[1], Ppos[2]);
								if (te != null)
								{
									if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
											&& te.Object.placementDir.getOpposite() == Ndirs[i3])
									{
										ConectT[i3] = true;
									} else if (te.Object.getUnlocalizedName().equals("corner")
											&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndirs[i3])
									{
										ConectT[i3] = true;
									} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
									{
										dirs = str4.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 3; i2++)
										{
											ForgeDirection STdir = dirs[i2];
											if (STdir.getOpposite() == Ndirs[i3])
											{
												ConectT[i3] = true;
												break;
											}
										}
									} else if (te.Object.getUnlocalizedName().equals("thall"))
									{
										str10.setRotation(te.Object.placementRotation);
										dirs = str10.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 2; i2++)
										{
											ForgeDirection STdir = dirs[i2];
											if (STdir.getOpposite() == Ndirs[i3]) ConectT[i3] = true;
										}
									}
								}
							}
						}
					}
					IPoint[1] += 3;
					player.setPositionAndUpdate(IPoint[0] + 0.5F, IPoint[1], IPoint[2] + 0.5F);
				}
				
				str10.setRotation(str.placementRotation);
				str10.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				dir = str.placementDir;
				dirs = str10.getDirs(dir);
				
				x = str.placementPos[0];
				y = str.placementPos[1];
				z = str.placementPos[2];
				
				for (int o = 0; o < 2; o++)
				{
					Ndir = dirs[o];
					int[] pos = str4.ChangePosForDir(dir, Ndir, x, y, z);
					
					if (!ConectT[o])
					{
						str1.deconstruct(world, Ndir, pos[0], pos[1], pos[2]);
					} else
					{
						pos = ForgeDirectionUtils.IncreaseByDir(Ndir.getOpposite(), pos, 1);
						str1.Build(world, Ndir.getOpposite(), pos[0], pos[1], pos[2]);
					}
					// str1.ClearWay(world, Ndir, pos[0],pos[1],pos[2]);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			
			case "bighall":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				ConectT = new boolean[] { false, false, false, false, false, false, false };
				if (IPoint != null && IPoint.length > 0)
				{
					Matrix = new HashMap<Integer, int[]>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					if (world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]) != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint[0], IPoint[1], IPoint[2]);
						for (int i2 = 0; i2 < te.ChildObjects.size(); i2++)
						{
							Structure Astr = (Structure) te.ChildObjects.get(i2);
							if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
									&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
									&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
							{
								te.ChildObjects.remove(i2);
								break;
							}
						}
					}
					int[] Ppos;
					Ppos = IPoint.clone();
					str11.setRotation(str.placementRotation);
					ForgeDirection[] Ndirs = str11.getDirs(str.placementDir);
					
					x = str.placementPos[0];
					y = str.placementPos[1];
					z = str.placementPos[2];
					
					List<int[]> posT = str11.getPos(str.placementDir, Ndirs, x, y, z);
					Iterator<int[]> posI = posT.iterator();
					
					for (int i3 = 0; i3 < Ndirs.length; i3++)
					{
						int[] pos;
						if (posI.hasNext()) pos = posI.next();
						else pos = new int[3];
						
						pos = ForgeDirectionUtils.IncreaseByDir(Ndirs[i3], pos, 4);
						
						if (Matrix != null)
						{
							if (MatrixHelper.FindPointInMatrix(Matrix, new int[] { pos[0], pos[1] - 3, pos[2] }) != null)
							{
								te = (TileEntityInfo) world.getTileEntity(pos[0], pos[1] - 3, pos[2]);
								if (te != null)
								{
									if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
											&& te.Object.placementDir.getOpposite() == Ndirs[i3])
									{
										ConectT[i3] = true;
									} else if (te.Object.getUnlocalizedName().equals("corner")
											&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndirs[i3])
									{
										ConectT[i3] = true;
									} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
									{
										dirs = str4.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 3; i2++)
										{
											ForgeDirection STdir = dirs[i2];
											if (STdir.getOpposite() == Ndirs[i3])
											{
												ConectT[i3] = true;
												break;
											}
										}
									} else if (te.Object.getUnlocalizedName().equals("thall"))
									{
										str10.setRotation(te.Object.placementRotation);
										dirs = str10.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < 2; i2++)
										{
											ForgeDirection STdir = dirs[i2];
											if (STdir.getOpposite() == Ndirs[i3]) ConectT[i3] = true;
										}
									}
								}
							}
						}
					}
					IPoint[1] += 3;
					player.setPositionAndUpdate(IPoint[0] + 0.5F, IPoint[1], IPoint[2] + 0.5F);
				}
				str11.setRotation(str.placementRotation);
				str11.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				dir = str.placementDir;
				
				dirs = str11.getDirs(dir);
				
				x = str.placementPos[0];
				y = str.placementPos[1];
				z = str.placementPos[2];
				
				List<int[]> posT = str11.getPos(dir, dirs, x, y, z);
				Iterator<int[]> posI = posT.iterator();
				
				for (int o = 0; o < dirs.length; o++)
				{
					Ndir = dirs[o];
					int[] pos;
					if (posI.hasNext()) pos = posI.next();
					else pos = new int[3];
					
					if (!ConectT[o])
					{
						str1.deconstruct(world, Ndir, pos[0], pos[1], pos[2]);
					} else
					{
						pos = ForgeDirectionUtils.IncreaseByDir(Ndir.getOpposite(), pos, 1);
						str1.Build(world, Ndir.getOpposite(), pos[0], pos[1], pos[2]);
					}
					// str1.ClearWay(world, Ndir, pos[0],pos[1],pos[2]);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "greenhouse":
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						te = prete.get(0);
						player.setPositionAndUpdate(te.xCoord + 0.5F, te.yCoord + 3, te.zCoord + 0.5F);
					}
				}
				str12.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
										&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			case "pierce":
				str13.deconstruct(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				str1.Build(world, str.placementDir, str.placementPos[0], str.placementPos[1], str.placementPos[2]);
				
				if (world.getTileEntity(Ipos[0], Ipos[1], Ipos[2]) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos[0], Ipos[1], Ipos[2])).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos[0] == str.placementPos[0] && Astr.placementPos[1] == str.placementPos[1] && Astr.placementPos[2] == str.placementPos[2])
										&& Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.delayBeforeCanPickup = 0;
						world.spawnEntityInWorld(item);
					}
					return 2;
				}
				break;
			}
		}
		
		for (int k = 0; k < afterI.size(); k++)
		{
			ItemStack curr = afterI.get(k);
			EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
			item.delayBeforeCanPickup = 0;
			world.spawnEntityInWorld(item);
		}
		return 0;
	}
	
}
