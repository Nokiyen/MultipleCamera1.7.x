package noki.multiplecamera;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Timer;
import noki.multiplecamera.block.BlockMultipleCamera;
import noki.multiplecamera.cc.CCManager;
import noki.multiplecamera.cc.PeripheralMultipleCamera;
import noki.multiplecamera.item.ItemViewer;
import noki.multiplecamera.tile.TileMultipleCamera;


/**********
 * @class MultipleCameraData
 *
 * @description
 * @description_en
 */
public class MultipleCameraData {

	//******************************//
	// define member variables.
	//******************************//
	// creative tab.
	public static CreativeTabs tab;
	
	// block.
	public static Block multipleCamera;
	public static String multipleCameraName = "multiple_camera";
	
	// item.
	public static Item multipleViewer;
	public static String multipleViewerName = "multiple_viewer";
	
	// dependency.
	public static boolean ccLoaded = false;
	
	// timer.
	@SideOnly(Side.CLIENT)
	public static Timer timer;
	
	
	//******************************//
	// define member methods.
	//******************************//
	public static void setModData(FMLPreInitializationEvent event) {
		
		tab = new TabMultipleCamera();
		
		ccLoaded = Loader.isModLoaded("ComputerCraft");
		if(ccLoaded == true) {
			CCManager.setCCConfig(event);
		}
		
	}
	
	public static void registerBlocks() {
		
		multipleCamera = new BlockMultipleCamera(multipleCameraName, tab);
		GameRegistry.registerBlock(multipleCamera, multipleCameraName);
		if(ccLoaded == true) {
			GameRegistry.registerTileEntity(PeripheralMultipleCamera.class, multipleCameraName);
		}
		else {
			GameRegistry.registerTileEntity(TileMultipleCamera.class, multipleCameraName);
		}
		
	}
	
	public static void registerItems() {
		
		multipleViewer = new ItemViewer(multipleViewerName, tab);
		GameRegistry.registerItem(multipleViewer, multipleViewerName);
		
	}
	
	public static void registerRecipes() {
				
		GameRegistry.addRecipe(new ItemStack(multipleCamera, 1, 0),
				"xxx", "yzy", "xwx", 'x', Blocks.planks, 'y', Items.gold_nugget, 'z', Blocks.glass_pane, 'w', Items.ender_pearl);
		GameRegistry.addRecipe(new ItemStack(multipleViewer, 1, 0),
				" x ", " y ", " z ", 'x', Items.iron_ingot, 'y', Blocks.glass_pane, 'z', Items.ender_pearl);
		
	}
	
	public static void registerCC() {
		
		if(ccLoaded) {
			CCManager.register();
		}
		
	}
		
	public static class TabMultipleCamera extends CreativeTabs {
		
		//******************************//
		// define member variables.
		//******************************//
		public static String label = "MultipleCamera";

		
		//******************************//
		// define member methods.
		//******************************//
		public TabMultipleCamera() {
			
			super(label);
			
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			
			return multipleViewer;

		}
		
	}

}
