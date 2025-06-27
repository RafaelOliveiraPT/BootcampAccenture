class Order {
  constructor(requesterName) {
    this.id = undefined;
    this.requesterName = requesterName;
    this.products = [];
  }

  addProducts(arrayProducts) {
    this.products = arrayProducts;
  }

  removeProduct(idProduct) {
    this.products.filter((e) => e.id !== idProduct);
  }

  save() {
    this.id = new Date().getTime();
    // save in db
  }
}

class Product {
  constructor(name, extra) {
    this.name = name;
    if (extra) {
      this.extra = extra;
    }
  }

  // add more methods
}
