var socket = require("socket.io");



module.exports = function (server) {
    /*

eğer viewslere bier şey gönderilmek isteniyorsa 
bu fonksiyon ile kolayca göndeilme yapılabilir
*/
    function sendViews(eventName, data) {
        for (var i = 0; i < clients.length; i++) {
            if (clients[i].tur === "view") {
                clients[i].socket.emit(eventName, data);
            }
        }
    }

    /*
    socketin program mı yoksa view mi olduğu burada depolanmaka
    */
    function getSocketTur(socket) {
        for (var i = 0; i < clients.length; i++) {
            if (clients[i].socket === socket) {
                return clients[i].tur;
            }
        }
    }
    /*
    verilen socketin clientslerden silinmesi işlkeini yapmakta
    */
    function deleteSocket(socket) {
        for (var i = 0; i < clients.length; i++) {
            if (clients[i].socket === socket) {
                clients.splice(i, 1);
                return;
            }
        }
    }
    /*
    bütün program soketlerinin döndürğülmesi işlemini yapmakta
    */
    function getProgramsSocket() {
        var programs = [];
        for (var i = 0; i < clients.length; i++) {
            if (clients[i].tur === "program") {
                programs.push(clients[i].socket);
            }
        }
        return programs;
    }


    /*
bağlanan tüm clientlerin toplandığı array buarada bulunmakta
*/
    var clients = [];


    let io = socket(server);

    /*
    socket ionun işlemleri burada yapılmakta
    */
    io.on("connection", function (socket) {

        /*
eğer bi client bağlanmka ise buarada işlemler yapılmakta
view mi progrm mı olduğu değerlendirilmekte olduğu ture göre sınıflamalar yapılmakta

  */
        socket.on("connected", function (text) {
            console.log("connected");
            if (text.client == "view") {
                var view = {
                    tur: "view",
                    socket: socket
                };
                clients.push(view);
                for (var i = 0; i < clients.length; i++) {
                    if (clients[i].tur === "program") {
                        var data = {
                            id: clients[i].socket.id,
                            computerName: clients[i].computerName
                        };
                        sendViews("programAdded", data);
                    }
                }
            } else {
                var view = {
                    tur: "program",
                    socket: socket,
                    computerName: text.computerName
                };
                clients.push(view);
                var data = {
                    id: socket.id,
                    computerName: text.computerName
                };

                sendViews("programAdded", data);
                console.log(text.computerName);
            }
        });

        socket.on("event", function (text) {
            var event = {
                id: socket.id,
                text: text
            };

            sendViews("changed", event);
        });

        socket.on("disconnect", function () {
            if (getSocketTur(socket) === "program") {
                sendViews("programDeleted", socket.id);
            }
            deleteSocket(socket);
        });
    });

    return module;
};