package noki.multiplecamera.packet;

import java.util.UUID;

import noki.multiplecamera.MultipleCameraCore;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;


/**********
 * @class PacketChunkLoad
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketChunkLoadResponse implements IMessage {

	//******************************//
	// define member variables.
	//******************************//
	public ByteBuf data;

	
	//******************************//
	// define member methods.
	//******************************//
	public PacketChunkLoadResponse() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		this.data = buf;
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
				
	}

}
