function renderPhones(phones) {
  var rowTemplate = document.getElementById("rowTemplate").innerHTML;
  var phonesHTML = "";
  phones.forEach(function (phone) {
    phonesHTML += Mustache.render(rowTemplate, phone);
  });
  document.getElementById("phones").innerHTML = phonesHTML;
}

var id = window.location.search.split("=")[1];
fetch("controller/contacts_phones?contact_id=" + id).then(function (resp) {
  var status = resp.status;
  if (status === 200) {
    resp.json().then(renderPhones);
  } else {
    console.log(resp);
  }
});