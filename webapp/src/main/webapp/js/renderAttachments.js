function renderAttachments(attachments) {
  var rowTemplate = document.getElementById("rowTemplate2").innerHTML;
  var attachmentsHTML = "";
  attachments.forEach(function (attachment) {
    attachmentsHTML += Mustache.render(rowTemplate, attachment);
  });
  document.getElementById("attachments").innerHTML = attachmentsHTML;
}


fetch("controller/contacts_attachments?contact_id=" + id).then(function (resp) {
  var status = resp.status;
  if (status === 200) {
    resp.json().then(renderAttachments);
  } else {
    console.log(resp);
  }
});
