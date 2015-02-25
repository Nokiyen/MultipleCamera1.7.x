package noki.multiplecamera.cc;

import cpw.mods.fml.common.Optional.Interface;
import noki.multiplecamera.tile.TileMultipleCamera;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;


/**********
 * @class PeripheralMultipleCamera
 *
 * @description
 * @description_en
 */
@Interface(iface = "dan200.computercraft.api.peripheral.IPeripheral", modid = "ComputerCraft", striprefs = true)
public class PeripheralMultipleCamera extends TileMultipleCamera implements IPeripheral {
	
	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	public PeripheralMultipleCamera() {
		
//		super();
		
	}
	
	@Override
	public String getType() {
		
		return "MultipleCamera";
		
	}
	
	@Override
	public String[] getMethodNames() {
		
		return new String[] { "activate", "isActivated" };
		
	}
	
	@Override
	public void attach(IComputerAccess computer) {
 
	}
	
	@Override
	public void detach(IComputerAccess computer) {
		
	}
	
	@Override
	public boolean equals(IPeripheral other) {
		
		return false;
		
	}
	
	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments)
			throws LuaException, InterruptedException {

		switch(method) {
			case 0:
				return this.activate(arguments);
			case 1:
				return this.isActivated();
			default:
				return new Object[] {false, "Failed for unknown reasons"};
		}
	}
	
	private Object[] activate(Object[] arguments) {
		
		if(arguments.length < 1) {
			return new Object[] {"Invalid arguments"};
		}
		
		if(!(arguments[0] instanceof Boolean)) {
			return new Object[] {"Invalid arguments"};
		}
		
		boolean flag = (Boolean)arguments[0];
		
		if(flag != this.activated) {
			this.switchActivated();
			return new Object[] {null};
		}
		
		return new Object[] {null};
		
	}
	
	private Object[] isActivated() {
		
		return new Object[] {this.activated};
		
	}	
	
}
