package net.glider.src.handlers.hooks;

import net.glider.src.utils.GLoger;
import gloomyfolken.hooklib.asm.AsmHook;
import gloomyfolken.hooklib.minecraft.HookLoader;
import gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;

public class GliderHookLoader extends HookLoader {
	//-Dfml.coreMods.load=net.glider.src.handlers.hooks.GliderHookLoader
	
	@Override
	public String[] getASMTransformerClass() {
	     return new String[]{PrimaryClassTransformer.class.getName()};
	}
	
    @Override
    public void registerHooks() {
    	GLoger.logInfo("Starting injecting hooks in GalactiCraft.");
        registerHookContainer("net.glider.src.handlers.hooks.Hooks"); //FreefallHook
        
        GLoger.logInfo("Replacing GC class FreefallHandler.");
        registerHookContainer("net.glider.src.handlers.hooks.FreefallHook"); 
    }
}
