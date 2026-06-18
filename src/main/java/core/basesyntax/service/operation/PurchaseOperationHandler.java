package core.basesyntax.service.operation;

import core.basesyntax.db.Storage;
import core.basesyntax.service.FruitTransaction;

public class PurchaseOperationHandler implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction) {
        String fruit = transaction.getFruit();
        int quantityToBuy = transaction.getQuantity();
        if (!Storage.fruits.containsKey(fruit)) {
            throw new RuntimeException("Product not found in storage: " + fruit);
        }
        int currentQuantity = Storage.fruits.get(fruit);
        if (currentQuantity < quantityToBuy) {
            throw new RuntimeException("Not enough " + fruit + " in storage. Available: "
                    + currentQuantity + ", requested: " + quantityToBuy);
        }
        Storage.fruits.put(fruit, currentQuantity - quantityToBuy);
    }
}

