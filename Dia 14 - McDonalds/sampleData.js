const sampleOrder1 = new Order("Ze Carlos");
sampleOrder1.addProducts([new Product("Big Mac®", "Normal")]);
sampleOrder1.id = 1;
/*
const sampleOrder2 = new Order("Ze Manel");
sampleOrder2.addProduct("Big Mac®", "Sem Molhos");
sampleOrder2.addProduct("Coca-Cola", "Sem Gelo");
sampleOrder2.id = 2;

const sampleOrder3 = new Order("Joao");
sampleOrder3.addProduct("Big Mac®");
sampleOrder3.addProduct("Double Cheeseburger");
sampleOrder3.addProduct("Coca-Cola");
sampleOrder3.addProduct("Batatas");
sampleOrder3.id = 3;

const sampleOrder4 = new Order("Ze");
sampleOrder4.addProduct("Big Mac®");
sampleOrder4.id = 4;*/

//const dataSample = [sampleOrder1, sampleOrder2, sampleOrder3, sampleOrder4];

const productNameTypeFoodList = [
  "",
  "Sanduiche",
  "Acompanhamento",
  "Bebida",
  "Molhos",
];

const productNameSanduichesList = [
  "Big Mac®",
  "Big Tasty®",
  "CBO®",
  "Filet-o-Fish®",
  "McVeggie",
  "Double Cheeseburger",
];

const productExtraSanduichesList = [
  "Normal",
  "Sem Molhos",
  "Sem Ketchup",
  "Sem Pickle",
];

const productNameAcompanhamentosList = [
  "Batata",
  "Batata (grande)",
  "Sopa",
  "Salada",
];

const productNameBebidasList = [
  "Agua",
  "Compal",
  "Ice Tea",
  "Fanta",
  "Coca-Cola",
];

const productExtraBebidasList = ["Normal", "Sem Gelo"];

const productNameSauces = [
  "Agridoce",
  "Barbecue",
  "Caesar",
  "Ketchup",
  "Maionese c/ Alho",
  "Molho p/ Batatas",
  "Mostarda",
];

const nameFilters = ["Todos", "Com extra", "Sem extra"];
