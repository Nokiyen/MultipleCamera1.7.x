package noki.multiplecamera.packet;

import noki.multiplecamera.ModInfo;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;


/**********
 * @class PacketHandler
 *
 * @description
 * @description_en
 * 
 * @see noki.multiplecamera.packet.*
 */
public class PacketHandler {
	
	//******************************//
	// define member variables.
	//******************************//
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.ID);
	
	
	//******************************//
	// define member methods.
	//******************************//
	public static void registerPackets() {
		
		instance.registerMessage(PacketCameraGetHandler.class, PacketCameraGet.class, 1, Side.SERVER);
		instance.registerMessage(PacketCameraGetResponseHandler.class, PacketCameraGetResponse.class, 2, Side.CLIENT);
		instance.registerMessage(PacketCameraSyncClientHandler.class, PacketCameraSyncClient.class, 3, Side.CLIENT);
		instance.registerMessage(PacketChunkLoadHandler.class, PacketChunkLoad.class, 4, Side.SERVER);
		instance.registerMessage(PacketChunkLoadResponseHandler.class, PacketChunkLoadResponse.class, 5, Side.CLIENT);
		
	}

}
