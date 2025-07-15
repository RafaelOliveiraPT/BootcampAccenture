/**
 * Implementação da classe MyTable
 */
class MyTable {
  #divID;

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
    ${buildHTMLElementFromArray(tHeadColumns, "th")}
  </thead>
  <tbody >

  </tbody>
</table>`;
  }

  insertRowMyTable(ArrayColumns) {
    const randomID = Date.now();
    document
      .getElementById(this.#divID)
      .querySelector("tbody")
      .insertAdjacentHTML(
        "afterbegin",
        `<tr> ${buildHTMLElementFromArray(ArrayColumns, "td")}   </tr> `
      );
  }

  clearTable() {
    document.getElementById(this.#divID).innerHTML = "";
  }
}

function buildHTMLElementFromArray(array, tagName) {
  let stringAux = "";
  for (const element of array) {
    stringAux += `<${tagName}> ${element} </${tagName}>`;
  }
  return stringAux;
}
