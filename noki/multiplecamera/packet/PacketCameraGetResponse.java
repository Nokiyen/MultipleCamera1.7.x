package noki.multiplecamera.packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import noki.multiplecamera.MultipleCameraCore;
import noki.multiplecamera.camera.CameraManagerServer;
import noki.multiplecamera.camera.EntityCamera;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;


/**********
 * @class PacketCameraGetResponse
 *
 * @description
 * @description_en
 * 
 * @see
 */
public class PacketCameraGetResponse implements IMessage {

	//******************************//
	// define member variables.
	//******************************//
	public ByteBuf data;
	public HashMap<Integer, EntityCamera> cameras;
	public ArrayList<EntityCamera> orderedCameras;

	
	//******************************//
	// define member methods.
	//******************************//
	public PacketCameraGetResponse() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
		MultipleCameraCore.log("PacketCameraGetResponse, fromBytes.");
		
		this.data = buf;
		this.cameras = new HashMap<Integer, EntityCamera>();
		int times = buf.readInt();
		
		for(int i=0; i < times; i++) {
			int dimensionId = buf.readInt();
//			World world = Minecraft.getMinecraft().theWorld;
			double posX = buf.readDouble();
			double posY = buf.readDouble();
			double posZ = buf.readDouble();			
			float yaw = buf.readFloat();
			float pitch = buf.readFloat();
			boolean isTurtle = buf.readBoolean();
			int id = buf.readInt();
			
			World world = MultipleCameraCore.proxy.getClientWorld();
			MultipleCameraCore.log("player dimensionId is %s.", world.provider.dimensionId);
			MultipleCameraCore.log("camera dimensionId is %s.", dimensionId);
			if(world == null || world.provider.dimensionId != dimensionId) {
				continue;
			}
//			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			EntityPlayer player = MultipleCameraCore.proxy.getClientPlayer();
			if(player == null) {
				continue;
			}
//			int range = Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
			int chunkX = (int)Math.floor(posX) >> 4;
			int chunkZ = (int)Math.floor(posZ) >> 4;
			int range = MultipleCameraCore.proxy.getRenderDistanceChunks();
/*			if(chunkX < player.chunkCoordX-range || player.chunkCoordX+range < chunkX
					|| chunkZ < player.chunkCoordZ-range || player.chunkCoordZ+range < chunkZ) {
				continue;
			}*/
			
			this.cameras.put(id, new EntityCamera(world, posX, posY, posZ, yaw, pitch, null, isTurtle));
		}
		
		this.orderedCameras = new ArrayList<EntityCamera>(this.cameras.values());
		
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		
		MultipleCameraCore.log("PacketCameraGetResponse, toBytes.");
		
		HashMap<Integer, EntityCamera> cameras = CameraManagerServer.getCameras();
		
		buf.writeInt(cameras.size());
		MultipleCameraCore.log("camera size is %s.", cameras.size());
		for(Map.Entry<Integer, EntityCamera> each: cameras.entrySet()) {
			buf.writeInt(each.getValue().worldObj.provider.dimensionId);
			MultipleCameraCore.log("dimensionId, toBytes() is %s.", each.getValue().worldObj.provider.dimensionId);
			buf.writeDouble(each.getValue().posX);
			buf.writeDouble(each.getValue().posY);
			buf.writeDouble(each.getValue().posZ);
			buf.writeFloat(each.getValue().rotationYaw);
			buf.writeFloat(each.getValue().rotationPitch);
			buf.writeBoolean(each.getValue().isTurtle);
			buf.writeInt(each.getKey());
		}
		
	}

}