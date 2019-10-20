"use strict";

function renderContacts(contacts) {
  var rowTemplate = document.getElementById("rowTemplate").innerHTML;
  var contactsHTML = "";
  contacts.forEach(function (contact) {
    contactsHTML += Mustache.render(rowTemplate, contact);
  });
  document.getElementById("contacts").innerHTML = contactsHTML;
}

fetch("controller/contact_list?page=1&index=3").then(function (response) {
  var status = response.status;
  if (status === 200) {
    response.json().then(renderContacts);
  } else {
    handleErrorsLoading(response);
  }
});

function createPagination(numberOfRows, command) {
  var paginationIndex = 3;
  var counter = 1;
  var numberOfPages = Math.ceil(numberOfRows / paginationIndex);
  var paginationDiv = document.getElementById("pagination");
  paginationDiv.innerHTML = "";
  for (var i = 1; i <= numberOfPages; i++) {
    (function () {
      var page = counter;
      var button = document.createElement("button");
      button.type = "button";
      button.className = "btn btn-danger mr-1 btn-ms";
      button.onclick = function () {
        fetch(command + "?page=" + page + "&index=" + paginationIndex).then(function (resp) {
          var status = resp.status;
          if (status === 200) {
            resp.json().then(renderContacts);
          } else {
            handleValidationErrors(resp);
          }
        });
      };
      button.appendChild(document.createTextNode(i.toString()));
      paginationDiv.appendChild(button);
      counter++;
    })();
  }
}

getJson("controller/count_contacts").then(function (numberOfRows) {
  createPagination(numberOfRows, "controller/contact_list");
});
