"use strict";

function handleValidationErrors(resp) {
  resp.json().then(function (errors) {
    document.getElementById("serverErrors").innerHTML = errors;
  });
}
