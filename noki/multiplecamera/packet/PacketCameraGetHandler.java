package noki.multiplecamera.packet;

import noki.multiplecamera.MultipleCameraCore;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**********
 * @class PacketCameraGetHandler
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraGetHandler implements IMessageHandler<PacketCameraGet, PacketCameraGetResponse> {

	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public PacketCameraGetResponse onMessage(PacketCameraGet message, MessageContext ctx) {
		
		MultipleCameraCore.log("PacketCameraGetHandler, onMessage.");
		return new PacketCameraGetResponse();
		
	}

}
