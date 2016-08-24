package net.glider.src.handlers;

import micdoodle8.mods.galacticraft.core.client.KeyHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.glider.src.entity.EntityRocketFakeTiered;
import net.glider.src.entity.ExtendedPlayer;
import net.glider.src.entity.EntityRocketFakeTiered.EnumLaunchPhase;
import net.glider.src.items.ItemMod;
import net.glider.src.items.ItemSpaceJetpack;
import net.glider.src.network.PacketHandler;
import net.glider.src.network.packets.DismountPacket;
import net.glider.src.network.packets.OpenRocketFuelGuiPacket;
import net.glider.src.renderer.animations.AnimationHandlerJetpack;
import net.glider.src.utils.GliderModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.gameevent.TickEvent.Type;

public class KeyHandlerClient extends KeyHandler
{
  //  public static KeyBinding galaxyMap;
    public static KeyBinding openFuelGui;
    public static KeyBinding TestAnim;
    
    private int updates = 0;
    
    static
    {
    	Minecraft mc = Minecraft.getMinecraft();
     //   galaxyMap = new KeyBinding(GCCoreUtil.translate("keybind.map.name"), ConfigManagerCore.keyOverrideMapI == 0 ? Keyboard.KEY_M : ConfigManagerCore.keyOverrideMapI, Constants.MOD_NAME_SIMPLE);
        openFuelGui = micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient.openFuelGui;
     
         TestAnim = new KeyBinding(GCCoreUtil.translate("Jetpack Toggle"), Keyboard.KEY_H, GliderModInfo.MOD_NAME);
         
        //  toggleAdvGoggles = new KeyBinding(GCCoreUtil.translate("keybind.sensortoggle.name"), ConfigManagerCore.keyOverrideToggleAdvGogglesI == 0 ? Keyboard.KEY_K : ConfigManagerCore.keyOverrideToggleAdvGogglesI, Constants.MOD_NAME_SIMPLE);
        // See ConfigManagerCore.class for actual defaults. These do nothing
    }
    //ClientRegistry.registerKeyBinding(KeyHandlerClient.openFuelGui);
    public static KeyBinding accelerateKey;
    public static KeyBinding decelerateKey;
    public static KeyBinding leftKey;
    public static KeyBinding rightKey;
    public static KeyBinding upKey;
    public static KeyBinding downKey;
    public static KeyBinding spaceKey;
    public static KeyBinding leftShiftKey;
    private static Minecraft mc = Minecraft.getMinecraft();

    public KeyHandlerClient()
    {
        super(new KeyBinding[] {openFuelGui,TestAnim}, new boolean[] {false,false},  getVanillaKeyBindings(), new boolean[] { false, true, true, true, true, true, true });
    }

    private static KeyBinding[] getVanillaKeyBindings()
    {
        KeyBinding invKey =  mc.gameSettings.keyBindInventory;
         accelerateKey =  mc.gameSettings.keyBindForward;
         decelerateKey =  mc.gameSettings.keyBindBack;
         leftKey =  mc.gameSettings.keyBindLeft;
         rightKey =  mc.gameSettings.keyBindRight;
         upKey =  mc.gameSettings.keyBindForward;
         downKey =  mc.gameSettings.keyBindBack;
         spaceKey =  mc.gameSettings.keyBindJump;
         leftShiftKey =  mc.gameSettings.keyBindSneak;
        return new KeyBinding[] { invKey,  accelerateKey,  decelerateKey,  leftKey,  rightKey,  spaceKey,  leftShiftKey };
    }

    @Override
    public void keyDown(Type types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
    {
        if ( mc.thePlayer != null && tickEnd)
        {
            EntityClientPlayerMP playerBase = PlayerUtil.getPlayerBaseClientFromPlayer( mc.thePlayer, false);
            
            if (playerBase == null)
            {
            	return;
            }
            
            GCPlayerStatsClient stats = GCPlayerStatsClient.get(playerBase);

            if (kb.getKeyCode() ==  openFuelGui.getKeyCode())
            {
                if (playerBase.ridingEntity instanceof EntityRocketFakeTiered)
                {
                	EntityRocketFakeTiered rocket = (EntityRocketFakeTiered) playerBase.ridingEntity;
                	if (rocket.launchPhase == EnumLaunchPhase.DOCKED.ordinal())
                	{
                		PacketHandler.sendToServer(new OpenRocketFuelGuiPacket(playerBase.ridingEntity.getEntityId()));
                	}
                }
			} else if (kb.getKeyCode() == TestAnim.getKeyCode() && playerBase.getCurrentArmor(2) != null && playerBase.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack)
            {
            	ItemSpaceJetpack jetpack = (ItemSpaceJetpack) playerBase.getCurrentArmor(2).getItem();
            	ExtendedPlayer prop = ExtendedPlayer.get(playerBase);
            	
            	if (prop.getAnimationHandler().isAnimationActive("Enabled idle"))
            	{
            		jetpack.setActive(false);
            		((AnimationHandlerJetpack)prop.getAnimationHandler()).activateAnimation("Disable", 0,true);
            	}else if (prop.getAnimationHandler().isAnimationActive("Disabled idle"))
            	{
            		jetpack.setActive(true);
            		((AnimationHandlerJetpack)prop.getAnimationHandler()).activateAnimation("Enable", 0,true);
            	}else if (prop.getAnimationHandler().animCurrentChannels.size() == 0)
            	{
            		jetpack.setActive(true);
            		((AnimationHandlerJetpack)prop.getAnimationHandler()).activateAnimation("Enable", 0,true);
            	}
            }
        }

        if ( mc.thePlayer != null &&  mc.currentScreen == null)
        {
            int keyNum  = -1;

            if (kb ==  accelerateKey)
            {
                keyNum = 0;
            }
            else if (kb ==  decelerateKey)
            {
            	keyNum = 1;
            }
            else if (kb ==  leftKey)
            {
            	keyNum = 2;
            }
            else if (kb ==  rightKey)
            {
            	keyNum = 3;
            }
            else if (kb ==  spaceKey)
            {
            	keyNum = 4;
            }
            else if (kb ==  leftShiftKey)
            {
            	keyNum = 5;
            }

            Entity entityTest =  mc.thePlayer.ridingEntity;
            if (entityTest != null && entityTest instanceof EntityRocketFakeTiered && keyNum == 5)
            {
                EntityRocketFakeTiered entity = (EntityRocketFakeTiered) entityTest;

                if (kb.getKeyCode() ==  mc.gameSettings.keyBindInventory.getKeyCode())
                {
                    KeyBinding.setKeyBindState( mc.gameSettings.keyBindInventory.getKeyCode(), false);
                }

               PacketHandler.sendToServer(new DismountPacket());
            }/*else if (mc.thePlayer.getCurrentArmor(2).getItem() == ItemMod.spaceJetpack && keyNum != -1)
            {
            	ItemSpaceJetpack jetpack = (ItemSpaceJetpack) mc.thePlayer.getCurrentArmor(2).getItem();
            	if (jetpack.KeyPressed != keyNum)
            	{
            	jetpack.KeyPressed = keyNum;
            	jetpack.markDirty();
            	}
            }*/
        }
    }

    @Override
    public void keyUp(Type types, KeyBinding kb, boolean tickEnd)
    {
    }
}