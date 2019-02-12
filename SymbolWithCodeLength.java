package stuff;

public class SymbolWithCodeLength implements Comparable<SymbolWithCodeLength>{
	int sym;
	int codelength;
	
	public SymbolWithCodeLength(int sym, int codelength) {
		this.sym = sym;
		this.codelength = codelength;
	}

	@Override
	public int compareTo(SymbolWithCodeLength o) {
		if(this.codelength < o.codelength) {
			return -1;
		} else if(this.codelength > o.codelength) {
			return 1;
		} else if(this.codelength == o.codelength) {
			if(this.sym > o.sym) {
				return 1;
			} else if(this.sym < o.sym) {
				return -1;
			} else {
				return 0;
			}
		}
		return 0;
	}

}
