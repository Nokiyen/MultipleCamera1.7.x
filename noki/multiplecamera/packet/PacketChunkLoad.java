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
public class PacketChunkLoad implements IMessage {

	//******************************//
	// define member variables.
	//******************************//
	public ByteBuf data;
	
	public int dimensionId;
	public int chunkPosX;
	public int chunkPosZ;
	public int renderDistance;
	public UUID uuid;

	
	//******************************//
	// define member methods.
	//******************************//
	public PacketChunkLoad() {
		
	}
	
	public PacketChunkLoad(int dimensionId, int chunkPosX, int chunkPosZ, int renderDistance, UUID uuid) {
		
		this.dimensionId = dimensionId;
		this.chunkPosX = chunkPosX;
		this.chunkPosZ = chunkPosZ;
		this.renderDistance = renderDistance;
		this.uuid = uuid;
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		this.data = buf;
		this.dimensionId = buf.readInt();
		this.chunkPosX = buf.readInt();
		this.chunkPosZ = buf.readInt();
		this.renderDistance = buf.readInt();
		
		int length = buf.readInt();
		char[] chars = new char[length];
		for(int i=0; i<length; i++) {
			chars[i] = buf.readChar();
		}
		this.uuid = UUID.fromString(new String(chars));
		
		MultipleCameraCore.log("dimensionId is %s.", this.dimensionId);
		MultipleCameraCore.log("chunkPosX is %s.", this.chunkPosX);
		MultipleCameraCore.log("chunkPosZ is %s.", this.chunkPosZ);
		MultipleCameraCore.log("renderDistance is %s.", this.renderDistance);
		MultipleCameraCore.log("uuid is %s.", this.uuid.toString());
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		
		buf.writeInt(this.dimensionId);
		buf.writeInt(this.chunkPosX);
		buf.writeInt(this.chunkPosZ);
		buf.writeInt(this.renderDistance);
		
		String uuidString = this.uuid.toString();
		int length = uuidString.length();
		buf.writeInt(length);
		for(int i=0; i<length; i++) {
			buf.writeChar(uuidString.charAt(i));
		}
		
		MultipleCameraCore.log("dimensionId is %s.", this.dimensionId);
		MultipleCameraCore.log("chunkPosX is %s.", this.chunkPosX);
		MultipleCameraCore.log("chunkPosZ is %s.", this.chunkPosZ);
		MultipleCameraCore.log("renderDistance is %s.", this.renderDistance);
		MultipleCameraCore.log("uuid is %s.", this.uuid.toString());
		
	}

}
