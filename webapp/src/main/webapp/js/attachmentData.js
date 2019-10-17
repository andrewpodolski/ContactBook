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
