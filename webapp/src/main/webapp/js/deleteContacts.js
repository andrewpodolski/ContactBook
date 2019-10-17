function deleteContacts() {
  var idArray = [];
  var checkboxes = document.querySelectorAll("input[type=checkbox]:checked");
  for (var i = 0; i < checkboxes.length; i++) {
    idArray.push(checkboxes[i].value);
  }
  if (idArray.length === 0) {
    text = "Choose any contact to delete";
    document.getElementById("contactErrors").innerHTML = text;
  } else {
    text = "";
    fetch("controller/delete_contacts", {
      method: "POST",
      headers: {
        "Accept": "application/json",
        "Content-type": "application/json"
      },
      body: JSON.stringify(idArray)
    }).then(function (res) {
    });
    checkboxes = document.getElementsByName("check");
    for (i = 0; i < checkboxes.length; i++)
      checkboxes[i].checked = false;
    window.location.reload();
  }
}