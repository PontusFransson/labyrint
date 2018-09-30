package map;

import java.util.ArrayList;
import blocks.Block;
import blocks.GoalBlock;
import javafx.scene.paint.Color;
import blocks.ClosedBlock;
import javafx.scene.shape.Circle;

public class MapSolver {

	Map map;

	static boolean solution = false;

	static int steps = 0;

	static int dir = 1;

	ArrayList<Block> visited_block = new ArrayList<Block>();
	ArrayList<Block> path = new ArrayList<Block>();

	public MapSolver(Map map) {
		this.map = map;
		int x = map.getStartX();
		int y = map.getStartY();

		solve(x, y, 1);
		solve(x, y, 2);
		solve(x, y, 3);
		solve(x, y, 4);

	}

	private void solve(int x, int y, int dir) {

		Block b = map.getBlock(x, y);

		if (solution) {
			return;
		}

		if (b instanceof GoalBlock) {
			System.out.println(steps);

			solution = true;
			return;

		}

		if (b instanceof ClosedBlock || b == null || visited_block.contains(b)) {

			return;

		}

		path.add(b);

		visited_block.add(b);

		steps++;

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
