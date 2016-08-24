package net.glider.src.dimensions;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldChunkManagerSpace;
import micdoodle8.mods.galacticraft.core.world.gen.BiomeGenBaseOrbit;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldChunkManagerOrbitModif extends WorldChunkManagerSpace
{
    @Override
    public BiomeGenBase getBiome()
    {
        return BiomeGenBaseOrbit.space;
    }
}
