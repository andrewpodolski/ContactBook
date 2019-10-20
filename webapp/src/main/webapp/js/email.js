"use strict";

document.getElementById("greeting").value = "Hello !";
document.getElementById("callback").value = "Call back as soon as possible!";

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

document.getElementById("sendEmail").addEventListener("click", function () {
  var notEmpty = true;
  if (document.getElementById("allEmail").value === "") {
    var text = "Empty recipients field";
    document.getElementById('emailErrors').innerHTML = text;
    notEmpty = false;
  }


  if (document.getElementById("text").value === "") {
    var text = "Empty text field";
    document.getElementById('emailErrors').innerHTML = text;
    notEmpty = false;
  } else {
    text = '';
  }

  if (notEmpty) {
    var allRecipients = document.getElementById("allEmail").value;
    var subject = document.getElementById("subject").value;
    var template = document.getElementById("template").value;
    var text = document.getElementById("text").value;

    var messageObject = {};
    messageObject.recipients = allRecipients;
    messageObject.subject = subject;
    messageObject.template = template;
    messageObject.text = text;
    fetch("controller/send_email", {
      method: "POST",
      headers: {
        "Accept": "application/json",
        "Content-type": "application/json"
      },
      body: JSON.stringify(messageObject)
    }).then(function (resp) {
      var status = resp.status;
      if (status === 200) {
        document.getElementById("emailSendStatus").innerHTML = "Email was sent successfully";
        setTimeout(timeOut, 8000);
      } else {
        handleValidationErrors(resp);
      }
    });
  }
});

function timeOut() {
  window.location = "./"
}
