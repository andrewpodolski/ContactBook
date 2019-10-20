function defaultImage() {
  fetch("controller/default_image").then(function (response) {
    var status = response.status;
    if (status === 200) {
      response.json().then(function (data) {
        var image = new Image();
        image.src = 'data:image/png;base64,' + data;
        document.getElementById("contactPhoto").src = image.src;
      });
    } else {
      handleValidationErrors(response);
    }
  });
}

defaultImage();

window.onload = function () {
  var image = document.getElementById("contactPhoto");
  var upload = document.getElementById("uploadPhoto");
  image.onclick = function () {
    upload.click();
  };
};

function addPhoto() {
  var preview = document.getElementById('contactPhoto');
  var file = document.querySelector('input[type=file]').files[0];
  var fileReader = new FileReader();

  fileReader.onloadend = function () {
    preview.src = fileReader.result;
  };

  if (file) {
    fileReader.readAsDataURL(file);
  }
}

document.getElementById("addButton").addEventListener("click", function () {
  var name = document.forms["addForm"]["name"].value;
  var surname = document.forms["addForm"]["surname"].value;
  var patronymic = document.forms["addForm"]["patronymic"].value;
  var year = document.forms["addForm"]["year"].value;
  var month = document.forms["addForm"]["month"].value;
  var day = document.forms["addForm"]["day"].value;
  var nationality = document.forms["addForm"]["nationality"].value;
  var gender = document.forms["addForm"]["gender"].value;
  var maritalStatus = document.forms["addForm"]["maritalStatus"].value;
  var email = document.forms["addForm"]["email"].value;
  var webSite = document.forms["addForm"]["webSite"].value;
  var placeOfWork = document.forms["addForm"]["placeOfWork"].value;
  var country = document.forms["addForm"]["country"].value;
  var city = document.forms["addForm"]["city"].value;
  var street = document.forms["addForm"]["street"].value;
  var houseNumber = document.forms["addForm"]["houseNumber"].value;
  var flatNumber = document.forms["addForm"]["flatNumber"].value;
  var zipCode = document.forms["addForm"]["zipCode"].value;

  if (validateContact("addForm")) {
    var contact = {};
    var address = {};
    contact.id = 0;
    contact.name = name;
    contact.surname = surname;
    contact.patronymic = patronymic;
    contact.birthday = year + "-" + month + "-" + day;
    contact.nationality = nationality;
    contact.gender = gender;
    contact.maritalStatus = maritalStatus;
    contact.email = email;
    contact.webSite = webSite;
    contact.placeOfWork = placeOfWork;
    address.country = country;
    address.city = city;
    address.street = street;
    address.houseNumber = houseNumber;
    address.flatNumber = flatNumber;
    address.zipCode = zipCode;
    contact.address = address;
    contact.imageName = document.getElementById("contactPhoto").src;

    fetch("controller/create_contact", {
      method: "POST",
      headers: {
        "Accept": "application/json",
        "Content-type": "application/json"
      },
      body: JSON.stringify(contact)
    }).then(function (response) {
      var status = response.status;
      if (status === 200) {
        window.location = "./";
      } else {
        handleValidationErrors(response);
      }
    });
  }
})