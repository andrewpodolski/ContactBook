"use strict";

document.getElementById("startSearch").addEventListener("click", function () {
  var isValid = validateSearch('searchForm');
  if (isValid) {
    var query = search();
    if (query === "controller/search") {
      var text = "Choose any value!";
      document.getElementById("serverErrors").innerHTML = text;
    } else {
      var res = getJson(query);
      res.then(function (data) {
        if (data === 0) {
          var text = "No results!";
          document.getElementById("serverErrors").innerHTML = text;
        } else {
          closeSearchForm();
          fetch("controller/search_result?page=1&index=3").then(function (resp) {
            var status = resp.status;
            if (status === 200) {
              resp.json().then(renderContacts);
            } else {
              handleValidationErrors(resp)
            }
          });
          createPagination(data, "controller/search_result");
          var closeSearchResult = document.createElement("button");
          closeSearchResult.type = "button";
          closeSearchResult.className = "btn btn-danger btn-ms";
          closeSearchResult.onclick = function () {
            window.location.reload();
          };
          closeSearchResult.appendChild(document.createTextNode("Close"));
          document.getElementById("pagination").appendChild(closeSearchResult);
          var searchOption = document.getElementById("searchOption");
          searchOption.innerHTML = "";
          var p = document.createElement("h3");
          p.appendChild(document.createTextNode("Search options:"));
          searchOption.appendChild(p);
          var ul = document.createElement("ul");
          var params = query.split("?")[1].split("&");
          for (var i = 0; i < params.length; i++) {
            var pair = params[i].split("=");
            var key = pair[0];
            var value = pair[1];
            var li = document.createElement("li");
            li.appendChild(document.createTextNode(key + " - " + value));
            ul.appendChild(li);
          }
          searchOption.appendChild(ul);
        }
      });
    }
  }
});

function search() {
  var name = document.forms["searchForm"]["name"].value;
  var surname = document.forms["searchForm"]["surname"].value;
  var patronymic = document.forms["searchForm"]["patronymic"].value;
  var year = document.forms["searchForm"]["year"].value;
  var month = document.forms["searchForm"]["month"].value;
  var day = document.forms["searchForm"]["day"].value;
  var ageComparison = document.forms["searchForm"]["ageComparison"].value;
  var nationality = document.forms["searchForm"]["nationality"].value;
  var gender = document.forms["searchForm"]["gender"].value;
  var maritalStatus = document.forms["searchForm"]["maritalStatus"].value;
  var webSite = document.forms["searchForm"]["webSite"].value;
  var email = document.forms["searchForm"]["email"].value;
  var placeOfWork = document.forms["searchForm"]["placeOfWork"].value;
  var country = document.forms["searchForm"]["country"].value;
  var city = document.forms["searchForm"]["city"].value;
  var street = document.forms["searchForm"]["street"].value;
  var houseNumber = document.forms["searchForm"]["houseNumber"].value;
  var flatNumber = document.forms["searchForm"]["flatNumber"].value;
  var zipCode = document.forms["searchForm"]["zipCode"].value;

  var searchUrl = "controller/search?";
  if (name.length !== 0)
    searchUrl += ("name=" + name + "&");
  if (surname.length !== 0)
    searchUrl += ("surname=" + surname + "&");
  if (patronymic.length !== 0)
    searchUrl += ("patronymic=" + patronymic + "&");


  if (ageComparison !== "-") {
    searchUrl += ("ageComparison=" + ageComparison + "&");
    if (year !== "year")
      searchUrl += ("year=" + year + "&");
    if (month !== "month")
      searchUrl += ("month=" + month + "&");
    if (day !== "day")
      searchUrl += ("day=" + day + "&");
  }
  if (nationality.length !== 0)
    searchUrl += ("nationality=" + nationality + "&");
  if (gender.length !== 0)
    searchUrl += ("gender=" + gender + "&");
  if (maritalStatus.length !== 0)
    searchUrl += ("maritalStatus=" + maritalStatus + "&");
  if (webSite.length !== 0)
    searchUrl += ("webSite=" + webSite + "&");
  if (email.length !== 0)
    searchUrl += ("email=" + email + "&");
  if (placeOfWork.length !== 0)
    searchUrl += ("placeOfWork=" + placeOfWork + "&");
  if (country.length !== 0)
    searchUrl += ("country=" + country + "&");
  if (city.length !== 0)
    searchUrl += ("city=" + city + "&");
  if (street.length !== 0)
    searchUrl += ("street=" + street + "&");
  if (houseNumber.length !== 0)
    searchUrl += ("houseNumber=" + houseNumber + "&");
  if (flatNumber.length !== 0)
    searchUrl += ("flatNumber=" + flatNumber + "&");
  if (zipCode.length !== 0)
    searchUrl += ("zipCode=" + zipCode + "&");
  searchUrl = searchUrl.substring(0, searchUrl.length - 1);
  return searchUrl;
}