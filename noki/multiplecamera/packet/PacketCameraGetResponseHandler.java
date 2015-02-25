package noki.multiplecamera.packet;

import noki.multiplecamera.MultipleCameraCore;
import noki.multiplecamera.camera.CameraManagerClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;


/**********
 * @class PacketCameraGetResponseHandler
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraGetResponseHandler implements IMessageHandler<PacketCameraGetResponse, IMessage> {

	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public IMessage onMessage(PacketCameraGetResponse message, MessageContext ctx) {
		
		MultipleCameraCore.log("PacketCameraGetResponseHandler, onMessage.");
		CameraManagerClient.setClientCameras(message.cameras);
		CameraManagerClient.setOrderedClientCameras(message.orderedCameras);
		if(message.cameras.size() != 0) {
			MultipleCameraCore.log("camera exists.");
			CameraManagerClient.setClientId(0);
			CameraManagerClient.setViewCamera(message.orderedCameras.get(0));
		}
		else {
			MultipleCameraCore.log("camera doesn't exist.");
			CameraManagerClient.setClientId(-1);
		}
		
		return null;
		
	}

}
