package net.glider.src.strucures;

import net.minecraftforge.common.util.ForgeDirection;

public abstract class StructureRotatable extends Structure {



	public StructureRotatable(boolean hidden) {
		super(hidden);
	}
	/**
	 * want to count ignoring ascending order ? you can
	 * @param nowV -current value
	 * @param meta 0 - everything, 1 - everything excluding pierce, 2 - only add
	 * structures, 3 - only window(only rot == 0), 4 - solar panels, 5 -
	 * greenhouse, 6 - pierce
	 */
	public int nextPossibleValue(int nowV, ForgeDirection dir, int meta)
	{
		for (int i = nowV + 1; i < 5; i++)
		{
			if (isPossible(dir, i, meta))
			{
				return i;
			}
		}
		for (int i = 0; i < 4; i++)
		{
			if (isPossible(dir, i, meta))
			{
				return i;
			}
		}
		if (isPossible(dir, nowV + 1, meta))
		{
			return nowV++;
		} else
			return nowV;
	}
	
	
	public abstract void setRotation(int rot);
	
	public abstract int getRotation();
	/**
	@param meta 0 - everything, 1 - everything excluding pierce, 2 - only add
	 * structures, 3 - only window(only rot == 0), 4 - solar panels, 5 -
	 * greenhouse, 6 - pierce
	 */
	public abstract boolean isPossible(ForgeDirection dir, int rot, int meta);

}
