import {Component, OnInit} from '@angular/core';
import {LineItem} from "../model/lineItem";
import {CartService} from "../services/cart.service";
import {Product} from "../model/product";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  lineItems: LineItem[] = [];

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.cartService.showAll()
      .subscribe(res => {
      this.lineItems = res;
    },
        err => {
          console.error(err);
        })
  }

  public delete(lineItem: LineItem) {
    return this.cartService.removeFromCart(lineItem)
      .subscribe(res => {
        console.info(res);
        this.ngOnInit();
      },
        err => {
          console.error(err);
        })
  }
}
