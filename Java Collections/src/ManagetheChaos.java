import java.util.*;

public class ManagetheChaos {
	List<KitchenItem> allItems;
	Stack<Item> cubby;
	int cubbysize = 15;

	public ManagetheChaos(List<KitchenItem> kitchenItems) {
		allItems = kitchenItems;
		cubby = new Stack<Item>();
	}

	public ManagetheChaos(List<KitchenItem> kitchenItems, Stack C) {
		allItems = kitchenItems;
		if (C.size() > cubbysize)
			throw new RuntimeException("Cubby is not that big!");
		cubby = C;
	}

	public List<Item> findSpares() {
		// TODO
		List<Item> unusedItems = new ArrayList<>();

		int i = 0;

		while (i < allItems.size()) {
			KitchenItem usedItems = allItems.get(i);
			int difference = usedItems.getQuantity() - usedItems.neededquantity;

			i++;

			for (int j = 0; j < difference; j++) {


				unusedItems.add(new Item(usedItems.item, usedItems.getOwner()));

			}
		}

		return unusedItems; //return the new list of unused items

	}

	public void putAway() {
		// TODO

		List<Item> unusedItems = findSpares();
		Collections.sort(unusedItems);

		for (int j = 0; j < unusedItems.size(); j++) {
			if (cubby.size() + 1 > cubbysize) {  //exception if there is not enough space
				throw new RuntimeException("Cubby Overflow !!!!");
			}
			Item getVariable = unusedItems.get(j);
			cubby.push(getVariable);
			getVariable.intheCubby = true;



		}


	}

	public void putAwaySmart() {
		List<Item> unusedItems = findSpares();
		Collections.sort(unusedItems);

		Stack<Item> elementsOut = new Stack<>();
		int sizeVariable = cubby.size();

		for (int i = 0; i < sizeVariable; i++) {
			elementsOut.add(cubby.pop());
		}
		int i = 0;
		while (sizeVariable <= cubbysize && !elementsOut.empty()) {

			if (cubby.size()+1 > cubbysize)
				throw new RuntimeException("Cubby Overflow !!!!");

			if (elementsOut.peek() == null) {
				//if the head == 0

				elementsOut.pop();
				//delete it
				Item getNotUsed = unusedItems.get(i);
				cubby.push(getNotUsed);
				unusedItems.get(i).intheCubby = true;
				i++;


			} else {
				Item poped = elementsOut.pop();
				cubby.push(poped);

			}

		}
		while (elementsOut.empty() && i < unusedItems.size()) {

			cubby.push(unusedItems.get(i));


			i++;
		}


	}


	// TODO


	public boolean replaceable(Item item) {
		// TODO

			boolean newBoolean = cubby.contains(item);
			return newBoolean;

	}

	public Item replace(Item item) {
		// TODO

		List<Item> brokenItem = new LinkedList<>();
		brokenItem.add(item);

		Item newReplace = null;
		if(replaceable(item)) {
			if(item == null) {
				throw new RuntimeException("Try with another item");
			}


			newReplace = brokenItem.set(0, item);
			cubby.remove(item);

			return newReplace;

		}else {
			throw new RuntimeException("Item not replaceable ");
		}




	}

	public static void main(String[] args) {
		// USE FOR TESTING
		


	}
}




