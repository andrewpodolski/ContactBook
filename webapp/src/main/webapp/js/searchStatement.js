"use strict";

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