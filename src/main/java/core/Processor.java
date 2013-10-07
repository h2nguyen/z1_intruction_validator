package core;

public class Processor {

	protected Register F;
	protected Register G;
	
	protected String Aa;
	protected String Ab;
	protected String Ae;
	
	protected String Ba;
	protected String Bb;
	protected String Be;
	
	public Processor() {
		
	}
	
	public void loadRegF(Register f) {
		this.F = f;
	}
	
	public void loadRegG(Register g) {
		this.G = g;
	}
	
	public void addition() {
		Addition addition = new Addition();
		addition.run();
	}

	private void initRegisters() {
		// TODO Auto-generated method stub
		
	}
	
	protected String negAf() {
		return "-"+this.F.exponent.number;
	}
	
	protected String Ag() {
		return this.F.exponent.number;
	}
	
	protected String negAe() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String negAg() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String Af() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String Bg() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String Bf() {
		// TODO Auto-generated method stub
		return null;
	}
}
