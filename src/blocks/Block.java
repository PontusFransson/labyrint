package blocks;

import javafx.scene.Group;

public abstract class Block extends Group {

	public static double SIZE = 25;

	private int blockId = -1;
	
	public Block(){
		blockId = -1;
	}
	
	public void setBlockId(int newValue){
		blockId = newValue;
	}
	
	public int getBlockId(){
		
		return blockId;
		
	}

}