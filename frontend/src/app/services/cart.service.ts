import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LineItem} from "../model/lineItem";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {
  }

  public showAll() {
    return this.http.get<LineItem[]>('api/v1/cart');
  }

  public addToCart(product: Product) {
    return this.http.post<Product>('api/v1/cart', product);
  }

  public removeFromCart(product: Product) {
    return this.http.delete<Product>('api/v1/cart', ({
      body: product
    }));
  }
  //
  // public delete(product: Product) {
  //   return this.http.delete<Product>('api/v1/cart');
  // }
}
