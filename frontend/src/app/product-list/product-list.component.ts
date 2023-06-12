import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {CartService} from "../services/cart.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService,
              private cartService: CartService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.productService.findAll(1)
      .subscribe(res => {
          this.products = res.content;
        },
        err => {
          console.error(err);
        });
  }

  public delete(id: number | null) {
    if (id != null) {
      return this.productService.delete(id)
        .subscribe(res => {
            console.info(res);
            // this.router.navigateByUrl("/product");
            // this.productService.findAll(1);
            this.ngOnInit();
          },
          err => {
            console.error(err);
          })
    }
    return alert("Fuck you!");
  }

  public addToCart(product: Product) {
    return this.cartService.addToCart(product)
      .subscribe();
  }
}
