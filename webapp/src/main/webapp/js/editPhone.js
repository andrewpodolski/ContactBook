"use strict";

function editPhone() {
    var countryCode = document.forms["editPhoneForm"]["countryCode2"].value;
    var operatorCode = document.forms["editPhoneForm"]["operatorCode2"].value;
    var phoneNumber = document.forms["editPhoneForm"]["phoneNumber2"].value;
    var type = document.forms["editPhoneForm"]["type2"].value;
    var comment = document.forms["editPhoneForm"]["comment2"].value;

    var phoneToEdit = [];
    var checkboxes = document.querySelectorAll("input[type=checkbox]:checked[name=checkPhone]");
    for (var i = 0; i < checkboxes.length; i++) {
        phoneToEdit.push(checkboxes[i].value);
    }
    if (phoneToEdit.length === 0 || phoneToEdit.length > 1) {
        alert("You can edit only 1 phone!");
    } else {
        var table = document.getElementById("phonesTable");
        var length = table.rows.length;
        var rowToUpdate;
        for (i = 1; i < length; i++) {
            var num = table.rows[i].cells[3].innerHTML;
            if (num === phoneToEdit[0]) {
                rowToUpdate = i;
            }
        }
        var currentPhone = table.rows[rowToUpdate].cells[3].innerHTML;
        var allPhonesWithoutCurrent = [];
        for (i = 1; i < length; i++) {
            allPhonesWithoutCurrent.push(table.rows[i].cells[3].innerHTML);
        }
        var currentPhoneIndex = allPhonesWithoutCurrent.indexOf(currentPhone);
        allPhonesWithoutCurrent.splice(currentPhoneIndex, 1);
        var alreadyExists = false;
        for (i = 0; i < allPhonesWithoutCurrent.length; i++) {
            if (allPhonesWithoutCurrent[i] === phoneNumber) {
                alreadyExists = true;
                alert("Phone is already exists!");
            }
        }
        var validation = validatePhoneEdit();
        if (validation && !alreadyExists) {
            table.deleteRow(rowToUpdate);
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
            document.getElementById("editPhonePopUp").style.display = "none";
        }
    }
}