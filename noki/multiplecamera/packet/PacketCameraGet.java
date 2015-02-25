package noki.multiplecamera.packet;

import noki.multiplecamera.MultipleCameraCore;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;


/**********
 * @class PacketCameraGet
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraGet implements IMessage {

	//******************************//
	// define member variables.
	//******************************//
	public ByteBuf data;

	
	//******************************//
	// define member methods.
	//******************************//
	public PacketCameraGet() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		MultipleCameraCore.log("PacketCameraGet, fromBytes.");
		this.data = buf;
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {

		MultipleCameraCore.log("PacketCameraGet, toBytes.");

	}

}
