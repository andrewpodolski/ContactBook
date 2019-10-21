"use strict";

function validatePhoneAdd() {
  var countryCode = document.forms["addPhoneForm"]["countryCode"].value;
  var operatorCode = document.forms["addPhoneForm"]["operatorCode"].value;
  var phoneNumber = document.forms["addPhoneForm"]["phoneNumber"].value;
  var comment = document.forms["addPhoneForm"]["comment"].value;
  var text;

  if (countryCode.length > 5 || isNaN(countryCode) || countryCode.length === 0) {
    text = "Country code must be less then 4 symbols!Cant\'t have letters!";
    document.getElementById("validateCountryCode").innerHTML = text;
  } else {
    text = "";
  }

  if (operatorCode.length > 5 || isNaN(operatorCode) || countryCode.length === 0) {
    text = "Operator code must be less then 4 symbols!Cant\'t have letters!";
    document.getElementById("validateOperatorCode").innerHTML = text;
  } else {
    text = "";
  }

  if (phoneNumber.length < 5 || phoneNumber.length > 10 || isNaN(phoneNumber) || countryCode.length === 0) {
    text = "Country code must be less then 4 symbols!Cant\'t have letters!";
    document.getElementById("validatePhone").innerHTML = text;
    return false;
  } else {
    text = "";
  }

  if (comment.length > 30) {
    text = "Comment must be less then 30 symbols!";
    document.getElementById("validateComment").innerHTML = text;
    return false;
  } else {
    text = "";
  }
  return true;
}

function validatePhoneEdit() {
  var countryCode = document.forms["editPhoneForm"]["countryCode2"].value;
  var operatorCode = document.forms["editPhoneForm"]["operatorCode2"].value;
  var phoneNumber = document.forms["editPhoneForm"]["phoneNumber2"].value;
  var comment = document.forms["editPhoneForm"]["comment2"].value;
  var text;


  if (countryCode.length > 5 || isNaN(countryCode) || countryCode.length === 0) {
    text = "Country code must be less then 4 symbols!Cant\'t have letters!";
    document.getElementById("validateCountryCode2").innerHTML = text;
  } else {
    text = "";
  }

  if (operatorCode.length > 5 || isNaN(operatorCode) || operatorCode.length === 0) {
    text = "Operator code must be less then 4 symbols!Cant\'t have letters!";
    document.getElementById("validateOperatorCode2").innerHTML = text;
  } else {
    text = "";
  }

  if (phoneNumber.length < 5 || phoneNumber.length > 15 || isNaN(phoneNumber) || phoneNumber.length === 0) {
    text = "Country code must be less then 4 symbols!Cant\'t have letters!";
    document.getElementById("validatePhone2").innerHTML = text;
    return false;
  } else {
    text = "";
  }

  if (comment.length > 30) {
    text = "Comment must be less then 30 symbols!";
    document.getElementById("validateComment2").innerHTML = text;
    return false;
  } else {
    text = "";
  }

  return true;
}