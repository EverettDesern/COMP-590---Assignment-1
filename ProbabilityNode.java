package stuff;

public class ProbabilityNode implements Comparable<ProbabilityNode> {

	public char sym;
	public double probability;
	public ProbabilityNode left, right;
	public int depth;
	public int height;

	public ProbabilityNode(char sym, double probability) {
		this.sym = sym;
		this.probability = probability;
		this.left = null;
		this.right = null;
		this.depth = 0;
	}

	public ProbabilityNode(ProbabilityNode leftNode, ProbabilityNode rightNode) {
		this.left = leftNode;
		this.right = rightNode;
		this.probability = leftNode.probability + rightNode.probability;
		this.depth = max(leftNode.depth, rightNode.depth) + 1;
	}

	public int max(int depth1, int depth2) {
		if (depth1 >= depth2) {
			return depth1;
		} else {
			return depth2;
		}
	}

	@Override
	public int compareTo(ProbabilityNode a) {
		double prob = this.probability;
		double prob2 = a.probability;
		if (prob < prob2) {
			return -1;
		} else if (prob > prob2) {
			return 1;
		} else if (prob == prob2){
			double depth = this.depth;
			double depth2 = a.depth;
			if (depth < depth2) {
				return -1;
			} else if (depth > depth2) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}
	


}
