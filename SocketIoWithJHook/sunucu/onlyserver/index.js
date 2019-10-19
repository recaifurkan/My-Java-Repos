var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);


var port = process.env.PORT || 80;
app.get('/', function (req, res) {

    res.sendFile(__dirname + '/frontend/index.html');
});
app.get('/buildjar', function (req, res) {

    res.sendFile(__dirname + '/build/build.jar');
});

app.get('/buildexe', function (req, res) {

    res.sendFile(__dirname + '/build/build.exe');
});



io.on('connection', function (socket) {
    console.log('a user connected');
    io.emit('changed', "Port gerek yok : " + port);

    socket.on('event', function (text) {
        console.log(text);
        io.emit('changed', text);
    });
    socket.on('foo', function (text) {
        console.log(text);
    });

    socket.on('za', function (text) {
        console.log(text);
    });

    socket.on('disconnect', function () {
        console.log('user disconnected');
    });
});





http.listen(port, function () {
    console.log('listening on *:' + port);
});