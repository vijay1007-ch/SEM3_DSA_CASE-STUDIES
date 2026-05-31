import java.util.*;

public class SteamBPlusTree {

    static class BPlusTree {

        private List<Integer> prices;

        public BPlusTree() {
            prices = new ArrayList<>();
        }

        // Insert price
        public void insert(int price) {
            prices.add(price);
            Collections.sort(prices);
        }

        // Display all prices
        public void display() {
            System.out.println("Game Prices:");
            for (int price : prices) {
                System.out.print(price + " ");
            }
            System.out.println();
        }

        // Range Search
        public List<Integer> rangeSearch(int low, int high) {

            List<Integer> result = new ArrayList<>();

            for (int price : prices) {
                if (price >= low && price <= high) {
                    result.add(price);
                }
            }

            return result;
        }

        // Range Count
        public int rangeCount(int low, int high) {

            int count = 0;

            for (int price : prices) {

                if (price >= low && price <= high) {
                    count++;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {

        BPlusTree steamIndex = new BPlusTree();

        int gamePrices[] = {10, 20, 30, 40, 50, 60};

        // Insert values
        for (int price : gamePrices) {
            steamIndex.insert(price);
        }

        steamIndex.display();

        System.out.println("\nRange Search (20 - 50)");

        List<Integer> result = steamIndex.rangeSearch(20, 50);

        for (int value : result) {
            System.out.print(value + " ");
        }

        System.out.println();

        int count = steamIndex.rangeCount(20, 50);

        System.out.println("\nCount = " + count);
    }
}