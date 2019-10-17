"use strict";
var years = [];
for (var i = 2019; i >= 1900; i--) {
    years.push(i);
}
var yearSelect = document.querySelector("#yearSelect");
for (i in years) {
    yearSelect.options[yearSelect.options.length] = new Option(years[i], years[i]);
}

var months = {
    1 : "January", 2 : "February", 3 : "March", 4 : "April", 5 : "May", 6 : "June",
    7 : "July", 8 : "August", 9 : "September", 10 : "October", 11 : "November", 12 : "December"
};

var monthSelect = document.querySelector("#monthSelect");
for (i in months) {
    monthSelect.options[monthSelect.options.length] = new Option(months[i], i);
}

var days = [];
for (i = 1; i <= 31; i++) {
    days.push(i);
}

var daySelect = document.querySelector("#daySelect");
for (i in days) {
    daySelect.options[daySelect.options.length] = new Option(days[i], days[i]);
}