import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../model/product";
import {Category} from "../model/category";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  category =  new Category(null, "")

  product = new Product(null, "", "", 0, this.category.id, this.category.name);

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param['id'] == 'new') {
        this.product = new Product(null, "", "", 0, this.category.id, this.category.name);
      } else {
        this.productService.findById(param['id'])
          .subscribe(res => {
              this.product = res;
            },
            err => {
              console.error(err);
            })
      }
    })
  }

  submit() {
    if (this.product.id != null) {
    this.productService.save(this.product)
      .subscribe(res => {
          console.info(res);
          this.router.navigateByUrl('/product');
        },
        err => {
          console.error(err);
        })
    } else{
      this.productService.create(this.product)
        .subscribe(res => {
            console.info(res);
            this.router.navigateByUrl('/product');
          },
          err => {
            console.error(err);
          })
    }
  }
}
