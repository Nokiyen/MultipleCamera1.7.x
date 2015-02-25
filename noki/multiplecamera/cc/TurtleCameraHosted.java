package noki.multiplecamera.cc;

import net.minecraft.util.Vec3;
import noki.multiplecamera.MultipleCameraCore;
import noki.multiplecamera.camera.CameraManagerServer;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;


/**********
 * @class TurtleCameraHosted
 *
 * @description
 * @description_en
 */
public class TurtleCameraHosted implements IPeripheral {
	
	//******************************//
	// define member variables.
	//******************************//
	private ITurtleAccess turtle;
	@SuppressWarnings("unused")
	private TurtleSide side;
	
	private boolean activated = false;
	private int cameraId = -1;
	
	//******************************//
	// define member methods.
	//******************************//
	public TurtleCameraHosted(ITurtleAccess turtle, TurtleSide side) {
		
		this.turtle = turtle;
		this.side = side;
		
	}
	
	@Override
	public String getType() {
		
		return "Camera";
		
	}
	
	@Override
	public String[] getMethodNames() {
		
		return new String[] { "activate", "isActivated" };
		
	}
	
	@Override
	public synchronized void attach(IComputerAccess computer) {
		
		Vec3 vec = this.turtle.getVisualPosition(1.0F);
		this.cameraId = CameraManagerServer.addCamera(this.turtle.getWorld(),
				vec.xCoord, vec.yCoord-1.5D, vec.zCoord, this.turtle.getVisualYaw(1.0F), 0, null, true); 
		this.activated = true;
		
	}
	
	@Override
	public synchronized void detach(IComputerAccess computer) {
		
		CameraManagerServer.removeCamera(this.cameraId);
		
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
	
	private synchronized Object[] activate(Object[] arguments) {
		
		if(arguments.length < 1) {
			return new Object[] {"Invalid arguments"};
		}
		
		if(!(arguments[0] instanceof Boolean)) {
			return new Object[] {"Invalid arguments"};
		}
		
		boolean flag = (Boolean)arguments[0];
		
		if(flag == true && this.activated == false) {
			Vec3 vec = this.turtle.getVisualPosition(1.0F);
			this.cameraId = CameraManagerServer.addCamera(this.turtle.getWorld(),
					vec.xCoord, vec.yCoord-1.5D, vec.zCoord, this.turtle.getVisualYaw(1.0F), 0, null, true); 
			this.activated = true;
			return new Object[] {null};
		}
		else if(flag == false && this.activated == true){
			CameraManagerServer.removeCamera(this.cameraId);
			this.cameraId = -1;
			this.activated = false;
			return new Object[] {null};
		}
		
		return new Object[] {null};
		
	}
	
	private Object[] isActivated() {
		
		return new Object[] {this.activated};
		
	}
	
	public synchronized void updateCamera() {
		
		if(!this.turtle.getWorld().isRemote) {
			Vec3 vec = this.turtle.getVisualPosition(1.0F);
			MultipleCameraCore.log("turtle pos is %s/%s/%s.", vec.xCoord, vec.yCoord, vec.zCoord);
			CameraManagerServer.updateCamera(this.cameraId, this.turtle.getWorld(),
					vec.xCoord, vec.yCoord-1.5D, vec.zCoord, this.turtle.getVisualYaw(1.0F), 0); 
		}
		
	}
	
}
