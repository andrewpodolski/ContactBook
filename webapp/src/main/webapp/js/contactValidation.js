"use strict";

function validateContact(formName) {
  var name = document.forms[formName]["name"].value;
  var surname = document.forms[formName]["surname"].value;
  var patronymic = document.forms[formName]["patronymic"].value;
  var year = document.forms[formName]["year"].value;
  var month = document.forms[formName]["month"].value;
  var day = document.forms[formName]["day"].value;
  var nationality = document.forms[formName]["nationality"].value;
  var email = document.forms[formName]["email"].value;
  var webSite = document.forms[formName]["webSite"].value;
  var placeOfWork = document.forms[formName]["placeOfWork"].value;
  var country = document.forms[formName]["country"].value;
  var city = document.forms[formName]["city"].value;
  var street = document.forms[formName]["street"].value;
  var houseNumber = document.forms[formName]["houseNumber"].value;
  var flatNumber = document.forms[formName]["flatNumber"].value;
  var zipCode = document.forms[formName]["zipCode"].value;
  var hasNumber = /\d/;
  var text;


  if (name.length < 1 || name.length > 20 || hasNumber.test(name)) {
    text = 'Field is required! Must be less then 20 symbols! Can\'t have digits!';
    document.getElementById("validateName").innerHTML = text;

  } else {
    text = "";
  }

  if (surname.length < 1 || surname.length > 20 || hasNumber.test(surname)) {
    text = 'Field is required! Must be less then 20 symbols! Can\'t have digits!';
    document.getElementById("validateSurname").innerHTML = text;

  } else {
    text = "";
  }

  if (patronymic.length > 20 || hasNumber.test(patronymic)) {
    text = 'Must be less then 20 symbols! Can\'t have digits!';
    document.getElementById("validatePatronymic").innerHTML = text;

  } else {
    text = "";
  }

  if ((year === "year" && month === "month" && day === "day")) {
  } else if (year !== "year" && month !== "month" && day !== "day") {
    var birthday = year + "-" + month + "-" + day;
    var date = new Date(birthday);
    if (parseInt(year) !== date.getFullYear() || parseInt(month) !== (date.getMonth() + 1) || parseInt(day) !== date.getDate()) {
      text = "This date doesn't exists";
      document.getElementById("validateBorn").innerHTML = text;

    } else {
      text = "";
    }

  } else {
    text = "Invalid date";
    document.getElementById("validateBorn").innerHTML = text;

  }
  if (year !== "year" && month !== "month" && day !== "day") {
    var date = new Date();
    if (parseInt(year) === date.getFullYear()) {
      if (parseInt(month) > date.getMonth() && parseInt(day) > date.getDate()) {
        text = "Invalid date";
        document.getElementById("validateBorn").innerHTML = text;
        return false;
      } else {
        text = "";
      }
    }
  }

  if (nationality.length > 20 || hasNumber.test(nationality)) {
    text = 'Must be less then 20 symbols!Can\'t have digits!';
    document.getElementById("validateNat").innerHTML = text;

  } else {
    text = "";
  }

  if (!email.includes("@") || email.length > 45 || email.length === 0) {
    text = 'Field is required! Must be less then 45 symbols.Must include \'@\'';
    document.getElementById("validateEmail").innerHTML = text;

  } else {
    text = "";
  }

  if (webSite.length > 45) {
    alert("Max symbols for field surname - 45!");
    text = 'Must be less then 45 symbols';
    document.getElementById("validateWeb").innerHTML = text;

  } else {
    text = "";
  }

  if (placeOfWork.length > 30) {
    text = 'Must be less then 30 symbols';
    document.getElementById("validatePlace").innerHTML = text;

  } else {
    text = "";
  }

  if (country.length > 30 || hasNumber.test(country)) {
    text = "Must be less then 30 symbols!Can't have digits!";
    document.getElementById("validateCountry").innerHTML = text;

  } else {
    text = ""
  }

  if (city.length > 30 || hasNumber.test(city)) {
    text = "Must be less then 30 symbols!Can't have digits!";
    document.getElementById("validateCity").innerHTML = text;

  } else {
    text = "";
  }

  if (street.length > 20) {
    text = "Must be less then 20 symbols!";
    document.getElementById("validateStreet").innerHTML = text;

  } else {
    text = "";
  }

  if (houseNumber.length > 10) {
    text = "Must be less then 10 symbols";
    document.getElementById("validateHouse").innerHTML = text;

  } else {
    text = "";
  }


  if (flatNumber.length > 10) {
    text = "Must be less then 10 symbols";
    document.getElementById("validateFlat").innerHTML = text;

  } else {
    text = "";
  }


  if (zipCode.length > 10) {
    text = "Must be less then 10 symbols";
    document.getElementById("validateZip").innerHTML = text;

  } else {
    text = "";
  }

  return true;
}