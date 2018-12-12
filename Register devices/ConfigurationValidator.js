function validateConfigState() {

    var IPpattern = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    var portPattern = /^\d{4}$/;
   

    var IPv4 = document.getElementById("ip_address").value;
    var port = document.getElementById("port_number").value;


    IPv4 = IPv4.trim();
    port = port.trim();
    if (!IPv4.match(IPpattern))
        alert("IP syntax malformed");

    if (!port.match(portPattern))
        alert("Port number is inaccurate");

    if(IPv4.match(IPpattern) && port.match(portPattern))

}