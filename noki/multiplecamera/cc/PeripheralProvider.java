package noki.multiplecamera.cc;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import noki.multiplecamera.block.BlockMultipleCamera;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;


/**********
 * @class PeripheralProvider
 * 
 * @description
 * @description_en
 */
public class PeripheralProvider implements IPeripheralProvider {

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		
		Block block = world.getBlock(x, y, z);
		if(block instanceof BlockMultipleCamera) {
			return (IPeripheral)world.getTileEntity(x, y, z);
		}		
		return null;
		
	}

}
