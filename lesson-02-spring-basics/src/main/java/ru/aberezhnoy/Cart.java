package ru.aberezhnoy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.aberezhnoy.persist.ProductRepository;

import java.util.Scanner;

public class Cart {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    public void createCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Please see our today's offer:");
        productRepository.printCatalog();

        label:
        while (true) {
            System.out.println("Please, choose an action: \n to add an item text: add \n to remove item text: remove \n to view the current cart text: current order \n to empty current cart text: clear \n to leave text exit");

            String customerChoice = scanner.nextLine();
            switch (customerChoice) {
                case "add":
                    System.out.println("Please, text the product id that you want to add");
                    cartService.addProductToCartById(Long.valueOf(scanner.nextLine()));
                    System.out.println("Your current order: ");
                    cartService.printProductsInCart();
                    break;
                case "remove":
                    System.out.println("Please, text the product id that you want to remove");
                    cartService.removeProductFromCartById(Long.valueOf(scanner.nextLine()));
                    System.out.println("Your current order: ");
                    cartService.printProductsInCart();
                    break;
                case "clear":
                    cartService.emptyCart();
                    System.out.println("Your cart is now empty. Do you want to continue? ");
                    break;
                case "current order":
                    cartService.printProductsInCart();
                    break;
                case "exit":
                    System.out.println("Have a nice day! We hope to see you soon!");
                    break label;
            }
        }
    }
}
