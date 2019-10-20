function openAddForm() {
  window.location = "createContact.html";
}

function openSearchForm() {
  document.getElementById("contactsTable").style.display = "none";
  document.getElementById("emailForm").style.display = "none";
  document.getElementById("searchForm").style.display = "block";
}

function closeSearchForm() {
  document.getElementById("searchForm").style.display = "none";
  document.getElementById("contactsTable").style.display = "block";
}