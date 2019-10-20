window.onload = function () {
  var image = document.getElementById("contactPhoto");
  var upload = document.getElementById("uploadPhoto");
  image.onclick = function () {
    upload.click();
  };
};

function changePhoto() {
  var preview = document.getElementById('contactPhoto');
  var file = document.querySelector('input[type=file]').files[0];
  var fileReader = new FileReader();

  fileReader.onloadend = function () {
    preview.src = fileReader.result;
  };

  if (file) {
    fileReader.readAsDataURL(file);
  }
}

function savePhoto() {
  document.getElementById("photo").value = document.getElementById("contactPhoto").src;
}