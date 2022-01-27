package ru.aberezhnoy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.aberezhnoy.persist.Product;
import ru.aberezhnoy.persist.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartService {

    @Autowired
    private ProductRepository productRepository;

    private List<Product> productsInCart;

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    @PostConstruct
    public void init() {
        productsInCart = new ArrayList<>();
    }

    public void addProductToCartById(Long id) {
        productsInCart.add(productRepository.findById(id));
    }

    public void removeProductFromCartById(Long id) {
        productsInCart.remove(productsInCart.stream().filter(i -> i.getId() == id).findFirst().get());
    }

    public void emptyCart() {
        productsInCart.clear();
    }

    public void printProductsInCart() {
        for (Product p : productsInCart) {
            System.out.println("Product id: " + p.getId() + ", product name: " + p.getName());
        }
    }

//    public void createCart() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome! Please see our today's offer:");
//        productRepository.printCatalog();
//
//        label:
//        while (true) {
//            System.out.println("Please, choose an action: \n to add an item text: add \n to remove item text: remove \n to view the current cart text: current order \n to empty current cart text: clear \n to leave text exit");
//
//            String customerChoice = scanner.nextLine();
//            switch (customerChoice) {
//                case "add":
//                    System.out.println("Please, text the product id that you want to add");
//                    addProductToCartById(Long.valueOf(scanner.nextLine()));
//                    System.out.println("Your current order: ");
//                    printProductsInCart();
//                    break;
//                case "remove":
//                    System.out.println("Please, text the product id that you want to remove");
//                    removeProductFromCartById(Long.valueOf(scanner.nextLine()));
//                    System.out.println("Your current order: ");
//                    printProductsInCart();
//                    break;
//                case "clear":
//                    emptyCart();
//                    System.out.println("Your cart is now empty. Do you want to continue? ");
//                    break;
//                case "current order":
//                    printProductsInCart();
//                    break;
//                case "exit":
//                    System.out.println("Have a nice day! We hope to see you soon!");
//                    break label;
//            }
//        }
//    }
}
