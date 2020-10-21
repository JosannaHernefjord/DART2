
import java.util.ArrayList;
import java.util.Collections;

public class ItemLibrary {

    protected ArrayList<Item> itemList;   //Instance variable

    //--------------CONSTRUCTOR---------
    public ItemLibrary() {
        itemList = new ArrayList<>();
    }

    //--------------METHODS------------
    public void addItem(Item item) {
        if (!contains(item.getId())) {
            itemList.add(item);
        } else {
            System.out.println("Game with ID: " + item.getId() + " already exist.");
        }
    }

    public void addReview(int id, Review review) {
        for (Item item : itemList) {
            if (item.getId() == id) {
                item.addReview(review);
            }
        }
    }

    public void removeItem(int idToRemove) {
        boolean foundItem = false;

        for (Item item : itemList) {
            if (idToRemove == item.getId()) {
                itemList.remove(item);
                foundItem = true;
                System.out.println("Game removed!");
                break;
            }
        }

        if (!foundItem) {
            System.out.println("Item with ID: " + idToRemove + " was not found.");
        }
    }

    public void rentItem(int id) {
        for (Item item : itemList) {
            if (id == item.getId()) {
                item.rentOut();
                break;
            }
        }
    }

    public void returnItem(int id) {
        for (Item item : itemList) {
            if (id == item.getId()) {
                item.returnItem();
                break;
            }
        }
    }

    public boolean contains(int id) {
        for (Item item : itemList) {
            if (id == item.getId()) {
                return true;
            }
        }

        return false;
    }

    public boolean checkAvailability(int id) {
        for (Item item : itemList) {
            if (id == item.getId()) {
                return item.getIsAvailable();
            }
        }

        return false;
    }

    public double getDailyRent(int id) {
        for (Item item : itemList) {
            if (id == item.getId()) {
                return item.getDailyRent();
            }
        }

        return 0;
    }

    //prints all games in list to console
    public void printAllItems() {
        Collections.sort(itemList);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    public void printItemHistory() {
        for (Item item : itemList) {
            for (Review review : item.getReviews()) {
                System.out.println(review.toString());
            }
        }
    }

    public void printRentFrequency() {
        for (Item item : itemList) {
            if (item.getReviews().size() > 0) {
                System.out.println("ID: " + item.getId() + "  " + item.getTitle() + "  Times rented: " + item.getReviews().size());
            }
        }
    }

    public void printMostProfitable() {
        itemList.trimToSize();
        Item mostProfitableGame = itemList.get(0);

        for (Item item : itemList) {
            if (mostProfitableGame.getTotalRentProfit() < item.getTotalRentProfit()) {
                mostProfitableGame = item;
            }
        }
        System.out.println(mostProfitableGame.toString());
    }
}
