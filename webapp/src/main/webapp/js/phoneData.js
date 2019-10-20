"use strict";

function createPhone() {
  var countryCode = document.forms["addPhoneForm"]["countryCode"].value;
  var operatorCode = document.forms["addPhoneForm"]["operatorCode"].value;
  var phoneNumber = document.forms["addPhoneForm"]["phoneNumber"].value;
  var type = document.forms["addPhoneForm"]["type"].value;
  var comment = document.forms["addPhoneForm"]["comment"].value;

  var table = document.getElementById("phonesTable");
  var length = table.rows.length;
  var allPhones = [];
  for (var i = 1; i < length; i++) {
    allPhones.push(table.rows[i].cells[3].innerHTML);
  }

  var alreadyExists = false;
  for (i = 0; i < allPhones.length; i++) {
    if (allPhones[i] === phoneNumber) {
      alreadyExists = true;
      alert("Phone is already exists!");
    }
  }

  var validation = validatePhoneAdd();
  if (validation && !alreadyExists) {
    var row = document.createElement("tr");
    var checkbox = document.createElement("td");
    var input = document.createElement("input");
    input.type = "checkbox";
    input.name = "checkPhone";
    input.value = phoneNumber;
    checkbox.appendChild(input);
    var country = document.createElement("td");
    country.appendChild(document.createTextNode(countryCode));
    var operator = document.createElement("td");
    operator.appendChild(document.createTextNode(operatorCode));
    var number = document.createElement("td");
    number.appendChild(document.createTextNode(phoneNumber));
    var phoneType = document.createElement("td");
    phoneType.appendChild(document.createTextNode(type));
    var commentary = document.createElement("td");
    commentary.appendChild(document.createTextNode(comment));
    row.appendChild(checkbox);
    row.appendChild(country);
    row.appendChild(operator);
    row.appendChild(number);
    row.appendChild(phoneType);
    row.appendChild(commentary);
    table.appendChild(row);
    document.forms["addPhoneForm"].reset();
    document.getElementById("addPhonePopUp").style.display = "none";
  }
}

function tablePhonesJson() {
  var table = document.getElementById("phonesTable");
  var length = table.rows.length;
  var allPhones = [];
  for (var i = 1; i < length; i++) {
    var countryCode = table.rows[i].cells[1].innerHTML;
    if (countryCode.length === 0) {
      countryCode = null;
    } else {
      countryCode = parseInt(countryCode);
    }
    var operatorCode = table.rows[i].cells[2].innerHTML;
    if (operatorCode.length === 0) {
      operatorCode = null;
    } else {
      operatorCode = parseInt(operatorCode);
    }
    var phoneNumber = table.rows[i].cells[3].innerHTML;
    phoneNumber = parseInt(phoneNumber);
    var type = table.rows[i].cells[4].innerHTML;
    if (type.length === 0) {
      type = null;
    }
    var comment = table.rows[i].cells[5].innerHTML;
    if (comment.length === 0) {
      comment = null;
    }
    var phoneInfo = {
      "contactId": parseInt(id), "countryCode": countryCode, "operatorCode": operatorCode,
      "phoneNumber": phoneNumber, "type": type, "comment": comment
    };
    allPhones.push(phoneInfo);
  }
  document.getElementById("allPhones").value = JSON.stringify(allPhones);
}


function deletePhones() {
  var phones = [];
  var checkboxes = document.querySelectorAll("input[type=checkbox]:checked[name=checkPhone]");
  for (var i = 0; i < checkboxes.length; i++) {
    phones.push(checkboxes[i].value);
  }
  if (phones.length === 0) {
    alert("You didn't choose phones!");
  } else {
    var table = document.getElementById("phonesTable");
    for (var j = 1, row; row = table.rows[j]; j++) {
      var number = row.cells[3].innerHTML;
      if (phones.includes(number)) {
        table.deleteRow(j);
        j--;
      }
    }
  }
}
