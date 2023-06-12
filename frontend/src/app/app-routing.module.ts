import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductListComponent} from "./product-list/product-list.component";
import {ProductFormComponent} from "./product-form/product-form.component";
import {CartComponent} from "./cart/cart.component";

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "product"},
  {path: "product", component: ProductListComponent},
  {path: "cart", component: CartComponent},
  {path: "product/:id", component: ProductFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
