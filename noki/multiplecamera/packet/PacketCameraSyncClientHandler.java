package noki.multiplecamera.packet;

import noki.multiplecamera.camera.CameraManagerClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**********
 * @class PacketCameraSyncClientHandler
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraSyncClientHandler implements IMessageHandler<PacketCameraSyncClient, IMessage> {

	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public IMessage onMessage(PacketCameraSyncClient message, MessageContext ctx) {
		
		CameraManagerClient.syncCamera(message.id, message.dimensionId,
				message.posX, message.posY, message.posZ, message.yaw, message.pitch);
		return null;
		
	}

}
