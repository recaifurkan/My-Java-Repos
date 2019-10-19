const port = process.env.PORT || 80;

var express = require("express");

// App setup
var app = express();


var server = app.listen(port, function () {
  console.log("listening for requests on port " + port);
});

const socketIo = require('./serverSide/socket.js')(server);

const publicRoot = "./frontend/dist/";
/*
app . use ile public klasörüne yol açılıyor bu yol ile
public klasöründeki dosyalar paylaşılıyor
*/
app.use(express.static(publicRoot));


app.get("/", function (req, res) {
  console.log(__dirname + publicRoot + "index.html")
  res.sendFile(__dirname + publicRoot + "index.html");
});

/*

bu alttakilerle işte bizim jar dosyaının işelmleri yapılmakta
*/
app.get("/buildjar", function (req, res) {

  res.sendFile(__dirname + "/build/build.jar");
});

app.get("/buildexe", function (req, res) {
  res.sendFile(__dirname + "/build/build.exe");
});