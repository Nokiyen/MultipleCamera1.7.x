package noki.multiplecamera.packet;

import noki.multiplecamera.MultipleCameraCore;
import noki.multiplecamera.camera.CameraManagerClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**********
 * @class PacketChunkLoadHandler
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketChunkLoadResponseHandler implements IMessageHandler<PacketChunkLoadResponse, IMessage> {

	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public IMessage onMessage(PacketChunkLoadResponse message, MessageContext ctx) {
		
		MultipleCameraCore.log("on message of PacketChunkLoadResponseHandler.");
		CameraManagerClient.loadRenderers();
		
		return null;
		
	}

}
