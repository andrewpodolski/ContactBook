"use strict";

function getJson(url) {
    return fetch(url).then(function (res) {
        return res.json();
    })
}