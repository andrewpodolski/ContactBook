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