package edu.gatech.cs6310;

public class LineItem {
    private Item item;

    private int quantity;

    private Integer weight;

    private Integer cost;

    private final Boolean isNull;

    public LineItem(Boolean nullable) {
        this.isNull = nullable;
    }

    public LineItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.weight = item.getWeight() * quantity;
        this.cost = item.getPrice() * quantity;
        this.isNull = false;
    }

    @Override
    public String toString() {
        return item.toString();
    }

    public String displayLineItemInfo() {
        return "item_name:" + item.getName() + "," +
                "total_quantity:" + quantity + "," +
                "total_cost:" + cost + "," +
                "total_weight:" + weight;
    }

    /* ======================= GETTERS AND SETTERS ======================= */
    public Item getItem() {
        return item;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCost() {
        return cost;
    }

    public Boolean getIsNull() {
        return isNull;
    }
}
