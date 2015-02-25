package noki.multiplecamera.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import noki.multiplecamera.MultipleCameraCore;
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
public class PacketChunkLoadHandler implements IMessageHandler<PacketChunkLoad, PacketChunkLoadResponse> {

	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public PacketChunkLoadResponse onMessage(PacketChunkLoad message, MessageContext ctx) {
		
		WorldServer world = MinecraftServer.getServer().worldServerForDimension(message.dimensionId);
		NetHandlerPlayServer handler = ((EntityPlayerMP)world.func_152378_a(message.uuid)).playerNetServerHandler;
		
		for(int x=message.chunkPosX-message.renderDistance; x<=message.chunkPosX+message.renderDistance;x++) {
			for(int z=message.chunkPosZ-message.renderDistance; z<=message.chunkPosZ+message.renderDistance;z++) {
				Chunk chunk = world.getChunkFromChunkCoords(x, z);
				handler.sendPacket(new S21PacketChunkData(chunk, false, 1));
				MultipleCameraCore.log("sended packet.");
			}
		}
		
		return new PacketChunkLoadResponse();
		
	}

}
