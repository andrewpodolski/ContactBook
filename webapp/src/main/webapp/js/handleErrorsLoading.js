"use strict";

function handleErrorsLoading(resp) {
  resp.json().then(function (errors) {
    document.getElementById("contactErrors").innerHTML = errors;
  });
}
