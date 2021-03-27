
public class Item implements Comparable<Item> {
	public String item;
	private String owner;
	public boolean intheCubby;

	public Item(String name, String ownersName) {
		// TODO


		this.item = name;
		this.owner = ownersName;
		this.intheCubby = false;


	}

	public String getOwner() {
		// TODO
		return owner;
	}

	public void setOwner(String name) {
		// TODO
		this.owner = name;

	}

	@Override
	public int compareTo(Item o) {
		// TODO


		return this.owner.compareTo(o.owner);

	}

	@Override
	public String toString() {
		// TODO
		return getOwner() + ": " + this.item; //output format
	}

	@Override
	public boolean equals(Object obj) {
		// TODO

		Item another = (Item) obj;

		if((Item)obj == null){
			return false;
		}else if((another.item).equals(this.item)){
			return true;

		}else{
			return false;
		}


	}

	@Override
	public int hashCode() {
		return this.item.hashCode();
	}

}


