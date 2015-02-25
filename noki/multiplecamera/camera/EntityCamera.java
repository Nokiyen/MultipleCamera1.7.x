package noki.multiplecamera.camera;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class EntityCamera extends EntityLivingBase {
	
	public UUID uuid;
	public boolean isTurtle;

	public EntityCamera(World world, double posX, double posY, double posZ, float yaw, float pitch,
			UUID uuid, boolean isTurtle) {
		
		super(world);
		this.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
		this.setPositionAndRotation(posX, posY, posZ, yaw, pitch);
		this.yOffset = 0.0F;
		this.uuid = uuid;
		this.isTurtle = isTurtle;
		
	}
	
	public void setCameraInfo(World world, double posX, double posY, double posZ, float yaw, float pitch) {
		
		this.setWorld(world);
		this.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
		this.setPositionAndRotation(posX, posY, posZ, yaw, pitch);
		
	}

	@Override
	public ItemStack getHeldItem() {
		
		return null;
		
	}

	@Override
	public ItemStack getEquipmentInSlot(int slotNumber) {
		
		return null;
		
	}

	@Override
	public void setCurrentItemOrArmor(int slotNum, ItemStack stack) {
		
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		
		return null;
		
	}

}
