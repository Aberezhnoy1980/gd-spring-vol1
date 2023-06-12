export class Product {

  constructor(public id: number | null,
              public name: string,
              public description: string,
              public price: number,
              public categoryId: number | null,
              public categoryName: string) {
  }
}
