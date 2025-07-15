/**
 * Implementação da classe MyTable
 */
class MyTable {
  #divID;
  #numberOfLines = 0;
  #tBodyID = "tBody1";

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
  <tbody id=${this.#tBodyID}>

  </tbody>
</table>
<button onclick=deleteRows("${
      this.#divID
    }") class="btn btn-primary" type="button">Save</button>
                `;
  }

  insertRowMyTable(ArrayColumns) {
    document
      .getElementById(this.#tBodyID)
      .insertAdjacentHTML(
        "beforeend",
        `<tr> <th scope="row">${
          this.#numberOfLines
        }</th> ${buildHtmlElementFromArray(
          ArrayColumns,
          "td"
        )} <td><input class="form-check-input" type="checkbox" id="checkbox-${
          this.#numberOfLines
        }"> </td> </tr> `
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

function deleteRows(divID) {
  const tabela = document.getElementById(divID).querySelector("table");
  const tbody = tabela.querySelector("tbody");
  const rows = tbody.rows;
  for (let i = rows.length - 1; i > 0; i--) {
    for (let celula of rows[i].cells) {
      const checkbox = celula.querySelector('input[type="checkbox"]');
      if (checkbox && checkbox.checked) {
        document
          .getElementById(divID)
          .querySelector("table")
          .deleteRow(i + 1);
      }
    }
  }
}
