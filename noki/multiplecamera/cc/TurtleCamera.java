package noki.multiplecamera.cc;
 
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import noki.multiplecamera.MultipleCameraData;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleCommandResult;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.turtle.TurtleUpgradeType;
import dan200.computercraft.api.turtle.TurtleVerb;


/**********
 * @class TurtleCamera
 *
 * @description
 * @descriptoin_en
 */
public class TurtleCamera implements ITurtleUpgrade {
	
	//******************************//
	// define member variables.
	//******************************//
	private ItemStack upgradeItem = new ItemStack(MultipleCameraData.multipleCamera, 1, 0);
	
	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public int getUpgradeID() {
		
		return CCManager.cameraTurtlePID;
		
	}
	
	@Override
	public String getUnlocalisedAdjective() {
		
		return "Camera";
		
	}
	
	@Override
	public TurtleUpgradeType getType() {
		
		return  TurtleUpgradeType.Peripheral;
		
	}
	
	@Override
	public ItemStack getCraftingItem() {
		
		return this.upgradeItem;
		
	}
	
	@Override
	public IPeripheral createPeripheral(ITurtleAccess turtle, TurtleSide side) {
		
		return new TurtleCameraHosted(turtle, side);
		
	}
	
	@Override
	public IIcon getIcon(ITurtleAccess turtle, TurtleSide side) {
		
		return MultipleCameraData.multipleCamera.getIcon(0, 2);
		
	}
	
	@Override
	public TurtleCommandResult useTool(ITurtleAccess turtle, TurtleSide side, TurtleVerb verb, int direction) {
		
		return null;
		
	}
	
	@Override
	public void update(ITurtleAccess turtle, TurtleSide side) {
		
//		MultipleCameraCore.log("on update");
		IPeripheral peripheral = turtle.getPeripheral(side);
		if(peripheral != null && peripheral instanceof TurtleCameraHosted) {
			((TurtleCameraHosted)peripheral).updateCamera();
		}
		
	}
 
}
