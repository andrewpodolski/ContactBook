"use strict";

function validateAttachmentAdd() {
  var fileName = document.forms["addAttachmentForm"]["fileName"].value;
  var attachmentComment = document.forms["addAttachmentForm"]["attachmentComment"].value;
  var text;

  if (fileName.length === 0 || fileName.length > 25) {
      text = 'File name must be less than 25 symbols';
    document.getElementById("validateAttachmentName").innerHTML = text;
    return false;
  } else {
    text = "";
  }

  if (attachmentComment.length > 45) {
    text = 'Comment must be less than 25 symbols';
    document.getElementById("validateAttachmentComment").innerHTML = text;
  } else {
    text = "";
  }
  return true;
}

function validateAttachmentEdit() {
  var fileName = document.forms["editAttachmentForm"]["fileName2"].value;
  var attachmentComment = document.forms["editAttachmentForm"]["attachmentComment2"].value;
  var text;

  if (fileName.length === 0 || fileName.length > 25) {
      text = 'File name must be less than 25 symbols';
      document.getElementById("vAttachName").innerHTML = text;
      return false;
  } else {
      text = "";
  }

  if (attachmentComment.length > 45) {
      text = 'Comment must be less than 25 symbols';
      document.getElementById("vAttachComment").innerHTML = text;
  } else {
      text = "";
  }
  return true;
}