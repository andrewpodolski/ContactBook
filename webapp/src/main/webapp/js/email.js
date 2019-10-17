"use strict";

function openEmailFrom() {
  var idArray = [];
  var checkboxes = document.querySelectorAll("input[type=checkbox]:checked");
  for (var i = 0; i < checkboxes.length; i++) {
    idArray.push(checkboxes[i].value);
  }
  if (idArray.length === 0) {
    var text = 'Choose any contact';
    document.getElementById('contactErrors').innerHTML = text;
  } else {
    document.getElementById("contactsTable").style.display = "none";
    document.getElementById("searchForm").style.display = "none";
    document.getElementById("emailForm").style.display = "block";
    for (i = 0; i < idArray.length; i++) {
      fetch("controller/contact_info?contact_id=" + idArray[i]).then(function (resp) {
        var status = resp.status;
        if (status === 200) {
          resp.json().then(function (data) {
            var email = data.email;
            if (email !== null) {
              document.getElementById("recipients").value += (data.email + '\r\n');
              document.getElementById("allEmail").value += (data.email + " ");
            }
          });
        } else {
          handleValidationErrors(resp);
        }
      });
    }
  }
}

function closeEmailForm() {
  document.getElementById("emailForm").style.display = "none";
  document.getElementById("contactsTable").style.display = "block";
  document.getElementById('allEmail').value = '';
  document.getElementById('subject').value = '';
  document.getElementById('text').value = '';
}

function setTemplateMessage() {
  var selected = document.getElementById("template");
  var selectedValue = selected.options[selected.selectedIndex].value;
  var area = document.getElementById("text");
  if (selectedValue !== "-") {
    area.value = selectedValue;
  } else {
    area.value = "";
    area.disabled = false;
  }
}
