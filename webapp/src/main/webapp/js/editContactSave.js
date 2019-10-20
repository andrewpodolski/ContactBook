"use strict";
document.getElementById("deleteButton").addEventListener("click", deletePhones);
document.getElementById("openAddPopUp").addEventListener("click", function openPopUp() {
  document.getElementById("addPhonePopUp").style.display = "block";
  document.getElementById("editPhonePopUp").style.display = "none";
  document.getElementById("addAttachmentPopUp").style.display = "none";
  document.getElementById("editAttachmentPopUp").style.display = "none";
});
document.getElementById("openEditPopUp").addEventListener("click", editPhoneFillInputs);
document.getElementById("closeAddPopUp").addEventListener("click", function closePopUp() {
  document.getElementById("addPhonePopUp").style.display = "none";
});
document.getElementById("closeEditPopUp").addEventListener("click", function closePopUp() {
  document.getElementById("editPhonePopUp").style.display = "none";
});
document.getElementById("createPhone").addEventListener("click", createPhone);
document.getElementById("editPhone").addEventListener("click", editPhone);

document.getElementById("deleteAttachmentButton").addEventListener("click", deleteAttachments);
document.getElementById("openAddAttachmentPopUp").addEventListener("click", function openPopUp() {
  document.getElementById("addAttachmentPopUp").style.display = "block";
  document.getElementById("editAttachmentPopUp").style.display = "none";
  document.getElementById("addPhonePopUp").style.display = "none";
  document.getElementById("editPhonePopUp").style.display = "none";
});
document.getElementById("openEditAttachmentPopUp").addEventListener("click", editAttachmentFillInputs);
document.getElementById("closeAddAttachmentPopUp").addEventListener("click", function closePopUp() {
  document.getElementById("addAttachmentPopUp").style.display = "none";
});
document.getElementById("closeEditAttachmentPopUp").addEventListener("click", function closePopUp() {
  document.getElementById("editAttachmentPopUp").style.display = "none";
});
document.getElementById("createAttachment").addEventListener("click", createAttachment);
document.getElementById("editAttachment").addEventListener("click", editAttachment);
document.getElementById("downloadAttachmentsButton").addEventListener("click", downloadAttachment);

document.getElementById("finalEdit").addEventListener("click", function () {
  if (validateContact('editForm')) {
    tablePhonesJson();
    tableAttachmentsJson();
    savePhoto();
    var editForm = document.getElementById("editForm");
    var formData = new FormData(editForm);
    fetch("controller/edit_contact", {
      method: "POST",
      body: formData
    }).then(function (resp) {
      var status = resp.status;
      if (status === 200) {
        window.location = "./";
      } else {
        handleValidationErrors(resp);
        handleErrorsLoading(resp);
      }
    });
  }
});
