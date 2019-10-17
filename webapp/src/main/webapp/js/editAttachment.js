"use strict";

function editAttachment() {
  var fileName = document.forms["editAttachmentForm"]["fileName2"].value;
  var extension = document.forms["editAttachmentForm"]["fileExtension"].value;
  var comment = document.forms["editAttachmentForm"]["attachmentComment2"].value;
  var nameWithExtension = fileName + "." + extension;

  var attachmentToEdit = [];
  var checkboxes = document.querySelectorAll("input[type=checkbox]:checked[value=old],input[type=checkbox]:checked[value=edit]");
  for (var i = 0; i < checkboxes.length; i++) {
    attachmentToEdit.push(checkboxes[i].value);
  }
  if (attachmentToEdit.length === 0 || attachmentToEdit.length > 1) {
    alert("You can edit only 1 attachment!");
  } else {
    var table = document.getElementById("attachmentsTable");
    var length = table.rows.length;
    var rowToUpdate;
    var attachmentId = checkboxes[0].getAttribute("class");
    for (i = 1; i < length; i++) {
      var num = table.rows[i].cells[0].childNodes[0].className;
      if (num === attachmentId) {
        rowToUpdate = i;
      }
    }

    var validation = validateAttachmentEdit();
    if (validation) {
      var loadedDate = table.rows[rowToUpdate].cells[2].innerHTML;
      table.deleteRow(rowToUpdate);
      var row = document.createElement("tr");
      var checkbox = document.createElement("td");
      var input = document.createElement("input");
      input.type = "checkbox";
      input.className = attachmentId;
      input.name = "checkAttachment";
      input.value = "edit";
      checkbox.appendChild(input);
      var newFile = document.createElement("td");
      newFile.style.backgroundColor = "yellow";
      newFile.appendChild(document.createTextNode(nameWithExtension));
      var date = document.createElement("td");
      date.appendChild(document.createTextNode(loadedDate));
      var commentary = document.createElement("td");
      commentary.appendChild(document.createTextNode(comment));
      row.appendChild(checkbox);
      row.appendChild(newFile);
      row.appendChild(date);
      row.appendChild(commentary);
      table.appendChild(row);
      document.getElementById("editAttachmentPopUp").style.display = "none";
    }
  }
}