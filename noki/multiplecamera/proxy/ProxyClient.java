package noki.multiplecamera.proxy;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import noki.multiplecamera.MultipleCameraData;
import noki.multiplecamera.event.EventJoinWorld;
import noki.multiplecamera.event.EventRenderScreen;
import noki.multiplecamera.gui.GuiViewer;


/**********
 * @class ProxyClient
 *
 * @description クライアント用proxyクラス。
 * @description_en Proxy class for Client.
 */
public class ProxyClient implements ProxyCommon {
	
	//******************************//
	// define member variables.
	//******************************//


	//******************************//
	// define member methods.
	//******************************//
	@Override
	public void registerEvents() {
				
		MinecraftForge.EVENT_BUS.register(new EventRenderScreen());
		MinecraftForge.EVENT_BUS.register(new EventJoinWorld());
		
	}
	
	@Override
	public World getClientWorld() {
		
		return Minecraft.getMinecraft().theWorld;
		
	}
	
	@Override
	public EntityPlayer getClientPlayer() {
		
		return Minecraft.getMinecraft().thePlayer;
		
	}
	
	@Override
	public int getRenderDistanceChunks() {
		
		return Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
		
	}
	
	@Override
	public void openItemClientGui(EntityPlayer player) {
		
		if(Minecraft.getMinecraft().currentScreen instanceof GuiViewer) {
			return;
		}
		Minecraft.getMinecraft().displayGuiScreen(new GuiViewer(player));
		
	}
	
	@Override
	public void getTimer() {
		
		Object obj = 
				ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "timer", "field_71428_T");
		if(obj instanceof Timer) {
			MultipleCameraData.timer = (Timer)obj;
		}

	}
	
}
