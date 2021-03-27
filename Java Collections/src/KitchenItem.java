
public class KitchenItem extends Item {

	private int totalquantity;
	public int neededquantity;

	public KitchenItem(String name, int n) {
		// TODO

		super(name,"shared");
		this.item = item;
		this.totalquantity = n;

	}

	public KitchenItem(String name, int n, String ownersName) {
		// TODO
		super(name,ownersName);

		this.totalquantity = n;



	}

	public KitchenItem(KitchenItem thing) {
		// TODO
		super(thing.item, thing.getOwner());


		this.totalquantity = thing.totalquantity;
		this.neededquantity = thing.neededquantity;
		this.intheCubby = thing.intheCubby;

	}

	public int getQuantity() {
		return totalquantity;
	}

	public void needed(int n) {
		neededquantity = n;
	}

}

