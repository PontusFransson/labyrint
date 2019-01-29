package map;

import java.util.ArrayList;
import blocks.Block;
//AV LEVI SUNESSON
import blocks.GoalBlock;
import javafx.scene.paint.Color;
import blocks.ClosedBlock;
import javafx.scene.shape.Circle;

public class MapSolver {

	Map map;

	static boolean solution = false;

	static int dir = 1;

	ArrayList<Block> visited_block = new ArrayList<Block>();
	ArrayList<Block> path = new ArrayList<Block>();

	Block goal;
	int goalID;

	public MapSolver(Map map) {
		this.map = map;
		int x = map.getStartX();
		int y = map.getStartY();

		findGoal(x, y);

		solve(x, y, 1);
		solve(x, y, 2);
		solve(x, y, 3);
		solve(x, y, 4);

	}

	private void findGoal(int startX, int startY){

		Block toBeGoal = new GoalBlock(-1);

		ArrayList<Block> goals = new ArrayList<Block>();

		for (ArrayList<Block> rows : map.getBlocks()) {

			for (Block block : rows) {

				if (block instanceof GoalBlock) {
					goals.add(block);
				}

			}

		}

		double shortDist = -1;

		for (Block goal : goals) {

			int y = map.row(goal);
			int x = map.col(goal);

			double dist = Math.sqrt(Math.pow(x-startX, 2) + Math.pow(y-startY, 2));


			if (shortDist < 0 || dist < shortDist) {
				shortDist = dist;
				toBeGoal = goal;
				this.goalID = goal.getBlockId();
				this.goal = toBeGoal;

			}

		}

	}

	private void solve(int x, int y, int dir) {

		Block b = map.getBlock(x, y);

		if (b instanceof ClosedBlock || b == null || visited_block.contains(b)) {

			return;

		}

		b.getBlockId();

		if (solution) {
			return;
		}

		if (b.getBlockId() == goalID) {
			
			path.add(b);
			System.out.println(path.size());
			solution = true;
			return;
		
		}



		path.add(b);

		visited_block.add(b);

		if (dir == 1) {
			solve(x, y - 1, 1);
			solve(x + 1, y, 2);
			solve(x - 1, y, 4);
		}

		if (dir == 2) {
			solve(x, y - 1, 1);
			solve(x + 1, y, 2);
			solve(x, y + 1, 3);
		}

		if (dir == 3) {
			solve(x + 1, y, 2);
			solve(x, y + 1, 3);
			solve(x - 1, y, 4);
		}
		if (dir == 4) {
			solve(x, y - 1, 1);
			solve(x, y + 1, 3);
			solve(x - 1, y, 4);
		}

		if (!solution) {
			path.remove(b);
		}
	}

	public void showNextPath() {

		if (path.size() == 0) {
			return;
		}

		Circle c = new Circle(Block.SIZE / 2, Block.SIZE / 2, Block.SIZE / 3, Color.BLUE);
		path.get(0).getChildren().add(c);
		path.remove(0);

	}

}
