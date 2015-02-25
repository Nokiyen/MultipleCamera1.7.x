package noki.multiplecamera;

import noki.multiplecamera.packet.PacketHandler;
import noki.multiplecamera.proxy.ProxyCommon;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;


/**********
 * @ModName MultipleCameraCore
 * 
 * @description
 * 
 * @caution ここはコアファイルなので、原則、具体的な処理をしないよう気を付ける。
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCY)
public class MultipleCameraCore {
	
	//******************************//
	// define member variables.
	//******************************//
	//	core.
	@Instance(value = ModInfo.ID)
	public static MultipleCameraCore instance;
	@Metadata
	public static ModMetadata metadata;	//	extract from mcmod.info file, not java internal coding.
	@SidedProxy(clientSide = ModInfo.PROXY_LOCATION+"ProxyClient", serverSide = ModInfo.PROXY_LOCATION+"ProxyServer")
	public static ProxyCommon proxy;
	
	public static VersionInfo versionInfo;
	public static boolean debugFlag = true;

	
	//******************************//
	// define member methods.
	//******************************//
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
//		MultipleCameraData.getConfig(event);
//		MultipleCameraPacketHandler.registerPre();
		MultipleCameraData.setModData(event);
		versionInfo = new VersionInfo(metadata.modId.toLowerCase(), metadata.version, metadata.updateUrl);
		
		MultipleCameraData.registerBlocks();
		MultipleCameraData.registerItems();
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerEvents();
		PacketHandler.registerPackets();
		MultipleCameraData.registerRecipes();
		MultipleCameraData.registerCC();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		//	nothing to do.
		proxy.getTimer();
		
	}
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		
		versionInfo.notifyUpdate(Side.SERVER);
		
	}
	
	//----------
	//Static Method.
	//----------
	public static void log(String message, Object... data) {
		
		if(debugFlag) {
			FMLLog.fine("[MultipleCamera:LOG] " + message, data);
		}
		
	}

}
