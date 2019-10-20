"use strict";

function validateSearch(formName) {
  var name = document.forms[formName]["name"].value;
  var surname = document.forms[formName]["surname"].value;
  var patronymic = document.forms[formName]["patronymic"].value;
  var year = document.forms[formName]["year"].value;
  var month = document.forms[formName]["month"].value;
  var day = document.forms[formName]["day"].value;
  var ageComparison = document.forms[formName]["ageComparison"].value;
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

  if (name.length > 20) {
    alert("Max length of name - 20 symbols");
    return false;
  }

  if (surname.length > 20) {
    alert("Max length of surname - 20 symbols");
    return false;
  }

  if (patronymic.length > 20) {
    alert("Max length of name - 20 symbols");
    return false;
  }

  if (ageComparison !== "-") {
    if (year === "year" && month === "month" && day === "day") {
      alert("Choose at least one parameter!");
      return false;
    }
  }

  if (nationality.length > 20) {
    alert("Max length of nationality - 20 symbols");
    return false;
  }

  if (email.length > 25) {
    alert("Max length of email - 25 symbols");
    return false;
  }

  if (webSite.length > 45) {
    alert("Max length of website - 45 symbols");
    return false;
  }

  if (placeOfWork.length > 40) {
    alert("Max length of place of work - 40 symbols");
    return false;
  }

  if (country.length > 20) {
    alert("Max length of country - 20 symbols");
    return false;
  }

  if (city.length > 20) {
    alert("Max length of city - 20 symbols");
    return false;
  }

  if (street.length > 25) {
    alert("Max length of street - 25 symbols");
    return false;
  }

  if (houseNumber.length > 10) {
    alert("Max length of number of house - 10 symbols");
    return false;
  }

  if (flatNumber.length > 10) {
    alert("Max length of number of flat - 10 symbols");
    return false;
  }

  if (zipCode.length > 10) {
    alert("Max length of zipcode - 10 symbols");
    return false;
  }

  return true;
}