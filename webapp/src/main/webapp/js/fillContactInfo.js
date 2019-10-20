function fillInputContact() {
  fetch("controller/contact_info?contact_id=" + id).then(function (resp) {
    var status = resp.status;
    if (status === 200) {
      resp.json().then(function (data) {
        document.getElementById("id").value = id;
        document.getElementById("name").value = data.name;
        document.getElementById("surname").value = data.surname;
        document.getElementById("patronymic").value = data.patronymic;
        var year = "year", month = "month", day = "day";
        if (data.birthday !== null) {
          var dateArray = data.birthday.split("-");
          year = dateArray[0];
          month = parseInt(dateArray[1]);
          day = parseInt(dateArray[2]);
        }
        document.getElementById("yearSelect").value = year;
        document.getElementById("monthSelect").value = month;
        document.getElementById("daySelect").value = day;
        document.getElementById("nationality").value = data.nationality;
        var g = data.gender;
        if (g === "male") {
          document.getElementById("genderMale").checked = true;
        }
        if (g === "female") {
          document.getElementById("genderFemale").checked = true;
        }
        var m = data.maritalStatus;
        if (m === "married") {
          document.getElementById("maritalStatusMarried").checked = true;
        }
        if (m === "single") {
          document.getElementById("maritalStatusSingle").checked = true;
        }
        document.getElementById("email").value = data.email;
        document.getElementById("webSite").value = data.webSite;
        document.getElementById("placeOfWork").value = data.placeOfWork;
        document.getElementById("country").value = data.address.country;
        document.getElementById("city").value = data.address.city;
        document.getElementById("street").value = data.address.street;
        document.getElementById("houseNumber").value = data.address.houseNumber;
        document.getElementById("flatNumber").value = data.address.flatNumber;
        document.getElementById("zipCode").value = data.address.zipCode;
      });
    } else {
      handleValidationErrors(resp)
    }
  });
}

fillInputContact();

function insertPhoto() {
  fetch("controller/contact_image?contact_id=" + id).then(function (resp) {
    var status = resp.status;
    if (status === 200) {
      resp.json().then(function (data) {
        var image = new Image();
        image.src = 'data:image/png;base64,' + data;
        document.getElementById("contactPhoto").src = image.src;
      });
    } else {
      handleValidationErrors(resp);
    }
  });
}

insertPhoto();
