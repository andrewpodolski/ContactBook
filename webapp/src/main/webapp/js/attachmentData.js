function createAttachment() {
    var fileName = document.forms["addAttachmentForm"]["fileName"].value;
    var attachmentComment = document.forms["addAttachmentForm"]["attachmentComment"].value;

    var table = document.getElementById("attachmentsTable");
    var length = table.rows.length;
    var allAttachments = [];
    for (var i = 1; i < length; i++) {
        allAttachments.push(table.rows[i].cells[1].innerHTML);
    }

    var array = fileName.split("\\");
    var name = array[array.length - 1];

    var validation = validateAttachmentAdd();
    if (validation) {
        var row = document.createElement("tr");
        var checkbox = document.createElement("td");
        var input = document.createElement("input");
        input.type = "checkbox";
        input.className = name;
        input.name = "checkAttachment";
        input.value = "new";
        checkbox.appendChild(input);
        var file = document.createElement("td");
        file.style.backgroundColor= "#FFFF00";
        file.appendChild(document.createTextNode(name));
        var date = document.createElement("td");
        var currentDate = new Date();
        var loadedDate = currentDate.getFullYear() + "-" + (currentDate.getMonth() + 1) + "-" + currentDate.getDate();
        date.appendChild(document.
        createTextNode(loadedDate));
        var comment = document.createElement("td");
        comment.appendChild(document.createTextNode(attachmentComment));
        row.appendChild(checkbox);
        row.appendChild(file);
        row.appendChild(date);
        row.appendChild(comment);
        table.appendChild(row);

        var oldFile = document.getElementById("fileName");
        oldFile.id = "";
        var copyFileToMainForm = oldFile.cloneNode();
        var id = window.location.search.split("=")[1];
        if (attachmentComment.length === 0) {
            attachmentComment = null;
        }
        var attachmentInfo = {"contactId": parseInt(id), "state": "new", "fileName": name,
            "loadedDate": loadedDate, "comment": attachmentComment};
        copyFileToMainForm.setAttribute("name", JSON.stringify(attachmentInfo));
        copyFileToMainForm.setAttribute("id", name);
        copyFileToMainForm.style.display = "none";
        document.getElementById("editForm").appendChild(copyFileToMainForm);

        var newInput = document.createElement("input");
        newInput.type = "file";
        newInput.name = "fileName";
        newInput.id = "fileName";
        var form = document.forms["addAttachmentForm"];
        form.replaceChild(newInput, form.childNodes[5]);
        document.forms["addAttachmentForm"]["attachmentComment"].value = "";
        document.getElementById("addAttachmentPopUp").style.display = "none";
    }
}

function deleteAttachments() {
    var attachments = [];
    var checkboxes = document.querySelectorAll("input[type=checkbox]:checked[value=old]," +
      "input[type=checkbox]:checked[value=edit], input[type=checkbox]:checked[value=new]");
    for (var i = 0; i < checkboxes.length; i++) {
        attachments.push(checkboxes[i].getAttribute("class"));
    }
    if (attachments.length === 0) {
        alert("You didn't choose attachments (white, yellow or green cell)!");
    } else {
        var table = document.getElementById("attachmentsTable");
        for (var j = 1, row; row = table.rows[j]; j++) {
            var name = table.rows[j].cells[0].childNodes[0].className;
            if (attachments.includes(name)) {
                if (!isNaN(name)) {
                    table.rows[j].cells[1].style.backgroundColor = "red";
                    table.rows[j].cells[0].childNodes[0].value = "deleted";
                } else {
                    var form = document.getElementById("editForm");
                    var input = document.getElementById(name);
                    if (form.contains(input)) {
                        input.remove();
                    }
                    table.deleteRow(j);
                    j--;
                }
            }
        }
    }
}

function tableAttachmentsJson() {
    var table = document.getElementById("attachmentsTable");
    var length = table.rows.length;
    var allAttachments = [];
    for (var i = 1; i < length; i++) {
        var attachmentId = table.rows[i].cells[0].childNodes[0].className;
        var state = table.rows[i].cells[0].childNodes[0].value;
        var fileName = table.rows[i].cells[1].innerHTML;
        var loadedDate = table.rows[i].cells[2].innerHTML;
        var comment = table.rows[i].cells[3].innerHTML;
        if (comment.length === 0) {
            comment = null;
        }
        var attachmentInfo = {
            "attachmentId": parseInt(attachmentId), "contactId": parseInt(id), "state": state,
            "fileName": fileName, "loadedDate": loadedDate, "comment": comment
        };
        allAttachments.push(attachmentInfo);
    }
    document.getElementById("allAttachments").value = JSON.stringify(allAttachments);
}

function downloadAttachment() {
    var attachment = [];
    var checkboxes = document.querySelectorAll("input[type=checkbox]:checked[value=old]");
    for (var i = 0; i < checkboxes.length; i++) {
        attachment.push(checkboxes[i].getAttribute("class"));
    }
    if (attachment.length === 0 || attachment.length > 1) {
        alert("You can download only white attachment!");
    } else {
        var href = "controller/download_attachment?attachment_id=" + attachment[0];
        var download = document.createElement("a");
        download.href = href;
        download.click();
    }
}
