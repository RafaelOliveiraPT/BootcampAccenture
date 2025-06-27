/**
 * Implementação da classe MyTable
 */
class MyTable {
  #divID;
  #numberOfLines = 0;

  /**
   * @param {string} divID - id of div
   */
  constructor(divID) {
    this.#divID = divID;
  }

  initTable(tHeadColumns) {
    document.getElementById(this.#divID).innerHTML = `
<table class="table table-striped table-hover">
  <thead>
    <th>#</th>
    ${buildHtmlElementFromArray(tHeadColumns, "th")}
  </thead>
  <tbody >

  </tbody>
</table>`;
  }

  getNameCustomer() {
    return document.getElementById("clientName").value;
  }

  getProducts() {
    const tableContent = document
      .getElementById(this.#divID)
      .querySelector("tbody");
    const rows = tableContent.rows;
    const productsArray = [];

    for (const row of rows) {
      const celulas = row.cells;
      const name = celulas[1].innerText;
      const nameExtra = celulas[2].innerText;
      productsArray.push(new Product(name, nameExtra));
    }
    return productsArray;
  }

  insertRowMyTable(ArrayColumns, id) {
    const randomID = Date.now();
    if (!id) {
      id = this.#numberOfLines;
    }
    document
      .getElementById(this.#divID)
      .querySelector("tbody")
      .insertAdjacentHTML(
        "afterbegin",
        `<tr> <th scope="row">${id}</th> ${buildHtmlElementFromArray(
          ArrayColumns,
          "td"
          //)} <td> <button onclick="removeItemTable('${randomID}')" id="${randomID}" type="button" class="btn"> <img src="https://icons.getbootstrap.com/assets/icons/trash.svg" alt="removeItem"/> </button> </td>  </tr> `
        )} <td> <button onclick="removeItemTable(this, ${randomID})" id="${randomID}" type="button" class="btn"> <img src="https://icons.getbootstrap.com/assets/icons/trash.svg" alt="removeItem"/> </button> </td>  </tr> `
        /*
        <td><input class="form-check-input" type="checkbox" id="checkbox-${
          this.#numberOfLines
        }"> </td> </tr> `*/
      );

    this.#numberOfLines++;
  }
}

function buildHtmlElementFromArray(array, tagName) {
  let stringAux = "";
  for (const element of array) {
    stringAux += `<${tagName}> ${element} </${tagName}>`;
  }
  return stringAux;
}
